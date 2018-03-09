-- :name insert-user :? :1
insert into users (first_name, last_name, email, password_hash)
values (:first_name, :last_name, :email, crypt(:password, gen_salt('md5')))
returning *

-- :name insert-users :! :*
insert into users (first_name, last_name, email, password_hash)
values :tuple*:users

-- :name get-all-users :? :*
select * from users
order by id

-- :name user-by-id :? :1
select * from users
where id = :id

-- :name user-by-email :? :1
select * from users
where email = :email

-- :name validate-password :? :1
select crypt(:password, u.password_hash) = u.password_hash as is_valid
from users as u
where email = :email
