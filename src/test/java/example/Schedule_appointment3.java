package example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Schedule_appointment3
{
WebDriver driver;
@Test
public void Validate_appointment() 
         {
	launchbrowser("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
	login("ria1","Ria12345");
	naviagateTomodule("Schedule Appointment");
	book_appointment("Alexander",20,"12Pm","symptom");
         }


	@BeforeTest
  public void initiatedriver() 
	{
	WebDriverManager.chromedriver().setup();
	driver=new ChromeDriver();
	driver.manage().window().maximize();
    }
	public void launchbrowser(String url) 
	{
		driver.get(url);
    }
	public void login(String username,String password)
	{
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.name("submit")).click();
    }
	public void naviagateTomodule(String schedule_appointment) 
	{
		driver.findElement(By.xpath("//li//a[@class='dropdown']")).click();
		
    }
	void book_appointment(String doctor_name,int no_ofdays,String time,String symp)
	{
driver.findElement(By.xpath("//input[@value='Create new appointment']")).click();// create appointment click
driver.findElement(By.xpath("//li//a[@class='dropdown']")).click();	
    }
	
	
	
	
}
