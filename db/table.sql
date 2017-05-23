DROP TABLE IF EXISTS t_oper_user;

CREATE TABLE `t_oper_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `created_at` datetime DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `expire_Time` bigint(20) DEFAULT NULL COMMENT '每次登录有效时间',
  `user_no` varchar(255) DEFAULT NULL COMMENT '用户编号',
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  KEY `index_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;