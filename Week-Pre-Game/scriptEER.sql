-- MySQL Script generated by MySQL Workbench
-- Fri Apr 22 17:43:52 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`EventCategory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`EventCategory` ;

CREATE TABLE IF NOT EXISTS `mydb`.`EventCategory` (
  `CategoryID` INT NOT NULL,
  `categoryName` VARCHAR(45) NOT NULL,
  `description` VARCHAR(255) NULL,
  `duration` INT NOT NULL,
  PRIMARY KEY (`CategoryID`),
  UNIQUE INDEX `categoryName_UNIQUE` (`categoryName` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Booking`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Booking` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Booking` (
  `bookingId` INT NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`bookingId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`event` ;

CREATE TABLE IF NOT EXISTS `mydb`.`event` (
  `eventId` INT NOT NULL,
  `bookingId` INT NOT NULL,
  `CategoryID` INT NOT NULL,
  `startTime` DATETIME NOT NULL,
  `eventNote` VARCHAR(255) NULL,
  PRIMARY KEY (`eventId`, `bookingId`, `CategoryID`),
  INDEX `fk_event_EventCategory_idx` (`CategoryID` ASC) VISIBLE,
  INDEX `fk_event_Booking1_idx` (`bookingId` ASC) VISIBLE,
  CONSTRAINT `fk_event_EventCategory`
    FOREIGN KEY (`CategoryID`)
    REFERENCES `mydb`.`EventCategory` (`CategoryID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_Booking1`
    FOREIGN KEY (`bookingId`)
    REFERENCES `mydb`.`Booking` (`bookingId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

insert into `booking` (`bookingId`,`name`,`email`) values 
(65000001, 'Paramet','paramt.55@gmail.com' ),
(65000002, 'Michel','michel.ez@gmail.com' ),
(65000003, 'John','johnwick.007@gmail.com' ),
(65000004, 'Alice','alice.inwonderland@gmail.com' );

insert into `eventcategory` (`CategoryID`,`categoryName`,`description`,`duration`) values 
(10001, 'Back-End','Back-End clinic', 90 ),
(10002, 'Database','Database clinic',60 ),
(10003, 'Front-End','Front-End clinic', 120 ),
(10004, 'Project-Management','Project-Management',60 ),
(10005, 'DevOps-Infra','DevOps-Infra clinic',60 ),
(10006, 'Business-Requirement','Business-Requirement clinic', 90 );

insert into `event` (`eventId`,`bookingId`,`CategoryID`,`startTime`,`eventNote`) values 
(1, 65000001,10001,'2022-04-1','Working homework' ),
(2, 65000002,10002,'2022-04-7' ,'Meeting about study'),
(3, 65000003,10003,'2022-04-14',null ),
(4, 65000004,10004,'2022-04-15','Database about Travel in Thailand' );

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
