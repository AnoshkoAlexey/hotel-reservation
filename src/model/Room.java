package model;

import java.util.Objects;

/**
* A class used to implement IRoom interface,
* that keeps information about a room
*
* @author CORP\anoshkoaleksei
* */
public class Room implements IRoom {
    private String roomNumber;
    private Double price;
    private RoomType enumeration;

    /**
     * A constructor for a room object with all parameters
     *
     * @param roomNumber the room's number
     * @param price the room's price
     * @param enumeration the room's type
     */
    public Room(String roomNumber, Double price, RoomType enumeration) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }

    /**
     * Default constructor for a room object
     */
    public Room() {}

    /**
     * {@inheritDoc}
     **/
    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * Set room's number
     *
     * @param roomNumber number of the room
     */
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double getRoomPrice() {
        return price;
    }

    /**
     * Set room's price
     *
     * @param price price of the room per night
     */
    public void setRoomPrice(Double price) {
        this.price = price;
    }

    /**
     * {@inheritDoc}
     **/
    @Override
    public RoomType getRoomType() {
        return enumeration;
    }

    /**
     * Set room'type
     *
     * @param roomType Type of the room
     **/
    public void setRoomType(RoomType roomType) {
        this.enumeration = roomType;
    }

    /**
     * Check if the room is free
     *
     * @return true if the room is free, false otherwise
     */
    @Override
    public boolean isFree() {
        return !Objects.isNull(price) && price.equals(0.0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Room Number: " + roomNumber + " " + enumeration.toString() + " bed" +
                "Room Price: $" + price;

    }

    /**
     * {@inheritDoc}
     *
     * Define new rule according to which two rooms compare
     * with each other using room number attribute
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {

        if (object == this) {
            return true;
        }

        if (!(object instanceof Room room)) {
            return false;
        }

        return Objects.equals(this.roomNumber, room.roomNumber);
    }

    /**
     * {@inheritDoc}
     *
     * Define  hash code of the room, using room number
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }
}
