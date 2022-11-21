package model;

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


    public Room(String roomNumber, Double price, RoomType enumeration) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }

    public Room() {

    }

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

    @Override
    public boolean isFree() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Room Number: " + roomNumber + " " + enumeration.toString() + " bed" +
                "Room Price: $" + price;

    }
}
