CREATE DATABASE `test` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

-- create table access_token
CREATE TABLE IF NOT EXISTS `access_token` (
	`accessToken` VARCHAR(256) NOT NULL,
	`userId` BIGINT,
	`firstIssueAt` DATETIME,
	`expireAt` DATETIME,
	`expireIn` DATETIME,
    `createTime` DATETIME,
    `updateTime` DATETIME,
    `deleted` TINYINT,
	PRIMARY KEY (`accessToken`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- create table user
CREATE TABLE IF NOT EXISTS `user` (
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
	`thirdPartyId` VARCHAR(45),
	`createTime` DATETIME,
    `updateTime` DATETIME,
    `deleted` TINYINT,
    `status` VARCHAR(10),
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- create table user_data
CREATE TABLE IF NOT EXISTS `user_data` (
	`userId` BIGINT NOT NULL,
	`data_key` VARCHAR(45),
	`data_value` VARCHAR(264),
	`createTime` DATETIME,
    `updateTime` DATETIME,
    `deleted` TINYINT,
	PRIMARY KEY (`userId`, `data_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
