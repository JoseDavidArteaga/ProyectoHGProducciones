package co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.ClienteEntity;

// Repositorio JPA para la entidad Cliente
// Proporciona métodos para operaciones CRUD básicas
public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {
    
    // Método para encontrar cliente por email (si es necesario)
    ClienteEntity findByEmail(String email);
}