# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table tags (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  type                      varchar(255),
  constraint pk_tags primary key (id))
;

create table communities_tags (
  communities_id                 bigint not null,
  tags_id                        bigint not null,
  constraint pk_communities_tags primary key (communities_id, tags_id))
;

alter table communities_tags add constraint fk_communities_tags_communities_01 foreign key (communities_id) references communities (id) on delete restrict on update restrict;

alter table communities_tags add constraint fk_communities_tags_tags_02 foreign key (tags_id) references tags (id) on delete restrict on update restrict;

# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table communities_tags;

drop table tags;

SET FOREIGN_KEY_CHECKS=1;

