package org.iit.cloud.mmp.admin;
import java.util.HashMap;
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

public class Messege 
{
	WebDriver driver;
	@Test(description="TC_002 Validate the the send functionality in patient midule")
	public void Validate_messeg() 
	{
		
		
		launchbrowser("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		HelperClass HelperClassobj=new HelperClass(driver);
		HelperClassobj.login("ria1","Ria12345");
		navigateToAmodule("Messages"); 	 
		
		String contact_reason="Reason"+AppLibrary.generateRandomText();
		String subject="subject"+AppLibrary.generateRandomText();
		String currentDate=AppLibrary.getFutureDate(0, "dd-MM-yyyy");
		sendmesssege(contact_reason,subject);
		String patientname=fetchPatientName();
		launchbrowser("http://96.84.175.78/MMP-Release2-Admin-Build.2.1.000/login.php");
		HelperClassobj.login("Ben@123","Frank@123");
		
		
		HashMap<String,String>expectedhMap = new HashMap<String,String>();
		expectedhMap.put("Reason",contact_reason);
		expectedhMap.put("subject",subject);
		expectedhMap.put("Date", currentDate);
		expectedhMap.put("PatientName", patientname);
		
		HashMap<String, String> actualHMap=fetchMessegeDetails(patientname, subject, currentDate, contact_reason);
		Assert.assertEquals(actualHMap, expectedhMap);
		
	}
	public String fetchPatientName()
	{
		navigateToAmodule("Profile");
		String patientname=driver.findElement(By.id("fname")).getAttribute("value");
		return patientname;
		
	}
	
	HashMap<String, String>  fetchMessegeDetails(String patientname,String subject,String currentDate, String reason) 
	{
		
		driver.findElement(By.xpath("//span[normalize-space()='Messages']")).click();
		
		HashMap<String,String>actualHMap = new HashMap<String,String>();
		
		/*String patient_messege=driver.findElement(By.xpath("//tbody//tr[3]/td[2]")).getText();
		actualhMap.put("Reason", patient_messege);
		
		String Patient_name=driver.findElement(By.xpath("//tbody/tr[2]/td[1]/b[1]")).getText();
		actualhMap.put("patientname", patientname);
		
		String Patient_name=driver.findElement(By.xpath("//tbody/tr[2]/td[1]/b[1]")).getText();
		actualhMap.put("patientname", patientname);
		
		String Patient_name=driver.findElement(By.xpath("//tbody/tr[2]/td[1]/b[1]")).getText();
		actualhMap.put("patientname", patientname);*/
		actualHMap.put("patientName", driver.findElement(By.xpath("(//b[contains(text(),'"+patientname+"')])[1]")).getText());
		actualHMap.put("reason", 	driver.findElement(By.xpath("(//b[contains(text(),'"+reason+"')])[1]")).getText());
		actualHMap.put("subject", driver.findElement(By.xpath("//tbody//tr[3]/td[2]")).getText());
		actualHMap.put("date", driver.findElement(By.xpath("//b[normalize-space()='"+currentDate+"']")).getText().trim());
		 

		
		return actualHMap;
		
		
		
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
	
	
	public void navigateToAmodule(String moduleName)
	{
		driver.findElement(By.xpath("//span[normalize-space()='"+moduleName+"']")).click();

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
