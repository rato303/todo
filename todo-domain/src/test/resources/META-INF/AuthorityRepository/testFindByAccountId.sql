delete from public.authority;

insert into public.authority(
    account_id
  , name
) values (
    'user1'
  , 'todo'
), (
    'user1'
  , 'account'
), (
    'user2'
  , 'todo'
), (
    'user2'
  , 'account'
)
;