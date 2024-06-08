create table limits (
    id                      bigserial primary key,
    user_id                 bigint not null unique,
    quota                   numeric(8, 2) not null,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

insert into limits (user_id, quota)
select
    x,
    10000.00
from
    system_range(1, 100);


