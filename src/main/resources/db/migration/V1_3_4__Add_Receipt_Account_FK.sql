ALTER TABLE receipt
ADD CONSTRAINT account
FOREIGN KEY (account)
REFERENCES account(id);
