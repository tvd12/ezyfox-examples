#EzyFox Demo

#Requirements

**1. [Java 8] (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)** <br/>
**2. [Eclipse] (https://eclipse.org/downloads/) (support [Maven](https://maven.apache.org/))**<br/>
**3. [Lombok] (https://projectlombok.org/)**<br/>
**4. [MySQL] (https://www.mysql.com/)**<br/>
**5. [SmartFox] (http://www.smartfoxserver.com/)**

#Setup

**1. Import projects to eclipse**

Select: `File -> Import -> Existing Maven Projects -> <Select Maven Pom File> -> Finish`

**2. Create MySQL Table**

- Create a database
- Create table `video_poker_user`, example:

```sql
CREATE TABLE `video_poker_user` (
  `id` int(11) NOT NULL,
  `username` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `money` bigint(10) DEFAULT '200000',
  `last_login_ip` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  `last_logout` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `UK_37akto6lql6u9b3jx9en09u8g` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
```

**3. Configure**

In `ezyfox-videopoker-server-example` project:
- Open file `src/main/resources/videopoker/hibernate_mysql.properties` and modify three lines (if need):

```
# modify database name (test)
hibernate.connection.url=jdbc:mysql://localhost/test?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC

# modify db's user name
hibernate.connection.username=root

# modify db's user password
hibernate.connection.password=123
```
- Open file `pom.xml` and modify `<deploy.path>/Applications/SmartFoxServer_2X/SFS2X/extensions</deploy.path>` with your smarfox `extensions` directory path

#Run

- With `ezyfox-videopoker-server-example` project:

Run maven with goals: `clean assembly:directory install`

- Start/Restart smartfox server

- With `ezyfox-videopoker-client-example` project:

Run file `EzyFoxVideoPokerClientApp.java` with java application

