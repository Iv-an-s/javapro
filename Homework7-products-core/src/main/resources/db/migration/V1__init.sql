create table categories(
    id                  bigserial primary key,
    title               varchar(50) not null,
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp
);

create table if not exists products (
    id              bigserial primary key,
    category_id     bigint not null references categories (id),
    title           varchar(255),
    price           numeric(8, 2) not null,
    available_count int not null,
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

create table accounts (
    id              bigserial primary key,
    account         varchar(64) not null unique,
    balance         numeric(8, 2) not null,
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

create table users(
    id              bigserial primary key,
    username        varchar(64) not null,
    account_id      bigint references accounts (id),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

create table orders (
    id                      bigserial primary key,
    username                varchar(255) not null,
    total_price             numeric(8, 2) not null,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

create table order_items (
    id                      bigserial primary key,
    product_id              bigint not null references products (id),
    order_id                bigint not null references orders (id),
    quantity                int not null,
    price_per_product       numeric(8, 2) not null,
    price                   numeric(8, 2) not null,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

insert into accounts (account, balance)
values ('1111-2222', 1000.00),
    ('2222-1111', 2000.00)
;

insert into users (username, account_id)
values ('Bob', 1),
    ('Tom', 2)
;

insert into categories (title) values
    ('fruit'),
    ('bakery_products'),
    ('dairy_products'),
    ('no_category')
;

insert into products (title, price, category_id, available_count) values
    ('Milk', 100.00, 3, 33),
    ('bread', 10.00, 2, 44),
    ('cake', 20.00, 2, 55),
    ('cheese', 30.00, 3, 1),
    ('apple', 40.00, 1, 2),
    ('orange', 50.00, 1, 0)
;

insert into orders (username, total_price) values
    ('bob', 200.00)
;

insert into order_items (product_id, order_id, quantity, price_per_product, price) values
    (1, 1, 2, 100.00, 200.00)
;