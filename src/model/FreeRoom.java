package model;

/**
 * A class used to define a free room
 *
 * @author CORP\anoshkoaleksei
 */
public class FreeRoom extends Room{
    public FreeRoom() {
        this.setRoomPrice(0.0);
    }

    /**
     * Changes default representation of the instance
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
