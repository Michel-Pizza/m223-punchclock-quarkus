
INSERT INTO
    Privilege(name, canEdit, seeUsers, crudUsers)
    VALUES ('user', 1, 0, 0),
           ('vorgesetzter', 1, 1, 0),
           ('admin', 1, 1, 1);


INSERT INTO
    Workplace(workplaceName)
    VALUES ('Zuhause'),
           ('Arbeit');



INSERT INTO User(username, password, firstname, lastname, birthday, present, privilege_id)
VALUES ('Michel', 'Schrem', 'Michel', 'Schrem', '2004-03-04', false, 1),
('Michel2', 'Schrem2', 'Michel2', 'Schrem2', '2005-03-04', false, 2),
('Michel3', 'Schrem3', 'Michel3', 'Schrem3', '2006-03-04', false, 3);
