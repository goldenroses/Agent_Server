CREATE SEQUENCE user_id_seq;
CREATE TABLE "user" (
  id INT PRIMARY KEY NOT NULL DEFAULT nextval('user_id_seq'),
  active VARCHAR(300) DEFAULT NULL,
  username VARCHAR(300) DEFAULT NULL,
  password VARCHAR(1055) DEFAULT NULL,
  non_expired BOOLEAN DEFAULT NULL,
  non_locked BOOLEAN DEFAULT NULL,
  enabled BOOLEAN DEFAULT NULL,
  credentials_non_expired BOOLEAN DEFAULT NULL,
   UNIQUE (username)
);
ALTER SEQUENCE user_id_seq OWNED BY "user".id;