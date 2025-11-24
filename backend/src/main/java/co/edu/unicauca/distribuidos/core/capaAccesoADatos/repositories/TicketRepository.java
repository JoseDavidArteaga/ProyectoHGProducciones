package co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.TicketEntity;

// Repositorio JPA para la entidad Ticket
// Proporciona métodos para operaciones CRUD y consultas personalizadas
public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {
    
    // Consulta para encontrar tickets por evento
    @Query("SELECT t FROM TicketEntity t WHERE t.evento.id = :eventoId")
    List<TicketEntity> findByEventoId(@Param("eventoId") Integer eventoId);
    
    // Consulta para encontrar tickets por cliente
    @Query("SELECT t FROM TicketEntity t WHERE t.cliente.id = :clienteId ORDER BY t.fechaCompra DESC")
    List<TicketEntity> findByClienteId(@Param("clienteId") Integer clienteId);
    
    // Consulta para encontrar tickets por estado
    @Query("SELECT t FROM TicketEntity t WHERE t.estado = :estado")
    List<TicketEntity> findByEstado(@Param("estado") String estado);
    
    // Consulta para encontrar un ticket por número único
    @Query("SELECT t FROM TicketEntity t WHERE t.numeroTicket = :numeroTicket")
    TicketEntity findByNumeroTicket(@Param("numeroTicket") String numeroTicket);
    
    // Consulta para contar tickets vendidos por evento
    @Query("SELECT COUNT(t) FROM TicketEntity t WHERE t.evento.id = :eventoId AND t.estado = 'vendido'")
    Integer countTicketsVendidosByEventoId(@Param("eventoId") Integer eventoId);
}