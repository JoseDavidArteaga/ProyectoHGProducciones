-- Datos de prueba para eventos de HGProducciones

-- CLIENTES DE EJEMPLO
INSERT INTO usuarios (nombre, apellido, email, create_at) VALUES 
('Juan', 'Pérez', 'juan.perez@email.com', CURRENT_TIMESTAMP),
('María', 'González', 'maria.gonzalez@email.com', CURRENT_TIMESTAMP),
('Carlos', 'Rodríguez', 'carlos.rodriguez@email.com', CURRENT_TIMESTAMP),
('Ana', 'Martínez', 'ana.martinez@email.com', CURRENT_TIMESTAMP),
('Luis', 'López', 'luis.lopez@email.com', CURRENT_TIMESTAMP);

-- CONCIERTOS
INSERT INTO eventos (nombre, descripcion, precio_ticket, tipo_evento, imagen, ubicacion, capacidad_total, tickets_disponibles, fecha_evento, create_at) VALUES 
('Concierto de Rock Nacional', 'Noche de rock con las mejores bandas nacionales', 45000, 'Concierto', 'concierto_rock.png', 'Teatro Mayor Julio Mario Santodomingo', 2000, 2000, '2025-02-15 20:00:00', CURRENT_TIMESTAMP),
('Festival de Jazz', 'Festival de jazz con artistas internacionales', 65000, 'Concierto', 'festival_jazz.png', 'Centro de Convenciones Ágora', 1500, 1500, '2025-03-10 19:30:00', CURRENT_TIMESTAMP),
('Concierto Sinfónico', 'Orquesta Sinfónica Nacional presenta obras clásicas', 55000, 'Concierto', 'concierto_sinfonico.png', 'Teatro Colón', 800, 800, '2025-02-28 20:30:00', CURRENT_TIMESTAMP),
('Noche de Salsa', 'Los mejores exponentes de la salsa colombiana', 35000, 'Concierto', 'concierto_salsa.png', 'Coliseo El Campín', 5000, 5000, '2025-03-05 21:00:00', CURRENT_TIMESTAMP);

-- TEATRO
INSERT INTO eventos (nombre, descripcion, precio_ticket, tipo_evento, imagen, ubicacion, capacidad_total, tickets_disponibles, fecha_evento, create_at) VALUES 
('Hamlet', 'La obra maestra de Shakespeare en versión moderna', 40000, 'Teatro', 'teatro_hamlet.png', 'Teatro Nacional La Castellana', 400, 400, '2025-02-20 19:00:00', CURRENT_TIMESTAMP),
('El Avaro', 'Comedia clásica de Molière', 30000, 'Teatro', 'teatro_avaro.png', 'Teatro Libre', 250, 250, '2025-03-01 18:00:00', CURRENT_TIMESTAMP),
('La Casa de Bernarda Alba', 'Drama de Federico García Lorca', 35000, 'Teatro', 'teatro_bernarda.png', 'Teatro Jorge Eliécer Gaitán', 600, 600, '2025-03-15 20:00:00', CURRENT_TIMESTAMP);

-- EVENTOS DEPORTIVOS
INSERT INTO eventos (nombre, descripcion, precio_ticket, tipo_evento, imagen, ubicacion, capacidad_total, tickets_disponibles, fecha_evento, create_at) VALUES 
('Millonarios vs Nacional', 'Clásico del fútbol colombiano', 25000, 'Deportivo', 'futbol_clasico.png', 'Estadio El Campín', 40000, 40000, '2025-02-25 16:00:00', CURRENT_TIMESTAMP),
('Torneo de Tenis Bogotá Open', 'Torneo ATP de tenis en Bogotá', 80000, 'Deportivo', 'torneo_tenis.png', 'Club El Nogal', 1000, 1000, '2025-03-08 14:00:00', CURRENT_TIMESTAMP),
('Carrera 10K HGProducciones', 'Carrera atlética por las calles de Bogotá', 50000, 'Deportivo', 'carrera_10k.png', 'Parque Simón Bolívar', 3000, 3000, '2025-03-12 07:00:00', CURRENT_TIMESTAMP);

-- EVENTOS ESPECIALES
INSERT INTO eventos (nombre, descripcion, precio_ticket, tipo_evento, imagen, ubicacion, capacidad_total, tickets_disponibles, fecha_evento, create_at) VALUES 
('Stand Up Comedy Night', 'Los mejores comediantes del país en una noche', 28000, 'Comedia', 'standup_comedy.png', 'Teatro Cafam', 300, 300, '2025-02-22 21:30:00', CURRENT_TIMESTAMP),
('Exposición de Arte Digital', 'Muestra interactiva de arte digital contemporáneo', 15000, 'Cultural', 'expo_arte_digital.png', 'Museo de Arte Moderno', 200, 200, '2025-02-18 10:00:00', CURRENT_TIMESTAMP);

-- TICKETS DE EJEMPLO (algunos ya vendidos)
INSERT INTO tickets (evento_id, cliente_id, numero_ticket, precio_compra, estado, fecha_compra, create_at) VALUES 
(1, 1, 'HGP-1-ABC12345', 45000, 'vendido', '2025-01-15 10:30:00', CURRENT_TIMESTAMP),
(1, 2, 'HGP-1-DEF67890', 45000, 'vendido', '2025-01-16 14:20:00', CURRENT_TIMESTAMP),
(4, 1, 'HGP-4-GHI11111', 35000, 'vendido', '2025-01-18 16:45:00', CURRENT_TIMESTAMP);

-- Actualizar tickets disponibles para eventos con ventas
UPDATE eventos SET tickets_disponibles = tickets_disponibles - 2 WHERE id = 1;
UPDATE eventos SET tickets_disponibles = tickets_disponibles - 1 WHERE id = 4;