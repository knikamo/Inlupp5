import java.lang.Exception;

public class EmptyFileException extends Exception {
	
	public EmptyFileException() {
		super();
		}
	public EmptyFileException(String message) {
		super(message);
		}
}	