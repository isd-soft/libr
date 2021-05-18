INSERT INTO public.book (id, average_rating, description, language, maturity_rating, page_count, preview_link, published_date, publisher, rating_count, title) VALUES (9999, 3, 'description', 'language', 'maturityRating', 500, 'previewLink', 'published_date', 'publisher', 5, 'BB');

INSERT INTO public.person (id, age, email, first_name, last_name, password, phone, role) VALUES (9999, 35, 'test1@gmail.com', 'test', 'test', '$2a$10$DZhaQBnYmtHs9tG5qLcexOEZ4G/Uo3hCqusJj135bzx.DSMq6lgKu', '237591231', 'ADMIN');

INSERT INTO public.industry_identifiers (book_id, industry_identifier) VALUES (9999, 'asdas');
INSERT INTO public.industry_identifiers (book_id, industry_identifier) VALUES (9999, 'sdfs');
INSERT INTO public.industry_identifiers (book_id, industry_identifier) VALUES (9999, 'sdf');
INSERT INTO public.industry_identifiers (book_id, industry_identifier) VALUES (9999, 'Sdfsdf');

INSERT INTO public.book_action (id, action_date, status, book_id, person_id) VALUES (9999, '2021-05-17 22:15:13.000000', 'SUBMITTED', 9999, 9999);