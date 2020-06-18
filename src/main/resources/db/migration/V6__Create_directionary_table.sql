create table DICTIONARY
(
	id bigint auto_increment,
	name VARCHAR(255),
	constraint DICTIONARY_PK
		primary key (id)
);

comment on table DICTIONARY is '字典表';
