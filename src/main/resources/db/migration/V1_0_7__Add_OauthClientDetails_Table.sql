CREATE TABLE oauth_client_details (
  client_id VARCHAR PRIMARY KEY,
  resource_ids VARCHAR(300) DEFAULT NULL,
  client_secret VARCHAR(300) DEFAULT NULL,
  scope VARCHAR(300) DEFAULT NULL,
  authorized_grant_types VARCHAR(300) DEFAULT NULL,
  web_server_redirect_uri VARCHAR(300) DEFAULT NULL,
  authorities VARCHAR(300) DEFAULT NULL,
  access_token_validity VARCHAR(300) DEFAULT NULL,
  refresh_token_validity VARCHAR(300) DEFAULT NULL,
  additional_information VARCHAR(300) DEFAULT NULL,
  autoapprove VARCHAR(300) DEFAULT NULL
  );
