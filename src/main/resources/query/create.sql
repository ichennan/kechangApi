create table tbl_user_info
(
	id int auto_increment,
	create_time datetime not null DEFAULT CURRENT_TIMESTAMP,
	update_time datetime not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	create_user varchar(255) null,
	update_user varchar(255) null,
	remark varchar(255) null,
	constraint tbl_user_info_pk
		primary key (id)
);