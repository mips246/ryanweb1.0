/*
Navicat MySQL Data Transfer

Source Server         : test2
Source Server Version : 80016
Source Host           : localhost:3306
Source Database       : ryan1

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2019-10-23 21:01:20
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
  `coursename` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
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
  `report` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
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
  `file` varchar(100) NOT NULL,
  `video` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`coursenumber`,`teacherid`),
  KEY `teacherid` (`teacherid`),
  CONSTRAINT `coursenumber` FOREIGN KEY (`coursenumber`) REFERENCES `course` (`coursenumber`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `teacherid` FOREIGN KEY (`teacherid`) REFERENCES `teacher` (`teacherid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of courseteacher
-- ----------------------------

-- ----------------------------
-- Table structure for `homework`
-- ----------------------------
DROP TABLE IF EXISTS `homework`;
CREATE TABLE `homework` (
  `studentid` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `coursenumber` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `descriptionurl` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `homeworkurl` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `grade1` int(3) DEFAULT NULL,
  `grade2` int(3) DEFAULT NULL,
  `grade3` int(3) DEFAULT NULL,
  `grade4` int(3) DEFAULT NULL,
  `grade5` int(3) DEFAULT NULL,
  `grade6` int(3) DEFAULT NULL,
  `grade7` int(3) DEFAULT NULL,
  `grade8` int(3) DEFAULT NULL,
  `grade9` int(3) DEFAULT NULL,
  `grade10` int(3) DEFAULT NULL,
  `grade11` int(3) DEFAULT NULL,
  `grade12` int(3) DEFAULT NULL,
  `grade13` int(3) DEFAULT NULL,
  PRIMARY KEY (`studentid`,`coursenumber`),
  KEY `course` (`coursenumber`),
  CONSTRAINT `course` FOREIGN KEY (`coursenumber`) REFERENCES `course` (`coursenumber`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student` FOREIGN KEY (`studentid`) REFERENCES `student` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of homework
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
