-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema cdi_rest
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `cdi_rest` ;

-- -----------------------------------------------------
-- Schema cdi_rest
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cdi_rest` DEFAULT CHARACTER SET utf8 ;
USE `cdi_rest` ;

-- -----------------------------------------------------
-- Table `cdi_rest`.`tb_product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cdi_rest`.`tb_product` ;

CREATE TABLE IF NOT EXISTS `cdi_rest`.`tb_product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `description` VARCHAR(100) NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
