drop table if exists carshop.car;
drop table if exists carshop.cuzov;
drop table if exists carshop.dvigatel;
drop table if exists carshop.corobka;

create table carshop.cuzov(
	id serial primary key,
	name varchar(500)
);

create table carshop.dvigatel(
	id serial primary key,
	name varchar(500),
	maxspeed integer
);

create table carshop.corobka(
	id serial primary key,
	automatic boolean
);

create table carshop.car(
	id serial primary key,
	model varchar(100),
	cuzov_id serial,
	dvigatel_id serial,
	corobka_id serial,
	link varchar(2000),
	FOREIGN KEY (cuzov_id) REFERENCES carshop.cuzov (id),
	FOREIGN KEY (dvigatel_id) REFERENCES carshop.dvigatel (id),
	FOREIGN KEY (corobka_id) REFERENCES carshop.corobka (id)
);

insert into carshop.cuzov(name) values ('Нет кузова');
insert into carshop.cuzov(name) values ('Маленький кузов');
insert into carshop.cuzov(name) values ('Обычный кузов');
insert into carshop.cuzov(name) values ('Большой кузов');

insert into carshop.dvigatel(name,maxspeed) values ('Медленный двигатель', 60);
insert into carshop.dvigatel(name,maxspeed) values ('Средний двигатель', 120);
insert into carshop.dvigatel(name,maxspeed) values ('Мощный двигатель', 240);

insert into carshop.corobka(automatic) values (true);
insert into carshop.corobka(automatic) values (false);

insert into carshop.car(model,cuzov_id,dvigatel_id,corobka_id,link) values('BMW', 1, 3, 1, 'img/bmw.jpg');
insert into carshop.car(model,cuzov_id,dvigatel_id,corobka_id,link) values('OPEL', 1, 2, 2, 'img/opel.jpg');
--insert into carshop.car(model,cuzov_id,dvigatel_id,corobka_id,link) values('LADA', 3, 1, 2);
--insert into carshop.car(model,cuzov_id,dvigatel_id,corobka_id,link) values('KIA', 1, 3, 1);
--insert into carshop.car(model,cuzov_id,dvigatel_id,corobka_id,link) values('TOYOTA', 2, 3, 1);
--insert into carshop.car(model,cuzov_id,dvigatel_id,corobka_id,link) values('HONDA', 2, 3, 1);
