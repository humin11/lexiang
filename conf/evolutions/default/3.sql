# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table communities_tags (
  communities_id                 bigint not null,
  user_id                        bigint not null,
  constraint pk_communities_users primary key (communities_id, users_id))
;


alter table communities_users add constraint fk_communities_users_communities_01 foreign key (communities_id) references communities (id) on delete restrict on update restrict;

alter table communities_users add constraint fk_communities_users_user_02 foreign key (users_id) references User (id) on delete restrict on update restrict;

# --- !Downs

SET FOREIGN_KEY_CHECKS=0;


SET FOREIGN_KEY_CHECKS=1;

