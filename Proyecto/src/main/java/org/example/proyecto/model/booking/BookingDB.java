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
     * Obtiene la lista de todas las reservas almacenadas en la base de datos.
     *
     * @return Lista de objetos BookingDTO que representan las reservas almacenadas en la base de datos.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     * @todo Hacer las fechas LocalDate y hacer el cambio a String
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
     * Inserta una nueva reserva en la base de datos.
     *
     * @param newBooking Objeto BookingDTO que representa la nueva reserva a insertar.
     * @return true si la reserva fue insertada exitosamente, false en caso contrario.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     */
    @Override
    public boolean insertBooking(BookingDTO newBooking) throws SQLException {
        String sql = "INSERT INTO reservas (fecha_ini, fecha_fin, id_cuenta) VALUES(?, ?, ?);";
        preparedStatement.setDate(1, java.sql.Date.valueOf(newBooking.getFechaInicio()));
        preparedStatement.setDate(2, java.sql.Date.valueOf(newBooking.getFechaFin()));
        preparedStatement.setInt(3, newBooking.getId_cuenta());
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected != 0;
    }

    /**
     * Elimina una reserva de la base de datos utilizando su identificador.
     *
     * @param deletedBooking Objeto BookingDTO que contiene la información de la reserva que se desea eliminar.
     * @return true si la reserva fue eliminada exitosamente, false en caso contrario.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     * * @todo excepciones propias para cuando se introduzca la contraseña incorrecta o no exista el usuario
     */
    @Override
    public boolean deleteBookingByID(BookingDTO deletedBooking) throws SQLException {
        String sql = "DELETE FROM reservas WHERE id_reserva = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, deletedBooking.getId_reserva());
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected != 0;
    }

    /**
     * Actualiza la información de una reserva en la base de datos.
     *
     * @param updatedBooking Objeto BookingDTO que contiene la información actualizada de la reserva.
     * @return true si la actualización fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     */
    @Override
    public boolean updateBooking(BookingDTO updatedBooking) throws SQLException {
        String sql = "UPDATE reservas SET fecha_ini = ?, fecha_fin = ?, id_cuenta = ? WHERE id_reserva = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDate(1,java.sql.Date.valueOf(updatedBooking.getFechaInicio()));
        preparedStatement.setDate(2,java.sql.Date.valueOf(updatedBooking.getFechaFin()));
        preparedStatement.setInt(3,updatedBooking.getId_cuenta());
        preparedStatement.setInt(4,updatedBooking.getId_reserva());
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected != 0;
    }

}


