package example;
import static org.testng.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

@Test
public class Schedule_appointment {
	
	public void validateLogin()
	{
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		driver.findElement(By.id("username")).sendKeys("ria1");
		driver.findElement(By.id("password")).sendKeys("Ria12345");
		driver.findElement(By.name("submit")).click();
		driver.findElement(By.xpath("//span[normalize-space()='Schedule Appointment']")).click();
		driver.findElement(By.xpath("//input[@value='Create new appointment']")).click();
		String doctorname="Smith";
		driver.findElement(By.xpath("//h4[normalize-space()='Dr."+doctorname+"']/ancestor::ul/following-sibling::button")).click();
		
		driver.switchTo().frame("myframe");
		driver.findElement(By.id("datepicker")).click();
		
		HashMap<String,String> expectedhMap = new HashMap<String,String>();
		String expectedDate = getFutureDate(20,"dd/MMMM/yyyy");
		expectedhMap.put("Date",getFutureDate(20,"MM/dd/yyyy"));
		
		//ystem.out.println(expectedhMap);
		String expectedDateArr[] = expectedDate.split("/");
		String expectedDt = expectedDateArr[0];
		String expectedMonth = expectedDateArr[1]; 
		String expectedYear = expectedDateArr[2] ;
		String randomsymtext=generateRandomText();
		 
		String actualYear = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();
		
		while(!(expectedYear.equals(actualYear)))
		{
			driver.findElement(By.xpath("//span[text()='Next']")).click();
			actualYear = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();
		}
		//System.out.println(actualYear);
		String actualMonth = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
		while(!(expectedMonth.equals(actualMonth)))
		{
			driver.findElement(By.xpath("//span[text()='Next']")).click();
			actualYear = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
		}
		
		driver.findElement(By.linkText(expectedDt)).click();
		
		Select timeSelect = new Select(driver.findElement(By.id("time")));
		timeSelect.selectByVisibleText("11Am");
		String expected_time="11am";
		
		driver.findElement(By.xpath("//button[@id='ChangeHeatName']")).click();
		driver.findElement(By.id("sym")).sendKeys(randomsymtext);
	    driver.findElement(By.xpath("//input[@value='Submit']")).click();
		
		
		
		//String expected_Date = getFutureDate(20,"MM/DD/yyyy");
		
		
		expectedhMap.put("Time","11am");
		expectedhMap.put("Doctor",doctorname);
		expectedhMap.put("Symptoms", randomsymtext);
		
		//System.out.println("expectedDate" +expected_Date);
		System.out.println("expected_time" +expected_time);
		System.out.println("expected_doctorname"+doctorname);
		System.out.println(randomsymtext);
		
		String actual_date=driver.findElement(By.xpath("//tbody/tr[1]/td[1]")).getText().trim();
		System.out.println("actual_date-" +actual_date);
		
		String actual_time=driver.findElement(By.xpath("//tbody/tr[1]/td[2]")).getText().trim();
		System.out.println("actual_time-" +actual_time);
		
		String actual_sysmptoms=driver.findElement(By.xpath("//tbody/tr[1]/td[3]")).getText().trim();
		System.out.println("actual_sysmptoms-" +actual_sysmptoms);
		
		String actual_doctor=driver.findElement(By.xpath("//tbody/tr[1]/td[4]")).getText().trim();
		System.out.println("actual_doctor-" +actual_doctor);
		
		
		
		HashMap<String,String> actualhMap = new HashMap<String,String>();
		actualhMap.put("Date",actual_date);
		actualhMap.put("Time",actual_time);
		actualhMap.put("Symptoms",actual_sysmptoms);
		actualhMap.put("Doctor", actual_doctor);
		 assertTrue(expectedhMap.equals(actualhMap));
		
		
		
	}
	public String generateRandomText()
	{
		Random r1 = new Random();
		String symptomText = "MMP Automation Team " + r1.nextInt(10000);
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
