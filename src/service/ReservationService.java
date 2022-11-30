package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A class that allows to manage a collection of reservations
 *
 * @author CORP\anoshkoaleksei
 */
public class ReservationService {

    /** Keeps collection of all reservations */
    private final Map<String, Collection<Reservation>> reservations;

    /** Keep collection of all rooms */
    private final Map<String, IRoom> rooms;
    private static final ReservationService SINGLETON = new ReservationService();

    private static final int ALTERNATIVE_DATES_PERIOD_DAYS = 7;

    /**
     * Default constructor for the reservation service
     */
    private ReservationService() {
        reservations = new HashMap<>();
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
     * @param roomNumber number of the room
     * @return a room with required number
     */
    public IRoom getARoom(String roomNumber) {
        return rooms.get(roomNumber);
    }

    /**
     * Retun all rooms entered to the system
     *
     * @return the collection of rooms
     */
    public Collection<IRoom> getAllRooms() {
        return rooms.values();
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

        Reservation reservation = new Reservation(customer, room, checkInDate, checkInDate);

        Collection<Reservation> customerReservations = getCustomersReservation(customer);

        if (Objects.isNull(customerReservations)) {
            customerReservations = new LinkedList<>();
        }

        customerReservations.add(reservation);
        reservations.put(customer.getEmail(), customerReservations);

        return reservation;
    }

    /**
     * Return a collection of available rooms in interval between two dates
     *
     * @param checkInDate date when reservation start
     * @param checkOutDate date when reservation end
     * @return a collection of free rooms in interval between two dates
     */
    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        return findAvailableRooms(checkInDate, checkOutDate);
    }

    /**
     * Looks for available rooms in a defined period
     *
     * @param checkInDate begin of reservation
     * @param checkOutDate end of reservation
     * @return collection of rooms available in this period
     */
    private Collection<IRoom> findAvailableRooms(Date checkInDate, Date checkOutDate) {

        Collection<Reservation> allReservations = getAllReservations();
        Collection<IRoom> busyRooms = new LinkedList<>();

        allReservations.forEach(reservation -> {
            if (checkInDate.before(reservation.getCheckOutDate()) &&
                    checkOutDate.after(reservation.getCheckInDate())) {
                busyRooms.add(reservation.getRoom());
            }
        });

        return rooms
                .values()
                .stream()
                .filter(room -> busyRooms.stream().noneMatch(busyRoom -> busyRooms.equals(room)))
                .collect(Collectors.toList());
    }

    /**
     * Looks for alternative rooms in a defined period
     *
     * @param checkInDate start of reservation
     * @param checkOutDate end of reservation
     * @return collection of free rooms in a defined period
     */
    public Collection<IRoom> findAlternativeRooms(Date checkInDate, Date checkOutDate) {
        return findAvailableRooms(addDays(checkInDate), addDays(checkInDate));
    }

    /**
     * Get a collection of all reservations
     *
     * @return collection of all reservations
     */
    private Collection<Reservation> getAllReservations() {

        Collection<Reservation> allReservations = new LinkedList<>();
        reservations.values().forEach(allReservations::addAll);
        return allReservations;
    }


    /**
     * Return a collection of reservations made by a customer
     *
     * @param customer the customer who made reservations
     * @return A collection of reservations made by the customer
     */
    public Collection<Reservation> getCustomersReservation(Customer customer) {
        return reservations.get(customer.getEmail());
    }

    /**
     * Display all reservations
     */
    public void printAllReservation() {

        Collection<Reservation> reservations = getAllReservations();

        if (reservations.isEmpty()) {
            System.out.println("No reservations found");
        } else {
            reservations.forEach(System.out::println);
        }
    }

    /**
     * Add default number of days to the date
     *
     * @param date an original date
     * @return a date with default number of days added
     */
    public Date addDays(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, ALTERNATIVE_DATES_PERIOD_DAYS);

        return calendar.getTime();
    }
}