/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : mybatis

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2017-05-24 13:09:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `role_name` varchar(255) NOT NULL,
  `note` varchar(255) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('bob', 'good boy', '1');
INSERT INTO `t_role` VALUES ('bob', 'good boy', '2');
INSERT INTO `t_role` VALUES ('bob', 'good boy', '3');
INSERT INTO `t_role` VALUES ('bob', 'good boy', '4');
INSERT INTO `t_role` VALUES ('bob', 'good boy', '5');
INSERT INTO `t_role` VALUES ('bob', 'good boy', '6');
INSERT INTO `t_role` VALUES ('bob', 'good boy', '7');
INSERT INTO `t_role` VALUES ('bob', 'good boy', '8');
INSERT INTO `t_role` VALUES ('bob', 'good boy', '9');
INSERT INTO `t_role` VALUES ('bob', 'good boy', '10');
INSERT INTO `t_role` VALUES ('bob', 'good boy', '11');
INSERT INTO `t_role` VALUES ('bob', 'good boy', '12');
INSERT INTO `t_role` VALUES ('bob', 'good boy', '13');
INSERT INTO `t_role` VALUES ('bob', 'good boy', '14');
INSERT INTO `t_role` VALUES ('bob', 'good boy', '15');