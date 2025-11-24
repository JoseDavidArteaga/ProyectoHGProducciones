package co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.EventoEntity;

// Repositorio JPA para la entidad Evento
// Proporciona métodos para operaciones CRUD y consultas personalizadas
public interface EventoRepository extends JpaRepository<EventoEntity, Integer> {
    
    // Consulta personalizada para encontrar eventos por tipo
    @Query("SELECT e FROM EventoEntity e WHERE e.tipoEvento = :tipoEvento")
    List<EventoEntity> findByTipoEvento(@Param("tipoEvento") String tipoEvento);
    
    // Consulta personalizada para obtener tipos de eventos distintos
    @Query("SELECT DISTINCT e.tipoEvento FROM EventoEntity e")
    List<String> findDistinctTiposEvento();
    
    // Consulta para encontrar eventos próximos (fecha mayor a hoy)
    @Query("SELECT e FROM EventoEntity e WHERE e.fechaEvento > :fechaActual ORDER BY e.fechaEvento ASC")
    List<EventoEntity> findEventosProximos(@Param("fechaActual") Date fechaActual);
    
    // Consulta para encontrar eventos con tickets disponibles
    @Query("SELECT e FROM EventoEntity e WHERE e.ticketsDisponibles > 0 ORDER BY e.fechaEvento ASC")
    List<EventoEntity> findEventosConTicketsDisponibles();
    
    // Consulta para encontrar eventos por ubicación
    @Query("SELECT e FROM EventoEntity e WHERE e.ubicacion LIKE %:ubicacion%")
    List<EventoEntity> findByUbicacion(@Param("ubicacion") String ubicacion);
}