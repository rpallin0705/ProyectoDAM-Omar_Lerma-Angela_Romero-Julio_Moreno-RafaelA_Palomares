package org.example.proyecto.model.account;

import org.example.proyecto.SetUpConnection;
import org.example.proyecto.model.user.UserDTO;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDB implements AccountDAO{
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;

    public AccountDB() throws SQLException, IOException {
        connection = SetUpConnection.getInstance().getConnection();
    }

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
