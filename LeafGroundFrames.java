package week4.day1;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LeafGroundFrames {

	public static void main(String[] args) throws IOException {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://leafground.com/pages/frame.html");

		//Screenshot of the Click Me
		driver.switchTo().frame(0);
		WebElement clickme = driver.findElement(By.id("Click"));
		File ss = clickme.getScreenshotAs(OutputType.FILE);
		File folder = new File("./snaps/img.png");
		FileHandler.copy(ss, folder);
		driver.switchTo().defaultContent();

		//Find Number of Frames
		List<WebElement> framelist = driver.findElements(By.tagName("iframe"));
		System.out.println("Frames Visible in the MainPage : " + framelist.size());



	}

}
