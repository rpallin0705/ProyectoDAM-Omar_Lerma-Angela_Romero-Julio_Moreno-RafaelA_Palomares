package org.example.proyecto.model.hotel;

import org.example.proyecto.SetUpConnection;
import java.io.IOException;
import java.net.IDN;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelDB implements HotelDAO {
private Connection connection;
private PreparedStatement preparedStatement;
private Statement statement;

    public HotelDB(Connection connection) throws SQLException, IOException {
        this.connection = SetUpConnection.getInstance().getConnection();
    }
    /**
     * HotelDB
     * method that connects with the DataBase and gets a list of hotels, looking in a view, not directly the tables
     * @return list of hotels
     * @throws SQLException
     */
    @Override
    public List<HotelDTO> getHotels() throws SQLException {
        ArrayList<HotelDTO> hotels = new ArrayList<>();
        HotelDTO hotelDTO = null;
        String sql = "SELECT * FROM vista_hoteles";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            int id_alojamiento = resultSet.getInt("id_alojamiento");
            String nombre_alojamiento = resultSet.getString("nombre_alojamiento");
            String calle = resultSet.getString("calle");
            int clasificacion = resultSet.getInt("clasificacion");
            String tipo_habitacion = resultSet.getString("tipo_habitacion");
            int numero_huespedes = resultSet.getInt("numero_huespedes");
            switch (tipo_habitacion.toUpperCase()){
                case "INDIVIDUAL" -> {
                    hotelDTO = new HotelDTO(id_alojamiento,nombre_alojamiento,calle,clasificacion, RoomType.INDIVIDUAL,numero_huespedes);
                    hotels.add(hotelDTO);
                }
                case "DOBLE" -> {
                    hotelDTO = new HotelDTO(id_alojamiento,nombre_alojamiento,calle,clasificacion, RoomType.DOUBLE,numero_huespedes);
                    hotels.add(hotelDTO);
                }
                case "SUPLEMENTO" -> {
                    hotelDTO = new HotelDTO(id_alojamiento,nombre_alojamiento,calle,clasificacion, RoomType.SUPPLEMENT,numero_huespedes);
                    hotels.add(hotelDTO);
                }
            }
        }
        return hotels;
    }
    /**
     * HotelDB
     * method that connects with the DataBase and updates a concrete record in the housing table
     * and then updates a concrete record in the hotel table (both searching by ID)
     * @return true if the rows affected are not 0
     * @throws SQLException
     */
    @Override
    public boolean updateHotel(HotelDTO updatedHotel) throws SQLException {
        //alojamientos
        String sql = "UPDATE alojamientos SET nombre = ?, calle = ? WHERE id_alojamiento = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, updatedHotel.getNombre());
        preparedStatement.setString(2, updatedHotel.getCalle());
        preparedStatement.setInt(3,updatedHotel.getHousingId());
        int rowsAffected = preparedStatement.executeUpdate();
        //hoteles
        String sql2 = "UPDATE hoteles SET clasificacion = ?, tipo_habitacion = ?, numero_huespedes = ? WHERE id_alojamiento = ?";
        preparedStatement = connection.prepareStatement(sql2);
        preparedStatement.setInt(1,updatedHotel.getHotelClassification());
        preparedStatement.setString(2,updatedHotel.getRoomType().toString());
        preparedStatement.setInt(3,updatedHotel.getHostNumber());
        preparedStatement.setInt(4,updatedHotel.getHousingId());
        rowsAffected += preparedStatement.executeUpdate();
        //vista_hoteles
        String sql3 = "UPDATE vista_hoteles SET nombre = ?, calle = ?,clasificacion = ?, tipo_habitacion = ?, numero_huespedes = ? WHERE id_alojamiento = ?";
        preparedStatement.setString(1, updatedHotel.getNombre());
        preparedStatement.setString(2, updatedHotel.getCalle());
        preparedStatement.setInt(3, updatedHotel.getClasificacion());
        preparedStatement.setString(4, updatedHotel.getTipo_habitacion().toString());
        preparedStatement.setInt(5,updatedHotel.getNumero_huespedes());
        preparedStatement.setInt(6, updatedHotel.getId_alojamiento());
        rowsAffected += preparedStatement.executeUpdate();
        connection.commit();
        connection.setAutoCommit(true);
        return rowsAffected != 0;
    }
    /**
     * HotelDB
     * method that connects with the DataBase and deletes a concrete hotel from the housing table (on cascade) searching by ID
     * @return true if the rows affected are not 0
     * @throws SQLException
     */
    @Override
    public boolean deleteHotel(HotelDTO deletedHotel) throws SQLException {
        String sql = "DELETE FROM alojamientos WHERE id_alojamiento = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,deletedHotel.getHousingId());
        int rowsAffected = preparedStatement.executeUpdate();
        //vista_aps_turisticos
        String sql2 = "DELETE FROM vista_aps_turisticos WHERE id_alojamiento = ?";
        preparedStatement = connection.prepareStatement(sql2);
        preparedStatement.setInt(1, deletedHotel.getId_alojamiento());
        rowsAffected += preparedStatement.executeUpdate();
        connection.commit();
        connection.setAutoCommit(true);
        return rowsAffected != 0;
    }
    /**
     * HotelDB
     * method that connects with the DataBase and insert a record into the housing table, then takes de ID
     * (autoincremental) and insert a record into the hotels apartments table
     * @return true if the rows affected are not 0
     * @throws SQLException
     */
    @Override
    public boolean insertHotel(HotelDTO insertedHotel) throws SQLException {
        //alojamientos
        String sql = "INSERT INTO alojamientos (nombre, calle) VALUES (?, ?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, insertedHotel.getNombre());
        preparedStatement.setString(2,insertedHotel.getCalle());
        int rowsAffected = preparedStatement.executeUpdate();
        //conseguir id del ultimo alojamiento insertado
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        int idConseguido = generatedKeys.getInt(1);
        //hoteles
        String sql2 = "INSERT INTO hoteles (id_alojamiento, clasificacion, tipo_habitacion, numero_huespedes) VALUES (?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(sql2);
        preparedStatement.setInt(1,idConseguido);
        preparedStatement.setInt(2, insertedHotel.getHotelClassification());
        preparedStatement.setString(3,insertedHotel.getRoomType().toString());
        preparedStatement.setInt(4,insertedHotel.getHostNumber());
        rowsAffected += preparedStatement.executeUpdate();
        //vista_hoteles
        String sql3 = "INSERT INTO vista_hoteles (id_alojamiento, nombre, calle, clasificacion, tipo_habitacion, numero_huespedes) VALUES (?, ?, ?, ?, ?, ?)";
        preparedStatement.setInt(1, idConseguido);
        preparedStatement.setString(2, insertedHotel.getNombre());
        preparedStatement.setString(3, insertedHotel.getCalle());
        preparedStatement.setInt(4, insertedHotel.getClasificacion());
        preparedStatement.setString(5, insertedHotel.getTipo_habitacion().toString());
        preparedStatement.setInt(6,insertedHotel.getNumero_huespedes());
        rowsAffected += preparedStatement.executeUpdate();
        connection.commit();
        connection.setAutoCommit(true);
        return rowsAffected != 0;
    }
}
