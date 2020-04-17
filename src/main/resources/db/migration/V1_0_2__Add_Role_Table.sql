CREATE SEQUENCE role_id_seq;
CREATE TABLE role (
  id INT PRIMARY KEY NOT NULL DEFAULT nextval('role_id_seq'),
  name VARCHAR(300) DEFAULT NULL
);
ALTER SEQUENCE role_id_seq OWNED BY role.id;