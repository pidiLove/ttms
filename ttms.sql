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
   studio_flag          smallint comment    'ȡֵ���壺
                        0����δ������λ�����Ը���������Ϣ������λ
                        1���Ѿ�����Ӱ������λ��Ϣ��������λ�������ٰ�����λ��
                        -1��Ӱ���𻵻��߷���������ʹ��',

   primary key (studio_id)
);
create table seat
(
   seat_id              int not null auto_increment,
   studio_id            int,
   seat_row             int,
   seat_column          int,
   seat_status          smallint comment    'ȡֵ���壺
                       0���˴��ǿ�λ��û�а�������
                       1����õ���λ������ʹ��
                       -1����λ�𻵣����ܰ�����λ',

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
   emp_flag             varchar(10)  comment    'ȡֵ���壺
                       -1��Ա��δ��½�ɹ�
                       1������Ա��½
                       2����ͨ�û���¼',
   primary key (emp_id)
);