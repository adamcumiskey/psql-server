create table if not exists users (
  id varchar primary key default uuid_generate_v4(),
  first_name varchar not null,
  last_name varchar not null,
  email varchar not null
);
