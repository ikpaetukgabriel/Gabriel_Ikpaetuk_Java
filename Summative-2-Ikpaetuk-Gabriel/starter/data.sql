use book_store;

INSERT INTO publisher
(`publisher_id`, `name`, `street`, `city`, `state`, `postal_code`, `phone`, `email`)
VALUES
(1, "McGraw-Hill", "Hollywood", "Los Angeles", "CA", "11100", "111-222-3333", "mcGrawHill@gmail.com");

INSERT INTO author
(`author_id`, `first_name`, `last_name`, `street`, `city`, `state`, `postal_code`, `phone`, `email`)
VALUES
(1, "Stephen", "King", "Hollywood", "Los Angeles", "CA", "11100", "111-222-3333", "sking@gmail.com");

INSERT INTO book
(`book_id`, `isbn`, `publish_date`, `author_id`, `title`, `publisher_id`, `price`)
VALUES
(1, "9783161484100", "2015-08-01", 1, "An interesting book", 1, 29.99);
