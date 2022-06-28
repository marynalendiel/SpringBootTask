CREATE TABLE IF NOT EXISTS `user_order_db`.`user`
(
    `id`         BIGINT      NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(45) NOT NULL,
    `last_name`  VARCHAR(45) NOT NULL,
    `email`      VARCHAR(45) NOT NULL,
    `city`       VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE
);