package co.edu.unicauca.distribuidos.core.fachadaServices.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TicketDTO {
	private Integer id;
	private Integer eventoId;
	private Integer clienteId;
	private String numeroTicket; // Código único del ticket
	private Double precioCompra;
	private String estado; // vendido, reservado, cancelado
	private Date fechaCompra;
	private Date createAt;
	
	// Información adicional del evento y cliente para mostrar en frontend
	private String nombreEvento;
	private String nombreCliente;
	private String emailCliente;
	private Date fechaEvento;
	private String ubicacionEvento;

	public TicketDTO() {

	}
}