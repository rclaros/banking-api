CREATE TABLE bank_transaction (
  bank_history_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  account_origin_id BIGINT  NOT NULL,
  account_destination_id BIGINT  NOT NULL,
  amount DECIMAL(10,2) NOT NULL,
  created datetime NOT NULL,
  PRIMARY KEY(bank_history_id),
  INDEX IX_bank_bank_history_id (bank_history_id)
)