insert into REIZIGER
(voorletters, tussenvoegsel, achternaam,gebortedatum)
values ('G', 'van', 'Rijn', date('2002-09-17'));
insert into REIZIGER
(voorletters, tussenvoegsel, achternaam,gebortedatum)
values ('B', 'van', 'Rijn', date('2002-10-22'));
insert into REIZIGER
(voorletters, tussenvoegsel, achternaam,gebortedatum)
values ('H', null, 'Lubben', date('1998-08-11'));
insert into REIZIGER
(voorletters, tussenvoegsel, achternaam,gebortedatum)
values ('F', null, 'Memari', date('2002-12-03'));
insert into REIZIGER
(voorletters, tussenvoegsel, achternaam,gebortedatum)
values ('G', null, 'Piccardo', date('2002-12-03'));

insert into OV_Chipkaart
(kaartNummer, geldigTot, klasse, saldo, reizigerID)
values (35283, date('2018-05-31'), 2, 25.50, 2);
insert into OV_Chipkaart
(kaartNummer, geldigTot, klasse, saldo, reizigerID)
values (46392, date('2017-05-31'), 2, 5.50, 2);
insert into OV_Chipkaart
(kaartNummer, geldigTot, klasse, saldo, reizigerID)
values (57401, date('2015-05-31'), 2, 0.0, 2);
insert into OV_Chipkaart
(kaartNummer, geldigTot, klasse, saldo, reizigerID)
values (68514, date('2020-03-31'), 1, 2.50, 3);
insert into OV_Chipkaart
(kaartNummer, geldigTot, klasse, saldo, reizigerID)
values (79625, date('2020-01-31'), 1, 25.50, 4);
insert into OV_Chipkaart
(kaartNummer, geldigTot, klasse, saldo, reizigerID)
values (90537, date('2019-12-31'), 2, 20.0, 5);
insert into OV_Chipkaart
(kaartNummer, geldigTot, klasse, saldo, reizigerID)
values (18326, date('2017-12-31'), 2, 0.0, 5);

commit;
