create table users
(
    id           int auto_increment
        primary key,
    email        varchar(255)               not null,
    name         varchar(255)               not null,
    phone_number varchar(12)                not null,
    password     varchar(255)               not null,
    role         varchar(20) default 'USER' null
);

create table service_type
(
    id     int auto_increment
        primary key,
    type   varchar(255) not null,
    length time         not null
);

create table appointments
(
    id               int auto_increment
        primary key,
    reservation_date datetime not null,
    user_id          int      not null,
    service_type_id  int      not null,
    constraint appointments__id_fk
        foreign key (user_id) references users (id)
            on delete cascade,
    constraint appointments__id_fk_2
        foreign key (service_type_id) references service_type (id)
            on delete cascade
);


