ALTER TABLE user_role
ADD CONSTRAINT fk_role_permission_role FOREIGN KEY (role_id) REFERENCES role(id);
