package co.edu.unicauca.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.unicauca.security.entity.UsuarioEntity;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    
    Optional<UsuarioEntity> findByEmail(String email);
    
    boolean existsByEmail(String email);
}