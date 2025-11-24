-- Script para crear la base de datos de autenticación
-- Ejecuta este script en phpMyAdmin después de activar XAMPP

CREATE DATABASE IF NOT EXISTS testdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE testdb;

-- Crear tabla de roles
CREATE TABLE IF NOT EXISTS roles (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(20) NULL,
    PRIMARY KEY (id),
    UNIQUE KEY UK_roles_name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Insertar roles predeterminados
INSERT IGNORE INTO roles (name) VALUES 
('ROLE_USER'),
('ROLE_MODERATOR'),
('ROLE_ADMIN');

-- Crear tabla de usuarios
CREATE TABLE IF NOT EXISTS users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(20) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(120) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY UKr43af9ap4edm43mmtq01oddj6 (username),
    UNIQUE KEY UK6dotkott2kjsp8vw4d0m25fb7 (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Crear tabla de relación usuario-roles
CREATE TABLE IF NOT EXISTS user_roles (
    user_id BIGINT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    KEY FKhfh9dx7w3ubf1co1vdev94g3f (role_id),
    CONSTRAINT FKh8ciramu9cc9q3qcqiv4ue8a6 FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT FKhfh9dx7w3ubf1co1vdev94g3f FOREIGN KEY (role_id) REFERENCES roles (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Verificar datos insertados
SELECT * FROM roles;