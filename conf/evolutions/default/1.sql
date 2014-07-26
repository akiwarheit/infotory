# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table account (
  email                     varchar(255) not null,
  name                      varchar(255),
  password                  varchar(255),
  constraint pk_account primary key (email))
;

create table role (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  constraint pk_role primary key (id))
;


create table account_roles (
  account_email                  varchar(255) not null,
  role_id                        bigint not null,
  constraint pk_account_roles primary key (account_email, role_id))
;



alter table account_roles add constraint fk_account_roles_account_01 foreign key (account_email) references account (email) on delete restrict on update restrict;

alter table account_roles add constraint fk_account_roles_role_02 foreign key (role_id) references role (id) on delete restrict on update restrict;

# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table account;

drop table account_roles;

drop table role;

SET FOREIGN_KEY_CHECKS=1;

