DROP DATABASE Interfaces;
CREATE DATABASE Interfaces;

use Interfaces;



CREATE TABLE Clientes(
	codigo INT NOT NULL,
	nif VARCHAR(9) NOT NULL,
	apellidos VARCHAR(35) NOT NULL,
	nombre VARCHAR(15) NOT NULL,
	domicilio VARCHAR(40) NOT NULL,
	codigo_postal VARCHAR(5) NOT NULL,
	localidad VARCHAR(20) NOT NULL,
	telefono VARCHAR(9) NOT NULL,
	movil VARCHAR(9) NOT NULL,
	fax VARCHAR(9) NOT NULL,
	email VARCHAR(20) NOT NULL,
	total_venta FLOAT NOT NULL,
	PRIMARY KEY (codigo)
);

INSERT INTO Clientes (codigo, nif, apellidos, nombre, domicilio, codigo_postal, localidad, telefono, movil, fax, email, total_venta) 
VALUES
(1, '12345678A', 'Gómez Pérez', 'Juan', 'Calle Mayor 1', '28001', 'Madrid', '910000001', '600000001', '910000101', 'juan.gomez@ex.com', 150.75),
(2, '23456789B', 'López García', 'María', 'Avenida Sol 23', '41012', 'Sevilla', '910000002', '600000002', '910000102', 'maria.lopez@ex.com', 200.50),
(3, '34567890C', 'Fernández Ruiz', 'Luis', 'Plaza Luna 5', '50004', 'Zaragoza', '910000003', '600000003', '910000103', 'luis.fer@ex.com', 175.30),
(4, '45678901D', 'Martínez Torres', 'Ana', 'Calle Río 12', '30002', 'Murcia', '910000004', '600000004', '910000104', 'ana.mtz@ex.com', 220.10),
(5, '56789012E', 'Sánchez Morales', 'Carlos', 'Calle Mar 7', '08014', 'Barcelona', '910000005', '600000005', '910000105', 'carlos.sm@ex.com', 190.00);


CREATE TABLE Proveedores(
	codigo INT NOT NULL,
	nif VARCHAR(9) NOT NULL,
	apellidos VARCHAR(35) NOT NULL,
	nombre VARCHAR(15) NOT NULL,
	domicilio VARCHAR(40) NOT NULL,
	codigo_postal VARCHAR(5) NOT NULL,
	localidad VARCHAR(20) NOT NULL,
	telefono VARCHAR(9) NOT NULL,
	movil VARCHAR(9) NOT NULL,
	fax VARCHAR(9) NOT NULL,
	email VARCHAR(20) NOT NULL,
	total_compras FLOAT NOT NULL,
	PRIMARY KEY (codigo)
);

CREATE TABLE Articulos(
	codigo INT NOT NULL,
	descripcion VARCHAR(25) NOT NULL,
	stock FLOAT NOT NULL,
	stock_minimo FLOAT NOT NULL,
	precio_compra FLOAT NOT NULL,
	precio_venta FLOAT NOT NULL,
	PRIMARY KEY(codigo)
);