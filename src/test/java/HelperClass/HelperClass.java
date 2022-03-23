package HelperClass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperClass 
{
	WebDriver driver;
	public	HelperClass(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void login(String admin_username,String admin_password) 
	{
		driver.findElement(By.id("username")).sendKeys(admin_username);
		driver.findElement(By.id("password")).sendKeys(admin_password);
		driver.findElement(By.xpath("//input[@value='Sign In']")).click();
	}

}
