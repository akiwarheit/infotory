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
  constraint uq_role_name unique (name),
  constraint pk_role primary key (id))
;

create table manufacturer (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  constraint uq_manufacturer_name unique (name),
  constraint pk_manufacturer primary key (id))
;

create table product (
  id                        bigint auto_increment not null,
  upc                       varchar(255),
  description               varchar(255),
  created                   datetime,
  updated                   datetime,
  manufacturer_id           bigint,
  constraint pk_product primary key (id))
;


create table account_roles (
  account_email                  varchar(255) not null,
  role_id                        bigint not null,
  constraint pk_account_roles primary key (account_email, role_id))
;
alter table product add constraint fk_product_manufacturer_1 foreign key (manufacturer_id) references manufacturer (id) on delete restrict on update restrict;
create index ix_product_manufacturer_1 on product (manufacturer_id);



alter table account_roles add constraint fk_account_roles_account_01 foreign key (account_email) references account (email) on delete restrict on update restrict;

alter table account_roles add constraint fk_account_roles_role_02 foreign key (role_id) references role (id) on delete restrict on update restrict;

# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table account;

drop table account_roles;

drop table role;

drop table manufacturer;

drop table product;

SET FOREIGN_KEY_CHECKS=1;

