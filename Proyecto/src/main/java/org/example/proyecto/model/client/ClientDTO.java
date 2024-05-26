package org.example.proyecto.model.client;

import org.example.proyecto.model.account.AccountDTO;

public class ClientDTO extends AccountDTO {
    private String direccion;

    public ClientDTO(int id_cuenta, String email, String nombre_apellidos, String direccion) {
        super(id_cuenta, email, nombre_apellidos);
        this.direccion = direccion;
    }

    /*GETTERS*/
    public String getDireccion() {
        return direccion;
    }

    /*SETTERS*/
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return String.format("%s,%s",super.toString(),direccion);
    }
}