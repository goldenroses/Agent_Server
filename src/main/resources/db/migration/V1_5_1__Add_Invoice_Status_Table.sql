CREATE SEQUENCE invoice_status_id_seq;
CREATE TABLE invoice_status (
  id INT PRIMARY KEY NOT NULL DEFAULT nextval('invoice_status_id_seq'),
  name VARCHAR(255) DEFAULT NULL
);
ALTER SEQUENCE invoice_status_id_seq OWNED BY invoice_status.id;