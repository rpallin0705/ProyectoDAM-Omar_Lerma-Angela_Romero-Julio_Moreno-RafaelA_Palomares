package org.example.proyecto.model.touristApartment;

import org.example.proyecto.SetUpConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TouristApartmentDB implements TouristApartmentDAO{
    private PreparedStatement preparedStatement;
    private Connection connection;
    private Statement statement;

    public TouristApartmentDB() throws SQLException, IOException {
        connection = SetUpConnection.getInstance().getConnection();
    }

    /**
     * TouristApartmentDB
     * method that connects with the DataBase and gets a list of apartments, looking in a view, not directly the tables
     * @return list of tourist apartments
     * @throws SQLException
     */
    @Override
    public List<TouristApartmentDTO> getTouristApartments() throws SQLException {
        ArrayList<TouristApartmentDTO> apartments = new ArrayList<>();
        TouristApartmentDTO touristApartmentDTO= null;
        String sql = "SELECT * FROM vista_aps_turisticos";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            touristApartmentDTO = new TouristApartmentDTO(resultSet.getInt("id_alojamiento"),resultSet.getString("nombre_alojamiento"),resultSet.getString("calle"),resultSet.getInt("dist_centro_km") );
            apartments.add(touristApartmentDTO);
        }
        return apartments;
    }
    /**
     * TouristApartmentDB
     * method that connects with the DataBase and updates a concrete record in the housing table
     * and then updates a concrete record in the tourist apartments table (both searching by ID)
     * @return true if the rows affected are not 0
     * @throws SQLException
     */
    @Override
    public boolean updateTourisApartment(TouristApartmentDTO updatedApartment) throws SQLException {
        //alojamientos
        String sql = "UPDATE alojamientos SET nombre = ?, calle = ? WHERE id_alojamiento = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, updatedApartment.getNombre());
        preparedStatement.setString(2, updatedApartment.getCalle());
        preparedStatement.setInt(3,updatedApartment.getHousingId());
        int rowsAffected = preparedStatement.executeUpdate();
        //apartamentos
        String sql2 = "UPDATE aps_turisticos SET dist_centro_km = ? WHERE id_alojamiento = ?";
        preparedStatement = connection.prepareStatement(sql2);
        preparedStatement.setFloat(1,updatedApartment.getDowntownDistance());
        preparedStatement.setInt(2,updatedApartment.getHousingId());
        rowsAffected += preparedStatement.executeUpdate();
        return rowsAffected != 0;
    }
    /**
     * TouristApartmentDB
     * method that connects with the DataBase and deletes a concrete apartment from the housing table (on cascade) searching by ID
     * @return true if the rows affected are not 0
     * @throws SQLException
     */
    @Override
    public boolean deleteTouristApartment(TouristApartmentDTO deletedApartment) throws SQLException {
        //aps_turisticos && alojamientos
        String sql = "DELETE FROM alojamientos WHERE id_alojamiento = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, deletedApartment.getHousingId());
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected != 0;
    }
    /**
     * TouristApartmentDB
     * method that connects with the DataBase and insert a record into the housing table, then takes de ID
     * (autoincremental) and insert a record into the tourist apartments table
     * @return true if the rows affected are not 0
     * @throws SQLException
     */
    @Override
    public boolean insertTouristApartment(TouristApartmentDTO insertedApartment) throws SQLException {
        //alojamientos
        String sql = "INSERT INTO alojamientos (nombre, calle) VALUES (?, ?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, insertedApartment.getNombre());
        preparedStatement.setString(2, insertedApartment.getCalle());
        int rowsAffected = preparedStatement.executeUpdate();
        //conseguir id del ultimo alojamiento insertado
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        int idGenerado = generatedKeys.getInt(1);
        //apartamentos
        String sql2 = "INSERT INTO aps_turisticos (id_alojamiento, dist_centro_km) VALUES (?, ?)";
        preparedStatement = connection.prepareStatement(sql2);
        preparedStatement.setInt(1,idGenerado);
        preparedStatement.setInt(2,insertedApartment.getHousingId());
        rowsAffected += preparedStatement.executeUpdate();
        return rowsAffected != 0;
    }
}
