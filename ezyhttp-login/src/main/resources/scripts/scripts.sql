CREATE TABLE `access_token` (
  `accessToken` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
  `userId` bigint DEFAULT NULL,
  `firstIssueAt` datetime DEFAULT NULL,
  `expireAt` datetime DEFAULT NULL,
  `expireIn` datetime DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `deleted` tinyint DEFAULT NULL,
  PRIMARY KEY (`accessToken`),
  INDEX `index_user_id` (`userId`),
  INDEX `index_deleted` (`deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `user_data` (
  `userId` bigint NOT NULL,
  `data_key` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `data_value` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `deleted` tinyint DEFAULT NULL,
  PRIMARY KEY (`userId`,`data_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `lastName` varchar(120) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `gender` int DEFAULT NULL,
  `avatarURL` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `birthOfDate` date DEFAULT NULL,
  `accountType` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `fullName` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `firstName` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(120) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `thirdPartyId` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `deleted` tinyint DEFAULT NULL,
  `status` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `index_email` (`email`),
  INDEX `index_account_type` (`accountType`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
