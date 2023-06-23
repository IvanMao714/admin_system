
-- ----------------------------
-- Table structure for T_USER
-- ----------------------------
CREATE TABLE T_USER (
                        ID INT NOT NULL COMMENT 'ID',
                        USERNAME VARCHAR(40) NOT NULL COMMENT '用户名',
                        PASSWD VARCHAR(128) NOT NULL COMMENT '密码',
                        EMAIL VARCHAR(40) NOT NULL COMMENT '邮箱',
                        CREATE_TIME DATETIME NULL COMMENT '创建时间',
                        STATUS CHAR(1) NOT NULL COMMENT '是否有效 1：有效  0：锁定',
                        PRIMARY KEY (ID)
);
-- ----------------------------
-- Records of T_USER
-- ----------------------------
INSERT INTO T_USER VALUES ('1', 'ivan120714', '120714', 'maoyifan714@gmail.com',STR_TO_DATE('2023-06-22 17:20:21', '%Y-%m-%d %H:%i:%s'), '1');
