import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		String text = "473356800000";
		d.setTime(Long.parseLong(text));
		System.out.println(d.toString());
	}

}
