/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50562
Source Host           : localhost:3306
Source Database       : ryan1

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2019-12-03 18:19:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `userid` varchar(40) NOT NULL,
  `username` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `courseid` varchar(40) NOT NULL,
  `coursename` varchar(40) NOT NULL,
  `studentcount` int(10) NOT NULL,
  `createtime` varchar(40) NOT NULL,
  PRIMARY KEY (`courseid`),
  KEY `coursename` (`coursename`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for courseselect
-- ----------------------------
DROP TABLE IF EXISTS `courseselect`;
CREATE TABLE `courseselect` (
  `courseid` varchar(40) NOT NULL,
  `studentid` varchar(40) NOT NULL,
  `grade` int(11) DEFAULT NULL,
  `teacherid` varchar(40) NOT NULL,
  PRIMARY KEY (`courseid`,`studentid`,`teacherid`),
  KEY `studentid` (`studentid`),
  KEY `teacher_id` (`teacherid`),
  CONSTRAINT `cn` FOREIGN KEY (`courseid`) REFERENCES `course` (`courseid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `studentid` FOREIGN KEY (`studentid`) REFERENCES `student` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `teacher_id` FOREIGN KEY (`teacherid`) REFERENCES `teacher` (`teacherid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for courseteacher
-- ----------------------------
DROP TABLE IF EXISTS `courseteacher`;
CREATE TABLE `courseteacher` (
  `courseid` varchar(40) NOT NULL,
  `coursename` varchar(40) NOT NULL,
  `teacherid` varchar(40) NOT NULL,
  `teachername` varchar(40) NOT NULL,
  PRIMARY KEY (`courseid`,`teacherid`),
  KEY `teacherid` (`teacherid`),
  KEY `course_name` (`coursename`),
  KEY `teacher_name` (`teachername`),
  CONSTRAINT `coursenumber` FOREIGN KEY (`courseid`) REFERENCES `course` (`courseid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `course_name` FOREIGN KEY (`coursename`) REFERENCES `course` (`coursename`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `teacherid` FOREIGN KEY (`teacherid`) REFERENCES `teacher` (`teacherid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `teacher_name` FOREIGN KEY (`teachername`) REFERENCES `teacher` (`teachername`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `file_no` int(11) NOT NULL AUTO_INCREMENT,
  `file_url` varchar(100) NOT NULL,
  `studentid` varchar(40) DEFAULT NULL,
  `courseid` varchar(40) DEFAULT NULL,
  `teacherid` varchar(40) DEFAULT NULL,
  `file_type` int(3) NOT NULL,
  `grade` int(3) DEFAULT NULL,
  `course_section` int(3) DEFAULT NULL,
  `create_time` varchar(40) DEFAULT NULL,
  `file_name` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`file_no`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `userid` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL,
  `password` varchar(32) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `teacherid` varchar(40) NOT NULL,
  `teachername` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `description` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`teacherid`),
  KEY `teachername` (`teachername`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
