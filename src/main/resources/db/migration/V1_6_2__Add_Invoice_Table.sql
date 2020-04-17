CREATE SEQUENCE invoice_id_seq;
CREATE TABLE "invoice" (
  id INT PRIMARY KEY NOT NULL DEFAULT nextval('invoice_id_seq'),
  account_id INTEGER DEFAULT NULL,
  invoice_payment_template INTEGER DEFAULT NULL,
  status_id INTEGER DEFAULT NULL
);
ALTER SEQUENCE invoice_id_seq OWNED BY "invoice".id;