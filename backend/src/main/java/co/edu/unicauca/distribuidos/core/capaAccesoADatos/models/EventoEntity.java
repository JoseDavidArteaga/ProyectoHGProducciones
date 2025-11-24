package co.edu.unicauca.distribuidos.core.capaAccesoADatos.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "eventos")
public class EventoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String nombre;
    private String descripcion;
    private Double precioTicket;
    private String tipoEvento; // concierto, teatro, deportivo, etc.
    private String imagen;
    private String ubicacion;
    private Integer capacidadTotal;
    private Integer ticketsDisponibles;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEvento;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
}