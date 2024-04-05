CREATE TABLE Usuarios (
    Email               VARCHAR2(30),
    Telefono            NUMBER(9),
    Nombre_Apellidos    VARCHAR2(50),
    Contraseña          VARCHAR2(16),
    Direccion           VARCHAR2(50),
    CONSTRAINT PK1 PRIMARY KEY(Email, Telefono)
);

CREATE TABLE Alojamientos (
    Cod_Alojamiento         VARCHAR2(10) PRIMARY KEY,
    Nom_Alojamiento         VARCHAR2(20),
    Direccion_Alojamiento   VARCHAR2(50),
    Num_Huespedes           NUMBER(10),
    Tipo_Alojamiento        VARCHAR2(20)
);

CREATE TABLE Reserva (
    Fecha_Ini           DATE,
    Fecha_Fin           DATE,
    Email               VARCHAR2(30),
    Telefono            NUMBER(9),
    Cod_Alojamiento     VARCHAR2(10),
    CONSTRAINT PK2 PRIMARY KEY(Email, Telefono, Cod_Alojamiento, Fecha_Ini, Fecha_Fin),
    CONSTRAINT FK1 FOREIGN KEY(Email, Telefono) REFERENCES Usuarios(Email, Telefono),
    CONSTRAINT FK3 FOREIGN KEY(Cod_Alojamiento) REFERENCES Alojamientos(Cod_Alojamiento)
);

CREATE TABLE Hoteles (
    Cod_Alojamiento             VARCHAR2(10) PRIMARY KEY,
    Numero_Huespedes_Hoteles    NUMBER(10),
    Tipo_Habitacion             VARCHAR2(10),
    Clasificacion               NUMBER(1),
    CONSTRAINT FK4 FOREIGN KEY(Cod_Alojamiento) REFERENCES Alojamientos(Cod_Alojamiento)
);

CREATE TABLE Aps_Turisticos (
    Cod_Alojamiento     VARCHAR2(10) PRIMARY KEY,
    Dist_Al_Centro_Km   NUMBER(5),
    CONSTRAINT FK5 FOREIGN KEY(Cod_Alojamiento) REFERENCES Alojamientos(Cod_Alojamiento)
);
INSERT INTO "Alojamientos" ("Cod_Alojamiento", "Nom_Alojamiento", "Direccion_Alojamiento", "Num_Huespedes", "Tipo_Alojamiento")
VALUES 
('ALOJ001', 'Hotel Ejemplo', 'Calle Principal 123', 50, 'Hotel'),
('ALOJ002', 'Cabaña de Montaña', 'Camino del Bosque 45', 4, 'Hotel'),
('ALOJ003', 'Apartamento Costero', 'Paseo Marítimo 67', 6, 'ApartamentoTuristico'),
('ALOJ004', 'Villa del Lago', 'Avenida del Lago 89', 8, 'Hotel'),
('ALOJ005', 'Hostal La Plaza', 'Plaza Mayor 12', 20, 'Hotel'),
('ALOJ006', 'Albergue del Bosque', 'Sendero Verde s/n', 30, 'Hotel'),
('ALOJ007', 'Resort Paradise', 'Carretera del Paraíso km 10', 200, 'Hotel'),
('ALOJ008', 'Pensión del Sol', 'Calle del Sol 34', 15, 'Hotel'),
('ALOJ009', 'Casa Rural El Encanto', 'Camino Viejo 56', 10, 'Hotel'),
('ALOJ010', 'Apartahotel Playa Azul', 'Avenida de la Playa 78', 40, 'ApartamentoTuristico');

INSERT INTO "Aps_Turisticos" ("Cod_Alojamiento", "Dist_Al_Centro_Km")
VALUES 
('ALOJ001', 2),
('ALOJ002', 10),
('ALOJ003', 0.5),
('ALOJ004', 15),
('ALOJ005', 1),
('ALOJ006', 20),
('ALOJ007', 5),
('ALOJ008', 3),
('ALOJ009', 12),
('ALOJ010', 0.8);
INSERT INTO Usuarios (Email, Telefono, Nombre_Apellidos, Contraseña, Direccion)
VALUES 
('usuario1@example.com', 123456789, 'Juan Pérez', 'contraseña1', 'Calle Falsa 123'),
('usuario2@example.com', 987654321, 'María García', 'contraseña2', 'Avenida Principal 456'),
('usuario3@example.com', 567890123, 'Pedro Martínez', 'contraseña3', 'Plaza Central 789'),
('usuario4@example.com', 321654987, 'Laura López', 'contraseña4', 'Calle Real 234'),
('usuario5@example.com', 789012345, 'Ana Sánchez', 'contraseña5', 'Paseo Marítimo 567'),
('usuario6@example.com', 654321098, 'Carlos Rodríguez', 'contraseña6', 'Avenida del Bosque 890'),
('usuario7@example.com', 456789012, 'Sofía Fernández', 'contraseña7', 'Callejón Oscuro 345'),
('usuario8@example.com', 890123456, 'Miguel Ruiz', 'contraseña8', 'Ronda de la Luna 678'),
('usuario9@example.com', 234567890, 'Elena Torres', 'contraseña9', 'Travesía del Sol 901'),
('usuario10@example.com', 012345678, 'David Gómez', 'contraseña10', 'Callejuela Estrecha 234');

INSERT INTO Reserva (Fecha_Ini, Fecha_Fin, Email, Telefono, Cod_Alojamiento)
VALUES 
(TO_DATE('2024-04-01', 'YYYY-MM-DD'), TO_DATE('2024-04-10', 'YYYY-MM-DD'), 'usuario1@example.com', 123456789, 'ALOJ001'),
(TO_DATE('2024-04-05', 'YYYY-MM-DD'), TO_DATE('2024-04-15', 'YYYY-MM-DD'), 'usuario2@example.com', 987654321, 'ALOJ002'),
(TO_DATE('2024-04-10', 'YYYY-MM-DD'), TO_DATE('2024-04-20', 'YYYY-MM-DD'), 'usuario3@example.com', 567890123, 'ALOJ003'),
(TO_DATE('2024-04-15', 'YYYY-MM-DD'), TO_DATE('2024-04-25', 'YYYY-MM-DD'), 'usuario4@example.com', 321654987, 'ALOJ004'),
(TO_DATE('2024-04-20', 'YYYY-MM-DD'), TO_DATE('2024-04-30', 'YYYY-MM-DD'), 'usuario5@example.com', 789012345, 'ALOJ005'),
(TO_DATE('2024-04-25', 'YYYY-MM-DD'), TO_DATE('2024-05-05', 'YYYY-MM-DD'), 'usuario6@example.com', 654321098, 'ALOJ006'),
(TO_DATE('2024-05-01', 'YYYY-MM-DD'), TO_DATE('2024-05-10', 'YYYY-MM-DD'), 'usuario7@example.com', 456789012, 'ALOJ007'),
(TO_DATE('2024-05-05', 'YYYY-MM-DD'), TO_DATE('2024-05-15', 'YYYY-MM-DD'), 'usuario8@example.com', 890123456, 'ALOJ008'),
(TO_DATE('2024-05-10', 'YYYY-MM-DD'), TO_DATE('2024-05-20', 'YYYY-MM-DD'), 'usuario9@example.com', 234567890, 'ALOJ009'),
(TO_DATE('2024-05-15', 'YYYY-MM-DD'), TO_DATE('2024-05-25', 'YYYY-MM-DD'), 'usuario10@example.com', 012345678, 'ALOJ010');
INSERT INTO Hoteles (Cod_Alojamiento, Numero_Huespedes_Hoteles, Tipo_Habitacion, Clasificacion)
VALUES 
('ALOJ001', 100, 'Doble', 4),
('ALOJ002', 6, 'Suite', 5),
('ALOJ003', 8, 'Apartamento', 3),
('ALOJ004', 150, 'Doble', 4),
('ALOJ005', 30, 'Doble', 3),
('ALOJ006', 40, 'Dormitorio', 2),
('ALOJ007', 300, 'Suite', 5),
('ALOJ008', 20, 'Doble', 3),
('ALOJ009', 12, 'Cabaña', 2),
('ALOJ010', 60, 'Apartamento', 4);
