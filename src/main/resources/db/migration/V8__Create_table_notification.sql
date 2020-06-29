CREATE TABLE notification
(
    id bigint auto_increment primary key,
    notifier bigint not null,
    receiver bigint not null,
    outer_id bigint not null,
    type int not null,
    gmt_create bigint not null,
    status int default 0 not null
);


