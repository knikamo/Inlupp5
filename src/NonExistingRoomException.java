import java.lang.Exception;

public class NonExistingRoomException extends NullPointerException {
	public NonExistingRoomException() {
		super();
	}
	public NonExistingRoomException(String message) {
		super(message);
	}
}