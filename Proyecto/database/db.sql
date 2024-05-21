-- --TABLAS
-- DROP TABLE IF EXISTS cuentas;
-- CREATE TABLE cuentas(
--     id_cuenta           INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
--     email               VARCHAR2(20),
--     contrasena          VARCHAR(8),
--     nombre_apellidos    VARCHAR2(20)
-- );

-- DROP TABLE IF EXISTS usuarios;
-- CREATE TABLE usuarios(
--     id_cuenta           INT NOT NULL PRIMARY KEY FOREIGN KEY REFERENCES cuentas(id_cuenta) ON DELETE CASCADE
--     admin               BOOLEAN NOT NULL
-- );

-- DROP TABLE IF EXISTS clientes;
-- CREATE TABLE clientes(
--     id_cuenta           INT NOT NULL IDENTITY(1,1) PRIMARY KEY FOREIGN KEY REFERENCES cuentas(id_cuenta) ON DELETE CASCADE,
--     direccion           VARCHAR2(30)
-- );

-- DROP TABLE IF EXISTS reservas;
-- CREATE TABLE reservas(
--     id_reserva  INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
--     fecha_ini   DATE,
--     fecha_fin   DATE,
--     id_cuenta   INT NOT NULL FOREIGN KEY REFERENCES usuarios(id_cuenta)
-- );

-- DROP TABLE IF EXISTS alojamientos;
-- CREATE TABLE alojamientos(
--     id_alojamiento  INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
--     nombre          VARCHAR2(20),
--     calle           VARCHAR2(30)
-- );

-- DROP TABLE IF EXISTS hoteles;
-- CREATE TABLE hoteles(
--     id_alojamiento      INT NOT NULL PRIMARY KEY FOREIGN KEY REFERENCES alojamientos(id_alojamiento) ON DELETE CASCADE,
--     clasificacion       NUMBER(1),
--     tipo_habitacion     VARCHAR(15),
--     numero_huespedes    NUMBER(1)
-- );

-- DROP TABLE IF EXISTS aps_turisticos;
-- CREATE TABLE aps_turisticos(
--     id_alojamiento  INT NOT NULL PRIMARY KEY FOREIGN KEY REFERENCES alojamientos(id_alojamiento) ON DELETE CASCADE,
--     dist_centro_km  NUMBER(5)
-- );
-- -- Vista para atributos de hoteles
-- CREATE OR REPLACE VIEW vista_hoteles AS
-- SELECT 
--     a.id_alojamiento,
--     a.nombre AS nombre_alojamiento,
--     a.calle,
--     h.clasificacion,
--     h.tipo_habitacion,
--     h.numero_huespedes
-- FROM 
--     alojamientos a
-- JOIN 
--     hoteles h 
-- ON a.id_alojamiento = h.id_alojamiento;

-- -- Vista para atributos de aps_turisticos
-- CREATE OR REPLACE VIEW vista_aps_turisticos AS
-- SELECT 
--     a.id_alojamiento,
--     a.nombre AS nombre_alojamiento,
--     a.calle,
--     ap.dist_centro_km
-- FROM 
--     alojamientos a
-- JOIN 
--     aps_turisticos ap 
-- ON a.id_alojamiento = ap.id_alojamiento;

-- -- Vista para atributos de usuarios
-- CREATE OR REPLACE VIEW vista_usuarios AS
-- SELECT c.id_cuenta, c.email, c.contrasena, c.nombre_apellidos, u.admin
-- FROM cuentas c
-- JOIN usuarios u
-- ON c.id_cuenta = u.id_cuenta;

-- -- Vista para atributos de clientes
-- CREATE OR REPLACE VIEW vista_clientes AS
-- SELECT c.id_cuenta, c.email, c.contrasena, c.nombre_apellidos, cl.direccion
-- FROM cuentas c
-- JOIN clientes cl
-- ON c.id_cuenta = cl.id_cuenta;

-- TABLAS
DROP TABLE IF EXISTS cuentas;
CREATE TABLE cuentas(
    id_cuenta           INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    email               VARCHAR(20),
    contrasena          VARCHAR(8),
    nombre_apellidos    VARCHAR(20)
);

DROP TABLE IF EXISTS usuarios;
CREATE TABLE usuarios(
    id_cuenta           INTEGER NOT NULL PRIMARY KEY,
    admin               INTEGER NOT NULL,
    FOREIGN KEY (id_cuenta) REFERENCES cuentas(id_cuenta) ON DELETE CASCADE
);

DROP TABLE IF EXISTS clientes;
CREATE TABLE clientes(
    id_cuenta           INTEGER NOT NULL PRIMARY KEY,
    direccion           VARCHAR(30),
    FOREIGN KEY (id_cuenta) REFERENCES cuentas(id_cuenta) ON DELETE CASCADE
);

DROP TABLE IF EXISTS reservas;
CREATE TABLE reservas(
    id_reserva  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    fecha_ini   DATE,
    fecha_fin   DATE,
    id_cuenta   INTEGER NOT NULL,
    FOREIGN KEY (id_cuenta) REFERENCES usuarios(id_cuenta)
);

DROP TABLE IF EXISTS alojamientos;
CREATE TABLE alojamientos(
    id_alojamiento  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    nombre          VARCHAR(20),
    calle           VARCHAR(30)
);

DROP TABLE IF EXISTS hoteles;
CREATE TABLE hoteles(
    id_alojamiento      INTEGER NOT NULL PRIMARY KEY,
    clasificacion       INTEGER,
    tipo_habitacion     VARCHAR(15),
    numero_huespedes    INTEGER,
    FOREIGN KEY (id_alojamiento) REFERENCES alojamientos(id_alojamiento) ON DELETE CASCADE
);

DROP TABLE IF EXISTS aps_turisticos;
CREATE TABLE aps_turisticos(
    id_alojamiento  INTEGER NOT NULL PRIMARY KEY,
    dist_centro_km  INTEGER,
    FOREIGN KEY (id_alojamiento) REFERENCES alojamientos(id_alojamiento) ON DELETE CASCADE
);

-- Vista para atributos de hoteles
CREATE VIEW vista_hoteles AS
SELECT 
    a.id_alojamiento,
    a.nombre AS nombre_alojamiento,
    a.calle,
    h.clasificacion,
    h.tipo_habitacion,
    h.numero_huespedes
FROM 
    alojamientos a
JOIN 
    hoteles h 
ON a.id_alojamiento = h.id_alojamiento;

-- Vista para atributos de aps_turisticos
CREATE VIEW vista_aps_turisticos AS
SELECT 
    a.id_alojamiento,
    a.nombre AS nombre_alojamiento,
    a.calle,
    ap.dist_centro_km
FROM 
    alojamientos a
JOIN 
    aps_turisticos ap 
ON a.id_alojamiento = ap.id_alojamiento;

-- Vista para atributos de usuarios
CREATE VIEW vista_usuarios AS
SELECT c.id_cuenta, c.email, c.contrasena, c.nombre_apellidos, u.admin
FROM cuentas c
JOIN usuarios u
ON c.id_cuenta = u.id_cuenta;

-- Vista para atributos de clientes
CREATE VIEW vista_clientes AS
SELECT c.id_cuenta, c.email, c.contrasena, c.nombre_apellidos, cl.direccion
FROM cuentas c
JOIN clientes cl
ON c.id_cuenta = cl.id_cuenta;
