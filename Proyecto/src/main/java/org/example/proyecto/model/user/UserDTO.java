package org.example.proyecto.model.user;

public class UserDTO {
    private String userEmail;
    private String userPhoneNumber;
    private String userFullName;
    private String userPassword;
    private String userAddress;

    public UserDTO(String userEmail, String userPhoneNumber, String userFullName, String userPassword, String direccion) {
        this.userEmail = userEmail;
        this.userPhoneNumber = userPhoneNumber;
        this.userFullName = userFullName;
        this.userPassword = userPassword;
        this.userAddress = direccion;
    }

    public UserDTO(String userEmail, String userPhoneNumber, String userFullName, String direccion) {
        this.userEmail = userEmail;
        this.userPhoneNumber = userPhoneNumber;
        this.userFullName = userFullName;
        this.userAddress = direccion;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getDireccion() {
        return userAddress;
    }

    public void setDireccion(String direccion) {
        this.userAddress = direccion;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s", userEmail, userPhoneNumber, userFullName, userAddress);
    }
}
