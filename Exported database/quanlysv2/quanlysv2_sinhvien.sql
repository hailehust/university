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
-- Table structure for table `sinhvien`
--

DROP TABLE IF EXISTS `sinhvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sinhvien` (
  `heSinhVien` varchar(45) NOT NULL,
  `mssv` varchar(20) NOT NULL,
  `hoTen` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `maNganhHoc` varchar(45) NOT NULL,
  `maChuongTrinhHoc` varchar(45) NOT NULL,
  `maLopSinhVien` varchar(45) NOT NULL,
  `trangThai` varchar(45) NOT NULL,
  `bangTotNghiep` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`mssv`),
  KEY `fk_sinhvientc_3_idx` (`maLopSinhVien`),
  KEY `fk_sinhvientc_2_idx` (`maChuongTrinhHoc`),
  KEY `fk_sinhvientc_1_idx` (`maNganhHoc`),
  CONSTRAINT `fk_sinhvientc_1` FOREIGN KEY (`maNganhHoc`) REFERENCES `nganhhoc` (`maNganhHoc`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sinhvientc_2` FOREIGN KEY (`maChuongTrinhHoc`) REFERENCES `chuongtrinhhoc` (`maChuongTrinhHoc`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sinhvientc_3` FOREIGN KEY (`maLopSinhVien`) REFERENCES `lopsinhvien` (`maLopSinhVien`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sinhvien`
--

LOCK TABLES `sinhvien` WRITE;
/*!40000 ALTER TABLE `sinhvien` DISABLE KEYS */;
INSERT INTO `sinhvien` VALUES ('NC','NC_20180001','Niên Thị Chế','nc1','nc1','NC_CNTT','NC_CNTT_KS','NC_CNTT_KS_2018_A','hoc',NULL),('NC','NC_20180002','Niên Thị Chế','nc2','nc2','NC_CNTT','NC_CNTT_KS','NC_CNTT_KS_2018_A','hoc',NULL),('NC','NC_20180003','Niên Thị Chế','nc3','nc3','NC_CNTT','NC_CNTT_KS','NC_CNTT_KS_2018_A','hoc',NULL),('NC','NC_20180004','Niên Thị Chế','nc4','nc4','NC_CNTT','NC_CNTT_KS','NC_CNTT_KS_2018_A','hoc',NULL),('NC','NC_20180005','Niên Thị Chế','nc5','nc5','NC_CNTT','NC_CNTT_KS','NC_CNTT_KS_2018_A','hoc',NULL),('NC','NC_20180006','Niên Thị Chế','nc6','nc6','NC_CNTT','NC_CNTT_KS','NC_CNTT_KS_2018_A','hoc',NULL),('NC','NC_20180007','Niên Thị Chế','nc7','nc7','NC_CNTT','NC_CNTT_KS','NC_CNTT_KS_2018_A','hoc',NULL),('NC','NC_20180008','Niên Thị Chế','nc8','nc8','NC_CNTT','NC_CNTT_KS','NC_CNTT_KS_2018_A','hoc',NULL),('NC','NC_20180009','Niên Thị Chế','nc9','nc9','NC_CNTT','NC_CNTT_KS','NC_CNTT_KS_2018_A','hoc',NULL),('NC','NC_20180010','Niên Thị Chế','nc10','nc10','NC_CNTT','NC_CNTT_KS','NC_CNTT_KS_2018_A','hoc',NULL),('NC','NC_20180011','Niên Thị Chế','nc11','nc11','NC_CNTT','NC_CNTT_KS','NC_CNTT_KS_2018_A','hoc',NULL),('TC','TC_20180001','Tín Văn Chỉ','tc1','tc1','TC_CNTT','TC_CNTT_KS','TC_CNTT_KS_2018_A','hoc',NULL),('TC','TC_20180002','Tín Văn Chỉ','tc2','tc2','TC_CNTT','TC_CNTT_KS','TC_CNTT_KS_2018_A','hoc',NULL),('TC','TC_20180003','Tín Văn Chỉ','tc3','tc3','TC_CNTT','TC_CNTT_KS','TC_CNTT_KS_2018_A','hoc',NULL),('TC','TC_20180004','Tín Văn Chỉ','tc4','tc4','TC_CNTT','TC_CNTT_KS','TC_CNTT_KS_2018_A','hoc',NULL),('TC','TC_20180005','Tín Văn Chỉ','tc5','tc5','TC_CNTT','TC_CNTT_KS','TC_CNTT_KS_2018_A','hoc',NULL),('TC','TC_20180006','Tín Văn Chỉ','tc6','tc6','TC_CNTT','TC_CNTT_KS','TC_CNTT_KS_2018_A','hoc',NULL),('TC','TC_20180007','Tín Văn Chỉ','tc7','tc7','TC_CNTT','TC_CNTT_KS','TC_CNTT_KS_2018_A','hoc',NULL),('TC','TC_20180008','Tín Văn Chỉ','tc7','tc8','TC_CNTT','TC_CNTT_KS','TC_CNTT_KS_2018_A','hoc',NULL),('TC','TC_20180009','Tín Văn Chỉ','tc9','tc9','TC_CNTT','TC_CNTT_KS','TC_CNTT_KS_2018_A','hoc',NULL),('TC','TC_20180010','Tín Văn Chỉ','tc10','tc10','TC_CNTT','TC_CNTT_KS','TC_CNTT_KS_2018_A','hoc',NULL),('TC','TC_20180011','Tín Văn Chỉ','tc11','tc11','TC_CNTT','TC_CNTT_KS','TC_CNTT_KS_2018_A','hoc','');
/*!40000 ALTER TABLE `sinhvien` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-15 16:42:33
