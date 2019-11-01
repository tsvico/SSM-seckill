/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : seckill

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2019-10-30 16:09:00
*/

SET FOREIGN_KEY_CHECKS=0;

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
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

-- ----------------------------
-- Records of seckill
-- ----------------------------
INSERT INTO `seckill` VALUES ('1000', '1000秒杀iPhoneX', '96', '2019-10-31 00:00:00', '2019-11-01 00:00:00', '2019-10-23 15:57:43');
INSERT INTO `seckill` VALUES ('1001', '500秒杀小米MIX4', '5', '2019-10-30 14:20:00', '2019-11-02 00:00:00', '2019-10-09 15:57:43');
INSERT INTO `seckill` VALUES ('1002', '300秒杀小米9', '100', '2019-11-01 00:00:00', '2019-11-02 00:00:00', '2019-10-09 15:57:43');
INSERT INTO `seckill` VALUES ('1003', '200秒杀大西瓜', '100', '2019-11-01 00:00:00', '2019-11-02 00:00:00', '2019-10-09 15:57:43');

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
INSERT INTO `success_kill` VALUES ('1001', '13488887777', '0', '2019-10-30 14:50:40');
INSERT INTO `success_kill` VALUES ('1001', '18005385482', '0', '2019-10-30 14:50:40');
