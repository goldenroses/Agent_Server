ALTER TABLE user_role
ADD CONSTRAINT fk_user_role_user FOREIGN KEY (user_id) REFERENCES "user"(id);
