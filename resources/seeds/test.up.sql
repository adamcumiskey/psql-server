insert into users (first_name, last_name, email, password_hash)
values ('Adam', 'Cumiskey', 'adam.cumiskey@gmail.com', crypt('password', gen_salt('md5')));
insert into users (first_name, last_name, email, password_hash)
values ('Jane', 'Smith', 'jane.smith@gmail.com', crypt('password', gen_salt('md5')));
insert into users (first_name, last_name, email, password_hash)
values ('John', 'Doe', 'john.doe@gmail.com', crypt('password', gen_salt('md5')));

