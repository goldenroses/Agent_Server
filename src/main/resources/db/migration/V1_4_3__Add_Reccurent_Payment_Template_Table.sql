CREATE SEQUENCE recurrent_payment_template_id_seq;
CREATE TABLE recurrent_payment_template(
  id INT PRIMARY KEY NOT NULL DEFAULT nextval('recurrent_payment_template_id_seq'),
  template_name VARCHAR(255) DEFAULT NULL,
  water_payment VARCHAR(255) DEFAULT NULL,
  security_payment VARCHAR(255) DEFAULT NULL,
  amenities_payment VARCHAR(255) DEFAULT NULL
);
ALTER SEQUENCE recurrent_payment_template_id_seq OWNED BY recurrent_payment_template.id;