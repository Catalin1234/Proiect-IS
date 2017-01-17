create database if not exists endava_internshipProject;

use endava_internshipProject;

create table endava_internshipProject.user(
userId int unsigned primary key,
password varchar(20),
userName varchar(20),
firstName varchar(20),
lastName varchar(20),
role int(1) unsigned,
grade VARCHAR(20)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table endava_internshipProject.training(
trainingId int unsigned primary key,
trainingName varchar (35),
trainerName varchar(25),
startDate date,
stopDate date,
grade VARCHAR(20),
technology varchar(20),
numberPeople int
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table endava_internshipProject.enrollment(
trainingId int unsigned,
userId int unsigned,
hasVoted boolean,
constraint fk_training_enrollment_trainingid foreign key (trainingId) references endava_internshipProject.training(trainingId) on delete restrict on update cascade,
constraint fk_user_enrollment_userid foreign key (userId) references endava_internshipProject.user(userId) on delete restrict on update cascade
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table endava_internshipProject.rating(
trainingId int unsigned,
numberOfSubmits int,
overall float,
constraint fk_training_rating_trainingid foreign key (trainingId) references endava_internshipProject.training(trainingId) on delete restrict on update cascade
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `endava_internshipproject`.`user`
  CHANGE COLUMN `userName` `userName` VARCHAR(20) NOT NULL ,
  ADD INDEX `User_index` (`userName` ASC);


INSERT INTO `endava_internshipproject`.`user` (`userId`, `password`, `userName`, `firstName`, `lastName`, `role`, `grade`)
VALUES  ('1', '1234', 'cnarita', 'Catalin-Ioan', 'Narita', '1', 'JT'),
  ('2', '1234', 'rflazar', 'Raluca-Florina', 'Lazar', '1', 'T'),
  ('3', '1234', 'ctimbus', 'Calin', 'Timbus', '1', 'T'),
  ('4', '1234', 'aaldea', 'Andrada', 'Aldea', '2', 'ST'),
  ('5', '1234', 'dtoderici', 'Dan', 'Toderici', '2', 'EN'),
  ('6', '1234', 'pbendea', 'Paula', 'Bendea', '1', 'SE'),
  ('7', '1234', 'sprodan', 'Sergiu', 'Prodan', '2', 'C'),
  ('8', '1234', 'rivan', 'Razvan', 'Ivan', '2', 'SC');

INSERT INTO `training` VALUES
  (1,'OOP Principles','Alex Graur','2016-09-01','2016-09-15','JT','Java',20),
  (2,'Git','Catalin Jurjiu','2016-08-18','2016-08-28','T','Git',10),
  (3,'Declaration and Access Control','Marius Muntianu','2016-07-11','2016-07-11','JT','Java',10),
  (4,'Exceptions','Alex Graur','2016-07-12','2016-09-13','JT','Java',20),
  (5,'Strings and Formatting','Iulia Danis','2016-09-13','2016-09-14','JT','Java',20),
  (6,'IO','Catalin Jurjiu','2016-08-14','2016-08-25','ST','Java',10),
  (7,'Generics & Collections','Rares Oltean','2016-08-18','2016-08-28','SE','Java',10),
  (8,'Gradle','Ionut David','2016-08-20','2016-08-20','EN','Gradle',15),
  (9,'Design Patterns','Rares Oltean','2016-08-21','2016-08-28','C','Java',10),
  (10,'Unit tests and Mocking','Rares Oltean','2016-07-25','2016-07-28','SC','Java',10),
  (11,'Spring Core','Ionut David','2016-01-20','2016-02-20','T','Spring',15),
  (12,'HTML & CSS','Istvan Trombitas','2016-09-20','2016-10-20','ST','UI',15),
  (13,'SQL Basics','Paul Palacean','2016-09-01','2016-09-15','SE','SQL',20),
  (14,'JPA','Rares Oltean','2016-08-25','2016-08-28','SC','Java',10),
  (15,'Hibernate','Alexandra Dan','2014-11-11','2015-02-10','C','Hibernate',10),
  (16,'ServletsJSP','Cristian Balint','2016-09-01','2016-09-15','T','JSP',20),
  (17,'Spring Core','Ionut David','2016-01-20','2016-02-20','JT','Spring',15),
  (18,'Hibernate','Alexandra Dan','2014-11-11','2015-02-10','ST','Hibernate',10),
  (19,'ServletsJSP','Cristian Balint','2016-09-01','2016-09-15','SE','JSP',20),
  (20,'Spring MVC','Paul Palacean','2016-10-01','2016-10-08','SC','Spring',20),
  (21,'Spring Security','Cosmin Tantas','2016-09-01','2016-09-15','ST','Spring',20),
  (22,'Rest','Gabriela Niculita','2016-09-01','2016-09-15','T','Java',20),
  (23,'Jenkins','Paul Palacean','2016-09-01','2016-09-15','SC','Jenkins',20);

INSERT INTO enrollment values
  (1,1,1),
  (5,1,1),
  (1,2,1),
  (6,2,0),
  (1,3,1),
  (2,3,0),
  (1,7,1);

INSERT INTO rating VALUES
  (1,4,3.5),
  (2,0,0),
  (3,0,0),
  (4,0,0),
  (5,1,5),
  (6,0,0),
  (7,0,0),
  (8,0,0);