-- db/commands/sessions.sql
-- Sessions

-- :name insert-session :! :n
insert into sessions (user_id, token)
values (:user_id, :token)

-- :name session-by-user-id :? :1
select * from sessions
where user_id = :user_id

-- :name user-for-session :? :1
select u.* from sessions as s
join users as u on u.id = s.user_id
where s.id = :id
