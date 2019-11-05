package org.seckill.dao;

import org.seckill.entity.Goods;

import java.util.List;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/11/5 11:03
 * 功能
 */
public interface GoodsDao {
    List<Goods> findByAll();
}
