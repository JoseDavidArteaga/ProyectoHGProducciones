package co.edu.unicauca.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.edu.unicauca.security.dto.RegistroDto;
import co.edu.unicauca.security.entity.UsuarioEntity;
import co.edu.unicauca.security.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioEntity registrarUsuario(RegistroDto registroDto) {
        if (usuarioRepository.existsByEmail(registroDto.getEmail())) {
            throw new RuntimeException("Ya existe un usuario con este email");
        }

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNombre(registroDto.getNombre());
        usuario.setApellido(registroDto.getApellido());
        usuario.setEmail(registroDto.getEmail());
        usuario.setPassword(passwordEncoder.encode(registroDto.getPassword()));
        usuario.setRol(registroDto.getRol());
        usuario.setActivo(true);

        return usuarioRepository.save(usuario);
    }

    public Optional<UsuarioEntity> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public List<UsuarioEntity> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<UsuarioEntity> findById(Integer id) {
        return usuarioRepository.findById(id);
    }

    public void deleteById(Integer id) {
        usuarioRepository.deleteById(id);
    }

    public UsuarioEntity updateUsuario(Integer id, RegistroDto registroDto) {
        UsuarioEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Verificar si el email ya existe en otro usuario
        Optional<UsuarioEntity> usuarioExistente = usuarioRepository.findByEmail(registroDto.getEmail());
        if (usuarioExistente.isPresent() && !usuarioExistente.get().getId().equals(id)) {
            throw new RuntimeException("Ya existe un usuario con este email");
        }

        usuario.setNombre(registroDto.getNombre());
        usuario.setApellido(registroDto.getApellido());
        usuario.setEmail(registroDto.getEmail());
        if (registroDto.getPassword() != null && !registroDto.getPassword().isEmpty()) {
            usuario.setPassword(passwordEncoder.encode(registroDto.getPassword()));
        }
        usuario.setRol(registroDto.getRol());

        return usuarioRepository.save(usuario);
    }

    public UsuarioEntity toggleActivoUsuario(Integer id) {
        UsuarioEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setActivo(!usuario.getActivo());
        return usuarioRepository.save(usuario);
    }
}