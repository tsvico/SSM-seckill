/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : seckill

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2019-11-08 16:54:39
全部sql文件以及数据
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for goodslist
-- ----------------------------
DROP TABLE IF EXISTS `goodslist`;
CREATE TABLE `goodslist` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `goods_id` bigint(20) DEFAULT NULL,
  `goods_name` varchar(100) DEFAULT NULL,
  `goods_orig` float(8,2) DEFAULT NULL COMMENT '原价',
  `goods_seckill_price` float(8,2) DEFAULT NULL COMMENT '秒杀价',
  `chengjiao` int(5) DEFAULT '0' COMMENT '成交量',
  `goods_numbers` int(10) DEFAULT NULL,
  `images1` varchar(255) DEFAULT NULL,
  `images2` varchar(255) DEFAULT NULL,
  `images3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `goodsid` (`goods_id`),
  CONSTRAINT `goodsid` FOREIGN KEY (`goods_id`) REFERENCES `seckill` (`seckill_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='商品列表';

-- ----------------------------
-- Records of goodslist
-- ----------------------------
INSERT INTO `goodslist` VALUES ('1', '1000000', 'iPhoneX', '1500.00', '1000.00', '9995', '10000', 'https://gd1.alicdn.com/imgextra/i1/708804205/O1CN01zRkMft1gvw1OmCMBj_!!708804205.jpg', 'https://gd3.alicdn.com/imgextra/i3/708804205/O1CN01LVF6J91gvw1OELfCJ_!!708804205.jpg', 'https://gd2.alicdn.com/imgextra/i2/708804205/O1CN01kKmBPl1gvw1PkeXwD_!!708804205.jpg');
INSERT INTO `goodslist` VALUES ('2', '1000001', '红米K20Pro', '1000.00', '500.00', '534', '10000', 'https://gd3.alicdn.com/imgextra/i2/292911747/O1CN01HarHNs1OmATwYesQp_!!292911747.jpg', 'https://gd3.alicdn.com/imgextra/i3/292911747/O1CN01xNSBMA1OmATtW7QU1_!!https://gd3.alicdn.com/imgextra/i3/292911747/O1CN01Ved9nM1OmASXq2tT4_!!292911747.jpg', 'https://gd3.alicdn.com/imgextra/i3/292911747/O1CN01jWLXHC1OmASWJWiQE_!!292911747.jpg');
INSERT INTO `goodslist` VALUES ('3', '1000002', '小米9Pro', '700.00', '300.00', '314', '10000', 'https://gd1.alicdn.com/imgextra/i1/292911747/O1CN01hg33LT1OmAUOss5FM_!!292911747.jpg', 'https://gd1.alicdn.com/imgextra/i1/292911747/O1CN01bY98Zw1OmAUMlj6kL_!!292911747.jpg', 'https://gd2.alicdn.com/imgextra/i2/292911747/O1CN01VmcHe21OmAUjgAWMT_!!292911747.jpg');
INSERT INTO `goodslist` VALUES ('4', '1000003', 'OnePlus/一加7TPro', '4000.00', '3000.00', '6767', '10000', 'https://gd3.alicdn.com/imgextra/i4/292911747/O1CN01TT6vWK1OmAUdYq145_!!292911747.jpg', 'https://gd2.alicdn.com/imgextra/i2/292911747/O1CN01JtKoeW1OmAUcYewJI_!!292911747.png', 'https://gd2.alicdn.com/imgextra/i2/292911747/O1CN01qhTVZa1OmAUalEfKn_!!292911747.jpg');
INSERT INTO `goodslist` VALUES ('5', '1000004', 'HUAWEI Mate 30 Pro', '6000.00', '5000.00', '4648', '10000', 'https://gd1.alicdn.com/imgextra/i1/292911747/O1CN01FaLmm61OmAUsYr2Tw_!!292911747.jpg', 'https://gd3.alicdn.com/imgextra/i3/292911747/O1CN01WsLbHh1OmAUCyjoRe_!!292911747.jpg', 'https://gd1.alicdn.com/imgextra/i1/292911747/O1CN018BOY3R1OmAUGg9PHT_!!292911747.jpg');
INSERT INTO `goodslist` VALUES ('6', '1000005', '华为nova5', '2200.00', '2200.00', '6455', '10000', 'https://gd2.alicdn.com/imgextra/i4/292911747/O1CN01zUuZIZ1OmAUq3jrqc_!!292911747.jpg', 'https://gd3.alicdn.com/imgextra/i3/292911747/O1CN010HcVWC1OmASq7ugle_!!292911747.jpg', 'https://gd3.alicdn.com/imgextra/i3/292911747/O1CN01DClFob1OmASswcRtx_!!292911747.jpg');
INSERT INTO `goodslist` VALUES ('7', '1000006', '华为honor v20', '2000.00', '2000.00', '65443', '10000', 'https://gd3.alicdn.com/imgextra/i3/292911747/O1CN01uw1Hrw1OmAPslP2vQ_!!292911747.jpg', 'https://gd4.alicdn.com/imgextra/i4/292911747/O1CN01zRyPOL1OmAPq5J57A_!!292911747.jpg', 'https://gd4.alicdn.com/imgextra/i4/292911747/O1CN010eaBup1OmAPopJR8P_!!292911747.jpg');
INSERT INTO `goodslist` VALUES ('8', '1000007', '小米CC9', '2500.00', '2500.00', '69793', '10000', 'https://gd1.alicdn.com/imgextra/i1/292911747/O1CN01c7EKiY1OmATzpPMMY_!!292911747.jpg', 'https://gd1.alicdn.com/imgextra/i1/292911747/O1CN014q0D841OmAT4wKXPY_!!292911747.png', 'https://gd1.alicdn.com/imgextra/i1/292911747/O1CN01zvrdI81OmAT8DxOAu_!!292911747.jpg');

-- ----------------------------
-- Table structure for seckill
-- ----------------------------
DROP TABLE IF EXISTS `seckill`;
CREATE TABLE `seckill` (
  `seckill_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品库存ID',
  `name` varchar(120) NOT NULL COMMENT '商品名称',
  `number` int(11) NOT NULL COMMENT '库存数量',
  `start_time` timestamp NOT NULL COMMENT '秒杀开始时间',
  `end_time` timestamp NOT NULL COMMENT '秒杀结束时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`seckill_id`),
  KEY `idx_start_time` (`start_time`),
  KEY `idx_end_time` (`end_time`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1000008 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

-- ----------------------------
-- Records of seckill
-- ----------------------------
INSERT INTO `seckill` VALUES ('1000000', '1000秒杀iPhoneX', '86', '2019-11-06 13:56:40', '2019-11-29 00:00:00', '2019-11-06 15:57:43');
INSERT INTO `seckill` VALUES ('1000001', '500秒杀小米米K20Pro', '0', '2019-10-30 14:20:00', '2019-11-03 00:00:00', '2019-10-09 15:57:43');
INSERT INTO `seckill` VALUES ('1000002', '小米9Pro', '78', '2019-11-01 20:35:30', '2019-11-05 08:00:00', '2019-10-09 15:57:43');
INSERT INTO `seckill` VALUES ('1000003', '200秒杀OnePlus/一加7TPro', '90', '2019-11-03 22:00:00', '2019-11-06 00:00:00', '2019-10-09 15:57:43');
INSERT INTO `seckill` VALUES ('1000004', '0元HUAWEI Mate 30 Pro', '999', '2019-11-02 20:00:20', '2019-11-02 21:00:00', '2019-11-02 19:41:57');
INSERT INTO `seckill` VALUES ('1000005', '华为nova5', '222', '2019-11-21 11:21:36', '2019-11-19 11:21:40', '2019-11-05 11:21:43');
INSERT INTO `seckill` VALUES ('1000006', '华为honor v20', '300', '2019-11-20 11:23:51', '2019-11-27 11:23:54', '2019-11-20 11:23:57');
INSERT INTO `seckill` VALUES ('1000007', '小米CC9', '598', '2019-11-07 17:33:00', '2019-11-21 11:33:03', '2019-11-05 11:33:03');

-- ----------------------------
-- Table structure for success_kill
-- ----------------------------
DROP TABLE IF EXISTS `success_kill`;
CREATE TABLE `success_kill` (
  `seckill_id` bigint(20) NOT NULL COMMENT '秒杀商品ID',
  `user_phone` bigint(20) NOT NULL COMMENT '用户手机号',
  `state` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态表示:-1无效 0 成功 1 已付款 2 已发货',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`seckill_id`,`user_phone`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';

-- ----------------------------
-- Records of success_kill
-- ----------------------------
INSERT INTO `success_kill` VALUES ('1000000', '1564654', '0', '2019-11-07 10:56:58');
INSERT INTO `success_kill` VALUES ('1000000', '4561231', '0', '2019-11-07 10:58:30');
INSERT INTO `success_kill` VALUES ('1000000', '15512881188', '0', '2019-11-06 17:09:38');
INSERT INTO `success_kill` VALUES ('1000000', '1122334455688', '0', '2019-11-06 15:31:59');
INSERT INTO `success_kill` VALUES ('1000007', '1564654', '0', '2019-11-07 20:19:24');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(16) DEFAULT NULL COMMENT '用户名',
  `user_pwd` varchar(24) DEFAULT NULL COMMENT '密码',
  `user_nickname` varchar(16) DEFAULT NULL COMMENT '昵称',
  `user_phone` int(15) DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10005 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('10000', '15588537323', '123', '用户1', '1564654');
INSERT INTO `user` VALUES ('10001', '112233', '123', '用户2', '4561231');
INSERT INTO `user` VALUES ('10002', '112233312', '123', '用户3', '15616');
INSERT INTO `user` VALUES ('10003', '1122333', '123', '用户4', '15616');
INSERT INTO `user` VALUES ('10004', '312414', '123', '用户5', '15152');

-- ----------------------------
-- Procedure structure for execute_seckill
-- ----------------------------
DROP PROCEDURE IF EXISTS `execute_seckill`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `execute_seckill`(IN v_seckill_id BIGINT, IN v_phone BIGINT,IN v_kill_time TIMESTAMP,
    OUT r_result INTEGER)
BEGIN
    DECLARE insert_count INT DEFAULT 0;
    START TRANSACTION;
    INSERT IGNORE INTO success_kill
    (seckill_id, user_phone,create_time)
      VALUES
        (v_seckill_id,v_phone,v_kill_time);
      SELECT ROW_COUNT() INTO insert_count;
    IF(insert_count = 0) THEN
      ROLLBACK ;
      SET r_result = -1;
    ELSEIF (insert_count < 0) THEN
      ROLLBACK ;
      SET r_result = -2;
    ELSE
      UPDATE seckill SET number = number-1
      WHERE seckill_id = v_seckill_id
        AND end_time >= v_kill_time
        AND start_time <= v_kill_time
        AND number > 0;
    SELECT ROW_COUNT() INTO insert_count;
      IF(insert_count=0) THEN
        ROLLBACK ;
        SET r_result=0;
      ELSEIF (insert_count < 0) THEN
        ROLLBACK ;
        SET r_result = -2;
      ELSE
        COMMIT ;
        SET r_result=1;
      END IF ;
    END IF ;
  END
;;
DELIMITER ;
