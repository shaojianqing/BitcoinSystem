-- BitcoinDB.transaction_address_map definition

CREATE TABLE `transaction_address_map` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(100) NOT NULL,
  `transaction_hash` varchar(100) NOT NULL,
  `type` varchar(100) NOT NULL,
  `value` bigint NOT NULL,
  `create_time` datetime NOT NULL,
  `modify_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `address_IDX` (`address`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=ascii;