package org.seckill.entity;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/11/5 10:56
 * 功能 商品
 */
public class Goods {
    private long id;
    private long goodsid;
    private String goodsname;
    private float goodsorig; //原价
    private float goodsseckillprice;//秒杀价
    private int chengjiao;
    private int goodsnumbers;
    private String images1;
    private String images2;
    private String images3;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(long goodsid) {
        this.goodsid = goodsid;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public float getGoodsorig() {
        return goodsorig;
    }

    public void setGoodsorig(float goodsorig) {
        this.goodsorig = goodsorig;
    }

    public float getGoodsseckillprice() {
        return goodsseckillprice;
    }

    public void setGoodsseckillprice(float goodsseckillprice) {
        this.goodsseckillprice = goodsseckillprice;
    }

    public int getChengjiao() {
        return chengjiao;
    }

    public void setChengjiao(int chengjiao) {
        this.chengjiao = chengjiao;
    }

    public int getGoodsnumbers() {
        return goodsnumbers;
    }

    public void setGoodsnumbers(int goodsnumbers) {
        this.goodsnumbers = goodsnumbers;
    }

    public String getImages1() {
        return images1;
    }

    public void setImages1(String images1) {
        this.images1 = images1;
    }

    public String getImages2() {
        return images2;
    }

    public void setImages2(String images2) {
        this.images2 = images2;
    }

    public String getImages3() {
        return images3;
    }

    public void setImages3(String images3) {
        this.images3 = images3;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", goodsid=" + goodsid +
                ", goodsname='" + goodsname + '\'' +
                ", goodsorig=" + goodsorig +
                ", goodsseckillprice=" + goodsseckillprice +
                ", chengjiao=" + chengjiao +
                ", goodsnumbers=" + goodsnumbers +
                ", images1='" + images1 + '\'' +
                ", images2='" + images2 + '\'' +
                ", images3='" + images3 + '\'' +
                '}';
    }
}
