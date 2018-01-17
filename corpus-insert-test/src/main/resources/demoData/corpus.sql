/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : mybatis_chapter2

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2017-11-30 16:24:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `corpus`
-- ----------------------------
DROP TABLE IF EXISTS `corpus`;
CREATE TABLE `corpus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `classify` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `frequncy` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19533704 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of corpus
-- ----------------------------
