
CREATE TABLE `goods` (
  `id` bigint(20) auto_increment NOT NULL,
  `goods_name` varchar(128) DEFAULT NULL,
  `goods_title` varchar(128) DEFAULT NULL,
  `goods_img` varchar(128) DEFAULT NULL,
  `goods_detail` varchar(128) DEFAULT NULL,
  `goods_price` decimal(10,2) DEFAULT NULL,
  `goods_stock` int(11) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `order_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) unsigned NOT NULL COMMENT 'user id',
  `goods_id` bigint(20) unsigned NOT NULL COMMENT 'goods id',
  `delivery_addr_id` bigint(20) unsigned NOT NULL COMMENT '地址id',
  `goods_name` varchar(255) NOT NULL COMMENT '商品名称',
  `goods_count` int(10) unsigned NOT NULL COMMENT '商品数量',
  `goods_price` decimal(10,2) unsigned NOT NULL COMMENT '商品价格',
  `order_channel` tinyint(3) unsigned NOT NULL COMMENT '订单来源',
  `status` tinyint(3) unsigned NOT NULL COMMENT '设备id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `pay_date` datetime DEFAULT NULL COMMENT '支付时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='订单信息';


CREATE TABLE `seckill_goods` (
  `id` bigint(20) auto_increment NOT NULL,
  `goods_id` bigint(20) DEFAULT NULL,
  `seckill_price` decimal(10,2) DEFAULT NULL,
  `stock_count` int(11) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀商品表';


CREATE TABLE `seckill_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `goods_id` varchar(128) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀订单表';


CREATE TABLE `seckill_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `phone` bigint(11) unsigned DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 NOT NULL,
  `password` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT 'md5(md5(pass明文 + 固定salt) + salt)',
  `salt` varchar(10) CHARACTER SET utf8mb4 DEFAULT NULL,
  `head_img` varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '头像，云存储的ID',
  `register_date` datetime DEFAULT NULL COMMENT '注册时间',
  `last_login_date` datetime DEFAULT NULL COMMENT '上次登录时间',
  `login_count` int(11) unsigned DEFAULT '0' COMMENT '登陆次数',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
