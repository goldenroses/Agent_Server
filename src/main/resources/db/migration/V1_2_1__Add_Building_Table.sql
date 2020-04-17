CREATE SEQUENCE building_id_seq;
CREATE TABLE building(
  id INT PRIMARY KEY NOT NULL DEFAULT nextval('building_id_seq'),
  building_name VARCHAR(255) DEFAULT NULL,
  building_location VARCHAR(255) DEFAULT NULL,
  building_description VARCHAR(255) DEFAULT NULL,
  building_owner VARCHAR(255) DEFAULT NULL,
  building_number_of_houses INT DEFAULT NULL,
  building_registration_date VARCHAR(255) DEFAULT NULL
);
ALTER SEQUENCE building_id_seq OWNED BY building.id;