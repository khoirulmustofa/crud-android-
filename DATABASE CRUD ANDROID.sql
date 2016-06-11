/*
SQLyog Ultimate v9.02 
MySQL - 5.6.25 : Database - inventory_km
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`inventory_km` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `inventory_km`;

/*Table structure for table `customer` */

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `address` varchar(300) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `email` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`name`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `customer` */

insert  into `customer`(`id`,`name`,`address`,`phone`,`email`) values (7,'cinta','ttt','rrrrrrrrrrr','rrrrrrrrrrr'),(6,'eter','eter','ertter','ertter'),(1,'khoirul mustofa','Rembang','081230400086','khoirul.mustofa2014@gmail.com'),(4,'nameww','address','phone','email');

/*Table structure for table `member` */

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `id` tinyint(4) unsigned NOT NULL AUTO_INCREMENT,
  `nama` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `alamat` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `member` */

insert  into `member`(`id`,`nama`,`alamat`) values (1,'Akhmad Dharma Kasman ','Jalan Serua Bojongsari '),(2,'Kresna Abimanyu','Jln. Diponegoro'),(3,'Giampaolo Pazzini','Jalan Kasuari 25'),(4,'Ricardo Montolivo','Jalan Margonda Depok 24'),(5,'Stephan El Sharawy','Jalan Bungur 2 No. 39'),(7,'Antonio Nocerino','Kebayoran Lama Jalan Mangga 12'),(21,'Crisitiano Ronaldo \"CR7\"','Jalan Pedurenan Selatan 19'),(22,'Neymar Junior','Semarang');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
