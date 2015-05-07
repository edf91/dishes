CREATE DATABASE  IF NOT EXISTS `dishes` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `dishes`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: dishes
-- ------------------------------------------------------
-- Server version	5.6.15

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
-- Table structure for table `dish_activity`
--

DROP TABLE IF EXISTS `dish_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dish_activity` (
  `activity_id` varchar(255) NOT NULL DEFAULT '',
  `dish_id` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`activity_id`,`dish_id`),
  KEY `FK164EC414A159C588` (`activity_id`),
  KEY `FK164EC414D57EFCA8` (`dish_id`),
  CONSTRAINT `FK164EC414A159C588` FOREIGN KEY (`activity_id`) REFERENCES `t_activity` (`id`),
  CONSTRAINT `FK164EC414D57EFCA8` FOREIGN KEY (`dish_id`) REFERENCES `t_dish` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dish_activity`
--

LOCK TABLES `dish_activity` WRITE;
/*!40000 ALTER TABLE `dish_activity` DISABLE KEYS */;
INSERT INTO `dish_activity` VALUES ('174ef50b-f0c4-4d75-b1e5-7e540e16e2cd','0ea95f46-c57c-4aaf-b25b-0b51c4e293a7'),('7ed0b6d2-bb5c-40cc-af90-1b8da4a8dfa0','0ea95f46-c57c-4aaf-b25b-0b51c4e293a7'),('7ed0b6d2-bb5c-40cc-af90-1b8da4a8dfa0','7100c8bc-5e5b-4c89-a920-ddd7b24521cb'),('d8c40c33-93df-49fa-ae81-b914da4752d9','0ea95f46-c57c-4aaf-b25b-0b51c4e293a7'),('d8c40c33-93df-49fa-ae81-b914da4752d9','7100c8bc-5e5b-4c89-a920-ddd7b24521cb');
/*!40000 ALTER TABLE `dish_activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_activity`
--

DROP TABLE IF EXISTS `t_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_activity` (
  `id` varchar(255) NOT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `last_modify_time` bigint(20) DEFAULT NULL,
  `sort_index` bigint(20) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `activity_begin_time` bigint(20) DEFAULT NULL,
  `activity_end_time` bigint(20) DEFAULT NULL,
  `activitySumPrice` double DEFAULT NULL,
  `original_sum_price` double DEFAULT NULL,
  `disabled` tinyint(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_activity`
--

LOCK TABLES `t_activity` WRITE;
/*!40000 ALTER TABLE `t_activity` DISABLE KEYS */;
INSERT INTO `t_activity` VALUES ('174ef50b-f0c4-4d75-b1e5-7e540e16e2cd',1430663405617,1430663405617,0,0,0,0,50,100,0,'特价','特价50'),('7ed0b6d2-bb5c-40cc-af90-1b8da4a8dfa0',1430760514110,1430760514110,0,0,0,0,200,200,0,'套餐3','300'),('d8c40c33-93df-49fa-ae81-b914da4752d9',1430663389055,1430663389055,0,0,0,0,150,200,0,'套餐','选择套餐150');
/*!40000 ALTER TABLE `t_activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_board`
--

DROP TABLE IF EXISTS `t_board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_board` (
  `id` varchar(255) NOT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `last_modify_time` bigint(20) DEFAULT NULL,
  `sort_index` bigint(20) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `isUse` tinyint(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `last_order_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_board`
--

LOCK TABLES `t_board` WRITE;
/*!40000 ALTER TABLE `t_board` DISABLE KEYS */;
INSERT INTO `t_board` VALUES ('3a383933-5be6-472b-b133-de2cf7c62515',1430488755804,1430488755804,0,0,0,'04',NULL),('62acd006-a949-45e9-8ba0-772c15717198',1430488753789,1430488753789,0,0,0,'03',NULL),('a324cffd-a3f5-4f76-a6f6-00abf1f2546a',1430489851783,1430934156311,0,0,0,'09','99b5e136-2bec-48f0-945d-bd449ebe2301'),('a7ad4685-e7c8-4796-ae47-f4cdd69ba91d',1431006819249,1431006819249,0,0,0,'022',NULL),('bf524df6-432e-494a-90d9-139ca8d390aa',1430488757948,1430934627360,0,0,0,'05','a9a17506-9601-41de-8200-87d490fdf946');
/*!40000 ALTER TABLE `t_board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_dish`
--

DROP TABLE IF EXISTS `t_dish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_dish` (
  `id` varchar(255) NOT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `last_modify_time` bigint(20) DEFAULT NULL,
  `sort_index` bigint(20) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `activity_price` double DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_activity` tinyint(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `disabled` bit(1) DEFAULT NULL,
  `dish_type_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKCB5BEE85B0F070BD` (`dish_type_id`),
  CONSTRAINT `FKCB5BEE85B0F070BD` FOREIGN KEY (`dish_type_id`) REFERENCES `t_dish_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_dish`
--

LOCK TABLES `t_dish` WRITE;
/*!40000 ALTER TABLE `t_dish` DISABLE KEYS */;
INSERT INTO `t_dish` VALUES ('0ea95f46-c57c-4aaf-b25b-0b51c4e293a7',1430558778171,1430558778171,0,0,0,'长崎杂烩面长崎杂烩面',1,'长崎杂烩面',7,100,'\0','6fd6ff40-4add-4972-ab19-44b40b6b2174'),('1d0919cd-9a25-4faa-a877-3d2c22d23634',1431004192094,1431004192094,0,0,123,'123',1,'asdf',1,123,'','6fd6ff40-4add-4972-ab19-44b40b6b2174'),('25b5ed45-13f4-4d55-a9fb-5d40e10b3a8d',1431006710896,1431006710896,0,0,0,'长崎杂烩面长崎杂烩面',0,'吉列猪扒2',0,100,'','6fd6ff40-4add-4972-ab19-44b40b6b2174'),('2bd7785b-8dd7-4820-91a7-6fe47620338f',1431006520611,1431006520611,0,0,444,'sdfasfdsaf',1,'wtf2',0,111,'','b47d6205-0e8f-47c6-94c4-82c3eb40c2ee'),('7100c8bc-5e5b-4c89-a920-ddd7b24521cb',1430558789463,1430558789463,0,0,0,'长崎杂烩面长崎杂烩面',1,'长崎炸面',7,100,'\0','6fd6ff40-4add-4972-ab19-44b40b6b2174'),('840d29d8-f0f5-4406-bcac-b97f5db59080',1431006428447,1431006428447,0,0,444,'sdfasfdsaf',1,'wtf',0,111,'','b47d6205-0e8f-47c6-94c4-82c3eb40c2ee'),('916e4978-d054-4f1d-9a0a-407b6cc043fd',1431006555148,1431006555148,0,0,444,'sdfasfdsaf',1,'231323',0,111,'','b47d6205-0e8f-47c6-94c4-82c3eb40c2ee'),('ab7a0b25-ade5-4418-ac85-9b8df542ad83',1430569800139,1430569800139,0,0,50,'',0,'山椒鸡翅',5,200,'','6fd6ff40-4add-4972-ab19-44b40b6b2174'),('c6e1981a-f78d-41f7-a7cd-233ce4714556',1430558799581,1430558799581,0,0,50,'长崎杂烩面长崎杂烩面',0,'吉列猪扒',7,100,'','6fd6ff40-4add-4972-ab19-44b40b6b2174'),('ca5f2206-1ea0-486a-af7f-39615657cc47',1431006606186,1431006606186,0,0,444,'sdfasfdsaf',1,'asfe',0,111,'','b47d6205-0e8f-47c6-94c4-82c3eb40c2ee'),('ee720fc1-4992-4aca-b0e4-8b4f3eb7bb02',1431006766823,1431006766823,0,0,111,'',1,'山椒鸡翅2',0,200,'\0','6fd6ff40-4add-4972-ab19-44b40b6b2174');
/*!40000 ALTER TABLE `t_dish` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_dish_type`
--

DROP TABLE IF EXISTS `t_dish_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_dish_type` (
  `id` varchar(255) NOT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `last_modify_time` bigint(20) DEFAULT NULL,
  `sort_index` bigint(20) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `disabled` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_dish_type`
--

LOCK TABLES `t_dish_type` WRITE;
/*!40000 ALTER TABLE `t_dish_type` DISABLE KEYS */;
INSERT INTO `t_dish_type` VALUES ('29d6d613-3e88-44df-87f4-c586fa7bd213',1430538062711,1430538062711,0,0,'饮料',0,0),('6fd6ff40-4add-4972-ab19-44b40b6b2174',1430538044725,1430538044725,0,0,'小吃',0,0),('b47d6205-0e8f-47c6-94c4-82c3eb40c2ee',1431009897328,1431009897328,0,0,'寿司',0,0),('ec73d4f1-04bf-43eb-9d9f-8a608bb1ecc4',1430538074419,1430538074419,0,0,'甜品',0,0);
/*!40000 ALTER TABLE `t_dish_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_order`
--

DROP TABLE IF EXISTS `t_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_order` (
  `id` varchar(255) NOT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `last_modify_time` bigint(20) DEFAULT NULL,
  `sort_index` bigint(20) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `activeIds` text,
  `dishIds` text,
  `order_time` bigint(20) DEFAULT NULL,
  `real_price` double DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `board_id` varchar(255) NOT NULL,
  `disabled` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA0C0C3C329C1BC8C` (`board_id`),
  CONSTRAINT `FKA0C0C3C329C1BC8C` FOREIGN KEY (`board_id`) REFERENCES `t_board` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_order`
--

LOCK TABLES `t_order` WRITE;
/*!40000 ALTER TABLE `t_order` DISABLE KEYS */;
INSERT INTO `t_order` VALUES ('2d045a65-8fac-4359-870c-fc656fe98bd1',1430845788886,1430934175083,0,0,'d8c40c33-93df-49fa-ae81-b914da4752d9,7ed0b6d2-bb5c-40cc-af90-1b8da4a8dfa0,d8c40c33-93df-49fa-ae81-b914da4752d9,d8c40c33-93df-49fa-ae81-b914da4752d9,d8c40c33-93df-49fa-ae81-b914da4752d9,d8c40c33-93df-49fa-ae81-b914da4752d9,7ed0b6d2-bb5c-40cc-af90-1b8da4a8dfa0,7ed0b6d2-bb5c-40cc-af90-1b8da4a8dfa0','0ea95f46-c57c-4aaf-b25b-0b51c4e293a7,0ea95f46-c57c-4aaf-b25b-0b51c4e293a7,7100c8bc-5e5b-4c89-a920-ddd7b24521cb,7100c8bc-5e5b-4c89-a920-ddd7b24521cb,0ea95f46-c57c-4aaf-b25b-0b51c4e293a7,0ea95f46-c57c-4aaf-b25b-0b51c4e293a7',1430841600000,1950,'第一次下单;第二次下单啦;降八百','bf524df6-432e-494a-90d9-139ca8d390aa',1),('3acc2337-4e77-43f0-b95d-991f591eb0f4',1430845832792,1430934286370,0,0,'d8c40c33-93df-49fa-ae81-b914da4752d9,7ed0b6d2-bb5c-40cc-af90-1b8da4a8dfa0,7ed0b6d2-bb5c-40cc-af90-1b8da4a8dfa0,174ef50b-f0c4-4d75-b1e5-7e540e16e2cd,d8c40c33-93df-49fa-ae81-b914da4752d9,d8c40c33-93df-49fa-ae81-b914da4752d9,7ed0b6d2-bb5c-40cc-af90-1b8da4a8dfa0,7ed0b6d2-bb5c-40cc-af90-1b8da4a8dfa0','0ea95f46-c57c-4aaf-b25b-0b51c4e293a7,0ea95f46-c57c-4aaf-b25b-0b51c4e293a7,7100c8bc-5e5b-4c89-a920-ddd7b24521cb,0ea95f46-c57c-4aaf-b25b-0b51c4e293a7,0ea95f46-c57c-4aaf-b25b-0b51c4e293a7',1430582400000,1800,'字啊此','a324cffd-a3f5-4f76-a6f6-00abf1f2546a',1),('99b5e136-2bec-48f0-945d-bd449ebe2301',1430934156304,1430934410076,0,0,'d8c40c33-93df-49fa-ae81-b914da4752d9,d8c40c33-93df-49fa-ae81-b914da4752d9,7ed0b6d2-bb5c-40cc-af90-1b8da4a8dfa0,174ef50b-f0c4-4d75-b1e5-7e540e16e2cd,174ef50b-f0c4-4d75-b1e5-7e540e16e2cd','0ea95f46-c57c-4aaf-b25b-0b51c4e293a7,7100c8bc-5e5b-4c89-a920-ddd7b24521cb',1430934156312,1000,'safsd','a324cffd-a3f5-4f76-a6f6-00abf1f2546a',1),('a9a17506-9601-41de-8200-87d490fdf946',1430934627358,1430934635058,0,0,'','c6e1981a-f78d-41f7-a7cd-233ce4714556,ab7a0b25-ade5-4418-ac85-9b8df542ad83,c6e1981a-f78d-41f7-a7cd-233ce4714556',1430934627360,400,'111','bf524df6-432e-494a-90d9-139ca8d390aa',1);
/*!40000 ALTER TABLE `t_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `type` varchar(31) NOT NULL,
  `id` varchar(255) NOT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `last_modify_time` bigint(20) DEFAULT NULL,
  `sort_index` bigint(20) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `disabled` tinyint(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `tel_phone` varchar(255) DEFAULT NULL,
  `user_account` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES ('ADMIN_USER','011f1c60-2097-469d-a677-0cbdee8f19da',1431011986265,1431011986265,0,0,'sdad',0,'888888',NULL,'admin22'),('WAITER_USER','358ad0e8-b2ab-4974-a766-3f64605edec0',1431011814638,1431011814638,0,0,'add',0,'888888',NULL,'admin3'),('CASHIER_USER','5f56e9cb-11b6-4b7b-8d9e-5d48dcd86fa9',1430239444739,1430239444739,0,0,'admin1',0,'888888',NULL,'admin1'),('ADMIN_USER','ae9fa5bb-b607-417e-9ae7-48961f54e62e',1430236086625,1430236086625,0,0,'admin',0,'admin',NULL,'admin'),('ADMIN_USER','f7f17fe3-6c1d-41d2-85e8-7bdd05719b2d',1431011883676,1431011883676,0,0,'sdad',0,'888888',NULL,'admin2');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-05-08  2:02:15
