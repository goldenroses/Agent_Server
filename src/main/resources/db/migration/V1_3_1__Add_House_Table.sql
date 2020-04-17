CREATE SEQUENCE house_id_seq;
CREATE TABLE house (
  id INT PRIMARY KEY NOT NULL DEFAULT nextval('house_id_seq'),
  building_id bigint DEFAULT NULL,
  house_name VARCHAR(255) DEFAULT NULL,
  house_floor_number VARCHAR(255) DEFAULT NULL,
  house_description VARCHAR(500) DEFAULT NULL,
  status_id bigint DEFAULT NULL
);
ALTER SEQUENCE house_id_seq OWNED BY house.id;