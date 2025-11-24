# HGProducciones - Sistema de Venta de Tickets

## Descripción del Proyecto

HGProducciones es un sistema web para la compra y venta de tickets de eventos de entretenimiento. El sistema permite a los usuarios ver eventos disponibles, comprar tickets y a los administradores gestionar eventos.

### Tipos de Eventos Soportados:
- 🎵 **Conciertos**: Rock, Jazz, Sinfónico, Salsa, etc.
- 🎭 **Teatro**: Obras clásicas, dramas, comedias
- ⚽ **Deportivos**: Fútbol, tenis, atletismo
- 🎤 **Comedia**: Stand-up comedy
- 🎨 **Culturales**: Exposiciones, muestras artísticas

## Funcionalidades Principales

### Para Clientes:
- ✅ Ver eventos disponibles
- ✅ Filtrar eventos por tipo, ubicación y fecha
- ✅ Comprar tickets de eventos
- ✅ Ver historial de tickets comprados
- ✅ Consultar información detallada de eventos

### Para Administradores:
- ✅ Crear nuevos eventos
- ✅ Gestionar capacidad y precios
- ✅ Actualizar información de eventos
- ✅ Eliminar eventos
- ✅ Ver estadísticas de ventas

## Tecnologías Utilizadas

### Backend:
- **Java 17** con Spring Boot
- **Spring Data JPA** para persistencia
- **H2 Database** (desarrollo) / **MySQL** (producción)
- **ModelMapper** para mapeo de DTOs
- **Lombok** para reducción de código boilerplate

### Frontend:
- **Angular 17** con TypeScript
- **Angular Router** para navegación
- **HttpClient** para comunicación con API REST
- **Bootstrap** para estilos (opcional)

## Estructura del Proyecto

```
HGProducciones/
├── backend/
│   ├── src/main/java/co/edu/unicauca/distribuidos/core/
│   │   ├── capaControladores/          # Controladores REST
│   │   │   ├── EventoRestController.java
│   │   │   ├── TicketRestController.java
│   │   │   └── ClienteRestController.java
│   │   ├── capaAccesoADatos/
│   │   │   ├── models/                 # Entidades JPA
│   │   │   │   ├── EventoEntity.java
│   │   │   │   ├── TicketEntity.java
│   │   │   │   └── ClienteEntity.java
│   │   │   └── repositories/           # Repositorios JPA
│   │   │       ├── EventoRepository.java
│   │   │       ├── TicketRepository.java
│   │   │       └── UsuarioRepository.java
│   │   └── fachadaServices/
│   │       ├── DTO/                    # Data Transfer Objects
│   │       │   ├── EventoDTO.java
│   │       │   ├── TicketDTO.java
│   │       │   └── ClienteDTO.java
│   │       └── services/               # Servicios de negocio
│   │           ├── IEventoService.java
│   │           ├── EventoServiceImpl.java
│   │           ├── ITicketService.java
│   │           └── TicketServiceImpl.java
│   └── src/main/resources/
│       ├── application.properties
│       └── data.sql                    # Datos de prueba
└── frontend/
    └── src/app/
        ├── cortesBarberia/             # Reutilizado para eventos
        │   ├── modelos/
        │   │   ├── evento.ts          # Modelo de Evento
        │   │   └── ticket.ts          # Modelo de Ticket
        │   └── servicios/
        │       ├── evento.service.ts  # Servicio de Eventos
        │       └── ticket.service.ts  # Servicio de Tickets
        ├── clientes/                   # Gestión de clientes
        └── app.routes.ts              # Rutas actualizadas
```

## API REST Endpoints

### Eventos:
- `GET /api/eventos` - Listar todos los eventos
- `GET /api/eventos/{id}` - Obtener evento por ID
- `GET /api/eventos/tipo/{tipo}` - Filtrar por tipo de evento
- `GET /api/eventos/proximos` - Eventos próximos
- `GET /api/eventos/disponibles` - Eventos con tickets disponibles
- `POST /api/eventos` - Crear nuevo evento
- `PUT /api/eventos/{id}` - Actualizar evento
- `DELETE /api/eventos/{id}` - Eliminar evento

### Tickets:
- `GET /api/tickets` - Listar todos los tickets
- `GET /api/tickets/cliente/{clienteId}` - Tickets por cliente
- `GET /api/tickets/evento/{eventoId}` - Tickets por evento
- `POST /api/tickets/comprar/{eventoId}/{clienteId}` - Comprar ticket
- `GET /api/tickets/numero/{numeroTicket}` - Buscar por número

## Instalación y Ejecución

### Backend:
1. Navegar a la carpeta `backend`
2. Ejecutar: `./mvnw spring-boot:run`
3. La API estará disponible en `http://localhost:5000`

### Frontend:
1. Navegar a la carpeta `frontend`
2. Instalar dependencias: `npm install`
3. Ejecutar: `ng serve`
4. La aplicación estará disponible en `http://localhost:4200`

## Base de Datos

El sistema utiliza las siguientes tablas principales:

### eventos
- id, nombre, descripcion, precio_ticket, tipo_evento, imagen, ubicacion, capacidad_total, tickets_disponibles, fecha_evento, create_at

### tickets  
- id, evento_id, cliente_id, numero_ticket, precio_compra, estado, fecha_compra, create_at

### usuarios (clientes)
- id, nombre, apellido, email, create_at

## Migración desde Barbería

Este proyecto ha sido transformado desde un sistema de gestión de barbería a un sistema de venta de tickets. Los principales cambios incluyen:

- **ProductoEntity** → **EventoEntity**
- **ProductoDTO** → **EventoDTO**
- **ProductoService** → **EventoService**
- **ProductoRestController** → **EventoRestController**
- Nuevas entidades: **TicketEntity**, **TicketDTO**
- Nuevos servicios para gestión de tickets
- Actualización de rutas y navegación
- Nueva base de datos con eventos y tickets de ejemplo

## Próximas Mejoras

- 🔐 Sistema de autenticación y autorización
- 💳 Integración con pasarelas de pago
- 📱 Aplicación móvil
- 📧 Notificaciones por email
- 📊 Dashboard de estadísticas avanzado
- 🎟️ Generación de tickets en PDF
- 📍 Mapa de ubicaciones de eventos

## Contacto

**HGProducciones Team**  
Email: info@hgproducciones.com  
Website: www.hgproducciones.com