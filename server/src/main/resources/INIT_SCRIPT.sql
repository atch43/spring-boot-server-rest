-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: projectdb
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(1000) NOT NULL,
  `extra` varchar(1000) NOT NULL,
  `createdAt` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
INSERT INTO `log` VALUES (2,'Trying getUsersByKeyword(page=1)','',1587885425567),(3,'Trying getUsersByKeyword(page=1)','',1587885433276),(4,'Trying getUsersByKeyword(page=1)','',1587885445421),(5,'Access forbidden: getUsernames','user3',1587885452774),(6,'Trying getUsersByKeyword(page=1)','',1587885457600),(7,'Trying getUsersByKeyword(page=1)','',1587885465523),(8,'Trying getUsersByKeyword(page=1)','',1587885535140),(9,'Trying getUsersByKeyword(page=1)','',1587885591541),(10,'Trying getUsersByKeyword(page=1)','',1587885594134),(11,'Trying getUsersByKeyword(page=1)','',1587885917382),(12,'Trying getUsersByKeyword(page=1)','',1587885928085),(13,'Trying getUsersByKeyword(page=1)','',1587885929267),(14,'Trying getUsersByKeyword(page=1)','',1587885932298),(15,'Trying getUsersByKeyword(page=1)','',1587885933153),(16,'Trying getUsersByKeyword(page=1)','',1587885936709),(17,'Trying getUsersByKeyword(page=1)','',1587885940192),(18,'Trying getUsersByKeyword(page=1)','',1587885943339),(19,'Trying getUsersByKeyword(page=1)','',1587885988371),(20,'Trying getUsersByKeyword(page=1)','',1587885995657),(21,'Trying getUsersByKeyword(page=1)','',1587886048472),(22,'Internal server error: getUsersByKeyword','\r\n### Error querying database.  Cause: java.sql.SQLSyntaxErrorException: Unknown table \'users\' in field list\r\n### The error may exist in it/project/server/mapper/query/UserQuery.java (best guess)\r\n### The error may involve it.project.server.mapper.query.UserQuery.getUsersByField-Inline\r\n### The error occurred while setting parameters\r\n### SQL: SELECT users.id, users.first_name as firstName, users.last_name as lastName, users.username, users.password, users.dob, users.sex, users.telephone, users.privilege_id WHERE id = ? OR (? = \'\' AND username LIKE \'%\' ? \'%\' AND CONCAT(first_name,\' \', last_name) LIKE \'%\' ? \'%\') AND privilege_id = (select id from user_privileges where description = \'USER\') ORDER BY username DESC\r\n### Cause: java.sql.SQLSyntaxErrorException: Unknown table \'users\' in field list\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Unknown table \'users\' in field list',1587886048620),(23,'Trying getUsersByKeyword(page=1)','',1587886080794),(24,'Trying getUsersByKeyword(page=1)','',1587886083904),(25,'Trying getUsersByKeyword(page=1)','',1587886086715),(26,'Trying getUsersByKeyword(page=1)','',1587886087480),(27,'Trying getUsersByKeyword(page=1)','',1587886089206),(28,'Trying getUsersByKeyword(page=1)','',1587886091026),(29,'Trying getUsersByKeyword(page=1)','',1587886094124),(30,'Trying getUsersByKeyword(page=1)','',1587886101234),(31,'Trying getUsersByKeyword(page=1)','',1587886101793),(32,'Trying getUsersByKeyword(page=1)','',1587886163280),(33,'Trying getUsersByKeyword(page=1)','',1587886165783),(34,'Trying getUsersByKeyword(page=1)','',1587886180928),(35,'Trying getUsersByKeyword(page=1)','',1587886181790),(36,'Trying getUsersByKeyword(page=2)','',1587886186907),(37,'Trying getUsersByKeyword(page=1)','',1587886187874),(38,'Trying getUsersByKeyword(page=1)','',1587886354960),(39,'Trying getUsersByKeyword(page=1)','',1587886358328),(40,'Trying getUsersByKeyword(page=1)','',1587886361193),(41,'Trying getUsersByKeyword(page=1)','',1587886363882),(42,'Trying getUsersByKeyword(page=1)','',1587886757995),(43,'Trying getUsersByKeyword(page=1)','',1587886912563),(44,'Trying getUsersByKeyword(page=1)','',1587888431011),(45,'Trying getUsersByKeyword(page=1)','',1587888455955),(46,'Trying getUsersByKeyword(page=1)','',1587888462813),(47,'Trying getUsersByKeyword(page=1)','',1587888464836),(48,'Trying getUsersByKeyword(page=2)','',1587888467755),(49,'Trying getUsersByKeyword(page=1)','',1587888468505),(50,'Trying getUsersByKeyword(page=1)','',1587888490542),(51,'Trying getUsersByKeyword(page=1)','',1587888496116),(52,'Trying getUsersByKeyword(page=1)','',1587888503188),(53,'Trying getUsersByKeyword(page=1)','',1587888686229);
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skills`
--

DROP TABLE IF EXISTS `skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `skills` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `skill` varchar(127) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skills`
--

LOCK TABLES `skills` WRITE;
/*!40000 ALTER TABLE `skills` DISABLE KEYS */;
INSERT INTO `skills` VALUES (1,'Organization'),(2,'Coding'),(3,'Design'),(4,'Experience'),(5,'Communication'),(6,'Efficiency'),(7,'Creativity');
/*!40000 ALTER TABLE `skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_addresses`
--

DROP TABLE IF EXISTS `user_addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_addresses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `CAP` int(11) DEFAULT NULL,
  `street` varchar(50) DEFAULT NULL,
  `number` varchar(20) DEFAULT NULL,
  `residence` tinyint(1) DEFAULT NULL,
  `user_addressescol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_addresses`
--

LOCK TABLES `user_addresses` WRITE;
/*!40000 ALTER TABLE `user_addresses` DISABLE KEYS */;
INSERT INTO `user_addresses` VALUES (1,1,10456,'via nole','44',0,NULL),(2,1,10345,'via po','2',0,NULL),(3,1,10457,'via nole','45',0,NULL),(4,1,10347,'via po','3',0,NULL),(6,1,10457,'via nole','45',0,NULL),(7,1,10347,'via po','3',0,NULL),(8,1,10460,'via nole','46',0,NULL),(9,1,10351,'via po','4',0,NULL),(13,1,10457,'via nole','45',0,NULL),(14,1,10347,'via po','3',0,NULL),(15,1,10460,'via nole','46',0,NULL),(16,1,10351,'via po','4',0,NULL),(17,1,10463,'via nole','46',0,NULL),(18,1,10354,'via po','4',0,NULL),(19,1,10468,'via nole','47',0,NULL),(20,1,10360,'via po','5',0,NULL),(28,2,10456,'via nole','44',0,NULL),(29,2,10345,'via po','2',0,NULL),(30,2,10457,'via nole','45',0,NULL),(31,2,10347,'via po','3',0,NULL),(32,2,10457,'via nole','45',0,NULL),(33,2,10347,'via po','3',0,NULL),(59,3,10456,'via nole','44',0,NULL),(60,3,10345,'via po','2',0,NULL),(61,3,10457,'via nole','45',1,NULL),(62,3,10347,'via po','3',0,NULL),(63,3,10457,'via nole','45',0,NULL),(64,3,10347,'via po','3',0,NULL);
/*!40000 ALTER TABLE `user_addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_privileges`
--

DROP TABLE IF EXISTS `user_privileges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_privileges` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `level` int(11) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_privileges`
--

LOCK TABLES `user_privileges` WRITE;
/*!40000 ALTER TABLE `user_privileges` DISABLE KEYS */;
INSERT INTO `user_privileges` VALUES (1,10,'USER'),(2,100,'ADMIN'),(3,90,'ACCOUNT_ADMIN'),(4,1000,'SUPERADMIN');
/*!40000 ALTER TABLE `user_privileges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `privilege_id` int(11) DEFAULT NULL,
  `dob` mediumtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','a722c63db8ec8625af6cf71cb8c2d939','nome1234','cognome12','M','3311231231',2,'663638400000'),(2,'account','a722c63db8ec8625af6cf71cb8c2d939','nome2','cognome2','M','3349994251',3,'379209600000'),(3,'user3','a722c63db8ec8625af6cf71cb8c2d939','nome1','cognome1','M','3349994251',1,'726796800000'),(4,'super','a722c63db8ec8625af6cf71cb8c2d939','nome1','cognome1','M','3349994250',4,'157161600000'),(5,'user1245','a722c63db8ec8625af6cf71cb8c2d939','nome1','last','M','3349994251',1,'631324800000'),(6,'user12456','a722c63db8ec8625af6cf71cb8c2d939','nome1','cognome1','M','3349994251',1,'157166238150'),(7,'user124567','a722c63db8ec8625af6cf71cb8c2d939','nome1','cognome1','M','3349994251',1,'157166238150'),(8,'GCCDDE','a722c63db8ec8625af6cf71cb8c2d939','ABFEGI','DAADEE','NA','3349994251',1,'664930860000'),(9,'HEAGCC','a722c63db8ec8625af6cf71cb8c2d939','FAEAIB','BCIFAF','NA','3349994251',1,'664930860000'),(10,'IGGBAG','a722c63db8ec8625af6cf71cb8c2d939','CBAIAF','HAJGFH','NA','3349994251',1,'664930860000'),(11,'AACIHA','a722c63db8ec8625af6cf71cb8c2d939','JHGACB','IIHEGJ','NA','3349994251',1,'664930860000'),(12,'GHEBCH','a722c63db8ec8625af6cf71cb8c2d939','JHHDII','HDDFIF','NA','3349994251',1,'664930860000'),(13,'EDDHGJ','a722c63db8ec8625af6cf71cb8c2d939','JHAHHD','HGAABH','F','3349994251',1,'664934400000'),(14,'BFDCBJ','a722c63db8ec8625af6cf71cb8c2d939','CCHAAC','HAIDCA','NA','3349994251',1,'664934400000'),(15,'EDBFGE','a722c63db8ec8625af6cf71cb8c2d939','EHDECA','FGCGDG','F','3349994251',1,'664930860000'),(16,'ECHBDF','a722c63db8ec8625af6cf71cb8c2d939','FBBBDB','AFEIAD','NA','3349994251',1,'664930860000'),(17,'ICFIIF','a722c63db8ec8625af6cf71cb8c2d939','AJEDED','ECHICG','NA','3349994251',1,'664930860000'),(18,'GJBGJI','a722c63db8ec8625af6cf71cb8c2d939','DCCGCC','HJFIEI','NA','3349994251',1,'664930860000'),(19,'IIHJED','a722c63db8ec8625af6cf71cb8c2d939','EBFBJF','AEICGE','NA','3349994251',1,'664930860000'),(20,'EGCJAF','a722c63db8ec8625af6cf71cb8c2d939','FCDBFC','FCFHEG','M','3349994251',1,'664934400000'),(21,'BIIIDC','a722c63db8ec8625af6cf71cb8c2d939','DAJIBB','EHFEGA','NA','3349994251',1,'664934400000'),(22,'AACAEJ','a722c63db8ec8625af6cf71cb8c2d939','GBIAFF','CEGGDJ','NA','3349994251',1,'664930860000'),(23,'EGJFBB','a722c63db8ec8625af6cf71cb8c2d939','JGDHED','DHIHBE','NA','3349994251',1,'664930860000'),(24,'IJJBII','a722c63db8ec8625af6cf71cb8c2d939','GGDICF','AHEBCI','NA','3349994251',1,'664930860000'),(25,'DCBAIJ','a722c63db8ec8625af6cf71cb8c2d939','AGBIIG','FHDECH','NA','3349994251',1,'664930860000'),(26,'JHGCCC','a722c63db8ec8625af6cf71cb8c2d939','HAJEDD','HGCJCE','NA','3349994251',1,'664934400000'),(27,'DDJGEE','a722c63db8ec8625af6cf71cb8c2d939','GJIFCE','EHHCBB','NA','3349994251',1,'664930860000'),(28,'BCHABF','a722c63db8ec8625af6cf71cb8c2d939','CHAHHE','JCGCFH','NA','3349994251',1,'664930860000'),(29,'CBAFIF','a722c63db8ec8625af6cf71cb8c2d939','CFBAJF','IFBBCJ','NA','3349994251',1,'664930860000'),(30,'IDECFD','a722c63db8ec8625af6cf71cb8c2d939','IEHDDH','FFDICG','NA','3349994251',1,'664930860000');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_skills`
--

DROP TABLE IF EXISTS `users_skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_skills` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `skill_id` int(11) NOT NULL,
  `value` decimal(10,0) NOT NULL,
  `active` bit(1) NOT NULL DEFAULT b'0',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_skills`
--

LOCK TABLES `users_skills` WRITE;
/*!40000 ALTER TABLE `users_skills` DISABLE KEYS */;
INSERT INTO `users_skills` VALUES (4,3,1,60,_binary '','2020-04-25 08:58:06'),(5,3,2,80,_binary '','2020-04-25 08:58:37'),(6,3,3,60,_binary '','2020-04-25 08:58:37'),(7,3,4,50,_binary '','2020-04-25 08:58:37'),(8,3,5,30,_binary '','2020-04-25 08:58:37'),(9,3,6,70,_binary '','2020-04-25 08:58:37'),(10,3,7,90,_binary '','2020-04-25 08:58:37'),(11,4,2,80,_binary '','2020-04-25 09:57:07'),(12,4,3,90,_binary '','2020-04-25 09:57:07'),(13,4,4,50,_binary '','2020-04-25 09:57:07'),(14,4,5,70,_binary '','2020-04-25 09:57:07'),(15,4,6,20,_binary '','2020-04-25 09:57:07'),(16,4,7,60,_binary '','2020-04-25 09:57:07'),(17,4,1,90,_binary '','2020-04-25 09:57:33'),(18,5,1,90,_binary '','2020-04-25 09:58:06'),(19,5,2,70,_binary '','2020-04-25 09:58:06'),(20,5,3,60,_binary '','2020-04-25 09:58:06'),(21,5,4,50,_binary '','2020-04-25 09:58:06'),(22,5,5,20,_binary '','2020-04-25 09:58:06'),(23,5,6,60,_binary '','2020-04-25 09:58:06'),(24,5,7,60,_binary '','2020-04-25 09:58:06');
/*!40000 ALTER TABLE `users_skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'projectdb'
--
/*!50003 DROP FUNCTION IF EXISTS `randomGenerator` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `randomGenerator`() RETURNS varchar(128) CHARSET utf8mb4
    READS SQL DATA
    DETERMINISTIC
BEGIN

SET @chars = 'ABCDEFGHIJ';
SET @charLen = length(@chars);

SET @randomPassword = '';

WHILE length(@randomPassword) < 6
    DO
    SET @randomPassword = concat(@randomPassword, substring(@chars,CEILING(RAND() * @charLen),1));
END WHILE;

RETURN @randomPassword ;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-26 10:29:53
