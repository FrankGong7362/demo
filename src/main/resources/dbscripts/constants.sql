/*
 Navicat Premium Dump SQL

 Source Server         : mysql80
 Source Server Type    : MySQL
 Source Server Version : 80044 (8.0.44)
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80044 (8.0.44)
 File Encoding         : 65001

 Date: 20/11/2025 02:20:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for constants
-- ----------------------------
DROP TABLE IF EXISTS `constants`;
CREATE TABLE `constants`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '1：学科 2：资料类型',
  `label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `created_time` datetime NULL DEFAULT NULL,
  `updated_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `constants` VALUES
                            (1, '1', '计算机科学', 'computer_science', '计算机科学学科', NOW(), NOW()),
                            (2, '1', '数学', 'mathematics', '数学学科', NOW(), NOW()),
                            (3, '1', '物理学', 'physics', '物理学学科', NOW(), NOW()),
                            (4, '1', '化学', 'chemistry', '化学学科', NOW(), NOW()),
                            (5, '1', '生物学', 'biology', '生物学学科', NOW(), NOW()),
                            (6, '1', '经济学', 'economics', '经济学学科', NOW(), NOW()),
                            (7, '1', '文学', 'literature', '文学学科', NOW(), NOW()),
                            (8, '1', '历史学', 'history', '历史学学科', NOW(), NOW()),
                            (9, '2', '课件', 'courseware', '教学课件资料', NOW(), NOW()),
                            (10, '2', '习题集', 'exercises', '练习题和习题集', NOW(), NOW()),
                            (11, '2', '实验指导', 'lab_guide', '实验操作指导书', NOW(), NOW()),
                            (12, '2', '参考书籍', 'reference_book', '参考书籍和文献', NOW(), NOW()),
                            (13, '2', '论文资料', 'papers', '学术论文和研究报告', NOW(), NOW()),
                            (14, '2', '视频教程', 'video', '教学视频资料', NOW(), NOW());