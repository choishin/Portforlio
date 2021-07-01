-- 맛집 정보 DB에 넣기
drop table gourmet;
create table gourmet (
gourmet_index int auto_increment primary key,
gourmet_name varchar(30),
gourmet_star varchar(20),
gourmet_visitor_review_cnt varchar(20),
gourmet_blogger_review_cnt varchar(20),
gourmet_address varchar(200),
gourmet_openinghour varchar(200),
gourmet_service varchar(500),
gourmet_info varchar(500)
);

-- 맛집정보 가격 DB에 넣기
drop table gourmet_price;
create table gourmet_price(
gourmet_name varchar(20),
gourmet_menu varchar(50),
gourmet_price varchar(50)
);

create table gourmet_image(
gourmet_name varchar(20),
gourmet_image varchar(200)
);

-- 데이터 확인 
select * from gourmet;
select * from gourmet_price;
select * from gourmet_price where gourmet_price<=3000;
select * from gourmet_price where gourmet_name='서현실비'and gourmet_price LIKE '%원';
select gourmet_index from gourmet where gourmet_name='서현실비';

