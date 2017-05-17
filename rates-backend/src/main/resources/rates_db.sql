/*
SQLyog Ultimate v9.50 
MySQL - 5.5.5-10.1.13-MariaDB : Database - rates
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`rates` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `rates`;

/*Table structure for table `currency` */

DROP TABLE IF EXISTS `currency`;

CREATE TABLE `currency` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `currency` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `currency` */

insert  into `currency`(`id`,`currency`) values (1,'EUR'),(2,'AMD'),(3,'NZD'),(4,'AUD'),(5,'JPY'),(6,'HUF'),(7,'CAD');

/*Table structure for table `historical` */

DROP TABLE IF EXISTS `historical`;

CREATE TABLE `historical` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `usdeur` double DEFAULT NULL,
  `usdamd` double DEFAULT NULL,
  `usdnzd` double DEFAULT NULL,
  `usdjpy` double DEFAULT NULL,
  `usdhuf` double DEFAULT NULL,
  `usdcad` double DEFAULT NULL,
  `usdaud` double DEFAULT NULL,
  `historical_date` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `historical` */

insert  into `historical`(`id`,`usdeur`,`usdamd`,`usdnzd`,`usdjpy`,`usdhuf`,`usdcad`,`usdaud`,`historical_date`) values (1,1.1074,568.532994,2.241309,127.933302,269.657357,1.562798,1.863958,'2002-05-02'),(2,0.750445,405.553336,1.195259,89.83912,220.039332,0.990269,0.95184,'2013-01-18'),(3,0.891469,554.974735,1.782341,118.69526,221.492033,1.423255,1.586676,'2003-05-01'),(4,0.814343,439.477839,1.63021,117.499565,217.139049,1.161629,1.377111,'2006-04-05'),(5,0.766883,504.99121,1.408949,103.20193,189.117148,1.187422,1.277228,'2004-11-22');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(100) DEFAULT NULL,
  `lastname` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `passwd` varchar(100) DEFAULT NULL,
  `register_date` datetime DEFAULT NULL,
  `birth_date` datetime DEFAULT NULL,
  `last_login_date` datetime DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `street` varchar(50) DEFAULT NULL,
  `zipcode` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`firstname`,`lastname`,`email`,`passwd`,`register_date`,`birth_date`,`last_login_date`,`country`,`city`,`street`,`zipcode`) values (11,'John','Smith','armen@gmail.com','0b4e7a0e5fe84ad35fb5f95b9ceeac79','2017-05-13 21:55:33',NULL,NULL,'Armenia','Yerevan','new streeet','0295595');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
