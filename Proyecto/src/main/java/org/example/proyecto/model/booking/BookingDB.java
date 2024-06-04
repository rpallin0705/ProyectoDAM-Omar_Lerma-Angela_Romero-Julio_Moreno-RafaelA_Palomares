package org.example.proyecto.model.booking;

import org.example.proyecto.SetUpConnection;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the {@link BookingDAO} interface for accessing and manipulating booking data in the database.
 * This class uses JDBC for database operations.
 *
 * @version 1.0
 * @since 2024-05-28
 *
 * @Author Omar, Rafael
 */
public class BookingDB implements BookingDAO {
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    /**
     * Constructs a {@code BookingDB} object and establishes a database connection.
     *
     * @throws SQLException if a database access error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public BookingDB() throws SQLException, IOException {
        connection = SetUpConnection.getInstance().getConnection();
    }

    /**
     * Gets the list of all bookings stored in the database.
     *
     * @return List of {@link BookingDTO} objects representing the bookings stored in the database.
     * @throws SQLException If an error occurs while executing the SQL query.
     */
    @Override
    public List<BookingDTO> getBookings() throws SQLException {
        List<BookingDTO> bookings = new ArrayList<>();
        String sql = "SELECT * FROM reservas;";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            LocalDate fecha_ini = resultSet.getDate("fecha_ini").toLocalDate();
            LocalDate fecha_fin = resultSet.getDate("fecha_fin").toLocalDate();
            int id_cuenta = resultSet.getInt("id_cuenta");
            int id_alojamiento = resultSet.getInt("id_alojamiento");
            int id_reserva = resultSet.getInt("id_reserva");

            BookingDTO bookingDTO = new BookingDTO(fecha_ini, fecha_fin, id_reserva, id_cuenta, id_alojamiento);
            bookings.add(bookingDTO);
        }

        return bookings;
    }

    /**
     * Inserts a new booking into the database.
     *
     * @param newBooking The {@link BookingDTO} object representing the new booking to insert.
     * @return true if the booking was successfully inserted, false otherwise.
     * @throws SQLException If an error occurs while executing the SQL query.
     */
    @Override
    public boolean insertBooking(BookingDTO newBooking) throws SQLException {
        String sql = "INSERT INTO reservas (fecha_ini, fecha_fin, id_cuenta, id_alojamiento) VALUES(?, ?, ?, ?);";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDate(1, java.sql.Date.valueOf(newBooking.getCheckInDate()));
        preparedStatement.setDate(2, java.sql.Date.valueOf(newBooking.getCheckOutDate()));
        preparedStatement.setInt(3, newBooking.getCountId());
        preparedStatement.setInt(4, newBooking.getHousingId());
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected != 0;
    }

    /**
     * Deletes a booking from the database based on its ID.
     *
     * @param deletedBooking The {@link BookingDTO} object containing the ID of the booking to delete.
     * @return true if the booking was successfully deleted, false otherwise.
     * @throws SQLException If an error occurs while executing the SQL query.
     * @todo Create custom exceptions for when an incorrect password is entered or the user does not exist.
     */
    @Override
    public boolean deleteBooking(BookingDTO deletedBooking) throws SQLException {
        String sql = "DELETE FROM reservas WHERE id_reserva = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, deletedBooking.getBookingId());
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected != 0;
    }

    /**
     * Updates the details of an existing booking in the database.
     *
     * @param updatedBooking The {@link BookingDTO} object containing the updated booking details.
     * @return true if the update was successful, false otherwise.
     * @throws SQLException If an error occurs while executing the SQL query.
     */
    @Override
    public boolean updateBooking(BookingDTO updatedBooking) throws SQLException {
        String sql = "UPDATE reservas SET fecha_ini = ?, fecha_fin = ?, id_cuenta = ?, id_alojamiento = ? WHERE id_reserva = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDate(1, java.sql.Date.valueOf(updatedBooking.getCheckInDate()));
        preparedStatement.setDate(2, java.sql.Date.valueOf(updatedBooking.getCheckOutDate()));
        preparedStatement.setInt(3, updatedBooking.getCountId());
        preparedStatement.setInt(4, updatedBooking.getHousingId());
        preparedStatement.setInt(5, updatedBooking.getBookingId());
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected != 0;
    }
}
