CREATE TABLE oauth_refresh_token (
  token_id VARCHAR(300) DEFAULT NULL,
  token BYTEA DEFAULT NULL,
  authentication BYTEA DEFAULT NULL
  );