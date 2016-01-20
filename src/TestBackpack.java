import org.junit.*;
import junit.framework.TestCase;

public class TestBackpack extends TestCase
{
    private Backpack bp;


    protected void setUp() {
	this.bp = new Backpack();
    }


    @Test
    public void testAddToBackpackAndDropFromBackpack() {
	assertNull(bp.hasKey());
	assertNull(bp.hasBook("TestBook"));

	assertTrue(bp.addToBackpack(new Key()));
	assertTrue(bp.addToBackpack(new Book("TestBook", "TestAuthor", 42, 1.0)));

	assertNotNull(bp.hasKey());
	assertNotNull(bp.hasBook("TestBook"));
	
	assertTrue(bp.dropFromBackpack(bp.hasKey()));
	assertTrue(bp.dropFromBackpack(bp.hasBook("TestBook")));
    }

}
