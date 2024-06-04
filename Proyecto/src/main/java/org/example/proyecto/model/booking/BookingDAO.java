package org.example.proyecto.model.booking;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface for Booking Data Access Object (DAO).
 * Provides methods for accessing and manipulating booking data in the database.
 *
 * @version 1.0
 * @since 2024-05-28
 *
 * @Author Omar
 */
public interface BookingDAO {

    /**
     * Retrieves a list of all bookings from the database.
     *
     * @return a list of {@link BookingDTO} representing all bookings.
     * @throws SQLException if a database access error occurs.
     */
    List<BookingDTO> getBookings() throws SQLException;

    /**
     * Inserts a new booking into the database.
     *
     * @param newBooking a {@link BookingDTO} object containing the new booking details.
     * @return true if the booking was successfully inserted, false otherwise.
     * @throws SQLException if a database access error occurs.
     */
    boolean insertBooking(BookingDTO newBooking) throws SQLException;

    /**
     * Deletes a booking from the database based on its ID.
     *
     * @param deletedBooking a {@link BookingDTO} object containing the ID of the booking to be deleted.
     * @return true if the booking was successfully deleted, false otherwise.
     * @throws SQLException if a database access error occurs.
     */
    boolean deleteBooking(BookingDTO deletedBooking) throws SQLException;

    /**
     * Updates the details of an existing booking in the database.
     *
     * @param updatedBooking a {@link BookingDTO} object containing the updated booking details.
     * @return true if the booking was successfully updated, false otherwise.
     * @throws SQLException if a database access error occurs.
     */
    boolean updateBooking(BookingDTO updatedBooking) throws SQLException;
}
