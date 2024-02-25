insert into user_details(id, name, birth_date)
values(1001, 'manmurug', '2020-09-09');

insert into user_details(id, name, birth_date)
values(1002, 'coolie', '2011-09-09');

insert into user_details(id, name, birth_date)
values(1003, 'faker', '1920-09-09');

insert into user_details(id, name, birth_date)
values(1004, 'elias', '1985-09-09');



insert into user_info(id, name, birth_date)
values(1001, 'manmurug', '2020-09-09');

insert into user_info(id, name, birth_date)
values(1002, 'coolie', '2011-09-09');

insert into user_info(id, name, birth_date)
values(1003, 'faker', '1920-09-09');

insert into user_info(id, name, birth_date)
values(1004, 'elias', '1985-09-09');

insert into post(id, description, user_info_id)
values(101, 'This is my first post',1001);
insert into post(id, description, user_info_id)
values(102, 'This is my second post',1001); 

insert into post(id, description, user_info_id)
values(103, 'This is my first post',1002);

insert into post(id, description, user_info_id)
values(104, 'This is my first post',1004);
