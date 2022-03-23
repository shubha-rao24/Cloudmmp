package org.iit.cloud.mmp.patient;
import java.util.Random;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import HelperClass.AppLibrary;
import HelperClass.HelperClass;
import io.github.bonigarcia.wdm.WebDriverManager;

public class US_002_Messege 
{
	WebDriver driver;
	@Test(description="TC_002 Validate the the send functionality in patient midule")
	public void Validate_messeg() 
	{
		
		launchbrowser("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		
		HelperClass HelperClassobj=new HelperClass(driver);
		HelperClassobj.login("ria1","Ria12345");
		navigateToAmodule(" Messeges ");
		String contact_reason;
		
			contact_reason = "Reason"+AppLibrary.generateRandomText();
		
		String subject;
		
			subject = "subject"+AppLibrary.generateRandomText();
		
		
		String expected="Messages Successfully sent";
		String  result=sendmesssege(contact_reason,subject);
		Assert.assertEquals(expected, result);
		driver.get("http://96.84.175.78/MMP-Release2-Admin-Build.2.1.000/login.php");
		//HelperClass HelperClassobj=new HelperClass(driver);
		//HelperClassobj.login("Ben@123","Frank@123");
		String messg=Validate_patient_messeging_admin(" Messeges ");
		System.out.println(messg);
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
	
	
	public void navigateToAmodule(String modulename) 
	{
	driver.findElement(By.xpath("//span[normalize-space()='Messages']")).click();
		
	}
	
	
	public String sendmesssege(String reason,String messege)
	{
		driver.findElement(By.id("subject")).sendKeys(reason);
		driver.findElement(By.id("message")).sendKeys(messege);
		System.out.println(messege);
		driver.findElement(By.xpath("//input[contains(@value,'Send')]")).click();
		Alert myalert=driver.switchTo().alert();
		String text=myalert.getText();
		myalert.accept();
		//Assert.assertTrue(true);
		return text;
		
		
	}
	

}
