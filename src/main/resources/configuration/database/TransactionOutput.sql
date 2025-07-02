-- BitcoinDB.transaction_output definition

CREATE TABLE `transaction_output` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `transaction_hash` varchar(100) NOT NULL,
  `script_pubKey` text NOT NULL,
  `coin_value` bigint NOT NULL,
  `create_time` datetime NOT NULL,
  `modify_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `transaction_hash_IDX` (`transaction_hash`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=ascii;