package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/11/5 11:11
 * 功能
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"" +
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"
})
public class GoodsServiceTest {

    @Autowired
    private GoodsService goodsService;
    @Test
    public void getAllGoods() {
        System.out.println(goodsService.GetAllGoods());
        /**
         * [
         * Goods{id=1, goodsid=1000000, goodsname='iPhoneX', goodsprice=1000.0, chengjiao=0, goodsnumbers=10000, images1='https://gd1.alicdn.com/imgextra/i1/708804205/O1CN01zRkMft1gvw1OmCMBj_!!708804205.jpg', images2='https://gd3.alicdn.com/imgextra/i3/708804205/O1CN01LVF6J91gvw1OELfCJ_!!708804205.jpg', images3='https://gd2.alicdn.com/imgextra/i2/708804205/O1CN01kKmBPl1gvw1PkeXwD_!!708804205.jpg'},
         * Goods{id=2, goodsid=1000001, goodsname='红米K20Pro', goodsprice=500.0, chengjiao=0, goodsnumbers=10000, images1='https://gd3.alicdn.com/imgextra/i2/292911747/O1CN01HarHNs1OmATwYesQp_!!292911747.jpg', images2='https://gd3.alicdn.com/imgextra/i3/292911747/O1CN01xNSBMA1OmATtW7QU1_!!https://gd3.alicdn.com/imgextra/i3/292911747/O1CN01Ved9nM1OmASXq2tT4_!!292911747.jpg', images3='https://gd3.alicdn.com/imgextra/i3/292911747/O1CN01jWLXHC1OmASWJWiQE_!!292911747.jpg'},
         * Goods{id=3, goodsid=1000002, goodsname='小米9Pro', goodsprice=300.0, chengjiao=0, goodsnumbers=10000, images1='https://gd1.alicdn.com/imgextra/i1/292911747/O1CN01hg33LT1OmAUOss5FM_!!292911747.jpg', images2='https://gd1.alicdn.com/imgextra/i1/292911747/O1CN01bY98Zw1OmAUMlj6kL_!!292911747.jpg', images3='https://gd2.alicdn.com/imgextra/i2/292911747/O1CN01VmcHe21OmAUjgAWMT_!!292911747.jpg'},
         * Goods{id=4, goodsid=1000003, goodsname='OnePlus/一加7TPro', goodsprice=3000.0, chengjiao=0, goodsnumbers=10000, images1='https://gd3.alicdn.com/imgextra/i4/292911747/O1CN01TT6vWK1OmAUdYq145_!!292911747.jpg', images2='https://gd2.alicdn.com/imgextra/i2/292911747/O1CN01JtKoeW1OmAUcYewJI_!!292911747.png', images3='https://gd2.alicdn.com/imgextra/i2/292911747/O1CN01qhTVZa1OmAUalEfKn_!!292911747.jpg'},
         * Goods{id=5, goodsid=1000004, goodsname='HUAWEI Mate 30 Pro', goodsprice=5000.0, chengjiao=0, goodsnumbers=10000, images1='https://gd1.alicdn.com/imgextra/i1/292911747/O1CN01FaLmm61OmAUsYr2Tw_!!292911747.jpg', images2='https://gd3.alicdn.com/imgextra/i3/292911747/O1CN01WsLbHh1OmAUCyjoRe_!!292911747.jpg', images3='https://gd1.alicdn.com/imgextra/i1/292911747/O1CN018BOY3R1OmAUGg9PHT_!!292911747.jpg'}]
         */
    }
}