ALTER TABLE house
ADD CONSTRAINT tenant
FOREIGN KEY (tenant)
REFERENCES tenant(id);
