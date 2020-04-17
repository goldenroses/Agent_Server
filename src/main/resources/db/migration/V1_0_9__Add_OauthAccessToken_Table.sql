CREATE TABLE oauth_access_token (
  authentication_id VARCHAR PRIMARY KEY DEFAULT NULL,
  authentication BYTEA DEFAULT NULL,
  token_id VARCHAR(100) DEFAULT NULL,
  token BYTEA DEFAULT NULL,
  user_name VARCHAR DEFAULT NULL,
  refresh_token VARCHAR DEFAULT NULL,
  client_id VARCHAR(500) DEFAULT NULL,
  UNIQUE (user_name)
  );