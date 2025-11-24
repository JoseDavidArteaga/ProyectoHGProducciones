package co.edu.unicauca.distribuidos.core.fachadaServices.services;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.EventoEntity;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories.EventoRepository;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.EventoDTO;

@Service
public class EventoServiceImpl implements IEventoService {

    @Autowired
    private EventoRepository eventoRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<EventoDTO> findAll() {
        List<EventoEntity> eventosEntity = this.eventoRepository.findAll();
        List<EventoDTO> eventosDTO = this.modelMapper.map(eventosEntity, new TypeToken<List<EventoDTO>>() {}.getType());
        return eventosDTO;
    }

    @Override
    public EventoDTO findById(Integer id) {
        EventoEntity objEventoEntity = this.eventoRepository.findById(id).orElse(null);
        EventoDTO objEventoDTO = this.modelMapper.map(objEventoEntity, EventoDTO.class);
        return objEventoDTO;
    }

    @Override
    public EventoDTO save(EventoDTO evento) {
        evento.setCreateAt(new Date());
        // Si no se especificaron tickets disponibles, usar la capacidad total
        if (evento.getTicketsDisponibles() == null && evento.getCapacidadTotal() != null) {
            evento.setTicketsDisponibles(evento.getCapacidadTotal());
        }
        EventoEntity objEventoEntity = this.modelMapper.map(evento, EventoEntity.class);
        EventoEntity objEventoEntitySaved = this.eventoRepository.save(objEventoEntity);
        EventoDTO objEventoDTO = this.modelMapper.map(objEventoEntitySaved, EventoDTO.class);
        return objEventoDTO;
    }

    @Override
    public EventoDTO update(Integer id, EventoDTO evento) {
        EventoEntity objEventoEntity = this.modelMapper.map(evento, EventoEntity.class);
        objEventoEntity.setId(id);
        EventoEntity objEventoEntitySaved = this.eventoRepository.save(objEventoEntity);
        EventoDTO objEventoDTO = this.modelMapper.map(objEventoEntitySaved, EventoDTO.class);
        return objEventoDTO;
    }

    @Override
    public boolean delete(Integer id) {
        try {
            this.eventoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<EventoDTO> findByTipoEvento(String tipoEvento) {
        List<EventoEntity> eventosEntity = this.eventoRepository.findByTipoEvento(tipoEvento);
        List<EventoDTO> eventosDTO = this.modelMapper.map(eventosEntity, new TypeToken<List<EventoDTO>>() {}.getType());
        return eventosDTO;
    }

    @Override
    public List<String> findDistinctTiposEvento() {
        return this.eventoRepository.findDistinctTiposEvento();
    }
    
    @Override
    public List<EventoDTO> findEventosProximos() {
        List<EventoEntity> eventosEntity = this.eventoRepository.findEventosProximos(new Date());
        List<EventoDTO> eventosDTO = this.modelMapper.map(eventosEntity, new TypeToken<List<EventoDTO>>() {}.getType());
        return eventosDTO;
    }
    
    @Override
    public List<EventoDTO> findEventosConTicketsDisponibles() {
        List<EventoEntity> eventosEntity = this.eventoRepository.findEventosConTicketsDisponibles();
        List<EventoDTO> eventosDTO = this.modelMapper.map(eventosEntity, new TypeToken<List<EventoDTO>>() {}.getType());
        return eventosDTO;
    }
    
    @Override
    public List<EventoDTO> findByUbicacion(String ubicacion) {
        List<EventoEntity> eventosEntity = this.eventoRepository.findByUbicacion(ubicacion);
        List<EventoDTO> eventosDTO = this.modelMapper.map(eventosEntity, new TypeToken<List<EventoDTO>>() {}.getType());
        return eventosDTO;
    }
    
    @Override
    public boolean actualizarTicketsDisponibles(Integer eventoId, Integer ticketsVendidos) {
        try {
            EventoEntity evento = this.eventoRepository.findById(eventoId).orElse(null);
            if (evento != null && evento.getTicketsDisponibles() >= ticketsVendidos) {
                evento.setTicketsDisponibles(evento.getTicketsDisponibles() - ticketsVendidos);
                this.eventoRepository.save(evento);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}