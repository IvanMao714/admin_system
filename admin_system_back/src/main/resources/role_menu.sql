CREATE TABLE T_ROLE_MENU (
                                 `id` bigint(200) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                 `role_id` bigint(200) NOT NULL DEFAULT '0' COMMENT '角色ID',
                                 `menu_id` bigint(200) NOT NULL DEFAULT '0' COMMENT '菜单id',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;