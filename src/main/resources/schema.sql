create table if not exists location
(
    id               bigint auto_increment primary key,
    country          varchar(255) null,
    local_time_epoch int          null,
    name             varchar(255) null,
    region           varchar(255) null,
    local_date_time  datetime(6)  null,
    latitude         double       null,
    longitude        double       null,
    time_zone_name   varchar(255) null
);

create table if not exists weather
(
    id                bigint auto_increment
        primary key,
    weather_condition varchar(255) null,
    humidity          int          null,
    pressure          double       null,
    temperature       double       null,
    wind              double       null,
    location_id       bigint       null,
    constraint UK_location_id
        unique (location_id),
    constraint FK_location_location
        foreign key (location_id) references location (id)
);