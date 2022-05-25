-- MySQL Script generated by MySQL Workbench
-- Sat May 21 22:06:14 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema oasip_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema oasip_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `oasip_db` DEFAULT CHARACTER SET utf8mb4 ;
USE `oasip_db` ;

-- -----------------------------------------------------
-- Table `oasip_db`.`EventCategory`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oasip_db`.`EventCategory` (
  `CategoryID` INT NOT NULL,
  `CategoryName` VARCHAR(110) NOT NULL,
  `Description` VARCHAR(510) NULL,
  `Duration` INT NOT NULL,
  PRIMARY KEY (`CategoryID`),
  UNIQUE INDEX `CategoryName_UNIQUE` (`CategoryName` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oasip_db`.`EventBooking`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oasip_db`.`EventBooking` (
  `BookingID` INT NOT NULL AUTO_INCREMENT,
  `BookingName` VARCHAR(110) NOT NULL,
  `BookingEmail` VARCHAR(110) NOT NULL,
  `Category` INT NOT NULL,
  `StartTime` DATETIME NOT NULL,
  `BookingDuration` INT NOT NULL,
  `EventNote` VARCHAR(510) NULL,
  INDEX `fk_EventBooking_EventCategory_idx` (`Category` ASC) VISIBLE,
  PRIMARY KEY (`BookingID`),
  CONSTRAINT `fk_EventBooking_EventCategory`
    FOREIGN KEY (`Category`)
    REFERENCES `oasip_db`.`EventCategory` (`CategoryID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

insert into `EventCategory` (`CategoryID`,`CategoryName`,`Description`,`Duration`) values 
(1, 'Project Management Clinic','ตารางนัดหมายนี้ใช้สำหรับนัดหมาย project management clinic ในวิชา INT221 integrated project I ให้นักศึกษาเตรียมเอกสารที่เกี่ยวข้องเพื่อแสดง EventBooking ระหว่างขอคำปรึกษา', 30 ),
(2,'DevOps/Infra Clinic','Use this event category for DevOps/Infra clinic.',20),
(3,'Database Clinic','ตารางนัดหมายนี้ใช้สำหรับนัดหมาย database clinic ในวิชา INT221 integrated project I',15),
(4,'Client-side Clinic','ตารางนัดหมายนี้ใช้สำหรับนัดหมาย client-side clinic ในวิชา INT221 integrated project',30),
(5,'Server-side Clinic','',30);

insert into `EventBooking` (`BookingID`,`BookingName`,`BookingEmail`,`Category`,`StartTime`,`BookingDuration`,`EventNote`) values
insert into `EventBooking` (`BookingID`,`BookingName`,`BookingEmail`,`Category`,`StartTime`,`BookingDuration`,`EventNote`) values
(1,'Somchai Jaidee (OR-7)','somchai.jai@mail.kmutt.ac.th',2,'2022-05-23 13:00',30,''),
(2,'Somsri Rakdee (SJ-3)','somsri.rak@mail.kmutt.ac.th',1,'2022-04-27 09:30',30,' ขอปรึกษาเรื่องเพื่อนไม่ช่วยงาน '),
(3,'สมเกียรติ ขยันเรียน กลุ่ม TT-4','somkiat.kay@kmutt.ac.th',3,'2022-05-23 16:30',15,'');