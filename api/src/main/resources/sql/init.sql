create database echat;
use echat;
create table user_info
(
    id        int auto_increment,
    user_id   varchar(64)  not null,
    user_name varchar(256) not null,
    mail      varchar(64)  not null,
    passwd    varchar(256) not null,
    phone     varchar(11)  null,
    create_time timestamp not null,
    update_time timestamp not null,
    constraint id
        primary key (id)
);
