create extension if not exists 'uuid-ossp'

create table if not exists user (
  id uuid primary key default uuid_generate_v4(),
  first_name varchar,
  last_name varchar,
  email varchar,
  password_hash varchar
)
