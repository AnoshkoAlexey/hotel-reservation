package unit.model;

import model.FreeRoom;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FreeRoomTest {

    @Test
    void testToString() {
    }

    @Test
    public void constructorTest() {
        FreeRoom freeRoom = new FreeRoom();
        assertEquals(freeRoom.getRoomPrice(), 0.0);
    }
}