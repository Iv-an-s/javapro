create table product_type (
    id bigserial primary key,
    title varchar(255) not null unique
);

create table accounts (
    id bigserial primary key,
    account varchar(64) not null unique,
    balance int
);

create table users (
    id bigserial primary key,
    username varchar(255) not null unique,
    user_account varchar(64) references accounts (account)
);


create table products (
    id bigserial primary key,
    product_type_id bigint references product_type (id),
    price int
);

create table orders (
    id bigserial primary key,
    user_id bigint not null references users (id),
    total_price int
);

create table order_items (
    id bigserial primary key,
    product_id bigint references products (id),
    order_id bigint references orders (id),
    quantity int,
    price_per_product int,
    price int
);


create table warehouse (
    id bigserial primary key,
    product_id bigint references products (id),
    quantity int
);
