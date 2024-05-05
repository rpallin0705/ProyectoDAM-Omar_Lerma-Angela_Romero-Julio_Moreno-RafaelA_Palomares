package org.example.proyecto.model.client;

public class ClientDTO {
    private String email;
    private String phoneNumber;
    private String fullName;
    private String clientAddress;

    public ClientDTO(String email, String phoneNumber, String fullName, String clientAddress) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.clientAddress = clientAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s", email, phoneNumber, fullName, clientAddress);
    }
}
