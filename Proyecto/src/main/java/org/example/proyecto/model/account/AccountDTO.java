package org.example.proyecto.model.account;

public class AccountDTO {
    private int id_cuenta;
    private String email;
    private String nombre_apellidos;

    public AccountDTO(int id_cuenta, String email, String nombre_apellidos) {
        this.id_cuenta = id_cuenta;
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
