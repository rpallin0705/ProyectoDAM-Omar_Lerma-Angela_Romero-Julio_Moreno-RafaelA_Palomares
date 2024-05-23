package org.example.proyecto.model.client;

import org.example.proyecto.model.account.AccountDTO;

import java.util.Objects;

public class ClientDTO extends AccountDTO {
    private String direccion;

    public ClientDTO(int id_cuenta, String email, String contrasena, String nombre_apellidos, String direccion) {
        super(id_cuenta, email, contrasena, nombre_apellidos);
        this.direccion = direccion;
    }

    /**
     * Constructor Copia
     * @param clientToCopy Objeto ClienteDTO que se quiere copiar
     */
    public ClientDTO(ClientDTO clientToCopy){
        super(clientToCopy.getId_cuenta(), clientToCopy.getEmail(), clientToCopy.getContrasena(), clientToCopy.getNombre_apellidos());
        this.direccion = clientToCopy.getDireccion();
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