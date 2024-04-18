drop table if exists usuarios;
create table usuarios (
    email               varchar2(30),
    telefono            varchar2(9), -- Cambiado a VARCHAR2
    nombre_apellidos    varchar2(50),
    contrasena          varchar2(16),
    direccion           varchar2(50),
    constraint pk1 primary key(email, telefono)
);

drop table if exists alojamientos;
create table alojamientos (
    cod_alojamiento         varchar2(10) primary key,
    nom_alojamiento         varchar2(20),
    direccion_alojamiento   varchar2(50),
    num_huespedes           number(10),
    tipo_alojamiento        varchar2(20)
);

drop table if exists reserva;
create table reserva (
    fecha_ini           date,
    fecha_fin           date,
    email               varchar2(30),
    telefono            varchar2(9), -- Cambiado a VARCHAR2
    cod_alojamiento     varchar2(10),
    constraint pk2 primary key(email, telefono, cod_alojamiento, fecha_ini, fecha_fin),
    constraint fk1 foreign key(email, telefono) references usuarios(email, telefono),
    constraint fk3 foreign key(cod_alojamiento) references alojamientos(cod_alojamiento)
);

drop table if exists hoteles;
create table hoteles (
    cod_alojamiento             varchar2(10) primary key,
    numero_huespedes_hoteles    number(10),
    tipo_habitacion             varchar2(10),
    clasificacion               number(1),
    constraint fk4 foreign key(cod_alojamiento) references alojamientos(cod_alojamiento)
);

create table aps_turisticos (
    cod_alojamiento     varchar2(10) primary key,
    dist_al_centro_km   number(5),
    constraint fk5 foreign key(cod_alojamiento) references alojamientos(cod_alojamiento)
);

insert into alojamientos (cod_alojamiento, nom_alojamiento, direccion_alojamiento, num_huespedes, tipo_alojamiento)
values
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

insert into aps_turisticos (cod_alojamiento, dist_al_centro_km)
values
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

insert into usuarios (email, telefono, nombre_apellidos, contrasena, direccion)
values
('usuario1@example.com', '123456789', 'Juan Pérez', 'contraseña1', 'Calle Falsa 123'),
('usuario2@example.com', '987654321', 'María García', 'contraseña2', 'Avenida Principal 456'),
('usuario3@example.com', '567890123', 'Pedro Martínez', 'contraseña3', 'Plaza Central 789'),
('usuario4@example.com', '321654987', 'Laura López', 'contraseña4', 'Calle Real 234'),
('usuario5@example.com', '789012345', 'Ana Sánchez', 'contraseña5', 'Paseo Marítimo 567'),
('usuario6@example.com', '654321098', 'Carlos Rodríguez', 'contraseña6', 'Avenida del Bosque 890'),
('usuario7@example.com', '456789012', 'Sofía Fernández', 'contraseña7', 'Callejón Oscuro 345'),
('usuario8@example.com', '890123456', 'Miguel Ruiz', 'contraseña8', 'Ronda de la Luna 678'),
('usuario9@example.com', '234567890', 'Elena Torres', 'contraseña9', 'Travesía del Sol 901'),
('usuario10@example.com', '123456789', 'David Gómez', 'contraseña10', 'Callejuela Estrecha 234');

insert into reserva (fecha_ini, fecha_fin, email, telefono, cod_alojamiento)
values
('2024-04-01', '2024-04-10', 'usuario1@example.com', '123456789', 'ALOJ001'),
('2024-04-05', '2024-04-15', 'usuario2@example.com', '987654321', 'ALOJ002'),
('2024-04-10', '2024-04-20', 'usuario3@example.com', '567890123', 'ALOJ003'),
('2024-04-15', '2024-04-25', 'usuario4@example.com', '321654987', 'ALOJ004'),
('2024-04-20', '2024-04-30', 'usuario5@example.com', '789012345', 'ALOJ005'),
('2024-04-25', '2024-05-05', 'usuario6@example.com', '654321098', 'ALOJ006'),
('2024-05-01', '2024-05-10', 'usuario7@example.com', '456789012', 'ALOJ007'),
('2024-05-05', '2024-05-15', 'usuario8@example.com', '890123456', 'ALOJ008'),
('2024-05-10', '2024-05-20', 'usuario9@example.com', '234567890', 'ALOJ009');

insert into hoteles (cod_alojamiento, numero_huespedes_hoteles, tipo_habitacion, clasificacion)
values
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
