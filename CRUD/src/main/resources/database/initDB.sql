CREATE SCHEMA IF NOT EXISTS `TODOlist` DEFAULT CHARACTER SET utf8 ;
USE `TODOlist` ;

-- -----------------------------------------------------
-- Table `TODOlist`.`TODO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TODOlist`.`TODO` ;

CREATE TABLE IF NOT EXISTS `TODOlist`.`TODO` (
  `idTODO` INT NOT NULL AUTO_INCREMENT,
  `nameTODO` VARCHAR(120) NOT NULL,
  `isCompleted` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idTODO`);

