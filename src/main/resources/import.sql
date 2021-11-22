
INSERT INTO
    Privilege(name, canEdit, seeUsers, crudUsers)
    VALUES ('user', 1, 0, 0),
           ('vorgesetzter', 1, 1, 0),
           ('admin', 1, 1, 1);


INSERT INTO
    Workplace(workplaceName)
    VALUES ('Zuhause'),
           ('Arbeit');



INSERT INTO User(username, password, firstname, lastname, birthday, present,  privilege_id)
VALUES ('user', 'user', 'user', 'user', '2004-03-04', 1,  1),
('vorgesetzer', 'vorgesetzer', 'vorgesetzer', 'vorgesetzer', '2005-03-04', 1, 2),
('admin', 'admin', 'admin', 'admin', '2006-03-04', 0, 3);




INSERT INTO Entry(checkIn, checkOut, description, isLogin, workplace_id, user_id)
VALUES ('2021-11-22 16:07:32.312843',	'2021-11-22 16:07:32.312843',	'guhioköuolhik.ui',	0,	2,	1),
       ('2021-11-22 16:07:32.312843',	'2021-11-22 16:07:32.312843',	'guhioköuolhik.ui',	0,	2,	2),
       ('2021-11-22 16:07:32.312843',	'2021-11-22 16:07:32.312843',	'guhioköuolhik.ui',	0,	2,	3);

