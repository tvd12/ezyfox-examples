CREATE DATABASE `test` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

-- create table access_token
CREATE TABLE IF NOT EXISTS `access_token` (
	`accessToken` VARCHAR(256) NOT NULL,
	`idToken` VARCHAR(256) NOT NULL,
	`firstIssueAt` DATETIME,
	`expireAt` DATETIME,
	`expireIn` DATETIME,
	`userId` BIGINT,
    `createTime` DATETIME,
    `updateTime` DATETIME,
     `deleted` TINYINT,
	`version` INT,
	PRIMARY KEY (`accessToken`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- create table user_information
CREATE TABLE IF NOT EXISTS `user_information` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`lastName` VARCHAR(45),
	`gender` INT,
	`avatarURL` VARCHAR(256),
	`password` VARCHAR(256),
	`birthOfDate` DATE,
	`accountType` VARCHAR(45),
	`fullName` VARCHAR(45),
    `firstName` VARCHAR(45),
	`email` VARCHAR(45),
	`createTime` DATETIME,
    `updateTime` DATETIME,
    `deleted` TINYINT,
    `version` INT,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;