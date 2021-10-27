CREATE DATABASE  IF NOT EXISTS `negozio elettronica` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `negozio elettronica`;
-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: negozio elettronica
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `contract`
--

DROP TABLE IF EXISTS `contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contract` (
  `EmployeeID` int NOT NULL,
  `HireDate` datetime NOT NULL,
  `FiredDate` datetime DEFAULT NULL,
  `ContractType` varchar(60) NOT NULL,
  PRIMARY KEY (`EmployeeID`,`HireDate`),
  KEY `ContractType_idx` (`ContractType`),
  CONSTRAINT `ContractType` FOREIGN KEY (`ContractType`) REFERENCES `contract_types` (`TypeName`),
  CONSTRAINT `EmployeeID` FOREIGN KEY (`EmployeeID`) REFERENCES `employees` (`EmployeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract`
--

LOCK TABLES `contract` WRITE;
/*!40000 ALTER TABLE `contract` DISABLE KEYS */;
/*!40000 ALTER TABLE `contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contract_types`
--

DROP TABLE IF EXISTS `contract_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contract_types` (
  `TypeName` varchar(60) NOT NULL,
  `MinWage` float NOT NULL,
  `WorkingHoursDuration` time NOT NULL,
  PRIMARY KEY (`TypeName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract_types`
--

LOCK TABLES `contract_types` WRITE;
/*!40000 ALTER TABLE `contract_types` DISABLE KEYS */;
/*!40000 ALTER TABLE `contract_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `FirstName` varchar(10) NOT NULL,
  `LastName` varchar(20) NOT NULL,
  `Fiscalcode` varchar(16) NOT NULL,
  `Phone` varchar(10) DEFAULT NULL,
  `DeliveryAddress` varchar(60) NOT NULL,
  PRIMARY KEY (`Fiscalcode`),
  CONSTRAINT `customers_fiscalcode_chk` CHECK ((length(`Fiscalcode`) = 16))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers_accounts`
--

DROP TABLE IF EXISTS `customers_accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers_accounts` (
  `Email` varchar(150) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `Fiscalcode` varchar(16) NOT NULL,
  PRIMARY KEY (`Email`),
  KEY `Fiscalcode_idx` (`Fiscalcode`),
  CONSTRAINT `Fiscalcode` FOREIGN KEY (`Fiscalcode`) REFERENCES `customers` (`Fiscalcode`),
  CONSTRAINT `customers_accounts_fiscalcode_chk` CHECK ((length(`Fiscalcode`) = 16))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers_accounts`
--

LOCK TABLES `customers_accounts` WRITE;
/*!40000 ALTER TABLE `customers_accounts` DISABLE KEYS */;
/*!40000 ALTER TABLE `customers_accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `FirstName` varchar(10) NOT NULL,
  `LastName` varchar(20) NOT NULL,
  `EmployeeID` int NOT NULL,
  `Fiscalcode` varchar(16) NOT NULL,
  `Salary` float NOT NULL,
  PRIMARY KEY (`EmployeeID`),
  UNIQUE KEY `Fiscalcode` (`Fiscalcode`),
  CONSTRAINT `employees_fiscalcode_chk` CHECK ((length(`Fiscalcode`) = 16))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees_account`
--

DROP TABLE IF EXISTS `employees_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees_account` (
  `EmployeeID` int NOT NULL,
  `Password` varchar(45) NOT NULL,
  PRIMARY KEY (`EmployeeID`),
  CONSTRAINT `FK_EmployeeID` FOREIGN KEY (`EmployeeID`) REFERENCES `employees` (`EmployeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees_account`
--

LOCK TABLES `employees_account` WRITE;
/*!40000 ALTER TABLE `employees_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `employees_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `models`
--

DROP TABLE IF EXISTS `models`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `models` (
  `ModelID` int NOT NULL,
  `ModelName` varchar(40) NOT NULL,
  `Brand` varchar(30) NOT NULL,
  `Description` varchar(100) NOT NULL,
  `Category` varchar(15) NOT NULL,
  `ModelImage` longblob,
  `UnitPrice` decimal(10,4) NOT NULL,
  `Discount` int DEFAULT NULL,
  `UnitInStock` int NOT NULL,
  `MaxQuantytyPerOrder` int NOT NULL,
  `SalesCatalogMembership` tinyint(1) NOT NULL,
  `Shelf` varchar(10) NOT NULL,
  `Lane` varchar(10) NOT NULL,
  `Compartment` varchar(10) NOT NULL,
  PRIMARY KEY (`ModelID`),
  KEY `FK_warehouseShelf_idx` (`Shelf`),
  KEY `FK_warehouseCompartment_idx` (`Compartment`),
  KEY `FK_warehouseLane_idx` (`Lane`),
  CONSTRAINT `FK_warehouseCompartment` FOREIGN KEY (`Compartment`) REFERENCES `warehouse` (`Compartment`),
  CONSTRAINT `FK_warehouseLane` FOREIGN KEY (`Lane`) REFERENCES `warehouse` (`Lane`),
  CONSTRAINT `FK_warehouseShelf` FOREIGN KEY (`Shelf`) REFERENCES `warehouse` (`Shelf`),
  CONSTRAINT `models_chk_1` CHECK ((`SalesCatalogMembership` <= 1))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `models`
--

LOCK TABLES `models` WRITE;
/*!40000 ALTER TABLE `models` DISABLE KEYS */;
/*!40000 ALTER TABLE `models` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_details` (
  `ProductID` int NOT NULL,
  `OrderID` int NOT NULL,
  `Quantity` int NOT NULL,
  PRIMARY KEY (`OrderID`,`ProductID`),
  KEY `FK_ProductID_idx` (`ProductID`),
  CONSTRAINT `FK_OrderID` FOREIGN KEY (`OrderID`) REFERENCES `orders` (`OrderID`),
  CONSTRAINT `FK_ProductID` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_details`
--

LOCK TABLES `order_details` WRITE;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `OrderID` int NOT NULL,
  `OrderDate` datetime NOT NULL,
  `TotalAmount` decimal(10,4) NOT NULL,
  `PaymentMethod` varchar(20) NOT NULL,
  `OrderType` varchar(10) NOT NULL,
  `EmployeeID` int DEFAULT NULL,
  `Email` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`OrderID`),
  KEY `FK_employeeID_order_idx` (`EmployeeID`),
  KEY `FK_customerEmail_idx` (`Email`),
  CONSTRAINT `FK_customerEmail` FOREIGN KEY (`Email`) REFERENCES `customers_accounts` (`Email`),
  CONSTRAINT `FK_employeeID_order` FOREIGN KEY (`EmployeeID`) REFERENCES `employees` (`EmployeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `ProductID` int NOT NULL,
  `ModelID` int NOT NULL,
  PRIMARY KEY (`ProductID`),
  KEY `ModelID_idx` (`ModelID`),
  CONSTRAINT `ModelID` FOREIGN KEY (`ModelID`) REFERENCES `models` (`ModelID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_invoices`
--

DROP TABLE IF EXISTS `purchase_invoices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase_invoices` (
  `InvoiceID` int NOT NULL,
  `OrderID` int NOT NULL,
  `IssueDate` date NOT NULL,
  `TotalAmount` decimal(10,4) NOT NULL,
  `SupplierID` int NOT NULL,
  PRIMARY KEY (`InvoiceID`),
  UNIQUE KEY `OrderID` (`OrderID`),
  KEY `FK_supplierID_idx` (`SupplierID`),
  KEY `FK_supplierID_invoice_idx` (`SupplierID`),
  CONSTRAINT `FK_purchaseOrderID` FOREIGN KEY (`OrderID`) REFERENCES `purchase_orders` (`OrderID`),
  CONSTRAINT `FK_supplierID_invoice` FOREIGN KEY (`SupplierID`) REFERENCES `suppliers` (`VAT_Number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_invoices`
--

LOCK TABLES `purchase_invoices` WRITE;
/*!40000 ALTER TABLE `purchase_invoices` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchase_invoices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_orders`
--

DROP TABLE IF EXISTS `purchase_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase_orders` (
  `OrderID` int NOT NULL,
  `Quantity` int NOT NULL,
  `NetPrice` decimal(10,4) NOT NULL,
  `ModelID` int NOT NULL,
  PRIMARY KEY (`OrderID`),
  KEY `FK_ModelID_idx` (`ModelID`),
  CONSTRAINT `FK_ModelID` FOREIGN KEY (`ModelID`) REFERENCES `models` (`ModelID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_orders`
--

LOCK TABLES `purchase_orders` WRITE;
/*!40000 ALTER TABLE `purchase_orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchase_orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receipts`
--

DROP TABLE IF EXISTS `receipts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `receipts` (
  `ReceiptID` int NOT NULL,
  `OrderID` int NOT NULL,
  `IssueDate` datetime NOT NULL,
  PRIMARY KEY (`ReceiptID`),
  UNIQUE KEY `OrderID` (`OrderID`),
  CONSTRAINT `OrderID` FOREIGN KEY (`OrderID`) REFERENCES `orders` (`OrderID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipts`
--

LOCK TABLES `receipts` WRITE;
/*!40000 ALTER TABLE `receipts` DISABLE KEYS */;
/*!40000 ALTER TABLE `receipts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suppliers`
--

DROP TABLE IF EXISTS `suppliers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `suppliers` (
  `VAT_Number` int NOT NULL,
  `CompanyName` varchar(40) NOT NULL,
  `Phone` varchar(24) NOT NULL,
  `Email` varchar(150) NOT NULL,
  `Address` varchar(60) NOT NULL,
  PRIMARY KEY (`VAT_Number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suppliers`
--

LOCK TABLES `suppliers` WRITE;
/*!40000 ALTER TABLE `suppliers` DISABLE KEYS */;
/*!40000 ALTER TABLE `suppliers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warehouse`
--

DROP TABLE IF EXISTS `warehouse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `warehouse` (
  `Shelf` varchar(10) NOT NULL,
  `Lane` varchar(10) NOT NULL,
  `Compartment` varchar(10) NOT NULL,
  UNIQUE KEY `Shelf_UNIQUE` (`Shelf`),
  UNIQUE KEY `Lane_UNIQUE` (`Lane`),
  UNIQUE KEY `Compartment_UNIQUE` (`Compartment`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warehouse`
--

LOCK TABLES `warehouse` WRITE;
/*!40000 ALTER TABLE `warehouse` DISABLE KEYS */;
/*!40000 ALTER TABLE `warehouse` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-27 20:15:04
