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

    /**
     * Constructs a UserDTO object with the specified details.
     *
     * @param id_cuenta        The ID of the user's account.
     * @param email            The email address of the user.
     * @param nombre_apellidos The name and surname(s) of the user.
     * @param admin            Indicates whether the user has administrator privileges.
     * @param contrasena       The password of the user.
     */
    public UserDTO(int id_cuenta, String email, String nombre_apellidos, boolean admin, String contrasena) {
        super(id_cuenta, email, nombre_apellidos);
        this.admin = admin;
        this.contrasena = contrasena;
    }

    /**
     * Copy constructor for UserDTO.
     *
     * @param userToCopy The UserDTO object to be copied.
     */
    public UserDTO(UserDTO userToCopy) {
        super(userToCopy.getId_cuenta(), userToCopy.getEmail(), userToCopy.getNombre_apellidos());
        this.admin = userToCopy.isAdmin();
        this.contrasena = userToCopy.getContrasena();
    }

    /**
     * Constructs a UserDTO object with the specified details.
     *
     * @param email        The email address of the user.
     * @param nombre_apellidos The name and surname(s) of the user.
     * @param isAdmin      Indicates whether the user has administrator privileges.
     * @param contrasenna  The password of the user.
     */
    public UserDTO(String email, String nombre_apellidos, boolean isAdmin, String contrasenna) {
        super(email, nombre_apellidos);
        this.admin = isAdmin;
        this.contrasena = contrasenna;
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

    /**
     * Gets the password of the user.
     *
     * @return the password of the user.
     */
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

    /**
     * Sets the password of the user.
     *
     * @param contrasena the password to set for the user.
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }


    /**
     * Returns a string representation of the user.
     *
     * @return the string representation of the user.
     */
    @Override
    public String toString() {
        return String.format("%s,%s,%s", super.toString(), admin, contrasena);
    }

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
