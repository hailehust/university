-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: localhost    Database: quanlysv2
-- ------------------------------------------------------
-- Server version	5.7.22-0ubuntu0.16.04.1

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
-- Table structure for table `hocphan`
--

DROP TABLE IF EXISTS `hocphan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hocphan` (
  `maHocPhan` varchar(40) NOT NULL,
  `tenHocPhan` varchar(45) NOT NULL,
  `trongSoGiuaKy` float NOT NULL,
  `trongSoCuoiKy` float NOT NULL,
  `sttHocKy` varchar(45) DEFAULT NULL,
  `heHocPhan` varchar(5) NOT NULL,
  PRIMARY KEY (`maHocPhan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hocphan`
--

LOCK TABLES `hocphan` WRITE;
/*!40000 ALTER TABLE `hocphan` DISABLE KEYS */;
INSERT INTO `hocphan` VALUES ('NC_HHDC1','Niên chế - Hóa học đại cương 1',0.2,0.8,'1','NC'),('NC_HHDC2','Niên chế - Hóa học đại cương 2',0.2,0.8,'2','NC'),('NC_HHDC3','Niên chế - Hóa học đại cương 3',0.2,0.8,'3','NC'),('NC_VLDC1','Niên chế - Vật lý đại cương 1',0.3,0.7,'1','NC'),('NC_VLDC2','Niên chế - Vật lý đại cương 2',0.3,0.7,'2','NC'),('NC_VLDC3','Niên chế - Vật lý đại cương 3',0.3,0.7,'3','NC'),('TC_HHDC1','Tín chỉ - Hóa học đại cương 1',0.2,0.8,NULL,'TC'),('TC_HHDC2','Tín chỉ - Hóa học đại cương 2',0.2,0.8,NULL,'TC'),('TC_HHDC3','Tín chỉ - Hóa học đại cương 3',0.2,0.8,NULL,'TC'),('TC_VLDC1','Tín chỉ - Vật lý đại cương 1',0.3,0.7,NULL,'TC'),('TC_VLDC2','Tín chỉ - Vật lý đại cương 2',0.3,0.7,NULL,'TC'),('TC_VLDC3','Tín chỉ - Vật lý đại cương 3',0.3,0.7,NULL,'TC');
/*!40000 ALTER TABLE `hocphan` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-15 16:42:32
