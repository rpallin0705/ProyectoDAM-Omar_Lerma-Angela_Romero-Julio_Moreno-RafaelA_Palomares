package org.example.proyecto.model.account;

import java.util.Objects;

/**
 * Data Transfer Object (DTO) for account information.
 * This class encapsulates the data of an account and provides methods for accessing and manipulating this data.
 *
 * @version 1.0
 * @since 2024-05-28
 *
 * @Author Omar
 */
public class AccountDTO {
    private int id_cuenta;
    private String email;
    private String nombre_apellidos;

    public AccountDTO(int id_cuenta, String email, String nombre_apellidos) {
        this.id_cuenta = id_cuenta;
        this.email = email;
        this.nombre_apellidos = nombre_apellidos;
    }

    /**
     * Constructs an {@code AccountDTO} object with the specified email and name.
     * This constructor is intended for creating new account objects to be inserted into the database.
     *
     * @param email the new account email.
     * @param nombre_apellidos the new account name and surname.
     */
    public AccountDTO(String email, String nombre_apellidos) {
        this.email = email;
        this.nombre_apellidos = nombre_apellidos;
    }

    /**
     * Checks if this account is equal to another object.
     * Two accounts are considered equal if their email and name are the same.
     *
     * @param o the object to compare with.
     * @return true if the accounts are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountDTO that)) return false;
        return Objects.equals(email, that.email) && Objects.equals(nombre_apellidos, that.nombre_apellidos);
    }

    /**
     * Returns a hash code value for the account.
     * The hash code is based on the account's email and name.
     *
     * @return the hash code value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(email, nombre_apellidos);
    }

    /**
     * Gets the account ID.
     *
     * @return the account ID.
     */
    public int getId_cuenta() {
        return id_cuenta;
    }

    /**
     * Gets the account email.
     *
     * @return the account email.
     */
    public String getEmail() {
        return email;
    }

    public String getNombre_apellidos() {
        return nombre_apellidos;
    }


    /*SETTERS*/
    public void setEmail(String email) {
        this.email = email;
    }

    public void setNombre_apellidos(String nombre_apellidos) {
        this.nombre_apellidos = nombre_apellidos;
    }

    /**
     * Returns a string representation of the account.
     * The string contains the account ID, email, and name, separated by commas.
     *
     * @return the string representation of the account.
     */
    @Override
    public String toString() {
        return String.format("%d,%s,%s", id_cuenta, email, nombre_apellidos);
    }
}
