package co.edu.unicauca.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import co.edu.unicauca.security.dto.JwtResponseDto;
import co.edu.unicauca.security.dto.LoginDto;
import co.edu.unicauca.security.dto.RegistroDto;
import co.edu.unicauca.security.entity.UsuarioEntity;
import co.edu.unicauca.security.service.JwtService;
import co.edu.unicauca.security.service.UsuarioService;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            UsuarioEntity usuario = usuarioService.findByEmail(userDetails.getUsername())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            String jwt = jwtService.generateToken(userDetails);

            JwtResponseDto jwtResponse = new JwtResponseDto(
                jwt,
                usuario.getEmail(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getRol()
            );

            return ResponseEntity.ok(jwtResponse);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Credenciales inválidas");
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registro(@Valid @RequestBody RegistroDto registroDto) {
        try {
            UsuarioEntity nuevoUsuario = usuarioService.registrarUsuario(registroDto);
            
            Map<String, String> response = new HashMap<>();
            response.put("message", "Usuario registrado exitosamente");
            response.put("email", nuevoUsuario.getEmail());
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al registrar usuario");
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @GetMapping("/perfil")
    public ResponseEntity<?> getPerfil(Authentication authentication) {
        try {
            String email = authentication.getName();
            UsuarioEntity usuario = usuarioService.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            Map<String, Object> perfil = new HashMap<>();
            perfil.put("id", usuario.getId());
            perfil.put("nombre", usuario.getNombre());
            perfil.put("apellido", usuario.getApellido());
            perfil.put("email", usuario.getEmail());
            perfil.put("rol", usuario.getRol());

            return ResponseEntity.ok(perfil);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al obtener perfil");
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
}