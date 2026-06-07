create database if not exists takeout
    default character set utf8mb4
    default collate utf8mb4_general_ci;

use takeout;

drop table if exists employee;

create table employee
(
    id          bigint primary key auto_increment comment '主键',
    name        varchar(32)  not null comment '姓名',
    username    varchar(32)  not null comment '用户名',
    password    varchar(64)  not null comment '密码',
    phone       varchar(11)  null comment '手机号',
    sex         varchar(2)   null comment '性别',
    id_number   varchar(18)  null comment '身份证号',
    status      int          not null default 1 comment '账号状态，1正常，0锁定',
    create_time datetime     null comment '创建时间',
    update_time datetime     null comment '更新时间',
    create_user bigint       null comment '创建人',
    update_user bigint       null comment '修改人',
    constraint idx_username unique (username)
) comment '员工表';

insert into employee
(name, username, password, phone, sex, id_number, status, create_time, update_time, create_user, update_user)
values
('管理员', 'admin', md5('123456'), '13800138000', '1', '110101199001010011', 1, now(), now(), 1, 1);


CREATE TABLE IF NOT EXISTS `category` (
                                          `id`          BIGINT        NOT NULL AUTO_INCREMENT COMMENT '主键',
                                          `type`        INT           COMMENT '类型：1菜品分类 2套餐分类',
                                          `name`        VARCHAR(32)   NOT NULL UNIQUE COMMENT '分类名称',
                                          `sort`        INT           NOT NULL DEFAULT 0 COMMENT '顺序',
                                          `status`      INT           NOT NULL DEFAULT 1 COMMENT '状态 0:禁用 1:启用',
                                          `create_time` DATETIME      COMMENT '创建时间',
                                          `update_time` DATETIME      COMMENT '修改时间',
                                          `create_user` BIGINT        COMMENT '创建人',
                                          `update_user` BIGINT        COMMENT '修改人',
                                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜品及套餐分类';


-- 菜品表
CREATE TABLE IF NOT EXISTS `dish` (
                                      `id`          BIGINT        NOT NULL AUTO_INCREMENT COMMENT '主键',
                                      `name`        VARCHAR(32)   NOT NULL UNIQUE COMMENT '菜品名称',
                                      `category_id` BIGINT        NOT NULL COMMENT '菜品分类id',
                                      `price`       DECIMAL(10,2) COMMENT '菜品价格',
                                      `image`       VARCHAR(255)  COMMENT '图片路径',
                                      `description` VARCHAR(255)  COMMENT '描述信息',
                                      `status`      INT           NOT NULL DEFAULT 1 COMMENT '0 停售 1 起售',
                                      `create_time` DATETIME      COMMENT '创建时间',
                                      `update_time` DATETIME      COMMENT '修改时间',
                                      `create_user` BIGINT        COMMENT '创建人',
                                      `update_user` BIGINT        COMMENT '修改人',
                                      PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜品';

-- 菜品口味表
CREATE TABLE IF NOT EXISTS `dish_flavor` (
                                             `id`      BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
                                             `dish_id` BIGINT       NOT NULL COMMENT '菜品id',
                                             `name`    VARCHAR(32)  NOT NULL COMMENT '口味名称',
                                             `value`   VARCHAR(255) COMMENT '口味数据列表（JSON）',
                                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜品口味';


-- 套餐表
CREATE TABLE IF NOT EXISTS `setmeal` (
                                         `id`          BIGINT        NOT NULL AUTO_INCREMENT COMMENT '主键',
                                         `category_id` BIGINT        NOT NULL COMMENT '菜品分类id',
                                         `name`        VARCHAR(32)   NOT NULL UNIQUE COMMENT '套餐名称',
                                         `price`       DECIMAL(10,2) NOT NULL COMMENT '套餐价格',
                                         `status`      INT           NOT NULL DEFAULT 1 COMMENT '0停售 1起售',
                                         `description` VARCHAR(255)  COMMENT '描述信息',
                                         `image`       VARCHAR(255)  COMMENT '图片路径',
                                         `create_time` DATETIME      COMMENT '创建时间',
                                         `update_time` DATETIME      COMMENT '修改时间',
                                         `create_user` BIGINT        COMMENT '创建人',
                                         `update_user` BIGINT        COMMENT '修改人',
                                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='套餐';

-- 套餐菜品关联表（套餐里包含哪些菜品，各几份）
CREATE TABLE IF NOT EXISTS `setmeal_dish` (
                                              `id`         BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
                                              `setmeal_id` BIGINT       COMMENT '套餐id',
                                              `dish_id`    BIGINT       COMMENT '菜品id',
                                              `name`       VARCHAR(32)  COMMENT '菜品名称（冗余）',
                                              `price`      DECIMAL(10,2) COMMENT '菜品价格（冗余）',
                                              `copies`     INT          COMMENT '菜品份数',
                                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='套餐菜品关联';