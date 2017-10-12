<<<<<<< HEAD
/* 全统一 | 0：false | 1：true */

drop table if exists t_erp_hotel;

/*==============================================================*/
/* Table: t_erp_hotel                                           */
/*==============================================================*/
create table t_erp_hotel
(
   id                   int not null auto_increment comment 'ID',
   bus_id               int(11) not null comment '商家ID',
   shop_id              int(11) not null comment '门店ID',
   name                 varchar(200) not null comment '名称',
   introduction         varchar(2000) comment '介绍',
   phone                varchar(12) not null comment '电话',
   address              varchar(200) not null comment '地址',
   if_reserve_man       int(1) not null default 0 comment '是否收集预约人',
   if_reserve_phone     int(1) not null default 0 comment '是否收集预约电话',
   if_remark            int(1) not null default 0 comment '是否收集备注',
   pay_mode             int(1) not null default 0 comment '支付方式(1：在线支付 | 2：到店支付 | 3：1&2)',
   if_sms               int(1) not null default 0 comment '是否开启短信通知',
   sms_phone            varchar(12) comment '接受信息手机号',
   if_check_out         int(1) not null default 0 comment '是否开启一键退房',
   if_food              int(1) not null default 0 comment '是否开启餐饮',
   if_bulletin          int(1) not null default 0 comment '是否开启公告',
   bulletin             varchar(2000) comment '公告',
   if_remnant_room      int(1) not null default 0 comment '是否显示剩余房型',
   if_continue          int(1) not null default 0 comment '是否显示一键续住',
   if_confirm_info      int(1) not null default 0 comment '是否确认订单信息功能',
   if_breakfast         int(1) not null default 0 comment '是否开启早餐券',
   breakfast_quantity   int(3) comment '默认早餐券数量',
   if_activity_prices   int(1) not null default 0 comment '是否开启房价活动',
   if_free_deposit      int(1) not null default 0 comment '是否开启会员免押金',
   if_last_check_out    int(1) not null default 0 comment '是否开启会员最晚退房时间',
   creator              varchar(20) unique comment '创建者',
   create_time          datetime comment '创建时间',
   update_time          timestamp default now() on update CURRENT_TIMESTAMP comment '更新时间',
   primary key (id)
);

alter table t_erp_hotel engine=innodb default charset=utf8 comment 'ERP酒店主表';
/*{busId:1, shopId:1, name:'jiudian', phone:'123', address:'dizhi', introduction:'jianjie'}*/

drop table if exists t_erp_hotel_image;

/*==============================================================*/
/* Table: t_erp_hotel_image                                     */
/*==============================================================*/
create table t_erp_hotel_image
(
   id                   int not null auto_increment comment 'ID',
   subjection_id        int(11) not null comment '隶属ID',
   name                 varchar(100) comment '名称',
   url                  varchar(2000) comment '路径',
   type                 varchar(12) not null comment '类型',
   subjection           int(2) not null comment '隶属(0:酒店, 1:房型, 2:菜品, 等等)',
   primary key (id)
);

alter table t_erp_hotel_image engine=innodb default charset=utf8 comment 'ERP酒店图片';

drop table if exists t_erp_hotel_installation;

/*==============================================================*/
/* Table: t_erp_hotel_installation                              */
/*==============================================================*/
create table t_erp_hotel_installation
(
   id                   int not null auto_increment comment 'ID',
   name                 varchar(20) not null unique comment '名称',
   logo                 varchar(2000) comment 'LOGO路径',
   if_use               int(1) not null default 0 comment '是否启用',
   type                 int(1) not null default 0 comment '类型(0:酒店)',
   primary key (id)
);

alter table t_erp_hotel_installation engine=innodb default charset=utf8 comment 'ERP酒店基础设施';

drop table if exists t_erp_hotel_installation_relation;

/*==============================================================*/
/* Table: t_erp_hotel_installation_relation                     */
/*==============================================================*/
create table t_erp_hotel_installation_relation
(
   hotel_id             int(11) not null comment '酒店ID',
   installation_id      int(11) not null comment '基础设施ID',
   unique key hir_key (hotel_id, installation_id)
);

alter table t_erp_hotel_installation_relation engine=innodb default charset=utf8 comment '酒店 - 基础设施';

drop table if exists t_erp_hotel_member_deposit_relation;

/*==============================================================*/
/* Table: t_erp_hotel_member_deposit_relation                   */
/*==============================================================*/
create table t_erp_hotel_member_deposit_relation
(
   hotel_id             int(11) not null comment '酒店ID',
   vip_level            int(3) not null comment '会员等级',
   if_free_deposit      int(1) not null default 0 comment '是否免押金'
);

alter table t_erp_hotel_member_deposit_relation engine=innodb default charset=utf8 comment '酒店 - 会员免押设置';

drop table if exists t_erp_hotel_member_check_out_relation;

/*==============================================================*/
/* Table: t_erp_hotel_member_check_out_relation                 */
/*==============================================================*/
create table t_erp_hotel_member_check_out_relation
(
   hotel_id             int(11) not null comment '酒店ID',
   vip_level            int(3) not null comment '会员等级',
   last_check_out       datetime comment '最晚退房时间'
);

alter table t_erp_hotel_member_check_out_relation engine=innodb default charset=utf8 comment '酒店 - 会员最晚退房设置';

/*==============================================================*//*==============================================================*//*==============================================================*/

drop table if exists t_erp_hotel_room;

/*==============================================================*/
/* Table: t_erp_hotel_room                                      */
/*==============================================================*/
create table t_erp_hotel_room
(
   id                   int not null auto_increment comment 'ID',
   hotel_id             int(11) not null comment '酒店ID',
   type                 varchar(20) not null comment '房型类型',
   total                int(3) not null default 0 comment '房型总数',
   introduction         varchar(140) comment '简介',
   price                int(10) not null default 0 comment '价格',
   deposit              int(10) not null default 0 comment '押金',
   if_discount_price    int(1) not null default 0 comment '是否启用折扣价',
   discount_price       int(10) comment '折扣价',
   if_weekend_price     int(1) not null default 0 comment '是否启用周末价',
   weekend_price        int(10) comment '周末价',
   if_monday_price      int(1) not null default 0 comment '是否启用周一价',
   monday_price         int(10) comment '周末价',
   if_tuesday_price     int(1) not null default 0 comment '是否启用周二价',
   tuesday_price        int(10) comment '周末价',
   if_wednesday_price   int(1) not null default 0 comment '是否启用周三价',
   wednesday_price      int(10) comment '周末价',
   if_thursday_price    int(1) not null default 0 comment '是否启用周四价',
   thursday_price       int(10) comment '周末价',
   if_sunday_price      int(1) not null default 0 comment '是否启用周日价',
   sunday_price         int(10) comment '周末价',
   -- description          varchar(2000) comment '相关说明',
   team_price           int(10) comment '团队价',
   team_price_effective int(7) comment '团队价生效数量',
   if_use               int(1) not null default 0 comment '是否启用',
   creator              varchar(20) unique comment '创建者',
   create_time          datetime comment '创建时间',
   update_time          timestamp default now() on update CURRENT_TIMESTAMP comment '更新时间',
   primary key (id)
);

alter table t_erp_hotel_room engine=innodb default charset=utf8 comment 'ERP酒店房型';

-- drop table if exists t_erp_hotel_room_floor;

/*==============================================================*/
/* Table: t_erp_hotel_room_floor                                */
/*==============================================================*/
-- create table t_erp_hotel_room_floor
-- (
--    id                   int not null auto_increment comment 'ID',
--    room_id              int(11) not null comment '房型ID',
--    floor                varchar(10) not null comment '楼层',
--    primary key (id)
-- );

-- alter table t_erp_hotel_room_floor engine=innodb default charset=utf8 comment 'ERP酒店房型楼层';

drop table if exists t_erp_hotel_room_suite;

/*==============================================================*/
/* Table: t_erp_hotel_room_suite                                */
/*==============================================================*/
create table t_erp_hotel_room_suite
(
   id                   int not null auto_increment comment 'ID',
   room_id              int(11) not null comment '房型ID',
   floor                varchar(10) not null comment '楼层',
   number               varchar(10) not null comment '房间号',
   status               varchar(10) comment '房间状态(第一行)',
   room_status          varchar(10) comment '房态',
   remark               varchar(140) comment '备注',
   primary key (id)
);

alter table t_erp_hotel_room_suite engine=innodb default charset=utf8 comment 'ERP酒店房型楼层房间';

drop table if exists t_erp_hotel_room_suite_status;

/*==============================================================*/
/* Table: t_erp_hotel_room_suite_status                         */
/*==============================================================*/
create table t_erp_hotel_room_suite_status
(
   id                   int not null auto_increment comment 'ID',
   logo                 varchar(1000) comment 'logo url',
   name                 varchar(10) not null comment ' 状态名称',
   primary key (id)
);

alter table t_erp_hotel_room_suite_status engine=innodb default charset=utf8 comment 'ERP酒店房型楼层房间状态(第二行)';

drop table if exists t_erp_hotel_room_suite_status_relation;

/*==============================================================*/
/* Table: t_erp_hotel_room_suite_status_relation                */
/*==============================================================*/
create table t_erp_hotel_room_suite_status_relation
(
   room_suite_id        int(11) not null comment '房间ID',
   suite_status_id      int(11) not null comment '房间状态ID',
   unique key room_suite_key (room_suite_id, suite_status_id)
);

alter table t_erp_hotel_room_suite_status_relation engine=innodb default charset=utf8 comment 'ERP酒店房型楼层房间状态(第二行) 关系';

drop table if exists t_erp_hotel_room_suite_status_log;

/*==============================================================*/
/* Table: t_erp_hotel_room_suite_status_log                     */
/*==============================================================*/
create table t_erp_hotel_room_suite_status_log
(
   id                   int not null auto_increment comment 'ID',
   room_suite_id        int(11) not null comment '房间ID',
   number               varchar(10) not null comment '房间号',
   old_room_status      varchar(10) not null comment '旧房态',
   new_room_status      varchar(10) not null comment '新房态',
   change_time          datetime not null comment '变更时间',
   operator_id          int(11) not null comment '操作人ID',
   operator_name        varchar(10) comment '操作人', 
   remark               varchar(140) comment '备注',
   primary key (id)
);

alter table t_erp_hotel_room_suite_status_log engine=innodb default charset=utf8 comment 'ERP酒店房态日志';

drop table if exists t_erp_hotel_room_calendar;

/*==============================================================*/
/* Table: t_erp_hotel_room_calendar                             */
/*==============================================================*/
create table t_erp_hotel_room_calendar
(
  id                    int(11) not null auto_increment comment '主键',
  room_id               int(11) not null comment '房型ID',
  price                 int(10) not null default 0 comment '价格',
  time                  datetime comment '时间日期',
  PRIMARY KEY (`id`),
  unique key `room_id_key` (`room_id`,`time`)
);

alter table t_erp_hotel_room_calendar engine=innodb default charset=utf8 comment '酒店房型日历价格 可根据日期调整价格';

/*drop table if exists t_erp_hotel_room_image;*/

/*==============================================================*/
/* Table: t_erp_hotel_room_image                                */
/*==============================================================*/
/*create table t_erp_hotel_room_image
(
   id                   int not null auto_increment comment 'ID',
   room_id              int(11) not null comment '房型ID',
   name                 varchar(100) comment '名称',
   url                  varchar(2000) comment '路径',
   type                 varchar(12) not null comment '类型',
   primary key (id)
);

alter table t_erp_hotel_room_image engine=innodb default charset=utf8 comment 'ERP酒店图片';*/

drop table if exists t_erp_hotel_room_installation;

/*==============================================================*/
/* Table: t_erp_hotel_room_installation                         */
/*==============================================================*/
create table t_erp_hotel_room_installation
(
   id                   int not null auto_increment comment 'ID',
   name                 varchar(20) not null unique comment '名称',
   logo                 varchar(2000) comment 'LOGO路径',
   if_use               int(1) not null default 0 comment '是否启用',
   primary key (id)
);

alter table t_erp_hotel_room_installation engine=innodb default charset=utf8 comment 'ERP酒店房型基础设施';

drop table if exists t_erp_hotel_room_installation_relation;

/*==============================================================*/
/* Table: t_erp_hotel_room_installation_relation                */
/*==============================================================*/
create table t_erp_hotel_room_installation_relation
(
   room_id              int(11) not null comment '房型ID',
   installation_id      int(11) not null comment '基础设施ID',
   unique key hir_room_key (hotel_id, installation_id)
);

alter table t_erp_hotel_room_installation_relation engine=innodb default charset=utf8 comment '酒店房型 - 基础设施';

drop table if exists t_erp_hotel_hours_room;

/*==============================================================*/
/* Table: t_erp_hotel_hours_room                                */
/*==============================================================*/
create table t_erp_hotel_hours_room
(
   id                   int not null auto_increment comment 'ID',
   room_id              int(11) not null comment '房型ID',
   type                 varchar(20) not null comment '房型类型',
   rule_name            varchar(50) not null comment '规则名称',
   duration             int(8) not null comment '时长(单位：min)',
   price                int(10) not null default 0 comment '价格',
   deposit              int(10) not null default 0 comment '押金',
   tome_out_price       int(10) not null default 0 comment '每超一小时价格',
   primary key (id)
);

alter table t_erp_hotel_hours_room engine=innodb default charset=utf8 comment '钟点房';

drop table if exists t_erp_hotel_long_time_room;

/*==============================================================*/
/* Table: t_erp_hotel_long_time_room                            */
/*==============================================================*/
create table t_erp_hotel_long_time_room
(
   id                   int not null auto_increment comment 'ID',
   hotel_id             int(11) not null comment '酒店ID',
   room_id              int(11) not null comment '房型ID',
   type                 varchar(20) not null comment '房型类型',
   rule_name            varchar(50) not null comment '规则名称',
   minimum_day          int(8) not null comment '最少入住天数',
   price                int(10) not null default 0 comment '价格',
   deposit              int(10) not null default 0 comment '押金',
   creator              varchar(20) comment '创建者',
   create_time          datetime comment '创建时间',
   primary key (id)
);

alter table t_erp_hotel_long_time_room engine=innodb default charset=utf8 comment '长包房';

/*==============================================================*//*==============================================================*//*==============================================================*/

drop table if exists t_erp_hotel_authorization;

/*==============================================================*/
/* Table: t_erp_hotel_authorization                             */
/*==============================================================*/
create table t_erp_hotel_authorization
(
   id                   int not null auto_increment comment 'ID',
   hotel_id             int(11) not null comment '酒店ID',
   shop_id              int(11) not null comment '门店ID',
   account_id           int(11) not null comment '账号ID',
   -- account_name         varchar(20) not null comment '(账号)核销员名称',
   -- account_phone        varchar(20) not null comment '(账号)核销员电话',
   function_id          int(11) not null comment '功能ID',
   creator              varchar(20) comment '创建者',
   create_time          datetime comment '创建时间',
   primary key (id),
   unique key authorization_key (hotel_id, shop_id, account_id, function_id)
);

alter table t_erp_hotel_authorization engine=innodb default charset=utf8 comment '授权人-功能-关系'; 

drop table if exists t_erp_hotel_function;

/*==============================================================*/
/* Table: t_erp_hotel_function                                  */
/*==============================================================*/
create table t_erp_hotel_function
(
   id                   int not null auto_increment comment 'ID',
   function_name        varchar(20) not null comment '功能名称',
   description          varchar(140) not null comment '功能描述',
   model                int(1) not null comment '模块',
   primary key (id)
);

alter table t_erp_hotel_function engine=innodb default charset=utf8 comment '授权功能';  

/*drop table if exists t_erp_hotel_authorization_function_relation;*/

/*==============================================================*/
/* Table: t_erp_hotel_authorization_function_relation           */
/*==============================================================*/
/*create table t_erp_hotel_authorization_function_relation
(
   author_id            int(11) not null comment '用户ID',
   function_id          int(11) not null comment '功能ID',
   unique key author_function_key (author_id, function_id)
);

alter table t_erp_hotel_authorization_function_relation engine=innodb default charset=utf8 comment '授权功能';  */

/*==============================================================*//*==============================================================*//*==============================================================*/

drop table if exists t_erp_hotel_food;

/*==============================================================*/
/* Table: t_erp_hotel_food                                      */
/*==============================================================*/
create table t_erp_hotel_food
(
   id                   int not null auto_increment comment 'ID',
   hotel_id             int(11) not null comment '酒店ID',
   name                 varchar(50) not null comment '菜品名称',
   -- type                 int(1) not null comment '菜式类型 1:早餐 2:午餐 3:晚餐 4:宵夜',
   breakfast            int(1) default 0 comment '早餐',
   lunch                int(1) default 0 comment '午餐',
   dinner               int(1) default 0 comment '晚餐',
   supper               int(1) default 0 comment '宵夜',
   provide_from         int(1) not null comment '菜品提供方 1:本酒店 2:合作方',
   company_name         varchar(50) comment '合作方名称',
   order_phone          varchar(20) comment '新订单接受电话',
   food_price           int(10) not null default 0 comment '菜品单价',
   food_deliver_time    int(8) not null comment '配送时间 分钟为单位',
   if_use               int(1) not null default 0 comment '是否启用',
   creator              varchar(20) unique comment '创建者',
   create_time          datetime comment '创建时间',
   update_time          timestamp default now() on update CURRENT_TIMESTAMP comment '更新时间',
   primary key (id)
);

alter table t_erp_hotel_food engine=innodb default charset=utf8 comment 'ERP酒店菜品';

/*drop table if exists t_erp_hotel_food_image;*/

/*==============================================================*/
/* Table: t_erp_hotel_food_image                                */
/*==============================================================*/
/*create table t_erp_hotel_food_image
(
   id                   int not null auto_increment comment 'ID',
   food_id              int(11) not null comment '酒店菜品ID',
   url                  varchar(2000) comment '路径',
   type                 varchar(12) not null comment '类型',
   primary key (id)
);

alter table t_erp_hotel_food_image engine=innodb default charset=utf8 comment 'ERP酒店菜品-图片';*/

/*==============================================================*//*==============================================================*//*==============================================================*/

drop table if exists t_erp_hotel_cash_pledge;

/*==============================================================*/
/* Table: t_erp_hotel_cash_pledge                               */
/*==============================================================*/
create table t_erp_hotel_cash_pledge
(
   id                   int(11) not null auto_increment comment 'id',
   bus_id               int(11) comment '商家id',
   member_id            int(11) comment '会员id',
   hotel_id             int(11) not null comment '酒店id',
   book_name            varchar(20) comment '预订人',
   book_phone           varchar(20) default null comment '电话',
   cash_pledge          int(10) not null default 0 comment '押金金额',
   status               int(4) not null comment '押金状态',
   type                 varchar(10) default '入住押金' comment '押金类型，交易类型',
   refunds              int(10) comment '退款金额',
   refunds_explain      varchar(200) comment '退款说明',
   createtime           varchar(30) not null comment '创建时间',
   order_id             varchar(30) not null comment '订单id',
   lookstatus           int(4) not null comment '是否查看',
   primary key (id),
   unique key `oid_que` (`order_id`)
);

alter table t_erp_hotel_cash_pledge engine=innodb default charset=utf8 comment 'ERP酒店-押金';

drop table if exists t_erp_hotel_room_order;

/*==============================================================*/
/* Table: t_erp_hotel_room_order                                */
/*==============================================================*/
create table t_erp_hotel_room_order
(
   id                   int(11) not null auto_increment comment 'id',
   hotel_id             int(11) not null comment '酒店ID',
   bus_id               int(11) comment '商家id',
   /*member_id            int(11) comment '会员id',
   book_name            varchar(20) not null comment '预订人',
   book_phone           varchar(20) not null comment '电话',
   book_gender          varchar(20) comment '性别',
   document_type        varchar(20) not null comment '证件类型',
   document_type_value  varchar(50) not null comment '证件号码',*/
   check_in_mode        int(1) not null comment '入住方式(0：散客，1：协议单位， 2：团队)',
   check_in_standard    int(1) not null comment '入住标准(0：全天房，1：钟点房，2：长包房)', 
   price                int(10) not null default 0 comment '订单价格',
   quantity             int(4) not null comment '数量',
   if_cash_pledge       int(1) not null comment '是否免押金',
   cash_pledge          int(10) not null default 0 comment '订单押金',
   order_number         varchar(50) not null comment '订单号',
   check_in_time        datetime comment '入住时间',
   check_out_time       datetime comment '离店时间',
   create_time          datetime not null comment '创建时间',
   pay_type             int(4) not null comment '支付方式 1:在线支付 2:到店支付 3:储值卡支付 4:支付宝 5:银行卡 6:现金',
   pay_time             datetime comment '支付时间时间',
   pay_status           int(1) not null comment '支付状态 0:未支付 1:已支付 2:挂账, 3:已退款',
   order_status         int(1) not null comment '订单状态 0:处理中 1:已确认 2:已取消 3:已入住 4:已完成',
   remark               varchar(140) comment '备注',
   source               varchar(20) comment '来源',
   available            int(1) default 1 comment '是否可用(0:false, 1:true)',
   primary key (id)
);

alter table t_erp_hotel_room_order engine=innodb default charset=utf8 comment 'ERP酒店-房间-订单';

drop table if exists t_erp_hotel_room_order_guest;

/*==============================================================*/
/* Table: t_erp_hotel_room_order_guest                          */
/*==============================================================*/
create table t_erp_hotel_room_order_guest
(
   id                   int(11) not null auto_increment comment 'id',
   order_id             int(11) not null comment '订单ID',
   member_id            int(11) comment '会员ID',
   book_name            varchar(20) not null comment '预订人',
   book_phone           varchar(20) not null comment '电话',
   book_gender          varchar(20) comment '性别',
   document_type        varchar(20) not null comment '证件类型',
   document_type_value  varchar(50) not null comment '证件号码',
   guest_type           int(1) not null comment '客人类型(0：主，1：从)',
   primary key (id)
);

alter table t_erp_hotel_room_order_guest engine=innodb default charset=utf8 comment 'ERP酒店-房间-订单-客人';

drop table if exists t_erp_hotel_room_order_suite;

/*==============================================================*/
/* Table: t_erp_hotel_room_order_suite                          */
/*==============================================================*/
create table t_erp_hotel_room_order_suite
(
   id                   int(11) not null auto_increment comment 'id',
   order_id             int(11) not null comment '订单ID',
   room_suite_id        int(11) comment '房间ID',
   /*activity_id          int(11) comment '房价活动ID',*/
   check_in_mode        int(1) not null comment '入住方式(0：散客，1：协议单位， 2：团队)',
   price                int(10) not null default 0 comment '房价',
   check_in_days        int(8) not null comment '入住天数',
   remark               varchar(140) comment '备注',
   primary key (id)
);

alter table t_erp_hotel_room_order_suite engine=innodb default charset=utf8 comment 'ERP酒店-房间-订单-房间';

drop table if exists t_erp_hotel_room_order_member_card;

/*==============================================================*/
/* Table: t_erp_hotel_room_order_member_card                    */
/*==============================================================*/
create table t_erp_hotel_room_order_member_card
(
   id                   int(11) not null auto_increment comment 'id',
   order_id             int(11) not null comment '订单ID',
   cardVolumeCode       varchar(20) comment '卡券code',
   fenbi                int(10) default '0' comment '粉币',
   primary key (id)
);

alter table t_erp_hotel_room_order_member_card engine=innodb default charset=utf8 comment 'ERP酒店-房间-订单-会员卡使用情况';

drop table if exists t_erp_hotel_room_order_protocol_unit;

/*==============================================================*/
/* Table: t_erp_hotel_room_order_protocol_unit                  */
/*==============================================================*/
create table t_erp_hotel_room_order_protocol_unit
(
   id                   int(11) not null auto_increment comment 'id',
   order_id             int(11) not null comment '订单ID',
   protocol_unit_id     int(11) comment '协议单位ID',
   protocol_unit_name   varchar(20) comment '协议单位名称',
   protocol_set_id      int(11) comment '协议套餐ID',
   protocol_set_price   int(10) not null default 0 comment '协议套餐价格',
   primary key (id)
);

alter table t_erp_hotel_room_order_protocol_unit engine=innodb default charset=utf8 comment 'ERP酒店-房间-订单-协议单位';

drop table if exists t_erp_hotel_invoice_category;

/*==============================================================*/
/* Table: t_erp_hotel_invoice_category                          */
/*==============================================================*/
create table t_erp_hotel_invoice_category
(
   id                   int(11) not null auto_increment comment 'id',
   name                 varchar(20) not null comment '发票类目名',
   primary key (id)
);

alter table t_erp_hotel_invoice_category engine=innodb default charset=utf8 comment 'ERP酒店-发票类目';

drop table if exists t_erp_hotel_room_order_check_out;

/*==============================================================*/
/* Table: t_erp_hotel_room_order_check_out                      */
/*==============================================================*/
create table t_erp_hotel_room_order_check_out
(
   id                   int(11) not null auto_increment comment 'id',
   order_id             int(11) not null comment '订单ID',
   member_id            int(11) not null comment '会员ID',
   room_suite_id        int(11) not null comment '房间ID',
   invoice_header       varchar(50) not null comment '发票抬头',
   invoice_category_id  int(11) not null comment '发票类目ID',
   primary key (id)
);

alter table t_erp_hotel_room_order_check_out engine=innodb default charset=utf8 comment 'ERP酒店-房间-订单-退房记录';

drop table if exists t_erp_hotel_room_order_consumption;

/*==============================================================*/
/* Table: t_erp_hotel_room_order_consumption                    */
/*==============================================================*/
create table t_erp_hotel_room_order_consumption
(
   id                   int(11) not null auto_increment comment 'id',
   order_id             int(11) not null comment '订单ID',
   member_id            int(11) null comment '会员ID',
   room_suite_id        int(11) not null comment '房间ID',
   name                 varchar(50) not null comment '物品名称',
   type                 int(1) not null comment '物品类型(损坏：0， 消费：1)',
   price                int(10) not null default 0 comment '物品价格',
   count                int(5) not null comment '物品数量',
   create_time          datetime not null comment '创建时间',
   primary key (id)
);

alter table t_erp_hotel_room_order_consumption engine=innodb default charset=utf8 comment 'ERP酒店-房间-订单-损坏&消费';

/*==============================================================*//*==============================================================*//*==============================================================*/

drop table if exists t_erp_hotel_food_order;

/*==============================================================*/
/* Table: t_erp_hotel_food_order                                */
/*==============================================================*/
create table t_erp_hotel_food_order
(
   id                   int(11) not null auto_increment comment 'id',
   hotel_id             int(11) not null comment '酒店ID',
   bus_id               int(11) comment '商家id',
   number               varchar(10) not null comment '房间号',
   price                int(10) not null default 0 comment '订单价格',
   order_number         varchar(50) not null comment '订单号',
   book_name            varchar(20) not null comment '预订人',
   book_phone           varchar(20) not null comment '电话',
   create_time          datetime not null comment '创建时间',
   arrival_time         datetime not null comment '预计到达时间',
   pay_type             int(4) not null comment '支付方式 1:在线支付 2:到店支付 3:储值卡支付 4:支付宝 5:银行卡 6:现金',
   pay_time             datetime comment '支付时间时间',
   pay_status           int(1) not null comment '支付状态 0:未支付 1:已支付 2:挂账 3:退款',
   order_status         int(1) not null comment '订单状态 0:处理中 1:已确认 2:已取消 3:已入住(不需要) 4:已完成',
   remark               varchar(140) comment '备注',
   source               varchar(20) comment '来源 ',
   available            int(1) default 1 comment '是否可用(0:false, 1:true)',
   primary key (id)
);

alter table t_erp_hotel_food_order engine=innodb default charset=utf8 comment 'ERP酒店-餐饮-订单';

drop table if exists t_erp_hotel_food_order_relation;

/*==============================================================*/
/* Table: t_erp_hotel_food_order_relation                       */
/*==============================================================*/
create table t_erp_hotel_food_order_relation
(
   order_id             int(11) not null comment '订单ID',
   food_id              int(11) not null comment '酒店菜品ID',
   unique key food_order_key (order_id, food_id)
);

alter table t_erp_hotel_food_order_relation engine=innodb default charset=utf8 comment 'ERP酒店-餐饮-订单-列表';

/*==============================================================*//*==============================================================*//*==============================================================*/

drop table if exists t_erp_hotel_shift_records;

/*==============================================================*/
/* Table: t_erp_hotel_shift_records                             */
/*==============================================================*/
create table t_erp_hotel_shift_records
(
   id                   int(11) not null auto_increment comment 'id',
   account_id           int(11) not null comment '账号ID(当前账号)',
   man_id               int(11) not null comment '交班人ID(交班前者账号)',
   man_name             varchar(20) comment '交班人姓名(交班前者账号)',
   price                int(10) not null default 0 comment '交班金额(现金部分)',
   shift_time           datetime comment '交班时间',
   primary key (id)
);

alter table t_erp_hotel_shift_records engine=innodb default charset=utf8 comment 'ERP酒店-交班记录';

drop table if exists t_erp_hotel_allocate_change;

/*==============================================================*/
/* Table: t_erp_hotel_allocate_change                           */
/*==============================================================*/
create table t_erp_hotel_allocate_change
(
   id                   int(11) not null auto_increment comment 'id',
   account_id           int(11) not null comment '账号ID(当前账号)',
   account_name         varchar(20) comment '账号姓名(当前账号)',
   man_id               int(11) not null comment '被分配人ID(分配后者账号)',
   man_name             varchar(20) comment '被分配人姓名(分配后者账号)',
   allocate_price       int(10) not null default 0 comment '分配金额',
   allocate_time        datetime comment '分配时间',
   primary key (id)
);

alter table t_erp_hotel_allocate_change engine=innodb default charset=utf8 comment 'ERP酒店-分配零钱';

/*==============================================================*//*==============================================================*//*==============================================================*/

drop table if exists t_erp_hotel_protocol_unit;

/*==============================================================*/
/* Table: t_erp_hotel_protocol_unit                             */
/*==============================================================*/
create table t_erp_hotel_protocol_unit
(
   id                   int(11) not null auto_increment comment 'id',
   name                 varchar(50) not null comment '单位名称',
   contact_person       varchar(20) not null comment '联系人',
   phone                varchar(20) not null comment '手机号码',
   tel                  varchar(20) not null comment '电话号码',
   protocol_number      varchar(20) not null comment '合同号',
   hotel_id             int(11) not null comment '酒店ID',
   hotel_name           varchar(200) not null comment '酒店名',
   remark               varchar(140) comment '备注',
   set_id               int(11) not null comment '套餐ID',
   create_time          datetime comment '创建时间',
   operator_id          int(11) not null comment '操作人ID',
   operator_name        varchar(10) comment '操作人', 
   if_check             int(1) not null comment '审核',
   account_id           int(11) comment '账号ID',
   type                 int(1) not null comment '0:协议单位, 1:中介',
   status               int(1) not null comment '是否可用',
   primary key (id)
);

alter table t_erp_hotel_protocol_unit engine=innodb default charset=utf8 comment 'ERP酒店-协议单位&中介(需创建账号)';

/*drop table if exists t_erp_hotel_intermediary;*/

/*==============================================================*/
/* Table: t_erp_hotel_intermediary                              */
/*==============================================================*/
/*create table t_erp_hotel_intermediary
(
   id                   int(11) not null auto_increment comment 'id',
   name                 varchar(50) not null comment '中介名称',
   contact_person       varchar(20) not null comment '联系人',
   phone                varchar(20) not null comment '手机号码',
   tel                  varchar(20) not null comment '电话号码',
   protocol_number      varchar(20) not null comment '合同号',
   remark               varchar(140) comment '备注',
   set_id               int(11) not null comment '套餐ID',
   create_time          datetime comment '创建时间',
   operator_id          int(11) not null comment '操作人ID',
   operator_name        varchar(10) comment '操作人', 
   if_check             int(1) not null comment '审核',
   account_id           int(11) comment '账号ID',
   primary key (id)
);

alter table t_erp_hotel_intermediary engine=innodb default charset=utf8 comment 'ERP酒店-中介(需创建账号)';*/

drop table if exists t_erp_hotel_classes;

/*==============================================================*/
/* Table: t_erp_hotel_classes                                   */
/*==============================================================*/
create table t_erp_hotel_classes
(
   id                   int(11) not null auto_increment comment 'id',
   name                 varchar(50) not null comment '班次名称',
   remark               varchar(140) comment '备注',
   start_time           datetime comment '开始时间',
   end_time             datetime comment '结束时间',
   create_time          datetime comment '创建时间',
   operator_id          int(11) not null comment '操作人ID',
   operator_name        varchar(10) comment '操作人', 
   primary key (id)
);

alter table t_erp_hotel_classes engine=innodb default charset=utf8 comment 'ERP酒店-班次';

drop table if exists t_erp_hotel_set;

/*==============================================================*/
/* Table: t_erp_hotel_set                                       */
/*==============================================================*/
create table t_erp_hotel_set
(
   id                   int(11) not null auto_increment comment 'id',
   name                 varchar(50) not null comment '套餐名称',
   remark               varchar(140) comment '备注',
   create_time          datetime comment '创建时间',
   operator_id          int(11) not null comment '操作人ID',
   operator_name        varchar(10) comment '操作人', 
   type                 int(1) not null comment '套餐类型(0：协议单位， 1：中介)',
   primary key (id)
);

alter table t_erp_hotel_set engine=innodb default charset=utf8 comment 'ERP酒店-套餐';

drop table if exists t_erp_hotel_set_room;

/*==============================================================*/
/* Table: t_erp_hotel_set_room                                  */
/*==============================================================*/
create table t_erp_hotel_set_room
(
   set_id               int(11) not null comment '房型ID',
   room_id              int(11) not null comment '套餐ID',
   cooperation_price    int(10) not null default 0 comment '合作价格',
   unique key set_room_key (set_id, room_id)
);

alter table t_erp_hotel_set_room engine=innodb default charset=utf8 comment 'ERP酒店-套餐(详情)-房型';

drop table if exists t_erp_hotel_debts;

/*==============================================================*/
/* Table: t_erp_hotel_debts                                     */
/*==============================================================*/
create table t_erp_hotel_debts
(
   id                   int(11) not null auto_increment comment 'id',
   debts_price          int(10) not null default 0 comment '挂账金额',
   order_id             int(11) not null comment '订单号',
   /* 还没好 */
   debts_type           int(1) not null comment '0：对内，1：对外',
   if_repayment         int(1) not null comment '是否还款',
   create_time          datetime comment '创建时间',
   operator_id          int(11) not null comment '操作人ID',
   operator_name        varchar(10) comment '操作人', 
   primary key (id)
);

alter table t_erp_hotel_debts engine=innodb default charset=utf8 comment 'ERP酒店-挂账';

/*==============================================================*//*==============================================================*//*==============================================================*/

drop table if exists t_erp_hotel_activity;

/*==============================================================*/
/* Table: t_erp_hotel_activity                                  */
/*==============================================================*/
create table t_erp_hotel_activity
(
   id                   int(11) not null auto_increment,
   bus_id               int(11) not null comment '商家id',
   hotel_id             int(11) not null comment '所属酒店id',
   activity_name        varchar(20) not null comment '活动名称',
   activity_stime       datetime not null comment '生效时间 始',
   activity_etime       datetime not null comment '生效时间 末',
   type                 int(4) not null comment '类型（0=秒杀, 1=团购, 2=特价房, 3=时租房）',
   is_room_limit        int(4) comment '是否开启房间限购',
   room_limit           int(4) comment '房间限购数（间/人）',
   is_vipcard           int(4) not null comment '是否关联会员卡',
   is_cardvolume        int(4) not null comment '是否关联卡券',
   rule                 varchar(200) comment '规则',
   is_room_count        int(4) comment '是否显示房间剩余数',
   room_count           int(8) comment '当房剩余 room_count  间时显',
   activity_status      int(4) not null comment '活动状态(0=未开始, 1=进行中, 2=已结束)',
   createtime           datetime not null comment '创建时间',
   live_stime           datetime comment '居住开始时间',
   live_etime           datetime comment '居住结束时间',
   grouplowest          int(5) default '0' comment '团购最低间数',
   grouplowesttip       varchar(50) default '' comment '当达不到最低间数提示',
   limit_hour           int(3) default '0' comment '时租房使用时间',
   whenrentstime        datetime comment '时租房可预订起始时间',
   whenrentetime        datetime comment '时租房可预订结束时间',
   update_time          timestamp default now() on update CURRENT_TIMESTAMP comment '更新时间',
   primary key (`id`)
);

alter table t_erp_hotel_activity engine=innodb default charset=utf8 comment 'ERP酒店-活动';

-- drop table if exists t_erp_hotel_activity_room;

/*==============================================================*/
/* Table: t_erp_hotel_activity_room                             */
/*==============================================================*/
-- create table t_erp_hotel_activity_room
-- (
--    id                   int(11) NOT NULL AUTO_INCREMENT,
--    activity_id          int(11) DEFAULT NULL COMMENT '活动id',
--    room_id              int(11) DEFAULT NULL COMMENT '房型ID',
--    price                int(10) not null default 0 comment '活动价格',
--    primary key (`id`)
-- );

-- alter table t_erp_hotel_activity_room engine=innodb default charset=utf8 comment 'ERP酒店-活动-房型';

drop table if exists t_erp_hotel_activity_room_suite;

/*==============================================================*/
/* Table: t_erp_hotel_activity_room_suite                       */
/*==============================================================*/
create table t_erp_hotel_activity_room_suite
(
   id                   int(11) NOT NULL AUTO_INCREMENT,
   activity_id          int(11) DEFAULT NULL COMMENT '活动id',
   room_id              int(11) DEFAULT NULL COMMENT '房型ID',
   suite_id             int(11) DEFAULT NULL COMMENT '房间ID',
   price                int(10) not null default 0 comment '活动价格',
   unique key activity_room_suite_key (activity_id, room_id, suite_id),
   primary key (`id`)
);

alter table t_erp_hotel_activity_room_suite engine=innodb default charset=utf8 comment 'ERP酒店-活动-房型-房间';


























=======
/* 全统一 | 0：false | 1：true */

DROP TABLE IF EXISTS t_erp_hotel;

/*==============================================================*/
/* Table: t_erp_hotel                                           */
/*==============================================================*/
CREATE TABLE t_erp_hotel
(
  id                 INT          NOT NULL AUTO_INCREMENT
  COMMENT 'ID',
  bus_id             INT(11)      NOT NULL
  COMMENT '商家ID',
  shop_id            INT(11)      NOT NULL
  COMMENT '门店ID',
  name               VARCHAR(200) NOT NULL
  COMMENT '名称',
  introduction       VARCHAR(2000) COMMENT '介绍',
  phone              VARCHAR(12)  NOT NULL
  COMMENT '电话',
  address            VARCHAR(200) NOT NULL
  COMMENT '地址',
  if_reserve_man     INT(1)       NOT NULL DEFAULT 0
  COMMENT '是否收集预约人',
  if_reserve_phone   INT(1)       NOT NULL DEFAULT 0
  COMMENT '是否收集预约电话',
  if_remark          INT(1)       NOT NULL DEFAULT 0
  COMMENT '是否收集备注',
  pay_mode           INT(1)       NOT NULL DEFAULT 0
  COMMENT '支付方式(1：在线支付 | 2：到店支付 | 3：1&2)',
  if_sms             INT(1)       NOT NULL DEFAULT 0
  COMMENT '是否开启短信通知',
  sms_phone          VARCHAR(12) COMMENT '接受信息手机号',
  if_check_out       INT(1)       NOT NULL DEFAULT 0
  COMMENT '是否开启一键退房',
  if_food            INT(1)       NOT NULL DEFAULT 0
  COMMENT '是否开启餐饮',
  if_bulletin        INT(1)       NOT NULL DEFAULT 0
  COMMENT '是否开启公告',
  bulletin           VARCHAR(2000) COMMENT '公告',
  if_remnant_room    INT(1)       NOT NULL DEFAULT 0
  COMMENT '是否显示剩余房型',
  if_continue        INT(1)       NOT NULL DEFAULT 0
  COMMENT '是否显示一键续住',
  if_confirm_info    INT(1)       NOT NULL DEFAULT 0
  COMMENT '是否确认订单信息功能',
  if_breakfast       INT(1)       NOT NULL DEFAULT 0
  COMMENT '是否开启早餐券',
  breakfast_quantity INT(3) COMMENT '默认早餐券数量',
  if_activity_prices INT(1)       NOT NULL DEFAULT 0
  COMMENT '是否开启房价活动',
  if_free_deposit    INT(1)       NOT NULL DEFAULT 0
  COMMENT '是否开启会员免押金',
  if_last_check_out  INT(1)       NOT NULL DEFAULT 0
  COMMENT '是否开启会员最晚退房时间',
  creator            VARCHAR(20) UNIQUE
  COMMENT '创建者',
  create_time        DATETIME COMMENT '创建时间',
  update_time        TIMESTAMP             DEFAULT now() ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间',
  PRIMARY KEY (id)
);

ALTER TABLE t_erp_hotel
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT 'ERP酒店主表';
/*{busId:1, shopId:1, name:'jiudian', phone:'123', address:'dizhi', introduction:'jianjie'}*/

DROP TABLE IF EXISTS t_erp_hotel_image;

/*==============================================================*/
/* Table: t_erp_hotel_image                                     */
/*==============================================================*/
CREATE TABLE t_erp_hotel_image
(
  id            INT         NOT NULL AUTO_INCREMENT
  COMMENT 'ID',
  subjection_id INT(11)     NOT NULL
  COMMENT '隶属ID',
  name          VARCHAR(100) COMMENT '名称',
  url           VARCHAR(2000) COMMENT '路径',
  type          VARCHAR(12) NOT NULL
  COMMENT '类型',
  subjection    INT(2)      NOT NULL
  COMMENT '隶属(0:酒店, 1:房型, 2:菜品, 等等)',
  PRIMARY KEY (id)
);

ALTER TABLE t_erp_hotel_image
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT 'ERP酒店图片';

DROP TABLE IF EXISTS t_erp_hotel_installation;

/*==============================================================*/
/* Table: t_erp_hotel_installation                              */
/*==============================================================*/
CREATE TABLE t_erp_hotel_installation
(
  id     INT         NOT NULL AUTO_INCREMENT
  COMMENT 'ID',
  name   VARCHAR(20) NOT NULL UNIQUE
  COMMENT '名称',
  logo   VARCHAR(2000) COMMENT 'LOGO路径',
  if_use INT(1)      NOT NULL DEFAULT 0
  COMMENT '是否启用',
  type   INT(1)      NOT NULL DEFAULT 0
  COMMENT '类型(0:酒店)',
  PRIMARY KEY (id)
);

ALTER TABLE t_erp_hotel_installation
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT 'ERP酒店基础设施';

DROP TABLE IF EXISTS t_erp_hotel_installation_relation;

/*==============================================================*/
/* Table: t_erp_hotel_installation_relation                     */
/*==============================================================*/
CREATE TABLE t_erp_hotel_installation_relation
(
  hotel_id        INT(11) NOT NULL
  COMMENT '酒店ID',
  installation_id INT(11) NOT NULL
  COMMENT '基础设施ID',
  UNIQUE KEY hir_key (hotel_id, installation_id)
);

ALTER TABLE t_erp_hotel_installation_relation
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT '酒店 - 基础设施';

DROP TABLE IF EXISTS t_erp_hotel_member_deposit_relation;

/*==============================================================*/
/* Table: t_erp_hotel_member_deposit_relation                   */
/*==============================================================*/
CREATE TABLE t_erp_hotel_member_deposit_relation
(
  hotel_id        INT(11) NOT NULL
  COMMENT '酒店ID',
  vip_level       INT(3)  NOT NULL
  COMMENT '会员等级',
  if_free_deposit INT(1)  NOT NULL DEFAULT 0
  COMMENT '是否免押金'
);

ALTER TABLE t_erp_hotel_member_deposit_relation
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT '酒店 - 会员免押设置';

DROP TABLE IF EXISTS t_erp_hotel_member_check_out_relation;

/*==============================================================*/
/* Table: t_erp_hotel_member_check_out_relation                 */
/*==============================================================*/
CREATE TABLE t_erp_hotel_member_check_out_relation
(
  hotel_id       INT(11) NOT NULL
  COMMENT '酒店ID',
  vip_level      INT(3)  NOT NULL
  COMMENT '会员等级',
  last_check_out DATETIME COMMENT '最晚退房时间'
);

ALTER TABLE t_erp_hotel_member_check_out_relation
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT '酒店 - 会员最晚退房设置';

/*==============================================================*//*==============================================================*//*==============================================================*/

DROP TABLE IF EXISTS t_erp_hotel_room;

/*==============================================================*/
/* Table: t_erp_hotel_room                                      */
/*==============================================================*/
CREATE TABLE t_erp_hotel_room
(
  id                   INT         NOT NULL AUTO_INCREMENT
  COMMENT 'ID',
  hotel_id             INT(11)     NOT NULL
  COMMENT '酒店ID',
  type                 VARCHAR(20) NOT NULL
  COMMENT '房型类型',
  total                INT(3)      NOT NULL DEFAULT 0
  COMMENT '房型总数',
  introduction         VARCHAR(140) COMMENT '简介',
  price                INT(10)     NOT NULL DEFAULT 0
  COMMENT '价格',
  deposit              INT(10)     NOT NULL DEFAULT 0
  COMMENT '押金',
  if_discount_price    INT(1)      NOT NULL DEFAULT 0
  COMMENT '是否启用折扣价',
  discount_price       INT(10) COMMENT '折扣价',
  if_weekend_price     INT(1)      NOT NULL DEFAULT 0
  COMMENT '是否启用周末价',
  weekend_price        INT(10) COMMENT '周末价',
  if_monday_price      INT(1)      NOT NULL DEFAULT 0
  COMMENT '是否启用周一价',
  monday_price         INT(10) COMMENT '周末价',
  if_tuesday_price     INT(1)      NOT NULL DEFAULT 0
  COMMENT '是否启用周二价',
  tuesday_price        INT(10) COMMENT '周末价',
  if_wednesday_price   INT(1)      NOT NULL DEFAULT 0
  COMMENT '是否启用周三价',
  wednesday_price      INT(10) COMMENT '周末价',
  if_thursday_price    INT(1)      NOT NULL DEFAULT 0
  COMMENT '是否启用周四价',
  thursday_price       INT(10) COMMENT '周末价',
  if_sunday_price      INT(1)      NOT NULL DEFAULT 0
  COMMENT '是否启用周日价',
  sunday_price         INT(10) COMMENT '周末价',
  -- description          varchar(2000) comment '相关说明',
  team_price           INT(10) COMMENT '团队价',
  team_price_effective INT(7) COMMENT '团队价生效数量',
  if_use               INT(1)      NOT NULL DEFAULT 0
  COMMENT '是否启用',
  creator              VARCHAR(20) UNIQUE
  COMMENT '创建者',
  create_time          DATETIME COMMENT '创建时间',
  update_time          TIMESTAMP            DEFAULT now() ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间',
  PRIMARY KEY (id)
);

ALTER TABLE t_erp_hotel_room
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT 'ERP酒店房型';

-- drop table if exists t_erp_hotel_room_floor;

/*==============================================================*/
/* Table: t_erp_hotel_room_floor                                */
/*==============================================================*/
-- create table t_erp_hotel_room_floor
-- (
--    id                   int not null auto_increment comment 'ID',
--    room_id              int(11) not null comment '房型ID',
--    floor                varchar(10) not null comment '楼层',
--    primary key (id)
-- );

-- alter table t_erp_hotel_room_floor engine=innodb default charset=utf8 comment 'ERP酒店房型楼层';

DROP TABLE IF EXISTS t_erp_hotel_room_suite;

/*==============================================================*/
/* Table: t_erp_hotel_room_suite                                */
/*==============================================================*/
CREATE TABLE t_erp_hotel_room_suite
(
  id          INT         NOT NULL AUTO_INCREMENT
  COMMENT 'ID',
  room_id     INT(11)     NOT NULL
  COMMENT '房型ID',
  floor       VARCHAR(10) NOT NULL
  COMMENT '楼层',
  number      VARCHAR(10) NOT NULL
  COMMENT '房间号',
  status      VARCHAR(10) COMMENT '房间状态(第一行)',
  room_status VARCHAR(10) COMMENT '房态',
  remark      VARCHAR(140) COMMENT '备注',
  PRIMARY KEY (id)
);

ALTER TABLE t_erp_hotel_room_suite
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT 'ERP酒店房型楼层房间';

DROP TABLE IF EXISTS t_erp_hotel_room_suite_status;

/*==============================================================*/
/* Table: t_erp_hotel_room_suite_status                         */
/*==============================================================*/
CREATE TABLE t_erp_hotel_room_suite_status
(
  id   INT         NOT NULL AUTO_INCREMENT
  COMMENT 'ID',
  logo VARCHAR(1000) COMMENT 'logo url',
  name VARCHAR(10) NOT NULL
  COMMENT ' 状态名称',
  PRIMARY KEY (id)
);

ALTER TABLE t_erp_hotel_room_suite_status
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT 'ERP酒店房型楼层房间状态(第二行)';

DROP TABLE IF EXISTS t_erp_hotel_room_suite_status_relation;

/*==============================================================*/
/* Table: t_erp_hotel_room_suite_status_relation                */
/*==============================================================*/
CREATE TABLE t_erp_hotel_room_suite_status_relation
(
  room_suite_id   INT(11) NOT NULL
  COMMENT '房间ID',
  suite_status_id INT(11) NOT NULL
  COMMENT '房间状态ID',
  UNIQUE KEY room_suite_key (room_suite_id, suite_status_id)
);

ALTER TABLE t_erp_hotel_room_suite_status_relation
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT 'ERP酒店房型楼层房间状态(第二行) 关系';

DROP TABLE IF EXISTS t_erp_hotel_room_suite_status_log;

/*==============================================================*/
/* Table: t_erp_hotel_room_suite_status_log                     */
/*==============================================================*/
CREATE TABLE t_erp_hotel_room_suite_status_log
(
  id              INT         NOT NULL AUTO_INCREMENT
  COMMENT 'ID',
  room_suite_id   INT(11)     NOT NULL
  COMMENT '房间ID',
  number          VARCHAR(10) NOT NULL
  COMMENT '房间号',
  old_room_status VARCHAR(10) NOT NULL
  COMMENT '旧房态',
  new_room_status VARCHAR(10) NOT NULL
  COMMENT '新房态',
  change_time     DATETIME    NOT NULL
  COMMENT '变更时间',
  operator_id     INT(11)     NOT NULL
  COMMENT '操作人ID',
  operator_name   VARCHAR(10) COMMENT '操作人',
  remark          VARCHAR(140) COMMENT '备注',
  PRIMARY KEY (id)
);

ALTER TABLE t_erp_hotel_room_suite_status_log
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT 'ERP酒店房态日志';

DROP TABLE IF EXISTS t_erp_hotel_room_calendar;

/*==============================================================*/
/* Table: t_erp_hotel_room_calendar                             */
/*==============================================================*/
CREATE TABLE t_erp_hotel_room_calendar
(
  id      INT(11) NOT NULL AUTO_INCREMENT
  COMMENT '主键',
  room_id INT(11) NOT NULL
  COMMENT '房型ID',
  price   INT(10) NOT NULL DEFAULT 0
  COMMENT '价格',
  time    DATETIME COMMENT '时间日期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `room_id_key` (`room_id`, `time`)
);

ALTER TABLE t_erp_hotel_room_calendar
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT '酒店房型日历价格 可根据日期调整价格';

/*drop table if exists t_erp_hotel_room_image;*/

/*==============================================================*/
/* Table: t_erp_hotel_room_image                                */
/*==============================================================*/
/*create table t_erp_hotel_room_image
(
   id                   int not null auto_increment comment 'ID',
   room_id              int(11) not null comment '房型ID',
   name                 varchar(100) comment '名称',
   url                  varchar(2000) comment '路径',
   type                 varchar(12) not null comment '类型',
   primary key (id)
);

alter table t_erp_hotel_room_image engine=innodb default charset=utf8 comment 'ERP酒店图片';*/

DROP TABLE IF EXISTS t_erp_hotel_room_installation;

/*==============================================================*/
/* Table: t_erp_hotel_room_installation                         */
/*==============================================================*/
CREATE TABLE t_erp_hotel_room_installation
(
  id     INT         NOT NULL AUTO_INCREMENT
  COMMENT 'ID',
  name   VARCHAR(20) NOT NULL UNIQUE
  COMMENT '名称',
  logo   VARCHAR(2000) COMMENT 'LOGO路径',
  if_use INT(1)      NOT NULL DEFAULT 0
  COMMENT '是否启用',
  PRIMARY KEY (id)
);

ALTER TABLE t_erp_hotel_room_installation
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT 'ERP酒店房型基础设施';

DROP TABLE IF EXISTS t_erp_hotel_room_installation_relation;

/*==============================================================*/
/* Table: t_erp_hotel_room_installation_relation                */
/*==============================================================*/
CREATE TABLE t_erp_hotel_room_installation_relation
(
  room_id         INT(11) NOT NULL
  COMMENT '房型ID',
  installation_id INT(11) NOT NULL
  COMMENT '基础设施ID',
  UNIQUE KEY hir_room_key (hotel_id, installation_id)
);

ALTER TABLE t_erp_hotel_room_installation_relation
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT '酒店房型 - 基础设施';

DROP TABLE IF EXISTS t_erp_hotel_hours_room;

/*==============================================================*/
/* Table: t_erp_hotel_hours_room                                */
/*==============================================================*/
CREATE TABLE t_erp_hotel_hours_room
(
  id             INT         NOT NULL AUTO_INCREMENT
  COMMENT 'ID',
  room_id        INT(11)     NOT NULL
  COMMENT '房型ID',
  type           VARCHAR(20) NOT NULL
  COMMENT '房型类型',
  rule_name      VARCHAR(50) NOT NULL
  COMMENT '规则名称',
  duration       INT(8)      NOT NULL
  COMMENT '时长(单位：min)',
  price          INT(10)     NOT NULL DEFAULT 0
  COMMENT '价格',
  deposit        INT(10)     NOT NULL DEFAULT 0
  COMMENT '押金',
  tome_out_price INT(10)     NOT NULL DEFAULT 0
  COMMENT '每超一小时价格',
  PRIMARY KEY (id)
);

ALTER TABLE t_erp_hotel_hours_room
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT '钟点房';

DROP TABLE IF EXISTS t_erp_hotel_long_time_room;

/*==============================================================*/
/* Table: t_erp_hotel_long_time_room                            */
/*==============================================================*/
CREATE TABLE t_erp_hotel_long_time_room
(
  id          INT         NOT NULL AUTO_INCREMENT
  COMMENT 'ID',
  hotel_id    INT(11)     NOT NULL
  COMMENT '酒店ID',
  room_id     INT(11)     NOT NULL
  COMMENT '房型ID',
  type        VARCHAR(20) NOT NULL
  COMMENT '房型类型',
  rule_name   VARCHAR(50) NOT NULL
  COMMENT '规则名称',
  minimum_day INT(8)      NOT NULL
  COMMENT '最少入住天数',
  price       INT(10)     NOT NULL DEFAULT 0
  COMMENT '价格',
  deposit     INT(10)     NOT NULL DEFAULT 0
  COMMENT '押金',
  creator     VARCHAR(20) COMMENT '创建者',
  create_time DATETIME COMMENT '创建时间',
  PRIMARY KEY (id)
);

ALTER TABLE t_erp_hotel_long_time_room
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT '长包房';

/*==============================================================*//*==============================================================*//*==============================================================*/

DROP TABLE IF EXISTS t_erp_hotel_authorization;

/*==============================================================*/
/* Table: t_erp_hotel_authorization                             */
/*==============================================================*/
CREATE TABLE t_erp_hotel_authorization
(
  id          INT     NOT NULL AUTO_INCREMENT
  COMMENT 'ID',
  hotel_id    INT(11) NOT NULL
  COMMENT '酒店ID',
  shop_id     INT(11) NOT NULL
  COMMENT '门店ID',
  account_id  INT(11) NOT NULL
  COMMENT '账号ID',
  -- account_name         varchar(20) not null comment '(账号)核销员名称',
  -- account_phone        varchar(20) not null comment '(账号)核销员电话',
  function_id INT(11) NOT NULL
  COMMENT '功能ID',
  creator     VARCHAR(20) COMMENT '创建者',
  create_time DATETIME COMMENT '创建时间',
  PRIMARY KEY (id),
  UNIQUE KEY authorization_key (hotel_id, shop_id, account_id, function_id)
);

ALTER TABLE t_erp_hotel_authorization
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT '授权人-功能-关系';

DROP TABLE IF EXISTS t_erp_hotel_function;

/*==============================================================*/
/* Table: t_erp_hotel_function                                  */
/*==============================================================*/
CREATE TABLE t_erp_hotel_function
(
  id            INT          NOT NULL AUTO_INCREMENT
  COMMENT 'ID',
  function_name VARCHAR(20)  NOT NULL
  COMMENT '功能名称',
  description   VARCHAR(140) NOT NULL
  COMMENT '功能描述',
  model         INT(1)       NOT NULL
  COMMENT '模块',
  PRIMARY KEY (id)
);

ALTER TABLE t_erp_hotel_function
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT '授权功能';

/*drop table if exists t_erp_hotel_authorization_function_relation;*/

/*==============================================================*/
/* Table: t_erp_hotel_authorization_function_relation           */
/*==============================================================*/
/*create table t_erp_hotel_authorization_function_relation
(
   author_id            int(11) not null comment '用户ID',
   function_id          int(11) not null comment '功能ID',
   unique key author_function_key (author_id, function_id)
);

alter table t_erp_hotel_authorization_function_relation engine=innodb default charset=utf8 comment '授权功能';  */

/*==============================================================*//*==============================================================*//*==============================================================*/

DROP TABLE IF EXISTS t_erp_hotel_food;

/*==============================================================*/
/* Table: t_erp_hotel_food                                      */
/*==============================================================*/
CREATE TABLE t_erp_hotel_food
(
  id                INT         NOT NULL AUTO_INCREMENT
  COMMENT 'ID',
  hotel_id          INT(11)     NOT NULL
  COMMENT '酒店ID',
  name              VARCHAR(50) NOT NULL
  COMMENT '菜品名称',
  -- type                 int(1) not null comment '菜式类型 1:早餐 2:午餐 3:晚餐 4:宵夜',
  breakfast         INT(1)               DEFAULT 0
  COMMENT '早餐',
  lunch             INT(1)               DEFAULT 0
  COMMENT '午餐',
  dinner            INT(1)               DEFAULT 0
  COMMENT '晚餐',
  supper            INT(1)               DEFAULT 0
  COMMENT '宵夜',
  provide_from      INT(1)      NOT NULL
  COMMENT '菜品提供方 1:本酒店 2:合作方',
  company_name      VARCHAR(50) COMMENT '合作方名称',
  order_phone       VARCHAR(20) COMMENT '新订单接受电话',
  food_price        INT(10)     NOT NULL DEFAULT 0
  COMMENT '菜品单价',
  food_deliver_time INT(8)      NOT NULL
  COMMENT '配送时间 分钟为单位',
  if_use            INT(1)      NOT NULL DEFAULT 0
  COMMENT '是否启用',
  creator           VARCHAR(20) UNIQUE
  COMMENT '创建者',
  create_time       DATETIME COMMENT '创建时间',
  update_time       TIMESTAMP            DEFAULT now() ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间',
  PRIMARY KEY (id)
);

ALTER TABLE t_erp_hotel_food
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT 'ERP酒店菜品';

/*drop table if exists t_erp_hotel_food_image;*/

/*==============================================================*/
/* Table: t_erp_hotel_food_image                                */
/*==============================================================*/
/*create table t_erp_hotel_food_image
(
   id                   int not null auto_increment comment 'ID',
   food_id              int(11) not null comment '酒店菜品ID',
   url                  varchar(2000) comment '路径',
   type                 varchar(12) not null comment '类型',
   primary key (id)
);

alter table t_erp_hotel_food_image engine=innodb default charset=utf8 comment 'ERP酒店菜品-图片';*/

/*==============================================================*//*==============================================================*//*==============================================================*/

DROP TABLE IF EXISTS t_erp_hotel_cash_pledge;

/*==============================================================*/
/* Table: t_erp_hotel_cash_pledge                               */
/*==============================================================*/
CREATE TABLE t_erp_hotel_cash_pledge
(
  id              INT(11)     NOT NULL AUTO_INCREMENT
  COMMENT 'id',
  bus_id          INT(11) COMMENT '商家id',
  member_id       INT(11) COMMENT '会员id',
  hotel_id        INT(11)     NOT NULL
  COMMENT '酒店id',
  book_name       VARCHAR(20) COMMENT '预订人',
  book_phone      VARCHAR(20)          DEFAULT NULL
  COMMENT '电话',
  cash_pledge     INT(10)     NOT NULL DEFAULT 0
  COMMENT '押金金额',
  status          INT(4)      NOT NULL
  COMMENT '押金状态',
  type            VARCHAR(10)          DEFAULT '入住押金'
  COMMENT '押金类型，交易类型',
  refunds         INT(10) COMMENT '退款金额',
  refunds_explain VARCHAR(200) COMMENT '退款说明',
  createtime      VARCHAR(30) NOT NULL
  COMMENT '创建时间',
  order_id        VARCHAR(30) NOT NULL
  COMMENT '订单id',
  lookstatus      INT(4)      NOT NULL
  COMMENT '是否查看',
  PRIMARY KEY (id),
  UNIQUE KEY `oid_que` (`order_id`)
);

ALTER TABLE t_erp_hotel_cash_pledge
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT 'ERP酒店-押金';

DROP TABLE IF EXISTS t_erp_hotel_room_order;

/*==============================================================*/
/* Table: t_erp_hotel_room_order                                */
/*==============================================================*/
CREATE TABLE t_erp_hotel_room_order
(
  id                INT(11)     NOT NULL AUTO_INCREMENT
  COMMENT 'id',
  hotel_id          INT(11)     NOT NULL
  COMMENT '酒店ID',
  bus_id            INT(11) COMMENT '商家id',
  /*member_id            int(11) comment '会员id',
  book_name            varchar(20) not null comment '预订人',
  book_phone           varchar(20) not null comment '电话',
  book_gender          varchar(20) comment '性别',
  document_type        varchar(20) not null comment '证件类型',
  document_type_value  varchar(50) not null comment '证件号码',*/
  check_in_mode     INT(1)      NOT NULL
  COMMENT '入住方式(0：散客，1：协议单位， 2：团队)',
  check_in_standard INT(1)      NOT NULL
  COMMENT '入住标准(0：全天房，1：钟点房，2：长包房)',
  price             INT(10)     NOT NULL DEFAULT 0
  COMMENT '订单价格',
  quantity          INT(4)      NOT NULL
  COMMENT '数量',
  if_cash_pledge    INT(1)      NOT NULL
  COMMENT '是否免押金',
  cash_pledge       INT(10)     NOT NULL DEFAULT 0
  COMMENT '订单押金',
  order_number      VARCHAR(50) NOT NULL
  COMMENT '订单号',
  check_in_time     DATETIME COMMENT '入住时间',
  check_out_time    DATETIME COMMENT '离店时间',
  create_time       DATETIME    NOT NULL
  COMMENT '创建时间',
  pay_type          INT(4)      NOT NULL
  COMMENT '支付方式 1:在线支付 2:到店支付 3:储值卡支付 4:支付宝 5:银行卡 6:现金',
  pay_time          DATETIME COMMENT '支付时间时间',
  pay_status        INT(1)      NOT NULL
  COMMENT '支付状态 0:未支付 1:已支付 2:挂账, 3:已退款',
  order_status      INT(1)      NOT NULL
  COMMENT '订单状态 0:处理中 1:已确认 2:已取消 3:已入住 4:已完成',
  remark            VARCHAR(140) COMMENT '备注',
  source            VARCHAR(20) COMMENT '来源',
  available         INT(1)               DEFAULT 1
  COMMENT '是否可用(0:false, 1:true)',
  PRIMARY KEY (id)
);

ALTER TABLE t_erp_hotel_room_order
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT 'ERP酒店-房间-订单';

DROP TABLE IF EXISTS t_erp_hotel_room_order_guest;

/*==============================================================*/
/* Table: t_erp_hotel_room_order_guest                          */
/*==============================================================*/
CREATE TABLE t_erp_hotel_room_order_guest
(
  id                  INT(11)     NOT NULL AUTO_INCREMENT
  COMMENT 'id',
  order_id            INT(11)     NOT NULL
  COMMENT '订单ID',
  member_id           INT(11) COMMENT '会员ID',
  book_name           VARCHAR(20) NOT NULL
  COMMENT '预订人',
  book_phone          VARCHAR(20) NOT NULL
  COMMENT '电话',
  book_gender         VARCHAR(20) COMMENT '性别',
  document_type       VARCHAR(20) NOT NULL
  COMMENT '证件类型',
  document_type_value VARCHAR(50) NOT NULL
  COMMENT '证件号码',
  guest_type          INT(1)      NOT NULL
  COMMENT '客人类型(0：主，1：从)',
  PRIMARY KEY (id)
);

ALTER TABLE t_erp_hotel_room_order_guest
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT 'ERP酒店-房间-订单-客人';

DROP TABLE IF EXISTS t_erp_hotel_room_order_suite;

/*==============================================================*/
/* Table: t_erp_hotel_room_order_suite                          */
/*==============================================================*/
CREATE TABLE t_erp_hotel_room_order_suite
(
  id            INT(11) NOT NULL AUTO_INCREMENT
  COMMENT 'id',
  order_id      INT(11) NOT NULL
  COMMENT '订单ID',
  room_suite_id INT(11) COMMENT '房间ID',
  /*activity_id          int(11) comment '房价活动ID',*/
  check_in_mode INT(1)  NOT NULL
  COMMENT '入住方式(0：散客，1：协议单位， 2：团队)',
  price         INT(10) NOT NULL DEFAULT 0
  COMMENT '房价',
  check_in_days INT(8)  NOT NULL
  COMMENT '入住天数',
  remark        VARCHAR(140) COMMENT '备注',
  PRIMARY KEY (id)
);

ALTER TABLE t_erp_hotel_room_order_suite
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT 'ERP酒店-房间-订单-房间';

DROP TABLE IF EXISTS t_erp_hotel_room_order_member_card;

/*==============================================================*/
/* Table: t_erp_hotel_room_order_member_card                    */
/*==============================================================*/
CREATE TABLE t_erp_hotel_room_order_member_card
(
  id             INT(11) NOT NULL AUTO_INCREMENT
  COMMENT 'id',
  order_id       INT(11) NOT NULL
  COMMENT '订单ID',
  cardVolumeCode VARCHAR(20) COMMENT '卡券code',
  fenbi          INT(10)          DEFAULT '0'
  COMMENT '粉币',
  PRIMARY KEY (id)
);

ALTER TABLE t_erp_hotel_room_order_member_card
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT 'ERP酒店-房间-订单-会员卡使用情况';

DROP TABLE IF EXISTS t_erp_hotel_room_order_protocol_unit;

/*==============================================================*/
/* Table: t_erp_hotel_room_order_protocol_unit                  */
/*==============================================================*/
CREATE TABLE t_erp_hotel_room_order_protocol_unit
(
  id                 INT(11) NOT NULL AUTO_INCREMENT
  COMMENT 'id',
  order_id           INT(11) NOT NULL
  COMMENT '订单ID',
  protocol_unit_id   INT(11) COMMENT '协议单位ID',
  protocol_unit_name VARCHAR(20) COMMENT '协议单位名称',
  protocol_set_id    INT(11) COMMENT '协议套餐ID',
  protocol_set_price INT(10) NOT NULL DEFAULT 0
  COMMENT '协议套餐价格',
  PRIMARY KEY (id)
);

ALTER TABLE t_erp_hotel_room_order_protocol_unit
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT 'ERP酒店-房间-订单-协议单位';

DROP TABLE IF EXISTS t_erp_hotel_invoice_category;

/*==============================================================*/
/* Table: t_erp_hotel_invoice_category                          */
/*==============================================================*/
CREATE TABLE t_erp_hotel_invoice_category
(
  id   INT(11)     NOT NULL AUTO_INCREMENT
  COMMENT 'id',
  name VARCHAR(20) NOT NULL
  COMMENT '发票类目名',
  PRIMARY KEY (id)
);

ALTER TABLE t_erp_hotel_invoice_category
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT 'ERP酒店-发票类目';

DROP TABLE IF EXISTS t_erp_hotel_room_order_check_out;

/*==============================================================*/
/* Table: t_erp_hotel_room_order_check_out                      */
/*==============================================================*/
CREATE TABLE t_erp_hotel_room_order_check_out
(
  id                  INT(11)     NOT NULL AUTO_INCREMENT
  COMMENT 'id',
  order_id            INT(11)     NOT NULL
  COMMENT '订单ID',
  member_id           INT(11)     NOT NULL
  COMMENT '会员ID',
  room_suite_id       INT(11)     NOT NULL
  COMMENT '房间ID',
  invoice_header      VARCHAR(50) NOT NULL
  COMMENT '发票抬头',
  invoice_category_id INT(11)     NOT NULL
  COMMENT '发票类目ID',
  PRIMARY KEY (id)
);

ALTER TABLE t_erp_hotel_room_order_check_out
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT 'ERP酒店-房间-订单-退房记录';

DROP TABLE IF EXISTS t_erp_hotel_room_order_consumption;

/*==============================================================*/
/* Table: t_erp_hotel_room_order_consumption                    */
/*==============================================================*/
CREATE TABLE t_erp_hotel_room_order_consumption
(
  id            INT(11)     NOT NULL AUTO_INCREMENT
  COMMENT 'id',
  order_id      INT(11)     NOT NULL
  COMMENT '订单ID',
  member_id     INT(11)     NULL
  COMMENT '会员ID',
  room_suite_id INT(11)     NOT NULL
  COMMENT '房间ID',
  name          VARCHAR(50) NOT NULL
  COMMENT '物品名称',
  type          INT(1)      NOT NULL
  COMMENT '物品类型(损坏：0， 消费：1)',
  price         INT(10)     NOT NULL DEFAULT 0
  COMMENT '物品价格',
  count         INT(5)      NOT NULL
  COMMENT '物品数量',
  create_time   DATETIME    NOT NULL
  COMMENT '创建时间',
  PRIMARY KEY (id)
);

ALTER TABLE t_erp_hotel_room_order_consumption
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT 'ERP酒店-房间-订单-损坏&消费';

/*==============================================================*//*==============================================================*//*==============================================================*/

DROP TABLE IF EXISTS t_erp_hotel_food_order;

/*==============================================================*/
/* Table: t_erp_hotel_food_order                                */
/*==============================================================*/
CREATE TABLE t_erp_hotel_food_order
(
  id           INT(11)     NOT NULL AUTO_INCREMENT
  COMMENT 'id',
  hotel_id     INT(11)     NOT NULL
  COMMENT '酒店ID',
  bus_id       INT(11) COMMENT '商家id',
  number       VARCHAR(10) NOT NULL
  COMMENT '房间号',
  price        INT(10)     NOT NULL DEFAULT 0
  COMMENT '订单价格',
  order_number VARCHAR(50) NOT NULL
  COMMENT '订单号',
  book_name    VARCHAR(20) NOT NULL
  COMMENT '预订人',
  book_phone   VARCHAR(20) NOT NULL
  COMMENT '电话',
  create_time  DATETIME    NOT NULL
  COMMENT '创建时间',
  arrival_time DATETIME    NOT NULL
  COMMENT '预计到达时间',
  pay_type     INT(4)      NOT NULL
  COMMENT '支付方式 1:在线支付 2:到店支付 3:储值卡支付 4:支付宝 5:银行卡 6:现金',
  pay_time     DATETIME COMMENT '支付时间时间',
  pay_status   INT(1)      NOT NULL
  COMMENT '支付状态 0:未支付 1:已支付 2:挂账 3:退款',
  order_status INT(1)      NOT NULL
  COMMENT '订单状态 0:处理中 1:已确认 2:已取消 3:已入住(不需要) 4:已完成',
  remark       VARCHAR(140) COMMENT '备注',
  source       VARCHAR(20) COMMENT '来源 ',
  available    INT(1)               DEFAULT 1
  COMMENT '是否可用(0:false, 1:true)',
  PRIMARY KEY (id)
);

ALTER TABLE t_erp_hotel_food_order
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT 'ERP酒店-餐饮-订单';

DROP TABLE IF EXISTS t_erp_hotel_food_order_relation;

/*==============================================================*/
/* Table: t_erp_hotel_food_order_relation                       */
/*==============================================================*/
CREATE TABLE t_erp_hotel_food_order_relation
(
  order_id INT(11) NOT NULL
  COMMENT '订单ID',
  food_id  INT(11) NOT NULL
  COMMENT '酒店菜品ID',
  UNIQUE KEY food_order_key (order_id, food_id)
);

ALTER TABLE t_erp_hotel_food_order_relation
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT 'ERP酒店-餐饮-订单-列表';

/*==============================================================*//*==============================================================*//*==============================================================*/

DROP TABLE IF EXISTS t_erp_hotel_shift_records;

/*==============================================================*/
/* Table: t_erp_hotel_shift_records                             */
/*==============================================================*/
CREATE TABLE t_erp_hotel_shift_records
(
  id         INT(11) NOT NULL AUTO_INCREMENT
  COMMENT 'id',
  account_id INT(11) NOT NULL
  COMMENT '账号ID(当前账号)',
  man_id     INT(11) NOT NULL
  COMMENT '交班人ID(交班前者账号)',
  man_name   VARCHAR(20) COMMENT '交班人姓名(交班前者账号)',
  price      INT(10) NOT NULL DEFAULT 0
  COMMENT '交班金额(现金部分)',
  shift_time DATETIME COMMENT '交班时间',
  PRIMARY KEY (id)
);

ALTER TABLE t_erp_hotel_shift_records
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT 'ERP酒店-交班记录';

DROP TABLE IF EXISTS t_erp_hotel_allocate_change;

/*==============================================================*/
/* Table: t_erp_hotel_allocate_change                           */
/*==============================================================*/
CREATE TABLE t_erp_hotel_allocate_change
(
  id             INT(11) NOT NULL AUTO_INCREMENT
  COMMENT 'id',
  account_id     INT(11) NOT NULL
  COMMENT '账号ID(当前账号)',
  account_name   VARCHAR(20) COMMENT '账号姓名(当前账号)',
  man_id         INT(11) NOT NULL
  COMMENT '被分配人ID(分配后者账号)',
  man_name       VARCHAR(20) COMMENT '被分配人姓名(分配后者账号)',
  allocate_price INT(10) NOT NULL DEFAULT 0
  COMMENT '分配金额',
  allocate_time  DATETIME COMMENT '分配时间',
  PRIMARY KEY (id)
);

ALTER TABLE t_erp_hotel_allocate_change
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT 'ERP酒店-分配零钱';

/*==============================================================*//*==============================================================*//*==============================================================*/

DROP TABLE IF EXISTS t_erp_hotel_protocol_unit;

/*==============================================================*/
/* Table: t_erp_hotel_protocol_unit                             */
/*==============================================================*/
CREATE TABLE t_erp_hotel_protocol_unit
(
  id              INT(11)      NOT NULL AUTO_INCREMENT
  COMMENT 'id',
  name            VARCHAR(50)  NOT NULL
  COMMENT '单位名称',
  contact_person  VARCHAR(20)  NOT NULL
  COMMENT '联系人',
  phone           VARCHAR(20)  NOT NULL
  COMMENT '手机号码',
  tel             VARCHAR(20)  NOT NULL
  COMMENT '电话号码',
  protocol_number VARCHAR(20)  NOT NULL
  COMMENT '合同号',
  hotel_id        INT(11)      NOT NULL
  COMMENT '酒店ID',
  hotel_name      VARCHAR(200) NOT NULL
  COMMENT '酒店名',
  remark          VARCHAR(140) COMMENT '备注',
  set_id          INT(11)      NOT NULL
  COMMENT '套餐ID',
  create_time     DATETIME COMMENT '创建时间',
  operator_id     INT(11)      NOT NULL
  COMMENT '操作人ID',
  operator_name   VARCHAR(10) COMMENT '操作人',
  if_check        INT(1)       NOT NULL
  COMMENT '审核',
  account_id      INT(11) COMMENT '账号ID',
  type            INT(1)       NOT NULL
  COMMENT '0:协议单位, 1:中介',
  status          INT(1)       NOT NULL
  COMMENT '是否可用',
  PRIMARY KEY (id)
);

ALTER TABLE t_erp_hotel_protocol_unit
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT 'ERP酒店-协议单位&中介(需创建账号)';

/*drop table if exists t_erp_hotel_intermediary;*/

/*==============================================================*/
/* Table: t_erp_hotel_intermediary                              */
/*==============================================================*/
/*create table t_erp_hotel_intermediary
(
   id                   int(11) not null auto_increment comment 'id',
   name                 varchar(50) not null comment '中介名称',
   contact_person       varchar(20) not null comment '联系人',
   phone                varchar(20) not null comment '手机号码',
   tel                  varchar(20) not null comment '电话号码',
   protocol_number      varchar(20) not null comment '合同号',
   remark               varchar(140) comment '备注',
   set_id               int(11) not null comment '套餐ID',
   create_time          datetime comment '创建时间',
   operator_id          int(11) not null comment '操作人ID',
   operator_name        varchar(10) comment '操作人', 
   if_check             int(1) not null comment '审核',
   account_id           int(11) comment '账号ID',
   primary key (id)
);

alter table t_erp_hotel_intermediary engine=innodb default charset=utf8 comment 'ERP酒店-中介(需创建账号)';*/

DROP TABLE IF EXISTS t_erp_hotel_classes;

/*==============================================================*/
/* Table: t_erp_hotel_classes                                   */
/*==============================================================*/
CREATE TABLE t_erp_hotel_classes
(
  id            INT(11)     NOT NULL AUTO_INCREMENT
  COMMENT 'id',
  name          VARCHAR(50) NOT NULL
  COMMENT '班次名称',
  remark        VARCHAR(140) COMMENT '备注',
  start_time    DATETIME COMMENT '开始时间',
  end_time      DATETIME COMMENT '结束时间',
  create_time   DATETIME COMMENT '创建时间',
  operator_id   INT(11)     NOT NULL
  COMMENT '操作人ID',
  operator_name VARCHAR(10) COMMENT '操作人',
  PRIMARY KEY (id)
);

ALTER TABLE t_erp_hotel_classes
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT 'ERP酒店-班次';

DROP TABLE IF EXISTS t_erp_hotel_set;

/*==============================================================*/
/* Table: t_erp_hotel_set                                       */
/*==============================================================*/
CREATE TABLE t_erp_hotel_set
(
  id            INT(11)     NOT NULL AUTO_INCREMENT
  COMMENT 'id',
  name          VARCHAR(50) NOT NULL
  COMMENT '套餐名称',
  remark        VARCHAR(140) COMMENT '备注',
  create_time   DATETIME COMMENT '创建时间',
  operator_id   INT(11)     NOT NULL
  COMMENT '操作人ID',
  operator_name VARCHAR(10) COMMENT '操作人',
  type          INT(1)      NOT NULL
  COMMENT '套餐类型(0：协议单位， 1：中介)',
  PRIMARY KEY (id)
);

ALTER TABLE t_erp_hotel_set
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT 'ERP酒店-套餐';

DROP TABLE IF EXISTS t_erp_hotel_set_room;

/*==============================================================*/
/* Table: t_erp_hotel_set_room                                  */
/*==============================================================*/
CREATE TABLE t_erp_hotel_set_room
(
  set_id            INT(11) NOT NULL
  COMMENT '房型ID',
  room_id           INT(11) NOT NULL
  COMMENT '套餐ID',
  cooperation_price INT(10) NOT NULL DEFAULT 0
  COMMENT '合作价格',
  UNIQUE KEY set_room_key (set_id, room_id)
);

ALTER TABLE t_erp_hotel_set_room
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT 'ERP酒店-套餐(详情)-房型';

DROP TABLE IF EXISTS t_erp_hotel_debts;

/*==============================================================*/
/* Table: t_erp_hotel_debts                                     */
/*==============================================================*/
CREATE TABLE t_erp_hotel_debts
(
  id            INT(11) NOT NULL AUTO_INCREMENT
  COMMENT 'id',
  debts_price   INT(10) NOT NULL DEFAULT 0
  COMMENT '挂账金额',
  order_id      INT(11) NOT NULL
  COMMENT '订单号',
  /* 还没好 */
  debts_type    INT(1)  NOT NULL
  COMMENT '0：对内，1：对外',
  if_repayment  INT(1)  NOT NULL
  COMMENT '是否还款',
  create_time   DATETIME COMMENT '创建时间',
  operator_id   INT(11) NOT NULL
  COMMENT '操作人ID',
  operator_name VARCHAR(10) COMMENT '操作人',
  PRIMARY KEY (id)
);

ALTER TABLE t_erp_hotel_debts
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT 'ERP酒店-挂账';

/*==============================================================*//*==============================================================*//*==============================================================*/

DROP TABLE IF EXISTS t_erp_hotel_activity;

/*==============================================================*/
/* Table: t_erp_hotel_activity                                  */
/*==============================================================*/
CREATE TABLE t_erp_hotel_activity
(
  id              INT(11)     NOT NULL AUTO_INCREMENT,
  bus_id          INT(11)     NOT NULL
  COMMENT '商家id',
  hotel_id        INT(11)     NOT NULL
  COMMENT '所属酒店id',
  activity_name   VARCHAR(20) NOT NULL
  COMMENT '活动名称',
  activity_stime  DATETIME    NOT NULL
  COMMENT '生效时间 始',
  activity_etime  DATETIME    NOT NULL
  COMMENT '生效时间 末',
  type            INT(4)      NOT NULL
  COMMENT '类型（0=秒杀, 1=团购, 2=特价房, 3=时租房）',
  is_room_limit   INT(4) COMMENT '是否开启房间限购',
  room_limit      INT(4) COMMENT '房间限购数（间/人）',
  is_vipcard      INT(4)      NOT NULL
  COMMENT '是否关联会员卡',
  is_cardvolume   INT(4)      NOT NULL
  COMMENT '是否关联卡券',
  rule            VARCHAR(200) COMMENT '规则',
  is_room_count   INT(4) COMMENT '是否显示房间剩余数',
  room_count      INT(8) COMMENT '当房剩余 room_count  间时显',
  activity_status INT(4)      NOT NULL
  COMMENT '活动状态(0=未开始, 1=进行中, 2=已结束)',
  createtime      DATETIME    NOT NULL
  COMMENT '创建时间',
  live_stime      DATETIME COMMENT '居住开始时间',
  live_etime      DATETIME COMMENT '居住结束时间',
  grouplowest     INT(5)               DEFAULT '0'
  COMMENT '团购最低间数',
  grouplowesttip  VARCHAR(50)          DEFAULT ''
  COMMENT '当达不到最低间数提示',
  limit_hour      INT(3)               DEFAULT '0'
  COMMENT '时租房使用时间',
  whenrentstime   DATETIME COMMENT '时租房可预订起始时间',
  whenrentetime   DATETIME COMMENT '时租房可预订结束时间',
  update_time     TIMESTAMP            DEFAULT now() ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间',
  PRIMARY KEY (`id`)
);

ALTER TABLE t_erp_hotel_activity
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT 'ERP酒店-活动';

-- drop table if exists t_erp_hotel_activity_room;

/*==============================================================*/
/* Table: t_erp_hotel_activity_room                             */
/*==============================================================*/
-- create table t_erp_hotel_activity_room
-- (
--    id                   int(11) NOT NULL AUTO_INCREMENT,
--    activity_id          int(11) DEFAULT NULL COMMENT '活动id',
--    room_id              int(11) DEFAULT NULL COMMENT '房型ID',
--    price                int(10) not null default 0 comment '活动价格',
--    primary key (`id`)
-- );

-- alter table t_erp_hotel_activity_room engine=innodb default charset=utf8 comment 'ERP酒店-活动-房型';

DROP TABLE IF EXISTS t_erp_hotel_activity_room_suite;

/*==============================================================*/
/* Table: t_erp_hotel_activity_room_suite                       */
/*==============================================================*/
CREATE TABLE t_erp_hotel_activity_room_suite
(
  id          INT(11) NOT NULL AUTO_INCREMENT,
  activity_id INT(11)          DEFAULT NULL
  COMMENT '活动id',
  room_id     INT(11)          DEFAULT NULL
  COMMENT '房型ID',
  suite_id    INT(11)          DEFAULT NULL
  COMMENT '房间ID',
  price       INT(10) NOT NULL DEFAULT 0
  COMMENT '活动价格',
  UNIQUE KEY activity_room_suite_key (activity_id, room_id, suite_id),
  PRIMARY KEY (`id`)
);

ALTER TABLE t_erp_hotel_activity_room_suite
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT 'ERP酒店-活动-房型-房间';


























>>>>>>> e84f7e20e010edce8c294c517de8dd968a5742c6
