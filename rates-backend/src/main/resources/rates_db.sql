DROP TABLE IF EXISTS `currency`;

CREATE TABLE `currency` (
  `id` bigint(11) NOT NULL auto_increment,
  `currency` varchar(3) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `currency` */

insert  into `currency`(`id`,`currency`) values (1,'EUR'),(2,'AMD'),(3,'NZD'),(4,'AUD'),(5,'JPY'),(6,'HUF'),(7,'CAD');

/*Table structure for table `historical` */

DROP TABLE IF EXISTS `historical`;

CREATE TABLE `historical` (
  `id` bigint(11) NOT NULL auto_increment,
  `usdeur` double default NULL,
  `usdamd` double default NULL,
  `usdnzd` double default NULL,
  `usdjpy` double default NULL,
  `usdhuf` double default NULL,
  `usdcad` double default NULL,
  `usdaud` double default NULL,
  `historical_date` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `historical` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(11) NOT NULL auto_increment,
  `firstname` varchar(100) default NULL,
  `lastname` varchar(100) default NULL,
  `email` varchar(100) default NULL,
  `passwd` varchar(100) default NULL,
  `register_date` datetime default NULL,
  `birth_date` datetime default NULL,
  `last_login_date` datetime default NULL,
  `country` varchar(50) default NULL,
  `city` varchar(50) default NULL,
  `street` varchar(50) default NULL,
  `zipcode` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`firstname`,`lastname`,`email`,`passwd`,`register_date`,`birth_date`,`last_login_date`,`country`,`city`,`street`,`zipcode`) values (11,'John','Smith','don@gmail.com','0b4e7a0e5fe84ad35fb5f95b9ceeac79','2017-05-13 21:55:33',NULL,NULL,'Armenia','Yerevan','new streeet','0295595');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
