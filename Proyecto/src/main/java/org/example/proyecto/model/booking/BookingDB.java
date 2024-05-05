package org.example.proyecto.model.booking;

import org.example.proyecto.SetUpConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDB implements BookingDAO{
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public BookingDB() throws SQLException, IOException {
        connection = SetUpConnection.getInstance().getConnection();
    }

    /**
     * Gets the list of all bookings stored in the database.
     *
     * @return List of BookingDTO objects representing the bookings stored in the database.
     * @throws SQLException If an error occurs while executing the SQL query.
     * @todo Convert dates to LocalDate and change to String format.
     */
    @Override
    public List<BookingDTO> getBookings() throws SQLException {
        List<BookingDTO> bookings = new ArrayList<>();
        String sql = "SELECT * FROM reservas;";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        BookingDTO bookingDTO = null;
        while (resultSet.next()) {
            String fecha_ini = resultSet.getString("fecha_ini");
            String fecha_fin = resultSet.getString("fecha_fin");
            String email = resultSet.getString("email");
            String telefono = resultSet.getString("telefono");
            String codAlojamiento = resultSet.getString("cod_alojamiento");
            bookingDTO = new BookingDTO(fecha_ini, fecha_fin, email, telefono, codAlojamiento);
            bookings.add(bookingDTO);
        }
        return bookings;
    }


    /**
     * Inserts a new booking into the database.
     *
     * @param newBooking The BookingDTO object representing the new booking to insert.
     * @return true if the booking was successfully inserted, false otherwise.
     * @throws SQLException If an error occurs while executing the SQL query.
     */
    @Override
    public boolean insertBooking(BookingDTO newBooking) throws SQLException {
        String sql = "INSERT INTO reservas (fecha_ini, fecha_fin, email, telefono, cod_alojamiento) VALUES (?, ?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, newBooking.getCheckInDate());
        preparedStatement.setString(2, newBooking.getCheckOutDate());
        preparedStatement.setString(3, newBooking.getEmail());
        preparedStatement.setString(4, newBooking.getPhoneNumber());
        preparedStatement.setString(5, newBooking.getHousingCode());
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected != 0;
    }


    @Override
    public boolean deleteBookingByID(String bookingId) throws SQLException {
        String sql = "DELETE FROM reservas WHERE id_reserva = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, bookingId);
        int result = preparedStatement.executeUpdate();
        return result != 0;
    }

    /**
     * Deletes a booking from the database using its identifier.
     *
     * @param bookingId The identifier of the booking to be deleted.
     * @return true if the booking was successfully deleted, false otherwise.
     * @throws SQLException If an error occurs while executing the SQL query.
     * @todo Custom exceptions for when the incorrect password is entered or the user does not exist.
     */
    @Override
    public boolean updateBooking(BookingDTO updatedBooking) throws SQLException {
        String sql = "UPDATE reservas SET fecha_ini = ?, fecha_fin = ?, telefono = ?, cod_alojamiento = ? WHERE email = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, updatedBooking.getCheckInDate());
        preparedStatement.setString(2, updatedBooking.getCheckOutDate());
        preparedStatement.setString(3, updatedBooking.getPhoneNumber());
        preparedStatement.setString(4, updatedBooking.getHousingCode());
        preparedStatement.setString(5, updatedBooking.getEmail());
        int result = preparedStatement.executeUpdate();
        return result != 0;
    }

}


