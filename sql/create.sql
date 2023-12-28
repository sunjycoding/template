DROP TABLE IF EXISTS tb_system_user;
CREATE TABLE tb_system_user
(
    id                 VARCHAR(64) PRIMARY KEY COMMENT 'id',
    username           VARCHAR(64)  NOT NULL UNIQUE COMMENT '用户名',
    password           VARCHAR(255) NOT NULL COMMENT '密码',
    name               VARCHAR(64)  NOT NULL COMMENT '用户姓名',
    gender             VARCHAR(64) COMMENT '性别',
    phone              VARCHAR(64) COMMENT '手机',
    enabled            BOOLEAN      NOT NULL COMMENT '启用状态',
    created_by         VARCHAR(64) COMMENT '创建人',
    created_date       DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    last_modified_by   VARCHAR(64) COMMENT '修改人',
    last_modified_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) COMMENT '用户表';

INSERT INTO db_template.tb_system_user (id, username, password, name, gender, phone, enabled)
VALUES ('1', 'admin', '{bcrypt}$2a$10$5RtO03DBtcpCeNJ7AwZzXOQKcVWgqoVZV5b8Rt68mlqvONe3U0SZK', '超级管理员', '', null,
        true);

DROP TABLE IF EXISTS tb_system_menu;
CREATE TABLE tb_system_menu
(
    id                 VARCHAR(64) PRIMARY KEY COMMENT 'id',
    name               VARCHAR(64) NOT NULL COMMENT '名称',
    type               ENUM ('DIRECTORY', 'MENU', 'BUTTON') NOT NULL COMMENT '类型(目录，菜单，按钮)',
    parent_id          VARCHAR(64) COMMENT '父id',
    path               VARCHAR(64) COMMENT '菜单路由',
    icon               VARCHAR(64) COMMENT '图标名称',
    permission_tag     VARCHAR(64) COMMENT '权限标识',
    order_num          INT      DEFAULT 100 COMMENT '排序',
    enabled            BOOLEAN     NOT NULL COMMENT '启用状态',
    created_by         VARCHAR(64) COMMENT '创建人',
    created_date       DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    last_modified_by   VARCHAR(64) COMMENT '修改人',
    last_modified_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) COMMENT '菜单表';

INSERT INTO db_template.tb_system_menu (id, name, type, parent_id, path, icon, permission_tag, order_num, enabled)
VALUES ('ac47a48d8721421980f46195a9f889b5', '系统管理', 'DIRECTORY', null, null, 'setting', null, 0, 1);
INSERT INTO db_template.tb_system_menu (id, name, type, parent_id, path, icon, permission_tag, order_num, enabled)
VALUES ('1f0f0f1abf6b486bab39095625049194', '用户管理', 'MENU', 'ac47a48d8721421980f46195a9f889b5', '/system/users',
        'user', 'use:list', 0, 1);
INSERT INTO db_template.tb_system_menu (id, name, type, parent_id, path, icon, permission_tag, order_num, enabled)
VALUES ('7f82acb78d6843c5acf01138e1781ad2', '字典管理', 'MENU', 'ac47a48d8721421980f46195a9f889b5', '/system/dicts',
        'data-analysis', 'dict:list', 1, 1);
INSERT INTO db_template.tb_system_menu (id, name, type, parent_id, path, icon, permission_tag, order_num, enabled)
VALUES ('df88ca2093814d3a89f9a37eeb500cbb', '菜单管理', 'DIRECTORY', 'ac47a48d8721421980f46195a9f889b5',
        '/system/menus', 'menu', 'menu:list', 2, 1);
INSERT INTO db_template.tb_system_menu (id, name, type, parent_id, path, icon, permission_tag, order_num, enabled)
VALUES ('0bd7e5eee84f442fbd25a055a2e24378', '角色管理', 'MENU', 'ac47a48d8721421980f46195a9f889b5', '/system/roles',
        'coordinate', 'role:list', 3, 1);
INSERT INTO db_template.tb_system_menu (id, name, type, parent_id, path, icon, permission_tag, order_num, enabled)
VALUES ('4e80040cc4664316b79ba29814c748bf', '部门管理', 'MENU', 'ac47a48d8721421980f46195a9f889b5',
        '/system/departments', 'office-building', 'departments:list', 4, 1);
INSERT INTO db_template.tb_system_menu (id, name, type, parent_id, path, icon, permission_tag, order_num, enabled)
VALUES ('120773d011ea4215a3c674bb57c808fd', '岗位管理', 'MENU', 'ac47a48d8721421980f46195a9f889b5', '/system/positions',
        'management', 'position:list', 5, 1);
INSERT INTO db_template.tb_system_menu (id, name, type, parent_id, path, icon, permission_tag, order_num, enabled)
VALUES ('360473d295f649438744c1f499026472', '代码生成', 'MENU', 'ac47a48d8721421980f46195a9f889b5', '/system/codegen',
        'printer', null, 100, 1);