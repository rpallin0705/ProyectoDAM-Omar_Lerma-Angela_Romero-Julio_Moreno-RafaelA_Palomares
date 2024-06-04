package org.example.proyecto.model.user;

import org.example.proyecto.model.account.AccountDTO;

import java.util.Objects;

/**
 * Data Transfer Object (DTO) for user information.
 * This class extends the AccountDTO class and represents user-specific data, including administrator status and password.
 */
public class UserDTO extends AccountDTO {
    private boolean admin;
    private String contrasena;

    public UserDTO(int id_cuenta, String email, String nombre_apellidos, boolean admin, String contrasena) {
        super(id_cuenta, email, nombre_apellidos);
        this.admin = admin;
        this.contrasena = contrasena;
    }

    /*GETTERS*/
    /**
     * Gets the administrator status of the user.
     *
     * @return true if the user is an administrator, false otherwise.
     */
    public boolean isAdmin() {
        return admin;
    }

    public String getContrasena() {
        return contrasena;
    }

    /*SETTERS*/
    /**
     * Sets the administrator status of the user.
     *
     * @param admin true if the user is to be set as an administrator, false otherwise.
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s",super.toString(),admin,contrasena);

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o The object to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO userDTO)) return false;
        if (!super.equals(o)) return false;
        return admin == userDTO.admin && Objects.equals(contrasena, userDTO.contrasena);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), admin, contrasena);
    }
}
