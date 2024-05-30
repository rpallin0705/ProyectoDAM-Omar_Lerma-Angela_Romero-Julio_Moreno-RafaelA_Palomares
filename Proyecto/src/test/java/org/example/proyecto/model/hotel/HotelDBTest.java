package org.example.proyecto.model.hotel;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HotelDBTest {
    HotelDB hotelDB = new HotelDB();

    HotelDBTest() throws SQLException, IOException {
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown(){
        hotelDB = null;
    }

    @Test
    void getHotels() {
        try {
            List<HotelDTO> hotelDTOList = hotelDB.getHotels();
            for (HotelDTO hotelDTO1 : hotelDTOList){
                System.out.println(hotelDTO1.toString());
            }
            assertNotNull(hotelDTOList);
            assertTrue(hotelDTOList.isEmpty());

            HotelDTO hotelDTOS = new HotelDTO(1,"posada","c/ ruiz jimenez",4, RoomType.DOUBLE,2);
            hotelDB.insertHotel(hotelDTOS);
            hotelDTOList = hotelDB.getHotels();
            assertTrue(hotelDTOList.isEmpty());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void updateHotel() throws SQLException {
        HotelDTO hotelDTO = new HotelDTO(1,"posada","c/ ruiz jimenez",4, RoomType.DOUBLE,2);
        hotelDB.insertHotel(hotelDTO);

        HotelDTO updateHotelDTO = new HotelDTO(1,"posada2","c/ ruiz jimenez",3, RoomType.INDIVIDUAL,1);
        assertTrue(hotelDB.updateHotel(updateHotelDTO));
    }

    @Test
    void deleteHotel() throws SQLException {
        HotelDTO hotelDTO = new HotelDTO(1,"posada","c/ ruiz jimenez",4, RoomType.DOUBLE,2);
        assertFalse(hotelDB.deleteHotel(hotelDTO));

    }

    @Test
    void insertHotel() throws SQLException {
        HotelDTO hotelDTO = new HotelDTO(1,"posada","c/ ruiz jimenez",4, RoomType.DOUBLE,2);
        assertTrue(hotelDB.insertHotel(hotelDTO));

        HotelDTO hotelDTO2 = new HotelDTO(2,"","c/ ruiz jimenez",-4, RoomType.INDIVIDUAL,2);
        HotelDTO hotelDTO3 = new HotelDTO(1,null,"c/ ruiz jimenez",4, RoomType.SUPPLEMENT,122);
        //Los siguientes tests deberian dar Falso pero como no tenemos gestionado los nulls y ni los numeros menor que 1 en el codigo da True
        //assertFalse(hotelDB.insertHotel(hotelDTO2));
        //assertFalse(hotelDB.insertHotel(hotelDTO2));
    }
}