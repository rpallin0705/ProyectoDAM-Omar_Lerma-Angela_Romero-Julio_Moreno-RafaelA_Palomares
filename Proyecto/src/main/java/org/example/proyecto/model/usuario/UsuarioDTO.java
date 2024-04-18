package org.example.proyecto.model.usuario;

public class UsuarioDTO {
    private String email;
    private String telefono;
    private String nombreApellidos;
    private String contrasenha;
    private String direccion;

    public UsuarioDTO(String email, String telefono, String nombreApellidos, String contrasenha, String direccion) {
        this.email = email;
        this.telefono = telefono;
        this.nombreApellidos = nombreApellidos;
        this.contrasenha = contrasenha;
        this.direccion = direccion;
    }

    public UsuarioDTO(String email, String telefono, String nombreApellidos, String direccion) {
        this.email = email;
        this.telefono = telefono;
        this.nombreApellidos = nombreApellidos;
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreApellidos() {
        return nombreApellidos;
    }

    public void setNombreApellidos(String nombreApellidos) {
        this.nombreApellidos = nombreApellidos;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s", email, telefono, nombreApellidos, direccion);
    }
}
