CREATE DATABASE  IF NOT EXISTS `image` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `image`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: image
-- ------------------------------------------------------
-- Server version	5.6.14-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `imagecolor`
--

DROP TABLE IF EXISTS `imagecolor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imagecolor` (
  `ID` int(11) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Color` varchar(45) NOT NULL,
  `Path` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imagecolor`
--

LOCK TABLES `imagecolor` WRITE;
/*!40000 ALTER TABLE `imagecolor` DISABLE KEYS */;
INSERT INTO `imagecolor` VALUES (0,'1.jpg','C:/Users/ASUS/Desktop/tezheng/1.txt','C:/Users/ASUS/Desktop/image database_1/'),(1,'10.jpg','C:/Users/ASUS/Desktop/tezheng/10.txt','C:/Users/ASUS/Desktop/image database_1/'),(2,'11.jpg','C:/Users/ASUS/Desktop/tezheng/11.txt','C:/Users/ASUS/Desktop/image database_1/'),(3,'12.jpg','C:/Users/ASUS/Desktop/tezheng/12.txt','C:/Users/ASUS/Desktop/image database_1/'),(4,'13.jpg','C:/Users/ASUS/Desktop/tezheng/13.txt','C:/Users/ASUS/Desktop/image database_1/'),(5,'14.jpg','C:/Users/ASUS/Desktop/tezheng/14.txt','C:/Users/ASUS/Desktop/image database_1/'),(6,'15.jpg','C:/Users/ASUS/Desktop/tezheng/15.txt','C:/Users/ASUS/Desktop/image database_1/'),(7,'16.jpg','C:/Users/ASUS/Desktop/tezheng/16.txt','C:/Users/ASUS/Desktop/image database_1/'),(8,'17.jpg','C:/Users/ASUS/Desktop/tezheng/17.txt','C:/Users/ASUS/Desktop/image database_1/'),(9,'18.jpg','C:/Users/ASUS/Desktop/tezheng/18.txt','C:/Users/ASUS/Desktop/image database_1/'),(10,'19.jpg','C:/Users/ASUS/Desktop/tezheng/19.txt','C:/Users/ASUS/Desktop/image database_1/'),(11,'2.jpg','C:/Users/ASUS/Desktop/tezheng/2.txt','C:/Users/ASUS/Desktop/image database_1/'),(12,'20.jpg','C:/Users/ASUS/Desktop/tezheng/20.txt','C:/Users/ASUS/Desktop/image database_1/'),(13,'3.jpg','C:/Users/ASUS/Desktop/tezheng/3.txt','C:/Users/ASUS/Desktop/image database_1/'),(14,'4.jpg','C:/Users/ASUS/Desktop/tezheng/4.txt','C:/Users/ASUS/Desktop/image database_1/'),(15,'5.jpg','C:/Users/ASUS/Desktop/tezheng/5.txt','C:/Users/ASUS/Desktop/image database_1/'),(16,'6.jpg','C:/Users/ASUS/Desktop/tezheng/6.txt','C:/Users/ASUS/Desktop/image database_1/'),(17,'7.jpg','C:/Users/ASUS/Desktop/tezheng/7.txt','C:/Users/ASUS/Desktop/image database_1/'),(18,'8.jpg','C:/Users/ASUS/Desktop/tezheng/8.txt','C:/Users/ASUS/Desktop/image database_1/'),(19,'9.jpg','C:/Users/ASUS/Desktop/tezheng/9.txt','C:/Users/ASUS/Desktop/image database_1/'),(20,'b1.jpg','C:/Users/ASUS/Desktop/tezheng/b1.txt','C:/Users/ASUS/Desktop/image database_1/'),(21,'b10.jpg','C:/Users/ASUS/Desktop/tezheng/b10.txt','C:/Users/ASUS/Desktop/image database_1/'),(22,'b11.jpg','C:/Users/ASUS/Desktop/tezheng/b11.txt','C:/Users/ASUS/Desktop/image database_1/'),(23,'b12.jpg','C:/Users/ASUS/Desktop/tezheng/b12.txt','C:/Users/ASUS/Desktop/image database_1/'),(24,'b13.jpg','C:/Users/ASUS/Desktop/tezheng/b13.txt','C:/Users/ASUS/Desktop/image database_1/'),(25,'b14.jpg','C:/Users/ASUS/Desktop/tezheng/b14.txt','C:/Users/ASUS/Desktop/image database_1/'),(26,'b15.jpg','C:/Users/ASUS/Desktop/tezheng/b15.txt','C:/Users/ASUS/Desktop/image database_1/'),(27,'b16.jpg','C:/Users/ASUS/Desktop/tezheng/b16.txt','C:/Users/ASUS/Desktop/image database_1/'),(28,'b17.jpg','C:/Users/ASUS/Desktop/tezheng/b17.txt','C:/Users/ASUS/Desktop/image database_1/'),(29,'b18.jpg','C:/Users/ASUS/Desktop/tezheng/b18.txt','C:/Users/ASUS/Desktop/image database_1/'),(30,'b19.jpg','C:/Users/ASUS/Desktop/tezheng/b19.txt','C:/Users/ASUS/Desktop/image database_1/'),(31,'b2.jpg','C:/Users/ASUS/Desktop/tezheng/b2.txt','C:/Users/ASUS/Desktop/image database_1/'),(32,'b20.jpg','C:/Users/ASUS/Desktop/tezheng/b20.txt','C:/Users/ASUS/Desktop/image database_1/'),(33,'b3.jpg','C:/Users/ASUS/Desktop/tezheng/b3.txt','C:/Users/ASUS/Desktop/image database_1/'),(34,'b4.jpg','C:/Users/ASUS/Desktop/tezheng/b4.txt','C:/Users/ASUS/Desktop/image database_1/'),(35,'b5.jpg','C:/Users/ASUS/Desktop/tezheng/b5.txt','C:/Users/ASUS/Desktop/image database_1/'),(36,'b6.jpg','C:/Users/ASUS/Desktop/tezheng/b6.txt','C:/Users/ASUS/Desktop/image database_1/'),(37,'b7.jpg','C:/Users/ASUS/Desktop/tezheng/b7.txt','C:/Users/ASUS/Desktop/image database_1/'),(38,'b8.jpg','C:/Users/ASUS/Desktop/tezheng/b8.txt','C:/Users/ASUS/Desktop/image database_1/'),(39,'b9.jpg','C:/Users/ASUS/Desktop/tezheng/b9.txt','C:/Users/ASUS/Desktop/image database_1/');
/*!40000 ALTER TABLE `imagecolor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-06 11:38:20
