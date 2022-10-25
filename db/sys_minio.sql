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

 Date: 25/10/2022 16:28:50
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
) ENGINE=InnoDB AUTO_INCREMENT=1584344842106966017 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of sys_file_info
-- ----------------------------
BEGIN;
INSERT INTO `sys_file_info` (`id`, `data_type`, `file_name`, `ext`, `size`, `file_md5`, `path`, `parent_dir_id`, `create_time`, `update_time`, `status`) VALUES (-1, 1, 'root', NULL, 0, '0', 'root', -2, '2022-10-16 23:17:33', '2022-10-21 18:04:17', 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
