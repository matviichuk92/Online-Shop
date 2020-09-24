CREATE SCHEMA `shop` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `shop`.`products` (
  `product_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(256) NOT NULL,
  `price` DOUBLE NOT NULL,
  `deleted` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`product_id`));

CREATE TABLE `shop`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(256) NOT NULL,
  `login` VARCHAR(256) NOT NULL,
  `password` VARCHAR(256) NOT NULL,
  `deleted` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE);

CREATE TABLE `shop`.`roles` (
  `role_id` BIGINT(11) NOT NULL,
  `role_name` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`role_id`));

CREATE TABLE `shop`.`users_roles` (
  `user_id` BIGINT(11) NOT NULL,
  `role_id` BIGINT(11) NOT NULL,
  INDEX `user_id_fk_idx` (`user_id` ASC) VISIBLE,
  INDEX `role_id_fk_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `user_id_fk_roles`
    FOREIGN KEY (`user_id`)
    REFERENCES `shop`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `role_id_fk`
    FOREIGN KEY (`role_id`)
    REFERENCES `shop`.`roles` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `shop`.`orders` (
  `order_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(11) NOT NULL,
  `deleted` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`order_id`),
  INDEX `user_id_fk_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `user_id_orders`
    FOREIGN KEY (`user_id`)
    REFERENCES `shop`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `shop`.`orders_products` (
  `order_id` BIGINT(11) NOT NULL,
  `product_id` BIGINT(11) NOT NULL,
  INDEX `order_id_fk_idx` (`order_id` ASC) VISIBLE,
  INDEX `product_id_fk_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `order_id_fk`
    FOREIGN KEY (`order_id`)
    REFERENCES `shop`.`orders` (`order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `product_id_fk`
    FOREIGN KEY (`product_id`)
    REFERENCES `shop`.`products` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `shop`.`shopping_cart` (
  `cart_id` BIGINT(11) NOT NULL,
  `user_id` BIGINT(11) NOT NULL,
  PRIMARY KEY (`cart_id`),
  INDEX `user_id_fk_cart_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `user_id_fk_cart`
    FOREIGN KEY (`user_id`)
    REFERENCES `shop`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `shop`.`shopping_carts_products` (
  `cart_id` BIGINT(11) NOT NULL,
  `product_id` BIGINT(11) NOT NULL,
  INDEX `cart_id_idx` (`cart_id` ASC) VISIBLE,
  INDEX `product_id_fk_cart_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `cart_id_fk`
    FOREIGN KEY (`cart_id`)
    REFERENCES `shop`.`shopping_cart` (`cart_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `product_id_fk_cart`
    FOREIGN KEY (`product_id`)
    REFERENCES `shop`.`products` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
