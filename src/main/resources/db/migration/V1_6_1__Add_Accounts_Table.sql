CREATE SEQUENCE account_id_seq;
CREATE TABLE "account" (
  id INT PRIMARY KEY NOT NULL DEFAULT nextval('account_id_seq'),
  house_id INTEGER DEFAULT NULL,
  invoice_id INTEGER DEFAULT NULL,
  receipt_id INTEGER DEFAULT NULL
);
ALTER SEQUENCE account_id_seq OWNED BY "account".id;