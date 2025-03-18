CREATE TABLE sensitive_words (
  id bigint identity PRIMARY KEY,
  word VARCHAR (200) UNIQUE NOT NULL
);