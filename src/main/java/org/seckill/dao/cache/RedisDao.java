package org.seckill.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.seckill.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/10/31 9:28
 * 功能
 */
public class RedisDao {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 类似于数据库连接池
     */
    private JedisPool jedisPool;

    public RedisDao(String ip, int port) {
        jedisPool = new JedisPool(ip, port);
    }


    /**
     * //全局定义运行期Schema 使用protostuff 性能几乎没有损失
     */
    private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);

    public Seckill getSeckill(long seckillId) {
        //redis操作
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "seckill:" + seckillId;
                //并没有实现内部序列化操作
                // get->byte[] ->反序列化 -> Object(Seckill)  存储返回都是二进制数组,和语言无关
                //采用自定义序列化
                //protostuff : pojo
                byte[] bytes = jedis.get(key.getBytes());
                if (bytes!=null){
                    //空对象
                    Seckill seckill = schema.newMessage();
                    ProtostuffIOUtil.mergeFrom(bytes,seckill,schema);
                    //seckill被反序列  被赋值
                    return seckill;
                }
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * //当缓存没有时
     * @param seckill
     * @return
     */
    public String putSeckill(Seckill seckill) {
        // set Object(seckill) -> 序列化  -> byte[]
        try{
            Jedis jedis = jedisPool.getResource();
            try{
                String key = "seckill:"+seckill.getSeckillId();
                //LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE) 缓存器
                byte[] bytes = ProtostuffIOUtil.toByteArray(
                        seckill,schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                //超时缓存
                // 单位秒 一小时
                int timeout = 60*60;
                //正常返回ok，错误返回错误信息
                return jedis.setex(key.getBytes(), timeout, bytes);
            }finally {
                jedis.close();
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return null;
    }
}
