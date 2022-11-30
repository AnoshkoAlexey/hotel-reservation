package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

/**
 * An API for the service classes
 *
 * @author CORP\anoshkoaleksei
 */
public class AdminResource {

    private final CustomerService customerService;
    private final ReservationService reservationService;

    private static final AdminResource SINGLETON  = new AdminResource();

    /**
     * Default constructor
     */
    private AdminResource() {
        customerService = CustomerService.getSingleton();
        reservationService = ReservationService.getSingleton();
    }

    /**
     * Returns singleton implementation of the class
     *
     * @return singleton
     */
    public static AdminResource getSingleton() {
        return SINGLETON;
    }

    /**
     * Returns a customer with the required email
     *
     * @param email the customer's email
     * @return the customer with the required email
     */
    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    /**
     * Add collection of rooms to the collection of rooms
     *
     * @param rooms a collection of rooms
     */
    public void addRoom(List<IRoom> rooms) {
        rooms.forEach(reservationService::addRoom);
    }

    /**
     * Return all rooms registered in the system
     *
     * @return a collection of rooms registered in the system
     */
    public Collection<IRoom> getAllRooms() {
        return reservationService.getAllRooms();
    }

    /**
     * Return a collection of all customers
     *
     * @return a collection of all customers
     */
    public Collection<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    /**
     * Displays all reservations made in the system
     */
    public void displayAllReservations() {
        reservationService.printAllReservation();
    }
}
