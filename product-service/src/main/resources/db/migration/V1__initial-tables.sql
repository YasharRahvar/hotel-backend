/* create table product */
CREATE TABLE `product` (`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `hotel_id` BIGINT(20) NOT NULL,
  `name` VARCHAR (250),
  `description` VARCHAR (250),
  `created_on` DATETIME,
  `updated_on` DATETIME,
  `deleted_on` DATETIME,
  PRIMARY KEY (`id`));

