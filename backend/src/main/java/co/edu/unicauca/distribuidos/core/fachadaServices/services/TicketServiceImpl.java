package co.edu.unicauca.distribuidos.core.fachadaServices.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.TicketEntity;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.EventoEntity;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.ClienteEntity;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories.TicketRepository;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories.EventoRepository;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories.ClienteRepository;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.TicketDTO;

@Service
public class TicketServiceImpl implements ITicketService {

    @Autowired
    private TicketRepository ticketRepository;
    
    @Autowired
    private EventoRepository eventoRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private IEventoService eventoService;
    
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TicketDTO> findAll() {
        List<TicketEntity> ticketsEntity = this.ticketRepository.findAll();
        return mapearTicketsConInfo(ticketsEntity);
    }

    @Override
    public TicketDTO findById(Integer id) {
        TicketEntity objTicketEntity = this.ticketRepository.findById(id).orElse(null);
        return mapearTicketConInfo(objTicketEntity);
    }

    @Override
    public TicketDTO save(TicketDTO ticket) {
        ticket.setCreateAt(new Date());
        ticket.setFechaCompra(new Date());
        
        // Generar número de ticket único si no se proporcionó
        if (ticket.getNumeroTicket() == null || ticket.getNumeroTicket().isEmpty()) {
            ticket.setNumeroTicket(generarNumeroTicket(ticket.getEventoId()));
        }
        
        TicketEntity objTicketEntity = this.modelMapper.map(ticket, TicketEntity.class);
        
        // Establecer las relaciones
        if (ticket.getEventoId() != null) {
            EventoEntity evento = this.eventoRepository.findById(ticket.getEventoId()).orElse(null);
            objTicketEntity.setEvento(evento);
        }
        
        if (ticket.getClienteId() != null) {
            ClienteEntity cliente = this.clienteRepository.findById(ticket.getClienteId()).orElse(null);
            objTicketEntity.setCliente(cliente);
        }
        
        TicketEntity objTicketEntitySaved = this.ticketRepository.save(objTicketEntity);
        return mapearTicketConInfo(objTicketEntitySaved);
    }

    @Override
    public TicketDTO update(Integer id, TicketDTO ticket) {
        TicketEntity objTicketEntity = this.modelMapper.map(ticket, TicketEntity.class);
        objTicketEntity.setId(id);
        
        // Establecer las relaciones
        if (ticket.getEventoId() != null) {
            EventoEntity evento = this.eventoRepository.findById(ticket.getEventoId()).orElse(null);
            objTicketEntity.setEvento(evento);
        }
        
        if (ticket.getClienteId() != null) {
            ClienteEntity cliente = this.clienteRepository.findById(ticket.getClienteId()).orElse(null);
            objTicketEntity.setCliente(cliente);
        }
        
        TicketEntity objTicketEntitySaved = this.ticketRepository.save(objTicketEntity);
        return mapearTicketConInfo(objTicketEntitySaved);
    }

    @Override
    public boolean delete(Integer id) {
        try {
            this.ticketRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<TicketDTO> findByEventoId(Integer eventoId) {
        List<TicketEntity> ticketsEntity = this.ticketRepository.findByEventoId(eventoId);
        return mapearTicketsConInfo(ticketsEntity);
    }

    @Override
    public List<TicketDTO> findByClienteId(Integer clienteId) {
        List<TicketEntity> ticketsEntity = this.ticketRepository.findByClienteId(clienteId);
        return mapearTicketsConInfo(ticketsEntity);
    }

    @Override
    public List<TicketDTO> findByEstado(String estado) {
        List<TicketEntity> ticketsEntity = this.ticketRepository.findByEstado(estado);
        return mapearTicketsConInfo(ticketsEntity);
    }

    @Override
    public TicketDTO findByNumeroTicket(String numeroTicket) {
        TicketEntity ticketEntity = this.ticketRepository.findByNumeroTicket(numeroTicket);
        return mapearTicketConInfo(ticketEntity);
    }

    @Override
    public Integer countTicketsVendidosByEventoId(Integer eventoId) {
        return this.ticketRepository.countTicketsVendidosByEventoId(eventoId);
    }
    
    @Override
    public String generarNumeroTicket(Integer eventoId) {
        String prefix = "HGP-" + eventoId + "-";
        String uniqueId = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        return prefix + uniqueId;
    }
    
    @Override
    public TicketDTO comprarTicket(Integer eventoId, Integer clienteId) {
        // Verificar que el evento tenga tickets disponibles
        EventoEntity evento = this.eventoRepository.findById(eventoId).orElse(null);
        if (evento == null || evento.getTicketsDisponibles() <= 0) {
            return null; // No hay tickets disponibles
        }
        
        // Crear el ticket
        TicketDTO nuevoTicket = new TicketDTO();
        nuevoTicket.setEventoId(eventoId);
        nuevoTicket.setClienteId(clienteId);
        nuevoTicket.setPrecioCompra(evento.getPrecioTicket());
        nuevoTicket.setEstado("vendido");
        
        // Guardar el ticket
        TicketDTO ticketGuardado = this.save(nuevoTicket);
        
        // Actualizar tickets disponibles del evento
        if (ticketGuardado != null) {
            this.eventoService.actualizarTicketsDisponibles(eventoId, 1);
        }
        
        return ticketGuardado;
    }
    
    // Métodos auxiliares para mapear con información adicional
    private TicketDTO mapearTicketConInfo(TicketEntity ticketEntity) {
        if (ticketEntity == null) return null;
        
        TicketDTO ticketDTO = this.modelMapper.map(ticketEntity, TicketDTO.class);
        
        // Agregar información del evento y cliente
        if (ticketEntity.getEvento() != null) {
            ticketDTO.setEventoId(ticketEntity.getEvento().getId());
            ticketDTO.setNombreEvento(ticketEntity.getEvento().getNombre());
            ticketDTO.setFechaEvento(ticketEntity.getEvento().getFechaEvento());
            ticketDTO.setUbicacionEvento(ticketEntity.getEvento().getUbicacion());
        }
        
        if (ticketEntity.getCliente() != null) {
            ticketDTO.setClienteId(ticketEntity.getCliente().getId());
            ticketDTO.setNombreCliente(ticketEntity.getCliente().getNombre() + " " + ticketEntity.getCliente().getApellido());
            ticketDTO.setEmailCliente(ticketEntity.getCliente().getEmail());
        }
        
        return ticketDTO;
    }
    
    private List<TicketDTO> mapearTicketsConInfo(List<TicketEntity> ticketsEntity) {
        return ticketsEntity.stream()
                .map(this::mapearTicketConInfo)
                .toList();
    }
}