package example;

import java.util.HashMap;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class patient_admin_module 
{
	WebDriver driver;
	@Test(priority=1)
	public void patient_messeging() 
	{
		
		launchbrowser("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		patient_login("ria1","Ria12345");
		navigateToAmodule(" Messeges ");
		String patient_messeg=generateRandomtext();
		System.out.println(patient_messeg);
		HashMap<String,String> expectedhMap = new HashMap<String,String>();
		expectedhMap.put("patient_messeg", patient_messeg);
		Patient_messege_to_doctor("Monthly visit",patient_messeg);
		
		driver.get("http://96.84.175.78/MMP-Release2-Admin-Build.2.1.000/login.php");
		Admin_login("Ben@123","Frank@123");
		String messg=Validate_patient_messeging_admin(" Messeges ");
		System.out.println(messg);
		if(messg.equals(patient_messeg)) {
			System.out.println("Test case is passed");
		}
		
		
		
		
	}
	
	 String Validate_patient_messeging_admin(String messg) 
	{
		 
		driver.findElement(By.xpath("//span[normalize-space()='Messages']")).click();
		String patient_messege=driver.findElement(By.xpath("//tbody//tr[3]/td[2]")).getText();
		
		String Patient_name=driver.findElement(By.xpath("//tbody/tr[2]/td[1]/b[1]")).getText();
		String date_of_messege=driver.findElement(By.xpath("//tbody/tr[2]/td[3]  ")).getText();
		System.out.println(date_of_messege);
		System.out.println(Patient_name);
		
		
		return patient_messege;
	}
	@BeforeTest
	public void initdriver() 
	{

		WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	/*@AfterTest
	public void tearDown()
	{
	driver.quit();
	}*/
	
	public void launchbrowser(String url) 
	{
		driver.get(url);
		
	}
	public void patient_login(String admin_username,String admin_password) 
	{
		driver.findElement(By.id("username")).sendKeys(admin_username);
		driver.findElement(By.id("password")).sendKeys(admin_password);
		driver.findElement(By.xpath("//input[@name='submit']")).click();
	}
	
	public void Admin_login(String username,String password) 
	{
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.name("admin")).click();
	}
	public String generateRandomtext() {
		Random r1 = new Random();
		String symptomText = "Patient messeges" + r1.nextInt(10000);
		return symptomText;
	}
	public void navigateToAmodule(String modulename) 
	{
	driver.findElement(By.xpath("//span[normalize-space()='Messages']")).click();
		
	}
	
	
	public void  Patient_messege_to_doctor(String reason_of_visit,String messege)
	{
		driver.findElement(By.id("subject")).sendKeys(reason_of_visit);
		driver.findElement(By.id("message")).sendKeys(messege);
		System.out.println(messege);
		driver.findElement(By.xpath("//input[contains(@value,'Send')]")).click();
		Alert myalert=driver.switchTo().alert();
		System.out.println(myalert.getText());
		myalert.accept();
		//Assert.assertTrue(true);
		
		
	}
	

}
