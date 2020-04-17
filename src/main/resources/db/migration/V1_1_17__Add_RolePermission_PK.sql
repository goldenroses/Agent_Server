ALTER TABLE role_permission
ADD CONSTRAINT pk_role_permission
PRIMARY KEY(role_id, permission_id);