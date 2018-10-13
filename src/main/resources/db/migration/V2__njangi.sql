-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema njangi
-- -----------------------------------------------------
-- Our database for the njangi app

-- -----------------------------------------------------
-- Schema njangi
--
-- Our database for the njangi app
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `njangi` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema pressing
-- -----------------------------------------------------
USE `njangi` ;

-- -----------------------------------------------------
-- Table `njangi`.`usersys`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `njangi`.`usersys` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `username` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `phone_number` VARCHAR(2555) NOT NULL,
  `is_active` BIT(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `njangi`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `njangi`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `njangi`.`permission`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `njangi`.`permission` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `njangi`.`njangi_account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `njangi`.`njangi_account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `amount` DOUBLE NOT NULL,
  `account_number` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `njangi`.`member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `njangi`.`member` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `username` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `is_active` BIT(1) NOT NULL,
  `identifier` VARCHAR(255) NOT NULL,
  `phone_number` VARCHAR(255) NOT NULL,
  `occupation` VARCHAR(255) NOT NULL,
  `location` VARCHAR(255) NOT NULL,
  `date_of_birth` DATETIME NOT NULL,
  `account_balance` DOUBLE NOT NULL,
  `picture` BIGINT(20) NOT NULL,
  `account_number` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `njangi`.`transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `njangi`.`transaction` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(255) NOT NULL,
  `date` DATE NOT NULL,
  `amount` DOUBLE NOT NULL,
  `note` VARCHAR(255) NOT NULL,
  `njangi_account_id` INT NOT NULL,
  `member_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_transaction_njangi_account1_idx` (`njangi_account_id` ASC),
  INDEX `fk_transaction_member1_idx` (`member_id` ASC),
  CONSTRAINT `fk_transaction_njangi_account1`
    FOREIGN KEY (`njangi_account_id`)
    REFERENCES `njangi`.`njangi_account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_transaction_member1`
    FOREIGN KEY (`member_id`)
    REFERENCES `njangi`.`member` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Registration and login Implementation in the Njangi app using spring boot security';


-- -----------------------------------------------------
-- Table `njangi`.`role_permission`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `njangi`.`role_permission` (
  `role_id` INT NOT NULL,
  `permission_id` INT NOT NULL,
  PRIMARY KEY (`role_id`, `permission_id`),
  INDEX `fk_role_has_permission_permission1_idx` (`permission_id` ASC),
  INDEX `fk_role_has_permission_role1_idx` (`role_id` ASC),
  CONSTRAINT `fk_role_has_permission_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `njangi`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_has_permission_permission1`
    FOREIGN KEY (`permission_id`)
    REFERENCES `njangi`.`permission` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `njangi`.`usersys_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `njangi`.`usersys_role` (
  `usersys_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`usersys_id`, `role_id`),
  INDEX `fk_user_has_role_role1_idx` (`role_id` ASC),
  INDEX `fk_user_has_role_user1_idx` (`usersys_id` ASC),
  CONSTRAINT `fk_user_has_role_user1`
    FOREIGN KEY (`usersys_id`)
    REFERENCES `njangi`.`usersys` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_role_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `njangi`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
