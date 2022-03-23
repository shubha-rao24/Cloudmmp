package org.iit.cloud.mmp.patient;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import HelperClass.HelperClass;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Edit_Patientprofile {
	WebDriver driver;
	@Test
	public void Edit_patient()
	{

		launchbrowser("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		HelperClass HelperClassobj=new HelperClass(driver);
		HelperClassobj.login("ria1","Ria12345");
		navigateToAmodule("Profile"); 	
		Edit_profile("110");
    }	

	void Edit_profile(String weight)

	{
		driver.findElement(By.xpath("//input[@id='Ebtn']")).click();//click  edit button

		HashMap<String,String>before_edit = new HashMap<String,String>();

//accessing the previuusly entered profile info/// and storing hash map
		before_edit.put("First Name",driver.findElement(By.xpath("//table//tbody//tr[2]//td[1]")).getAccessibleName());
		before_edit.put("Last Name", driver.findElement(By.xpath("//table//tbody//tr[2]//td[2]")).getAccessibleName());

		before_edit.put("Licence",driver.findElement(By.xpath("//table//tbody//tr[4]//td[1]")).getAccessibleName());
		before_edit.put("SSN", driver.findElement(By.xpath("//table//tbody//tr[4]//td[2]")).getAccessibleName());

		before_edit.put("Address", driver.findElement(By.xpath("//table//tbody//tr[6]//td[1]")).getAccessibleName());
		before_edit.put("Age", driver.findElement(By.xpath("//table//tbody//tr[6]//td[2]")).getAccessibleName());

		before_edit.put("Weight",driver.findElement(By.xpath("//table//tbody//tr[8]//td[1]")).getAccessibleName());
		before_edit.put("Height",driver.findElement(By.xpath("//table//tbody//tr[8]//td[2]")).getAccessibleName());

		before_edit.put("City", driver.findElement(By.xpath("//table//tbody//tr[10]//td[1]")).getAccessibleName());

		before_edit.put("State", driver.findElement(By.xpath("//table//tbody//tr[12]//td[1]")).getAccessibleName());
		before_edit.put("ZipCode", driver.findElement(By.xpath("//table//tbody//tr[12]//td[2]")).getAccessibleName());

		before_edit.put("Provider Information",driver.findElement(By.xpath("//table//tbody//tr[14]//td[1]")).getAccessibleName());
		before_edit.put("Insurance Information",driver.findElement(By.xpath("//table//tbody//tr[14]//td[2]")).getAccessibleName());
		System.out.println(before_edit);

		driver.findElement(By.id("weight")).clear();//cler the previous data
		driver.findElement(By.id("weight")).sendKeys(weight);//enterb th weight

		driver.findElement(By.xpath("//input[@id='Sbtn']")).click();//save button
		Alert messeg=driver.switchTo().alert();//switch to alert
		System.out.println(messeg.getText());//alert messege
		messeg.accept();//accept the alert ok
		driver.findElement(By.xpath("//input[@id='Ebtn']")).click();//again accessing the profile tocheck the updation

		HashMap<String,String>after_edit = new HashMap<String,String>();

//accessing the profile info after the updation and storing it in hash map//
		
		after_edit.put("First Name",driver.findElement(By.xpath("//table//tbody//tr[2]//td[1]")).getAccessibleName());
		after_edit.put("Last Name", driver.findElement(By.xpath("//table//tbody//tr[2]//td[2]")).getAccessibleName());

		after_edit.put("Licence",driver.findElement(By.xpath("//table//tbody//tr[4]//td[1]")).getAccessibleName());
		after_edit.put("SSN", driver.findElement(By.xpath("//table//tbody//tr[4]//td[2]")).getAccessibleName());

		after_edit.put("Address", driver.findElement(By.xpath("//table//tbody//tr[6]//td[1]")).getAccessibleName());
		after_edit.put("Age", driver.findElement(By.xpath("//table//tbody//tr[6]//td[2]")).getAccessibleName());

		after_edit.put("Weight",driver.findElement(By.xpath("//table//tbody//tr[8]//td[1]")).getAccessibleName());
		after_edit.put("Height",driver.findElement(By.xpath("//table//tbody//tr[8]//td[2]")).getAccessibleName());

		after_edit.put("City", driver.findElement(By.xpath("//table//tbody//tr[10]//td[1]")).getAccessibleName());

		after_edit.put("State", driver.findElement(By.xpath("//table//tbody//tr[12]//td[1]")).getAccessibleName());
		after_edit.put("ZipCode", driver.findElement(By.xpath("//table//tbody//tr[12]//td[2]")).getAccessibleName());

		after_edit.put("Provider Information",driver.findElement(By.xpath("//table//tbody//tr[14]//td[1]")).getAccessibleName());
		after_edit.put("Insurance Information",driver.findElement(By.xpath("//table//tbody//tr[14]//td[2]")).getAccessibleName());
		System.out.println(after_edit);
		Assert.assertEquals(after_edit, before_edit);//comparing 2 hashmap
		
		/*if(after_edit.get(weight)!=before_edit.get(weight))
		{
			Assert.assertEquals(after_edit, before_edit);
		}
		else
			Assert.assertEquals(after_edit, before_edit);*/

		
	}
	void Get_patient_profile() {
		driver.findElement(By.xpath("//input[@id='Ebtn']")).click();

		List<WebElement> patient_details=driver.findElements(By.xpath("//table//tbody//tr//td//input[@class='form-control form-cascade-control nav-input-search']"));
		System.out.println(patient_details.size());int i=0,j=0;
		int length=patient_details.size();
		System.out.println(length);//
		//String ele=driver.findElement(By.xpath("//table//tbody//tr[10]//td[2]")).getText();
		//System.out.print(ele);
		for(i=2;i<=length;i++)  //   //table[@class='table']//tr["+i+"]//td["+j+"]
		{
			for(j=1;j<=2;j++)

			{

				if((i==10)&&(j==2))

				{

					System.out.print("bLANK SPACE");
				}



				else {
					String text=driver.findElement(By.xpath("//table//tbody//tr["+i+"]//td["+j+"]")).getAccessibleName();

					System.out.print(text+"     ");
				}

			}

			System.out.println();	

		}


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
}
