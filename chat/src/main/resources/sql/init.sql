use echat;
create table user_info
(
    id        bigint auto_increment,
    user_id   bigint  not null,
    user_name varchar(256) not null,
    mail      varchar(64)  not null,
    passwd    varchar(256) not null,
    phone     varchar(11)  null,
    create_time timestamp not null,
    update_time timestamp not null,
    constraint id
        primary key (id)
);

create table t_message
(
    id bigint auto_increment,
    user_name varchar(256) not null,
    user_id bigint not null ,
    to_user_name varchar(256) not null ,
    to_user_id bigint not null ,
    message text ,
    type varchar(64) not null ,
    time timestamp not null ,
    constraint id
        primary key (id)
)