CREATE SCHEMA `shop` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `shop`.`product_table` (
  `product_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(225) NOT NULL,
  `price` DOUBLE NOT NULL,
  `deleted` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`product_id`));
