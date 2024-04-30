package org.example.proyecto.model.housing;

import org.example.proyecto.SetUpConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HousingDB implements HousingDAO
{
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public HousingDB() throws SQLException, IOException {
        connection = SetUpConnection.getInstance().getConnection();
    }

    @Override
    public List<HousingDTO> getHousings() throws SQLException {
        List<HousingDTO> housings = new ArrayList<>();
        String sql = "SELECT * FROM alojamientos;";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        HousingDTO housingDTO = null;
        while (resultSet.next()){
            int id_alojamiento = resultSet.getInt("id_alojamiento");
            String nombre = resultSet.getString("nombre");
            String calle = resultSet.getString("calle");
            housingDTO = new HousingDTO(id_alojamiento,nombre,calle);
            housings.add(housingDTO);
        }
        return housings;
    }

    @Override
    public boolean updateHousing(HousingDTO updatedHousing) throws SQLException {
        String sql = "UPDATE alojamientos SET  nombre = ?, calle = ? WHERE id_alojamiento = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,updatedHousing.getNombre());
        preparedStatement.setString(2,updatedHousing.getCalle());
        preparedStatement.setInt(3,updatedHousing.getId_alojamiento());
        preparedStatement = connection.prepareStatement(sql);
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected != 0;

    }

}
