insert into public.book (id, average_rating, description, language, maturity_rating, page_count, preview_link, published_date, publisher, rating_count, title) VALUES (9999, 3, 'description', 'language', 'maturityRating', 500, 'previewLink', 'published_date', 'publisher', 5, 'Java');
insert into public.book (id, average_rating, description, language, maturity_rating, page_count, preview_link, published_date, publisher, rating_count, title) VALUES (1111, 3, 'description', 'language', 'maturityRating', 500, 'previewLink', 'published_date', 'publisher', 5, 'Java');
insert into public.book (id, average_rating, description, language, maturity_rating, page_count, preview_link, published_date, publisher, rating_count, title) VALUES (2222, 3, 'description', 'language', 'maturityRating', 500, 'previewLink', 'published_date', 'publisher', 5, 'Clean Code');
insert into public.book (id, average_rating, description, language, maturity_rating, page_count, preview_link, published_date, publisher, rating_count, title) VALUES (3333, 3, 'description', 'language', 'maturityRating', 500, 'previewLink', 'published_date', 'publisher', 5, 'Java');
insert into public.book (id, average_rating, description, language, maturity_rating, page_count, preview_link, published_date, publisher, rating_count, title) VALUES (4444, 3, 'description', 'language', 'maturityRating', 500, 'previewLink', 'published_date', 'publisher', 5, 'Spring');
insert into public.book (id, average_rating, description, language, maturity_rating, page_count, preview_link, published_date, publisher, rating_count, title) VALUES (5555, 3, 'description', 'language', 'maturityRating', 500, 'previewLink', 'published_date', 'publisher', 5, 'java');
insert into public.book (id, average_rating, description, language, maturity_rating, page_count, preview_link, published_date, publisher, rating_count, title) VALUES (6666, 3, 'description', 'language', 'maturityRating', 500, 'previewLink', 'published_date', 'publisher', 5, 'java java');

insert into public.users (id, age, email, first_name, last_name, password, phone, role) VALUES (9999, 35, 'mariacojocari302@gmail.com', 'Maria', 'Maria', '$2a$10$DZhaQBnYmtHs9tG5qLcexOEZ4G/Uo3hCqusJj135bzx.DSMq6lgKu', '237591231', 'ADMIN');
insert into public.users (id, age, email, first_name, last_name, password, phone, role) VALUES (9998, 35, 'test@gmail.com', 'Bob', 'Bob', '$2a$10$DZhaQBnYmtHs9tG5qLcexOEZ4G/Uo3hCqusJj135bzx.DSMq6lgKu', '237591231', 'ADMIN');
insert into public.users (id, age, email, first_name, last_name, password, phone, role) VALUES (9997, 35, 'mariacojocari302@gmail.com', 'John', 'Smith', '$2a$10$DZhaQBnYmtHs9tG5qLcexOEZ4G/Uo3hCqusJj135bzx.DSMq6lgKu', '237591231', 'USER');
insert into public.users (id, age, email, first_name, last_name, password, phone, role) VALUES (9996, 35, 'test3@gmail.com', 'Jim', 'Jim', '$2a$10$DZhaQBnYmtHs9tG5qLcexOEZ4G/Uo3hCqusJj135bzx.DSMq6lgKu', '237591231', 'USER');
insert into public.users (id, age, email, first_name, last_name, password, phone, role) VALUES (9995, 35, 'test4@gmail.com', 'Anna', 'Anna', '$2a$10$DZhaQBnYmtHs9tG5qLcexOEZ4G/Uo3hCqusJj135bzx.DSMq6lgKu', '237591231', 'USER');

insert into public.book_action (id, action_date, status, book_id, users_id) VALUES (99994, '2021-05-17 22:15:13.000000', 'SUBMITTED', 9999, 9999);
insert into public.book_action (id, action_date, status, book_id, users_id) VALUES (145644, '2021-05-17 22:15:13.000000', 'SUBMITTED', 9999, 9998);
insert into public.book_action (id, action_date, status, book_id, users_id) VALUES (456624, '2021-04-17 22:15:14.000000', 'IN_USE', 9999, 9998);
insert into public.book_action (id, action_date, status, book_id, users_id) VALUES (4564563, '2021-02-17 22:15:15.000000', 'IN_USE', 1111, 9997);
insert into public.book_action (id, action_date, status, book_id, users_id) VALUES (4456435, '2021-03-17 22:15:16.000000', 'IN_LIBRARY', 2222, 9997);
insert into public.book_action (id, action_date, status, book_id, users_id) VALUES (5345345, '2021-04-27 22:15:17.000000', 'IN_USE', 3333, 9996);
insert into public.book_action (id, action_date, status, book_id, users_id) VALUES (3453456, '2021-04-17 22:15:17.000000', 'IN_LIBRARY', 4444, 9996);
insert into public.book_action (id, action_date, status, book_id, users_id) VALUES (3453457, '2021-04-24 22:15:17.000000', 'IN_USE', 5555, 9995);
insert into public.book_action (id, action_date, status, book_id, users_id) VALUES (3453458, '2021-04-17 22:15:17.000000', 'IN_USE', 6666, 9995);
insert into public.book_action (id, action_date, status, book_id, users_id) VALUES (9999445, '2021-05-17 22:15:13.000000', 'IN_USE', 9999, 9999);
insert into public.book_action (id, action_date, status, book_id, users_id) VALUES (9999467, '2021-02-17 22:15:13.000000', 'IN_LIBRARY', 9999, 9999);
insert into public.book_action (id, action_date, status, book_id, users_id) VALUES (9999489, '2021-05-27 22:15:13.000000', 'SUBMITTED', 9999, 9999);
insert into public.book_action (id, action_date, status, book_id, users_id) VALUES (9999409, '2021-04-04 22:15:13.000000', 'SUBMITTED', 9999, 9999);
