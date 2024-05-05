package org.example.proyecto.model.booking;

import org.example.proyecto.SetUpConnection;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
            LocalDate fecha_ini = LocalDate.of(resultSet.getDate("fecha_ini").getYear(),resultSet.getDate("fecha_ini").getMonth(),resultSet.getDate("fecha_ini").getDay());
            LocalDate fecha_fin =LocalDate.of(resultSet.getDate("fecha_fin").getYear(),resultSet.getDate("fecha_fin").getMonth(),resultSet.getDate("fecha_fin").getDay());
            int id_cuenta = resultSet.getInt("id_cuenta");
            int id_reserva = resultSet.getInt("id_reserva");
            bookingDTO = new BookingDTO(fecha_ini, fecha_fin, id_reserva, id_cuenta );
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
<<<<<<< HEAD
        String sql = "INSERT INTO reservas (fecha_ini, fecha_fin, email, telefono, cod_alojamiento) VALUES (?, ?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, newBooking.getCheckInDate());
        preparedStatement.setString(2, newBooking.getCheckOutDate());
        preparedStatement.setString(3, newBooking.getEmail());
        preparedStatement.setString(4, newBooking.getPhoneNumber());
        preparedStatement.setString(5, newBooking.getHousingCode());
=======
        String sql = "INSERT INTO reservas (fecha_ini, fecha_fin, id_cuenta) VALUES(?, ?, ?);";
        preparedStatement.setDate(1, java.sql.Date.valueOf(newBooking.getFechaInicio()));
        preparedStatement.setDate(2, java.sql.Date.valueOf(newBooking.getFechaFin()));
        preparedStatement.setInt(3, newBooking.getId_cuenta());
>>>>>>> a3722196b5bd945b50e0c8b9c391e23138175651
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected != 0;
    }

<<<<<<< HEAD

=======
    /**
     * Elimina una reserva de la base de datos utilizando su identificador.
     *
     * @param deletedBooking Objeto BookingDTO que contiene la información de la reserva que se desea eliminar.
     * @return true si la reserva fue eliminada exitosamente, false en caso contrario.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     * * @todo excepciones propias para cuando se introduzca la contraseña incorrecta o no exista el usuario
     */
>>>>>>> a3722196b5bd945b50e0c8b9c391e23138175651
    @Override
    public boolean deleteBookingByID(BookingDTO deletedBooking) throws SQLException {
        String sql = "DELETE FROM reservas WHERE id_reserva = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, deletedBooking.getId_reserva());
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected != 0;
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
        String sql = "UPDATE reservas SET fecha_ini = ?, fecha_fin = ?, id_cuenta = ? WHERE id_reserva = ?";
        preparedStatement = connection.prepareStatement(sql);
<<<<<<< HEAD
        preparedStatement.setString(1, updatedBooking.getCheckInDate());
        preparedStatement.setString(2, updatedBooking.getCheckOutDate());
        preparedStatement.setString(3, updatedBooking.getPhoneNumber());
        preparedStatement.setString(4, updatedBooking.getHousingCode());
        preparedStatement.setString(5, updatedBooking.getEmail());
        int result = preparedStatement.executeUpdate();
        return result != 0;
=======
        preparedStatement.setDate(1,java.sql.Date.valueOf(updatedBooking.getFechaInicio()));
        preparedStatement.setDate(2,java.sql.Date.valueOf(updatedBooking.getFechaFin()));
        preparedStatement.setInt(3,updatedBooking.getId_cuenta());
        preparedStatement.setInt(4,updatedBooking.getId_reserva());
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected != 0;
>>>>>>> a3722196b5bd945b50e0c8b9c391e23138175651
    }

}


