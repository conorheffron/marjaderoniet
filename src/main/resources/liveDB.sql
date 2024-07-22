CREATE SCHEMA `marjaderoniet`;

CREATE TABLE `marjaderoniet`.`contact` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `message` VARCHAR(255) NOT NULL,
  `created` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `recipient` VARCHAR(255) NOT NULL);
