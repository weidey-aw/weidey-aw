/*
 Navicat Premium Data Transfer

 Source Server         : 云服务器
 Source Server Type    : MySQL
 Source Server Version : 50744
 Source Host           : 116.196.77.51:3306
 Source Schema         : dev_inis_cn

 Target Server Type    : MySQL
 Target Server Version : 50744
 File Encoding         : 65001

 Date: 13/04/2024 14:44:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for inis_article_group
-- ----------------------------
DROP TABLE IF EXISTS `inis_article_group`;
CREATE TABLE `inis_article_group`  (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` int(32) NULL DEFAULT 0 COMMENT '父级ID',
  `key` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '唯一键',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `description` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `avatar` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `json` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '用于存储JSON数据',
  `text` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '用于存储文本数据',
  `result` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '不存储数据，用于封装返回结果',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '更新时间',
  `delete_time` bigint(20) UNSIGNED NULL DEFAULT 0 COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
