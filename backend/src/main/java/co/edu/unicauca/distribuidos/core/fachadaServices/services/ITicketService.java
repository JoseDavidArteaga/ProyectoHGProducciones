package co.edu.unicauca.distribuidos.core.fachadaServices.services;

import java.util.List;

import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.TicketDTO;

public interface ITicketService {
    
    public List<TicketDTO> findAll();
    public TicketDTO findById(Integer id);
    public TicketDTO save(TicketDTO ticket);
    public TicketDTO update(Integer id, TicketDTO ticket);
    public boolean delete(Integer id);
    public List<TicketDTO> findByEventoId(Integer eventoId);
    public List<TicketDTO> findByClienteId(Integer clienteId);
    public List<TicketDTO> findByEstado(String estado);
    public TicketDTO findByNumeroTicket(String numeroTicket);
    public Integer countTicketsVendidosByEventoId(Integer eventoId);
    public String generarNumeroTicket(Integer eventoId);
    public TicketDTO comprarTicket(Integer eventoId, Integer clienteId);
}