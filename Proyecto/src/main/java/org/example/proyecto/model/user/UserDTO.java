package org.example.proyecto.model.user;
import org.example.proyecto.model.account.AccountDTO;

import java.util.Objects;

public class UserDTO extends AccountDTO {
    private boolean admin;
    private String contrasena;


    public UserDTO(int id_cuenta, String email, String nombre_apellidos, boolean admin, String contrasena) {
        super(id_cuenta, email, nombre_apellidos);
        this.admin = admin;
        this.contrasena = contrasena;
    }

    public UserDTO(UserDTO userToCopy){
        super(userToCopy.getId_cuenta(), userToCopy.getEmail(), userToCopy.getNombre_apellidos());
        this.admin = userToCopy.isAdmin();
        this.contrasena = userToCopy.getContrasena();
    }

    public UserDTO(String email, String nombre_apellidos, boolean isAdmin, String contrasenna) {
        super(email, nombre_apellidos);
        this.admin = isAdmin;
        this.contrasena = contrasenna;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO userDTO)) return false;
        if (!super.equals(o)) return false;
        return admin == userDTO.admin && Objects.equals(contrasena, userDTO.contrasena);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), admin, contrasena);
    }
}
