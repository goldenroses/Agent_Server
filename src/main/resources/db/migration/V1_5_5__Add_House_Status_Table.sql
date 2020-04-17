CREATE SEQUENCE house_status_id_seq;
CREATE TABLE house_status (
  id INT PRIMARY KEY NOT NULL DEFAULT nextval('house_status_id_seq'),
  name VARCHAR(255) DEFAULT NULL
);
ALTER SEQUENCE house_status_id_seq OWNED BY house_status.id;