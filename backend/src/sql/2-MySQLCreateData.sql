-- ----------------------------------------------------------------------------
-- Put here INSERT statements for inserting data required by the application
-- in the "pa-project" database.
-------------------------------------------------------------------------------

INSERT INTO Province (name) VALUES ('A Coruña');
INSERT INTO Province (name) VALUES ('Pontevedra');

INSERT INTO SportTestType (name) VALUES ('Running');
INSERT INTO SportTestType (name) VALUES ('Ciclismo');

INSERT INTO SportTest (name, description, date, price, maxParticipants, numParticipants, sportTestTypeId, location, provinceId, rating, numRatings, version)
    VALUES ('Prueba 1', 'Desc', '2020-07-01 10:00', 9.95, 500, 93, 1, 'A Coruña', 1, 0, 0, 0);
INSERT INTO SportTest (name, description, date, price, maxParticipants, numParticipants, sportTestTypeId, location, provinceId, rating, numRatings, version)
    VALUES ('Prueba 2', 'Desc', '2022-01-01 10:00', 9.95, 100, 0, 2, 'A Coruña', 1, 0, 0, 0);
INSERT INTO SportTest (name, description, date, price, maxParticipants, numParticipants, sportTestTypeId, location, provinceId, rating, numRatings, version)
    VALUES ('Prueba 3', 'Desc', '2022-02-01 10:00', 9.95, 300, 0, 1, 'Pontevedra', 2, 0, 0, 0);
INSERT INTO SportTest (name, description, date, price, maxParticipants, numParticipants, sportTestTypeId, location, provinceId, rating, numRatings, version)
    VALUES ('Prueba 4', 'Desc', '2022-03-01 10:00', 9.95, 2, 0, 2, 'Pontevedra', 2, 0, 0, 0);

INSERT INTO User (userName, password, firstName, lastName, email, role)
    VALUES ('competitor1', '$2a$10$uicNGMO41KcyofyMPaCCwuQ89o1WM/wn5f9TGgCuFjZrfDLnECPyi', 'Juan', 'González', 'juan_gonzalez@mail.com', 0);
INSERT INTO User (userName, password, firstName, lastName, email, role)
    VALUES ('competitor2', '$2a$10$uicNGMO41KcyofyMPaCCwuQ89o1WM/wn5f9TGgCuFjZrfDLnECPyi', 'Sofía', 'Gómez', 'sofia_gomez@mail.com', 0);
INSERT INTO User (userName, password, firstName, lastName, email, role)
    VALUES ('competitor3', '$2a$10$uicNGMO41KcyofyMPaCCwuQ89o1WM/wn5f9TGgCuFjZrfDLnECPyi', 'Pedro', 'Pérez', 'pedro_perez@mail.com', 0);
INSERT INTO User (userName, password, firstName, lastName, email, role)
    VALUES ('employee', '$2a$10$uicNGMO41KcyofyMPaCCwuQ89o1WM/wn5f9TGgCuFjZrfDLnECPyi', 'Belén', 'Fernández', 'belen_fernandez@mail.com', 1);


INSERT INTO Inscription (userId, sportTestId, cardNumber, reservationDate, price, dorsal, dorsalCollected, score, version)
    VALUES (1, 1, 12345678, '2019-01-07 10:00', 9.95, 1, true, -1, 1);
INSERT INTO Inscription (userId, sportTestId, cardNumber, reservationDate, price, dorsal, dorsalCollected, score, version)
    VALUES (1, 2, 12345678, '2019-03-07 10:00', 9.95, 3, true, -1, 1);