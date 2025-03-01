-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: db_kursach_7
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `applicant`
--

DROP TABLE IF EXISTS `applicant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `applicant` (
  `id_applicant` int NOT NULL AUTO_INCREMENT,
  `education` varchar(255) DEFAULT NULL,
  `experience` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `rating` double DEFAULT NULL,
  `specialization` varchar(255) DEFAULT NULL,
  `id_user` int DEFAULT NULL,
  PRIMARY KEY (`id_applicant`),
  KEY `FK7yf79mrk9r2pgsku1mjfactia` (`id_user`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applicant`
--

LOCK TABLES `applicant` WRITE;
/*!40000 ALTER TABLE `applicant` DISABLE KEYS */;
INSERT INTO `applicant` VALUES (1,'Высшее','9  лет','',NULL,'IT',3),(2,NULL,NULL,NULL,NULL,NULL,4);
/*!40000 ALTER TABLE `applicant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `firm`
--

DROP TABLE IF EXISTS `firm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `firm` (
  `id_firm` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `field` varchar(45) DEFAULT NULL,
  `year` varchar(255) DEFAULT NULL,
  `website` varchar(45) DEFAULT NULL,
  `director` varchar(105) DEFAULT NULL,
  `work_phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_firm`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `firm`
--

LOCK TABLES `firm` WRITE;
/*!40000 ALTER TABLE `firm` DISABLE KEYS */;
INSERT INTO `firm` VALUES (4,'ОАО \"Победа\"','Продажи','2018','https://bizinspect.by','А.В. Киерчук','80291537585');
/*!40000 ALTER TABLE `firm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recruter`
--

DROP TABLE IF EXISTS `recruter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recruter` (
  `id_recruter` int NOT NULL AUTO_INCREMENT,
  `specialization` varchar(45) DEFAULT NULL,
  `numberOfHires` int DEFAULT NULL,
  `portfolio` varchar(45) DEFAULT NULL,
  `search_speciafication` varchar(45) DEFAULT NULL,
  `id_firm` int NOT NULL,
  `number_of_hires` int DEFAULT NULL,
  `search_specification` varchar(255) DEFAULT NULL,
  `id_user` int DEFAULT NULL,
  PRIMARY KEY (`id_recruter`),
  KEY `r_f_idx` (`id_firm`),
  KEY `FK6eg0eh5gn2g06n0kx54rdudbg` (`id_user`),
  CONSTRAINT `FK6eg0eh5gn2g06n0kx54rdudbg` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_users`),
  CONSTRAINT `FK94xpo9tcfwg15lthqpn7975w` FOREIGN KEY (`id_firm`) REFERENCES `firm` (`id_firm`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recruter`
--

LOCK TABLES `recruter` WRITE;
/*!40000 ALTER TABLE `recruter` DISABLE KEYS */;
INSERT INTO `recruter` VALUES (1,'IT',NULL,'',NULL,4,45,'',6);
/*!40000 ALTER TABLE `recruter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `response`
--

DROP TABLE IF EXISTS `response`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `response` (
  `id_response` int NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) DEFAULT NULL,
  `date_of_create` datetime DEFAULT NULL,
  `git_hub` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `id_applicant` int DEFAULT NULL,
  `vacancy` int DEFAULT NULL,
  PRIMARY KEY (`id_response`),
  KEY `FKmvhfg8u6bqy53tuwqu8wqnsmw` (`id_applicant`),
  KEY `FKgvk8ra2cv9cas8tpclh3kt0pp` (`vacancy`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `response`
--

LOCK TABLES `response` WRITE;
/*!40000 ALTER TABLE `response` DISABLE KEYS */;
INSERT INTO `response` VALUES (1,'Хочу работать','2025-03-01 10:03:40','https://github.com/Annamiha23a','Одобрено',1,3);
/*!40000 ALTER TABLE `response` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id_role` int NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (2,'ROLE_USER','Пользователь'),(1,'ROLE_ADMIN','Андминистратор'),(3,'ROLE_RECRUTER','Рекрутер');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `id_user` int NOT NULL,
  `id_role` int NOT NULL,
  PRIMARY KEY (`id_user`,`id_role`),
  KEY `FK2aam9nt2tv8vcfymi3jo9c314` (`id_role`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (3,2),(4,1),(6,3);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id_users` int NOT NULL AUTO_INCREMENT,
  `active` bit(1) DEFAULT NULL,
  `age` varchar(255) DEFAULT NULL,
  `bio` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_users`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (3,_binary '','20','Готов к сотрудничеству','Анна','Михалевич','$2a$10$j75sQYbWpIJkVXuvxDnV4eiIilzM5Ppy.dETOXndScX1GXsP96kda','80257919365','annamiha@list.ru'),(4,_binary '','23','Готов к сотрудничеству','Иван','Иванов','$2a$10$OJsINZHCdNWOt3TthCpsn.pMyVpyXVJ8.KHTIgwQB6SRZt3beXCCK','80291537585','user'),(6,_binary '','25','','Александра','Мороз','$2a$10$d4mUYaF.9bXnFwaT2WVrPOUkrL5ziPXXTRLdpcay/KBrOYlWjBxBa','80291537585','recruter@gmail.com');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vacancy`
--

DROP TABLE IF EXISTS `vacancy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vacancy` (
  `id_vacancy` int NOT NULL AUTO_INCREMENT,
  `position` varchar(45) DEFAULT NULL,
  `salary` varchar(255) DEFAULT NULL,
  `responsibilities` varchar(255) DEFAULT NULL,
  `requirements` varchar(255) DEFAULT NULL,
  `conditions` varchar(255) DEFAULT NULL,
  `keySkills` varchar(105) DEFAULT NULL,
  `date_of_create` datetime DEFAULT NULL,
  `firm_id_firm` int DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `key_skills` varchar(255) DEFAULT NULL,
  `id_firm` int DEFAULT NULL,
  PRIMARY KEY (`id_vacancy`),
  KEY `FKj80ns9l9xycq7lks0ufe6riuy` (`firm_id_firm`),
  KEY `FKgq3f9eo48mw3rh8p98kupxtan` (`id_firm`),
  CONSTRAINT `FKgq3f9eo48mw3rh8p98kupxtan` FOREIGN KEY (`id_firm`) REFERENCES `firm` (`id_firm`),
  CONSTRAINT `FKj80ns9l9xycq7lks0ufe6riuy` FOREIGN KEY (`firm_id_firm`) REFERENCES `firm` (`id_firm`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vacancy`
--

LOCK TABLES `vacancy` WRITE;
/*!40000 ALTER TABLE `vacancy` DISABLE KEYS */;
INSERT INTO `vacancy` VALUES (3,'1С Разработчик','500','Комфортный офис не далеко от метро','Работа на платформе 1С Бухгалтерия','Доработка функционала',NULL,'2025-03-01 10:02:26',NULL,NULL,'1С',4);
/*!40000 ALTER TABLE `vacancy` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-01 10:05:30
