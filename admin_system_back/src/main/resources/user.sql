
-- ----------------------------
-- Table structure for T_USER
-- ----------------------------
-- CREATE TABLE T_USER (
--                         ID BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT 'ID',
--                         USERNAME VARCHAR(40) NOT NULL COMMENT '用户名',
--                         PASSWORD VARCHAR(128) NOT NULL COMMENT '密码',
--                         EMAIL VARCHAR(40) NOT NULL COMMENT '邮箱',
--                         CREATE_TIME DATETIME NULL COMMENT '创建时间',
--                         STATUS CHAR(1) NOT NULL COMMENT '是否有效 1：有效  0：锁定',
--                         PRIMARY KEY (ID)
-- );
CREATE TABLE T_USER (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                            `user_name` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '用户名',
                            `nick_name` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '昵称',
                            `password` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '密码',
                            `status` char(1) DEFAULT '0' COMMENT '账号状态（0正常 1停用）',
                            `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
                            `phonenumber` varchar(32) DEFAULT NULL COMMENT '手机号',
                            `sex` char(1) DEFAULT NULL COMMENT '用户性别（0男，1女，2未知）',
                            `avatar` varchar(128) DEFAULT NULL COMMENT '头像',
                            `user_type` char(1) NOT NULL DEFAULT '1' COMMENT '用户类型（0管理员，1普通用户）',
                            `create_by` bigint(20) DEFAULT NULL COMMENT '创建人的用户id',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
                            `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                            `del_flag` int(11) DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
-- ----------------------------
-- Records of T_USER
-- ----------------------------
INSERT INTO T_USER VALUES (UUID(), 'ivan120714', '120714', 'maoyifan714@gmail.com',STR_TO_DATE('2023-06-22 17:20:21', '%Y-%m-%d %H:%i:%s'), '1');
INSERT INTO T_USER VALUES (1, 'ivan', '$2a$10$L/t4D1p5BcN2LPbQID/Bluyqx5iUxkk/dw5HMe93K7dgo1ASqizFy', 'maoyifan@gmail.com',STR_TO_DATE('2023-06-24 17:20:21', '%Y-%m-%d %H:%i:%s'), '1');