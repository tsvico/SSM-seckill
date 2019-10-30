-- 数据库初始化脚本

-- 创建数据库
CREATE DATABASE seckill;
-- 使用数据库
use seckill;
-- 创建秒杀数据库
create table seckill
(
    `seckill_id`  bigint       NOT NULL AUTO_INCREMENT COMMENT '商品库存ID',
    `name`       varchar(120) not null comment '商品名称',
    `number`     int          not null comment '库存数量',
    `start_time`  timestamp    not null comment '秒杀开始时间',
    `end_time`   timestamp    not null comment '秒杀结束时间',
    `create_time` timestamp    not null default current_timestamp comment '创建时间',
    primary key (seckill_id),
    key idx_start_time (start_time),
    key idx_end_time (end_time),
    key idx_create_time (create_time)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1000
  DEFAULT CHARSET = utf8 COMMENT ='秒杀库存表';

-- 初始化数据
insert into
    seckill(name, number, start_time, end_time)
values
       ('1000秒杀iPhoneX',100,'2019-11-01 00:00:00','2019-11-02 00:00:00'),
       ('500秒杀小米MIX4',10,'2019-11-01 00:00:00','2019-11-02 00:00:00'),
       ('300秒杀小米9',100,'2019-11-01 00:00:00','2019-11-02 00:00:00'),
       ('200秒杀大西瓜',100,'2019-11-01 00:00:00','2019-11-02 00:00:00');

-- 秒杀成功明细表
-- 用户登录认证相关信息
create table success_kill(
    `seckill_id` bigint not null comment '秒杀商品ID',
    `user_phone` bigint not null comment '用户手机号',
    `state` tinyint not null default -1 comment '状态表示:-1无效 0 成功 1 已付款 2 已发货',
    `create_time` timestamp not null comment '创建时间',
    primary key (seckill_id,user_phone), /*联合主键*/
    key idx_create_time(create_time)
)ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT ='秒杀成功明细表'

-- 锻炼手写sql能力