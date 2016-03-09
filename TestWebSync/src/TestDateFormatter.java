import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDateFormatter {

	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {

		// String orig = "October   2014";
		//String orig = "September 2014";
		//String orig = "August    2014";
		String orig = "February  2014";

		DateFormat df = new SimpleDateFormat("MMMM yyyy");
		Date date = df.parse(orig);
		System.out.println(date);

		String format = String.format("%1$-9tB %1$tY", date);
		System.out.println(format);
		System.out.println(format.equals(orig));
	}
}
