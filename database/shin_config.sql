CREATE
DATABASE /*!32312 IF NOT EXISTS*/`shin_config` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE
`shin_config`;

drop table if exists `shin_config`.`config_info`;
create table `shin_config`.`config_info`
(
    id                 bigint auto_increment comment 'id'
        primary key,
    data_id            varchar(255)                           not null comment 'data_id',
    group_id           varchar(255)                           null,
    content            longtext                               not null comment 'content',
    md5                varchar(32)                            null comment 'md5',
    gmt_create         datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    gmt_modified       datetime     default CURRENT_TIMESTAMP not null comment '修改时间',
    src_user           text                                   null comment 'source user',
    src_ip             varchar(50)                            null comment 'source ip',
    app_name           varchar(128)                           null,
    tenant_id          varchar(128) default ''                null comment '租户字段',
    c_desc             varchar(256)                           null,
    c_use              varchar(64)                            null,
    effect             varchar(64)                            null,
    type               varchar(64)                            null,
    c_schema           text                                   null,
    encrypted_data_key text                                   not null comment '秘钥',
    constraint uk_configinfo_datagrouptenant
        unique (data_id, group_id, tenant_id)
)
    comment 'config_info' collate = utf8mb3_bin;

INSERT INTO shin_config.config_info (id, data_id, group_id, content, md5, gmt_create, gmt_modified, src_user, src_ip, app_name, tenant_id, c_desc, c_use, effect, type, c_schema, encrypted_data_key) VALUES (1, 'dynamic_routes', 'DEFAULT_GROUP', 'routes:
# shin-uaa-admin
- id: shin-uaa-admin
  predicates:
  - name: Path
    args: 
      _genkey_0: /uaa/**
  filters: []
  uri: lb://shin-uaa-admin
  order: 0', '96d23ae7fe90ab685f927701412599f4', '2019-07-30 14:26:08', '2024-03-29 09:13:18', 'nacos', '0:0:0:0:0:0:0:1', '', '', '动态路由配置', 'null', 'null', 'yaml', 'null', '');
INSERT INTO shin_config.config_info (id, data_id, group_id, content, md5, gmt_create, gmt_modified, src_user, src_ip, app_name, tenant_id, c_desc, c_use, effect, type, c_schema, encrypted_data_key) VALUES (2, 'application-dev.yml', 'DEFAULT_GROUP', 'spring:
  # redis 相关
  redis:
    host: shin-redis
    port: 6379
    password: 
    database: 2
# mybaits-plus配置
mybatis-plus:
  # MyBatis Mapper所对应的XML文件位置
  mapper-locations: classpath:/mapper/*Mapper.xml
  global-config:
    sql-parser-cache: true
    # 关闭MP3.0自带的banner
    banner: false
    db-config:
      # 主键类型
      id-type: auto

#登录类型      
login:
  types:
    mysql: mysqlUserLoginStrategy', '4cb4b5536f7173d21b6d92eef16b87c8', '2019-07-28 23:14:26', '2024-03-29 09:18:15', 'nacos', '0:0:0:0:0:0:0:1', '', '', '主配置文件', 'null', 'null', 'yaml', 'null', '');
INSERT INTO shin_config.config_info (id, data_id, group_id, content, md5, gmt_create, gmt_modified, src_user, src_ip, app_name, tenant_id, c_desc, c_use, effect, type, c_schema, encrypted_data_key) VALUES (3, 'luban-gateway-dev.yml', 'DEFAULT_GROUP', '', '910db8876ea5a625dd7940bdbf10aa40', '2019-07-28 23:14:26', '2023-02-13 02:49:02', 'nacos', '127.0.0.1', '', '', '网关配置', 'null', 'null', 'yaml', 'null', '');
INSERT INTO shin_config.config_info (id, data_id, group_id, content, md5, gmt_create, gmt_modified, src_user, src_ip, app_name, tenant_id, c_desc, c_use, effect, type, c_schema, encrypted_data_key) VALUES (4, 'luban-upms-admin-dev.yml', 'DEFAULT_GROUP', '# 数据源
spring:
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    druid:
      driver-class-name: com.mysql.cj.jdbc.Driver
      username: root
      password: root
      url: jdbc:mysql://luban-mysql:3306/shin_uaa?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&allowMultiQueries=true&allowPublicKeyRetrieval=true
# Logger Config sql日志
logging:
  level:
    com.shin.uaa.admin.mapper: debug', '951b7079f1135ea258b179b50ec45983', '2019-07-28 23:14:26', '2024-03-29 09:23:47', 'nacos', '0:0:0:0:0:0:0:1', '', '', '用户权限管理配置', 'null', 'null', 'yaml', 'null', '');

drop table if exists `shin_config`.`config_info_aggr`;
create table `shin_config`.`config_info_aggr`
(
    id           bigint auto_increment comment 'id'
        primary key,
    data_id      varchar(255)            not null comment 'data_id',
    group_id     varchar(255)            not null comment 'group_id',
    datum_id     varchar(255)            not null comment 'datum_id',
    content      longtext                not null comment '内容',
    gmt_modified datetime                not null comment '修改时间',
    app_name     varchar(128)            null,
    tenant_id    varchar(128) default '' null comment '租户字段',
    constraint uk_configinfoaggr_datagrouptenantdatum
        unique (data_id, group_id, tenant_id, datum_id)
)
    comment '增加租户字段' collate = utf8mb3_bin;

drop table if exists `shin_config`.`config_info_beta`;
create table `shin_config`.`config_info_beta`
(
    id                 bigint auto_increment comment 'id'
        primary key,
    data_id            varchar(255)                           not null comment 'data_id',
    group_id           varchar(128)                           not null comment 'group_id',
    app_name           varchar(128)                           null comment 'app_name',
    content            longtext                               not null comment 'content',
    beta_ips           varchar(1024)                          null comment 'betaIps',
    md5                varchar(32)                            null comment 'md5',
    gmt_create         datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    gmt_modified       datetime     default CURRENT_TIMESTAMP not null comment '修改时间',
    src_user           text                                   null comment 'source user',
    src_ip             varchar(50)                            null comment 'source ip',
    tenant_id          varchar(128) default ''                null comment '租户字段',
    encrypted_data_key text                                   not null comment '秘钥',
    constraint uk_configinfobeta_datagrouptenant
        unique (data_id, group_id, tenant_id)
)
    comment 'config_info_beta' collate = utf8mb3_bin;

drop table if exists `shin_config`.`config_info_tag`;
create table `shin_config`.`config_info_tag`
(
    id           bigint auto_increment comment 'id'
        primary key,
    data_id      varchar(255)                           not null comment 'data_id',
    group_id     varchar(128)                           not null comment 'group_id',
    tenant_id    varchar(128) default ''                null comment 'tenant_id',
    tag_id       varchar(128)                           not null comment 'tag_id',
    app_name     varchar(128)                           null comment 'app_name',
    content      longtext                               not null comment 'content',
    md5          varchar(32)                            null comment 'md5',
    gmt_create   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    gmt_modified datetime     default CURRENT_TIMESTAMP not null comment '修改时间',
    src_user     text                                   null comment 'source user',
    src_ip       varchar(50)                            null comment 'source ip',
    constraint uk_configinfotag_datagrouptenanttag
        unique (data_id, group_id, tenant_id, tag_id)
)
    comment 'config_info_tag' collate = utf8mb3_bin;

drop table if exists `shin_config`.`config_tags_relation`;
create table `shin_config`.`config_tags_relation`
(
    id        bigint                  not null comment 'id',
    tag_name  varchar(128)            not null comment 'tag_name',
    tag_type  varchar(64)             null comment 'tag_type',
    data_id   varchar(255)            not null comment 'data_id',
    group_id  varchar(128)            not null comment 'group_id',
    tenant_id varchar(128) default '' null comment 'tenant_id',
    nid       bigint auto_increment
        primary key,
    constraint uk_configtagrelation_configidtag
        unique (id, tag_name, tag_type)
)
    comment 'config_tag_relation' collate = utf8mb3_bin;

create index idx_tenant_id
    on config_tags_relation (tenant_id);

drop table if exists `shin_config`.`group_capacity`;
create table `shin_config`.`group_capacity`
(
    id                bigint unsigned auto_increment comment '主键ID'
        primary key,
    group_id          varchar(128) default ''                not null comment 'Group ID，空字符表示整个集群',
    quota             int unsigned default '0'               not null comment '配额，0表示使用默认值',
    `usage`           int unsigned default '0'               not null comment '使用量',
    max_size          int unsigned default '0'               not null comment '单个配置大小上限，单位为字节，0表示使用默认值',
    max_aggr_count    int unsigned default '0'               not null comment '聚合子配置最大个数，，0表示使用默认值',
    max_aggr_size     int unsigned default '0'               not null comment '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
    max_history_count int unsigned default '0'               not null comment '最大变更历史数量',
    gmt_create        datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    gmt_modified      datetime     default CURRENT_TIMESTAMP not null comment '修改时间',
    constraint uk_group_id
        unique (group_id)
)
    comment '集群、各Group容量信息表' collate = utf8mb3_bin;

drop table if exists `shin_config`.`his_config_info`;
create table `shin_config`.`his_config_info`
(
    id                 bigint unsigned                        not null,
    nid                bigint unsigned auto_increment
        primary key,
    data_id            varchar(255)                           not null,
    group_id           varchar(128)                           not null,
    app_name           varchar(128)                           null comment 'app_name',
    content            longtext                               not null,
    md5                varchar(32)                            null,
    gmt_create         datetime     default CURRENT_TIMESTAMP not null,
    gmt_modified       datetime     default CURRENT_TIMESTAMP not null,
    src_user           text                                   null,
    src_ip             varchar(50)                            null,
    op_type            char(10)                               null,
    tenant_id          varchar(128) default ''                null comment '租户字段',
    encrypted_data_key text                                   not null comment '秘钥'
)
    comment '多租户改造' collate = utf8mb3_bin;

create index idx_did
    on his_config_info (data_id);

create index idx_gmt_create
    on his_config_info (gmt_create);

create index idx_gmt_modified
    on his_config_info (gmt_modified);
	
	create table permissions
(
    role     varchar(50)  not null,
    resource varchar(512) not null,
    action   varchar(8)   not null,
    constraint uk_role_permission
        unique (role, resource, action)
);

drop table if exists `shin_config`.`roles`;
create table `shin_config`.`roles`
(
    username varchar(50) not null,
    role     varchar(50) not null,
    constraint idx_user_role
        unique (username, role)
);

INSERT INTO shin_config.roles (username, role) VALUES ('nacos', 'ROLE_ADMIN');

drop table if exists `shin_config`.`tenant_capacity`;
create table `shin_config`.`tenant_capacity`
(
    id                bigint unsigned auto_increment comment '主键ID'
        primary key,
    tenant_id         varchar(128) default ''                not null comment 'Tenant ID',
    quota             int unsigned default '0'               not null comment '配额，0表示使用默认值',
    `usage`           int unsigned default '0'               not null comment '使用量',
    max_size          int unsigned default '0'               not null comment '单个配置大小上限，单位为字节，0表示使用默认值',
    max_aggr_count    int unsigned default '0'               not null comment '聚合子配置最大个数',
    max_aggr_size     int unsigned default '0'               not null comment '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
    max_history_count int unsigned default '0'               not null comment '最大变更历史数量',
    gmt_create        datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    gmt_modified      datetime     default CURRENT_TIMESTAMP not null comment '修改时间',
    constraint uk_tenant_id
        unique (tenant_id)
)
    comment '租户容量信息表' collate = utf8mb3_bin;

drop table if exists `shin_config`.`tenant_info`;
create table `shin_config`.`tenant_info`
(
    id            bigint auto_increment comment 'id'
        primary key,
    kp            varchar(128)            not null comment 'kp',
    tenant_id     varchar(128) default '' null comment 'tenant_id',
    tenant_name   varchar(128) default '' null comment 'tenant_name',
    tenant_desc   varchar(256)            null comment 'tenant_desc',
    create_source varchar(32)             null comment 'create_source',
    gmt_create    bigint                  not null comment '创建时间',
    gmt_modified  bigint                  not null comment '修改时间',
    constraint uk_tenant_info_kptenantid
        unique (kp, tenant_id)
)
    comment 'tenant_info' collate = utf8mb3_bin;

create index idx_tenant_id
    on tenant_info (tenant_id);

drop table if exists `shin_config`.`users`;
create table `shin_config`.`users`
(
    username varchar(50)  not null
        primary key,
    password varchar(500) not null,
    enabled  tinyint(1)   not null
);

INSERT INTO shin_config.users (username, password, enabled) VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);

