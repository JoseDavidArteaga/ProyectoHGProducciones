package co.edu.unicauca.distribuidos.core.capaControladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.EventoDTO;
import co.edu.unicauca.distribuidos.core.fachadaServices.services.IEventoService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200",  
 methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}) 
public class EventoRestController {

	@Autowired
	private IEventoService eventoService;

	@GetMapping("/eventos") 
	public List<EventoDTO> listarEventos() {
		return eventoService.findAll();
	}

	@GetMapping("/eventos/{id}")
	public EventoDTO consultarEvento(@PathVariable Integer id) {
		EventoDTO objEvento = null;
		objEvento = eventoService.findById(id);
		return objEvento; 
	}

	@GetMapping("/eventos/tipo/{tipoEvento}")
	public List<EventoDTO> consultarEventosPorTipo(@PathVariable String tipoEvento) {
		return eventoService.findByTipoEvento(tipoEvento);
	}

	@GetMapping("/eventos/tipos")
	public List<String> listarTiposEvento() {
		return eventoService.findDistinctTiposEvento();
	}

	@GetMapping("/eventos/proximos")
	public List<EventoDTO> listarEventosProximos() {
		return eventoService.findEventosProximos();
	}

	@GetMapping("/eventos/disponibles")
	public List<EventoDTO> listarEventosDisponibles() {
		return eventoService.findEventosConTicketsDisponibles();
	}

	@GetMapping("/eventos/ubicacion/{ubicacion}")
	public List<EventoDTO> consultarEventosPorUbicacion(@PathVariable String ubicacion) {
		return eventoService.findByUbicacion(ubicacion);
	}

	@PostMapping("/eventos")
	public EventoDTO crearEvento(@RequestBody EventoDTO evento) {
		EventoDTO objEvento = null;
		objEvento = eventoService.save(evento);
		return objEvento;
	}

	@PutMapping("/eventos/{id}")
	public EventoDTO actualizarEvento(@RequestBody EventoDTO evento, @PathVariable Integer id) {
		EventoDTO objEvento = null;
		EventoDTO eventoActual = eventoService.findById(id);
		if (eventoActual != null) {
			objEvento = eventoService.update(id, evento);
		}
		return objEvento;
	}

	@DeleteMapping("/eventos/{id}")
	public Boolean eliminarEvento(@PathVariable Integer id) {
		Boolean bandera = false;
		EventoDTO eventoActual = eventoService.findById(id);
		if (eventoActual != null) {
			bandera = eventoService.delete(id);
		}
		return bandera;
	}
}