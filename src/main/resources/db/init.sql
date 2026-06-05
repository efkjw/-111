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
