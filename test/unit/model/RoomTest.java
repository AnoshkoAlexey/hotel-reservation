package unit.model;

import model.Room;
import model.RoomType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    void getRoomNumber() {
      Room room = new Room();
      room.setRoomNumber("100");
      assertSame(room.getRoomNumber(), "100");
    }

    @Test
    void setRoomNumber() {
        Room room = new Room();
        room.setRoomNumber("100");
        assertSame(room.getRoomNumber(), "100");
    }

    @Test
    void getRoomPrice() {
        Room room = new Room();
        room.setRoomPrice(2.5);
        assertEquals(room.getRoomPrice(), 2.5);
    }

    @Test
    void setRoomPrice() {
        Room room = new Room();
        room.setRoomPrice(2.5);
        assertEquals(room.getRoomPrice(), 2.5);
    }

    @Test
    void getRoomType() {
        Room room = new Room();
        room.setRoomType(RoomType.DOUBLE);
        assertEquals(room.getRoomType(), RoomType.DOUBLE);
    }

    @Test
    void setRoomType() {
        Room room = new Room();
        room.setRoomType(RoomType.DOUBLE);
        assertEquals(room.getRoomType(), RoomType.DOUBLE);
    }

    @Test
    void isFree() {
    }

    @Test
    void testToString() {
    }
}