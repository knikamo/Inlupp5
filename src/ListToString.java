import java.util.ArrayList;
import java.lang.IndexOutOfBoundsException;

public interface ListToString {
		
		String arrListToString(ArrayList arrList) throws IndexOutOfBoundsException; /*{
		String s;

		Object current = arrList.get(0);
		
		s = current.toString();	

		for (int i = 1; i < arrList.size(); i++) {
			s += ", ";
			current = arrList.get(i);
			s += current.toString(); 
		}
		return s;
	} */

}