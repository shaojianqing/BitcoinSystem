-- BitcoinDB.block definition

CREATE TABLE `block` (
  `block_hash` varchar(100) CHARACTER SET ascii COLLATE ascii_general_ci NOT NULL,
  `prev_block_hash` varchar(100) CHARACTER SET ascii COLLATE ascii_general_ci NOT NULL,
  `merkle_root` varchar(100) NOT NULL,
  `block_height` bigint NOT NULL,
  `timestamp` bigint NOT NULL,
  `difficulty` bigint NOT NULL,
  `nonce` bigint NOT NULL,
  `version` bigint NOT NULL,
  `trx_count` bigint NOT NULL,
  `sync_status` varchar(16) NOT NULL,
  `verify_status` varchar(16) NOT NULL,
  `create_time` datetime NOT NULL,
  `modify_time` datetime NOT NULL,
  PRIMARY KEY (`block_hash`),
  UNIQUE KEY `block_hash_unique` (`block_hash`),
  KEY `block_prev_block_hash_idx` (`prev_block_hash`) USING BTREE,
  KEY `block_merkle_root_idx` (`merkle_root`) USING BTREE,
  KEY `block_block_height_idx` (`block_height`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=ascii;