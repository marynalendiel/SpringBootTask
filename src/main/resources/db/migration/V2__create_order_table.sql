CREATE TABLE IF NOT EXISTS `user_order_db`.`order`
(
    `id`          BIGINT         NOT NULL AUTO_INCREMENT,
    `price`       DECIMAL(10, 2) NOT NULL,
    `create_time` TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `user_id`     BIGINT         NOT NULL,
    PRIMARY KEY (`id`, `user_id`),
    INDEX `fk_order_user_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_order_user`
        FOREIGN KEY (`user_id`)
            REFERENCES `user_order_db`.`user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);