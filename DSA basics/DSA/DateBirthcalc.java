import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DateBirthcalc {
	
	public static void main(String[] args) throws ParseException  {
		Scanner sc = new Scanner(System.in);
		 System.out.println("Enter your date of birth (format xxxx-xx-xx):");
		 String birthday = sc.next();//input string, date of birth
		
		 Date d1 = new Date();//Current system time
		 long day1 = 24 * 60 * 60 * 1000;//The millisecond value of a day
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 Date d2 = sdf.parse(birthday);//Convert a string to a date object
		
		 //d1.getTime() converted to millisecond value
		if(d1.getTime() % day1 == 0) {
			System.out.println((d1.getTime() / day1)-(d2.getTime() / day1));
		}else {
			System.out.println((d1.getTime() / day1 + 1)-(d2.getTime() / day1));
		}


}
}