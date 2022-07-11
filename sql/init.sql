use sjx;
CREATE TABLE `sys_user` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系统用户id',
                            `real_name` varchar(50) DEFAULT '' COMMENT '用户姓名',
                            `username` varchar(50) NOT NULL COMMENT '用户名',
                            `password` varchar(100) DEFAULT '' COMMENT '密码',
                            `email` varchar(100) DEFAULT '' COMMENT '邮箱',
                            `mobile` varchar(100) DEFAULT '' COMMENT '手机号',
                            `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态 0：禁用 1：正常',
                            `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者用户id',
                            `create_time` bigint(20) DEFAULT '0' COMMENT '创建时间毫秒值',
                            `modify_time` bigint(20) DEFAULT '0' COMMENT '记录修改时间的毫秒数',
                            `flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0：正常 -1：删除',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='系统用户';

CREATE TABLE `sys_role` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色id',
                            `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
                            `remark` varchar(100) DEFAULT NULL COMMENT '备注',
                            `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
                            `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间毫秒值',
                            `modify_time` bigint(20) DEFAULT '0' COMMENT '修改时间的毫秒值',
                            `flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0：正常 -1：删除',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='角色';


CREATE TABLE `sys_menu` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
                            `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
                            `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
                            `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
                            `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
                            `type` int(11) DEFAULT NULL COMMENT '类型   1：目录   2：菜单   3：模块 4：隐藏模块',
                            `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
                            `order_num` int(11) DEFAULT NULL COMMENT '排序',
                            `level` int(11) DEFAULT NULL COMMENT '当前层级 1-N',
                            `router_url` varchar(500) DEFAULT '' COMMENT '前端路由url',
                            `create_time` bigint(20) DEFAULT '0' COMMENT '创建时间毫秒值',
                            `modify_time` bigint(20) DEFAULT '0' COMMENT '修改时间的毫秒值',
                            `flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0：正常 -1：删除',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

CREATE TABLE `sys_role_menu` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                 `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
                                 `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
                                 `flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0：正常 -1：删除',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

CREATE TABLE `sys_user_role` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                 `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                 `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
                                 `flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0：正常 -1：删除',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- 类目表 
-- auto-generated definition
create table ads_common_choose
(
    id          bigint auto_increment comment '数据id'
        primary key,
    type        tinyint                 null comment '搜索条件类型',
    name        varchar(50)             null comment '名称',
    `rank`      int          default 0  null comment '排名 排名越小越靠前',
    flag        tinyint      default 0  null comment '删除标识:0:正常-1:删除',
    parent_id   bigint       default 0  null comment '父级id',
    value_range varchar(200) default '' null comment '对应下拉框值的范围',
    remark      varchar(200) default '' null comment '备注信息'
)
    comment '类目表' charset = utf8mb4;

create index type
    on ads_common_choose (type);

create index type_index
    on ads_common_choose (type);

-- 类目类型表 
-- auto-generated definition
create table ads_common_choose_type
(
    id               bigint auto_increment comment '数据id'
        primary key,
    choose_type_name varchar(128)      not null comment '选项名称',
    create_user_id   bigint            null comment '创建者用户id',
    create_time      bigint  default 0 null comment '创建时间  单位：ms',
    modify_time      bigint  default 0 null comment '记录修改时间的毫秒数',
    flag             tinyint default 0 not null comment '是否删除 0：正常 -1：删除'
)
    comment '类目类型表';

-- 指标-数据表 
-- auto-generated definition
create table big_screen_data
(
    id            bigint auto_increment comment '数据id'
        primary key,
    name          varchar(255)             null,
    type          varchar(255)             null comment '指标类型',
    code          varchar(50)              not null comment '指标编码，格式 DT+数字',
    replace_value varchar(30)              null comment '指标编码值',
    sql_code      text                     null,
    result_type   int(2)                   null comment 'sql 返回数据类型 0:单值数据 1：多值数据',
    code_remark   varchar(2000) default '' not null comment '计算口径',
    caliber       varchar(1000)            null comment '指标编码备注',
    unit          varchar(25)              null comment '指标单位',
    count_time    varchar(5)               null comment '查询时间，实时，定时',
    value_type    int(2)                   null comment 'value查询方式 0：查询big_screen_value表，1：es聚合查询',
    time_dim      varchar(15)              null comment '时间（年月）',
    area_dim      varchar(15)              null comment '维度，区域（街道，楼宇，平台），',
    data_source   varchar(255)             null comment '数据源名称',
    flag          tinyint       default 0  not null comment '删除标识:0:正常-1:删除',
    constraint code
        unique (code)
)
    comment '指标-数据表';

-- 大屏-指标数据 
-- auto-generated definition
create table big_screen_value
(
    id          bigint auto_increment comment '数据id'
        primary key,
    code        varchar(50)   default '' not null comment '指标编码',
    value       varchar(5000) default '' not null comment '指标的数值',
    time_dim    varchar(25)              null comment '时间维度',
    area_dim    varchar(25)              null comment '区域维度',
    modify_time bigint        default 0  not null comment '数据更新时间的 毫秒值',
    flag        tinyint       default 0  not null comment '删除标识:0:正常-1:删除'
)
    comment '大屏-指标数据';

create index ads_big_data_all_area_data_code
    on big_screen_value (code);
