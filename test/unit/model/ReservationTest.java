package unit.model;

import model.Customer;
import model.Reservation;
import model.Room;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {

    @Test
    void testToString() {
    }

    @Test
    void setCustomer() {
        Reservation reservation = new Reservation();
        Customer customer = new Customer();
        reservation.setCustomer(customer);
        assertEquals(customer, reservation.getCustomer());
    }

    @Test
    void getCustomer() {
        Reservation reservation = new Reservation();
        Customer customer = new Customer();
        reservation.setCustomer(customer);
        assertEquals(customer, reservation.getCustomer());
    }

    @Test
    void getRoom() {
        Reservation reservation = new Reservation();
        Room room = new Room();
        reservation.setRoom(room);
        assertEquals(room, reservation.getRoom());
    }

    @Test
    void setRoom() {
        Reservation reservation = new Reservation();
        Room room = new Room();
        reservation.setRoom(room);
        assertEquals(room, reservation.getRoom());
    }

    @Test
    void getCheckInDate() {
        Reservation reservation = new Reservation();
        Date date = new Date();
        reservation.setCheckInDate(date);
        assertEquals(date, reservation.getCheckInDate());
    }

    @Test
    void setCheckInDate() {
        Reservation reservation = new Reservation();
        Date date = new Date();
        reservation.setCheckInDate(date);
        assertEquals(date, reservation.getCheckInDate());
    }

    @Test
    void getCheckOutDate() {
        Reservation reservation = new Reservation();
        Date date = new Date();
        reservation.setCheckOutDate(date);
        assertEquals(date, reservation.getCheckOutDate());
    }

    @Test
    void setCheckOutDate() {
        Reservation reservation = new Reservation();
        Date date = new Date();
        reservation.setCheckOutDate(date);
        assertEquals(date, reservation.getCheckOutDate());
    }
}