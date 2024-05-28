package org.example.proyecto.model.user;

import org.example.proyecto.model.account.AccountDTO;

/**
 * Represents a user, extending the AccountDTO class.
 *
 * @version 1.0
 * @since 2024-05-28
 * @author Omar
 */
public class UserDTO extends AccountDTO {

    private boolean admin;
    private String contrasena;

    /**
     * Constructs a new UserDTO object with the specified parameters.
     *
     * @param id_cuenta        The ID of the account.
     * @param email            The email address of the user.
     * @param nombre_apellidos The name and surname of the user.
     * @param admin            The admin status of the user.
     * @param contrasena       The password of the user.
     */
    public UserDTO(int id_cuenta, String email, String nombre_apellidos, boolean admin, String contrasena) {
        super(id_cuenta, email, nombre_apellidos);
        this.admin = admin;
        this.contrasena = contrasena;
    }

    /**
     * Retrieves the admin status of the user.
     *
     * @return true if the user is an admin, false otherwise.
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * Retrieves the password of the user.
     *
     * @return The password of the user.
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Sets the admin status of the user.
     *
     * @param admin The admin status to be set.
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    /**
     * Sets the password of the user.
     *
     * @param contrasena The password to be set.
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Returns a string representation of the UserDTO object.
     *
     * @return A string containing the attributes of the user in CSV format.
     */
    @Override
    public String toString() {
        return String.format("%s,%s,%s", super.toString(), admin, contrasena);
    }
}
