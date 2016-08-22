# Host: 127.0.0.1  (Version: 5.5.5-10.1.9-MariaDB)
# Date: 2016-08-10 10:33:23
# Generator: MySQL-Front 5.3  (Build 4.214)

/*!40101 SET NAMES gb2312 */;

#
# Structure for table "users"
#

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

#
# Data for table "users"
#

INSERT INTO `users` VALUES (5,'Brody',18),(6,'증증',28),(7,'령령',38);
