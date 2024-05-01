create table product_type (
    id bigserial primary key,
    title varchar(255) not null unique
);

create table users (
    id bigserial primary key,
    username varchar(255) not null unique
);

create table products (
    id bigserial primary key,
    account varchar(255),
    balance int,
    user_id bigint not null references users (id),
    product_type_id bigint references product_type (id)
);