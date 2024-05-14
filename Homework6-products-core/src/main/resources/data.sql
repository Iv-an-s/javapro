insert into product_type (title)
values ('cheese'),
    ('bread')
;

insert into accounts (account, balance)
values ('1111-2222', 1000),
    ('2222-1111', 2000)
;

insert into users (username, user_account)
values ('Bob', '1111-2222'),
    ('Tom', '2222-1111')
;

insert into products (product_type_id, price)
values (1, 1000),
    (2, 100)
;

insert into warehouse (product_id, quantity)
values (1, 15),
    (2, 33)
;