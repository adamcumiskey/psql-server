create table if not exists users (
  id varchar primary key default uuid_generate_v4(),
  first_name varchar,
  last_name varchar,
  email varchar,
  password_hash varchar
)
