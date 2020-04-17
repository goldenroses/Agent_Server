CREATE SEQUENCE permission_id_seq;
CREATE TABLE permission (
  id INT PRIMARY KEY NOT NULL DEFAULT nextval('permission_id_seq'),
  name VARCHAR(300) DEFAULT NULL
  );
ALTER SEQUENCE permission_id_seq OWNED BY permission.id;
