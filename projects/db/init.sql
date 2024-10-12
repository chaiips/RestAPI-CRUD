CREATE DATABASE IF NOT EXISTS accounts;

USE accounts;

CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    place_birth VARCHAR(255),
    age INT
);

INSERT INTO users (name, last_name, email, password, place_birth, age) VALUES
('Juan', 'Pérez', 'juan.perez@example.com', 'password123', 'Buenos Aires', 25),
('María', 'Gómez', 'maria.gomez@example.com', 'password456', 'Madrid', 30),
('Carlos', 'Fernández', 'carlos.fernandez@example.com', 'password789', 'USA', 28),
('Laura', 'Martínez', 'laura.martinez@example.com', 'password321', 'Mexico', 22),
('Pedro', 'Sánchez', 'pedro.sanchez@example.com', 'password654', 'Mexico', 35),
('Ana', 'López', 'ana.lopez@example.com', 'password111', 'Granada', 29),
('Jorge', 'Ramírez', 'jorge.ramirez@example.com', 'password222', 'Brazil', 34),
('Sofía', 'Torres', 'sofia.torres@example.com', 'password333', 'Zaragoza', 27),
('Ricardo', 'Hernández', 'ricardo.hernandez@example.com', 'password444', 'Mexico', 31),
('Lucía', 'Jiménez', 'lucia.jimenez@example.com', 'password555', 'Venezuela', 24);
