package org.example.proyecto.model.user;
import org.example.proyecto.model.account.AccountDTO;

public class UserDTO extends AccountDTO {
    private boolean admin;


    public UserDTO(int id_cuenta, String email, String contrasena, String nombre_apellidos, boolean admin) {
        super(id_cuenta, email, contrasena, nombre_apellidos);
        this.admin = admin;
    }

    /*GETTERS*/
    public boolean isAdmin() {
        return admin;
    }
  
    /*SETTERS*/
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return String.format("%s,%s",super.toString(),admin);

    }
}
