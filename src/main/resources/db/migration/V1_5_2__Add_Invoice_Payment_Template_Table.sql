CREATE SEQUENCE invoice_payment_template_id_seq;
CREATE TABLE invoice_payment_template (
  id INT PRIMARY KEY NOT NULL DEFAULT nextval('invoice_payment_template_id_seq'),
  invoice_number VARCHAR(255) DEFAULT NULL,
  invoice_amount VARCHAR(255) DEFAULT NULL,
  house_id VARCHAR(255) DEFAULT NULL,
  recurrent_payment_template_id bigint DEFAULT NULL,
  invoice_date VARCHAR(255) DEFAULT NULL
);
ALTER SEQUENCE invoice_payment_template_id_seq OWNED BY invoice_payment_template.id;