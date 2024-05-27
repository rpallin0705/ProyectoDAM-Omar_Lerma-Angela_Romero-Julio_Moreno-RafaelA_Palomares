package org.example.proyecto.model.client;

import org.example.proyecto.model.account.AccountDTO;

import java.util.Objects;

public class ClientDTO extends AccountDTO {
    private String direccion;

    public ClientDTO(int id_cuenta, String email, String nombre_apellidos, String direccion) {
        super(id_cuenta, email, nombre_apellidos);
        this.direccion = direccion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientDTO clientDTO)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(direccion, clientDTO.direccion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), direccion);
    }

    public ClientDTO(String email, String nombre_apellidos, String direccion) {
        super(email, nombre_apellidos);
        this.direccion = direccion;
    }

    /**
     * Constructor Copia
     * @param clientToCopy Objeto ClienteDTO que se quiere copiar
     */
    public ClientDTO(ClientDTO clientToCopy){
        super(clientToCopy.getId_cuenta(), clientToCopy.getEmail(), clientToCopy.getNombre_apellidos());
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