import java.lang.Exception;
/** Thrown when a non-existing room is found in rooms.txt */
public class NonExistingRoomException extends NullPointerException {
    public NonExistingRoomException() {
	super();
    }
    public NonExistingRoomException(String message) {
	super(message);
    }
}
