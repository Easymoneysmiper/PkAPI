-- 接口信息
create table if not exists pkapi.`interface_info`
(
    `id` bigint not null auto_increment comment '主键' primary key,
    `name` varchar(256) not null comment '接口名称',
    `userId` bigint not null comment '创建用户',
    `url` varchar(512) not null comment '接口路由',
    `description` varchar(256) null comment '接口描述',
    `method` varchar(256) default 'GET' null comment '接口方法',
    `status` tinyint default 0 not null comment '接口状态：（0-开放 1-关闭）',
    `requestHeader` varchar(256) null comment '请求头',
    `responseHeader` varchar(256) null comment '响应头',
    `createTime` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `updateTime` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `isDeleted` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
) comment '接口信息';
insert into pkapi.`interface_info` (`name`, `userId`, `url`, `createTime`, `updateTime`) values ('覃钰轩', 5906361, 'www.gidget-daugherty.co', '2022-06-27 18:37:25', '2022-12-30 04:00:35');
insert into pkapi.`interface_info` (`name`, `userId`, `url`, `createTime`, `updateTime`) values ('熊泽洋', 60, 'www.micheline-dickens.com', '2022-07-07 11:09:39', '2022-04-13 18:54:56');
insert into pkapi.`interface_info` (`name`, `userId`, `url`, `createTime`, `updateTime`) values ('贾靖琪', 544042, 'www.janet-lang.org', '2022-12-19 07:16:45', '2022-09-16 02:39:25');
insert into pkapi.`interface_info` (`name`, `userId`, `url`, `createTime`, `updateTime`) values ('刘雨泽', 693, 'www.robyn-hirthe.biz', '2022-05-31 03:00:12', '2022-05-18 13:05:11');
insert into pkapi.`interface_info` (`name`, `userId`, `url`, `createTime`, `updateTime`) values ('郭荣轩', 6, 'www.coleman-adams.info', '2022-05-28 07:24:49', '2022-01-21 06:07:26');
insert into pkapi.`interface_info` (`name`, `userId`, `url`, `createTime`, `updateTime`) values ('方旭尧', 6826985, 'www.loren-okeefe.name', '2022-11-30 23:43:00', '2022-09-02 09:24:33');
insert into pkapi.`interface_info` (`name`, `userId`, `url`, `createTime`, `updateTime`) values ('邵聪健', 52614613, 'www.teddy-reichert.com', '2022-11-23 01:39:38', '2022-05-04 05:05:38');
insert into pkapi.`interface_info` (`name`, `userId`, `url`, `createTime`, `updateTime`) values ('莫煜祺', 11, 'www.alec-donnelly.co', '2022-02-10 03:13:19', '2022-01-14 22:33:35');
insert into pkapi.`interface_info` (`name`, `userId`, `url`, `createTime`, `updateTime`) values ('魏浩宇', 6247296267, 'www.juan-cummings.info', '2022-12-07 20:05:07', '2022-01-05 16:20:57');
insert into pkapi.`interface_info` (`name`, `userId`, `url`, `createTime`, `updateTime`) values ('蒋雨泽', 31805, 'www.carlena-ankunding.com', '2022-03-17 16:49:15', '2022-10-18 13:42:16');
insert into pkapi.`interface_info` (`name`, `userId`, `url`, `createTime`, `updateTime`) values ('曹思源', 39349317, 'www.leroy-nicolas.co', '2022-05-07 17:32:25', '2022-04-28 06:58:15');
insert into pkapi.`interface_info` (`name`, `userId`, `url`, `createTime`, `updateTime`) values ('胡哲瀚', 362, 'www.viva-towne.org', '2022-09-21 03:28:48', '2022-10-13 22:48:46');
insert into pkapi.`interface_info` (`name`, `userId`, `url`, `createTime`, `updateTime`) values ('马金鑫', 99842, 'www.ellis-hintz.info', '2022-08-27 03:06:35', '2022-09-02 11:41:47');
insert into pkapi.`interface_info` (`name`, `userId`, `url`, `createTime`, `updateTime`) values ('彭金鑫', 901, 'www.brant-roberts.net', '2022-04-25 10:38:03', '2022-10-14 11:36:01');
insert into pkapi.`interface_info` (`name`, `userId`, `url`, `createTime`, `updateTime`) values ('薛越彬', 5023, 'www.alec-bahringer.net', '2022-01-06 19:47:51', '2022-05-10 03:51:27');
insert into pkapi.`interface_info` (`name`, `userId`, `url`, `createTime`, `updateTime`) values ('陶瑾瑜', 43, 'www.derrick-rutherford.info', '2022-06-12 15:57:55', '2022-03-26 07:47:20');
insert into pkapi.`interface_info` (`name`, `userId`, `url`, `createTime`, `updateTime`) values ('毛鑫鹏', 343316, 'www.tyree-padberg.io', '2022-11-07 21:10:11', '2022-04-24 20:16:09');
insert into pkapi.`interface_info` (`name`, `userId`, `url`, `createTime`, `updateTime`) values ('傅文轩', 6919538380, 'www.haywood-dare.info', '2022-10-17 14:30:51', '2022-03-06 17:14:34');
insert into pkapi.`interface_info` (`name`, `userId`, `url`, `createTime`, `updateTime`) values ('余笑愚', 2, 'www.roxanne-jones.org', '2022-10-05 20:16:46', '2022-07-06 23:12:01');
insert into pkapi.`interface_info` (`name`, `userId`, `url`, `createTime`, `updateTime`) values ('尹越彬', 68362168, 'www.eddie-breitenberg.biz', '2022-05-12 21:27:06', '2022-07-26 05:13:14');

create table if not exists `user_interface_info`
(
    `id` bigint not null auto_increment comment '主键' primary key,
    `userId` bigint not null comment '调用用户id',
    `interfaceInfoId` bigint not null comment '接口id',
    `totalNum` int default 0 not null comment '总调用次数',
    `leftNum` int default 0 not null comment '剩余调用次数',
    `status` int default 0 not null comment '0-正常，1-禁用',
    `createTime` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `updateTime` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `isDelete` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
) comment '用户调用接口关系';