CREATE DATABASE  IF NOT EXISTS `hms_new` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `hms_new`;
-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: hms_new
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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `id` int NOT NULL AUTO_INCREMENT,
  `line1` varchar(50) NOT NULL,
  `line2` varchar(50) NOT NULL,
  `unique_id` varchar(45) NOT NULL,
  `city_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_address_city1_idx` (`city_id`),
  CONSTRAINT `fk_address_city1` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'351/D','Pahala Imbulgoda','C-1655471853655',1),(2,'131/BD','Kirillawala','C-1655472015095',3),(3,'134/F','Parakandeniya','C-1655628674120',3),(4,'456/B','Amunugoda','C-1655628739124',1),(5,'87/c','Parakandeniya','C-1655630498507',3),(6,'Wayne Manor','Gotham','C-1656560079245',5),(7,'351/D','Pahala Imbulgoda','C-1658285634108',5),(8,'351/D','Imbulgoda','C-1658313530905',1),(9,'351/2/A','Pahala Imbulgoda','C-1658807917749',2),(10,'157/2/B','Ganemulla','C-1659239394860',1),(11,'351/E','Pahala Imbulgoda','C-1659253067086',8);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `available`
--

DROP TABLE IF EXISTS `available`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `available` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `available`
--

LOCK TABLES `available` WRITE;
/*!40000 ALTER TABLE `available` DISABLE KEYS */;
INSERT INTO `available` VALUES (1,'Available'),(2,'Unavailable');
/*!40000 ALTER TABLE `available` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `checkin`
--

DROP TABLE IF EXISTS `checkin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `checkin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `user_id` int NOT NULL,
  `date_time` datetime NOT NULL,
  `unique_id` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_checkin_customer1_idx` (`customer_id`),
  KEY `fk_checkin_user1_idx` (`user_id`),
  CONSTRAINT `fk_checkin_customer1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `fk_checkin_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checkin`
--

LOCK TABLES `checkin` WRITE;
/*!40000 ALTER TABLE `checkin` DISABLE KEYS */;
INSERT INTO `checkin` VALUES (1,2,1,'2022-06-18 00:00:00','Ch1655552962478'),(2,1,1,'2022-06-18 00:00:00','Ch1655553313834'),(3,2,1,'2022-06-19 00:00:00','Ch1655627998931'),(4,3,1,'2022-06-19 00:00:00','Ch1655628946197'),(5,4,1,'2022-06-19 00:00:00','Ch1655630670813'),(6,5,1,'2022-06-19 00:00:00','Ch1655637569674'),(7,5,2,'2022-06-28 00:00:00','Ch1656387570378'),(8,3,2,'2022-06-28 00:00:00','Ch1656391306455'),(9,2,2,'2022-06-28 00:00:00','Ch1656391736390'),(10,1,2,'2022-06-28 00:00:00','Ch1656392051317'),(11,4,2,'2022-06-28 00:00:00','Ch1656392404168'),(12,5,2,'2022-06-28 00:00:00','Ch1656392422404'),(13,2,2,'2022-06-28 00:00:00','Ch1656392434299'),(14,2,2,'2022-06-28 00:00:00','Ch1656392447123'),(15,6,1,'2022-06-30 00:00:00','Ch1656560118350'),(16,1,1,'2022-06-30 00:00:00','Ch1656561201489'),(17,3,1,'2022-06-30 00:00:00','Ch1656562183777'),(18,6,1,'2022-07-04 00:00:00','Ch1656911950195'),(19,1,1,'2022-07-11 00:00:00','Ch1657520818880'),(20,2,2,'2022-07-11 00:00:00','Ch1657521129841'),(21,3,1,'2022-07-11 00:00:00','Ch1657521227799'),(22,1,1,'2022-07-13 00:00:00','Ch1657720892690'),(23,2,1,'2022-07-13 00:00:00','Ch1657720903434'),(24,3,1,'2022-07-13 00:00:00','Ch1657720916109'),(25,4,1,'2022-07-13 00:00:00','Ch1657720930642'),(26,5,1,'2022-07-13 00:00:00','Ch1657720945304'),(27,6,1,'2022-07-13 00:00:00','Ch1657720957727'),(28,7,1,'2022-07-20 00:00:00','Ch1658285740241'),(29,8,1,'2022-07-25 00:00:00','Ch1658749870911'),(30,7,1,'2022-07-25 00:00:00','Ch1658750128110'),(31,9,1,'2022-07-31 00:00:00','Ch1659238420689'),(32,10,2,'2022-07-31 00:00:00','Ch1659239988897'),(33,11,1,'2022-07-31 00:00:00','Ch1659253169132'),(34,1,1,'2022-07-31 00:00:00','Ch1659280868073');
/*!40000 ALTER TABLE `checkin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `checkin_item`
--

DROP TABLE IF EXISTS `checkin_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `checkin_item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `checkin_id` int NOT NULL,
  `room_id` int NOT NULL,
  `checkin_date` date NOT NULL,
  `checkout_date` date NOT NULL,
  `checkin_status_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_checkin_item_checkin1_idx` (`checkin_id`),
  KEY `fk_checkin_item_room1_idx` (`room_id`),
  KEY `fk_checkin_item_checkin_status1_idx` (`checkin_status_id`),
  CONSTRAINT `fk_checkin_item_checkin1` FOREIGN KEY (`checkin_id`) REFERENCES `checkin` (`id`),
  CONSTRAINT `fk_checkin_item_checkin_status1` FOREIGN KEY (`checkin_status_id`) REFERENCES `checkin_status` (`id`),
  CONSTRAINT `fk_checkin_item_room1` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checkin_item`
--

LOCK TABLES `checkin_item` WRITE;
/*!40000 ALTER TABLE `checkin_item` DISABLE KEYS */;
INSERT INTO `checkin_item` VALUES (1,1,1,'2022-06-18','2022-06-20',2),(2,2,2,'2022-06-18','2022-06-22',2),(3,2,3,'2022-06-18','2022-06-22',2),(4,3,6,'2022-06-19','2022-06-30',2),(5,4,4,'2022-06-19','2022-06-22',2),(6,5,7,'2022-06-19','2022-06-23',2),(7,5,8,'2022-06-19','2022-06-23',2),(8,6,5,'2022-06-19','2022-06-21',2),(9,6,9,'2022-06-19','2022-06-22',2),(10,7,10,'2022-06-28','2022-06-30',2),(11,7,8,'2022-06-28','2022-06-30',2),(12,8,2,'2022-06-28','2022-07-04',2),(13,8,5,'2022-06-28','2022-07-02',2),(14,9,3,'2022-06-28','2022-07-05',2),(15,9,4,'2022-06-28','2022-06-30',2),(16,10,7,'2022-06-28','2022-06-30',2),(17,11,10,'2022-06-28','2022-06-30',2),(18,11,9,'2022-06-28','2022-06-30',2),(19,12,1,'2022-06-22','2022-06-30',2),(20,13,2,'2022-06-01','2022-06-30',2),(21,14,3,'2022-06-10','2022-06-15',2),(22,15,4,'2022-06-30','2022-07-04',2),(23,15,5,'2022-06-30','2022-07-02',2),(24,16,6,'2022-06-30','2022-07-02',2),(25,17,7,'2022-07-01','2022-07-04',2),(26,18,8,'2022-07-04','2022-07-09',2),(27,19,11,'2022-07-11','2022-07-15',2),(28,20,1,'2022-07-11','2022-07-12',2),(29,21,2,'2022-07-11','2022-07-15',2),(30,22,3,'2022-07-13','2022-07-15',2),(31,23,4,'2022-07-13','2022-07-16',2),(32,24,5,'2022-07-13','2022-07-17',2),(33,25,6,'2022-07-13','2022-07-20',2),(34,26,7,'2022-07-13','2022-07-23',2),(35,27,8,'2022-07-13','2022-07-27',2),(36,28,9,'2022-07-20','2022-07-22',2),(37,29,8,'2022-07-25','2022-07-27',2),(38,30,10,'2022-07-25','2022-07-30',2),(39,31,12,'2022-07-31','2022-08-02',2),(40,32,9,'2022-07-31','2022-08-02',2),(41,32,11,'2022-07-31','2022-08-02',2),(42,33,1,'2022-07-31','2022-08-02',2),(43,33,2,'2022-07-31','2022-08-02',2),(44,34,3,'2022-07-31','2022-08-03',2),(45,34,4,'2022-07-31','2022-08-03',2),(46,34,5,'2022-07-31','2022-08-03',2);
/*!40000 ALTER TABLE `checkin_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `checkin_status`
--

DROP TABLE IF EXISTS `checkin_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `checkin_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checkin_status`
--

LOCK TABLES `checkin_status` WRITE;
/*!40000 ALTER TABLE `checkin_status` DISABLE KEYS */;
INSERT INTO `checkin_status` VALUES (1,'Not Complete'),(2,'Complete');
/*!40000 ALTER TABLE `checkin_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'Gampaha'),(2,'Colombo'),(3,'Kadawatha'),(4,'Kiribathgoda'),(5,'Gotham'),(6,'Kaluthara'),(7,'Kelaniya'),(8,'Kandy');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clean`
--

DROP TABLE IF EXISTS `clean`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clean` (
  `id` int NOT NULL AUTO_INCREMENT,
  `room_id` int NOT NULL,
  `cleaner_id` int NOT NULL,
  `date_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_clean_room1_idx` (`room_id`),
  KEY `fk_clean_user1_idx` (`cleaner_id`),
  CONSTRAINT `fk_clean_room1` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`),
  CONSTRAINT `fk_clean_user1` FOREIGN KEY (`cleaner_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clean`
--

LOCK TABLES `clean` WRITE;
/*!40000 ALTER TABLE `clean` DISABLE KEYS */;
INSERT INTO `clean` VALUES (1,1,3,NULL),(2,2,3,NULL),(3,3,4,NULL),(4,4,3,NULL),(5,5,3,NULL),(6,6,3,NULL),(7,7,3,NULL),(8,8,3,NULL),(9,9,3,NULL),(10,10,3,NULL),(11,1,3,NULL),(12,3,3,NULL),(13,2,3,NULL),(14,4,3,NULL),(15,5,3,NULL),(16,6,3,NULL),(17,7,3,NULL),(18,8,3,NULL),(19,9,3,NULL),(20,10,3,NULL),(21,11,3,NULL),(22,8,3,NULL),(23,1,4,'2022-07-25 17:25:59'),(24,2,4,'2022-07-25 17:26:04'),(25,3,4,'2022-07-25 17:26:07'),(26,9,4,'2022-07-25 17:26:11'),(27,4,4,'2022-07-31 12:17:51'),(28,5,4,'2022-07-31 12:17:56'),(29,11,4,'2022-07-31 13:13:33');
/*!40000 ALTER TABLE `clean` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clean_status`
--

DROP TABLE IF EXISTS `clean_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clean_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clean_status`
--

LOCK TABLES `clean_status` WRITE;
/*!40000 ALTER TABLE `clean_status` DISABLE KEYS */;
INSERT INTO `clean_status` VALUES (1,'Cleaned'),(2,'Not Cleaned');
/*!40000 ALTER TABLE `clean_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fname` varchar(45) NOT NULL,
  `lname` varchar(45) NOT NULL,
  `nic` varchar(15) NOT NULL,
  `contact_number` varchar(10) NOT NULL,
  `address_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_customer_address1_idx` (`address_id`),
  CONSTRAINT `fk_customer_address1` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Sandaru','Gunathilake','200010300944','0701794934',1),(2,'Dilshan M','Maduhansa G','200010300944','0752726222',2),(3,'Lahiru','Tharaka','893393579v','0712064592',3),(4,'Tharu','Mahi','200163300243','0754306610',4),(5,'Sandaruwani','Maduhansi','918420991V','0788764949',5),(6,'Bruce','Wayne','200010300944','0713102530',6),(7,'SandaruM','GunathilakeH','200010300944','0754306610',7),(8,'Jayalath','Gunathilake','622600056v','0788764949',8),(9,'Nipuni','Tharika','200010300944','0712064592',9),(10,'Ruwan','Perera','839339357v','0788764949',10),(11,'Sandun','Kumara','200010300944','0701794934',11);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date_time` datetime NOT NULL,
  `user_id` int NOT NULL,
  `checkin_id` int NOT NULL,
  `unique_id` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_invoice_user1_idx` (`user_id`),
  KEY `fk_invoice_checkin1_idx` (`checkin_id`),
  CONSTRAINT `fk_invoice_checkin1` FOREIGN KEY (`checkin_id`) REFERENCES `checkin` (`id`),
  CONSTRAINT `fk_invoice_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (1,'2022-06-19 16:37:20',1,5,'In1655636840773'),(2,'2022-06-19 16:44:25',1,2,'In1655637265011'),(3,'2022-06-19 16:50:07',1,6,'In1655637607055'),(4,'2022-06-23 12:13:29',1,4,'In1655966609392'),(5,'2022-06-27 23:57:39',1,3,'In1656354459523'),(6,'2022-06-28 08:47:17',1,1,'In1656386237887'),(7,'2022-06-28 09:11:46',2,7,'In1656387706843'),(8,'2022-06-28 10:12:36',2,8,'In1656391356548'),(9,'2022-06-28 10:19:15',2,9,'In1656391755802'),(10,'2022-06-28 10:24:35',2,10,'In1656392075337'),(11,'2022-06-28 10:31:20',2,14,'In1656392480732'),(12,'2022-06-28 10:32:16',2,13,'In1656392536287'),(13,'2022-06-28 10:33:09',2,12,'In1656392589236'),(14,'2022-06-28 10:50:06',2,11,'In1656393606577'),(15,'2022-06-28 10:50:58',2,11,'In1656393658873'),(16,'2022-06-30 09:05:53',1,15,'In1656560153358'),(17,'2022-06-30 09:23:37',1,16,'In1656561217965'),(18,'2022-06-30 09:40:07',1,17,'In1656562207242'),(19,'2022-07-04 10:49:27',1,18,'In1656911967583'),(20,'2022-07-11 11:57:34',1,19,'In1657520854056'),(21,'2022-07-11 12:02:41',2,20,'In1657521161724'),(22,'2022-07-11 12:04:02',1,21,'In1657521242411'),(23,'2022-07-13 19:32:54',1,22,'In1657720974619'),(24,'2022-07-16 09:15:10',1,27,'In1657943110299'),(25,'2022-07-20 08:27:24',1,23,'In1658285844261'),(26,'2022-07-25 17:18:37',1,28,'In1658749717372'),(27,'2022-07-25 17:19:09',1,26,'In1658749749556'),(28,'2022-07-25 17:19:35',1,25,'In1658749775451'),(29,'2022-07-25 17:19:55',1,24,'In1658749795272'),(30,'2022-07-25 17:21:55',1,29,'In1658749915075'),(31,'2022-07-26 09:48:58',1,30,'In1658809138212'),(32,'2022-07-31 09:04:11',1,31,'In1659238451135'),(33,'2022-07-31 09:30:27',2,32,'In1659240027698'),(34,'2022-07-31 13:10:48',1,33,'In1659253248045'),(35,'2022-07-31 20:51:30',1,34,'In1659280890542');
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice_item`
--

DROP TABLE IF EXISTS `invoice_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice_item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `day` int NOT NULL,
  `invoice_id` int NOT NULL,
  `checkin_id` int NOT NULL,
  `room_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_invoice_item_invoice1_idx` (`invoice_id`),
  KEY `fk_invoice_item_checkin1_idx` (`checkin_id`),
  KEY `fk_invoice_item_room1_idx` (`room_id`),
  CONSTRAINT `fk_invoice_item_checkin1` FOREIGN KEY (`checkin_id`) REFERENCES `checkin` (`id`),
  CONSTRAINT `fk_invoice_item_invoice1` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`id`),
  CONSTRAINT `fk_invoice_item_room1` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice_item`
--

LOCK TABLES `invoice_item` WRITE;
/*!40000 ALTER TABLE `invoice_item` DISABLE KEYS */;
INSERT INTO `invoice_item` VALUES (1,2,1,5,7),(2,4,1,5,8),(3,4,2,2,3),(4,4,2,2,2),(5,3,3,6,9),(6,2,3,6,5),(7,3,4,4,4),(8,11,5,3,6),(9,2,6,1,1),(10,2,7,7,8),(11,2,7,7,10),(12,4,8,8,5),(13,6,8,8,2),(14,2,9,9,4),(15,7,9,9,3),(16,2,10,10,7),(17,5,11,14,3),(18,29,12,13,2),(19,8,13,12,1),(20,2,14,11,9),(21,2,15,11,10),(22,4,16,15,4),(23,2,16,15,5),(24,2,17,16,6),(25,3,18,17,7),(26,5,19,18,8),(27,4,20,19,11),(28,1,21,20,1),(29,4,22,21,2),(30,2,23,22,3),(31,14,24,27,8),(32,3,25,23,4),(33,2,26,28,9),(34,10,27,26,7),(35,7,28,25,6),(36,4,29,24,5),(37,2,30,29,8),(38,5,31,30,10),(39,2,32,31,12),(40,2,33,32,11),(41,2,33,32,9),(42,2,34,33,1),(43,2,34,33,2),(44,3,35,34,3),(45,3,35,34,4),(46,3,35,34,5);
/*!40000 ALTER TABLE `invoice_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice_payment`
--

DROP TABLE IF EXISTS `invoice_payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice_payment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `invoice_id` int NOT NULL,
  `payment_type_id` int NOT NULL,
  `payment` double NOT NULL,
  `balance` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_invoice_payment_invoice1_idx` (`invoice_id`),
  KEY `fk_invoice_payment_payment_type1_idx` (`payment_type_id`),
  CONSTRAINT `fk_invoice_payment_invoice1` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`id`),
  CONSTRAINT `fk_invoice_payment_payment_type1` FOREIGN KEY (`payment_type_id`) REFERENCES `payment_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice_payment`
--

LOCK TABLES `invoice_payment` WRITE;
/*!40000 ALTER TABLE `invoice_payment` DISABLE KEYS */;
INSERT INTO `invoice_payment` VALUES (1,1,1,13000,0),(2,2,2,16000,0),(3,3,1,19500,0),(4,4,1,10500,0),(5,5,1,60000,5000),(6,6,2,5000,2000),(7,7,1,15000,1000),(8,8,1,35000,2000),(9,9,2,20000,2500),(10,10,1,5000,2000),(11,11,1,10000,2500),(12,12,2,100000,27500),(13,13,1,15000,3000),(14,14,1,10000,3000),(15,15,1,10000,1000),(16,16,1,25000,2000),(17,17,1,10000,0),(18,18,2,5000,500),(19,19,2,15000,2500),(20,20,1,10000,4000),(21,21,1,2000,500),(22,22,1,10000,0),(23,23,1,5000,2000),(24,24,2,40000,5000),(25,25,2,10500,0),(26,26,1,10000,3000),(27,27,1,15000,0),(28,28,2,50000,8000),(29,29,2,20000,0),(30,30,2,5000,0),(31,31,2,30000,5000),(32,32,2,10000,0),(33,33,2,10000,0),(34,34,1,10000,2000),(35,35,1,30000,0);
/*!40000 ALTER TABLE `invoice_payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `login` datetime DEFAULT NULL,
  `logout` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_log_user1_idx` (`user_id`),
  CONSTRAINT `fk_log_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
INSERT INTO `log` VALUES (1,1,'2022-07-21 07:57:57','2022-07-21 07:58:17'),(2,3,'2022-07-21 07:58:36','2022-07-21 07:58:48'),(3,2,'2022-07-21 08:10:50','2022-07-21 08:15:28'),(4,1,'2022-07-21 08:15:52','2022-07-21 08:17:25'),(5,2,'2022-07-21 08:19:49','2022-07-21 08:20:07'),(6,2,'2022-07-21 08:27:30','2022-07-21 08:27:35'),(7,1,'2022-07-21 08:31:44','2022-07-21 08:34:31'),(8,1,'2022-07-21 08:36:03','2022-07-21 08:36:15'),(9,2,'2022-07-21 09:26:23','2022-07-21 09:26:28'),(10,1,'2022-07-22 03:46:50','2022-07-22 03:47:17'),(11,1,'2022-07-25 08:29:26','2022-07-25 08:29:43'),(12,2,'2022-07-25 08:34:24','2022-07-25 08:34:53'),(13,1,'2022-07-25 16:54:32','2022-07-25 16:54:53'),(14,1,'2022-07-25 16:57:53','2022-07-25 17:00:11'),(15,1,'2022-07-25 17:06:55','2022-07-25 17:07:04'),(16,1,'2022-07-25 17:14:25','2022-07-25 17:14:48'),(17,1,'2022-07-25 17:18:18','2022-07-25 17:20:02'),(18,1,'2022-07-25 17:20:42','2022-07-25 17:22:10'),(19,1,'2022-07-25 17:25:06','2022-07-25 17:25:42'),(20,3,'2022-07-25 17:25:53','2022-07-25 17:26:13'),(21,1,'2022-07-25 17:26:18','2022-07-25 17:26:41'),(22,1,'2022-07-25 17:31:10','2022-07-25 17:31:24'),(23,1,'2022-07-25 17:33:37','2022-07-25 17:34:15'),(24,1,'2022-07-26 09:27:33','2022-07-26 09:29:08'),(25,1,'2022-07-26 09:35:50','2022-07-26 09:44:55'),(26,1,'2022-07-26 09:48:17','2022-07-26 09:50:16'),(27,1,'2022-07-26 09:57:05','2022-07-26 09:57:58'),(28,2,'2022-07-26 09:58:33','2022-07-26 09:59:04'),(29,2,'2022-07-26 10:04:21','2022-07-26 10:04:44'),(30,2,'2022-07-26 10:06:42','2022-07-26 10:06:50'),(31,1,'2022-07-31 09:02:45','2022-07-31 09:07:34'),(32,2,'2022-07-31 09:18:18','2022-07-31 09:24:51'),(33,2,'2022-07-31 09:28:48','2022-07-31 09:30:57'),(34,1,'2022-07-31 12:17:00','2022-07-31 12:17:34'),(35,3,'2022-07-31 12:17:42','2022-07-31 12:17:59'),(36,1,'2022-07-31 13:05:45','2022-07-31 13:12:48'),(37,3,'2022-07-31 13:12:57','2022-07-31 13:13:46'),(38,1,'2022-07-31 13:13:52','2022-07-31 13:22:02'),(39,1,'2022-07-31 13:59:51','2022-07-31 13:59:59'),(40,1,'2022-07-31 14:35:02','2022-07-31 14:44:07'),(41,1,'2022-07-31 20:03:44','2022-07-31 20:05:37'),(42,1,'2022-07-31 20:44:24','2022-07-31 20:52:55');
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_type`
--

DROP TABLE IF EXISTS `payment_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_type`
--

LOCK TABLES `payment_type` WRITE;
/*!40000 ALTER TABLE `payment_type` DISABLE KEYS */;
INSERT INTO `payment_type` VALUES (1,'Cash'),(2,'Card');
/*!40000 ALTER TABLE `payment_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `id` int NOT NULL AUTO_INCREMENT,
  `room_type_id` int NOT NULL,
  `available_id` int NOT NULL,
  `clean_status_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_room_room_type1_idx` (`room_type_id`),
  KEY `fk_room_available1_idx` (`available_id`),
  KEY `fk_room_clean_status1_idx` (`clean_status_id`),
  CONSTRAINT `fk_room_available1` FOREIGN KEY (`available_id`) REFERENCES `available` (`id`),
  CONSTRAINT `fk_room_clean_status1` FOREIGN KEY (`clean_status_id`) REFERENCES `clean_status` (`id`),
  CONSTRAINT `fk_room_room_type1` FOREIGN KEY (`room_type_id`) REFERENCES `room_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,1,2,2),(2,2,2,2),(3,1,2,2),(4,3,2,2),(5,4,2,2),(6,5,2,2),(7,1,2,2),(8,2,2,2),(9,3,2,2),(10,4,2,2),(11,1,1,1),(12,4,2,2);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_type`
--

DROP TABLE IF EXISTS `room_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_type`
--

LOCK TABLES `room_type` WRITE;
/*!40000 ALTER TABLE `room_type` DISABLE KEYS */;
INSERT INTO `room_type` VALUES (1,'Single',1500),(2,'Double',2500),(3,'Thriple',3500),(4,'King',5000),(5,'Queen',5500);
/*!40000 ALTER TABLE `room_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `contact_number` varchar(10) NOT NULL,
  `user_type_id` int NOT NULL,
  `user_status_id` int NOT NULL DEFAULT '1',
  `city_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_user_type_idx` (`user_type_id`),
  KEY `fk_user_user_status1_idx` (`user_status_id`),
  KEY `fk_user_city1_idx` (`city_id`),
  CONSTRAINT `fk_user_city1` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`),
  CONSTRAINT `fk_user_user_status1` FOREIGN KEY (`user_status_id`) REFERENCES `user_status` (`id`),
  CONSTRAINT `fk_user_user_type` FOREIGN KEY (`user_type_id`) REFERENCES `user_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Sandaru D','sandaru','12345','0754306610',1,1,2),(2,'Dilshan','dilshan','12345','0701794934',2,1,1),(3,'Maduhansa','madhu','12345','0752726222',3,1,1),(4,'Saman','saman','12345','0713102530',4,1,6);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_status`
--

DROP TABLE IF EXISTS `user_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_status`
--

LOCK TABLES `user_status` WRITE;
/*!40000 ALTER TABLE `user_status` DISABLE KEYS */;
INSERT INTO `user_status` VALUES (1,'Active'),(2,'Inactive');
/*!40000 ALTER TABLE `user_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_type`
--

DROP TABLE IF EXISTS `user_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_type`
--

LOCK TABLES `user_type` WRITE;
/*!40000 ALTER TABLE `user_type` DISABLE KEYS */;
INSERT INTO `user_type` VALUES (1,'Admin'),(2,'Receptionist'),(3,'Cleaning Supervisor'),(4,'Cleaner');
/*!40000 ALTER TABLE `user_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-02 13:26:35
