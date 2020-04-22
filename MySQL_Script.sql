create schema anotacao;

use anotacao;

create user 'user'@'localhost' identified by 'pass123';

grant select, insert, delete, update on anotacao.* to user@'localhost';

create table usr_usuario (
  usr_id bigint unsigned not null auto_increment,
  usr_nome varchar(20) not null,
  usr_senha varchar(50) not null,
  primary key (usr_id),
  unique key uni_usuario_nome (usr_nome)
);

create table aut_autorizacao (
  aut_id bigint unsigned not null auto_increment,
  aut_nome varchar(20) not null,
  primary key (aut_id),
  unique key uni_aut_nome (aut_nome)
);

create table uau_usuario_autorizacao (
  usr_id bigint unsigned not null,
  aut_id bigint unsigned not null,
  primary key (usr_id, aut_id),
  foreign key aut_usuario_fk (usr_id) references usr_usuario (usr_id) on delete restrict on update cascade,
  foreign key aut_autorizacao_fk (aut_id) references aut_autorizacao (aut_id) on delete restrict on update cascade
);

create table ant_anotacao (
  ant_id bigint unsigned not null auto_increment,
  ant_assunto varchar(100) not null,
  ant_texto varchar(500) not null,
  ant_data_hora datetime not null,
  usr_criacao_id bigint unsigned not null,
  primary key (ant_id),
  foreign key ant_usr_fk (usr_criacao_id) references usr_usuario(usr_id) on delete restrict on update cascade
);

insert into usr_usuario(usr_nome, usr_senha) values('teste', concat('{MD5}', md5('teste')));
insert into usr_usuario(usr_nome, usr_senha) values('admin', concat('{MD5}', md5('admin')));
insert into aut_autorizacao(aut_nome) values('ROLE_USUARIO');
insert into aut_autorizacao(aut_nome) values('ROLE_ADMIN');
insert into uau_usuario_autorizacao(usr_id, aut_id)
select usr_id, aut_id
from usr_usuario, aut_autorizacao
where usr_nome = 'teste'
and aut_nome = 'ROLE_USUARIO';
insert into uau_usuario_autorizacao(usr_id, aut_id)
select usr_id, aut_id
from usr_usuario, aut_autorizacao
where usr_nome = 'admin';