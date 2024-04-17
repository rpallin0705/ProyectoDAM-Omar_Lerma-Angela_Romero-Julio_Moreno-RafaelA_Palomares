package org.example.proyecto.model.usuarios;

public class UsuariosDTO {
    private String email;
    private String telefono;
    private String nombreApellidos;
    private String contrasenhagen;
    private String direccion;

    public UsuariosDTO(String email, String telefono, String nombreApellidos, String contrasenhagen, String direccion) {
        this.email = email;
        this.telefono = telefono;
        this.nombreApellidos = nombreApellidos;
        this.contrasenhagen = contrasenhagen;
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

    public String getContrasenhagen() {
        return contrasenhagen;
    }

    public void setContrasenhagen(String contrasenhagen) {
        this.contrasenhagen = contrasenhagen;
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
