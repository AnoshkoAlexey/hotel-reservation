package model;

import java.util.Date;

/**
 * A class that defines reservation
 *
 * @author CORP\anoshkoaleksei
 */
public class Reservation {

    private Customer customer;
    private IRoom room;
    private Date checkInDate;
    private Date checkOutDate;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Reservation \n" +
                customer.getFirstName() + " " + customer.getLastName() +
                "\nRoom: " + room.getRoomNumber() + " - " + room.getRoomType().toString() + " price per night" +
                "\nCheckin Date: " + checkInDate +
                "\nCheckout Date: " + checkOutDate;
    }

    /**
     * Set customer who does reservation
     *
     * @param customer customer who does reservation
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Get customer who did reservation
     *
     * @return customer who did reservation
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Get room that is reserved
     *
     * @return room that was reserved
     */
    public IRoom getRoom() {
        return room;
    }

    /**
     * Set room that to reserve
     *
     * @param room to be reserve
     */
    public void setRoom(IRoom room) {
        this.room = room;
    }

    /**
     * Get date when customer check in
     *
     * @return date when customer check in
     */
    public Date getCheckInDate() {
        return checkInDate;
    }

    /**
     * Set date when customer check in
     *
     * @param checkInDate date when customer check in
     */
    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    /**
     * Get date when customer check out
     *
     * @return date when customer check out
     */
    public Date getCheckOutDate() {
        return checkOutDate;
    }

    /**
     * Set date when customer check out
     *
     * @param checkOutDate date when customer check out
     */
    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
}
