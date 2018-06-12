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
-- Table structure for table `chuongtrinhhoc`
--

DROP TABLE IF EXISTS `chuongtrinhhoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chuongtrinhhoc` (
  `maChuongTrinhHoc` varchar(45) NOT NULL,
  `tenChuongTrinhHoc` varchar(45) NOT NULL,
  `maNganhHoc` varchar(45) NOT NULL,
  PRIMARY KEY (`maChuongTrinhHoc`),
  KEY `fk_chuongtrinhhoctc_nganhhoctc1_idx` (`maNganhHoc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chuongtrinhhoc`
--

LOCK TABLES `chuongtrinhhoc` WRITE;
/*!40000 ALTER TABLE `chuongtrinhhoc` DISABLE KEYS */;
INSERT INTO `chuongtrinhhoc` VALUES ('NC_CNTT_CN','Niên chế - Công nghệ thông tin - Cử nhân','NC_CNTT'),('NC_CNTT_KS','Niên chế - Công nghệ thông tin - Kỹ sư','NC_CNTT'),('TC_CNTT_CN','Tín chỉ - Công nghệ thông tin - Cử nhân','TC_CNTT'),('TC_CNTT_KS','Tín chỉ - Công nghệ thông tin - Kỹ sư','TC_CNTT');
/*!40000 ALTER TABLE `chuongtrinhhoc` ENABLE KEYS */;
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
