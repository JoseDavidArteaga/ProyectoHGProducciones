package co.edu.unicauca.security.dto;

import co.edu.unicauca.security.entity.RolEnum;

public class JwtResponseDto {
    
    private String token;
    private String tipo = "Bearer";
    private String email;
    private String nombre;
    private String apellido;
    private RolEnum rol;

    // Constructor vacío
    public JwtResponseDto() {
    }

    // Constructor completo
    public JwtResponseDto(String token, String email, String nombre, String apellido, RolEnum rol) {
        this.token = token;
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
    }

    // Getters y Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public RolEnum getRol() {
        return rol;
    }

    public void setRol(RolEnum rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "JwtResponseDto{" +
                "token='" + token + '\'' +
                ", tipo='" + tipo + '\'' +
                ", email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", rol=" + rol +
                '}';
    }
}