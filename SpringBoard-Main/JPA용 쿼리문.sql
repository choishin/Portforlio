-- 데이터 베이스 생성
create database spring;

-- Spring 데이터베이스 사용
use spring;
show tables;
desc board;
desc board_item;

-- drop
-- SET foreign_key_checks = 0;
-- drop table board;
-- drop table board_item;

-- alter
ALTER TABLE board_item MODIFY content LONGTEXT;

