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

 Date: 13/04/2024 14:42:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for inis_article
-- ----------------------------
DROP TABLE IF EXISTS `inis_article`;
CREATE TABLE `inis_article`  (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` int(32) NULL DEFAULT 0 COMMENT '用户ID',
  `title` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `abstract` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '摘要',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容',
  `covers` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '封面',
  `top` int(12) NULL DEFAULT 0 COMMENT '置顶',
  `views` int(32) NULL DEFAULT 0 COMMENT '浏览量',
  `tags` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签',
  `group` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分组',
  `remark` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `editor` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'tinymce' COMMENT '编辑器',
  `audit` int(12) NULL DEFAULT 0 COMMENT '审核',
  `last_update` bigint(20) NULL DEFAULT 0 COMMENT '最后更新时间',
  `json` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '用于存储JSON数据',
  `text` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '用于存储文本数据',
  `result` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '不存储数据，用于封装返回结果',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '更新时间',
  `delete_time` bigint(20) UNSIGNED NULL DEFAULT 0 COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
