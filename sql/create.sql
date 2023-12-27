DROP TABLE IF EXISTS tb_user;
CREATE TABLE tb_user
(
    id                 VARCHAR(64) PRIMARY KEY COMMENT 'id',
    username           VARCHAR(64)  NOT NULL UNIQUE COMMENT '用户名',
    password           VARCHAR(255) NOT NULL COMMENT '密码',
    name               VARCHAR(64)  NOT NULL COMMENT '用户姓名',
    gender             VARCHAR(64) COMMENT '性别',
    phone              VARCHAR(64) COMMENT '手机',
    enabled            BOOLEAN      NOT NULL COMMENT '是否启用',
    created_by         VARCHAR(64) COMMENT '创建人',
    created_date       DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    last_modified_by   VARCHAR(64) COMMENT '修改人',
    last_modified_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) COMMENT '用户表';

INSERT INTO db_template.tb_user (id, username, password, name, gender, phone, enabled)
VALUES ('1', 'admin', '{bcrypt}$2a$10$5RtO03DBtcpCeNJ7AwZzXOQKcVWgqoVZV5b8Rt68mlqvONe3U0SZK', '超级管理员', '', null,
        true);

DROP TABLE IF EXISTS tb_menu;
CREATE TABLE tb_menu
(
    id                 VARCHAR(64) PRIMARY KEY COMMENT 'id',
    name               VARCHAR(64) NOT NULL UNIQUE COMMENT '菜单名称',
    parent_id          VARCHAR(64) COMMENT '父id',
    path               VARCHAR(64) COMMENT '菜单路由',
    type               ENUM ('DIRECTORY', 'MENU', 'BUTTON') NOT NULL COMMENT '类型(目录，菜单，按钮)',
    icon               VARCHAR(64) COMMENT '图标名称',
    permission_tag     VARCHAR(64) COMMENT '权限标识',
    order_num          INT      DEFAULT 100 COMMENT '排序',
    enabled            BOOLEAN     NOT NULL COMMENT '是否启用',
    created_by         VARCHAR(64) COMMENT '创建人',
    created_date       DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    last_modified_by   VARCHAR(64) COMMENT '修改人',
    last_modified_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) COMMENT '菜单表';
