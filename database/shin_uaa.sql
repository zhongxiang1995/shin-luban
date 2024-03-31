CREATE
    DATABASE /*!32312 IF NOT EXISTS*/`shin_uaa` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE
    `shin_uaa`;

#角色表
drop table if exists `shin_uaa`.`sys_role`;
create table `shin_uaa`.`sys_role`
(
    id          varchar(32)                         not null comment 'PK'
        primary key,
    role_name   varchar(64)                         not null comment '角色名',
    role_code   varchar(64)                         not null comment '角色编码',
    constraint idx_role_code
        unique (role_code)
)
    comment '系统角色表' collate = utf8mb4_bin;

INSERT INTO shin_uaa.sys_role (id, role_name, role_code) VALUES ('1', '产品编辑人', 'EDITOR');
INSERT INTO shin_uaa.sys_role (id, role_name, role_code) VALUES ('2', '产品查询人', 'USER');

#菜单表
drop table if exists `shin_uaa`.`sys_menu`;
create table `shin_uaa`.`sys_menu`
(
    id          varchar(32)                         not null comment '菜单ID'
        primary key,
    name        varchar(32)                         not null comment '菜单名称',
    permission  varchar(64)                         null comment '菜单权限标识'
)
    comment '菜单权限表' charset = utf8mb3;

INSERT INTO shin_uaa.sys_menu (id, name, permission) VALUES ('1', '产品分页列表', 'product:page');
INSERT INTO shin_uaa.sys_menu (id, name, permission) VALUES ('2', '产品新增', 'product:add');
INSERT INTO shin_uaa.sys_menu (id, name, permission) VALUES ('3', '产品修改', 'product:edit');
INSERT INTO shin_uaa.sys_menu (id, name, permission) VALUES ('4', '产品删除', 'product:del');

#用户表
drop table if exists `shin_uaa`.`sys_user`;
create table `shin_uaa`.`sys_user`
(
    id          varchar(32)                         not null comment '主键ID'
        primary key,
    username    varchar(64)                         not null comment '用户名',
    password    varchar(255)                        not null comment '密码',
    type        char(32)  default 'mysql'           not null comment '账号类型：mysql；ldap；github',
    constraint uk_username
        unique (username)
)
    comment '用户表' collate = utf8mb4_bin;

INSERT INTO shin_uaa.sys_user (id, username, password, type) VALUES ('1', 'user_1', 'user_1',  'mysql');
INSERT INTO shin_uaa.sys_user (id, username, password, type) VALUES ('2', 'editor_1', 'editor_1', 'mysql');
INSERT INTO shin_uaa.sys_user (id, username, password, type) VALUES ('3', 'adm_1', 'adm_1',  'mysql');

#角色菜单表
drop table if exists `shin_uaa`.`sys_role_menu`;
create table `shin_uaa`.`sys_role_menu`
(
    role_id     varchar(32)                         not null comment '角色ID',
    menu_id     varchar(32)                         not null comment '菜单ID',
    primary key (role_id, menu_id)
)
    comment '角色菜单表' charset = utf8mb3;

INSERT INTO shin_uaa.sys_role_menu (role_id, menu_id) VALUES ('1', '1');
INSERT INTO shin_uaa.sys_role_menu (role_id, menu_id) VALUES ('1', '2');
INSERT INTO shin_uaa.sys_role_menu (role_id, menu_id) VALUES ('1', '3');
INSERT INTO shin_uaa.sys_role_menu (role_id, menu_id) VALUES ('1', '4');
INSERT INTO shin_uaa.sys_role_menu (role_id, menu_id) VALUES ('2', '1');

#用户角色表
drop table if exists `shin_uaa`.`sys_user_role`;
create table `shin_uaa`.`sys_user_role`
(
    user_id varchar(32) not null comment '用户ID',
    role_id varchar(32) not null comment '角色ID',
    primary key (user_id, role_id)
)
    comment '用户角色表' charset = utf8mb3;

INSERT INTO shin_uaa.sys_user_role (user_id, role_id) VALUES ('1', '2');
INSERT INTO shin_uaa.sys_user_role (user_id, role_id) VALUES ('2', '1');
INSERT INTO shin_uaa.sys_user_role (user_id, role_id) VALUES ('3', '1');
INSERT INTO shin_uaa.sys_user_role (user_id, role_id) VALUES ('3', '2');

#产品表
drop table if exists `shin_uaa`.`product`;
create table `shin_uaa`.`product`
(
    id          varchar(32)                         not null comment '产品ID'
        primary key,
    name        varchar(32)                         not null comment '产品名称',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint uk_name
        unique (name)
)comment '产品表' charset = utf8mb3;