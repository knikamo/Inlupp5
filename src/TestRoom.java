import org.junit.*;
import junit.framework.TestCase;

public class TestRoom extends TestCase
{
    private Room r;


    protected void setUp() {
	Boolean[] openDoors = {false, true, false, true};
	this.r = new Room("TestRoom", openDoors);
    }


    @Test
    public void testAddToRoomAndRemoveItem() {
	assertNull(r.hasKey());
	assertNull(r.hasBook("TestBook"));

	r.addToRoom(new Key());
	r.addToRoom(new Book("TestBook", "TestAuthoer", 42, 1.0));

	assertNotNull(r.hasKey());
	assertNotNull(r.hasBook("TestBook"));

	assertTrue(r.removeItem(r.hasKey()));
	assertTrue(r.removeItem(r.hasBook("TestBook")));
    }
}
