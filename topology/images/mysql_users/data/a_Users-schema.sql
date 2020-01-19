-- Users Database Schema
-- Version 1.0

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS Users;
CREATE SCHEMA Users;
USE Users;

--
-- Table structure fortable `User`
--

CREATE TABLE User (
  email VARCHAR(45) UNIQUE NOT NULL,
  first_name VARCHAR(45) NOT NULL,
  last_name VARCHAR(45) NOT NULL,
  role VARCHAR(45) NOT NULL,
  password VARCHAR(255) NOT NULL,
  PRIMARY KEY  (email)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `User`(`email`,`first_name`,`last_name`,`role`,`password`) VALUES
	('kaerdhalis@esport.com','benji','leG','admin','qwerty24');
