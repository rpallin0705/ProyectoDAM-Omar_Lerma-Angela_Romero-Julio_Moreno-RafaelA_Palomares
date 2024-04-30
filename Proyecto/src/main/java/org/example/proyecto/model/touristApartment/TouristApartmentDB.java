package org.example.proyecto.model.touristApartment;

import org.example.proyecto.model.hotel.HotelDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TouristApartmentDB implements TouristApartmentDAO{
    private PreparedStatement preparedStatement;
    private Connection connection;
    private Statement statement;
    @Override
    public List<TouristApartmentDTO> getHotels() throws SQLException {
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
        preparedStatement.setInt(1,updatedApartment.getDistanciaCentros());
        rowsAffected += preparedStatement.executeUpdate();
        return rowsAffected != 0;
    }

    @Override
    public boolean deleteTouristApartment(TouristApartmentDTO deletedApartment) throws SQLException {
        String sql = "DELETE FROM alojamientos WHERE id_alojamiento = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, deletedApartment.getId_alojamiento());
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected != 0;
    }

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
        preparedStatement.setInt(2,insertedApartment.getDistanciaCentros());
        rowsAffected += preparedStatement.executeUpdate();
        return rowsAffected != 0;
    }
}
