-- ----------------------------
-- Table structure for payment
-- ----------------------------
CREATE TABLE `payment`
(
    `id`     bigint(20)                                                  NOT NULL AUTO_INCREMENT COMMENT '主键',
    `serial` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '流水号',
    PRIMARY KEY (`id`) USING BTREE
)
