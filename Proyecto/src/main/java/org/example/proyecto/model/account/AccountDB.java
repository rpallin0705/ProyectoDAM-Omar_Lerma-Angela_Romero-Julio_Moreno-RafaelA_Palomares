package org.example.proyecto.model.account;

import org.example.proyecto.SetUpConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the {@link AccountDAO} interface for accessing and manipulating account data in the database.
 * This class uses JDBC for database operations.
 *
 * @version 1.0
 * @since 2024-05-28
 *
 * @Author Omar
 */
public class AccountDB implements AccountDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;

    /**
     * Constructs an {@code AccountDB} object and establishes a database connection.
     *
     * @throws SQLException if a database access error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public AccountDB() throws SQLException, IOException {
        connection = SetUpConnection.getInstance().getConnection();
    }

    /**
     * Retrieves a list of all accounts from the database.
     *
     * @return a list of {@link AccountDTO} representing all accounts.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public List<AccountDTO> getAccount() throws SQLException {
        String sql = "SELECT * FROM accounts;";
        List<AccountDTO> accounts = new ArrayList<>();
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        AccountDTO accountDTO = null;
        while (resultSet.next()){
            accountDTO = new AccountDTO(resultSet.getInt("id_cuenta"),resultSet.getString("email"),resultSet.getString("nombre_apellidos"));
            accounts.add(accountDTO);
        }
        return accounts;
    }

    /**
     * Updates the details of an existing account in the database.
     *
     * @param updatedAccount an {@link AccountDTO} object containing the updated account details.
     * @return true if the account was successfully updated, false otherwise.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public boolean updateAccount(AccountDTO updatedAccount) throws SQLException {
        String sql = "UPDATE cuentas SET email = ?, nombre_apellidos = ? WHERE id_cuenta = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,updatedAccount.getEmail());
        preparedStatement.setString(2,updatedAccount.getNombre_apellidos());
        preparedStatement.setInt(3,updatedAccount.getId_cuenta());
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected != 0;
    }
}
