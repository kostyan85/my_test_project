

CREATE TABLE  "carriers"(
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  name TEXT (50),
  contacts TEXT (50) ,
  comments TEXT (100),
  CONSTRAINT contacts_unique UNIQUE (contacts));


