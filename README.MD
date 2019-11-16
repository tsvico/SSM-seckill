### 详情页流程逻辑
![](https://i.loli.net/2019/10/24/Q2uTtFRaLGvC9bn.png)

### Restful 示例

<img src="https://i.loli.net/2019/10/24/SjpZbfyD8mzFPQA.png" width="50%" align=center alt="Restful接口">

![](https://i.loli.net/2019/10/24/iGeayd9K24sqVjt.png)

### 前端交互设计
![](https://i.loli.net/2019/10/30/oYqiF78vtOfGC4e.png)

### 可能出现高并发的点(红色)
一：高并发点
高并发出现在秒杀详情页，主要可能出现高并发问题的地方有：秒杀地址暴露、执行秒杀操作
![](https://i.loli.net/2019/10/30/FH1MGLiBlI2EKhR.png)

### 秒杀接口优化
![](https://i.loli.net/2019/10/30/zox9MZHqgcUneJC.png)
### 架构工作流程分析
![](https://i.loli.net/2019/11/01/ERcVNnuGdW2TLMq.png)

![](https://i.loli.net/2019/11/01/kylVrapAeSZ4xut.png)

参考文章[参考文章](README_test.md)

细节优化
````javascript
seckill2.js;
//只允许第一个提交的订单被发送到订单子系统
console.log(210,seckillId,$.cookie(""+seckillId)||false);
if($.cookie(""+seckillId)||false){
    node.html('<button class="btn btn-success">秒杀成功</button>');
    node.show();
    return;
}
//秒杀按钮元素随机化
var urlNumber = Math.floor(Math.random() * 10000000 + 1);
node = node.parent(".buyLink");
node.hide()
    .html('<button class="btn addCartButton" id="killBtn' + urlNumber + '">开始秒杀</button>');

//秒杀结果按钮颜色多样化
 switch (state) {
    case 1:
        tempClass = " btn-success";
        break;
    case -1:
        tempClass = " btn-warning";
        break;
    case 0:
        tempClass = "btn-info";
        break;
    default:
        tempClass = "btn-danger";
        break;
}
node.html('<button class="btn ' + tempClass + '">' + stateInfo + '</button>'); //显示秒杀结果

//库存判断
var havinventory = exposer.havinventory;
if (havinventory) {
    //还有库存
    seckill.countdown(seckillId, now, start, end);
} else {
    //库存为0
    node.html('秒杀结束'); //显示秒杀结果
    node.show();
}

//时间偏移量绑定
var killTime = new Date(startTime + wc); //加1秒，防止计时过程中时间偏移
````
```java
//SeckillService 
//exportSeckillUrl
//库存判断
if (seckill.getNumber()==0){
    return new Exposer(false, seckillId, false);
}
/*
 * 更新redis缓存中库存数量
 */
Seckill seckill = redisDao.getSeckill(seckillId);
seckill.setNumber(seckill.getNumber()-1);
redisDao.putSeckill(seckill);

```