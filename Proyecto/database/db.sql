-- TABLAS
DROP TABLE IF EXISTS cuentas;
CREATE TABLE cuentas (
    id_cuenta INTEGER PRIMARY KEY AUTOINCREMENT,
    email TEXT,
    contrasena TEXT,
    nombre_apellidos TEXT
);

DROP TABLE IF EXISTS usuarios;
CREATE TABLE usuarios (
    id_cuenta INTEGER PRIMARY KEY,
    admin INTEGER NOT NULL,
    FOREIGN KEY (id_cuenta) REFERENCES cuentas(id_cuenta) ON DELETE CASCADE
);

DROP TABLE IF EXISTS clientes;
CREATE TABLE clientes (
    id_cuenta INTEGER PRIMARY KEY,
    direccion TEXT,
    FOREIGN KEY (id_cuenta) REFERENCES cuentas(id_cuenta) ON DELETE CASCADE
);

DROP TABLE IF EXISTS reservas;
CREATE TABLE reservas (
    id_reserva INTEGER PRIMARY KEY AUTOINCREMENT,
    fecha_ini DATE,
    fecha_fin DATE,
    id_cuenta INTEGER NOT NULL,
    FOREIGN KEY (id_cuenta) REFERENCES usuarios(id_cuenta)
);

DROP TABLE IF EXISTS alojamientos;
CREATE TABLE alojamientos (
    id_alojamiento INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT,
    calle TEXT
);

DROP TABLE IF EXISTS hoteles;
CREATE TABLE hoteles (
    id_alojamiento INTEGER PRIMARY KEY,
    clasificacion INTEGER,
    tipo_habitacion TEXT,
    numero_huespedes INTEGER,
    FOREIGN KEY (id_alojamiento) REFERENCES alojamientos(id_alojamiento) ON DELETE CASCADE
);

DROP TABLE IF EXISTS aps_turisticos;
CREATE TABLE aps_turisticos (
    id_alojamiento INTEGER PRIMARY KEY,
    dist_centro_km REAL,
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
    hoteles h ON a.id_alojamiento = h.id_alojamiento;

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
    aps_turisticos ap ON a.id_alojamiento = ap.id_alojamiento;

-- Vista para atributos de usuarios
CREATE VIEW vista_usuarios AS
SELECT
    c.id_cuenta,
    c.email,
    c.contrasena,
    c.nombre_apellidos,
    u.admin
FROM
    cuentas c
JOIN
    usuarios u ON c.id_cuenta = u.id_cuenta;

-- Vista para atributos de clientes
CREATE VIEW vista_clientes AS
SELECT
    c.id_cuenta,
    c.email,
    c.contrasena,
    c.nombre_apellidos,
    cl.direccion
FROM
    cuentas c
JOIN
    clientes cl ON c.id_cuenta = cl.id_cuenta;
