-- Project Name : todo
-- Date/Time    : 2022/01/01 15:08:33
-- Author       : t-kimura
-- RDBMS Type   : PostgreSQL
-- Application  : A5:SQL Mk-2

/*
  << 注意！！ >>
  BackupToTempTable, RestoreFromTempTable疑似命令が付加されています。
  これにより、drop table, create table 後もデータが残ります。
  この機能は一時的に $$TableName のような一時テーブルを作成します。
  この機能は A5:SQL Mk-2でのみ有効であることに注意してください。
*/

-- TODO
--* BackupToTempTable
drop table if exists todo cascade;

--* RestoreFromTempTable
create table todo (
  todo_id character varying(36) not null
  , todo_title character varying(30) not null
  , finished BOOLEAN not null
  , created_at timestamp not null
  , account_id character varying(36) not null
  , constraint todo_PKC primary key (todo_id)
) ;

-- アカウント
--* BackupToTempTable
drop table if exists account cascade;

--* RestoreFromTempTable
create table account (
  account_id character varying(36) not null
  , account_name character varying(128) not null
  , password character varying(128) not null
  , constraint account_PKC primary key (account_id)
) ;

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

