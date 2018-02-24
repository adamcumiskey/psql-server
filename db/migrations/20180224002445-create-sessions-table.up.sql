create table if not exists sessions (
  id varchar primary key default uuid_generate_v4(),
  user_id varchar references users (id) on delete cascade,
  token varchar not null
);
