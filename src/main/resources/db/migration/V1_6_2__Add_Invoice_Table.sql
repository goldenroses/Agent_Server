CREATE SEQUENCE invoice_id_seq;
CREATE TABLE "invoice" (
  id INT PRIMARY KEY NOT NULL DEFAULT nextval('invoice_id_seq'),
  account INTEGER DEFAULT NULL,
  invoice_name VARCHAR(255) DEFAULT NULL,
  invoice_number VARCHAR(255) DEFAULT NULL,
  invoice_amount VARCHAR(255) DEFAULT NULL,
  invoice_date VARCHAR(255) DEFAULT NULL,
  status INTEGER DEFAULT NULL,
  water_payment VARCHAR(255) DEFAULT NULL,
  security_payment VARCHAR(255) DEFAULT NULL,
  amenities_payment VARCHAR(255) DEFAULT NULL
);
ALTER SEQUENCE invoice_id_seq OWNED BY "invoice".id;