/*
SQLyog Community v13.3.0 (64 bit)
MySQL - 10.4.32-MariaDB : Database - projektnidogadjaji
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`projektnidogadjaji` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `projektnidogadjaji`;

/*Table structure for table `angazman` */

DROP TABLE IF EXISTS `angazman`;

CREATE TABLE `angazman` (
  `dogadjajId` int(50) NOT NULL,
  `izvodjacId` int(50) NOT NULL,
  `trajanjeNastupa` int(50) NOT NULL,
  PRIMARY KEY (`dogadjajId`,`izvodjacId`),
  KEY `angazman_ibfk_2` (`izvodjacId`),
  CONSTRAINT `angazman_ibfk_1` FOREIGN KEY (`dogadjajId`) REFERENCES `dogadjaj` (`dogadjajId`) ON UPDATE CASCADE,
  CONSTRAINT `angazman_ibfk_2` FOREIGN KEY (`izvodjacId`) REFERENCES `izvodjac` (`izvodjacID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `angazman` */

insert  into `angazman`(`dogadjajId`,`izvodjacId`,`trajanjeNastupa`) values 
(1,1,50),
(1,2,50),
(1,3,30),
(2,4,60),
(2,5,100),
(2,6,20),
(12,1,50),
(12,3,50),
(15,1,50),
(15,4,40),
(16,1,50),
(17,1,560),
(33,1,50),
(34,1,1),
(35,1,1),
(36,1,12),
(37,4,90),
(37,7,50),
(37,8,40);

/*Table structure for table `dogadjaj` */

DROP TABLE IF EXISTS `dogadjaj`;

CREATE TABLE `dogadjaj` (
  `dogadjajId` int(10) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(100) NOT NULL,
  `datumVreme` datetime NOT NULL,
  `vrstaDogadjaja` enum('KUCNA_ZURKA','KLUB_ZURKA','MATURSKO_VECE','TEMATSKA_ZURKA','KORPORATIVNA_ZURKA','OPEN_AIR_ZURKA','KONCERT_ZURKA','FESTIVAL') NOT NULL,
  `lokacijaId` int(50) DEFAULT NULL,
  `korisnikId` int(50) DEFAULT NULL,
  PRIMARY KEY (`dogadjajId`),
  KEY `dogadjaj_ibfk_1` (`lokacijaId`),
  KEY `korisnikId` (`korisnikId`),
  CONSTRAINT `dogadjaj_ibfk_1` FOREIGN KEY (`lokacijaId`) REFERENCES `lokacija` (`lokacijaID`) ON UPDATE CASCADE,
  CONSTRAINT `dogadjaj_ibfk_2` FOREIGN KEY (`korisnikId`) REFERENCES `korisnik` (`korisnikId`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `dogadjaj` */

insert  into `dogadjaj`(`dogadjajId`,`naziv`,`datumVreme`,`vrstaDogadjaja`,`lokacijaId`,`korisnikId`) values 
(1,'Žurka kod Micka','2024-01-20 00:00:00','KUCNA_ZURKA',1,1),
(2,'Klubska žurka Beograd','2024-02-10 00:00:00','KLUB_ZURKA',2,1),
(3,'Maturantsko veče','2024-06-05 00:00:00','MATURSKO_VECE',5,1),
(4,'Tematska žurka 80-e','2024-03-15 00:00:00','TEMATSKA_ZURKA',6,1),
(5,'Korporativna žurka','2024-12-20 00:00:00','KORPORATIVNA_ZURKA',7,1),
(6,'Open Air Party','2024-07-25 00:00:00','OPEN_AIR_ZURKA',8,1),
(7,'Koncert - Rock Night','2024-05-18 00:00:00','KONCERT_ZURKA',9,1),
(8,'Letnji festival','2024-08-12 00:00:00','FESTIVAL',10,2),
(9,'Micko Zurka','2025-10-20 21:59:24','KUCNA_ZURKA',1,1),
(12,'test1','2025-11-09 20:22:00','KUCNA_ZURKA',1,1),
(15,'test4','2025-11-09 20:22:00','KUCNA_ZURKA',1,1),
(16,'tes5','2025-11-09 20:22:00','KUCNA_ZURKA',1,1),
(17,'test6','2025-11-09 20:22:00','KUCNA_ZURKA',1,1),
(33,'test10','2025-11-09 20:22:00','KUCNA_ZURKA',1,1),
(34,'1','2025-11-09 20:22:00','KUCNA_ZURKA',1,2),
(35,'1','2025-11-09 20:22:00','KUCNA_ZURKA',1,2),
(36,'12','2025-11-09 20:22:00','KUCNA_ZURKA',1,1),
(37,'micko zurka','2025-11-09 20:22:00','KUCNA_ZURKA',18,1);

/*Table structure for table `gost` */

DROP TABLE IF EXISTS `gost`;

CREATE TABLE `gost` (
  `gostId` int(20) NOT NULL AUTO_INCREMENT,
  `ime` varchar(20) NOT NULL,
  `prezime` varchar(20) NOT NULL,
  `telefon` varchar(20) NOT NULL,
  PRIMARY KEY (`gostId`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `gost` */

insert  into `gost`(`gostId`,`ime`,`prezime`,`telefon`) values 
(3,'a123','asd','123'),
(9,'armani','a','123'),
(10,'hasan','a','123'),
(11,'ahmed','a','123'),
(12,'mickan','mitic','648617909'),
(14,'ar','ar','444'),
(15,'asd','asd','12'),
(16,'qwerqwre','qwer','1'),
(17,'gnjila','dimic','642001'),
(18,'asd','asd','123'),
(19,'123','123','0'),
(23,'asd','asd','asd'),
(24,'test1test1','test1','test1'),
(26,'12','12','12'),
(27,'Danica','Mitic','0648617909');

/*Table structure for table `izvodjac` */

DROP TABLE IF EXISTS `izvodjac`;

CREATE TABLE `izvodjac` (
  `izvodjacId` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(20) DEFAULT NULL,
  `zanr` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`izvodjacId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `izvodjac` */

insert  into `izvodjac`(`izvodjacId`,`ime`,`zanr`) values 
(1,'nigur','House'),
(2,'NIggor','House'),
(3,'NIggor','RnB'),
(4,'gavr','House'),
(5,'nmigr','House'),
(6,'nmigr','Rock'),
(7,'micko','Rock'),
(8,'test','House'),
(9,'test','House'),
(10,'test1Izmena','House');

/*Table structure for table `korisnik` */

DROP TABLE IF EXISTS `korisnik`;

CREATE TABLE `korisnik` (
  `korisnikId` int(50) NOT NULL AUTO_INCREMENT,
  `ime` varchar(50) DEFAULT NULL,
  `prezime` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `lozinka` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`korisnikId`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `korisnik` */

insert  into `korisnik`(`korisnikId`,`ime`,`prezime`,`email`,`lozinka`) values 
(1,'micko','micko','micko@gmail.com','efeb5d0a316a30a54de0c3b58496e7fbf53215412e9eb88af7e00091bf67880f'),
(2,'milan','milan','milan@gmail.com','88d39393475c2e4c4ce9257936710d5c2ef3b52ae056af3d95e607d7522b4edc'),
(8,'Na','Mi','nata@gmail.com','03a03258602811bdab0ac9538a40e5665684ebf5bd3db2c791f9efc1cfd49055');

/*Table structure for table `lokacija` */

DROP TABLE IF EXISTS `lokacija`;

CREATE TABLE `lokacija` (
  `lokacijaId` int(20) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(50) NOT NULL,
  `adresa` varchar(50) NOT NULL,
  `kapacitet` int(10) NOT NULL,
  KEY `id` (`lokacijaId`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `lokacija` */

insert  into `lokacija`(`lokacijaId`,`naziv`,`adresa`,`kapacitet`) values 
(1,'Micko ','Save Jovanovića 71',150),
(2,'Arena Hall','Bulevar Arsenija Čarnojevića 58',2000),
(5,'Hotel Metropol','Bulevar Kralja Aleksandra 69',700),
(6,'Klub Komitet','Beton ',600),
(7,'Dom ','Dečanska 1',1000),
(8,'KC Grad','Braće Krsmanović 4',400),
(9,'Skadarlija Pub','Skadarska 21',250),
(10,'Vinoteka Ba','Cara Dušana 59',1999),
(13,'mickova gajba','Save Jovanovica 71',70),
(15,'Hotel Metropol','Bulevar',1),
(16,'Vinoteka Ba','Cara Duš',150),
(17,'Vinoteka Bali','cara milana',150),
(18,'Mickova gajba','Save Jovanovica 71',55),
(21,'test','test',40),
(22,'test1test1','test1test1',100),
(23,'test1test1','test1',15),
(24,'12','12',12);

/*Table structure for table `potvrda` */

DROP TABLE IF EXISTS `potvrda`;

CREATE TABLE `potvrda` (
  `gostId` int(50) NOT NULL,
  `dogadjajId` int(50) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `datumVreme` datetime NOT NULL,
  PRIMARY KEY (`gostId`,`dogadjajId`),
  KEY `dogadjajId` (`dogadjajId`),
  CONSTRAINT `potvrda_ibfk_1` FOREIGN KEY (`gostId`) REFERENCES `gost` (`gostId`) ON UPDATE CASCADE,
  CONSTRAINT `potvrda_ibfk_2` FOREIGN KEY (`dogadjajId`) REFERENCES `dogadjaj` (`dogadjajId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `potvrda` */

insert  into `potvrda`(`gostId`,`dogadjajId`,`status`,`datumVreme`) values 
(3,1,1,'2025-10-21 18:45:59'),
(3,34,1,'2025-10-21 12:46:40'),
(3,35,1,'2025-10-21 12:57:51'),
(3,37,0,'2025-10-21 18:37:56'),
(9,1,1,'2025-10-21 18:45:59'),
(9,34,0,'2025-10-21 12:46:45'),
(9,35,1,'2025-10-21 12:57:51'),
(9,37,0,'2025-10-21 18:37:56'),
(10,34,0,'2025-10-21 12:46:45'),
(10,35,1,'2025-10-21 12:57:51'),
(10,37,0,'2025-10-21 18:37:56'),
(11,1,1,'2025-10-21 18:45:59'),
(11,34,0,'2025-10-21 12:46:45'),
(11,36,1,'2025-10-21 12:58:10'),
(11,37,0,'2025-10-21 18:37:56'),
(12,36,1,'2025-10-21 12:58:10'),
(12,37,0,'2025-10-21 18:37:56'),
(14,15,0,'2025-10-20 23:31:06'),
(14,36,1,'2025-10-21 12:58:10'),
(14,37,1,'2025-10-21 18:38:00'),
(15,37,1,'2025-10-21 18:38:01'),
(16,37,1,'2025-10-21 18:38:01'),
(17,37,1,'2025-10-21 18:38:01'),
(18,17,1,'2025-10-20 23:32:44'),
(18,37,0,'2025-10-21 18:37:56'),
(19,17,1,'2025-10-20 23:32:44'),
(19,37,0,'2025-10-21 18:37:56'),
(23,17,1,'2025-10-20 23:32:44'),
(23,37,0,'2025-10-21 18:37:56'),
(24,17,1,'2025-10-20 23:32:44'),
(24,37,0,'2025-10-21 18:37:56'),
(26,37,0,'2025-10-21 18:37:56');

/* Trigger structure for table `potvrda` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `proveri_dolazak` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `proveri_dolazak` BEFORE INSERT ON `potvrda` FOR EACH ROW 
BEGIN
    IF NEW.datumVreme > (
        SELECT datumVreme FROM dogadjaj WHERE dogadjajId = NEW.dogadjajId
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Greška: Nije moguće potvrditi dolazak nakon što je događaj već počeo!';
    END IF;
END */$$


DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
