/* create table product */
CREATE TABLE `purchase` (`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) NOT NULL,
  `product_id` BIGINT(20) NOT NULL,
  `created_on` DATETIME,
  `updated_on` DATETIME,
  `deleted_on` DATETIME,
  PRIMARY KEY (`id`));

