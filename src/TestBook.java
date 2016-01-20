import org.junit.*;
import junit.framework.TestCase;

public class TestBook extends TestCase
{
    private Book book;


    protected void setUp() {
	this.book = new Book("TestBook", "TestAuthor", 42, 1.0);
    }


    @Test
    public void testGetName () {
	assertEquals("TestBook", book.getName());
    }

    
    @Test
    public void testToString () {
	assertEquals("'TestBook' (book)", book.toString());
    }

}
