package model;

/**
 * A class used to define a free room
 *
 * @author CORP\anoshkoaleksei
 */
public class FreeRoom extends Room{

    public FreeRoom(String roomNumber, RoomType enumeration) {
        super(roomNumber, 0.0, enumeration);
    }

    /**
     * Changes default representation of the instance
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
