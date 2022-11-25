/*
 Navicat Premium Data Transfer

 Source Server         : aliyun.xiaowenhao.ren
 Source Server Type    : MySQL
 Source Server Version : 80031 (8.0.31)
 Source Host           : aliyun.xiaowenhao.ren:3306
 Source Schema         : sys_minio

 Target Server Type    : MySQL
 Target Server Version : 80031 (8.0.31)
 File Encoding         : 65001

 Date: 25/11/2022 11:51:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_file_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_file_info`;
CREATE TABLE `sys_file_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `data_type` tinyint NOT NULL COMMENT '1 DIR:目录; 0 FILE文件',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件名',
  `ext` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文件后缀',
  `size` bigint DEFAULT NULL COMMENT '文件大小',
  `file_md5` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文件md5值',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件路径',
  `parent_dir_id` bigint NOT NULL COMMENT '父目录',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态 1 未删除 0 删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `path` (`path`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1595983315263950849 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of sys_file_info
-- ----------------------------
BEGIN;
INSERT INTO `sys_file_info` (`id`, `data_type`, `file_name`, `ext`, `size`, `file_md5`, `path`, `parent_dir_id`, `create_time`, `update_time`, `status`) VALUES (-1, 1, 'root', NULL, 0, '0', 'root', -2, '2022-10-16 23:17:33', '2022-10-21 18:04:17', 1);
INSERT INTO `sys_file_info` (`id`, `data_type`, `file_name`, `ext`, `size`, `file_md5`, `path`, `parent_dir_id`, `create_time`, `update_time`, `status`) VALUES (1585565735378358272, 1, 'game', NULL, 0, NULL, 'root/game', -1, '2022-10-27 17:35:04', '2022-10-27 17:35:04', 1);
INSERT INTO `sys_file_info` (`id`, `data_type`, `file_name`, `ext`, `size`, `file_md5`, `path`, `parent_dir_id`, `create_time`, `update_time`, `status`) VALUES (1585566887461392384, 0, 'android-studio-2021.3.1.17-mac.dmg', 'dmg', 1048613708, NULL, 'root/game/android-studio-2021.3.1.17-mac.dmg', 1585565735378358272, '2022-10-27 17:39:39', '2022-10-27 17:39:39', 1);
INSERT INTO `sys_file_info` (`id`, `data_type`, `file_name`, `ext`, `size`, `file_md5`, `path`, `parent_dir_id`, `create_time`, `update_time`, `status`) VALUES (1585588402189897728, 1, 'app', NULL, 0, NULL, 'root/app', -1, '2022-10-27 19:05:08', '2022-10-27 19:05:08', 1);
INSERT INTO `sys_file_info` (`id`, `data_type`, `file_name`, `ext`, `size`, `file_md5`, `path`, `parent_dir_id`, `create_time`, `update_time`, `status`) VALUES (1585588450504085504, 0, 'SpaceSniffer1.3.0.2.exe', 'exe', 2311168, NULL, 'root/app/SpaceSniffer1.3.0.2.exe', 1585588402189897728, '2022-10-27 19:05:20', '2022-10-27 19:05:20', 1);
INSERT INTO `sys_file_info` (`id`, `data_type`, `file_name`, `ext`, `size`, `file_md5`, `path`, `parent_dir_id`, `create_time`, `update_time`, `status`) VALUES (1585617617983508480, 0, '东方王朝：Oriental Empires CN（1.7）.7z', '7z', 673826245, NULL, 'root/game/东方王朝：Oriental Empires CN（1.7）.7z', 1585565735378358272, '2022-10-27 21:01:14', '2022-10-27 21:01:14', 1);
INSERT INTO `sys_file_info` (`id`, `data_type`, `file_name`, `ext`, `size`, `file_md5`, `path`, `parent_dir_id`, `create_time`, `update_time`, `status`) VALUES (1585619758248759296, 0, '（含dlc）正版破解dlc补丁（1.7）.zip', 'zip', 106591431, NULL, 'root/game/（含dlc）正版破解dlc补丁（1.7）.zip', 1585565735378358272, '2022-10-27 21:09:44', '2022-10-27 21:09:44', 1);
INSERT INTO `sys_file_info` (`id`, `data_type`, `file_name`, `ext`, `size`, `file_md5`, `path`, `parent_dir_id`, `create_time`, `update_time`, `status`) VALUES (1588358608725348352, 1, 'data', NULL, 0, NULL, 'root/data', -1, '2022-11-04 10:32:57', '2022-11-04 10:32:57', 1);
INSERT INTO `sys_file_info` (`id`, `data_type`, `file_name`, `ext`, `size`, `file_md5`, `path`, `parent_dir_id`, `create_time`, `update_time`, `status`) VALUES (1589798616950771712, 1, 'image', NULL, 0, NULL, 'root/image', -1, '2022-11-08 09:55:01', '2022-11-08 09:55:01', 1);
INSERT INTO `sys_file_info` (`id`, `data_type`, `file_name`, `ext`, `size`, `file_md5`, `path`, `parent_dir_id`, `create_time`, `update_time`, `status`) VALUES (1589798888787808256, 0, 'wallhaven-gpz2g7.jpeg', 'jpeg', 3435198, NULL, 'root/image/wallhaven-gpz2g7.jpeg', 1589798616950771712, '2022-11-08 09:56:06', '2022-11-08 09:56:06', 1);
INSERT INTO `sys_file_info` (`id`, `data_type`, `file_name`, `ext`, `size`, `file_md5`, `path`, `parent_dir_id`, `create_time`, `update_time`, `status`) VALUES (1589799286852423680, 0, 'wallhaven-9mjoy1.png', 'png', 15842713, NULL, 'root/image/wallhaven-9mjoy1.png', 1589798616950771712, '2022-11-08 09:57:41', '2022-11-08 09:57:41', 1);
INSERT INTO `sys_file_info` (`id`, `data_type`, `file_name`, `ext`, `size`, `file_md5`, `path`, `parent_dir_id`, `create_time`, `update_time`, `status`) VALUES (1590609586883268608, 0, 'GitKrakenSetup-7.5.5.exe', 'exe', 137183304, NULL, 'root/app/GitKrakenSetup-7.5.5.exe', 1585588402189897728, '2022-11-10 15:37:32', '2022-11-10 15:37:32', 1);
INSERT INTO `sys_file_info` (`id`, `data_type`, `file_name`, `ext`, `size`, `file_md5`, `path`, `parent_dir_id`, `create_time`, `update_time`, `status`) VALUES (1590611223878176768, 0, 'strings.json', 'json', 268559, NULL, 'root/app/strings.json', 1585588402189897728, '2022-11-10 15:44:02', '2022-11-10 15:44:02', 1);
INSERT INTO `sys_file_info` (`id`, `data_type`, `file_name`, `ext`, `size`, `file_md5`, `path`, `parent_dir_id`, `create_time`, `update_time`, `status`) VALUES (1592492479482040320, 0, 'joyu_official_5.2.8.apk', 'apk', 37649816, NULL, 'root/app/joyu_official_5.2.8.apk', 1585588402189897728, '2022-11-15 20:19:28', '2022-11-15 20:19:28', 1);
INSERT INTO `sys_file_info` (`id`, `data_type`, `file_name`, `ext`, `size`, `file_md5`, `path`, `parent_dir_id`, `create_time`, `update_time`, `status`) VALUES (1593170873869799424, 0, 'Panda-Gamepad-Pro-Apk-v1.4.9.apk', 'apk', 8709849, NULL, 'root/app/Panda-Gamepad-Pro-Apk-v1.4.9.apk', 1585588402189897728, '2022-11-17 17:15:10', '2022-11-17 17:15:10', 1);
INSERT INTO `sys_file_info` (`id`, `data_type`, `file_name`, `ext`, `size`, `file_md5`, `path`, `parent_dir_id`, `create_time`, `update_time`, `status`) VALUES (1593828052595511296, 0, 'node.zip', 'zip', 47580, NULL, 'root/data/node.zip', 1588358608725348352, '2022-11-19 12:46:34', '2022-11-19 12:46:34', 1);
INSERT INTO `sys_file_info` (`id`, `data_type`, `file_name`, `ext`, `size`, `file_md5`, `path`, `parent_dir_id`, `create_time`, `update_time`, `status`) VALUES (1595979349004259328, 0, 'work-place.jar', 'jar', 74982600, NULL, 'root/data/work-place.jar', 1588358608725348352, '2022-11-25 11:15:03', '2022-11-25 11:15:03', 1);
INSERT INTO `sys_file_info` (`id`, `data_type`, `file_name`, `ext`, `size`, `file_md5`, `path`, `parent_dir_id`, `create_time`, `update_time`, `status`) VALUES (1595983315263950848, 0, 'dist.zip', 'zip', 1883955, NULL, 'root/data/dist.zip', 1588358608725348352, '2022-11-25 11:30:48', '2022-11-25 11:30:48', 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
