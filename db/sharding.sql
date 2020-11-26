SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE DATABASE IF NOT EXISTS no_sharding DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_general_ci;
use no_sharding;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '读钓', 18, 'idudiao@163.com');
INSERT INTO `user` VALUES (2, '管理员', 16, 'admin@163.com');


CREATE DATABASE IF NOT EXISTS order_sharding DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_general_ci;
use order_sharding;

-- ----------------------------
-- Table structure for user_order_0
-- ----------------------------
DROP TABLE IF EXISTS `user_order_0`;
CREATE TABLE `user_order_0`  (
  `order_key` bigint(20) NOT NULL COMMENT '订单号',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `amount` float(9, 3) NULL DEFAULT NULL COMMENT '订单金额',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`order_key`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_order_0
-- ----------------------------
INSERT INTO `user_order_0` VALUES (1001, 2, 100.000, '2020-11-27 00:11:24');

-- ----------------------------
-- Table structure for user_order_1
-- ----------------------------
DROP TABLE IF EXISTS `user_order_1`;
CREATE TABLE `user_order_1`  (
  `order_key` bigint(20) NOT NULL COMMENT '订单号',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `amount` float(9, 3) NULL DEFAULT NULL COMMENT '订单金额',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`order_key`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_order_1
-- ----------------------------
INSERT INTO `user_order_1` VALUES (1002, 1, 200.000, '2020-11-27 00:13:08');

SET FOREIGN_KEY_CHECKS = 1;
