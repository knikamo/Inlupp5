import java.lang.Exception;
/** File is found but it's empty */
public class EmptyFileException extends Exception {
	
    public EmptyFileException() {
	super();
    }
    public EmptyFileException(String message) {
	super(message);
    }
}	
