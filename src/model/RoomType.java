package model;

/**
 * Types of rooms
 *
 * @author Anoshko Alexey
 *
 */
public enum RoomType {
    /**
     * Single room
     */
    SINGLE("1"),
    /**
     * Double room
     */
    DOUBLE("2");

    public final String label;

    RoomType(String label) {
        this.label = label;
    }

    /**
     * Define room type value according to label
     *
     * @param label a label that represents a room
     * @return a value of room type
     */
    public static RoomType valueOfLabel(String label) {
        for (RoomType roomType : values()) {
            if (roomType.label.equals(label)) {
                return roomType;
            }
        }
        throw new IllegalArgumentException();
    }
}
