package org.example.proyecto.model.user;
import org.example.proyecto.model.account.AccountDTO;

public class UserDTO extends AccountDTO {
    private boolean admin;
    private String contrasena;


    public UserDTO(int id_cuenta, String email, String nombre_apellidos, boolean admin, String contrasena) {
        super(id_cuenta, email, nombre_apellidos);
        this.admin = admin;
        this.contrasena = contrasena;
    }

    /*GETTERS*/
    public boolean isAdmin() {
        return admin;
    }

    public String getContrasena() {
        return contrasena;
    }

    /*SETTERS*/
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s",super.toString(),admin,contrasena);

    }
}
