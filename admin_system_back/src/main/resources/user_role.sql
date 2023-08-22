-- CREATE TABLE T_USER_ROLE (
--                         ID BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT 'ID',
--                         USER_ID BIGINT(20) NOT NULL COMMENT '用户ID',
--                         ROLE_ID BIGINT(20) NOT NULL COMMENT '角色ID',
--                         PRIMARY KEY (ID)
-- );

CREATE TABLE T_USER_ROLE (
                                 `id` bigint(200) NOT NULL AUTO_INCREMENT COMMENT 'id',
                                 `user_id` bigint(200) NOT NULL DEFAULT '0' COMMENT '用户id',
                                 `role_id` bigint(200) NOT NULL DEFAULT '0' COMMENT '角色id',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
