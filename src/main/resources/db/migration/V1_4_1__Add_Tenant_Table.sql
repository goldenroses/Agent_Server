CREATE SEQUENCE tenant_id_seq;
CREATE TABLE tenant (
  id INT PRIMARY KEY NOT NULL DEFAULT nextval('tenant_id_seq'),
  tenant_name VARCHAR(255) DEFAULT NULL,
  tenant_mobile VARCHAR(255) DEFAULT NULL,
  tenant_national_id VARCHAR(255) DEFAULT NULL,
  tenant_agreement VARCHAR(255) DEFAULT NULL,
  tenant_email VARCHAR(255) DEFAULT NULL
);
ALTER SEQUENCE tenant_id_seq OWNED BY tenant.id;