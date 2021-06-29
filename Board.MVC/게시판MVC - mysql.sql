-- 게시판 목록 지우기, 만들기
drop table boardList;
create table boardList(
board_index int auto_increment primary key,
board_title varchar(50)
);

-- 게시판 지우기, 만들기, 보기
delete from boardList where board_index=4;
insert into boardList (board_title) value ('');
UPDATE gongji SET title ='수정된 제목', date=date_format(now(),'%Y-%m-%d %I:%i:%s'), content='진자 열심히 했다' WHERE id=9;
select count(*) from boardList;
select max(board_index) from boardList;