package org.example.proyecto.model.account;

import java.util.Objects;

public class AccountDTO {
    private int id_cuenta;
    private String email;
    private String nombre_apellidos;

    public AccountDTO(int id_cuenta, String email, String nombre_apellidos) {
        this.id_cuenta = id_cuenta;
        this.email = email;
        this.nombre_apellidos = nombre_apellidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountDTO that)) return false;
        return Objects.equals(email, that.email) && Objects.equals(nombre_apellidos, that.nombre_apellidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, nombre_apellidos);
    }

    /**
     * Constructor for inserting objects in the database
     * @param email new account emails
     * @param nombre_apellidos new account name and surname
     */
    public AccountDTO(String email, String nombre_apellidos) {
        this.email = email;
        this.nombre_apellidos = nombre_apellidos;
    }

    /*GETTERS*/
    public int getId_cuenta() {
        return id_cuenta;
    }

    public String getEmail() {
        return email;
    }


    public String getNombre_apellidos() {
        return nombre_apellidos;
    }


    /*SETTERS*/
    public void setEmail(String email) {
        this.email = email;
    }

    public void setNombre_apellidos(String nombre_apellidos) {
        this.nombre_apellidos = nombre_apellidos;
    }
    /*TO STRING*/
    @Override
    public String toString() {
        return String.format("%d,%s,%s", id_cuenta, email, nombre_apellidos);
    }
}
