package org.example.proyecto.model.booking;

import java.sql.SQLException;
import java.util.List;

public interface BookingDAO {
    List<BookingDTO> getBookings() throws SQLException;
    boolean insertBooking(BookingDTO newBooking) throws SQLException;
    boolean deleteBookingByID(String BookingEmailDelete) throws SQLException;
    boolean updateBooking(BookingDTO updatedBooking) throws SQLException;
}
