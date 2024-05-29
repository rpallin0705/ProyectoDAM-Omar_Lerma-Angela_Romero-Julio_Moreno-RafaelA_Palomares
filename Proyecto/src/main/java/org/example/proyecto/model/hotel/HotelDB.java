package org.example.proyecto.model.hotel;

import org.example.proyecto.SetUpConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for handling database operations related to hotels.
 *
 * @version 1.0
 * @since 2024-05-28
 * @Author Omar
 */
public class HotelDB implements HotelDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;

    /**
     * Initializes a new instance of the HotelDB class.
     *
     * @throws SQLException if a database access error occurs.
     * @throws IOException  if an I/O error occurs.
     */
    public HotelDB() throws SQLException, IOException {
        connection = SetUpConnection.getInstance().getConnection();
    }

    /**
     * Retrieves the list of hotels from the database.
     *
     * @return a list of HotelDTO objects.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public List<HotelDTO> getHotels() throws SQLException {
        ArrayList<HotelDTO> hotels = new ArrayList<>();
        HotelDTO hotelDTO = null;
        String sql = "SELECT * FROM vista_hoteles";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int id_alojamiento = resultSet.getInt("id_alojamiento");
            String nombre_alojamiento = resultSet.getString("nombre_alojamiento");
            String calle = resultSet.getString("calle");
            int clasificacion = resultSet.getInt("clasificacion");
            String tipo_habitacion = resultSet.getString("tipo_habitacion");
            int numero_huespedes = resultSet.getInt("numero_huespedes");
            switch (tipo_habitacion.toUpperCase()) {
                case "INDIVIDUAL" -> {
                    hotelDTO = new HotelDTO(id_alojamiento, nombre_alojamiento, calle, clasificacion, RoomType.INDIVIDUAL, numero_huespedes);
                    hotels.add(hotelDTO);
                }
                case "DOBLE", "DOUBLE" -> {
                    hotelDTO = new HotelDTO(id_alojamiento, nombre_alojamiento, calle, clasificacion, RoomType.DOBLE, numero_huespedes);
                    hotels.add(hotelDTO);
                }
                case "SUPLEMENTO", "SUPPLEMENT" -> {
                    hotelDTO = new HotelDTO(id_alojamiento, nombre_alojamiento, calle, clasificacion, RoomType.SUPLEMENTO, numero_huespedes);
                    hotels.add(hotelDTO);
                }
            }
        }
        return hotels;
    }

    /**
     * Updates hotel information in the database.
     *
     * @param updatedHotel the updated hotel.
     * @return true if the update was successful, false otherwise.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public boolean updateHotel(HotelDTO updatedHotel) throws SQLException {
        //alojamientos
        String sql = "UPDATE alojamientos SET nombre = ?, calle = ? WHERE id_alojamiento = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, updatedHotel.getNombre());
        preparedStatement.setString(2, updatedHotel.getCalle());
        preparedStatement.setInt(3, updatedHotel.getHousingId());
        int rowsAffected = preparedStatement.executeUpdate();
        //hoteles
        String sql2 = "UPDATE hoteles SET clasificacion = ?, tipo_habitacion = ?, numero_huespedes = ? WHERE id_alojamiento = ?";
        preparedStatement = connection.prepareStatement(sql2);
        preparedStatement.setInt(1, updatedHotel.getHotelClassification());
        preparedStatement.setString(2, updatedHotel.getRoomType().toString());
        preparedStatement.setInt(3, updatedHotel.getHostNumber());
        preparedStatement.setInt(4, updatedHotel.getHousingId());
        rowsAffected += preparedStatement.executeUpdate();
        return rowsAffected != 0;
    }

    /**
     * Deletes a hotel from the database.
     *
     * @param deletedHotel the hotel to delete.
     * @return true if the deletion was successful, false otherwise.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public boolean deleteHotel(HotelDTO deletedHotel) throws SQLException {
        String sql = "DELETE FROM alojamientos WHERE id_alojamiento = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, deletedHotel.getHousingId());
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected != 0;
    }

    /**
     * Inserts a new hotel into the database.
     *
     * @param insertedHotel the hotel to insert.
     * @return true if the insertion was successful, false otherwise.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public boolean insertHotel(HotelDTO insertedHotel) throws SQLException {
        //alojamientos
        String sql = "INSERT INTO alojamientos (nombre, calle) VALUES (?, ?)";
        preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, insertedHotel.getNombre());
        preparedStatement.setString(2, insertedHotel.getCalle());
        int rowsAffected = preparedStatement.executeUpdate();
        //conseguir id del ultimo alojamiento insertado
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        int idConseguido = 0;
        if (generatedKeys.next()) {
            idConseguido = generatedKeys.getInt(1);
        }
        //hoteles
        String sql2 = "INSERT INTO hoteles (id_alojamiento, clasificacion, tipo_habitacion, numero_huespedes) VALUES (?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(sql2);
        preparedStatement.setInt(1, idConseguido);
        preparedStatement.setInt(2, insertedHotel.getHotelClassification());
        preparedStatement.setString(3, insertedHotel.getRoomType().toString());
        preparedStatement.setInt(4, insertedHotel.getHostNumber());
        rowsAffected += preparedStatement.executeUpdate();
        return rowsAffected != 0;
    }
}
