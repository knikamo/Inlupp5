import org.junit.*;
import junit.framework.TestCase;

public class AvatarTest extends TestCase
{
    private Avatar avatar;


    protected void setUp() {
	this.avatar = new Avatar("TestAvatar");
    }


    @Test
    public void testToString() {
	assertEquals("TestAvatar (you)", avatar.toString());
    }


    @Test 
    public void testCompletedCourses() {
	assertFalse(avatar.completedCourse("TestCourse"));
	assertEquals(0, avatar.getHp());

	avatar.addCompleted("TestCourse", 42);
	assertTrue(avatar.completedCourse("TestCourse"));
	assertEquals(42, avatar.getHp());

	avatar.removeCompleted("TestCourse", -42);
	assertFalse(avatar.completedCourse("TestCourse"));
	assertEquals(0, avatar.getHp());
    }



    @Test 
    public void testOngoingCourses() {
	assertFalse(avatar.ongoingCourse("TestCourse"));

	avatar.addOngoing("TestCourse");
	assertTrue(avatar.ongoingCourse("TestCourse"));

	avatar.removeOngoing("TestCourse");
	assertFalse(avatar.ongoingCourse("TestCourse"));
    }



    @Test 
    public void testHasKeyAndDrop() {
	assertNotNull(avatar.hasKey());

	assertTrue(avatar.drop(avatar.hasKey()));

	assertNull(avatar.hasKey());
    }



    @Test 
    public void testHasBookAndPickUp() {
	assertNull(avatar.hasBook("TestBook"));

	assertTrue(avatar.pickUp(new Book("TestBook", "TestAuthor", 42, 1.0)));

	assertNotNull(avatar.hasBook("TestBook"));
    }


    @Test 
    public void testReadyForExam() {
	assertFalse(avatar.readyForExam());

	// A test function that gives the avatar 180 hp
	avatar.cheat();
	
	assertTrue(avatar.readyForExam());
	
    }
}
