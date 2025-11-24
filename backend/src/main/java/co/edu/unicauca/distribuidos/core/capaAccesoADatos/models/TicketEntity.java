package co.edu.unicauca.distribuidos.core.capaAccesoADatos.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tickets")
public class TicketEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "evento_id")
    private EventoEntity evento;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;
    
    private String numeroTicket; // Código único del ticket
    private Double precioCompra;
    private String estado; // vendido, reservado, cancelado
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCompra;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
}