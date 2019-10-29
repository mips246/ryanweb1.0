/*
Navicat MySQL Data Transfer

Source Server         : test2
Source Server Version : 80016
Source Host           : localhost:3306
Source Database       : ryan1

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2019-10-28 20:36:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `userid` varchar(40) NOT NULL,
  `username` varchar(40) NOT NULL,
  `password` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('admin', 'Administrator', '142857');

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `courseid` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `coursename` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `studentcount` int(10) NOT NULL,
  `createtime` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`courseid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '计算机组成原理', '50', '2019.10.1');

-- ----------------------------
-- Table structure for `courseselect`
-- ----------------------------
DROP TABLE IF EXISTS `courseselect`;
CREATE TABLE `courseselect` (
  `courseid` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `studentid` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `grade` int(11) NOT NULL,
  PRIMARY KEY (`courseid`,`studentid`),
  KEY `studentid` (`studentid`),
  CONSTRAINT `cn` FOREIGN KEY (`courseid`) REFERENCES `course` (`courseid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `studentid` FOREIGN KEY (`studentid`) REFERENCES `student` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of courseselect
-- ----------------------------

-- ----------------------------
-- Table structure for `courseteacher`
-- ----------------------------
DROP TABLE IF EXISTS `courseteacher`;
CREATE TABLE `courseteacher` (
  `courseid` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `teacherid` varchar(40) NOT NULL,
  PRIMARY KEY (`courseid`,`teacherid`),
  KEY `teacherid` (`teacherid`),
  CONSTRAINT `coursenumber` FOREIGN KEY (`courseid`) REFERENCES `course` (`courseid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `teacherid` FOREIGN KEY (`teacherid`) REFERENCES `teacher` (`teacherid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of courseteacher
-- ----------------------------

-- ----------------------------
-- Table structure for `file`
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `file_url` varchar(100) NOT NULL,
  `studentid` varchar(40) DEFAULT NULL,
  `courseid` varchar(40) DEFAULT NULL,
  `teacherid` varchar(40) DEFAULT NULL,
  `file_type` int(3) NOT NULL,
  `grade` int(3) DEFAULT NULL,
  `course_section` int(3) DEFAULT NULL,
  `create_time` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `file_name` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`file_url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of file
-- ----------------------------

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `userid` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL,
  `password` varchar(32) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1933035', '王睿晗', '142857');
INSERT INTO `student` VALUES ('admin', 'administrator', 'admin');

-- ----------------------------
-- Table structure for `teacher`
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `teacherid` varchar(40) NOT NULL,
  `teachername` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `description` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`teacherid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('100001', '张三', '123456', '计算机组成原理');
