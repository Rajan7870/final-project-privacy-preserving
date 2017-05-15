/*
SQLyog Ultimate v11.24 (64 bit)
MySQL - 5.0.83-community-nt : Database - aggregate
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`aggregate` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `aggregate`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `Name` varchar(20) default NULL,
  `password` varchar(20) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `cloudupload` */

DROP TABLE IF EXISTS `cloudupload`;

CREATE TABLE `cloudupload` (
  `fileid` int(11) NOT NULL auto_increment,
  `username` varchar(50) NOT NULL,
  `filepath` varchar(50) NOT NULL,
  `filename` varchar(100) NOT NULL,
  `filecontent` varchar(10000) NOT NULL,
  `randomkey` varchar(300) NOT NULL,
  PRIMARY KEY  (`fileid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `fileshare` */

DROP TABLE IF EXISTS `fileshare`;

CREATE TABLE `fileshare` (
  `fileid` int(225) NOT NULL auto_increment,
  `username` varchar(1000) default NULL,
  `sharingusername` varchar(1000) default NULL,
  `filename` varchar(10000) default NULL,
  `status` varchar(10000) default NULL,
  KEY `fileid` (`fileid`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=latin1;

/*Table structure for table `groups` */

DROP TABLE IF EXISTS `groups`;

CREATE TABLE `groups` (
  `groupid` int(11) NOT NULL auto_increment,
  `groupname` varchar(100) default NULL,
  `filename` varchar(100) default NULL,
  PRIMARY KEY  (`groupid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Table structure for table `upload` */

DROP TABLE IF EXISTS `upload`;

CREATE TABLE `upload` (
  `fileid` int(11) NOT NULL auto_increment,
  `username` varchar(50) default NULL,
  `filepath` varchar(1000) default NULL,
  `filename` varchar(100) default NULL,
  `filekey` varchar(50) default NULL,
  `filecontent` text NOT NULL,
  `randomkey` int(50) default NULL,
  `status1` varchar(50) default 'No',
  PRIMARY KEY  (`fileid`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `Name` varchar(20) NOT NULL,
  `email` varchar(50) default NULL,
  `password` varchar(20) default NULL,
  `gender` varchar(10) default NULL,
  `phone` varchar(15) default NULL,
  PRIMARY KEY  (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `usergroup` */

DROP TABLE IF EXISTS `usergroup`;

CREATE TABLE `usergroup` (
  `joinid` int(11) NOT NULL auto_increment,
  `groupid` varchar(100) default NULL,
  `groupname` varchar(100) default NULL,
  `username` varchar(100) default NULL,
  `timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`joinid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
