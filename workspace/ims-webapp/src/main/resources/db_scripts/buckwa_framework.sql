/*
Navicat MySQL Data Transfer

Source Server         : MySQL_Localhost
Source Server Version : 50545
Source Host           : 127.0.0.1:3306
Source Database       : buckwa_framework

Target Server Type    : MYSQL
Target Server Version : 50545
File Encoding         : 65001

Date: 2016-06-30 16:24:11
*/

SET FOREIGN_KEY_CHECKS=0;

CREATE DATABASE `buckwa_framework`;

-- ----------------------------
-- Table structure for adm_operation
-- ----------------------------
DROP TABLE IF EXISTS `adm_operation`;
DROP TABLE IF EXISTS `adm_operation`;
CREATE TABLE `adm_operation` (
  `operation_id` int(11) NOT NULL AUTO_INCREMENT,
  `operation_code` varchar(20) NOT NULL,
  `operation_desc` varchar(200) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `is_deleted` char(1) NOT NULL DEFAULT 'N',
  `version` int(11) NOT NULL DEFAULT '1',
  `created_by` varchar(30) NOT NULL,
  `created_date` datetime NOT NULL,
  `updated_by` varchar(30) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`operation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of adm_operation
-- ----------------------------
INSERT INTO `adm_operation` VALUES ('1', 'OP_P1', '', null, 'N', '1', 'SYSTEM', '2016-06-06 21:02:05', null, null);
INSERT INTO `adm_operation` VALUES ('2', 'OP_P2', '', null, 'N', '1', 'SYSTEM', '2016-06-06 21:02:05', null, null);

-- ----------------------------
-- Table structure for adm_role
-- ----------------------------
DROP TABLE IF EXISTS `adm_role`;
CREATE TABLE `adm_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(20) NOT NULL,
  `role_desc` varchar(200) NOT NULL,
  `is_deleted` char(1) NOT NULL DEFAULT 'N',
  `version` int(11) NOT NULL DEFAULT '1',
  `created_by` varchar(30) NOT NULL,
  `created_date` datetime NOT NULL,
  `updated_by` varchar(30) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of adm_role
-- ----------------------------
INSERT INTO `adm_role` VALUES ('1', 'ROLE_USER', 'Normal User', 'N', '1', 'SYSTEM', '2016-06-06 21:02:05', null, null);
INSERT INTO `adm_role` VALUES ('2', 'ROLE_ADMIN', 'Administrator', 'N', '1', 'SYSTEM', '2016-06-06 21:02:05', null, null);

-- ----------------------------
-- Table structure for adm_user
-- ----------------------------
DROP TABLE IF EXISTS `adm_user`;
CREATE TABLE `adm_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` varchar(60) NOT NULL,
  `enabled` char(1) NOT NULL,
  `account_non_expired` char(1) NOT NULL,
  `credentials_non_expired` char(1) NOT NULL,
  `account_non_locked` char(1) NOT NULL,
  `is_deleted` char(1) NOT NULL DEFAULT 'N',
  `version` int(11) NOT NULL DEFAULT '1',
  `created_by` varchar(30) NOT NULL,
  `created_date` datetime NOT NULL,
  `updated_by` varchar(30) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of adm_user
-- ----------------------------
INSERT INTO `adm_user` VALUES ('1', 'admin', '$2a$10$f39laYr01qsXek4AmzvWy.zahGK2AsrOS1EGFnYcGMFDMrsRY4QSW', 'Y', 'Y', 'Y', 'Y', 'N', '2', 'SYSTEM', '2016-04-28 00:26:44', 'SYSTEM', '2016-06-04 23:16:37');

-- ----------------------------
-- Table structure for adm_user_attempt
-- ----------------------------
DROP TABLE IF EXISTS `adm_user_attempt`;
CREATE TABLE `adm_user_attempt` (
  `user_attempt_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `attempts` int(1) NOT NULL DEFAULT '0',
  `last_modified` datetime NOT NULL,
  PRIMARY KEY (`user_attempt_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of adm_user_attempt
-- ----------------------------
INSERT INTO `adm_user_attempt` VALUES ('1', 'admin', '0', '2016-06-04 23:37:46');

-- ----------------------------
-- Table structure for adm_user_operation
-- ----------------------------
DROP TABLE IF EXISTS `adm_user_operation`;
DROP TABLE IF EXISTS `adm_user_operation`;
CREATE TABLE `adm_user_operation` (
  `user_operation_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `operation_id` int(11) NOT NULL,
  `is_deleted` char(1) NOT NULL DEFAULT 'N',
  `version` int(11) NOT NULL DEFAULT '1',
  `created_by` varchar(30) NOT NULL,
  `created_date` datetime NOT NULL,
  `updated_by` varchar(30) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`user_operation_id`),
  KEY `adm_user_operation_fk01` (`user_id`),
  KEY `adm_user_operation_fk02` (`operation_id`),
  CONSTRAINT `adm_user_operation_fk01` FOREIGN KEY (`user_id`) REFERENCES `adm_user` (`user_id`),
  CONSTRAINT `adm_user_operation_fk02` FOREIGN KEY (`operation_id`) REFERENCES `adm_operation` (`operation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of adm_user_operation
-- ----------------------------

-- ----------------------------
-- Table structure for adm_user_role
-- ----------------------------
DROP TABLE IF EXISTS `adm_user_role`;
CREATE TABLE `adm_user_role` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `is_deleted` char(1) NOT NULL DEFAULT 'N',
  `version` int(11) NOT NULL DEFAULT '1',
  `created_by` varchar(30) NOT NULL,
  `created_date` datetime NOT NULL,
  `updated_by` varchar(30) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`user_role_id`),
  KEY `usr_user_role_fk01` (`user_id`),
  KEY `usr_user_role_fk02` (`role_id`),
  CONSTRAINT `usr_user_role_fk01` FOREIGN KEY (`user_id`) REFERENCES `adm_user` (`user_id`),
  CONSTRAINT `usr_user_role_fk02` FOREIGN KEY (`role_id`) REFERENCES `adm_role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of adm_user_role
-- ----------------------------
INSERT INTO `adm_user_role` VALUES ('1', '1', '1', 'N', '1', 'SYSTEM', '2016-06-06 21:02:54', null, null);
INSERT INTO `adm_user_role` VALUES ('2', '1', '2', 'N', '1', 'SYSTEM', '2016-06-06 21:02:54', null, null);

-- ----------------------------
-- Table structure for sys_message
-- ----------------------------
DROP TABLE IF EXISTS `sys_message`;
CREATE TABLE `sys_message` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT,
  `message_code` varchar(50) NOT NULL,
  `message_en` varchar(200) NOT NULL,
  `message_th` varchar(200) DEFAULT NULL,
  `message_type` char(1) NOT NULL,
  `is_deleted` char(1) NOT NULL DEFAULT 'N',
  `version` int(11) NOT NULL DEFAULT '1',
  `created_by` varchar(30) NOT NULL,
  `created_date` datetime NOT NULL,
  `updated_by` varchar(30) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_message
-- ----------------------------
INSERT INTO `sys_message` VALUES ('1', 'MSG_00001', 'Are you sure you want to delete selected item(s)?', 'คุณต้องการลบข้อมูลที่เลือกใช่ไหม?', 'I', 'N', 1, 'INITIAL', '2017-09-27 09:30:00', 'INITIAL', '2017-09-27 09:30:00');

-- ----------------------------
-- Table structure for sys_parameter_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_parameter_group`;
CREATE TABLE `sys_parameter_group` (
  `param_group_id` int(11) NOT NULL AUTO_INCREMENT,
  `param_group_code` varchar(50) NOT NULL,
  `param_group_desc` varchar(200) NOT NULL,
  `is_deleted` char(1) NOT NULL DEFAULT 'N',
  `version` int(11) NOT NULL DEFAULT '1',
  `created_by` varchar(30) NOT NULL,
  `created_date` datetime NOT NULL,
  `updated_by` varchar(30) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`param_group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_parameter_group
-- ----------------------------
INSERT INTO `sys_parameter_group` VALUES ('1', 'SYSTEM_CONFIG', 'SYSTEM_CONFIG', 'N', '1', 'SYSTEM', '2016-06-01 00:00:00', null, null);

-- ----------------------------
-- Table structure for sys_parameter_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_parameter_info`;
CREATE TABLE `sys_parameter_info` (
  `param_info_id` int(11) NOT NULL AUTO_INCREMENT,
  `param_group_id` int(11) NOT NULL,
  `param_code` varchar(50) NOT NULL,
  `value_1` varchar(200) DEFAULT NULL,
  `value_2` varchar(200) DEFAULT NULL,
  `value_3` varchar(200) DEFAULT NULL,
  `value_4` varchar(200) DEFAULT NULL,
  `value_5` varchar(200) DEFAULT NULL,
  `value_6` varchar(200) DEFAULT NULL,
  `sorting_order` int(11) DEFAULT '0',
  `is_default` char(1) DEFAULT 'N',
  `is_deleted` char(1) NOT NULL DEFAULT 'N',
  `version` int(11) NOT NULL DEFAULT '1',
  `created_by` varchar(30) NOT NULL,
  `created_date` datetime NOT NULL,
  `updated_by` varchar(30) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`param_info_id`),
  KEY `sys_parameter_info_fk01` (`param_group_id`),
  CONSTRAINT `sys_parameter_info_fk01` FOREIGN KEY (`param_group_id`) REFERENCES `sys_parameter_group` (`param_group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_parameter_info
-- ----------------------------
INSERT INTO `sys_parameter_info` VALUES ('1', '1', 'LOGIN_ATTEMPTS', 'N', '3', '', '', '', '', '0', 'N', 'N', '1', 'SYSTEM', '2016-06-01 00:00:00', null, null);

-- ----------------------------
-- Table structure for sys_webservice_logging
-- ----------------------------
DROP TABLE IF EXISTS `sys_webservice_logging`;
CREATE TABLE `sys_webservice_logging` (
  `ws_log_id` int(11) NOT NULL AUTO_INCREMENT,
  `source_system` varchar(50) DEFAULT NULL,
  `destination_system` varchar(50) DEFAULT NULL,
  `service_name` varchar(100) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `request_text` text,
  `response_text` text,
  `created_by` varchar(30) NOT NULL,
  `created_date` datetime NOT NULL,
  PRIMARY KEY (`ws_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_webservice_logging
-- ----------------------------

-- ----------------------------
-- Table structure for usr_profile
-- ----------------------------
DROP TABLE IF EXISTS `usr_profile`;
CREATE TABLE `usr_profile` (
  `profile_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `title` varchar(50) DEFAULT NULL,
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `is_deleted` char(1) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `created_by` varchar(30) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_by` varchar(30) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`profile_id`),
  KEY `usr_profile_fk01` (`user_id`),
  CONSTRAINT `usr_profile_fk01` FOREIGN KEY (`user_id`) REFERENCES `adm_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of usr_profile
-- ----------------------------
