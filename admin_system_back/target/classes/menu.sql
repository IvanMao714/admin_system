CREATE TABLE T_MENU (
                        ID BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT 'ID',
                        MENU_NAME VARCHAR(40) NOT NULL COMMENT '菜单名称',
                        MENU_ICON VARCHAR(100) DEFAULT '#' NOT NULL COMMENT '菜单图标',
                        PARENT_ID BIGINT(20)  DEFAULT NULL COMMENT '父菜单ID',
                        ORDER_NUM INT(11)  DEFAULT '0' COMMENT '显示顺序',
                        ROUTE_PATH VARCHAR(200) DEFAULT '' COMMENT '路由地址',
                        COMPONENT VARCHAR(255) DEFAULT NULL COMMENT '组件地址',
                        MENU_TYPE CHAR(1) DEFAULT '' COMMENT '菜单类型（M菜单，B按钮）',
                        PRAMS VARCHAR(100) NOT NULL COMMENT '权限标识',
                        CREATE_TIME DATETIME NOT NULL COMMENT '创建时间',
                        UPDATE_TIME DATETIME NOT NULL COMMENT '更新时间',
                        REMARK VARCHAR(500) NOT NULL COMMENT '备注',
                        PRIMARY KEY (ID)
);

CREATE TABLE T_MENU (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `menu_name` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '菜单名',
                            `route_path` varchar(200) DEFAULT NULL COMMENT '路由地址',
                            `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
                            `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID',
                            `order_num` int(11) DEFAULT '0' COMMENT '显示顺序',
                            `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
                            `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
                            `status` char(1) DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
                            `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
                            `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
                            `create_by` bigint(20) DEFAULT NULL,
                            `create_time` datetime DEFAULT NULL,
                            `update_by` bigint(20) DEFAULT NULL,
                            `update_time` datetime DEFAULT NULL,
                            `del_flag` int(11) DEFAULT '0' COMMENT '是否删除（0未删除 1已删除）',
                            `remark` varchar(500) DEFAULT NULL COMMENT '备注',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';