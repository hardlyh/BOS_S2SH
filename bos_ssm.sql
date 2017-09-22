/*
Navicat MySQL Data Transfer

Source Server         : BOS_SSM
Source Server Version : 50635
Source Host           : localhost:3306
Source Database       : bos_ssm

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2017-09-22 09:23:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for auth_function
-- ----------------------------
DROP TABLE IF EXISTS `auth_function`;
CREATE TABLE `auth_function` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `page` varchar(255) DEFAULT NULL,
  `generatemenu` varchar(255) DEFAULT NULL,
  `zindex` int(11) DEFAULT NULL,
  `pid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK33r6np87v1p6gge7t6rpcao5h` (`pid`),
  CONSTRAINT `FK33r6np87v1p6gge7t6rpcao5h` FOREIGN KEY (`pid`) REFERENCES `auth_function` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_function
-- ----------------------------
INSERT INTO `auth_function` VALUES ('11', '基础档案', 'jichudangan', null, null, '1', '0', null);
INSERT INTO `auth_function` VALUES ('113', '取派员管理', 'staff', null, 'page_base_staff.action', '1', '2', '11');
INSERT INTO `auth_function` VALUES ('114', '区域设置', 'region', null, 'page_base_region.action', '1', '3', '11');
INSERT INTO `auth_function` VALUES ('115', '管理分区', 'subarea', null, 'page_base_subarea.action', '1', '4', '11');
INSERT INTO `auth_function` VALUES ('116', '管理定区/调度排班', 'decidedzone', null, 'page_base_decidedzone.action', '1', '5', '11');
INSERT INTO `auth_function` VALUES ('12', '受理', 'shouli', null, null, '1', '1', null);
INSERT INTO `auth_function` VALUES ('121', '业务受理', 'noticebill', null, 'page_qupai_noticebill_add.action', '1', '0', '12');
INSERT INTO `auth_function` VALUES ('13', '调度', 'diaodu', null, null, '1', '2', null);
INSERT INTO `auth_function` VALUES ('132', '人工调度', 'personalassign', null, 'page_qupai_diaodu.action', '1', '1', '13');
INSERT INTO `auth_function` VALUES ('14', '系统管理', 'system', null, null, '1', '3', null);
INSERT INTO `auth_function` VALUES ('141', '用户管理', 'userlist', null, 'page_admin_userlist.action', '1', '1', '14');
INSERT INTO `auth_function` VALUES ('142', '功能权限管理', 'function', null, 'page_admin_function.action', '1', '2', '14');
INSERT INTO `auth_function` VALUES ('143', '角色管理', 'role', null, 'page_admin_role.action', '1', '3', '14');

-- ----------------------------
-- Table structure for auth_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_role`;
CREATE TABLE `auth_role` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_role
-- ----------------------------
INSERT INTO `auth_role` VALUES ('40283f815e3899ab015e389d3fea0000', '普通人员', 'common_user', '');
INSERT INTO `auth_role` VALUES ('40283f815e3899ab015e389d74cf0001', '超级管理员', 'admin', '');
INSERT INTO `auth_role` VALUES ('40283f815e3899ab015e389dba2d0002', '部门经理', 'department_manage', '');
INSERT INTO `auth_role` VALUES ('4028828d5e9e1e46015e9e43266c0000', '人事部', 'renshibu', '人事部管理');

-- ----------------------------
-- Table structure for bc_decidedzone
-- ----------------------------
DROP TABLE IF EXISTS `bc_decidedzone`;
CREATE TABLE `bc_decidedzone` (
  `id` varchar(32) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `staff_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh0xplk12o52a6eryw4hcqnfwt` (`staff_id`),
  CONSTRAINT `FK_decidedzone_staff` FOREIGN KEY (`staff_id`) REFERENCES `bc_staff` (`id`) ON DELETE SET NULL,
  CONSTRAINT `FKh0xplk12o52a6eryw4hcqnfwt` FOREIGN KEY (`staff_id`) REFERENCES `bc_staff` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bc_decidedzone
-- ----------------------------
INSERT INTO `bc_decidedzone` VALUES ('402881f25ea6f649015ea6f913930003', '北京东城区', '40283f815dd3ef13015dd3ef5d140000');
INSERT INTO `bc_decidedzone` VALUES ('402881f25ea6f649015ea6f9955a0004', '北京西城区', '40283f815e5c8773015e5c87fc1c0000');

-- ----------------------------
-- Table structure for bc_region
-- ----------------------------
DROP TABLE IF EXISTS `bc_region`;
CREATE TABLE `bc_region` (
  `id` varchar(32) NOT NULL,
  `province` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `district` varchar(50) DEFAULT NULL,
  `postcode` varchar(50) DEFAULT NULL,
  `shortcode` varchar(30) DEFAULT NULL,
  `citycode` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bc_region
-- ----------------------------
INSERT INTO `bc_region` VALUES ('QY001', '北京市', '北京市', '东城区', '110101', 'BJBJDC', 'beijing');
INSERT INTO `bc_region` VALUES ('QY002', '北京市', '北京市', '西城区', '110102', 'BJBJXC', 'beijing');
INSERT INTO `bc_region` VALUES ('QY003', '北京市', '北京市', '朝阳区', '110105', 'BJBJCY', 'beijing');
INSERT INTO `bc_region` VALUES ('QY004', '北京市', '北京市', '丰台区', '110106', 'BJBJFT', 'beijing');
INSERT INTO `bc_region` VALUES ('QY005', '北京市', '北京市', '石景山区', '110107', 'BJBJSJS', 'beijing');
INSERT INTO `bc_region` VALUES ('QY006', '北京市', '北京市', '海淀区', '110108', 'BJBJHD', 'beijing');
INSERT INTO `bc_region` VALUES ('QY007', '北京市', '北京市', '门头沟区', '110109', 'BJBJMTG', 'beijing');
INSERT INTO `bc_region` VALUES ('QY008', '北京市', '北京市', '房山区', '110111', 'BJBJFS', 'beijing');
INSERT INTO `bc_region` VALUES ('QY009', '北京市', '北京市', '通州区', '110112', 'BJBJTZ', 'beijing');
INSERT INTO `bc_region` VALUES ('QY010', '北京市', '北京市', '顺义区', '110113', 'BJBJSY', 'beijing');
INSERT INTO `bc_region` VALUES ('QY011', '北京市', '北京市', '昌平区', '110114', 'BJBJCP', 'beijing');
INSERT INTO `bc_region` VALUES ('QY012', '北京市', '北京市', '大兴区', '110115', 'BJBJDX', 'beijing');
INSERT INTO `bc_region` VALUES ('QY013', '北京市', '北京市', '怀柔区', '110116', 'BJBJHR', 'beijing');
INSERT INTO `bc_region` VALUES ('QY014', '北京市', '北京市', '平谷区', '110117', 'BJBJPG', 'beijing');
INSERT INTO `bc_region` VALUES ('QY015', '北京市', '北京市', '密云县', '110228', 'BJBJMY', 'beijing');
INSERT INTO `bc_region` VALUES ('QY016', '北京市', '北京市', '延庆县', '110229', 'BJBJYQ', 'beijing');
INSERT INTO `bc_region` VALUES ('QY017', '河北省', '石家庄市', '长安区', '130102', 'HBSJZZA', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY018', '河北省', '石家庄市', '桥东区', '130103', 'HBSJZQD', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY019', '河北省', '石家庄市', '桥西区', '130104', 'HBSJZQX', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY020', '河北省', '石家庄市', '新华区', '130105', 'HBSJZXH', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY021', '河北省', '石家庄市', '井陉矿区', '130107', 'HBSJZJXK', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY022', '河北省', '石家庄市', '裕华区', '130108', 'HBSJZYH', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY023', '河北省', '石家庄市', '井陉县', '130121', 'HBSJZJX', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY024', '河北省', '石家庄市', '正定县', '130123', 'HBSJZZD', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY025', '河北省', '石家庄市', '栾城县', '130124', 'HBSJZLC', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY026', '河北省', '石家庄市', '行唐县', '130125', 'HBSJZXT', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY027', '河北省', '石家庄市', '灵寿县', '130126', 'HBSJZLS', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY028', '河北省', '石家庄市', '高邑县', '130127', 'HBSJZGY', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY029', '河北省', '石家庄市', '深泽县', '130128', 'HBSJZSZ', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY030', '河北省', '石家庄市', '赞皇县', '130129', 'HBSJZZH', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY031', '河北省', '石家庄市', '无极县', '130130', 'HBSJZWJ', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY032', '河北省', '石家庄市', '平山县', '130131', 'HBSJZPS', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY033', '河北省', '石家庄市', '元氏县', '130132', 'HBSJZYS', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY034', '河北省', '石家庄市', '赵县', '130133', 'HBSJZZ', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY035', '河北省', '石家庄市', '辛集市', '130181', 'HBSJZXJ', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY036', '河北省', '石家庄市', '藁城市', '130182', 'HBSJZGC', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY037', '河北省', '石家庄市', '晋州市', '130183', 'HBSJZJZ', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY038', '河北省', '石家庄市', '新乐市', '130184', 'HBSJZXL', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY039', '河北省', '石家庄市', '鹿泉市', '130185', 'HBSJZLQ', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY040', '天津市', '天津市', '和平区', '120101', 'TJTJHP', 'tianjin');
INSERT INTO `bc_region` VALUES ('QY041', '天津市', '天津市', '河东区', '120102', 'TJTJHD', 'tianjin');
INSERT INTO `bc_region` VALUES ('QY042', '天津市', '天津市', '河西区', '120103', 'TJTJHX', 'tianjin');
INSERT INTO `bc_region` VALUES ('QY043', '天津市', '天津市', '南开区', '120104', 'TJTJNK', 'tianjin');
INSERT INTO `bc_region` VALUES ('QY044', '天津市', '天津市', '河北区', '120105', 'TJTJHB', 'tianjin');
INSERT INTO `bc_region` VALUES ('QY045', '天津市', '天津市', '红桥区', '120106', 'TJTJHQ', 'tianjin');
INSERT INTO `bc_region` VALUES ('QY046', '天津市', '天津市', '滨海新区', '120116', 'TJTJBHX', 'tianjin');
INSERT INTO `bc_region` VALUES ('QY047', '天津市', '天津市', '东丽区', '120110', 'TJTJDL', 'tianjin');
INSERT INTO `bc_region` VALUES ('QY048', '天津市', '天津市', '西青区', '120111', 'TJTJXQ', 'tianjin');
INSERT INTO `bc_region` VALUES ('QY049', '天津市', '天津市', '津南区', '120112', 'TJTJJN', 'tianjin');
INSERT INTO `bc_region` VALUES ('QY050', '天津市', '天津市', '北辰区', '120113', 'TJTJBC', 'tianjin');
INSERT INTO `bc_region` VALUES ('QY051', '天津市', '天津市', '武清区', '120114', 'TJTJWQ', 'tianjin');
INSERT INTO `bc_region` VALUES ('QY052', '天津市', '天津市', '宝坻区', '120115', 'TJTJBC', 'tianjin');
INSERT INTO `bc_region` VALUES ('QY053', '天津市', '天津市', '宁河县', '120221', 'TJTJNH', 'tianjin');
INSERT INTO `bc_region` VALUES ('QY054', '天津市', '天津市', '静海县', '120223', 'TJTJJH', 'tianjin');
INSERT INTO `bc_region` VALUES ('QY055', '天津市', '天津市', '蓟县', '120225', 'TJTJJ', 'tianjin');
INSERT INTO `bc_region` VALUES ('QY056', '山西省', '太原市', '小店区', '140105', 'SXTYXD', 'taiyuan');
INSERT INTO `bc_region` VALUES ('QY057', '山西省', '太原市', '迎泽区', '140106', 'SXTYYZ', 'taiyuan');
INSERT INTO `bc_region` VALUES ('QY058', '山西省', '太原市', '杏花岭区', '140107', 'SXTYXHL', 'taiyuan');
INSERT INTO `bc_region` VALUES ('QY059', '山西省', '太原市', '尖草坪区', '140108', 'SXTYJCP', 'taiyuan');
INSERT INTO `bc_region` VALUES ('QY060', '山西省', '太原市', '万柏林区', '140109', 'SXTYWBL', 'taiyuan');
INSERT INTO `bc_region` VALUES ('QY061', '山西省', '太原市', '晋源区', '140110', 'SXTYJY', 'taiyuan');
INSERT INTO `bc_region` VALUES ('QY062', '山西省', '太原市', '清徐县', '140121', 'SXTYQX', 'taiyuan');
INSERT INTO `bc_region` VALUES ('QY063', '山西省', '太原市', '阳曲县', '140122', 'SXTYYQ', 'taiyuan');
INSERT INTO `bc_region` VALUES ('QY064', '山西省', '太原市', '娄烦县', '140123', 'SXTYLF', 'taiyuan');
INSERT INTO `bc_region` VALUES ('QY065', '山西省', '太原市', '古交市', '140181', 'SXTYGJ', 'taiyuan');

-- ----------------------------
-- Table structure for bc_staff
-- ----------------------------
DROP TABLE IF EXISTS `bc_staff`;
CREATE TABLE `bc_staff` (
  `id` varchar(32) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `haspda` char(1) DEFAULT NULL,
  `deltag` char(1) DEFAULT NULL,
  `station` varchar(40) DEFAULT NULL,
  `standard` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bc_staff
-- ----------------------------
INSERT INTO `bc_staff` VALUES ('40283f815dd3ef13015dd3ef5d140000', '王五', '13213132131', '1', '0', 'mm', '1');
INSERT INTO `bc_staff` VALUES ('40283f815e5b2ed0015e5b3a25100000', '花花', '13213132131', '1', '0', 'mm', '1');
INSERT INTO `bc_staff` VALUES ('40283f815e5c8773015e5c87fc1c0000', '小明', '13213132131', '1', '0', 'mm', '1');
INSERT INTO `bc_staff` VALUES ('40283f815e607172015e60735a160000', '张全蛋', '13213132131', '1', '0', 'mm', '2');

-- ----------------------------
-- Table structure for bc_subarea
-- ----------------------------
DROP TABLE IF EXISTS `bc_subarea`;
CREATE TABLE `bc_subarea` (
  `id` varchar(32) NOT NULL,
  `decidedzone_id` varchar(32) DEFAULT NULL,
  `region_id` varchar(32) DEFAULT NULL,
  `addresskey` varchar(100) DEFAULT NULL,
  `startnum` varchar(30) DEFAULT NULL,
  `endnum` varchar(30) DEFAULT NULL,
  `single` char(1) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcjwxm19mx5njn3xyqgqp431mb` (`region_id`),
  KEY `FKlndbc8oldgbg3k1x63n3n6t7n` (`decidedzone_id`),
  CONSTRAINT `FK_area_decidedzone` FOREIGN KEY (`decidedzone_id`) REFERENCES `bc_decidedzone` (`id`) ON DELETE SET NULL,
  CONSTRAINT `FK_area_region` FOREIGN KEY (`region_id`) REFERENCES `bc_region` (`id`),
  CONSTRAINT `FKcjwxm19mx5njn3xyqgqp431mb` FOREIGN KEY (`region_id`) REFERENCES `bc_region` (`id`),
  CONSTRAINT `FKlndbc8oldgbg3k1x63n3n6t7n` FOREIGN KEY (`decidedzone_id`) REFERENCES `bc_decidedzone` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bc_subarea
-- ----------------------------
INSERT INTO `bc_subarea` VALUES ('402881f25ea6f649015ea6f8285c0000', '402881f25ea6f649015ea6f913930003', 'QY001', 'bjdc', '1001', '1005', '0', '东城区');
INSERT INTO `bc_subarea` VALUES ('402881f25ea6f649015ea6f876620001', '402881f25ea6f649015ea6f9955a0004', 'QY002', 'bjxc', '1005', '1009', '0', '西城区');

-- ----------------------------
-- Table structure for qp_noticebill
-- ----------------------------
DROP TABLE IF EXISTS `qp_noticebill`;
CREATE TABLE `qp_noticebill` (
  `id` varchar(32) NOT NULL,
  `staff_id` varchar(32) DEFAULT NULL,
  `customer_id` varchar(32) DEFAULT NULL,
  `customer_name` varchar(20) DEFAULT NULL,
  `delegater` varchar(20) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `pickaddress` varchar(200) DEFAULT NULL,
  `arrivecity` varchar(20) DEFAULT NULL,
  `product` varchar(20) DEFAULT NULL,
  `pickdate` date DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `volume` varchar(20) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `ordertype` varchar(20) DEFAULT NULL,
  `user_id` int(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhmbqr6qlg0uets978w5xshler` (`staff_id`),
  KEY `FKl5j3pm969oy1qdc1318jrmbgt` (`user_id`),
  CONSTRAINT `FKhmbqr6qlg0uets978w5xshler` FOREIGN KEY (`staff_id`) REFERENCES `bc_staff` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FKl5j3pm969oy1qdc1318jrmbgt` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qp_noticebill
-- ----------------------------
INSERT INTO `qp_noticebill` VALUES ('402881f25ea6f649015ea6fa9d070006', '40283f815dd3ef13015dd3ef5d140000', '4', '赵六', '赵六', '18633333333', '石家庄市桥西区和平路10号', '西安', '', '2017-09-12', null, null, '', '', '自动分单', '1');
INSERT INTO `qp_noticebill` VALUES ('402881f25ea6f649015ea6fb96860008', '40283f815dd3ef13015dd3ef5d140000', '4', '赵六', '赵六', '18633333333', '石家庄市桥西区和平路10号', '南京', '', '2017-09-27', null, null, '', '', '自动分单', '1');
INSERT INTO `qp_noticebill` VALUES ('4028828d5e998c69015e998cff2f0000', null, '1', '张三', '张三', '13811111111', '北京市西城区东城区100号', '1312', '', '2017-09-15', null, '1', '', '', '人工分单', '1');
INSERT INTO `qp_noticebill` VALUES ('4028828d5e9cc4d9015e9cc98dbc0001', '40283f815dd3ef13015dd3ef5d140000', '4', '赵六', '赵六', '18633333333', '石家庄市桥西区和平路10号', '1321312', '', '2017-09-13', null, null, '', '', '自动分单', '1');
INSERT INTO `qp_noticebill` VALUES ('ff8080815e9a62f7015e9a6d29690002', null, '8', '小李', '小李', '13788888888', '北京市昌平区建材城西路100号', '1321', '', '2017-09-07', null, null, '', '', '人工分单', '1');
INSERT INTO `qp_noticebill` VALUES ('ff8080815e9a83fe015e9a849f3c0001', '40283f815dd3ef13015dd3ef5d140000', '4', '赵六', '赵六', '18633333333', '石家庄市桥西区和平路10号', '12321', '', '2017-09-07', null, null, '', '', '自动分单', '1');

-- ----------------------------
-- Table structure for qp_workbill
-- ----------------------------
DROP TABLE IF EXISTS `qp_workbill`;
CREATE TABLE `qp_workbill` (
  `id` varchar(32) NOT NULL,
  `noticebill_id` varchar(32) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `pickstate` varchar(20) DEFAULT NULL,
  `buildtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `attachbilltimes` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `staff_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK55ckcnjyvwinnnniu5jpelg7y` (`staff_id`),
  KEY `FKggojlcovnpimuukxcueb18apt` (`noticebill_id`),
  CONSTRAINT `FK55ckcnjyvwinnnniu5jpelg7y` FOREIGN KEY (`staff_id`) REFERENCES `bc_staff` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FKggojlcovnpimuukxcueb18apt` FOREIGN KEY (`noticebill_id`) REFERENCES `qp_noticebill` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qp_workbill
-- ----------------------------
INSERT INTO `qp_workbill` VALUES ('402881f25e9d4ee5015e9d4f45d50000', '4028828d5e998c69015e998cff2f0000', '新单', '未取件', '2017-09-20 11:22:52', '0', null, null);
INSERT INTO `qp_workbill` VALUES ('402881f25e9d50a6015e9d510e080000', 'ff8080815e9a62f7015e9a6d29690002', '新单', '未取件', '2017-09-20 11:24:49', '0', null, null);
INSERT INTO `qp_workbill` VALUES ('402881f25ea6f649015ea6fa9d060005', '402881f25ea6f649015ea6fa9d070006', '新单', '未取件', '2017-09-22 08:26:36', '0', '', '40283f815dd3ef13015dd3ef5d140000');
INSERT INTO `qp_workbill` VALUES ('402881f25ea6f649015ea6fb96860007', '402881f25ea6f649015ea6fb96860008', '新单', '未取件', '2017-09-22 08:27:40', '0', '', '40283f815dd3ef13015dd3ef5d140000');
INSERT INTO `qp_workbill` VALUES ('4028828d5e9cc4d9015e9cc98cb80000', '4028828d5e9cc4d9015e9cc98dbc0001', '新单', '未取件', '2017-09-20 08:56:48', '0', '', '40283f815dd3ef13015dd3ef5d140000');
INSERT INTO `qp_workbill` VALUES ('ff8080815e9a83fe015e9a849f260000', 'ff8080815e9a83fe015e9a849f3c0001', '新单', '未取件', '2017-09-19 22:22:16', '0', '', '40283f815dd3ef13015dd3ef5d140000');

-- ----------------------------
-- Table structure for qp_workordermanage
-- ----------------------------
DROP TABLE IF EXISTS `qp_workordermanage`;
CREATE TABLE `qp_workordermanage` (
  `id` varchar(32) NOT NULL,
  `arrivecity` varchar(20) DEFAULT NULL,
  `product` varchar(20) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `floadreqr` varchar(255) DEFAULT NULL,
  `prodtimelimit` varchar(40) DEFAULT NULL,
  `prodtype` varchar(40) DEFAULT NULL,
  `sendername` varchar(20) DEFAULT NULL,
  `senderphone` varchar(20) DEFAULT NULL,
  `senderaddr` varchar(200) DEFAULT NULL,
  `receivername` varchar(20) DEFAULT NULL,
  `receiverphone` varchar(20) DEFAULT NULL,
  `receiveraddr` varchar(200) DEFAULT NULL,
  `feeitemnum` int(11) DEFAULT NULL,
  `actlweit` double DEFAULT NULL,
  `vol` varchar(20) DEFAULT NULL,
  `managerCheck` varchar(1) DEFAULT NULL,
  `updatetime` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qp_workordermanage
-- ----------------------------

-- ----------------------------
-- Table structure for role_function
-- ----------------------------
DROP TABLE IF EXISTS `role_function`;
CREATE TABLE `role_function` (
  `function_id` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL,
  PRIMARY KEY (`role_id`,`function_id`),
  KEY `FK5f8riddotqjpm9vly0b8c5nmf` (`function_id`),
  CONSTRAINT `FK10qo908yd9evkyb40vf88og85` FOREIGN KEY (`role_id`) REFERENCES `auth_role` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK5f8riddotqjpm9vly0b8c5nmf` FOREIGN KEY (`function_id`) REFERENCES `auth_function` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_function
-- ----------------------------
INSERT INTO `role_function` VALUES ('11', '40283f815e3899ab015e389d3fea0000');
INSERT INTO `role_function` VALUES ('11', '40283f815e3899ab015e389d74cf0001');
INSERT INTO `role_function` VALUES ('113', '40283f815e3899ab015e389d74cf0001');
INSERT INTO `role_function` VALUES ('113', '40283f815e3899ab015e389dba2d0002');
INSERT INTO `role_function` VALUES ('114', '40283f815e3899ab015e389d3fea0000');
INSERT INTO `role_function` VALUES ('114', '40283f815e3899ab015e389d74cf0001');
INSERT INTO `role_function` VALUES ('115', '40283f815e3899ab015e389d3fea0000');
INSERT INTO `role_function` VALUES ('115', '40283f815e3899ab015e389d74cf0001');
INSERT INTO `role_function` VALUES ('116', '40283f815e3899ab015e389d74cf0001');
INSERT INTO `role_function` VALUES ('116', '40283f815e3899ab015e389dba2d0002');
INSERT INTO `role_function` VALUES ('12', '40283f815e3899ab015e389d74cf0001');
INSERT INTO `role_function` VALUES ('121', '40283f815e3899ab015e389d74cf0001');
INSERT INTO `role_function` VALUES ('121', '4028828d5e9e1e46015e9e43266c0000');
INSERT INTO `role_function` VALUES ('13', '40283f815e3899ab015e389d74cf0001');
INSERT INTO `role_function` VALUES ('132', '40283f815e3899ab015e389d74cf0001');
INSERT INTO `role_function` VALUES ('132', '4028828d5e9e1e46015e9e43266c0000');

-- ----------------------------
-- Table structure for t_customer
-- ----------------------------
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `station` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `decidedzone_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK68mabqxay11a8sdn7bb42fthj` (`decidedzone_id`),
  CONSTRAINT `FK68mabqxay11a8sdn7bb42fthj` FOREIGN KEY (`decidedzone_id`) REFERENCES `bc_decidedzone` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_customer
-- ----------------------------
INSERT INTO `t_customer` VALUES ('1', '张三', '百度', '13811111111', '北京市西城区东城区100号', '402881f25ea6f649015ea6f9955a0004');
INSERT INTO `t_customer` VALUES ('2', '李四', '哇哈哈', '13822222222', '上海市虹桥区南京路250号', '402881f25ea6f649015ea6f913930003');
INSERT INTO `t_customer` VALUES ('3', '王五', '搜狗', '13533333333', '天津市河北区中山路30号', '402881f25ea6f649015ea6f913930003');
INSERT INTO `t_customer` VALUES ('4', '赵六', '联想', '18633333333', '石家庄市桥西区和平路10号', '402881f25ea6f649015ea6f913930003');
INSERT INTO `t_customer` VALUES ('5', '小白', '测试空间', '18511111111', '内蒙古自治区呼和浩特市和平路100号', '402881f25ea6f649015ea6f913930003');
INSERT INTO `t_customer` VALUES ('6', '小黑', '联想', '13722222222', '天津市南开区红旗路20号', '402881f25ea6f649015ea6f9955a0004');
INSERT INTO `t_customer` VALUES ('7', '小花', '百度', '13733333333', '北京市东城区王府井大街20号', '402881f25ea6f649015ea6f9955a0004');
INSERT INTO `t_customer` VALUES ('8', '小李', '长城', '13788888888', '北京市昌平区建材城西路100号', '402881f25ea6f649015ea6f9955a0004');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL,
  `salary` double DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `station` varchar(40) DEFAULT NULL,
  `telephone` varchar(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `role_id` varchar(32) NOT NULL,
  `user_id` int(32) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKqqlqhas35obkljn18mrh6mmms` (`role_id`),
  CONSTRAINT `FKeqon9sx5vssprq67dxm3s7ump` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKqqlqhas35obkljn18mrh6mmms` FOREIGN KEY (`role_id`) REFERENCES `auth_role` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
