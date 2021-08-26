insert into user (username, password)
values ('zavanton', '$2a$10$aVXiJ902o9RRgPnRY2d2n.zZnfB9Pe9rbrDQu1h2FxkptSd.8vBGW');

insert into authority (name)
values ('ADMIN');
insert into authority (name)
values ('USER');

insert into user_authority (user_id, authority_id)
values (1, 1);
