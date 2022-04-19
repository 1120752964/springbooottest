/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : localhost:3306
 Source Schema         : clothingmall

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 18/04/2022 20:54:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for administrator
-- ----------------------------
DROP TABLE IF EXISTS `administrator`;
CREATE TABLE `administrator`  (
  `aid` int(255) NOT NULL COMMENT 'id',
  `aname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员名',
  `anumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员账号',
  `apassword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员密码',
  `acreate_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `astatus` int(1) NULL DEFAULT NULL COMMENT '是否正在访问',
  PRIMARY KEY (`aid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of administrator
-- ----------------------------
INSERT INTO `administrator` VALUES (1, 'admin', '123', '202cb962ac59075b964b07152d234b70', '2021-4-9', 0);
INSERT INTO `administrator` VALUES (2, '110', '110', '5f93f983524def3dca464469d2cf9f3e', '2021-4-9', 0);

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
  `cartid` int(11) NOT NULL AUTO_INCREMENT COMMENT '购物车id',
  `commodityname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名',
  `userunumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户账号',
  `commodityid` int(11) NULL DEFAULT NULL COMMENT '商品编号',
  `size` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '该商品大小',
  `color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '该商品颜色',
  `quantity` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '该商品数量',
  `priceforone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '该商品价格',
  `storename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提供该商品的店铺名',
  `colorimg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品颜色对应的图片',
  `cost` int(11) NULL DEFAULT NULL COMMENT '该条商品对应的总价格',
  PRIMARY KEY (`cartid`) USING BTREE,
  INDEX `b`(`userunumber`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES (1, '2018春秋新款V领修身丝绒西装女 双排扣复古极简中长款工装外套', '170705106', 6, 'S', '酒红色', '13', '638', 'Z老板 ZIA ZHANG', 'http://gd4.alicdn.com/imgextra/i1/97182647/O1CN01zoNOKF1VQMtg0ToSv_!!97182647.jpg_30x30.jpg', 8294);
INSERT INTO `cart` VALUES (8, '2021春装新款长袖西服短款外套修身格子小西装女韩版小个子上衣', 'undefined', 7, 'M', '粉色格子西装', '10', '63', '侃侃衣诚旗舰店', 'http://img.alicdn.com/imgextra/i1/842994830/O1CN01DGzttc1lYBaWCPM7I_!!842994830.jpg_40x40q90.jpg', 630);
INSERT INTO `cart` VALUES (9, '思洛町修身收腰毛呢西装外套女2021新款春季英伦风千鸟格羊毛西服', 'undefined', 1, '40', '咖啡千鸟格', '3', '399', '怡情姿旗舰店', 'http://img.alicdn.com/imgextra/i1/2201549839978/O1CN012vWyU02NZyc6Xzuh5_!!2201549839978.jpg_40x40q90.jpg', 1197);
INSERT INTO `cart` VALUES (12, 'PRICH2021年夏季新款气质修身显瘦碎花设计感连衣裙女PROWB6301N', '170705107', 9, '165', '50蓝色现货', '2', '607', 'PRICH官方旗舰店', 'http://img.alicdn.com/imgextra/i2/1985598030/O1CN0107mj9C29Bn4aX9X1f_!!1985598030.jpg_40x40q90.jpg', 1214);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `commentid` int(11) NOT NULL AUTO_INCREMENT COMMENT '评价的id',
  `commodityid` int(11) NULL DEFAULT NULL COMMENT '评价的商品的id',
  `commentcontent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评价的内容',
  `commentusernumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评价的用户的id',
  `gradetocommdity` int(255) NULL DEFAULT NULL COMMENT '用户对商品的打分',
  `createdtime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `commentusername` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评价的用户的用户名',
  PRIMARY KEY (`commentid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (2, 8, '面料很棒', '170705107', 5, '2021-05-11 13:16:08', '李东亮');
INSERT INTO `comment` VALUES (3, 8, '服务态度很好', '170705107', 4, '2021-05-11 13:25:22', '李东亮');
INSERT INTO `comment` VALUES (5, 8, '隑錒', '170705107', 1, '2021-05-11 13:31:55', '李东亮');
INSERT INTO `comment` VALUES (6, 1, '一般般', '170705107', 3, '2021-05-11 14:09:56', '李东亮');
INSERT INTO `comment` VALUES (7, 1, '芜湖', '170705107', 3, '2021-05-11 14:11:26', '李东亮');
INSERT INTO `comment` VALUES (8, 1, '哎哎哎', '170705107', 4, '2021-05-11 14:12:03', '李东亮');
INSERT INTO `comment` VALUES (9, 1, '哎哎哎', '170705107', 2, '2021-05-11 14:14:15', '李东亮');
INSERT INTO `comment` VALUES (10, 1, '雀食蟀', '170705107', 5, '2021-05-13 15:25:14', '李东亮');
INSERT INTO `comment` VALUES (11, 1, 'qweqwe', '170705107', 4, '2021-10-08 23:19:20', '李东亮');
INSERT INTO `comment` VALUES (12, 1, 'qweqwe', '170705107', 3, '2022-04-18 20:46:51', '李东亮');

-- ----------------------------
-- Table structure for commodity
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity`  (
  `cid` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品编号',
  `cname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品描述',
  `coldprice` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '打折前价格',
  `cnowprice` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当前价格',
  `csales` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '销量',
  `caddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发货地',
  `cquantity` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '库存',
  `cimages` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片url',
  `cstatus` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品申请状态(未通过,已通过)',
  `storename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提供该商品的商铺名',
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity
-- ----------------------------
INSERT INTO `commodity` VALUES (1, '思洛町修身收腰毛呢西装外套女2021新款春季英伦风千鸟格羊毛西服', '422', '399', '242', '上海', '168', 'https://img.alicdn.com/imgextra/i4/417790449/O1CN01S8RXas1FBgTWXIbah_!!417790449.jpg_400x400.jpg', '已通过', '怡情姿旗舰店');
INSERT INTO `commodity` VALUES (5, '2021春装新款长袖西服短款外套修身格子小西装女韩版小个子上衣', '298', '72', '257', '安徽合肥', '6317', 'https://img.alicdn.com/imgextra/i1/842994830/O1CN01t4D0Xn1lYBZzi5XLL_!!0-item_pic.jpg_430x430q90.jpg', '已通过', '侃侃衣诚旗舰店');
INSERT INTO `commodity` VALUES (7, '2021春装新款长袖西服短款外套修身格子小西装女韩版小个子上衣', '168', '63', '492', '安徽合肥', '3288', 'https://img.alicdn.com/imgextra/i1/842994830/O1CN01VChjrV1lYBaXpHKpC_!!842994830-0-lubanu-s.jpg_430x430q90.jpg', '已通过', '侃侃衣诚旗舰店');
INSERT INTO `commodity` VALUES (8, '韩国显瘦高腰蜜桃臀牛仔裤 后背纽扣破洞弹力修身铅笔裤2021新款', '398', '398', '477', '广东深圳', '716', 'https://gd2.alicdn.com/imgextra/i1/0/O1CN01VUrYQO1nqclJEa2Xx_!!0-item_pic.jpg_400x400.jpg', '已通过', '锐雪加');
INSERT INTO `commodity` VALUES (9, 'PRICH2021年夏季新款气质修身显瘦碎花设计感连衣裙女PROWB6301N', '1080', '607', '531', '上海', '1284', 'https://img.alicdn.com/imgextra/https://img.alicdn.com/bao/uploaded/i4/1985598030/O1CN019mSqdq29Bn5D9uuUc_!!1985598030.jpg_430x430q90.jpg', '已通过', 'PRICH官方旗舰店');
INSERT INTO `commodity` VALUES (10, '气质碎花大码真丝连衣裙洋气翻领桑蚕丝系腰带中长款长裙2021', '289', '289', '41', '浙江湖州', '24', 'https://img.alicdn.com/imgextra/i3/144183500/O1CN01a90t1L1bj2nBEnpB0_!!144183500.jpg_400x400.jpg', '已通过', '执着真丝');
INSERT INTO `commodity` VALUES (11, '2021春季女新款印花雪纺连衣裙九分袖立领单排扣A字裙碎花中长裙', '296', '296', '4', '浙江杭州 ', '1999', 'https://img.alicdn.com/imgextra/i3/279832726/O1CN01IOxyhg1W0YIQzORI0_!!279832726.jpg_400x400.jpg', '已通过', 'ManMans studio');
INSERT INTO `commodity` VALUES (31, '善己新款显瘦卫衣短款夹克衫女春秋宽松BF风连帽拉链开衫女士外套', '298', '238', '85', '北京', '53', 'https://img.alicdn.com/imgextra/i2/47244283/O1CN01s9t1Gn1hVf0fBLXmY_!!0-saturn_solar.jpg_468x468q75.jpg', '已通过', '怡情姿旗舰店');
INSERT INTO `commodity` VALUES (36, '海军风翻领连衣裙夏季2021新款法式复古小众收腰显瘦气质A字裙子', '158', '158', '300', ' 湖北武汉', '187', 'https://img.alicdn.com/imgextra/i3/13908605/O1CN01Hcz4C52DR8oj8Slga_!!0-saturn_solar.jpg_468x468q75.jpg_.webp', '已通过', '怡情姿旗舰店');
INSERT INTO `commodity` VALUES (37, '120405 秋铭薇女装外套 单排扣长袖翻领单排扣小西装外套通勤风', '110', '43', '27', '广东深圳', '38', 'https://img.alicdn.com/imgextra/i4/11413658/O1CN01MiHK1B1ctPSXY8NQL_!!0-saturn_solar.jpg_468x468q75.jpg_.webp', '已通过', '深圳铭薇服饰');
INSERT INTO `commodity` VALUES (38, '明星同款蕾丝小西装女夏显瘦薄款西服百折裙两件套OL套裙女', '328', '198', '2', '广东广州', '14', 'https://img.alicdn.com/imgextra/i2/14219353/O1CN01PuAfdi2Ixj0eYycBd_!!0-saturn_solar.jpg_468x468q75.jpg_.webp', '已通过', '衣家伊人');
INSERT INTO `commodity` VALUES (39, '', '', '', '', '', '', '', '未通过', '怡情姿旗舰店');

-- ----------------------------
-- Table structure for commodity_detail
-- ----------------------------
DROP TABLE IF EXISTS `commodity_detail`;
CREATE TABLE `commodity_detail`  (
  `commodity_detail_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `commoditycnumber` int(11) NULL DEFAULT NULL COMMENT '绑定的commodity外键',
  `commodity_quantity` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '该种商品的库存',
  `commoditysize` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '该种商品的大小型号',
  `commoditycolorcimage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对应的颜色图片url',
  `commoditycolortext` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对应颜色名称',
  PRIMARY KEY (`commodity_detail_id`) USING BTREE,
  INDEX `commodity->commodity_detail`(`commoditycnumber`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 151 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity_detail
-- ----------------------------
INSERT INTO `commodity_detail` VALUES (1, 1, '160', '36', 'http://img.alicdn.com/imgextra/i1/2201549839978/O1CN012vWyU02NZyc6Xzuh5_!!2201549839978.jpg_40x40q90.jpg', '咖啡千鸟格');
INSERT INTO `commodity_detail` VALUES (2, 1, '100', '38', 'http://img.alicdn.com/imgextra/i1/2201549839978/O1CN012vWyU02NZyc6Xzuh5_!!2201549839978.jpg_40x40q90.jpg', '咖啡千鸟格');
INSERT INTO `commodity_detail` VALUES (3, 1, '55', '40', 'http://img.alicdn.com/imgextra/i1/2201549839978/O1CN012vWyU02NZyc6Xzuh5_!!2201549839978.jpg_40x40q90.jpg', '咖啡千鸟格');
INSERT INTO `commodity_detail` VALUES (4, 1, '105', '43', 'http://img.alicdn.com/imgextra/i1/2201549839978/O1CN012vWyU02NZyc6Xzuh5_!!2201549839978.jpg_40x40q90.jpg', '咖啡千鸟格');
INSERT INTO `commodity_detail` VALUES (5, 5, '555', 'S', 'http://img.alicdn.com/imgextra/i4/842994830/O1CN01sUmj6G1lYBZxTW8iQ_!!842994830.jpg_40x40q90.jpg', '蓝格');
INSERT INTO `commodity_detail` VALUES (6, 5, '552', 'M', 'http://img.alicdn.com/imgextra/i4/842994830/O1CN01sUmj6G1lYBZxTW8iQ_!!842994830.jpg_40x40q90.jpg', '蓝格');
INSERT INTO `commodity_detail` VALUES (7, 5, '551', 'L', 'http://img.alicdn.com/imgextra/i4/842994830/O1CN01sUmj6G1lYBZxTW8iQ_!!842994830.jpg_40x40q90.jpg', '蓝格');
INSERT INTO `commodity_detail` VALUES (8, 5, '551', 'XL', 'http://img.alicdn.com/imgextra/i4/842994830/O1CN01sUmj6G1lYBZxTW8iQ_!!842994830.jpg_40x40q90.jpg', '蓝格');
INSERT INTO `commodity_detail` VALUES (9, 5, '549', 'XXL', 'http://img.alicdn.com/imgextra/i4/842994830/O1CN01sUmj6G1lYBZxTW8iQ_!!842994830.jpg_40x40q90.jpg', '蓝格');
INSERT INTO `commodity_detail` VALUES (10, 5, '550', '3XL', 'http://img.alicdn.com/imgextra/i4/842994830/O1CN01sUmj6G1lYBZxTW8iQ_!!842994830.jpg_40x40q90.jpg', '蓝格');
INSERT INTO `commodity_detail` VALUES (11, 5, '502', 'S', 'http://img.alicdn.com/imgextra/i2/842994830/O1CN01tcbqKO1lYBZzhcX3r_!!842994830.jpg_40x40q90.jpg', '红格');
INSERT INTO `commodity_detail` VALUES (12, 5, '504', 'M', 'http://img.alicdn.com/imgextra/i2/842994830/O1CN01tcbqKO1lYBZzhcX3r_!!842994830.jpg_40x40q90.jpg', '红格');
INSERT INTO `commodity_detail` VALUES (13, 5, '501', 'L', 'http://img.alicdn.com/imgextra/i2/842994830/O1CN01tcbqKO1lYBZzhcX3r_!!842994830.jpg_40x40q90.jpg', '红格');
INSERT INTO `commodity_detail` VALUES (14, 5, '503', 'XL', 'http://img.alicdn.com/imgextra/i2/842994830/O1CN01tcbqKO1lYBZzhcX3r_!!842994830.jpg_40x40q90.jpg', '红格');
INSERT INTO `commodity_detail` VALUES (15, 5, '499', 'XXL', 'http://img.alicdn.com/imgextra/i2/842994830/O1CN01tcbqKO1lYBZzhcX3r_!!842994830.jpg_40x40q90.jpg', '红格');
INSERT INTO `commodity_detail` VALUES (16, 5, '500', '3XL', 'http://img.alicdn.com/imgextra/i2/842994830/O1CN01tcbqKO1lYBZzhcX3r_!!842994830.jpg_40x40q90.jpg', '红格');
INSERT INTO `commodity_detail` VALUES (18, 7, '552', 'S', 'http://img.alicdn.com/imgextra/i4/842994830/O1CN01QMKjWk1lYBaQzOWdW_!!842994830.jpg_40x40q90.jpg', '七分袖卡其格子西装');
INSERT INTO `commodity_detail` VALUES (19, 7, '546', 'M', 'http://img.alicdn.com/imgextra/i4/842994830/O1CN01QMKjWk1lYBaQzOWdW_!!842994830.jpg_40x40q90.jpg', '七分袖卡其格子西装');
INSERT INTO `commodity_detail` VALUES (20, 7, '539', 'L', 'http://img.alicdn.com/imgextra/i4/842994830/O1CN01QMKjWk1lYBaQzOWdW_!!842994830.jpg_40x40q90.jpg', '七分袖卡其格子西装');
INSERT INTO `commodity_detail` VALUES (21, 7, '550', 'S', 'http://img.alicdn.com/imgextra/i1/842994830/O1CN01DGzttc1lYBaWCPM7I_!!842994830.jpg_40x40q90.jpg', '粉色格子西装');
INSERT INTO `commodity_detail` VALUES (22, 7, '549', 'M', 'http://img.alicdn.com/imgextra/i1/842994830/O1CN01DGzttc1lYBaWCPM7I_!!842994830.jpg_40x40q90.jpg', '粉色格子西装');
INSERT INTO `commodity_detail` VALUES (23, 7, '552', 'L', 'http://img.alicdn.com/imgextra/i1/842994830/O1CN01DGzttc1lYBaWCPM7I_!!842994830.jpg_40x40q90.jpg', '粉色格子西装');
INSERT INTO `commodity_detail` VALUES (24, 8, '52', 'M', 'http://gd4.alicdn.com/imgextra/i3/26285141/O1CN012WwIks1nqclIBfvkz_!!26285141.jpg_30x30.jpg', '白色');
INSERT INTO `commodity_detail` VALUES (25, 8, '96', 'L', 'http://gd4.alicdn.com/imgextra/i3/26285141/O1CN012WwIks1nqclIBfvkz_!!26285141.jpg_30x30.jpg', '白色');
INSERT INTO `commodity_detail` VALUES (26, 8, '124', 'XL', 'http://gd4.alicdn.com/imgextra/i3/26285141/O1CN012WwIks1nqclIBfvkz_!!26285141.jpg_30x30.jpg', '白色');
INSERT INTO `commodity_detail` VALUES (27, 8, '130', 'M', 'http://gd1.alicdn.com/imgextra/i4/26285141/O1CN01OBNMzi1nqcl6r3asX_!!26285141.jpg_30x30.jpg', '黑色');
INSERT INTO `commodity_detail` VALUES (28, 8, '157', 'L', 'http://gd1.alicdn.com/imgextra/i4/26285141/O1CN01OBNMzi1nqcl6r3asX_!!26285141.jpg_30x30.jpg', '黑色');
INSERT INTO `commodity_detail` VALUES (29, 8, '157', 'XL', 'http://gd1.alicdn.com/imgextra/i4/26285141/O1CN01OBNMzi1nqcl6r3asX_!!26285141.jpg_30x30.jpg', '黑色');
INSERT INTO `commodity_detail` VALUES (30, 9, '46', '155', 'http://img.alicdn.com/imgextra/i1/1985598030/O1CN01GJ2Qe529Bn4WMSdNG_!!1985598030.jpg_40x40q90.jpg', '19黑色现货');
INSERT INTO `commodity_detail` VALUES (31, 9, '85', '160', 'http://img.alicdn.com/imgextra/i1/1985598030/O1CN01GJ2Qe529Bn4WMSdNG_!!1985598030.jpg_40x40q90.jpg', '19黑色现货');
INSERT INTO `commodity_detail` VALUES (32, 9, '97', '165', 'http://img.alicdn.com/imgextra/i1/1985598030/O1CN01GJ2Qe529Bn4WMSdNG_!!1985598030.jpg_40x40q90.jpg', '19黑色现货');
INSERT INTO `commodity_detail` VALUES (33, 9, '47', '170', 'http://img.alicdn.com/imgextra/i1/1985598030/O1CN01GJ2Qe529Bn4WMSdNG_!!1985598030.jpg_40x40q90.jpg', '19黑色现货');
INSERT INTO `commodity_detail` VALUES (34, 9, '76', '155', 'http://img.alicdn.com/imgextra/i3/1985598030/O1CN01Eehl0S29Bn4TKziHO_!!1985598030.jpg_40x40q90.jpg', '35米色现货');
INSERT INTO `commodity_detail` VALUES (35, 9, '200', '160', 'http://img.alicdn.com/imgextra/i3/1985598030/O1CN01Eehl0S29Bn4TKziHO_!!1985598030.jpg_40x40q90.jpg', '35米色现货');
INSERT INTO `commodity_detail` VALUES (36, 9, '238', '165', 'http://img.alicdn.com/imgextra/i3/1985598030/O1CN01Eehl0S29Bn4TKziHO_!!1985598030.jpg_40x40q90.jpg', '35米色现货');
INSERT INTO `commodity_detail` VALUES (37, 9, '101', '170', 'http://img.alicdn.com/imgextra/i3/1985598030/O1CN01Eehl0S29Bn4TKziHO_!!1985598030.jpg_40x40q90.jpg', '35米色现货');
INSERT INTO `commodity_detail` VALUES (38, 9, '57', '155', 'http://img.alicdn.com/imgextra/i2/1985598030/O1CN0107mj9C29Bn4aX9X1f_!!1985598030.jpg_40x40q90.jpg', '50蓝色现货');
INSERT INTO `commodity_detail` VALUES (39, 9, '143', '160', 'http://img.alicdn.com/imgextra/i2/1985598030/O1CN0107mj9C29Bn4aX9X1f_!!1985598030.jpg_40x40q90.jpg', '50蓝色现货');
INSERT INTO `commodity_detail` VALUES (40, 9, '131', '165', 'http://img.alicdn.com/imgextra/i2/1985598030/O1CN0107mj9C29Bn4aX9X1f_!!1985598030.jpg_40x40q90.jpg', '50蓝色现货');
INSERT INTO `commodity_detail` VALUES (41, 9, '63', '170', 'http://img.alicdn.com/imgextra/i2/1985598030/O1CN0107mj9C29Bn4aX9X1f_!!1985598030.jpg_40x40q90.jpg', '50蓝色现货');
INSERT INTO `commodity_detail` VALUES (42, 10, '15', '165/88B(L)', 'http://gd4.alicdn.com/imgextra/i3/144183500/O1CN01qSIjYe1bj2n89yr5Z_!!144183500.jpg_30x30.jpg', '蓝黑花瓣');
INSERT INTO `commodity_detail` VALUES (43, 10, '9', '170/92B(XL)', 'http://gd4.alicdn.com/imgextra/i3/144183500/O1CN01qSIjYe1bj2n89yr5Z_!!144183500.jpg_30x30.jpg', '蓝黑花瓣');
INSERT INTO `commodity_detail` VALUES (44, 11, '1855', 'S', 'http://gd3.alicdn.com/imgextra/i3/279832726/O1CN01YWrUlZ1W0YIBRGL5X_!!279832726.jpg_30x30.jpg', '黑色');
INSERT INTO `commodity_detail` VALUES (45, 11, '81', 'S', 'http://gd3.alicdn.com/imgextra/i2/279832726/O1CN01eFmXk81W0YIEuOxWO_!!279832726.jpg_30x30.jpg', '驼色');
INSERT INTO `commodity_detail` VALUES (46, 11, '63', 'M', 'http://gd3.alicdn.com/imgextra/i2/279832726/O1CN01eFmXk81W0YIEuOxWO_!!279832726.jpg_30x30.jpg', '驼色');
INSERT INTO `commodity_detail` VALUES (88, 31, '34', 'M', 'http://img.alicdn.com/imgextra/i2/1785103099/O1CN01MynXgW1YlO480BNud_!!1785103099.jpg_40x40q90.jpg', '蓝色');
INSERT INTO `commodity_detail` VALUES (89, 31, '19', 'L', 'http://img.alicdn.com/imgextra/i2/1785103099/O1CN01MynXgW1YlO480BNud_!!1785103099.jpg_40x40q90.jpg', '蓝色');
INSERT INTO `commodity_detail` VALUES (137, 36, '86', 'S', 'http://img.alicdn.com/imgextra/i3/250118929/O1CN01XhEQs32FpX8ScRqMv_!!250118929.jpg_40x40q90.jpg', '蓝色');
INSERT INTO `commodity_detail` VALUES (138, 36, '51', 'M', 'http://img.alicdn.com/imgextra/i3/250118929/O1CN01XhEQs32FpX8ScRqMv_!!250118929.jpg_40x40q90.jpg', '蓝色');
INSERT INTO `commodity_detail` VALUES (139, 36, '50', 'L', 'http://img.alicdn.com/imgextra/i3/250118929/O1CN01XhEQs32FpX8ScRqMv_!!250118929.jpg_40x40q90.jpg', '蓝色');
INSERT INTO `commodity_detail` VALUES (140, 37, '21', 'S', 'http://gd3.alicdn.com/imgextra/i4/112632168/O1CN01b6InIV1RszJ0cVxee_!!112632168.jpg_30x30.jpg', '紫红');
INSERT INTO `commodity_detail` VALUES (141, 37, '14', 'M', 'http://gd3.alicdn.com/imgextra/i4/112632168/O1CN01b6InIV1RszJ0cVxee_!!112632168.jpg_30x30.jpg', '紫红');
INSERT INTO `commodity_detail` VALUES (142, 37, '1', 'S', 'http://gd1.alicdn.com/imgextra/i1/112632168/O1CN01L3GOMN1RszIw5sJ0O_!!112632168.jpg_30x30.jpg', '暗红');
INSERT INTO `commodity_detail` VALUES (143, 37, '2', 'M', 'http://gd1.alicdn.com/imgextra/i1/112632168/O1CN01L3GOMN1RszIw5sJ0O_!!112632168.jpg_30x30.jpg', '暗红');
INSERT INTO `commodity_detail` VALUES (144, 38, '8', 'L', 'http:////gd2.alicdn.com/imgextra/i3/307804503/O1CN01djD8bH1j8Q0vnsVdf_!!307804503.jpg_30x30.jpg', '白色');
INSERT INTO `commodity_detail` VALUES (145, 38, '6', 'XL', 'http:////gd2.alicdn.com/imgextra/i3/307804503/O1CN01djD8bH1j8Q0vnsVdf_!!307804503.jpg_30x30.jpg', '白色');
INSERT INTO `commodity_detail` VALUES (146, 39, '', '', '', '');
INSERT INTO `commodity_detail` VALUES (147, 39, '', '', '', '');
INSERT INTO `commodity_detail` VALUES (148, 39, '', '', '', '');
INSERT INTO `commodity_detail` VALUES (149, 39, '', '', '', '');
INSERT INTO `commodity_detail` VALUES (150, 39, '', '', '', '');

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail`  (
  `orderdetailid` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `commoditycnumber` int(11) NULL DEFAULT NULL COMMENT '该商品的商品编号(外键)',
  `orderonumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号(外键)',
  `commoditycolor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下单的该商品的颜色',
  `commoditycount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下单的该商品的数量',
  `commoditysize` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下单的该商品的型号',
  `ordercommoditycount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '该商品共花的金额',
  `commoditystatus` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '该订单状态(0:未支付;1:支付后未发货;2:已发货;3:已送达4.已评价',
  `commodityname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名',
  `commoditypriceforone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品单价',
  PRIMARY KEY (`orderdetailid`) USING BTREE,
  INDEX `order_detail->order`(`orderonumber`) USING BTREE,
  INDEX `order_detail->commodity`(`commoditycnumber`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 263 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES (246, 8, '20210510114634', '白色', '1', 'M', '398', '已支付', '韩国显瘦高腰蜜桃臀牛仔裤 后背纽扣破洞弹力修身铅笔裤2021新款', '398');
INSERT INTO `order_detail` VALUES (247, 1, '20210510114634', '咖啡千鸟格', '1', '43', '399', '已收货', '思洛町修身收腰毛呢西装外套女2021新款春季英伦风千鸟格羊毛西服', '399');
INSERT INTO `order_detail` VALUES (248, 36, '20210510114634', '蓝色', '1', 'S', '158', '已支付', '海军风翻领连衣裙夏季2021新款法式复古小众收腰显瘦气质A字裙子', '158');
INSERT INTO `order_detail` VALUES (249, 31, '20210511214248', '蓝色', '1', 'M', '238', '已发货', '善己新款显瘦卫衣短款夹克衫女春秋宽松BF风连帽拉链开衫女士外套', '238');
INSERT INTO `order_detail` VALUES (250, 31, '20210511215337', '蓝色', '1', 'M', '238', '未支付', '善己新款显瘦卫衣短款夹克衫女春秋宽松BF风连帽拉链开衫女士外套', '238');
INSERT INTO `order_detail` VALUES (251, 31, '20210511215404', '蓝色', '1', 'M', '238', '已发货', '善己新款显瘦卫衣短款夹克衫女春秋宽松BF风连帽拉链开衫女士外套', '238');
INSERT INTO `order_detail` VALUES (252, 31, '20210511215642', '蓝色', '1', 'M', '238', '已发货', '善己新款显瘦卫衣短款夹克衫女春秋宽松BF风连帽拉链开衫女士外套', '238');
INSERT INTO `order_detail` VALUES (253, 31, '20210511215903', '蓝色', '1', 'M', '238', '已发货', '善己新款显瘦卫衣短款夹克衫女春秋宽松BF风连帽拉链开衫女士外套', '238');
INSERT INTO `order_detail` VALUES (254, 5, '20210513105058', '蓝格', '1', 'L', '72', '已支付', '2021春装新款长袖西服短款外套修身格子小西装女韩版小个子上衣', '72');
INSERT INTO `order_detail` VALUES (255, 31, '20210513111854', '蓝色', '1', 'L', '238', '已发货', '善己新款显瘦卫衣短款夹克衫女春秋宽松BF风连帽拉链开衫女士外套', '238');
INSERT INTO `order_detail` VALUES (256, 7, '20210601093624', '粉色格子西装', '3', 'M', '189', '已支付', '2021春装新款长袖西服短款外套修身格子小西装女韩版小个子上衣', '63');
INSERT INTO `order_detail` VALUES (257, 31, '20210601093712', '蓝色', '1', 'M', '238', '已发货', '善己新款显瘦卫衣短款夹克衫女春秋宽松BF风连帽拉链开衫女士外套', '238');
INSERT INTO `order_detail` VALUES (258, 1, '20211008231831', '咖啡千鸟格', '2', '38', '798', '已发货', '思洛町修身收腰毛呢西装外套女2021新款春季英伦风千鸟格羊毛西服', '399');
INSERT INTO `order_detail` VALUES (259, 8, '20220418200355', '黑色', '3', 'M', '1194', '已支付', '韩国显瘦高腰蜜桃臀牛仔裤 后背纽扣破洞弹力修身铅笔裤2021新款', '398');
INSERT INTO `order_detail` VALUES (260, 5, '20220418201724', '红格', '1', 'XXL', '72', '未支付', '2021春装新款长袖西服短款外套修身格子小西装女韩版小个子上衣', '72');
INSERT INTO `order_detail` VALUES (261, 7, '20220418201938', '七分袖卡其格子西装', '1', 'M', '63', '未支付', '2021春装新款长袖西服短款外套修身格子小西装女韩版小个子上衣', '63');
INSERT INTO `order_detail` VALUES (262, 7, '20220418205021', '七分袖卡其格子西装', '2', 'S', '126', '已支付', '2021春装新款长袖西服短款外套修身格子小西装女韩版小个子上衣', '63');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `oid` int(255) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userunumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下单的用户账号',
  `createdtime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `ocost` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '该订单所花总金额',
  `useraddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下单的用户的地址',
  `onumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `consigneename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收件人姓名',
  `phonenumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收件人手机号',
  PRIMARY KEY (`oid`) USING BTREE,
  INDEX `user_order`(`userunumber`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 125 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (110, '170705107', '2021-05-10 11:46:34', '955', '河北省沧州市新华区第三中学对面新兴小学院内东单302(默认地址)', '20210510114634', '李东亮', '15369801448');
INSERT INTO `orders` VALUES (111, '170705107', '2021-05-11 21:42:48', '238', '河北省沧州市新华区第三中学对面新兴小学院内东单302(默认地址)', '20210511214248', '李东亮', '15369801448');
INSERT INTO `orders` VALUES (112, '170705107', '2021-05-11 21:53:37', '238', '河北省沧州市新华区第三中学对面新兴小学院内东单302(默认地址)', '20210511215337', '李东亮', '15369801448');
INSERT INTO `orders` VALUES (113, '170705107', '2021-05-11 21:54:04', '238', '河北省沧州市新华区第三中学对面新兴小学院内东单302(默认地址)', '20210511215404', '李东亮', '15369801448');
INSERT INTO `orders` VALUES (114, '170705107', '2021-05-11 21:56:42', '238', '河北省沧州市新华区第三中学对面新兴小学院内东单302(默认地址)', '20210511215642', '李东亮', '15369801448');
INSERT INTO `orders` VALUES (115, '170705107', '2021-05-11 21:59:04', '238', '河北省沧州市新华区第三中学对面新兴小学院内东单302(默认地址)', '20210511215903', '李东亮', '15369801448');
INSERT INTO `orders` VALUES (116, '170705107', '2021-05-13 10:50:58', '72', '天津市市辖区和平区(默认地址)', '20210513105058', '李东亮', '15369801448');
INSERT INTO `orders` VALUES (117, '170705107', '2021-05-13 11:18:54', '238', '河北省沧州市市辖区东方骏景东单211', '20210513111854', '李放', '13363665581');
INSERT INTO `orders` VALUES (118, '170705107', '2021-06-01 09:36:24', '189', '河北省邯郸市峰峰矿区', '20210601093624', '张三', '13013223123');
INSERT INTO `orders` VALUES (119, '170705107', '2021-06-01 09:37:12', '238', '河北省沧州市市辖区东方骏景东单211', '20210601093712', '李放', '13363665581');
INSERT INTO `orders` VALUES (120, '170705107', '2021-10-08 23:18:31', '798', '河北省沧州市市辖区东方骏景东单211', '20211008231831', '李放', '13363665581');
INSERT INTO `orders` VALUES (121, '170705107', '2022-04-18 20:03:55', '1194', '内蒙古自治区赤峰市松山区', '20220418200355', '胡胜超', '15615611561');
INSERT INTO `orders` VALUES (122, '170705107', '2022-04-18 20:17:24', '72', '天津市市辖区和平区', '20220418201724', '李东亮', '15369801448');
INSERT INTO `orders` VALUES (123, '170705107', '2022-04-18 20:19:38', '63', '河北省沧州市市辖区东方骏景东单211(默认地址)', '20220418201938', '李东亮', '15369801448');
INSERT INTO `orders` VALUES (124, '170705107', '2022-04-18 20:50:21', '126', '河北省邯郸市峰峰矿区(默认地址)', '20220418205021', '李东亮', '15369801448');

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store`  (
  `storeid` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺名',
  `storeshotscore` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺热度',
  `storenumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品账号',
  `storepassword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商家密码',
  `storeproduce` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商家介绍',
  `commentid` int(11) NULL DEFAULT NULL COMMENT '评价ID',
  `storeaddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商家地址',
  PRIMARY KEY (`storeid`) USING BTREE,
  INDEX `store_sname`(`sname`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of store
-- ----------------------------
INSERT INTO `store` VALUES (1, '怡情姿旗舰店', '0', '170705107', '8068c76c7376bc08e2836ab26359d4a4', '这个店家很懒,没有介绍', NULL, NULL);
INSERT INTO `store` VALUES (2, '侃侃衣诚旗舰店', NULL, '123', '202cb962ac59075b964b07152d234b70', '希望胡jj别分', NULL, NULL);
INSERT INTO `store` VALUES (3, '123', NULL, '12345', '202cb962ac59075b964b07152d234b70', '123', NULL, NULL);
INSERT INTO `store` VALUES (4, '1', NULL, '1', 'c4ca4238a0b923820dcc509a6f75849b', '1', NULL, '北京市市辖区');
INSERT INTO `store` VALUES (5, '深圳铭薇服饰', NULL, '12', 'c20ad4d76fe97759aa27a0c99bff6710', '嘤嘤嘤', NULL, '广东省深圳市');
INSERT INTO `store` VALUES (6, '衣家伊人', NULL, '170705101', '8068c76c7376bc08e2836ab26359d4a4', '衣家伊人', NULL, '广东省广州市');

-- ----------------------------
-- Table structure for store_detail
-- ----------------------------
DROP TABLE IF EXISTS `store_detail`;
CREATE TABLE `store_detail`  (
  `store_commodity_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `store_sname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺名',
  `commodity_cnumber` int(11) NULL DEFAULT NULL COMMENT '店铺发布的商品',
  PRIMARY KEY (`store_commodity_id`) USING BTREE,
  INDEX `store->store_detail`(`store_sname`) USING BTREE,
  INDEX `commodity->commodity_cnumber`(`commodity_cnumber`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of store_detail
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `unumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `upassword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `usex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `utelphone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `upaypassword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `uaddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '默认地址',
  PRIMARY KEY (`uid`) USING BTREE,
  INDEX `unumber`(`unumber`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '李东亮', '170705107', 'qweasd123', '男', '15369801448', '111', '河北省沧州市新华区第三中学对面新兴小学院内东单302');
INSERT INTO `user` VALUES (2, '胡胜超', '1', '47bce5c74f589f4867dbd57e9ca9f808', '1', '1', '111', NULL);
INSERT INTO `user` VALUES (17, 'a', 'a', 'a', 'a', 'a', 'a', NULL);
INSERT INTO `user` VALUES (18, 'b', 'b', '92eb5ffee6ae2fec3ad71c777531578f', 'b', 'b', 'b', NULL);
INSERT INTO `user` VALUES (19, '陈世伟', '9999', '202cb962ac59075b964b07152d234b70', NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (20, '', '', 'd41d8cd98f00b204e9800998ecf8427e', '', '', '156236', NULL);
INSERT INTO `user` VALUES (21, '', '', 'd41d8cd98f00b204e9800998ecf8427e', '', '', '', NULL);
INSERT INTO `user` VALUES (22, '123', '123', '202cb962ac59075b964b07152d234b70', '', '15368497844', '123123', NULL);
INSERT INTO `user` VALUES (23, '', '', 'd41d8cd98f00b204e9800998ecf8427e', '', '', '', NULL);
INSERT INTO `user` VALUES (24, '123', '12', '202cb962ac59075b964b07152d234b70', '男', '15368461234', '123123', NULL);
INSERT INTO `user` VALUES (25, '陈世伟', '1234', '202cb962ac59075b964b07152d234b70', '男', '15648964513', '123123', NULL);
INSERT INTO `user` VALUES (26, '范建成', '170705104', '684cbf5d2dbab72186a525c688e04e9a', '男', '17070510415', '123123', '河北省衡水市河北衡水高新技术产业开发区东莞嫖到死1314号');
INSERT INTO `user` VALUES (27, '陈瑾皓', '170705101', '202cb962ac59075b964b07152d234b70', '男', '15664894564', '245654', '河北省保定市易县651');
INSERT INTO `user` VALUES (28, '侯诗尧', '170705106', '202cb962ac59075b964b07152d234b70', '男', '15536494321', '123', '北京市市辖区西城区搜ID金佛寺殴打佛寺对方法');
INSERT INTO `user` VALUES (29, 'asd', '1707051075', '7fc56270e7a70fa81a5935b72eacbe29', '男', '15369801448', 'a', '北京市市辖区西城区');
INSERT INTO `user` VALUES (30, '张三', '1343123', '8068c76c7376bc08e2836ab26359d4a4', '男', '13212331233', '111', '河北省秦皇岛市海港区');
INSERT INTO `user` VALUES (31, '李四', '131341541', '8068c76c7376bc08e2836ab26359d4a4', '男', '13451341342', '111111', '河北省秦皇岛市山海关区');

-- ----------------------------
-- Table structure for user_address
-- ----------------------------
DROP TABLE IF EXISTS `user_address`;
CREATE TABLE `user_address`  (
  `useraddressid` int(11) NOT NULL AUTO_INCREMENT,
  `userunumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `useraddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isdefault` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `consigneename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人',
  `phonenumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  PRIMARY KEY (`useraddressid`) USING BTREE,
  INDEX `user->user_address`(`userunumber`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_address
-- ----------------------------
INSERT INTO `user_address` VALUES (4, '170705107', '天津市市辖区和平区', '0', '李东亮', '15369801448');
INSERT INTO `user_address` VALUES (5, '170705101', '河北省保定市易县651', '0', '李放', '16616546513');
INSERT INTO `user_address` VALUES (11, '170705106', '内蒙古自治区乌海市海南区西欧叫小区', '0', '边凤祥', '13068511563');
INSERT INTO `user_address` VALUES (12, '170705107', '内蒙古自治区赤峰市松山区', '0', '胡胜超', '15615611561');
INSERT INTO `user_address` VALUES (13, '170705107', '河北省沧州市市辖区东方骏景东单211', '0', '李放', '13363665581');
INSERT INTO `user_address` VALUES (14, '1707051075', '北京市市辖区西城区', '0', 'asd', '15369801448');
INSERT INTO `user_address` VALUES (15, '1343123', '河北省秦皇岛市海港区', '0', '张三', '13212331233');
INSERT INTO `user_address` VALUES (16, '131341541', '河北省秦皇岛市山海关区', '0', '李四', '13451341342');
INSERT INTO `user_address` VALUES (17, '170705107', '河北省邯郸市峰峰矿区', '1', '张三', '13013223123');
INSERT INTO `user_address` VALUES (18, '170705107', '河北省邯郸市峰峰矿区qweqwe', '0', 'qwe', '13549887662');

SET FOREIGN_KEY_CHECKS = 1;
