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
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class Schedule_appointment2 {
	
	WebDriver driver;
	@Test
	public void validateScheduleAppointment() 
	{
		
		launchbrowser("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		login("ria1","Ria12345");
		navigateToAmodule("Schedule Appointment");
		String symps=generateRandomtext();
		boolean result =bookappointment("Smith",20,"10Am",symps);
		Assert.assertTrue(result);
		
	}
	@BeforeTest
	public void initdriver() 
	{

		WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	public void launchbrowser(String url) 
	{
		driver.get(url);
	}
	
	public void navigateToAmodule(String modulename) 
	{
	driver.findElement(By.xpath("//span[normalize-space()='"+modulename+"']")).click();
		
	}
	public void login(String username,String password) 
	{
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.name("submit")).click();
	}
	
	
	public boolean bookappointment( String doctorname,int noofdays,String timeofappoint,String symp)
	{
		driver.findElement(By.xpath("//input[@value='Create new appointment']")).click();
		driver.findElement(By.xpath("//h4[normalize-space()='Dr."+doctorname+"']/ancestor::ul/following-sibling::button")).click();
		
		driver.switchTo().frame("myframe");
		HashMap<String,String> expectedhMap=new HashMap<String,String>();
		
		expectedhMap.put("doctorname", doctorname);//  DOCTORS
		expectedhMap.put("sym", symp);//SYSMPTOMS
		expectedhMap.put("time", timeofappoint);//SYSMPTOMS
		expectedhMap.put("dateofAppointment", getFutureDate(noofdays,"MM/dd/yyyy"));// DATE OF APPOINTMENT
		
		String expectedDate = getFutureDate(noofdays,"dd/MMMM/yyyy");
		System.out.println("EXPECTED RESULT");
  		
  		System.out.println(timeofappoint);
  		System.out.println(symp);
  		System.out.println(doctorname);
		
		System.out.println(expectedhMap.get("dateofAppointment"));
		
		String expectedDateArr[] = expectedDate.split("/");
		String expectedDt = expectedDateArr[0];
		String expectedMonth = expectedDateArr[1]; 
		String expectedYear = expectedDateArr[2] ;
		String randomsymtext=symp;
		 
		driver.findElement(By.id("datepicker")).click();
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
		timeSelect.selectByVisibleText(timeofappoint);
		
		driver.findElement(By.xpath("//button[@id='ChangeHeatName']")).click();
		driver.findElement(By.id("sym")).sendKeys(randomsymtext);
	    driver.findElement(By.xpath("//input[@value='Submit']")).click();
	    
	  //Read the values from the Patient Portal table
	  		HashMap<String,String> actualhMap = new HashMap<String,String>();
	  		String actualDate = driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[1]")).getText().trim();
	  		actualhMap.put("dateofAppointment",actualDate);
	  		
	  		String actualtime= driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[2]")).getText().trim();
	  		actualhMap.put("time",actualtime);
	  		
	  		String actualSym= driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[3]")).getText().trim();
	  		actualhMap.put("sym",actualSym);
	  		
	  		String actualDoctor= driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[4]")).getText().trim();
	  		actualhMap.put("doctorname", actualDoctor);
	  		System.out.println("ACTUAL RESULT");
	  		System.out.println(actualDate);
	  		System.out.println(actualtime);
	  		System.out.println(actualSym);
	  		System.out.println(actualDoctor);
	  		
	  		return expectedhMap.equals(actualhMap) ;
		
		
	}
	
	private String generateRandomtext() {
		Random r1 = new Random();
		String symptomText = "MMP Automation Team " + r1.nextInt(10000);
		return symptomText;
	}
	public String getFutureDate(int days, String dateformat) 
	{
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE,days);
	 
		SimpleDateFormat sdf = new SimpleDateFormat(dateformat);
		return sdf.format(cal.getTime());
		
	}
	
	
	
	
	
	
	
}
