DROP DATABASE IF EXISTS db_hrr;
create DATABASE db_hrr character set utf8;
use db_hrr;


DROP TABLE IF EXISTS t_users;
CREATE TABLE t_users(
		id int primary key auto_increment , -- ��ţ�������
		userName varchar(40) unique, -- �û���
    password varchar(40) -- ����
);



DROP TABLE IF EXISTS t_scores;
CREATE TABLE t_scores(
		id int primary key auto_increment , -- ����
		score int,
        time varchar(40), 
        duration varchar(40), 
        userId int, 
        foreign key(userId) references t_users(id)
);

insert into t_users values(1,'admin','admin');





