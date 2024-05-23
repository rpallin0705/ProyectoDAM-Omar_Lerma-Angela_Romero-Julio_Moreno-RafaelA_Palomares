package org.example.proyecto.model.account;

import java.util.Objects;

public class AccountDTO {
    private int id_cuenta;
    private String email;
    private String contrasena;
    private String nombre_apellidos;

    public AccountDTO(int id_cuenta, String email, String contrasena, String nombre_apellidos) {
        this.id_cuenta = id_cuenta;
        this.email = email;
        this.contrasena = contrasena;
        this.nombre_apellidos = nombre_apellidos;
    }

    /*GETTERS*/
    public int getId_cuenta() {
        return id_cuenta;
    }

    public String getEmail() {
        return email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getNombre_apellidos() {
        return nombre_apellidos;
    }

    /*SETTERS*/
    public void setEmail(String email) {
        this.email = email;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setNombre_apellidos(String nombre_apellidos) {
        this.nombre_apellidos = nombre_apellidos;
    }
    /*TO STRING*/
    @Override
    public String toString() {
        return String.format("%d,%s,%s,%s", id_cuenta, email, contrasena, nombre_apellidos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDTO that = (AccountDTO) o;
        return id_cuenta == that.id_cuenta && Objects.equals(email, that.email) && Objects.equals(contrasena, that.contrasena) && Objects.equals(nombre_apellidos, that.nombre_apellidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_cuenta, email, contrasena, nombre_apellidos);
    }
}
