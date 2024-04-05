DROP table `marjaderoniet`.`contact`;

CREATE TABLE `marjaderoniet`.`contact` (
  `id` INT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `message` VARCHAR(255) NOT NULL,
  `created` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `recipient` VARCHAR(255) NOT NULL,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci
COMMENT = 'test contact table';

INSERT INTO `marjaderoniet`.`contact` (`id`, `first_name`, `last_name`, `email`, `message`, `created`, `recipient`) VALUES ('1', 'Conor', 'Heffron', 'conor.heffron@hotmail.com', 'hey', '2017-01-19 00:00:00', 'conor.heffron@hotmail.com');
INSERT INTO `marjaderoniet`.`contact` (`id`, `first_name`, `last_name`, `email`, `message`, `created`, `recipient`) VALUES ('2', 'Conor', 'Heffron', 'conor.heffron@gmail.com', 'hey', CURRENT_TIMESTAMP, 'conor.heffron@hotmail.com');


INSERT INTO `marjaderoniet`.`contact` (`id`, `first_name`, `last_name`, `email`, `message`, `recipient`) VALUES ('3', 'test', 'mctester', 'test@test', 'hey', 'conor.heffron@hotmail.com');
