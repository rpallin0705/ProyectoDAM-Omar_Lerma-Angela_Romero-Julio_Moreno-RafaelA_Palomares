package org.example.proyecto.model.account;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface for Account Data Access Object (DAO).
 * Provides methods for accessing and manipulating account data in the database.
 *
 * @version 1.0
 * @since 2024-05-28
 *
 * @Author Omar
 */
public interface AccountDAO {

    /**
     * Retrieves a list of all accounts from the database.
     *
     * @return a list of {@link AccountDTO} representing all accounts.
     * @throws SQLException if a database access error occurs.
     */
    List<AccountDTO> getAccount() throws SQLException;

    /**
     * Updates the details of an existing account in the database.
     *
     * @param updatedAccount an {@link AccountDTO} object containing the updated account details.
     * @return true if the account was successfully updated, false otherwise.
     * @throws SQLException if a database access error occurs.
     */
    boolean updateAccount(AccountDTO updatedAccount) throws SQLException;
}
