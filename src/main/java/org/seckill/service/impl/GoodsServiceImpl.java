package org.seckill.service.impl;

import org.seckill.dao.GoodsDao;
import org.seckill.entity.Goods;
import org.seckill.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/11/5 11:09
 * 功能
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;

    @Override
    public List<Goods> GetAllGoods() {
        return goodsDao.findByAll();
    }
}
