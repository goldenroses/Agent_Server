ALTER TABLE user_role
ADD CONSTRAINT pk_user_role
PRIMARY KEY(user_id, role_id);