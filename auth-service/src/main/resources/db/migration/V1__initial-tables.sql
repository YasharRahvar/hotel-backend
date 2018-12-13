/* create table user */
CREATE TABLE `user` (`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `email`  VARCHAR(255),
  `password`  VARCHAR(255),
  `created_on` DATETIME,
  `updated_on` DATETIME,
  `deleted_on` DATETIME,
  PRIMARY KEY (`id`));
