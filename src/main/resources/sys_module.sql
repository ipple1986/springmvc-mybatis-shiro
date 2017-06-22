/*
Navicat MySQL Data Transfer

Source Server         : 172.16.2.104_3306
Source Server Version : 50712
Source Host           : 172.16.2.104:3306
Source Database       : callcenter_com

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2017-05-31 18:16:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_module`
-- ----------------------------
DROP TABLE IF EXISTS `sys_module`;
CREATE TABLE `sys_module` (
  `MODULE_CODE` varchar(255) NOT NULL,
  `MODULE_NAME` varchar(64) NOT NULL,
  `MODULE_ORDER` varchar(32) DEFAULT NULL COMMENT '该菜单在当前同级中第几，排序用',
  `MODULE_TYPE` varchar(2) NOT NULL,
  `PARENT_CODE` varchar(32) DEFAULT NULL,
  `MODULE_DESC` varchar(128) DEFAULT NULL,
  `MODULE_URI` varchar(128) DEFAULT NULL,
  `MODULE_VIEW` tinyint(4) DEFAULT NULL,
  `MODULE_ODR` varchar(32) DEFAULT NULL COMMENT '菜单层级',
  `ICO_PATH` varchar(256) DEFAULT NULL,
  `second_show` varchar(2) DEFAULT NULL,
  `usual_show` varchar(2) DEFAULT NULL,
  `ispad` varchar(10) DEFAULT NULL,
  `imgname` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`MODULE_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_module
-- ----------------------------
INSERT INTO `sys_module` VALUES ('ADD_VISIT_CLIENT', '新建来访单', '1', '0', 'HTLFDGL', '', 'visit/visit_doAddVisitClient.do', '1', '4', 'main_nav_icon_43', null, null, null, null);
INSERT INTO `sys_module` VALUES ('ALLOTTED_TYPE', '分配设置', '10', '0', 'YPT_BUSINESS_CONFI', '分配设置', 'system/allotted_doInCfg.do', '2', '3', 'main_nav_icon_12', null, null, null, null);
INSERT INTO `sys_module` VALUES ('COMMISSION_INFO', '佣金管理', '4', '0', 'YPT_ANCHANG_MAG', '佣金管理', '', '1', '3', 'main_nav_icon_92', null, null, null, null);
INSERT INTO `sys_module` VALUES ('commission_list', '个人结算', '1', '0', 'JKB_Commission_MANAGER', '佣金管理-个人结算', 'jkb/commission_doCommissionList.do', '2', '4', 'main_nav_icon_83', null, null, null, null);
INSERT INTO `sys_module` VALUES ('DEAL_INFO_MAG', '成交登记', '1', '0', 'TYP_DEAL_MAG', '成交登记', 'commission/commission_doDealQuery.do', '1', '4', 'main_nav_icon_91', null, null, null, null);
INSERT INTO `sys_module` VALUES ('ESTATE', '楼盘设置', '1', '0', 'JKB_CAPTION', '楼盘设置', 'jkb/estate_project.do', '2', '4', 'main_nav_icon_73', null, null, null, null);
INSERT INTO `sys_module` VALUES ('GZSM', '规则说明', '3', '0', 'JKB_CAPTION', '', 'jkb/caption_gzsm.do', '1', '4', 'main_nav_icon_75', null, null, null, null);
INSERT INTO `sys_module` VALUES ('HOUSE_FIELD_MAG', '字段设置', '3', '0', 'YPT_HOUSE_MAG', '字段设置', 'system/newField_doFieldListSales.do?businessCode=house_info', '1', '4', 'main_nav_icon_45', null, null, null, null);
INSERT INTO `sys_module` VALUES ('HOUSE_SOURCE_MAG', '房源管理', '1', '0', 'YPT_HOUSE_MAG', '房源管理', 'system/houseSource_doListHouseSource.do', '1', '4', 'main_nav_icon_39', null, null, null, null);
INSERT INTO `sys_module` VALUES ('HOUSE_STATE_MAG', '状态设置', '2', '0', 'YPT_HOUSE_MAG', '状态设置', 'system/house_doHouseStateInit.do', '1', '4', 'main_nav_icon_44', null, null, null, null);
INSERT INTO `sys_module` VALUES ('HTLFDGL', '来访管理', '1', '0', 'YPT_ANCHANG_MAG', '来访管理', '', '1', '3', 'main_nav_icon_41', null, null, null, null);
INSERT INTO `sys_module` VALUES ('JKB_ADS', '广告配置', '4', '0', 'JKB_CAPTION', '', 'jkb/ads_list.do', '1', '4', 'main_nav_icon_76', null, null, null, null);
INSERT INTO `sys_module` VALUES ('JKB_AGENT', '经纪人管理', '1', '0', 'JKB_CDYY', '经纪人管理', 'jkb/agent_list.do', '1', '4', 'main_nav_icon_78', null, null, null, null);
INSERT INTO `sys_module` VALUES ('JKB_CAPTION', '公众号配置', '1', '0', 'JKB_MANAGER', '公众号配置', '', '1', '3', 'main_nav_icon_72', null, null, null, null);
INSERT INTO `sys_module` VALUES ('JKB_CDYY', '渠道运营', '2', '0', 'JKB_MANAGER', '渠道运营', '', '1', '3', 'main_nav_icon_77', null, null, null, null);
INSERT INTO `sys_module` VALUES ('JKB_CFG', '聚客宝设置', '11', '0', 'YPT_BUSINESS_CONFI', '聚客宝设置', 'system/jkbcfg_doInCfg.do', '2', '3', 'main_nav_icon_12', null, null, null, null);
INSERT INTO `sys_module` VALUES ('JKB_Commission_MANAGER', '佣金管理', '4', '0', 'JKB_MANAGER', '佣金管理', '', '2', '3', 'main_nav_icon_82', null, null, null, null);
INSERT INTO `sys_module` VALUES ('JKB_INDEX', '聚客宝首页', '0', '0', 'JKB_MANAGER', '', 'jkb/report_index.do', '1', '3', 'main_nav_icon_1', null, null, null, null);
INSERT INTO `sys_module` VALUES ('JKB_MANAGER', '聚客宝', '1', '0', 'YPT_CHANNEL_CONTROL', '聚客宝', '', '1', '2', '', null, null, null, null);
INSERT INTO `sys_module` VALUES ('JKB_RECOMMEND_MANAGER', '推荐管理', '3', '0', 'JKB_MANAGER', '', '', '1', '3', 'main_nav_icon_80', null, null, null, null);
INSERT INTO `sys_module` VALUES ('JKB_STORE', '门店管理', '2', '0', 'JKB_CDYY', '门店管理', 'jkb/store_list.do', '1', '4', 'main_nav_icon_79', null, null, null, null);
INSERT INTO `sys_module` VALUES ('JKB_TEMP', '模板配置', '5', '0', 'JKB_CAPTION', '模板配置', 'jkb/report_view.do', '1', '4', 'main_nav_icon_76', null, null, null, null);
INSERT INTO `sys_module` VALUES ('LFDCX', '来访单查询', '2', '0', 'HTLFDGL', '来访单查询', 'visit/visit_doQuery.do', '3', '4', 'main_nav_icon_40', null, null, null, null);
INSERT INTO `sys_module` VALUES ('NAME_LIST', '名单设置', '95', '0', 'YPT_BUSINESS_CONFI', '名单设置', 'system/namelist_doInCfg.do', '2', '3', 'main_nav_icon_67', null, null, null, null);
INSERT INTO `sys_module` VALUES ('RECOMMEND_LIST', '推荐查询', '1', '0', 'JKB_RECOMMEND_MANAGER', '推荐查询', 'jkb/recommend_list.do', '1', '4', 'main_nav_icon_81', null, null, null, null);
INSERT INTO `sys_module` VALUES ('RECOMMEND_RGSH', '审核管理', '2', '0', 'JKB_RECOMMEND_MANAGER', '审核管理', 'jkb/recommend_list.do?searchDto.examine=1', '1', '4', 'main_nav_icon_81', null, null, null, null);
INSERT INTO `sys_module` VALUES ('TYP_DEAL_MAG', '成交管理', '3', '0', 'YPT_ANCHANG_MAG', '成交管理', '', '1', '3', 'main_nav_icon_91', null, null, null, null);
INSERT INTO `sys_module` VALUES ('VISIST_FOLLOW_LIST', '跟进记录查询', '3', '0', 'HTLFDGL', '客户跟进记录查询', 'visit/visitfollow_doQuery.do', '2', '4', 'main_nav_icon_95', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YJJS', '佣金结算', '2', '0', 'COMMISSION_INFO', '佣金结算', 'commission/audit_query.do', '1', '4', 'main_nav_icon_93', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YJSBCX', '佣金申报查询', '1', '0', 'COMMISSION_INFO', '佣金申报查询', 'commission/info_query.do', '2', '4', 'main_nav_icon_94', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_ADD_FIELD', '新增字段', '3', '0', 'YPT_BUSINESS_CONFI', '新增字段', 'system/newField_doFieldListPt.do', '1', '3', 'main_nav_icon_68', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_ANCHANG_KEEPER', '案场管控', '2', '0', '-1', '案场管控', '', '1', '1', '', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_ANCHANG_MAG', '移动销售', '1', '0', 'YPT_ANCHANG_KEEPER', '云平台移动销售', '', '1', '2', '', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_APP', '应用维护', '93', '0', 'YPT_BUSINESS_CONFI', '应用维护', 'system/app_initAppInfoList.do', '0', '3', '', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_BEE_USER_MANAGER', '人员管理', '2', '0', 'YPT_LITTLE_BEE', '人员管理', 'bee/beeuser_list.do', '3', '3', 'main_nav_icon_49', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_BUSINESS_CALLED_SET', '来电设置', '4', '0', 'YPT_BUSINESS_CONFI', '云平台来电设置', 'system/incoming_doInCfg.do', '2', '3', 'main_nav_icon_10', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_BUSINESS_CONFI', '业务配置', '5', '0', 'YPT_ANCHANG_KEEPER', '云平台业务配置', null, '1', '2', null, null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_BUSINESS_CONTRACT_SET', '签约设置', '9', '0', 'YPT_BUSINESS_CONFI', '云平台签约设置', 'system/sign_doInCfg.do', '2', '3', 'main_nav_icon_15', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_BUSINESS_DIALING_SET', '外呼设置', '5', '0', 'YPT_BUSINESS_CONFI', '云平台外呼设置', 'system/outbound_doInCfg.do', '2', '3', 'main_nav_icon_11', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_BUSINESS_EXT_SET', '外拓设置', '92', '0', 'YPT_BUSINESS_CONFI', '云平台外拓设置', 'system/beeReclaim_doInCfg.do', '2', '3', 'main_nav_icon_16', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_BUSINESS_FIELD', '字段定义', '1', '0', 'YPT_BUSINESS_CONFI', '云平台字段定义', 'system/newField_gotoFieldSetPage.do', '1', '3', 'main_nav_icon_9', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_BUSINESS_FROM_SET', '认筹设置', '7', '0', 'YPT_BUSINESS_CONFI', '云平台认筹设置', 'system/recognition_doInCfg.do', '2', '3', 'main_nav_icon_13', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_BUSINESS_SUB_SET', '认购设置', '8', '0', 'YPT_BUSINESS_CONFI', '云平台认购设置', 'system/subscription_doInCfg.do', '2', '3', 'main_nav_icon_14', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_BUSINESS_VISIT_SET', '来访设置', '6', '0', 'YPT_BUSINESS_CONFI', '云平台来访设置', 'system/visit_doInCfg.do', '2', '3', 'main_nav_icon_12', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_CALL_LIST', '通话记录', '4', '0', 'YPT_CONTACT_CENTER', '通话记录', 'crm/calllist_doquery.do?type=1', '1', '3', 'main_nav_icon_46', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_Call_statistics', '外呼结果统计', '1', '0', 'YPT_statistics', '外呼结果统计', 'system/console_doCallStatistics.do', '3', '4', 'main_nav_icon_71', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_CHANNEL_CONTROL', '拓客助手', '4', '0', '-1', '', '', '1', '1', '', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_CHECK_QUERY', '考勤查询', '2', '0', 'YPT_STATISTICAL_QUERY', '考勤查询', 'bee/beecheck_list.do', '3', '4', 'main_nav_icon_48', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_CLIENT_SET', '终端设置3', '7', '0', 'YPT_CON_MANAGE', '终端设置', 'system/bluetooth_phoneTimeInit.do', '3', '3', 'main_nav_icon_6', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_CONTACT_CALLED_MAG', '来电管理', '1', '0', 'YPT_CONTACT_CENTER', '', '', '1', '3', 'main_nav_icon_17', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_CONTACT_CALLED_POJ', '来电查询', '1', '0', 'YPT_CONTACT_CALLED_MAG', '', 'crm/calloutlist_doquery.do?type=1', '1', '4', 'main_nav_icon_30', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_CONTACT_CENTER', '呼叫中心', '2', '0', 'YPT_ANCHANG_KEEPER', '云平台呼叫中心', '', '1', '2', '', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_CONTACT_DIALING_CallClient', 'Call客管理', '5', '0', 'YPT_CONTACT_DIALING_MAG', 'Call客管理', 'crm/callclient_toList.do', '3', '4', 'main_nav_icon_84', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_CONTACT_DIALING_IMPL', '外呼执行', '3', '0', 'YPT_CONTACT_DIALING_MAG', '', '', '1', '4', 'main_nav_icon_21', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_CONTACT_DIALING_IN', '外呼录入', '2', '0', 'YPT_CONTACT_DIALING_MAG', '', '', '1', '4', 'main_nav_icon_20', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_CONTACT_DIALING_MAG', '外呼管理', '2', '0', 'YPT_CONTACT_CENTER', '', '', '1', '3', 'main_nav_icon_18', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_CONTACT_DIALING_POJ', '项目管理', '1', '0', 'YPT_CONTACT_DIALING_MAG', '', 'crm/callprojectmag_doSelectList.do', '3', '4', 'main_nav_icon_19', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_CONTACT_DIALING_QUERY', '外呼查询', '4', '0', 'YPT_CONTACT_DIALING_MAG', '', 'crm/callspecimeninfo_doQuerySpecimenList.do', '3', '4', 'main_nav_icon_22', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_CON_MANAGE', '配置管理', '1', '0', 'YPT_SOUYE', '云平台配置管理', '', '1', '2', '', '1', '0', null, null);
INSERT INTO `sys_module` VALUES ('YPT_DATA_REPORT', '数据报表', '3', '0', 'YPT_YUNYING_KEEPER', '数据报表', '', '1', '2', '', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_DISPATCH_QUERY', '派单查询', '4', '0', 'YPT_STATISTICAL_QUERY', '派单查询', 'bee/beedispatch_list.do', '1', '4', 'main_nav_icon_48', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_FENPEI_MANA', '分配管理', '2', '0', 'YPT_MESSAGE_CONFIG', '', 'system/allocate_doInit.do', '1', '4', 'main_nav_icon_33', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_GROUP_REPORT', '销售团队分析', '1', '0', 'YPT_DATA_REPORT', '销售团队分析', 'system/report_init.do', '1', '3', 'main_nav_icon_85', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_HOUSE_MAG', '销控管理', '2', '0', 'YPT_ANCHANG_MAG', '销控管理', '', '1', '3', 'main_nav_icon_42', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_IBEACON', 'ibeacon', '2', '0', 'YPT_YINGXIAO', 'ibeacon', '', '1', '2', '', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_INCOME_REPORT', '来电分析', '1', '0', 'YPT_STATISTICS_REPORT', '来电分析', 'system/incomingReport_incomeReport.do', '1', '4', 'main_nav_icon_96', null, null, null, 'system/statistics_incomeReport.do');
INSERT INTO `sys_module` VALUES ('YPT_JUDGE_CLIENT', '判客设置', '91', '0', 'YPT_BUSINESS_CONFI', '云平台判客设置', 'system/judgeClient_doInCfg.do', '2', '3', 'main_nav_icon_13', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_LITTLE_BEE', '外拓展业系统', '1', '0', 'YPT_CHANNEL_CONTROL', '', '', '1', '2', '', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_LOCATION', '轨迹定位', '5', '0', 'YPT_ANCHANG_MAG', '轨迹定位', '', '3', '3', 'main_nav_icon_56', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_LOCATION_AREA_SETUP', '区域设置', '5', '0', 'YPT_LOCATION', '区域设置', 'system/bluetooth_areaSetupInit.do', '1', '4', 'main_nav_icon_59', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_LOCATION_ORBIT', '轨迹热图', '3', '0', 'YPT_LOCATION', '轨迹热图', 'system/bluetooth_orbitLocation.do', '1', '4', 'main_nav_icon_57', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_LOCATION_QUERY', '轨迹查询', '1', '0', 'YPT_LOCATION', '轨迹查询', 'system/bluetooth_orbitQuery.do', '1', '4', 'main_nav_icon_55', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_LOCATION_RUNTIME', '实时定位', '2', '0', 'YPT_LOCATION', '实时定位', 'system/bluetooth_runtimeLocation.do', '1', '4', 'main_nav_icon_54', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_LOCATION_STOP_SETUP', '驻留分析', '4', '0', 'YPT_LOCATION', '驻留分析', 'system/bluetooth_dwellTimeAnalyze.do', '1', '4', 'main_nav_icon_61', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_LOGO', 'LOGO配置', '0.1', '0', 'YPT_CON_MANAGE', 'LOGO配置', 'system/org_doLogoInit.do', '1', '3', 'main_nav_icon_63', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_MAG_CLIENT', '客户管理', '2', '0', 'YPT_YUNYING_KEEPER', '客户管理', '', '2', '2', '', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_MAG_CLIENT2', '客户管理', '2', '0', 'YPT_MAG_CLIENT', '客户管理', 'client/client_initClientInfo.do', '3', '3', 'main_nav_icon_42', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_MESSAGE_CONFIG', '短信管理', '1', '0', 'YPT_YINGXIAO', '云平台短信管理', '', '1', '2', 'main_nav_icon_32', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_MESSAGE_MANAGE', '短信配置', '3', '0', 'YPT_MESSAGE_CONFIG', '', 'system/msgcfg_doInit.do', '3', '4', 'main_nav_icon_34', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_MESSAGE_SEND_BANCH', '已废弃', '9', '0', 'YPT_MESSAGE_CONFIG', '', 'system/taskinfo_doInit.do', '1', '4', 'main_nav_icon_35', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_MESSAGE_SEND_R', '发送记录', '7', '0', 'YPT_MESSAGE_CONFIG', '', 'system/msghistory_doDataHistoryList.do', '3', '4', 'main_nav_icon_37', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_MESSAGE_SEND_RECORD', '群发记录', '6', '0', 'YPT_MESSAGE_CONFIG', '', 'system/taskinfo_doDataTaskHistoryList.do', '3', '4', 'main_nav_icon_36', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_MESSAGE_SEND_TASK_INFO', '短信群发', '5', '0', 'YPT_MESSAGE_CONFIG', '', 'system/taskinfo_doInit.do', '3', '4', 'main_nav_icon_35', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_MING_YUAN', '明源接口设置', '94', '0', 'YPT_BUSINESS_CONFI', '云平台明源接口设置', 'client/mingyuan_mingYuanShow.do', '2', '3', 'main_nav_icon_13', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_MODULE', '菜单管理', '5', '0', 'YPT_CON_MANAGE', '云平台菜单管理', 'system/saasModule_doSaaSModulelist.do', '0', '3', 'main_nav_icon_6', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_MONITOR_PLATFORM', '监控平台', '1', '0', 'YPT_LITTLE_BEE', '', 'bee/beeMonitor_doBeeMonitorMain.do', '3', '3', 'main_nav_icon_47', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_MONITOR_RECORD', '监控记录', '2', '0', 'YPT_STATE_MONITOR', '监控记录', 'system/machines_doMonitorrecordList.do', '1', '4', 'main_nav_icon_58', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_NEWORG', '组织架构', '1.1', '0', 'YPT_CON_MANAGE', '组织架构', 'system/newOrg_doNewOrgList.do', '1', '3', 'main_nav_icon_7', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_NEWTEAM', '项目团队', '1.2', '0', 'YPT_CON_MANAGE', '项目团队', 'system/newOrg_doNewTeamList.do', '2', '3', 'main_nav_icon_86', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_ORG', '机构设置', '1', '0', 'YPT_CON_MANAGE', '云平台机构管理', 'system/org_doList.do', '1', '3', 'main_nav_icon_6', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_PHONELOG', '话机异常查询', '6', '0', 'YPT_CON_MANAGE', '话机异常查询', 'system/phoneLog_doGroup.do', '1', '3', 'main_nav_icon_22', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_PHONE_SET', '终端设置', '4', '0', 'YPT_CON_MANAGE', '云平台话机设置', 'system/org/system/org_doPhoneList.do', '1', '3', 'main_nav_icon_31', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_RECLAIMCLIENT_QUERY', '拓客查询', '1', '0', 'YPT_STATISTICAL_QUERY', '拓客查询', 'bee/beeclient_list.do', '3', '4', 'main_nav_icon_52', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_ROLE_MAG', '角色管理', '1.3', '0', 'YPT_CON_MANAGE', '角色管理', 'system/role_doNewRoleList.do', '1', '3', 'main_nav_icon_8', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_SOUYE', '配置管理', '1', '0', '-1', '云平台的首页', null, '1', '1', '', '0', '1', '1', null);
INSERT INTO `sys_module` VALUES ('YPT_STATE_MONITOR', '状态监控', '6', '0', 'YPT_ANCHANG_MAG', '状态监控', '', '1', '3', 'main_nav_icon_62', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_STATE_MONITOR_LIST', '手机监控', '1', '0', 'YPT_STATE_MONITOR', '手机监控', 'system/machines_doPhoneControlList.do', '1', '4', 'main_nav_icon_60', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_STATISTICAL_QUERY', '统计查询', '4', '0', 'YPT_LITTLE_BEE', '统计查询', '', '3', '3', 'main_nav_icon_51', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_statistics', '统计分析', '7', '0', 'YPT_ANCHANG_MAG', '统计分析', '', '3', '3', 'main_nav_icon_70', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_STATISTICS_REPORT', '统计分析', '2', '0', 'YPT_DATA_REPORT', '统计分析', '', '1', '3', 'main_nav_icon_70', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_TASK_SETTING', '任务设置', '3', '0', 'YPT_LITTLE_BEE', '任务设置', 'bee/beetask_initTaskSet.do', '3', '3', 'main_nav_icon_50', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_TRAFFIC_ADJUST', '轮序调整查询', '2', '0', 'YPT_TRAFFIC_MAG', '轮序调整查询', 'traffic/callInAdjust_doList.do', '3', '4', 'main_nav_icon_66', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_TRAFFIC_CALLIN', '接电轮序查询', '1', '0', 'YPT_TRAFFIC_MAG', '接电轮序查询', 'traffic/callIn_doList.do', '3', '4', 'main_nav_icon_64', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_TRAFFIC_MAG', '轮序管理', '5', '0', 'YPT_CONTACT_CENTER', '轮序管理', '', '3', '3', 'main_nav_icon_65', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_TRAFFIC_TRANSFER', '中转号码设置', '3', '0', 'YPT_TRAFFIC_MAG', '中转号码设置', 'traffic/transfer_transferNumberList.do', '3', '4', 'main_nav_icon_69', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_VISIT_REPORT', '来访分析', '2', '0', 'YPT_STATISTICS_REPORT', '来访分析', 'system/statistics_visitVisitorReport.do', '1', '4', 'main_nav_icon_97', null, null, null, 'system/statistics_visitReport.do');
INSERT INTO `sys_module` VALUES ('YPT_WARN_QUERY', '预警查询', '3', '0', 'YPT_STATISTICAL_QUERY', '预警查询', 'bee/beewarn_list.do', '3', '4', 'main_nav_icon_53', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_WORKSPACE', '工作台', '1', '0', 'YPT_YUNYING_KEEPER', '云平台的工作台', 'system/console_doInCfg.do', '1', '2', null, null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_YINGXIAO', '互动营销', '5', '0', '-1', '云平台互动营销', '', '1', '1', '', null, null, null, null);
INSERT INTO `sys_module` VALUES ('YPT_YUNYING_KEEPER', '运营管控', '3', '0', '-1', '运营管控', '', '1', '1', '', null, null, null, null);
INSERT INTO `sys_module` VALUES ('ZCXY', '注册协议', '2', '0', 'JKB_CAPTION', '', 'jkb/caption_zcxy.do', '1', '4', 'main_nav_icon_74', null, null, null, null);


@Results(value = {  
            @Result(property = "moduleCode", column = "module_code"),  
            @Result(property = "moduleName", column = "module_name"), 
            @Result(property = "moduleOrder", column = "module_order"), 
            @Result(property = "moduleType", column = "module_type"), 
            @Result(property = "moduleDesc", column = "module_desc"), 
            @Result(property = "moduleUri", column = "module_uri"), 
            @Result(property = "moduleView", column = "module_view"),
            @Result(property = "moduleOdr", column = "module_odr"),
            @Result(property = "imgname", column = "imgname"),
            @Result(property = "icoPath", column = "ico_path"),
            @Result(property = "ispad", column = "ispad"),
            @Result(property = "secondShow", column = "second_show"),
            @Result(property = "usualShow", column = "usual_show"),
            @Result(property = "parentCode", column = "parent_code") }
    )
    <!-- 允许或不允许多种结果集从一个单独的语句中返回（需要适合的驱动） -->
		<setting name="multipleResultSetsEnabled" value="true" />
		<!-- 使用列标签代替列名。不同的驱动在这方便表现不同。参考驱动文档或充分测试两种方法来决定所使用的驱动 -->
		<setting name="useColumnLabel" value="true" />
		<!-- 允许JDBC支持生成的键。需要适合的驱动。如果设置为true则这个设置强制生成的键被使用，尽管一些驱动拒绝兼容但仍然有效（比如Derby） -->
		<setting name="useGeneratedKeys" value="true" />
		<!-- 指定MyBatis如何自动映射列到字段/属性。PARTIAL只会自动映射简单，没有嵌套的结果。FULL会自动映射任意复杂的结果（嵌套的或其他情况） -->
		<setting name="autoMappingBehavior" value="PARTIAL" />
		<!--当检测出未知列（或未知属性）时，如何处理，默认情况下没有任何提示，这在测试的时候很不方便，不容易找到错误。 NONE : 不做任何处理 
			(默认值) WARNING : 警告日志形式的详细信息 FAILING : 映射失败，抛出异常和详细信息 -->
		<setting name="autoMappingUnknownColumnBehavior" value="WARNING" />
		<!-- 配置默认的执行器。SIMPLE执行器没有什么特别之处。REUSE执行器重用预处理语句。BATCH执行器重用语句和批量更新 -->
		<setting name="defaultExecutorType" value="SIMPLE" />
		<!-- 设置超时时间，它决定驱动等待一个数据库响应的时间 -->
		<setting name="defaultStatementTimeout" value="25000" />
		<!--设置查询返回值数量，可以被查询数值覆盖 -->
		<setting name="defaultFetchSize" value="100" />
		<!-- 允许在嵌套语句中使用分页 -->
		<setting name="safeRowBoundsEnabled" value="false" />
		
		<!--MyBatis 利用本地缓存机制（Local Cache）防止循环引用（circular references）和加速重复嵌套查询。 
			默认值为 SESSION，这种情况下会缓存一个会话中执行的所有查询。 若设置值为 STATEMENT，本地会话仅用在语句执行上，对相同 SqlSession 
			的不同调用将不会共享数据。 -->
		<setting name="localCacheScope" value="SESSION" />
		<!-- 当没有为参数提供特定的 JDBC 类型时，为空值指定 JDBC 类型。 某些驱动需要指定列的 JDBC 类型，多数情况直接用一般类型即可，比如 
			NULL、VARCHAR OTHER。 -->
		<setting name="jdbcTypeForNull" value="VARCHAR" />
		<!-- 指定哪个对象的方法触发一次延迟加载。 -->
		<setting name="lazyLoadTriggerMethods" value="equals,clone,hashCode,toString" />
		
		
		
			<!-- 映射文件，mapper的配置文件 -->
	<mappers>
		<!--直接映射到相应的mapper文件 -->
		<!-- <mapper resource="mybatis/mappers/SysModuleMapper.xml" /> -->
		<!--扫描包路径下所有xxMapper.xml文件 -->
		<package name="cn.jufuns.saas.mybatis.mapper" />
	</mappers>