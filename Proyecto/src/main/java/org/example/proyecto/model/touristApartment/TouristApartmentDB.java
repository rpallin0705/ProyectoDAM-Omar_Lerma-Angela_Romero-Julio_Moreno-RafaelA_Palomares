package org.example.proyecto.model.touristApartment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TouristApartmentDB implements TouristApartmentDAO{
    private PreparedStatement preparedStatement;
    private Connection connection;
    private Statement statement;

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
        ResultSet resultSet = statement.getResultSet();
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
        String sql = "UPDATE alojamientos SET nombre = ?, calle = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, updatedApartment.getNombre());
        preparedStatement.setString(2, updatedApartment.getCalle());
        int rowsAffected = preparedStatement.executeUpdate();
        //apartamentos
        String sql2 = "UPDATE alojamientos SET dist_centro_km = ?";
        preparedStatement = connection.prepareStatement(sql2);
        preparedStatement.setFloat(1,updatedApartment.getDowntownDistance());
        rowsAffected += preparedStatement.executeUpdate();
        //vista_hoteles
        String sql3 = "UPDATE vista_aps_turisticos SET nombre = ?, calle = ?, dist_centro_km = ? WHERE id_alojamiento = ?";
        preparedStatement.setString(1, updatedApartment.getNombre());
        preparedStatement.setString(2, updatedApartment.getCalle());
        preparedStatement.setFloat(3, updatedApartment.getDowntownDistance());
        preparedStatement.setInt(4, updatedApartment.getHousingId());
        rowsAffected += preparedStatement.executeUpdate();
        connection.commit();
        connection.setAutoCommit(true);
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
        //vista_aps_turisticos
        String sql2 = "DELETE FROM vista_aps_turisticos WHERE id_alojamiento = ?";
        preparedStatement = connection.prepareStatement(sql2);
        preparedStatement.setInt(1, deletedApartment.getHousingId());
        rowsAffected += preparedStatement.executeUpdate();
        connection.commit();
        connection.setAutoCommit(true);
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
        //vista_hoteles
        String sql3 = "INSERT INTO vista_aps_turisticos (id_alojamiento, nombre, calle, dist_centro_km) VALUES (?, ?, ?, ?)";
        preparedStatement.setInt(1, idGenerado);
        preparedStatement.setString(2, insertedApartment.getNombre());
        preparedStatement.setString(3, insertedApartment.getCalle());
        preparedStatement.setFloat(4, insertedApartment.getDowntownDistance());
        rowsAffected += preparedStatement.executeUpdate();
        connection.commit();
        connection.setAutoCommit(true);
        return rowsAffected != 0;
    }
}
