-- BitcoinDB.transaction definition

CREATE TABLE `transaction` (
  `transaction_hash` varchar(100) NOT NULL,
  `message_version` bigint NOT NULL,
  `block_height` bigint NOT NULL,
  `block_hash` varchar(100) NOT NULL,
  `transaction_lock_time` bigint DEFAULT NULL,
  `verify_status` varchar(16) NOT NULL,
  `create_time` datetime NOT NULL,
  `modify_time` datetime NOT NULL,
  PRIMARY KEY (`transaction_hash`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii;