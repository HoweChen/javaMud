create table if not exists customer
(
    id         bigint unsigned auto_increment,
    first_name varchar(100) not null,
    last_name  varchar(100) not null,
    primary key (id)
)