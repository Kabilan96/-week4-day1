package week4.day1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		//Launch URL "http://leaftaps.com/opentaps/control/login"
		driver.get("http://leaftaps.com/opentaps/control/login");
		//Enter UserName and Password Using Id Locator
		driver.findElement(By.id("username")).sendKeys("demosalesmanager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		//Click on Login Button using Class Locator
		driver.findElement(By.className("decorativeSubmit")).click();
		//Click on CRM/SFA Link
		driver.findElement(By.linkText("CRM/SFA")).click();
		//Click on contacts Button
		driver.findElement(By.linkText("Contacts")).click();
		//Click on Merge Contacts using X-path Locator
		driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();
		//Click on Widget of From Contact
		driver.findElement(By.xpath("//img[@alt='Lookup']")).click();
		
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> list1 = new ArrayList<String>(windowHandles);
		driver.switchTo().window(list1.get(1));
		//Click on First Resulting Contact
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']//a")).click();
		//Click on Widget of To Contact
		driver.switchTo().window(list1.get(0));
		
		driver.findElement(By.xpath("(//img[@alt='Lookup'])[2]")).click();
		
		Set<String> windowHandles2 = driver.getWindowHandles();
		List<String> list2 = new ArrayList<String>(windowHandles2);
		driver.switchTo().window(list2.get(1));
		//Click on Second Resulting Contact
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])[2]/a")).click();
		driver.switchTo().window(list2.get(0));
		//Click on Merge button using X-path Locator
		driver.findElement(By.xpath("//a[text()='Merge']")).click();
		//Accept the Alert
		Alert alert = driver.switchTo().alert();
		alert.accept();
		//Verify the title of the page
		if(driver.getTitle().contains("View Contact"))
			System.out.println("Both Contact are Merged in View Contact Page");
		else
			System.out.println("Both Contact are Not Merged");
	
	}
	
}
