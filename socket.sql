/*
Navicat MySQL Data Transfer

Source Server         : llh
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : socket

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2016-07-23 16:23:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_friend`
-- ----------------------------
DROP TABLE IF EXISTS `t_friend`;
CREATE TABLE `t_friend` (
  `id` int(255) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `name1` text,
  `name2` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_friend
-- ----------------------------
INSERT INTO `t_friend` VALUES ('000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001', '123', '123');

-- ----------------------------
-- Table structure for `t_msg`
-- ----------------------------
DROP TABLE IF EXISTS `t_msg`;
CREATE TABLE `t_msg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` text,
  `target` text,
  `msg` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_msg
-- ----------------------------
INSERT INTO `t_msg` VALUES ('1', '456', 'aaaaa', 'asdasd');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('123', '456', '1');
INSERT INTO `t_user` VALUES ('456', '13', '2');
INSERT INTO `t_user` VALUES ('666', '666', '3');
INSERT INTO `t_user` VALUES ('333', '3333', '4');
INSERT INTO `t_user` VALUES ('555', '555', '5');
INSERT INTO `t_user` VALUES ('566', '222', '6');
INSERT INTO `t_user` VALUES ('5555', '1234', '7');
INSERT INTO `t_user` VALUES ('3654', '2365', '8');
INSERT INTO `t_user` VALUES ('55555', '.36414', '9');
INSERT INTO `t_user` VALUES ('6369', '1111', '10');
INSERT INTO `t_user` VALUES ('852', '12', '11');
INSERT INTO `t_user` VALUES ('abc', '123', '12');
INSERT INTO `t_user` VALUES ('963852', '123456', '13');
INSERT INTO `t_user` VALUES ('963852', '123555', '14');
INSERT INTO `t_user` VALUES ('22', '23333', '15');
INSERT INTO `t_user` VALUES ('aaaaaa', 'ssssss', '16');
INSERT INTO `t_user` VALUES ('aaa', 'sss', '17');
