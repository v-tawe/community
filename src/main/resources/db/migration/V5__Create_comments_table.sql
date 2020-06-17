create table comment
(
	id bigint auto_increment,
	parent_id bigint not null,
	type int not null,
	commenter long not null,
	gmt_create bigint not null,
	gmt_modified bigint not null,
	like_count bigint default 0 not null,
	constraint COMMENT_PK
		primary key (id)
);

comment on column comment.parent_id is '父类 question ID';

comment on column comment.type is '父类类型：第几级回复';

comment on column comment.commenter is '评论人 ID';

