-- Project Name : todo
-- Date/Time    : 2022/01/02 17:15:32
-- Author       : t-kimura
-- RDBMS Type   : PostgreSQL
-- Application  : A5:SQL Mk-2

-- 権限
drop table if exists authority cascade;

create table authority (
  account_id character varying(36) not null
  , name character varying(36) not null
  , constraint authority_PKC primary key (account_id,name)
) ;

-- TODO
drop table if exists todo cascade;

create table todo (
  todo_id character varying(36) not null
  , todo_title character varying(30) not null
  , finished BOOLEAN not null
  , created_at timestamp not null
  , account_id character varying(36) not null
  , constraint todo_PKC primary key (todo_id)
) ;

-- アカウント
drop table if exists account cascade;

create table account (
  account_id character varying(36) not null
  , account_name character varying(128) not null
  , password character varying(128) not null
  , constraint account_PKC primary key (account_id)
) ;

comment on table authority is '権限';
comment on column authority.account_id is 'アカウント識別子';
comment on column authority.name is '権限名';

comment on table todo is 'TODO';
comment on column todo.todo_id is 'TODO識別子';
comment on column todo.todo_title is 'TODO名';
comment on column todo.finished is '完了したかどうか';
comment on column todo.created_at is '作成日時';
comment on column todo.account_id is 'アカウント識別子';

comment on table account is 'アカウント';
comment on column account.account_id is 'アカウント識別子';
comment on column account.account_name is 'アカウント名';
comment on column account.password is 'パスワード';

