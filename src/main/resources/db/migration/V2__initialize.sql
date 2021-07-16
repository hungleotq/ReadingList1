create table Reader (
	username varchar(50) not null,
	fullname varchar(25) not null,
	password varchar(25) not null
);

create table Book (
	ID INT PRIMARY KEY,
	reader varchar(25) not null,
	title varchar(25),
	author varchar(25)
);

create sequence hibernate_sequence;

insert into Reader(username,password,fullname) values('craig','password','Craig walls');