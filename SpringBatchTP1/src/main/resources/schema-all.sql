DROP TABLE IF EXISTS book;

CREATE TABLE book  (
    id BIGINT NOT NULL DEFAULT nextval('book_id_seq'),
    title VARCHAR(90),
    author VARCHAR(40),
    isbn VARCHAR(13),
    publisher VARCHAR(20),
    year INT
);