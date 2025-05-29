-- BitcoinDB.transaction_input definition

CREATE TABLE `transaction_input` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `transaction_hash` varchar(100) NOT NULL,
  `from_transaction_hash` varchar(100) NOT NULL,
  `sequence` bigint NOT NULL,
  `transaction_output_index` bigint NOT NULL,
  `script_signature` text NOT NULL,
  `value` bigint NOT NULL,
  `create_time` datetime NOT NULL,
  `modify_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `transaction_hash_IDX` (`transaction_hash`) USING BTREE,
  KEY `transaction_output_index_IDX` (`transaction_output_index`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=ascii;