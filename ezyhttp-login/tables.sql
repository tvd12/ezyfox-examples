CREATE DATABASE `test` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
use test;

CREATE TABLE IF NOT EXISTS `user_information` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`thirdPartyId` VARCHAR(45),
	`avatarURL` VARCHAR(45),
	`registerTime` DATETIME,
	`birthOfDate` DATE,
	`accountType` VARCHAR(45),
	`fullName` VARCHAR(45),
	`lastUpdatedTime` DATETIME,
	`email` VARCHAR(45),
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;