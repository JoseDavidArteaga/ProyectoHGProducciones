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

import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.TicketDTO;
import co.edu.unicauca.distribuidos.core.fachadaServices.services.ITicketService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200",  
 methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}) 
public class TicketRestController {

	@Autowired
	private ITicketService ticketService;

	@GetMapping("/tickets") 
	public List<TicketDTO> listarTickets() {
		return ticketService.findAll();
	}

	@GetMapping("/tickets/{id}")
	public TicketDTO consultarTicket(@PathVariable Integer id) {
		TicketDTO objTicket = null;
		objTicket = ticketService.findById(id);
		return objTicket; 
	}

	@GetMapping("/tickets/evento/{eventoId}")
	public List<TicketDTO> consultarTicketsPorEvento(@PathVariable Integer eventoId) {
		return ticketService.findByEventoId(eventoId);
	}

	@GetMapping("/tickets/cliente/{clienteId}")
	public List<TicketDTO> consultarTicketsPorCliente(@PathVariable Integer clienteId) {
		return ticketService.findByClienteId(clienteId);
	}

	@GetMapping("/tickets/estado/{estado}")
	public List<TicketDTO> consultarTicketsPorEstado(@PathVariable String estado) {
		return ticketService.findByEstado(estado);
	}

	@GetMapping("/tickets/numero/{numeroTicket}")
	public TicketDTO consultarTicketPorNumero(@PathVariable String numeroTicket) {
		return ticketService.findByNumeroTicket(numeroTicket);
	}

	@GetMapping("/tickets/evento/{eventoId}/vendidos")
	public Integer contarTicketsVendidos(@PathVariable Integer eventoId) {
		return ticketService.countTicketsVendidosByEventoId(eventoId);
	}

	@PostMapping("/tickets")
	public TicketDTO crearTicket(@RequestBody TicketDTO ticket) {
		TicketDTO objTicket = null;
		objTicket = ticketService.save(ticket);
		return objTicket;
	}

	@PostMapping("/tickets/comprar/{eventoId}/{clienteId}")
	public TicketDTO comprarTicket(@PathVariable Integer eventoId, @PathVariable Integer clienteId) {
		return ticketService.comprarTicket(eventoId, clienteId);
	}

	@PutMapping("/tickets/{id}")
	public TicketDTO actualizarTicket(@RequestBody TicketDTO ticket, @PathVariable Integer id) {
		TicketDTO objTicket = null;
		TicketDTO ticketActual = ticketService.findById(id);
		if (ticketActual != null) {
			objTicket = ticketService.update(id, ticket);
		}
		return objTicket;
	}

	@DeleteMapping("/tickets/{id}")
	public Boolean eliminarTicket(@PathVariable Integer id) {
		Boolean bandera = false;
		TicketDTO ticketActual = ticketService.findById(id);
		if (ticketActual != null) {
			bandera = ticketService.delete(id);
		}
		return bandera;
	}
}