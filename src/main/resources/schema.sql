drop table if exists tb_user cascade;

create table tb_user (
     id bigint generated by default as identity,
     email varchar(255),
     nome varchar(255),
     senha varchar(255),
     primary key (id)
);