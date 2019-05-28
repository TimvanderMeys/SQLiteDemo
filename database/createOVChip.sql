CREATE TABLE OV_Chipkaart (
                              kaartNummer INTEGER NOT NULL PRIMARY KEY,
                              geldigTot   date NOT NULL,
                              klasse      number(1) NOT NULL,
                              saldo       number(16, 2) NOT NULL,
                              reizigerID  INTEGER NOT NULL,
                              FOREIGN KEY (reizigerID) REFERENCES Reiziger(reizigerID)
);


CREATE TABLE Reiziger (
                          reizigerID    INTEGER  NOT NULL PRIMARY KEY,
                          voorletters   varchar2(10) NOT NULL,
                          tussenvoegsel varchar2(10),
                          achternaam    varchar2(255) NOT NULL,
                          gebortedatum  date
);
