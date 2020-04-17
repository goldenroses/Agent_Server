CREATE SEQUENCE receipt_id_seq;
CREATE TABLE receipt (
  id INT PRIMARY KEY NOT NULL DEFAULT nextval('receipt_id_seq'),
  house bigint DEFAULT NULL,
  receipt_number VARCHAR(255) DEFAULT NULL,
  receipt_name VARCHAR(255) DEFAULT NULL,
  receipt_date VARCHAR(500) DEFAULT NULL
);
ALTER SEQUENCE receipt_id_seq OWNED BY receipt.id;