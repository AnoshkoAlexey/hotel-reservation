package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.*;

/**
 * An API class that gives access to customers, rooms and reservations
 *
 * @author CORP\anoshkoaleksei
 */

public class HotelResource {

    private final CustomerService customerService;
    private final ReservationService reservationService;
    private static final HotelResource SINGLETON = new HotelResource();

    /**
     * Define default constructor
     */
    private HotelResource() {
        customerService = CustomerService.getSingleton();
        reservationService = ReservationService.getSingleton();
    }

    /**
     * Return singleton of the class
     *
     * @return
     */
    public static HotelResource getSingleton() {
        return SINGLETON;
    }

    /**
     * Find a customer with the required email
     *
     * @param email customer's email
     * @return a customer with the required email
     */
    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    /**
     * Create a customer using entry parameters
     *
     * @param email customer's email
     * @param firstName customer's first name
     * @param lastName customer's last name
     */
    public void createCustomer(String email, String firstName, String lastName) {
        customerService.addCustomer(email, firstName, lastName);
    }

    /**
     * Find a room with a required number
     *
     * @param roomNumber the room's number
     * @return room with a required number or null otherwise
     */
    public IRoom getRoom(String roomNumber) {
        return reservationService.getARoom(roomNumber);
    }

    /**
     * Book a room using entry parameters
     *
     * @param customerEmail a customer's email
     * @param room a room to book
     * @param checkInDate  first date of reservation
     * @param checkOutDate last date of reservation
     * @return reservation's reference
     */
    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        Customer customer = customerService.getCustomer(customerEmail);
        return reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
    }

    /**
     * Return customer's reservations
     *
     * @param customerEmail customer's email
     * @return collection of the customer's reservations
     */
    public Collection<Reservation> getCustomerReservations(String customerEmail) {

        Customer customer = customerService.getCustomer(customerEmail);

        return Objects.isNull(customer) ? Collections.emptyList() : reservationService.getCustomersReservation(customer);
    }

    /**
     * Look for a room within required dates
     *
     * @param checkIn first date of reservation
     * @param checkOut last date of reservation
     * @return a collection of rooms available in requested time interval
     */
    public Collection<IRoom> findARoom(Date checkIn, Date checkOut) {
        return reservationService.findRooms(checkIn, checkOut);

    }

    /**
     * Look for a room within alternative period
     *
     * @param checkIn original begin of reservation
     * @param checkOut original end of reservation
     *
     * @return a collection of rooms available in alternative time interval
     */
    public Collection<IRoom> findAlternativeRooms(Date checkIn, Date checkOut) {
        return reservationService.findAlternativeRooms(checkIn, checkOut);
    }

    /**
     * Add days to a date
     *
     * @param date original date
     * @return a date after addition of necessary number of days
     */
    public Date addDays(Date date) {
        return reservationService.addDays(date);
    }
}
