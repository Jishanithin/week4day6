package week4day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		String title = driver.getTitle();
		System.out.println("Title" + title);

		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro ", Keys.ENTER);
		String text = driver.findElement(By.xpath("//span[@class='a-price-whole']")).getText();
		System.out.println("price of oneplus" + text);

		String text2 = driver.findElement(By.xpath("//span[@class='a-size-base']")).getText();
		System.out.println("Ratings " + text2);
		Thread.sleep(2000);
		WebElement rating = driver.findElement(By.xpath("//a[@href='javascript:void(0)']//i"));
		Actions a = new Actions(driver);
		a.click(rating).perform();

		String aList = driver.findElement(By.xpath("//td[@class='a-text-right a-nowrap']//a")).getText();
		System.out.println(" 5 star Rating in percentage : " + aList);

		driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']")).click();
		File src = driver.getScreenshotAs(OutputType.FILE);
		File dst = new File("./Amazon/pic.png");
		FileUtils.copyFile(src, dst);

		Set<String> windowHandles = driver.getWindowHandles();
		List<String> ListWindow = new ArrayList<String>(windowHandles);
		driver.switchTo().window(ListWindow.get(1));

		driver.findElement(By.id("add-to-cart-button")).click();
		
		 driver.findElement(By.id("nav-cart-count")).click();
		String text3 = driver.findElement(By.id("sc-subtotal-label-buybox")).getText();
	System.out.println(text3);
	}

}
