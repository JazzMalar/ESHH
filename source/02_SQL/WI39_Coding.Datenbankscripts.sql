-- MySQL Script generated by MySQL Workbench
-- 12/11/16 13:41:58
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Device`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Device` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Device` (
  `idDevice` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `stringId` VARCHAR(45) NULL,
  `gpio` TINYINT(1) NULL,
  PRIMARY KEY (`idDevice`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`DeviceAction_WS2801_01`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`DeviceAction_WS2801_01` ;

CREATE TABLE IF NOT EXISTS `mydb`.`DeviceAction_WS2801_01` (
  `idDeviceAction` INT NOT NULL AUTO_INCREMENT,
  `LuxStart` INT NULL,
  `LuxEnd` INT NULL,
  `ColorStart` INT NULL,
  `ColorEnd` INT NULL,
  `NumLeds` INT NULL,
  PRIMARY KEY (`idDeviceAction`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`DeviceAction_LIFX_01`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`DeviceAction_LIFX_01` ;

CREATE TABLE IF NOT EXISTS `mydb`.`DeviceAction_LIFX_01` (
  `idDeviceAction` INT NOT NULL AUTO_INCREMENT,
  `lifxId` VARCHAR(45) NULL,
  `lightIntesity` INT NULL,
  PRIMARY KEY (`idDeviceAction`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ActionGroupMember`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`ActionGroupMember` ;

CREATE TABLE IF NOT EXISTS `mydb`.`ActionGroupMember` (
  `idActionGroupMember` INT NOT NULL AUTO_INCREMENT,
  `idGroup` INT NULL,
  `idDevice` INT NULL,
  `idAction` INT NULL,
  `Offset` INT NULL,
  PRIMARY KEY (`idActionGroupMember`),
  INDEX `idDevice_idx` (`idDevice` ASC),
  INDEX `idDeviceAction_idx` (`idAction` ASC),
  CONSTRAINT `idDevice`
    FOREIGN KEY (`idDevice`)
    REFERENCES `mydb`.`Device` (`idDevice`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idDeviceAction`
    FOREIGN KEY (`idAction`)
    REFERENCES `mydb`.`DeviceAction_WS2801_01` (`idDeviceAction`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idDeviceAction2`
    FOREIGN KEY (`idAction`)
    REFERENCES `mydb`.`DeviceAction_LIFX_01` (`idDeviceAction`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Alarm`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Alarm` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Alarm` (
  `idTask` INT NOT NULL AUTO_INCREMENT,
  `startTime` TIME NULL,
  `repeatPattern` VARCHAR(45) NULL,
  `active` TINYINT(1) NULL,
  `idActionGroup` INT NULL,
  PRIMARY KEY (`idTask`),
  INDEX `idActionGroupMember_idx` (`idActionGroup` ASC),
  CONSTRAINT `idActionGroupMember`
    FOREIGN KEY (`idActionGroup`)
    REFERENCES `mydb`.`ActionGroupMember` (`idActionGroupMember`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
GRANT USAGE ON *.* TO wakeuplight;
 DROP USER wakeuplight;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'wakeuplight' IDENTIFIED BY 'wakeuplight';

GRANT ALL ON `mydb`.* TO 'wakeuplight';
GRANT SELECT ON TABLE `mydb`.* TO 'wakeuplight';
GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `mydb`.* TO 'wakeuplight';
GRANT SELECT, INSERT, TRIGGER ON TABLE `mydb`.* TO 'wakeuplight';
GRANT EXECUTE ON ROUTINE `mydb`.* TO 'wakeuplight';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `mydb`.`Device`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Device` (`idDevice`, `name`, `stringId`, `gpio`) VALUES (1, 'LEDStrip', 'WS2801_01', 1);
INSERT INTO `mydb`.`Device` (`idDevice`, `name`, `stringId`, `gpio`) VALUES (2, 'Lifx Birne', 'LIFX_01', 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`DeviceAction_WS2801_01`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`DeviceAction_WS2801_01` (`idDeviceAction`, `LuxStart`, `LuxEnd`, `ColorStart`, `ColorEnd`, `NumLeds`) VALUES (1, 0, 254, 000, 255255255, 25);
INSERT INTO `mydb`.`DeviceAction_WS2801_01` (`idDeviceAction`, `LuxStart`, `LuxEnd`, `ColorStart`, `ColorEnd`, `NumLeds`) VALUES (2, 50, 120, 175175175, 150150084, 25);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`DeviceAction_LIFX_01`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`DeviceAction_LIFX_01` (`idDeviceAction`, `lifxId`, `lightIntesity`) VALUES (1, 'a223d', 254);
INSERT INTO `mydb`.`DeviceAction_LIFX_01` (`idDeviceAction`, `lifxId`, `lightIntesity`) VALUES (2, 'aofo', 10);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`ActionGroupMember`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`ActionGroupMember` (`idActionGroupMember`, `idGroup`, `idDevice`, `idAction`, `Offset`) VALUES (1, 1, 1, 1, 0);
INSERT INTO `mydb`.`ActionGroupMember` (`idActionGroupMember`, `idGroup`, `idDevice`, `idAction`, `Offset`) VALUES (2, 1, 2, 2, 10);
INSERT INTO `mydb`.`ActionGroupMember` (`idActionGroupMember`, `idGroup`, `idDevice`, `idAction`, `Offset`) VALUES (3, 2, 1, 2, 20);
INSERT INTO `mydb`.`ActionGroupMember` (`idActionGroupMember`, `idGroup`, `idDevice`, `idAction`, `Offset`) VALUES (4, 2, 2, 1, 10);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`Alarm`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Alarm` (`idTask`, `startTime`, `repeatPattern`, `active`, `idActionGroup`) VALUES (1, '08:00:00', '1111100', 1, 1);
INSERT INTO `mydb`.`Alarm` (`idTask`, `startTime`, `repeatPattern`, `active`, `idActionGroup`) VALUES (2, '10:00:00', '0000011', 1, 2);

COMMIT;

