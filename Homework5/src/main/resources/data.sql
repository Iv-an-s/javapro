insert into product_type (title)
values ('account'),
    ('card')
;

insert into users (username)
values ('Bob'),
    ('Tom')
;

insert into products (account, balance, user_id, product_type_id)
values ('1111-2222', 250, 1, 1),
    ('1111-3333', 1750, 1, 2),
    ('2222-0000', 1850, 2, 1)
;
