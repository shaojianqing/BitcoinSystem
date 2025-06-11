-- BitcoinDB.transaction_address definition

CREATE TABLE `transaction_address` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `transaction_hash` varchar(100) NOT NULL,
  `transaction_index` bigint NOT NULL,
  `address` varchar(256) NOT NULL,
  `address_type` varchar(100) NOT NULL,
  `coin_value` bigint NOT NULL,
  `spend_status` bool NOT NULL,
  `create_time` datetime NOT NULL,
  `modify_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `address_IDX` (`address`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=ascii;