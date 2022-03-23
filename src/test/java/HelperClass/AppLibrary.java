package HelperClass;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class AppLibrary 
{
	
	public static String generateRandomText()
	{
		Random r1 = new Random();
		String symptomText = "Feeling feverish " + r1.nextInt(10000);
		return symptomText;
	}

	public static String getFutureDate(int days,String format)
	{
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE,days);
	 
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(cal.getTime());
		
	}

}
