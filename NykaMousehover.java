package week4day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NykaMousehover {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String title = driver.getTitle();
		System.out.println("Title" + title);

		WebElement brands = driver.findElement(By.xpath("//a[text()='brands']"));
		Actions build = new Actions(driver);
		build.moveToElement(brands).perform();

		driver.findElement(By.id("brandSearchBox")).click();
		driver.findElement(By.xpath("(//a[text()=\"L'Oreal Paris\"])[1]")).click();

		System.out.println(driver.getTitle());

		String windowHandle = driver.getWindowHandle();
		driver.switchTo().window(windowHandle);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Sort By : ']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='customer top rated']")).click();

		driver.findElement(By.xpath("//div[text()='Category']")).click();
		driver.findElement(By.xpath("//li[@class='filter-list-header  ']")).click();
		driver.findElement(By.xpath("//li[@class='filter-list-header  ']")).click();
		driver.findElement(By.xpath("//div[@class='control__indicator']")).click();
		driver.findElement(By.xpath("//div[text()='Concern']")).click();
		driver.findElement(By.xpath("//label[@for='chk_Color Protection_10764']//div[1]")).click();

		String filter = driver.findElement(By.xpath("//span[text()='filters applied']")).getText();

		String filter2 = driver.findElement(By.xpath("//li[text()='Shampoo']")).getText();
		System.out.println(filter);
		if (filter2.contains("Shampoo")) {
			System.out.println("Filter contains Shampoo");
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='m-content__product-list__title']//span")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> winList = new ArrayList<String>(windowHandles);
		driver.switchTo().window(winList.get(1));
		driver.findElement(By.xpath("//span[text()='175ml'] ")).click();
		String text = driver.findElement(By.xpath("//span[text()='150']  ")).getText();
		System.out.println("The price of the shampoo is " + text);
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[text()='ADD TO BAG'])[1]")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//div[@class='AddBagIcon']")).click();

		String text2 = driver.findElement(By.xpath("//div[@class='value medium-strong']")).getText();
		System.out.println("Grand total amount : " + text2);



		driver.findElement(By.xpath("(//span[text()='Proceed']")).click();
		driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
		String text3 = driver.findElement(By.xpath("(//div[@class='value']//span)[2]")).getText();
		System.out.println("The Last grand total " + text3);
		if (text2.contains(text3)) {
			System.out.println("Both the grand totals are same");
		}

	}

}
