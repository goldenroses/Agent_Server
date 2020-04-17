ALTER TABLE role_permission
ADD CONSTRAINT fk_role_permission_role FOREIGN KEY (role_id) REFERENCES role(id);
