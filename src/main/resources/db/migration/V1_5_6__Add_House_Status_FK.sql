ALTER TABLE house
ADD CONSTRAINT house_status_id
  FOREIGN KEY (status_id)
  REFERENCES house_status(id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
