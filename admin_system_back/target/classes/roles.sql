# CREATE TABLE T_ROLE (
#                         ID BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT 'ID',
#                         ROLE_NAME VARCHAR(40) NOT NULL COMMENT '角色名称',
#                         ROLE_CODE VARCHAR(128) NOT NULL COMMENT '角色代码',
#                         CREATE_TIME DATETIME NOT NULL COMMENT '创建时间',
#                         UPDATE_TIME DATETIME NOT NULL COMMENT '更新时间',
#                         REMARK VARCHAR(500) NOT NULL COMMENT '备注',
#                         PRIMARY KEY (ID)
# );

CREATE TABLE T_ROLE (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `name` varchar(128) DEFAULT NULL,
                            `role_key` varchar(100) DEFAULT NULL COMMENT '角色权限字符串',
                            `status` char(1) DEFAULT '0' COMMENT '角色状态（0正常 1停用）',
                            `del_flag` int(1) DEFAULT '0' COMMENT 'del_flag',
                            `create_by` bigint(200) DEFAULT NULL,
                            `create_time` datetime DEFAULT NULL,
                            `update_by` bigint(200) DEFAULT NULL,
                            `update_time` datetime DEFAULT NULL,
                            `remark` varchar(500) DEFAULT NULL COMMENT '备注',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

INSERT INTO T_ROLE VALUES ()