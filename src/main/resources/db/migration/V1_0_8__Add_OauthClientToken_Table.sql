CREATE TABLE oauth_client_token (
  token_id INT DEFAULT NULL,
  token BYTEA DEFAULT NULL,
  authentication_id VARCHAR(300) DEFAULT NULL,
  user_name VARCHAR(300) DEFAULT NULL,
  client_id VARCHAR(300) DEFAULT NULL
  );