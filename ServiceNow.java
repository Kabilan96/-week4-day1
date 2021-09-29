package week4.day1;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNow {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://dev110480.service-now.com/navpage.do");
		WebElement frame = driver.findElement(By.id("gsft_main"));
		driver.switchTo().frame(frame);
		driver.findElement(By.id("user_name")).sendKeys("aileen.mottern");
		driver.findElement(By.id("user_password")).sendKeys("Kabil.123");
		driver.findElement(By.name("not_important")).click();
		
		driver.switchTo().defaultContent();
		driver.findElement(By.id("filter")).sendKeys("incident");
		driver.findElement(By.xpath("//div[text()='Incidents']")).click();
		
		WebElement frame1 = driver.findElement(By.id("gsft_main"));
		driver.switchTo().frame(frame1);
		driver.findElement(By.id("sysverb_new")).click();
		
		WebElement dropdown = driver.findElement(By.xpath("(//table[@id='item_table']//table)[4]/tbody[1]/tr[2]/td[1]/div[1]/div[1]/div[2]/select[1]"));
		Select drop = new Select(dropdown);
		drop.selectByVisibleText("1 - High");
		
		driver.findElement(By.xpath("//textarea[contains(@class,'question_textarea_input cat_item_option')]")).sendKeys("Automated Testing");
		driver.findElement(By.id("submit_button")).click();
		
		driver.findElement(By.id("incident.short_description")).sendKeys("Test Case");
		
		String incNum = driver.findElement(By.xpath("//input[@class='form-control disabled ']")).getAttribute("value");
		System.out.println("The Incident Number : " +incNum);
		
		driver.findElement(By.id("sysverb_update")).click();
		
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys(incNum,Keys.ENTER);
		System.out.println("Incident Created Successfully : " + incNum);
		
		File ss = driver.getScreenshotAs(OutputType.FILE);
		File folder = new File("./snaps/img1.png");
		FileHandler.copy(ss, folder);
			
	}

}
