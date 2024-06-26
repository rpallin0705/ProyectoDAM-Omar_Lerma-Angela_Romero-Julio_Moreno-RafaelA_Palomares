package org.example.proyecto.model.client;

import org.example.proyecto.model.account.AccountDTO;

import java.util.Objects;

/**
 * Data Transfer Object (DTO) for client information.
 * This class extends the AccountDTO class and includes additional attributes specific to clients.
 *
 * @version 1.0
 * @since 2024-05-28
 *
 * @Author Omar
 */
public class ClientDTO extends AccountDTO {
    private String direccion;

    public ClientDTO(int id_cuenta, String email, String nombre_apellidos, String direccion) {
        super(id_cuenta, email, nombre_apellidos);
        this.direccion = direccion;
    }

    /**
     * Constructs a {@code ClientDTO} object with the specified email, name, surname, and address.
     *
     * @param email the email of the client.
     * @param nombre_apellidos the name and surname of the client.
     * @param direccion the address of the client.
     */
    public ClientDTO(String email, String nombre_apellidos, String direccion) {
        super(email, nombre_apellidos);
        this.direccion = direccion;
    }

    /**
     * Copy Constructor.
     *
     * @param clientToCopy the ClientDTO object to be copied.
     */
    public ClientDTO(ClientDTO clientToCopy){
        super(clientToCopy.getId_cuenta(), clientToCopy.getEmail(), clientToCopy.getNombre_apellidos());
        this.direccion = clientToCopy.getDireccion();
    }

    /* GETTERS */

    /**
     * Gets the address of the client.
     *
     * @return the address.
     */
    public String getDireccion() {
        return direccion;
    }

    /* SETTERS */

    /**
     * Sets the address of the client.
     *
     * @param direccion the new address.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Returns a string representation of the client.
     * The string contains the email, name, surname, and address, separated by commas.
     *
     * @return the string representation of the client.
     */
    @Override
    public String toString() {
        return String.format("%s,%s", super.toString(), direccion);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * This method checks if the given object is an instance of ClientDTO,
     * and if all the attributes (including inherited attributes) are equal.
     *
     * @param o the reference object with which to compare.
     * @return true if this object is the same as the o argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientDTO clientDTO)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(direccion, clientDTO.direccion);
    }

    /**
     * Returns a hash code value for the object.
     * This method returns the hash code value based on the hash code of
     * the superclass and the specific attribute 'direccion'.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), direccion);
    }

}
