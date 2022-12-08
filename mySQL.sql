create database examModul3;
use examModul3;

create table department(
id_department int not null primary key auto_increment,
name_department varchar(50) not null
);
INSERT INTO `exammodul3`.`department` (`id_department`, `name_department`) VALUES ('1', 'Phong dich vu');
INSERT INTO `exammodul3`.`department` (`id_department`, `name_department`) VALUES ('2', 'Phong san xuat');
INSERT INTO `exammodul3`.`department` (`id_department`, `name_department`) VALUES ('3', 'Phong cham soc khach hang');
INSERT INTO `exammodul3`.`department` (`id_department`, `name_department`) VALUES ('4', 'Phong kinh doanh');

create table employment(
id_employment int not null primary key auto_increment,
name_employment varchar(50) not null,
address varchar(50) not null,
email varchar(50) not null,
phone varchar(10) not null,
salary double not null,
id_department int not null,
foreign key (id_department) references department(id_department)
);
INSERT INTO `exammodul3`.`employment` (`id_employment`, `name_employment`,`address`, `email`, `phone`, `salary`, `id_department`) VALUES ('1', 'Quang','HP', 'quang@gmail.com', '0111111111', '1000', '3');
INSERT INTO `exammodul3`.`employment` (`id_employment`, `name_employment`,`address`, `email`, `phone`, `salary`, `id_department`) VALUES ('2', 'Nam', 'TB','nam@gmail.com', '0222222222', '1000', '1');
INSERT INTO `exammodul3`.`employment` (`id_employment`, `name_employment`,`address`, `email`, `phone`, `salary`, `id_department`) VALUES ('3', 'Viet','HN', 'viet@gmail.com', '0333333333', '1000', '4');
INSERT INTO `exammodul3`.`employment` (`id_employment`, `name_employment`,`address`, `email`, `phone`, `salary`, `id_department`) VALUES ('4', 'Ngan', 'LC','ngan@gmail.com', '0444444444', '100', '2');
