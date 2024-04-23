package org.example.proyecto.model.booking;

import org.example.proyecto.SetUpConnection;
import org.example.proyecto.model.user.UserDTO;

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
     * Obtiene la lista de todas las reservas almacenadas en la base de datos.
     *
     * @return Lista de objetos BookingDTO que representan las reservas almacenadas en la base de datos.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
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
     * Inserta una nueva reserva en la base de datos.
     *
     * @param newBooking Objeto BookingDTO que representa la nueva reserva a insertar.
     * @return true si la reserva fue insertada exitosamente, false en caso contrario.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     */
    @Override
    public boolean insertBooking(BookingDTO newBooking) throws SQLException {
        String sql = "INSERT INTO reservas (fecha_ini, fecha_fin, email, telefono, cod_alojamiento) VALUES (?, ?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, newBooking.getFechaInicio());
        preparedStatement.setString(2, newBooking.getFechaFin());
        preparedStatement.setString(3, newBooking.getEmail());
        preparedStatement.setString(4, newBooking.getTelefono());
        preparedStatement.setString(5, newBooking.getCodAlojamiento());
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected != 0;
    }

    /**
     * Elimina una reserva de la base de datos utilizando su identificador.
     *
     * @param bookingId Identificador de la reserva que se va a eliminar.
     * @return true si la reserva fue eliminada exitosamente, false en caso contrario.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     * * @todo excepciones propias para cuando se introduzca la contraseña incorrecta o no exista el usuario
     */
    @Override
    public boolean deleteBookingByID(String bookingId) throws SQLException {
        String sql = "DELETE FROM reservas WHERE id_reserva = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, bookingId);
        int result = preparedStatement.executeUpdate();
        return result != 0;
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
        String sql = "UPDATE reservas SET fecha_ini = ?, fecha_fin = ?, telefono = ?, cod_alojamiento = ? WHERE email = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, updatedBooking.getFechaInicio());
        preparedStatement.setString(2, updatedBooking.getFechaFin());
        preparedStatement.setString(3, updatedBooking.getTelefono());
        preparedStatement.setString(4, updatedBooking.getCodAlojamiento());
        preparedStatement.setString(5, updatedBooking.getEmail());
        int result = preparedStatement.executeUpdate();
        return result != 0;
    }

}

}
