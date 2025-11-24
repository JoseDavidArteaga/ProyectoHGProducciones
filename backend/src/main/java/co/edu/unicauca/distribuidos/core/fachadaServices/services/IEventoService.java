package co.edu.unicauca.distribuidos.core.fachadaServices.services;

import java.util.Date;
import java.util.List;

import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.EventoDTO;

public interface IEventoService {
    
    public List<EventoDTO> findAll();
    public EventoDTO findById(Integer id);
    public EventoDTO save(EventoDTO evento);
    public EventoDTO update(Integer id, EventoDTO evento);
    public boolean delete(Integer id);
    public List<EventoDTO> findByTipoEvento(String tipoEvento);
    public List<String> findDistinctTiposEvento();
    public List<EventoDTO> findEventosProximos();
    public List<EventoDTO> findEventosConTicketsDisponibles();
    public List<EventoDTO> findByUbicacion(String ubicacion);
    public boolean actualizarTicketsDisponibles(Integer eventoId, Integer ticketsVendidos);
}