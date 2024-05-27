package org.example.proyecto.model.account;


import java.sql.SQLException;
import java.util.List;

public interface AccountDAO {
    List<AccountDTO> getAccount() throws SQLException;
    boolean updateAccount(AccountDTO updatedAccount) throws SQLException;
}
