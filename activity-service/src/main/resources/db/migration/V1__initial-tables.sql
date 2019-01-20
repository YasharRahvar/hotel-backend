/* create table user */
CREATE TABLE `activity` (`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20),
  `name` VARCHAR (250),
  `description` VARCHAR (250),
  `created_on` DATETIME,
  `updated_on` DATETIME,
  `deleted_on` DATETIME,
  PRIMARY KEY (`id`));

