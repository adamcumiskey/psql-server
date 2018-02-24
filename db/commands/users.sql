-- src/psql_server/db/sql/users.sql
-- Users

-- :name insert-user :! :n
insert into users (first_name, last_name, email)
values (:first_name, :last_name, :email)

-- :name insert-users :! :n
insert into users (first_name, last_name, email)
values :tuple*:users

-- :name all-users :? :*
select * from users
order by id

-- :name user-by-id :? :1
select * from users
where id = :id

