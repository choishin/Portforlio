-- Spring 데이터베이스 생성
use spring;

-- sample 테이블 생성 
drop table sample;
create table sample (
id int auto_increment primary key,
title varchar(20)
);
-- 값 넣고 확인
insert into sample(title) values ('공지8');
select * from sample;