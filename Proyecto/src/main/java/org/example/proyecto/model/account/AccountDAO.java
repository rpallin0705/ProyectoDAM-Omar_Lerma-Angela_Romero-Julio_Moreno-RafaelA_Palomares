package org.example.proyecto.model.account;

import org.example.proyecto.model.user.UserDTO;

import java.sql.SQLException;
import java.util.List;

public interface AccountDAO {
    List<AccountDTO> getAccount() throws SQLException;
    boolean updateAccount(AccountDTO updatedAccount) throws SQLException;
}
