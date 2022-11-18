package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;

import java.util.*;

/**
 * A class that allows to manage a collection of reservations
 *
 * @author CORP\anoshkoaleksei
 */
public class ReservationService {

    /** Keeps collection of all reservations */
    private final Set<Reservation> reservations;

    /** Keep collection of all rooms */
    private final Map<String, IRoom> rooms;

    private static final ReservationService SINGLETON = new ReservationService();

    private ReservationService() {
        reservations = new HashSet<>();
        rooms = new HashMap<>();
    }

    /**
     * Get singleton reference of CustomerService class
     *
     * @return instance of CustomerService class
     */
    public static ReservationService getSingleton() {
        return SINGLETON;
    }

    /**
     * Add a room to the collection of rooms
     *
     * @param room A room's kind class that implement IRoom interface
     */
    public void addRoom(IRoom room) {
        rooms.put(room.getRoomNumber(), room);
    }

    /**
     * Look for a room with required number
     *
     * @param roomId number of the room
     * @return a room with required number
     */
    public IRoom getARoom(String roomId) {
        return rooms.get(roomId);
    }

    /**
     * Reserve a room for the customer in required interval
     *
     * @param customer the customer who reserves a room
     * @param room the room instance that customer wants to reserve
     * @param checkInDate first date of reservation
     * @param checkOutDate last date of reservation
     *
     * @return a reference for the reservation
     */
    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation reservation = new Reservation();
        reservation.setCustomer(customer);
        reservation.setRoom(room);
        reservation.setCheckInDate(checkInDate);
        reservation.setCheckOutDate(checkOutDate);
        reservations.add(reservation);
        return reservation;
    }

    /**
     * Return a collection of free rooms in interval between two dates
     *
     * @param checkInDate date when reservation start
     * @param checkOutDate date when reservation end
     * @return a collection of free rooms in interval between two dates
     */
     //TODO implementation of the method
    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        return rooms.values();
    }

    /**
     * Returns collection of reservations made by the customer
     *
     * @param customer the customer who made reservations
     * @return A collcection of reservations made by the customer
     */
    //TODO implementation of the method
    public Collection<Reservation> getCustomersReservation(Customer customer) {
        return reservations;
    }

    /**
     * Print all reservations
     */
    public void printAllReservation() {
        for (Reservation reservation : reservations) {
            System.out.println(reservation.toString());
        }
    }
}
