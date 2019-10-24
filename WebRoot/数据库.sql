/*
Navicat MySQL Data Transfer

Source Server         : test2
Source Server Version : 80016
Source Host           : localhost:3306
Source Database       : ryan1

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2019-10-24 16:15:34
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

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `coursename` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `coursenumber` varchar(40) NOT NULL,
  `studentcount` int(10) NOT NULL,
  `createtime` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`coursenumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------

-- ----------------------------
-- Table structure for `courseselect`
-- ----------------------------
DROP TABLE IF EXISTS `courseselect`;
CREATE TABLE `courseselect` (
  `coursenumber` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `studentid` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `grade` int(11) NOT NULL,
  PRIMARY KEY (`coursenumber`,`studentid`),
  KEY `studentid` (`studentid`),
  CONSTRAINT `cn` FOREIGN KEY (`coursenumber`) REFERENCES `course` (`coursenumber`) ON DELETE CASCADE ON UPDATE CASCADE,
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
  `coursenumber` varchar(40) NOT NULL,
  `teacherid` varchar(40) NOT NULL,
  PRIMARY KEY (`coursenumber`,`teacherid`),
  KEY `teacherid` (`teacherid`),
  CONSTRAINT `coursenumber` FOREIGN KEY (`coursenumber`) REFERENCES `course` (`coursenumber`) ON DELETE CASCADE ON UPDATE CASCADE,
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
  `role_type` int(3) NOT NULL,
  `grade` int(3) DEFAULT NULL,
  `course_section` int(3) DEFAULT NULL,
  `creat_time` varchar(40) DEFAULT NULL,
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
INSERT INTO `student` VALUES ('admin', 'administrator', 'admin');

-- ----------------------------
-- Table structure for `teacher`
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `teacherid` varchar(40) NOT NULL,
  `teachername` varchar(40) NOT NULL,
  `description` varchar(400) NOT NULL,
  PRIMARY KEY (`teacherid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
