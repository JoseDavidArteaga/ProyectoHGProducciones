package co.edu.unicauca.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import co.edu.unicauca.security.dto.RegistroDto;
import co.edu.unicauca.security.entity.UsuarioEntity;
import co.edu.unicauca.security.service.UsuarioService;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
@PreAuthorize("hasRole('ADMIN')")
public class AdminUsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioEntity>> getAllUsuarios() {
        List<UsuarioEntity> usuarios = usuarioService.findAll();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable Integer id) {
        try {
            UsuarioEntity usuario = usuarioService.findById(id)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Usuario no encontrado");
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @PostMapping
    public ResponseEntity<?> createUsuario(@Valid @RequestBody RegistroDto registroDto) {
        try {
            UsuarioEntity nuevoUsuario = usuarioService.registrarUsuario(registroDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al crear usuario");
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable Integer id, @Valid @RequestBody RegistroDto registroDto) {
        try {
            UsuarioEntity usuarioActualizado = usuarioService.updateUsuario(id, registroDto);
            return ResponseEntity.ok(usuarioActualizado);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al actualizar usuario");
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PatchMapping("/{id}/toggle-activo")
    public ResponseEntity<?> toggleActivoUsuario(@PathVariable Integer id) {
        try {
            UsuarioEntity usuario = usuarioService.toggleActivoUsuario(id);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Estado del usuario actualizado");
            response.put("usuario", usuario);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al cambiar estado del usuario");
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Integer id) {
        try {
            usuarioService.deleteById(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Usuario eliminado exitosamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al eliminar usuario");
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
}