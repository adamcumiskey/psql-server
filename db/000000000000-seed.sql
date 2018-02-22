begin;
create extension "uuid-ossp";

create table users (
  id varchar primary key default uuid_generate_v4(),
  first_name varchar not null,
  last_name varchar not null,
  email varchar not null
);

insert into users values (default, 'Adam', 'Cumiskey', 'adam.cumiskey@gmail.com');
end;
