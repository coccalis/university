CREATE DATABASE  IF NOT EXISTS `chartered_airlines_bd` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `chartered_airlines_bd`;
-- MariaDB dump 10.19  Distrib 10.4.21-MariaDB, for Win64 (AMD64)
--
-- Host: 127.0.0.1    Database: chartered_airlines_bd
-- ------------------------------------------------------
-- Server version	10.4.21-MariaDB

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
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customers` (
  `customer_no` decimal(3,0) NOT NULL,
  `customer_lastname` varchar(25) DEFAULT NULL,
  `customer_firstname` varchar(25) DEFAULT NULL,
  `citizenship` varchar(10) DEFAULT NULL,
  `birth_date` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`customer_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'Doe','John','CY','2000-03-17'),(2,'Kokkalis','Xristos','GR','2001-11-12'),(3,'Black','Donald','US','2001-01-06');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flights`
--

DROP TABLE IF EXISTS `flights`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flights` (
  `flight_no` decimal(3,0) NOT NULL,
  `departure` varchar(255) DEFAULT NULL,
  `arrival` varchar(255) DEFAULT NULL,
  `seats` int(3) DEFAULT NULL,
  `free_seats` int(3) DEFAULT NULL,
  PRIMARY KEY (`flight_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flights`
--

LOCK TABLES `flights` WRITE;
/*!40000 ALTER TABLE `flights` DISABLE KEYS */;
INSERT INTO `flights` VALUES (1,'Eleftherios Venizelos','London Southend Airport',50,15),(2,'Venice Marco Polo Airport','Macedonia',60,30),(3,'London Heathrow','Chania International Airport',80,40);
/*!40000 ALTER TABLE `flights` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `res_customer_view`
--

DROP TABLE IF EXISTS `res_customer_view`;
/*!50001 DROP VIEW IF EXISTS `res_customer_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `res_customer_view` (
  `Res_ID` tinyint NOT NULL,
  `C_ID` tinyint NOT NULL,
  `C_NAME` tinyint NOT NULL,
  `Flight_ID` tinyint NOT NULL,
  `COST` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservations` (
  `res_no` decimal(3,0) NOT NULL,
  `customer_no` decimal(3,0) NOT NULL,
  `flight_no` decimal(3,0) NOT NULL,
  `price` double(10,2) DEFAULT NULL,
  PRIMARY KEY (`res_no`),
  KEY `FK_customer_no` (`customer_no`),
  KEY `FK_flight_no` (`flight_no`),
  CONSTRAINT `FK_customer_no` FOREIGN KEY (`customer_no`) REFERENCES `customers` (`customer_no`),
  CONSTRAINT `FK_flight_no` FOREIGN KEY (`flight_no`) REFERENCES `flights` (`flight_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations`
--

LOCK TABLES `reservations` WRITE;
/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
INSERT INTO `reservations` VALUES (1,1,1,600.00),(2,2,2,300.00),(3,3,2,400.00),(4,1,3,550.00),(5,2,3,700.00),(6,3,3,800.00),(7,3,1,1000.00);
/*!40000 ALTER TABLE `reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `reservations_view`
--

DROP TABLE IF EXISTS `reservations_view`;
/*!50001 DROP VIEW IF EXISTS `reservations_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `reservations_view` (
  `Res_ID` tinyint NOT NULL,
  `C_ID` tinyint NOT NULL,
  `Flight_ID` tinyint NOT NULL,
  `COST` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `res_customer_view`
--

/*!50001 DROP TABLE IF EXISTS `res_customer_view`*/;
/*!50001 DROP VIEW IF EXISTS `res_customer_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `res_customer_view` AS select distinct `reservations`.`res_no` AS `Res_ID`,`reservations`.`customer_no` AS `C_ID`,`customers`.`customer_firstname` AS `C_NAME`,`reservations`.`flight_no` AS `Flight_ID`,`reservations`.`price` AS `COST` from (`reservations` join `customers` on(`customers`.`customer_no` = `reservations`.`customer_no`)) order by `customers`.`customer_firstname` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `reservations_view`
--

/*!50001 DROP TABLE IF EXISTS `reservations_view`*/;
/*!50001 DROP VIEW IF EXISTS `reservations_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `reservations_view` AS select `reservations`.`res_no` AS `Res_ID`,`reservations`.`customer_no` AS `C_ID`,`reservations`.`flight_no` AS `Flight_ID`,`reservations`.`price` AS `COST` from `reservations` */
/*!50002 WITH CASCADED CHECK OPTION */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-10 22:14:21
