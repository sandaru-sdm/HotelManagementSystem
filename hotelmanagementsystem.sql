CREATE DATABASE  IF NOT EXISTS `hotelmanagementsystem` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `hotelmanagementsystem`;
-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: hotelmanagementsystem
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `checkin`
--

DROP TABLE IF EXISTS `checkin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `checkin` (
  `checkinid` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `customernic` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `roomtype` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `roomno` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `checkindate` datetime DEFAULT NULL,
  `checkoutdate` datetime DEFAULT NULL,
  `noofday` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`checkinid`) USING BTREE,
  KEY `FK_CUSNIC` (`customernic`) USING BTREE,
  KEY `FK_ROOMNO` (`roomno`) USING BTREE,
  KEY `FK_ROOMTYPE` (`roomtype`) USING BTREE,
  CONSTRAINT `FK_CUSNIC` FOREIGN KEY (`customernic`) REFERENCES `customer` (`nic`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_ROOMNO` FOREIGN KEY (`roomno`) REFERENCES `room` (`roomno`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_ROOMTYPE` FOREIGN KEY (`roomtype`) REFERENCES `room` (`type`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checkin`
--

LOCK TABLES `checkin` WRITE;
/*!40000 ALTER TABLE `checkin` DISABLE KEYS */;
INSERT INTO `checkin` VALUES ('CH001','200010300944','Single','R001','2021-11-18 10:18:40','2021-11-25 10:57:19','7',17500.00,0),('CH0010','1122','King','R011','2021-12-04 09:53:24','2021-12-11 10:19:45','7',38500.00,0),('CH0011','2244','Double','R002','2021-12-04 10:30:01','2021-12-10 10:32:06','6',18000.00,0),('CH0012','948303620V','Double','R002','2021-12-04 10:49:17','2021-12-09 10:53:28','5',27000.00,0),('CH0013','200010300944','Double','R002','2021-12-04 11:17:46','2021-12-06 17:04:10','2',6000.00,1),('CH002','12345','King','R011','2021-11-21 10:13:53','2021-11-25 10:46:02','4',22000.00,1),('CH003','200010300944','Quad','R004','2021-11-23 10:09:56','2021-11-25 10:09:59','2',8000.00,1),('CH004','200010300944','Triple','R003','2021-11-23 10:16:04','2021-11-24 10:19:02','1',3500.00,0),('CH005','123456789','Single','R001','2021-11-26 14:39:27','2021-11-27 14:42:51','1',2500.00,0),('CH006','0112334','Double','R002','2021-11-27 09:21:39','2021-11-30 09:23:06','3',9000.00,0),('CH007','abcabc','King','R011','2021-11-27 09:25:43','2021-11-30 09:27:05','3',16500.00,0),('CH008','200010300944','Double','R002','2021-11-28 10:37:16','2021-11-30 10:38:31','2',6000.00,0),('CH009','987654','Single','R001','2021-12-01 10:07:45','2021-12-04 00:00:00','3',7500.00,0);
/*!40000 ALTER TABLE `checkin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `nic` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `fname` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `lname` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `mobile` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `email` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `gender` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `country` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`nic`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('0112334','Mia','Wolf','012345','mia@gmail.com','Female','USA',1),('1122','Dua','Lipa','0701794934','dua@gmail.com','Female','UK',1),('12345','John','Whick','0716444576','john@gmail.com','Male','USA',1),('123456789','Oliver','Quin','0788764949','oliver@gmail.com','Male','USA',1),('200010300944','Sandaru','Gunathilake','0754306610','sandarugunathilake09@gmail.com','Male','USA',1),('2244','Oliver','Quin','0754306610','oliver@gmail.com','Male','USA',1),('948303620V','Nipuni','Thrika','0705840435','nipuni@gmail.com','Female','Sri Lanka',1),('987654','Peter','Parker','1122354','peter@gmail.com','Male','USAQ',1),('abcabc','Mike','John','123123','mike@gmail.com','Male','Germany',1);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `empid` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `fname` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `lname` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `contact` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`empid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('E001','Bruce','Wayne','0754306610',1),('E002','Cark','Kent','0701794934',1),('E003','Diana','Prince','0752726222',1),('E004','Tony','Stark','0332257027',1);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fooditem`
--

DROP TABLE IF EXISTS `fooditem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fooditem` (
  `id` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `name` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `price` double(20,2) DEFAULT NULL,
  `imgpath` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fooditem`
--

LOCK TABLES `fooditem` WRITE;
/*!40000 ALTER TABLE `fooditem` DISABLE KEYS */;
INSERT INTO `fooditem` VALUES ('F001','Fried Rice',450.00,'C:/Users/MCTech/Desktop/Picture/food/fried_rice.jpg',1),('F002','Pasta',350.00,'C:/Users/MCTech/Desktop/Picture/food/index.jpg',1),('F003','Pizza',550.00,'C:/Users/MCTech/Desktop/Picture/food/pizza.jpg',1),('F004','Steak',890.00,'C:/Users/MCTech/Desktop/Picture/food/steak.jpg',1),('F005','Tacos',150.00,'C:/Users/MCTech/Desktop/Picture/food/tacos.jpg',1),('F006','Fruit Juice',250.00,'C:/Users/MCTech/Desktop/Picture/food/fruit juice.jpg',1),('F007','Soft Drinks',100.00,'C:/Users/MCTech/Desktop/Picture/food/soft drinks.jpg',1),('F008','Coockies',50.00,'C:/Users/MCTech/Desktop/Picture/food/chocolate-chip-cookie-16-500x375.jpg',1),('F009','Buns',30.00,'C:/Users/MCTech/Desktop/Picture/food/photo-1592811773343-9abf0b1a6920.jpg',1);
/*!40000 ALTER TABLE `fooditem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice` (
  `invoiceno` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `customerid` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `date` datetime DEFAULT NULL,
  `total` double(20,2) DEFAULT NULL,
  `discount` double(20,2) DEFAULT NULL,
  `nettotal` double(20,2) DEFAULT NULL,
  `payment` double(20,2) DEFAULT NULL,
  `empid` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`invoiceno`) USING BTREE,
  KEY `FK_CUSTOMERID` (`customerid`) USING BTREE,
  KEY `FK_INVOICE_EMPLOYEE` (`empid`) USING BTREE,
  CONSTRAINT `FK_CUSTOMERID` FOREIGN KEY (`customerid`) REFERENCES `customer` (`nic`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_INVOICE_EMPLOYEE` FOREIGN KEY (`empid`) REFERENCES `employee` (`empid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES ('INC1','200010300944','2021-11-22 13:14:55',5800.00,0.00,5800.00,6000.00,'E001'),('INC10','123456789','2021-11-26 14:43:16',6300.00,0.00,6300.00,7000.00,'E001'),('INC11','0112334','2021-11-27 09:23:29',11700.00,0.00,11700.00,15000.00,'E001'),('INC12','abcabc','2021-11-27 09:27:22',24350.00,0.00,24350.00,30000.00,'E001'),('INC13','200010300944','2021-11-28 10:38:48',14130.00,0.00,14130.00,20000.00,'E001'),('INC14','200010300944','2021-12-01 10:12:56',12930.00,0.00,12930.00,15000.00,'E001'),('INC15','1122','2021-12-04 10:20:32',44380.00,4380.00,40000.00,40000.00,'E001'),('INC16','987654','2021-12-04 10:25:10',13550.00,0.00,13550.00,15000.00,'E003'),('INC17','2244','2021-12-04 10:32:26',19350.00,350.00,19000.00,20000.00,'E003'),('INC18','948303620V','2021-12-04 10:54:58',15900.00,0.00,15900.00,20000.00,'E001'),('INC2','200010300944','2021-11-22 13:16:52',5800.00,0.00,5800.00,6000.00,'E001'),('INC3','200010300944','2021-11-22 13:25:54',5800.00,0.00,5800.00,6000.00,'E001'),('INC4','200010300944','2021-11-22 14:00:22',5800.00,0.00,5800.00,6000.00,'E001'),('INC5','200010300944','2021-11-22 14:03:28',5800.00,0.00,5800.00,6000.00,'E001'),('INC6','200010300944','2021-11-23 10:12:39',8000.00,0.00,8000.00,10000.00,'E001'),('INC7','200010300944','2021-11-23 10:19:27',3500.00,0.00,3500.00,4000.00,'E001'),('INC8','12345','2021-11-25 10:46:45',22000.00,0.00,22000.00,25000.00,'E001'),('INC9','200010300944','2021-11-25 10:57:42',18300.00,0.00,18300.00,20000.00,'E001');
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoiceitem`
--

DROP TABLE IF EXISTS `invoiceitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoiceitem` (
  `id` int NOT NULL AUTO_INCREMENT,
  `invoiceno` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `orderid` varchar(20) CHARACTER SET latin1 COLLATE latin1_spanish_ci DEFAULT NULL,
  `foodid` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `unitprice` double(10,2) DEFAULT NULL,
  `qty` double(10,2) DEFAULT NULL,
  `total` double(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_INVOICENO` (`invoiceno`) USING BTREE,
  KEY `FK_FOODID1` (`foodid`) USING BTREE,
  CONSTRAINT `FK_FOODID1` FOREIGN KEY (`foodid`) REFERENCES `fooditem` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_INVOICENO` FOREIGN KEY (`invoiceno`) REFERENCES `invoice` (`invoiceno`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoiceitem`
--

LOCK TABLES `invoiceitem` WRITE;
/*!40000 ALTER TABLE `invoiceitem` DISABLE KEYS */;
INSERT INTO `invoiceitem` VALUES (1,'INC5','O001','F001',450.00,1.00,450.00),(2,'INC5','O001','F002',350.00,1.00,350.00),(3,'INC9','O001','F001',450.00,1.00,450.00),(4,'INC9','O001','F002',350.00,1.00,350.00),(5,'INC10','O001','F001',450.00,1.00,450.00),(6,'INC10','O001','F002',350.00,1.00,350.00),(7,'INC10','O002','F001',450.00,1.00,450.00),(8,'INC10','O002','F002',350.00,1.00,350.00),(9,'INC10','O002','F003',550.00,1.00,550.00),(10,'INC10','O002','F004',890.00,1.00,890.00),(11,'INC10','O002','F005',150.00,1.00,150.00),(12,'INC10','O002','F006',250.00,1.00,250.00),(13,'INC10','O002','F008',50.00,2.00,100.00),(14,'INC10','O002','F009',30.00,2.00,60.00),(15,'INC10','O002','F007',100.00,2.00,200.00),(16,'INC11','O003','F001',450.00,2.00,900.00),(17,'INC11','O003','F009',30.00,10.00,300.00),(18,'INC11','O003','F005',150.00,2.00,300.00),(19,'INC11','O003','F007',100.00,2.00,200.00),(20,'INC11','O003','F006',250.00,4.00,1000.00),(21,'INC12','O004','F001',450.00,4.00,1800.00),(22,'INC12','O004','F009',30.00,10.00,300.00),(23,'INC12','O004','F006',250.00,8.00,2000.00),(24,'INC12','O004','F007',100.00,10.00,1000.00),(25,'INC12','O004','F008',50.00,20.00,1000.00),(26,'INC12','O004','F002',350.00,5.00,1750.00),(27,'INC13','O003','F001',450.00,2.00,900.00),(28,'INC13','O003','F009',30.00,10.00,300.00),(29,'INC13','O003','F005',150.00,2.00,300.00),(30,'INC13','O003','F007',100.00,2.00,200.00),(31,'INC13','O003','F006',250.00,4.00,1000.00),(32,'INC13','O005','F001',450.00,1.00,450.00),(33,'INC13','O005','F002',350.00,2.00,700.00),(34,'INC13','O005','F003',550.00,2.00,1100.00),(35,'INC13','O005','F004',890.00,2.00,1780.00),(36,'INC13','O005','F005',150.00,2.00,300.00),(37,'INC13','O005','F006',250.00,2.00,500.00),(38,'INC13','O005','F007',100.00,2.00,200.00),(39,'INC13','O005','F008',50.00,2.00,100.00),(40,'INC13','O005','F009',30.00,10.00,300.00),(41,'INC14','O006','F001',450.00,1.00,450.00),(42,'INC14','O006','F002',350.00,1.00,350.00),(43,'INC14','O006','F003',550.00,1.00,550.00),(44,'INC14','O006','F004',890.00,1.00,890.00),(45,'INC14','O006','F005',150.00,2.00,300.00),(46,'INC14','O007','F001',450.00,1.00,450.00),(47,'INC14','O007','F002',350.00,1.00,350.00),(48,'INC14','O007','F003',550.00,1.00,550.00),(49,'INC14','O007','F004',890.00,1.00,890.00),(50,'INC14','O007','F005',150.00,1.00,150.00),(51,'INC15','O009','F001',450.00,2.00,900.00),(52,'INC15','O009','F002',350.00,2.00,700.00),(53,'INC15','O009','F003',550.00,2.00,1100.00),(54,'INC15','O009','F004',890.00,2.00,1780.00),(55,'INC15','O009','F005',150.00,2.00,300.00),(56,'INC15','O009','F006',250.00,2.00,500.00),(57,'INC15','O009','F007',100.00,2.00,200.00),(58,'INC15','O009','F008',50.00,2.00,100.00),(59,'INC15','O009','F009',30.00,10.00,300.00),(60,'INC16','O001','F001',450.00,1.00,450.00),(61,'INC16','O001','F002',350.00,1.00,350.00),(62,'INC16','O002','F001',450.00,1.00,450.00),(63,'INC16','O002','F002',350.00,1.00,350.00),(64,'INC16','O002','F003',550.00,1.00,550.00),(65,'INC16','O002','F004',890.00,1.00,890.00),(66,'INC16','O002','F005',150.00,1.00,150.00),(67,'INC16','O002','F006',250.00,1.00,250.00),(68,'INC16','O002','F008',50.00,2.00,100.00),(69,'INC16','O002','F009',30.00,2.00,60.00),(70,'INC16','O002','F007',100.00,2.00,200.00),(71,'INC16','O008','F001',450.00,1.00,450.00),(72,'INC16','O008','F002',350.00,2.00,700.00),(73,'INC16','O008','F003',550.00,2.00,1100.00),(74,'INC17','O0010','F001',450.00,1.00,450.00),(75,'INC17','O0010','F002',350.00,1.00,350.00),(76,'INC17','O0010','F003',550.00,1.00,550.00),(77,'INC18','O0011','F001',450.00,1.00,450.00),(78,'INC18','O0011','F002',350.00,1.00,350.00),(79,'INC18','O0011','F007',100.00,1.00,100.00);
/*!40000 ALTER TABLE `invoiceitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location` (
  `id` int NOT NULL AUTO_INCREMENT,
  `floor` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `floor` (`floor`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1,'Ground Floor',1),(2,'First Floor',1),(3,'Second Floor',1),(4,'Third Floor',1);
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logindetails`
--

DROP TABLE IF EXISTS `logindetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `logindetails` (
  `id` int NOT NULL AUTO_INCREMENT,
  `empid` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `usertype` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `datetime` datetime DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_EMPID2` (`empid`) USING BTREE,
  KEY `FK_USERTYPE2` (`usertype`) USING BTREE,
  CONSTRAINT `FK_EMPID2` FOREIGN KEY (`empid`) REFERENCES `employee` (`empid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_USERTYPE2` FOREIGN KEY (`usertype`) REFERENCES `usertype` (`code`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logindetails`
--

LOCK TABLES `logindetails` WRITE;
/*!40000 ALTER TABLE `logindetails` DISABLE KEYS */;
INSERT INTO `logindetails` VALUES (1,'E001','admin','2021-11-15 16:41:42',1),(2,'E001','admin','2021-11-22 13:14:39',1),(3,'E001','admin','2021-11-22 13:16:38',1),(4,'E001','admin','2021-11-22 13:16:38',1),(5,'E001','admin','2021-11-22 13:25:39',1),(6,'E001','admin','2021-11-22 14:00:05',1),(7,'E001','admin','2021-11-22 14:02:59',1),(8,'E001','admin','2021-11-23 10:12:02',1),(9,'E001','admin','2021-11-23 10:15:02',1),(10,'E001','admin','2021-11-23 10:18:38',1),(11,'E003','RCEP','2021-11-24 10:07:54',1),(12,'E003','RCEP','2021-11-24 10:37:25',1),(13,'E001','admin','2021-11-25 10:44:52',1),(14,'E001','admin','2021-11-25 10:57:05',1),(15,'E001','admin','2021-11-26 10:40:54',1),(16,'E001','admin','2021-11-26 12:48:10',1),(17,'E001','admin','2021-11-26 14:37:43',1),(18,'E001','admin','2021-11-26 14:40:04',1),(19,'E001','admin','2021-11-27 09:20:11',1),(20,'E001','admin','2021-11-28 10:36:46',1),(21,'E003','RCEP','2021-12-01 09:16:11',1),(22,'E001','admin','2021-12-01 10:03:50',1),(23,'E001','admin','2021-12-01 10:06:36',1),(24,'E001','admin','2021-12-01 10:10:21',1),(25,'E001','admin','2021-12-01 10:11:19',1),(26,'E003','RCEP','2021-12-03 14:37:08',1),(27,'E001','admin','2021-12-04 09:51:37',1),(28,'E001','admin','2021-12-04 10:06:34',1),(29,'E001','admin','2021-12-04 10:19:27',1),(30,'E003','RCEP','2021-12-04 10:24:18',1),(31,'E003','RCEP','2021-12-04 10:28:47',1),(32,'E001','admin','2021-12-04 10:47:18',1),(33,'E001','admin','2021-12-04 11:17:24',1),(34,'E001','admin','2021-12-04 17:01:50',1),(35,'E001','admin','2021-12-04 17:03:51',1),(36,'E001','admin','2021-12-04 17:03:51',1),(37,'E001','admin','2021-12-04 17:03:51',1),(38,'E001','admin','2021-12-04 17:03:51',1),(39,'E001','admin','2021-12-04 17:03:52',1),(40,'E001','admin','2021-12-04 17:03:52',1),(41,'E001','admin','2022-03-09 02:09:06',1),(42,'E001','admin','2022-04-02 10:37:06',1);
/*!40000 ALTER TABLE `logindetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderitem`
--

DROP TABLE IF EXISTS `orderitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderitem` (
  `id` int NOT NULL AUTO_INCREMENT,
  `orderid` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `foodid` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `unitprice` double(10,2) DEFAULT NULL,
  `qty` double(10,2) DEFAULT NULL,
  `total` double(10,2) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_FOODID_06` (`foodid`) USING BTREE,
  KEY `FK_ORDERID02` (`orderid`) USING BTREE,
  CONSTRAINT `FK_FOODID_06` FOREIGN KEY (`foodid`) REFERENCES `fooditem` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_ORDERID02` FOREIGN KEY (`orderid`) REFERENCES `ordermanage` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=koi8r ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderitem`
--

LOCK TABLES `orderitem` WRITE;
/*!40000 ALTER TABLE `orderitem` DISABLE KEYS */;
INSERT INTO `orderitem` VALUES (11,'O001','F001',450.00,1.00,450.00,0),(12,'O001','F002',350.00,1.00,350.00,0),(13,'O002','F001',450.00,1.00,450.00,0),(14,'O002','F002',350.00,1.00,350.00,0),(15,'O002','F003',550.00,1.00,550.00,0),(16,'O002','F004',890.00,1.00,890.00,0),(17,'O002','F005',150.00,1.00,150.00,0),(18,'O002','F006',250.00,1.00,250.00,0),(19,'O002','F008',50.00,2.00,100.00,0),(20,'O002','F009',30.00,2.00,60.00,0),(21,'O002','F007',100.00,2.00,200.00,0),(22,'O003','F001',450.00,2.00,900.00,0),(23,'O003','F009',30.00,10.00,300.00,0),(24,'O003','F005',150.00,2.00,300.00,0),(25,'O003','F007',100.00,2.00,200.00,0),(26,'O003','F006',250.00,4.00,1000.00,0),(27,'O004','F001',450.00,4.00,1800.00,0),(28,'O004','F009',30.00,10.00,300.00,0),(29,'O004','F006',250.00,8.00,2000.00,0),(30,'O004','F007',100.00,10.00,1000.00,0),(31,'O004','F008',50.00,20.00,1000.00,0),(32,'O004','F002',350.00,5.00,1750.00,0),(33,'O005','F001',450.00,1.00,450.00,0),(34,'O005','F002',350.00,2.00,700.00,0),(35,'O005','F003',550.00,2.00,1100.00,0),(36,'O005','F004',890.00,2.00,1780.00,0),(37,'O005','F005',150.00,2.00,300.00,0),(38,'O005','F006',250.00,2.00,500.00,0),(39,'O005','F007',100.00,2.00,200.00,0),(40,'O005','F008',50.00,2.00,100.00,0),(41,'O005','F009',30.00,10.00,300.00,0),(42,'O006','F001',450.00,1.00,450.00,0),(43,'O006','F002',350.00,1.00,350.00,0),(44,'O006','F003',550.00,1.00,550.00,0),(45,'O006','F004',890.00,1.00,890.00,0),(46,'O006','F005',150.00,2.00,300.00,0),(47,'O007','F001',450.00,1.00,450.00,0),(48,'O007','F002',350.00,1.00,350.00,0),(49,'O007','F003',550.00,1.00,550.00,0),(50,'O007','F004',890.00,1.00,890.00,0),(51,'O007','F005',150.00,1.00,150.00,0),(52,'O008','F001',450.00,1.00,450.00,0),(53,'O008','F002',350.00,2.00,700.00,0),(54,'O008','F003',550.00,2.00,1100.00,0),(55,'O009','F001',450.00,2.00,900.00,0),(56,'O009','F002',350.00,2.00,700.00,0),(57,'O009','F003',550.00,2.00,1100.00,0),(58,'O009','F004',890.00,2.00,1780.00,0),(59,'O009','F005',150.00,2.00,300.00,0),(60,'O009','F006',250.00,2.00,500.00,0),(61,'O009','F007',100.00,2.00,200.00,0),(62,'O009','F008',50.00,2.00,100.00,0),(63,'O009','F009',30.00,10.00,300.00,0),(64,'O0010','F001',450.00,1.00,450.00,0),(65,'O0010','F002',350.00,1.00,350.00,0),(66,'O0010','F003',550.00,1.00,550.00,0),(67,'O0011','F001',450.00,1.00,450.00,0),(68,'O0011','F002',350.00,1.00,350.00,0),(69,'O0011','F007',100.00,1.00,100.00,0);
/*!40000 ALTER TABLE `orderitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordermanage`
--

DROP TABLE IF EXISTS `ordermanage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordermanage` (
  `id` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `roomno` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `nic` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `total` double(10,2) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_ROOMNO1` (`roomno`) USING BTREE,
  KEY `FK_NIC03` (`nic`) USING BTREE,
  CONSTRAINT `FK_NIC03` FOREIGN KEY (`nic`) REFERENCES `customer` (`nic`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_ROOMNO1` FOREIGN KEY (`roomno`) REFERENCES `room` (`roomno`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordermanage`
--

LOCK TABLES `ordermanage` WRITE;
/*!40000 ALTER TABLE `ordermanage` DISABLE KEYS */;
INSERT INTO `ordermanage` VALUES ('O001','R001','200010300944',800.00,'2021-11-22 12:41:25',0),('O0010','R002','2244',1350.00,'2021-12-04 10:31:43',0),('O0011','R002','948303620V',900.00,'2021-12-04 10:52:10',0),('O002','R001','123456789',3000.00,'2021-11-26 14:42:36',0),('O003','R002','0112334',2700.00,'2021-11-27 09:22:53',0),('O004','R011','abcabc',7850.00,'2021-11-27 09:26:53',0),('O005','R002','200010300944',5430.00,'2021-11-28 10:38:20',0),('O006','R004','200010300944',2540.00,'2021-12-01 09:18:33',0),('O007','R004','200010300944',2390.00,'2021-12-01 10:04:32',0),('O008','R001','987654',2250.00,'2021-12-01 10:08:18',0),('O009','R011','1122',5880.00,'2021-12-04 09:56:05',0);
/*!40000 ALTER TABLE `ordermanage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `roomno` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `type` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `description` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `location` int DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`roomno`) USING BTREE,
  KEY `type` (`type`) USING BTREE,
  KEY `FK_LOCATION` (`location`) USING BTREE,
  CONSTRAINT `FK_LOCATION` FOREIGN KEY (`location`) REFERENCES `location` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_ROOMTYPE1` FOREIGN KEY (`type`) REFERENCES `roomtype` (`type`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES ('R001','Single','A room assigned to one person.',1,2500.00,1),('R002','Double','A room assigned to two people.',1,3000.00,0),('R003','Triple','A room that can accommodate three persons.',1,3500.00,1),('R004','Quad','A room assigned to four people.',1,4000.00,1),('R005','Queen','A room with a queen-sized bed.',2,5000.00,1),('R006','Single','A room assigned to one person.',1,2500.00,0),('R007','Double','A room assigned to two people.',1,3000.00,1),('R008','Triple','A room that can accommodate three persons.',1,3500.00,1),('R009','Quad','A room assigned to four people.',1,4000.00,1),('R010','Queen','A room with a queen-sized bed.',2,5000.00,1),('R011','King','A room with a king-sized bed.',2,5500.00,1),('R012','King','A room with a king-sized bed.',2,5500.00,1),('R013','Twin','A room with two twin beds. ',1,3300.00,1),('R014','Twin','A room with two twin beds.',1,3300.00,1),('R015','Adjoining','Rooms with a common wall but no connecting door.',2,2800.00,0);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roomtype`
--

DROP TABLE IF EXISTS `roomtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roomtype` (
  `type` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roomtype`
--

LOCK TABLES `roomtype` WRITE;
/*!40000 ALTER TABLE `roomtype` DISABLE KEYS */;
INSERT INTO `roomtype` VALUES ('Adjoining',1),('Double',1),('King',1),('Quad',1),('Queen',1),('Single',1),('Triple',1),('Twin',1);
/*!40000 ALTER TABLE `roomtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `securityquestion1`
--

DROP TABLE IF EXISTS `securityquestion1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `securityquestion1` (
  `question` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`question`) USING BTREE,
  KEY `question` (`question`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `securityquestion1`
--

LOCK TABLES `securityquestion1` WRITE;
/*!40000 ALTER TABLE `securityquestion1` DISABLE KEYS */;
INSERT INTO `securityquestion1` VALUES ('In what town or city was your first full time job?',1),('What is the name of your favorite childhood friend?',1),('What is your favorite movie?',1),('What was your childhood nickname?',1),('What was your favorite food as a child?',1);
/*!40000 ALTER TABLE `securityquestion1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `securityquestion2`
--

DROP TABLE IF EXISTS `securityquestion2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `securityquestion2` (
  `question` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`question`) USING BTREE,
  KEY `question` (`question`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `securityquestion2`
--

LOCK TABLES `securityquestion2` WRITE;
/*!40000 ALTER TABLE `securityquestion2` DISABLE KEYS */;
INSERT INTO `securityquestion2` VALUES ('What city were you born in?',1),('What is your astrological sign?',1),('What is your oldest sibling’s middle name?',1),('What was your favorite school teacher’s first name?',1),('What was your first car?',1);
/*!40000 ALTER TABLE `securityquestion2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `systemuser`
--

DROP TABLE IF EXISTS `systemuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `systemuser` (
  `username` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `password` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `empid` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `usertype` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `q1no` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `q1ans` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `q2no` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `q2ans` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`username`) USING BTREE,
  KEY `FK_EMPID1` (`empid`) USING BTREE,
  KEY `FK_USERTYPE` (`usertype`) USING BTREE,
  KEY `FK_QUESTION1` (`q1no`) USING BTREE,
  KEY `FK_QUESTION2` (`q2no`) USING BTREE,
  CONSTRAINT `FK_EMPID1` FOREIGN KEY (`empid`) REFERENCES `employee` (`empid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_QUESTION1` FOREIGN KEY (`q1no`) REFERENCES `securityquestion1` (`question`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_QUESTION2` FOREIGN KEY (`q2no`) REFERENCES `securityquestion2` (`question`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_USERTYPE` FOREIGN KEY (`usertype`) REFERENCES `usertype` (`code`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `systemuser`
--

LOCK TABLES `systemuser` WRITE;
/*!40000 ALTER TABLE `systemuser` DISABLE KEYS */;
INSERT INTO `systemuser` VALUES ('batman','827ccb0eea8a706c4c34a16891f84e7b','E001','admin','In what town or city was your first full time job?','gotham','What city were you born in?','gotham',1),('ironman','202cb962ac59075b964b07152d234b70','E004','ICT','In what town or city was your first full time job?','newyork','What city were you born in?','newyork',1),('superman','202cb962ac59075b964b07152d234b70','E002','cashier','What is the name of your favorite childhood friend?','loise','What city were you born in?','smallville',1),('wonder','202cb962ac59075b964b07152d234b70','E003','RCEP','What was your childhood nickname?','diana','What is your astrological sign?','scorpio',1);
/*!40000 ALTER TABLE `systemuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usertype`
--

DROP TABLE IF EXISTS `usertype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usertype` (
  `code` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `description` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usertype`
--

LOCK TABLES `usertype` WRITE;
/*!40000 ALTER TABLE `usertype` DISABLE KEYS */;
INSERT INTO `usertype` VALUES ('admin','Admin',1),('cashier','cashier',1),('HRM','Human Resource Management',1),('ICT','ICT',1),('None','None',1),('RCEP','Receptionist',1);
/*!40000 ALTER TABLE `usertype` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-02 13:27:16
