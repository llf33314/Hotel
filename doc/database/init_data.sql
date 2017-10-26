/* ---------------------------------- 初始化数据字典 ---------------------------------- */
INSERT INTO `gt_hotel`.`sys_dictionary_type` (`id`, `dict_type_cn_name`, `dict_type_en_name`, `dict_type_status`, `dict_type_remark`) VALUES (1, '性别', 'GENDER', 0, '性别');
INSERT INTO `gt_hotel`.`sys_dictionary_type` (`id`, `dict_type_cn_name`, `dict_type_en_name`, `dict_type_status`, `dict_type_remark`) VALUES (2, '数据状态', 'DATA_STATE', 0, '数据状态');
INSERT INTO `gt_hotel`.`sys_dictionary_type` (`id`, `dict_type_cn_name`, `dict_type_en_name`, `dict_type_status`, `dict_type_remark`)
VALUES (3, '授权功能', 'AUTHORIZATION_CAPABILITIES', 0, '授权功能 针对ERP 个别员工可使用部分功能');
INSERT INTO `gt_hotel`.`sys_dictionary_type` (`id`, `dict_type_cn_name`, `dict_type_en_name`, `dict_type_status`, `dict_type_remark`) VALUES (4, '发票类型', 'INVOICE_TYPE', 0, '发票类型');
INSERT INTO `gt_hotel`.`sys_dictionary_type` (`id`, `dict_type_cn_name`, `dict_type_en_name`, `dict_type_status`, `dict_type_remark`) VALUES (5, '房型', 'ROOM_CATEGORY', 0, '房间类别');
INSERT INTO `gt_hotel`.`sys_dictionary_type` (`id`, `dict_type_cn_name`, `dict_type_en_name`, `dict_type_status`, `dict_type_remark`) VALUES (6, '客房状态', 'ROOM_STATUS', 0, '客房状态');
INSERT INTO `gt_hotel`.`sys_dictionary_type` (`id`, `dict_type_cn_name`, `dict_type_en_name`, `dict_type_status`, `dict_type_remark`) VALUES (7, '客房订单状态', 'GUEST_ROOM_ORDER_STATUS', 0, '客房订单状态');

INSERT INTO `gt_hotel`.`sys_dictionary` (`id`, `dict_type_id`, `dict_code`, `dict_cn_name`, `dict_en_name`) VALUES (1, 1, 0, '男', 'MAN');
INSERT INTO `gt_hotel`.`sys_dictionary` (`id`, `dict_type_id`, `dict_code`, `dict_cn_name`, `dict_en_name`) VALUES (2, 1, 1, '女', 'WOMEN');
INSERT INTO `gt_hotel`.`sys_dictionary` (`id`, `dict_type_id`, `dict_code`, `dict_cn_name`, `dict_en_name`) VALUES (3, 2, 0, '启用', 'ENABLE');
INSERT INTO `gt_hotel`.`sys_dictionary` (`id`, `dict_type_id`, `dict_code`, `dict_cn_name`, `dict_en_name`) VALUES (4, 2, 1, '禁用', 'DISABLE');
INSERT INTO `gt_hotel`.`sys_dictionary` (`id`, `dict_type_id`, `dict_code`, `dict_cn_name`, `dict_en_name`) VALUES (5, 2, 2, '删除', 'DELETE');
INSERT INTO `gt_hotel`.`sys_dictionary` (`id`, `dict_type_id`, `dict_code`, `dict_cn_name`, `dict_en_name`) VALUES (6, 3, 1, '提现', 'WITHDRAWAL');
INSERT INTO `gt_hotel`.`sys_dictionary` (`id`, `dict_type_id`, `dict_code`, `dict_cn_name`, `dict_en_name`) VALUES (7, 3, 2, '免押金', 'FREE_DEPOSIT');
INSERT INTO `gt_hotel`.`sys_dictionary` (`id`, `dict_type_id`, `dict_code`, `dict_cn_name`, `dict_en_name`) VALUES (8, 3, 3, '改价', 'CHANGE_PRICE');
INSERT INTO `gt_hotel`.`sys_dictionary` (`id`, `dict_type_id`, `dict_code`, `dict_cn_name`, `dict_en_name`) VALUES (9, 3, 4, '挂账', 'LOSSES');
INSERT INTO `gt_hotel`.`sys_dictionary` (`id`, `dict_type_id`, `dict_code`, `dict_cn_name`, `dict_en_name`) VALUES (10, 4, 1, '办公用品', 'OFFICE');
INSERT INTO `gt_hotel`.`sys_dictionary` (`id`, `dict_type_id`, `dict_code`, `dict_cn_name`, `dict_en_name`) VALUES (11, 4, 2, '住宿费', 'ACCOMMODATION');
INSERT INTO `gt_hotel`.`sys_dictionary` (`id`, `dict_type_id`, `dict_code`, `dict_cn_name`, `dict_en_name`) VALUES (12, 4, 3, '餐费', 'MEALS');
INSERT INTO `gt_hotel`.`sys_dictionary` (`id`, `dict_type_id`, `dict_code`, `dict_cn_name`, `dict_en_name`) VALUES (13, 4, 4, '培训费', 'TRAINING');
INSERT INTO `gt_hotel`.`sys_dictionary` (`id`, `dict_type_id`, `dict_code`, `dict_cn_name`, `dict_en_name`) VALUES (14, 4, 5, '打球费', 'PLAY');
INSERT INTO `gt_hotel`.`sys_dictionary` (`id`, `dict_type_id`, `dict_code`, `dict_cn_name`, `dict_en_name`) VALUES (15, 4, 6, '健身费', 'FITNESS');
INSERT INTO `gt_hotel`.`sys_dictionary` (`id`, `dict_type_id`, `dict_code`, `dict_cn_name`, `dict_en_name`) VALUES (16, 6, 1, '空房', 'VACANT_ROOM');
INSERT INTO `gt_hotel`.`sys_dictionary` (`id`, `dict_type_id`, `dict_code`, `dict_cn_name`, `dict_en_name`) VALUES (17, 6, 2, '锁定', 'LOCK');
INSERT INTO `gt_hotel`.`sys_dictionary` (`id`, `dict_type_id`, `dict_code`, `dict_cn_name`, `dict_en_name`) VALUES (18, 6, 3, '在住', 'IN_THE_LIVE');
INSERT INTO `gt_hotel`.`sys_dictionary` (`id`, `dict_type_id`, `dict_code`, `dict_cn_name`, `dict_en_name`) VALUES (19, 6, 4, '清洁', 'CLEAN');
INSERT INTO `gt_hotel`.`sys_dictionary` (`id`, `dict_type_id`, `dict_code`, `dict_cn_name`, `dict_en_name`) VALUES (20, 6, 5, '维护', 'MAINTAIN');
INSERT INTO `gt_hotel`.`sys_dictionary` (`id`, `dict_type_id`, `dict_code`, `dict_cn_name`, `dict_en_name`) VALUES (21, 7, 1, '处理中', 'PROCESSING');
INSERT INTO `gt_hotel`.`sys_dictionary` (`id`, `dict_type_id`, `dict_code`, `dict_cn_name`, `dict_en_name`) VALUES (22, 7, 2, '已确认', 'CONFIRMED');
INSERT INTO `gt_hotel`.`sys_dictionary` (`id`, `dict_type_id`, `dict_code`, `dict_cn_name`, `dict_en_name`) VALUES (23, 7, 3, '已入住', 'CHECK_IN');
INSERT INTO `gt_hotel`.`sys_dictionary` (`id`, `dict_type_id`, `dict_code`, `dict_cn_name`, `dict_en_name`) VALUES (24, 7, 4, '已完成', 'DONE');
INSERT INTO `gt_hotel`.`sys_dictionary` (`id`, `dict_type_id`, `dict_code`, `dict_cn_name`, `dict_en_name`) VALUES (25, 7, 5, '已取消', 'CANCEL');


/* ---------------------------------- 初始化基础设施 ---------------------------------- */
INSERT INTO `t_infrastructure` VALUES (1, '床型', 'icon-icon3', 'ROOM_CATEGORY');
INSERT INTO `t_infrastructure` VALUES (2, '面积', 'icon-mianji', 'ROOM_CATEGORY');
INSERT INTO `t_infrastructure` VALUES (3, '可住', 'icon-user', 'ROOM_CATEGORY');
INSERT INTO `t_infrastructure` VALUES (4, '上网', 'icon-wifi', 'ROOM_CATEGORY');
INSERT INTO `t_infrastructure` VALUES (5, '卫浴', 'icon-shuinuanweiyu', 'ROOM_CATEGORY');
INSERT INTO `t_infrastructure` VALUES (6, '窗户', 'icon-chuanghu', 'ROOM_CATEGORY');
INSERT INTO `t_infrastructure` VALUES (7, '吸烟', 'icon-xiyan', 'ROOM_CATEGORY');
INSERT INTO `t_infrastructure` VALUES (8, 'WIFI上网', 'icon-wifi', 'HOTEL');
INSERT INTO `t_infrastructure` VALUES (9, '餐厅', 'icon-canting', 'HOTEL');
INSERT INTO `t_infrastructure` VALUES (10, '停车场', 'icon-statictraffic', 'HOTEL');
INSERT INTO `t_infrastructure` VALUES (11, '商务中心', 'icon-woshou', 'HOTEL');
INSERT INTO `t_infrastructure` VALUES (12, '游泳池', 'icon-youyongchi', 'HOTEL');
INSERT INTO `t_infrastructure` VALUES (13, '会议室', 'icon-huiyishi', 'HOTEL');
INSERT INTO `t_infrastructure` VALUES (14, '健身房', 'icon-huilvjianshenfang', 'HOTEL');
INSERT INTO `t_infrastructure` VALUES (15, 'KTV', 'icon-ktv', 'HOTEL');
INSERT INTO `t_infrastructure` VALUES (16, 'SPA', 'icon--spa', 'HOTEL');
INSERT INTO `t_infrastructure` VALUES (17, '按摩', 'icon-anmo', 'HOTEL');
INSERT INTO `t_infrastructure` VALUES (18, '沐足', 'icon-zuliao-copy', 'HOTEL');
INSERT INTO `t_infrastructure` VALUES (19, '推油', 'icon-sangnayush', 'HOTEL');


