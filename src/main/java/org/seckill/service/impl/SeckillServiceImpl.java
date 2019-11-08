package org.seckill.service.impl;

import org.apache.commons.collections.MapUtils;
import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dao.cache.RedisDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStatEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/10/21 10:19
 * @ Component 所有的组件 在不知道是Service还是Dao时使用
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SuccessKilledDao successKilledDao;

    @Autowired
    private RedisDao redisDao;
    /**
     *  混淆加盐
     */
    private final String slat = "22sarrw";

    @Override
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 5);
    }

    @Override
    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {
        //优化点： 缓存优化 : 超时的基础上维护一致性
        /**
         * get from cache
         * if null
         *   get db
         * else
         *   put cache
         *login
         */
        //1 访问redis
        Seckill seckill = redisDao.getSeckill(seckillId);
        if (seckill == null) {
            //2 访问数据库
            seckill = seckillDao.queryById(seckillId);
            if (seckill == null) {
                //查不到 那该产品ID
                return new Exposer(false, seckillId);
            } else {
                //3 放入redis
                redisDao.putSeckill(seckill);
            }
        }

        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        //系统当前时间
        Long nowTime = System.currentTimeMillis();
        if (nowTime < startTime.getTime() || nowTime > endTime.getTime()) {
            return new Exposer(false, seckillId, nowTime, startTime.getTime(), endTime.getTime());
        }
        //库存判断
        if (seckill.getNumber()==0){
            return new Exposer(false, seckillId, false);
        }
        nowTime = null;
        String md5 = getMd5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    private String getMd5(long seckillId) {
        String base = seckillId + "/" + slat;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }

    /**
     * 使用注解控制事务方法的优点：
     * 1.开发团队达成一致约定，明确标注事务方法的编程风格
     * 2.保证事务方法的执行时间尽可能短,不要穿插其他RPC/HTTP请求
     * 3.不是所有的方法都需要事务，如只有一条修改操作，只读操作不需要事务控制
     */
    @Override
    @Transactional
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException {
        if (md5 == null || !md5.equals(getMd5(seckillId))) {
            throw new SeckillException("seckill data rewrite");
        }
        //执行秒杀逻辑 : 减库存+记录购买行为
        Date nowTime = new Date();
        try {
            //减库存成功 记录购买行为
            int insertCount = successKilledDao.insertSuccessKilled(seckillId,
                    userPhone);
            if (insertCount <= 0) {
                //重复秒杀
                throw new RepeatKillException("seckill repeated");
            } else {
                //减库存,热点商品竞争
                int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
                if (updateCount <= 0) {
                    //没有更新到记录  秒杀结束 rollback
                    throw new SeckillCloseException("seckill is close");
                } else {
                    //秒杀成功 commit
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, successKilled);
                }
            }
        } catch (SeckillCloseException | RepeatKillException el) {
            throw el;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            //所有编译期异常   转化为   运行期异常 spring 声明式事务会做rollback回滚
            throw new SeckillException("seckill inner error:" + e.getMessage());
        }
    }


    @Override
    public SeckillExecution executeSeckillProcedure(long seckillId, long userPhone, String md5) throws SeckillException {
        if (md5 == null || !md5.equals(getMd5(seckillId))) {
            return new SeckillExecution(seckillId, SeckillStatEnum.DATA_REWRITE);
        }
        Date killTime = new Date();
        Map<String, Object> map = new HashMap<>(10);
        map.put("seckillId", seckillId);
        map.put("phone", userPhone);
        map.put("killTime", killTime);
        map.put("result", null);
        try {
            seckillDao.killByProcedure(map);
            //获取结果
            int result = MapUtils.getInteger(map, "result", -2);
            //result = (int)map.get("result");
            if (result == 1) {
                SuccessKilled sk = successKilledDao.
                        queryByIdWithSeckill(seckillId, userPhone);
                /*
                 * 更新redis缓存中库存数量
                 */
                Seckill seckill = redisDao.getSeckill(seckillId);
                seckill.setNumber(seckill.getNumber()-1);
                redisDao.putSeckill(seckill);

                return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, sk);
            } else {
                return new SeckillExecution(seckillId, SeckillStatEnum.stateOf(result));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new SeckillExecution(seckillId, SeckillStatEnum.INNER_ERROR);
        }
    }

    @Override
    public int getSeckillByIdandPhone(long seckillId, long userPhone) {
        return successKilledDao.getSeckillByIdandPhone(seckillId,userPhone);
    }
}
