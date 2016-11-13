create database ttms;

use ttms;


drop table if exists employee;
drop table if exists seat;
drop table if exists studio;

create table studio
(
   studio_id            int not null auto_increment,
   studio_name          varchar(100) not null,
   studio_row_count     int,
   studio_col_count     int,
   studio_introduction  varchar(2000),
   studio_flag          smallint comment    '取值含义：
                        0：尚未生成座位，可以根据行列信息生成座位
                        1：已经根据影厅的座位信息安排了座位，不能再安排座位；
                        -1：影厅损坏或者废弃，不能使用',

   primary key (studio_id)
);
create table seat
(
   seat_id              int not null auto_increment,
   studio_id            int,
   seat_row             int,
   seat_column          int,
   seat_status          smallint comment    '取值含义：
                       0：此处是空位，没有安排座椅
                       1：完好的座位，可以使用
                       -1：座位损坏，不能安排座位',

   primary key (seat_id),
   foreign key(studio_id)references studio(studio_id) 
);
select * from employee;
create table employee
(
   emp_id               int not null auto_increment,
   emp_no               char(20) not null,
   emp_name             varchar(100) not null,
   emp_password          varchar(30) not null,
   emp_tel_num          char(20),
   emp_addr             varchar(200),
   emp_email            varchar(100),
   emp_flag             varchar(10)  comment    '取值含义：
                       -1：员工未登陆成功
                       1：管理员登陆
                       2：普通用户登录',
   primary key (emp_id)
);