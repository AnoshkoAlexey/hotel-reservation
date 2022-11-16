package model;

/**
 * Room interface
 *
 * @author CORP\anoshkoaleksei
 */
public interface IRoom {

    /**
     * Provide the room number
     *
     * @return number of the room
     */
    String getRoomNumber();

    /**
     * Provide the room price
     *
     * @return price of the room
     */
     Double getRoomPrice();

    /**
     * Provide the room type
     *
     * @return type of the room
     */
     RoomType getRoomType();

    /**
     * Check if the room is free
     *
     * @return state of the room
     */
     boolean isFree();
}
