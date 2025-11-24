package co.edu.unicauca.distribuidos.core.fachadaServices.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EventoDTO {
	private Integer id;
	private String nombre;
	private String descripcion;
	private Double precioTicket;
	private String tipoEvento; // concierto, teatro, deportivo, etc.
	private String imagen;
	private String ubicacion;
	private Integer capacidadTotal;
	private Integer ticketsDisponibles;
	private Date fechaEvento;
	private Date createAt;

	public EventoDTO() {

	}
}