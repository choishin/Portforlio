use kopoctc;

drop table population;
create table population(
city_name varchar(20),
city_population int
);

select * from population;

drop table vaccine;
create table vaccine(
vaccine_index int auto_increment primary key,
vaccine_date varchar(50),
city_name varchar(20),
first_day_only int,
first_day_total int,
second_day_only int,
second_day_total int 
);

select * from vaccine;

delete from vaccine where vaccine_index=" ";
select * from vaccine where city_name='서울' and vaccine_date=623;
select * from vaccine where city_name='서울';
select city_name from population where city_population=2446144;
select city_population from population where city_name='';

select * from population where city_name='대구';
select (select first_day_total from vaccine where city_name='합계' and vaccine_date=622)/(select city_population from population where city_name='전국');