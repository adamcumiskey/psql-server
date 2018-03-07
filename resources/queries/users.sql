-- :name insert-user :! :n
insert into users (first_name, last_name, email, password)
values (:first_name, :last_name, :email, (select * from crypt(:password, gen_salt('md5')))

-- :name insert-users :! :n
insert into users (fist_name, last_name, email, password)
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
