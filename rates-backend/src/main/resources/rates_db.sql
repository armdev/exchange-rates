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


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
