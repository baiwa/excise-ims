/*
Navicat MySQL Data Transfer

Source Server         : My Local
Source Server Version : 50136
Source Host           : localhost:3306
Source Database       : buckwa_framework

Target Server Type    : MYSQL
Target Server Version : 50136
File Encoding         : 65001

Date: 2015-07-21 16:28:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for buckwa_test
-- ----------------------------
DROP TABLE IF EXISTS `buckwa_test`;
CREATE TABLE `buckwa_test` (
  `col_pk` int(11) NOT NULL AUTO_INCREMENT,
  `col_varchar` varchar(50) DEFAULT NULL,
  `col_int` int(11) DEFAULT NULL,
  `col_double` double DEFAULT NULL,
  `col_decimal` decimal(9,0) DEFAULT NULL,
  `col_timestamp` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `col_date` date DEFAULT NULL,
  `col_time` time DEFAULT NULL,
  PRIMARY KEY (`col_pk`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of buckwa_test
-- ----------------------------
INSERT INTO `buckwa_test` VALUES ('1', 'test_varchar1', '10', '20.25', '46', '2015-07-21 16:26:53', '2015-07-20', '19:15:42');
INSERT INTO `buckwa_test` VALUES ('2', 'test_varchar2', '20', '44.21', '32', '2015-07-21 16:27:32', '2015-07-01', '16:27:31');
INSERT INTO `buckwa_test` VALUES ('3', 'test_varchar3', '30', '66.99', '12', '2015-07-21 16:27:47', '2015-07-07', '06:16:33');
