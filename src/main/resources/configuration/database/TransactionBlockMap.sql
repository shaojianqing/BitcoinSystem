-- BitcoinDB.transaction_block definition

CREATE TABLE `transaction_block` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `block_hash` varchar(100) NOT NULL,
  `block_height` bigint NOT NULL,
  `transaction_hash` varchar(100) NOT NULL,
  `transaction_index` bigint NOT NULL,
  `create_time` datetime NOT NULL,
  `modify_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `block_hash_IDX` (`block_hash`) USING BTREE,
  KEY `transaction_index_IDX` (`transaction_index`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=ascii;