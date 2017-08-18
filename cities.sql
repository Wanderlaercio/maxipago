create database maxipago;
	
create table cities (id integer,
name varchar(25),
latitude double,
longitudde double,
primary key (id));

insert into cities values(1,'SÃO PAULO',-23.527421,-46.717132);
insert into cities values(2,'PINHAIS',-25.534032,-49.202838 );
insert into cities values(3,'CURITIBA',-25.420905,-49.264636);
insert into cities values(4,'RIO DE JANEIRO',-22.921660,-43.174041);