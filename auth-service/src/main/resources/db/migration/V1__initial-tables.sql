/* create table user */
CREATE TABLE `user` (`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `email`  VARCHAR(255),
  `password`  VARCHAR(255),
  `username` VARCHAR (255),
  `first_name` VARCHAR (255),
  `last_name` VARCHAR (255),
  `title` VARCHAR (255),
  `role_id` BIGINT (20),
  `created_on` DATETIME,
  `updated_on` DATETIME,
  `deleted_on` DATETIME,
  PRIMARY KEY (`id`));
