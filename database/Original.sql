-- MySQL dump 10.13  Distrib 8.0.21, for macos10.15 (x86_64)
--
-- Host: localhost    Database: newdb
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `admin_info`
--

DROP TABLE IF EXISTS `admin_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_info` (
  `ADMIN_ID` int NOT NULL AUTO_INCREMENT,
  `ADMIN_TYPE` int NOT NULL,
  `USER_CODE` varchar(128) NOT NULL,
  `USER_PW` varchar(32) NOT NULL,
  `USER_NAME` varchar(128) DEFAULT NULL,
  `DEPARTMENT` varchar(128) DEFAULT NULL,
  `STATUS` int NOT NULL,
  `NOTE` varchar(128) DEFAULT NULL,
  `CREATION_DATE` datetime NOT NULL,
  `CREATED_BY` int NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  `UPDATED_BY` int NOT NULL,
  PRIMARY KEY (`ADMIN_ID`),
  UNIQUE KEY `ADMIN_INFO_UNIQUE` (`ADMIN_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_info`
--

LOCK TABLES `admin_info` WRITE;
/*!40000 ALTER TABLE `admin_info` DISABLE KEYS */;
INSERT INTO `admin_info` VALUES (1,0,'root','63a9f0ea7bb98050796b649e85481845','root','info',1,NULL,'2020-07-14 00:00:00',0,'2020-07-14 00:00:00',0);
/*!40000 ALTER TABLE `admin_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login` (
  `LOGIN_ID` int NOT NULL AUTO_INCREMENT,
  `ADMIN_ID` int NOT NULL,
  `DEVICE_IP` varchar(32) DEFAULT NULL,
  `DEVICE_INFO` varchar(1024) DEFAULT NULL,
  `SERVICE_KEY` varchar(128) DEFAULT NULL,
  `IS_CURRENT` int DEFAULT NULL,
  `LOGIN_RESULT` int DEFAULT NULL,
  `SESSION_ID` int DEFAULT NULL,
  `DEVICE_ID` varchar(256) DEFAULT NULL,
  `CREATION_DATE` datetime NOT NULL,
  PRIMARY KEY (`LOGIN_ID`),
  UNIQUE KEY `LOGIN_UNIQUE` (`LOGIN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operating_log`
--

DROP TABLE IF EXISTS `operating_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operating_log` (
  `OPERATING_LOG_ID` int NOT NULL AUTO_INCREMENT,
  `OPERATING_LOG_TYPE` int NOT NULL,
  `TABLE_ID` int NOT NULL,
  `TABLE_PID` int NOT NULL,
  `OPERATING_MODE` int NOT NULL,
  `ADMIN_ID` int NOT NULL,
  `REMARK` varchar(512) DEFAULT NULL,
  `CREATION_DATE` datetime NOT NULL,
  PRIMARY KEY (`OPERATING_LOG_ID`),
  UNIQUE KEY `OPERATING_LOG_UNIQUE` (`OPERATING_LOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operating_log`
--

LOCK TABLES `operating_log` WRITE;
/*!40000 ALTER TABLE `operating_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `operating_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendor`
--

DROP TABLE IF EXISTS `vendor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vendor` (
  `VENDOR_ID` int NOT NULL AUTO_INCREMENT,
  `TAX_ID` varchar(128) DEFAULT NULL,
  `VENDOR_NAME` varchar(512) DEFAULT NULL,
  `OWNER_NAME` varchar(512) DEFAULT NULL,
  `NOTE` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`VENDOR_ID`),
  UNIQUE KEY `VENDOR_UNIQUE` (`VENDOR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendor`
--

LOCK TABLES `vendor` WRITE;
/*!40000 ALTER TABLE `vendor` DISABLE KEYS */;
/*!40000 ALTER TABLE `vendor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-02 13:44:25
