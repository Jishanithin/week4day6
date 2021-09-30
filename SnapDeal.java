package week4day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapDeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String title = driver.getTitle();
		System.out.println("Title" + title);

		driver.findElement(By.xpath("(//span[text()=\"Men's Fashion\"])[2]")).click();
		driver.findElement(By.xpath("(//span[text()='Sports Shoes'])[1]	")).click();

		String text = driver.findElement(By.xpath("//span[@class='category-count']")).getText();
		System.out.println("Total number of sport shoes are: " + text);
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		driver.findElement(By.xpath("//span[text()='Sort by:']")).click();
		driver.findElement(By.xpath("//li[@class='search-li']")).click();
//scrape price elements
		Thread.sleep(2000);
		List<WebElement> price = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));

//extract the prices from the price elements and store in a list
//
		int size = price.size();
		System.out.println("number of prices are" + size);

		List<String> prices = new ArrayList<String>();
		for (WebElement e : price) {
			String name = e.getText();
			prices.add(name);
		}
		List<String> sortedPrices = new ArrayList<String>(prices); // copy of a list
//sort the list
		Collections.sort(sortedPrices);
//true if the prices r sorted
		System.out.println(sortedPrices.equals(prices));

		WebElement firstvalue = driver.findElement(By.xpath("(//div[@class='price-text-box']/input[@value])[1]"));
		firstvalue.clear();
		firstvalue.sendKeys("900");

		WebElement lastValue = driver.findElement(By.xpath("(//div[@class='price-text-box']/input[@value])[2]"));
		lastValue.clear();
		lastValue.sendKeys("1200");

		driver.findElement(By.xpath("//div[text()[normalize-space()='GO']]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//label[@for='Color_s-Navy']")).click();
		Thread.sleep(2000);
		WebElement firstproduct = driver.findElement(By.xpath("(//p[@class='product-title'])[1] "));
		Actions a = new Actions(driver);
		a.moveToElement(firstproduct).perform();

		driver.findElement(By.xpath("//div[contains(@class,'center quick-view-bar')]")).click();
		String text2 = driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText();
		System.out.println("Price of the shoe : " + text2);

		String text3 = driver.findElement(By.xpath("//span[@class='percent-desc ']")).getText();
		System.out.println("Discount Percentage of the shoe : " + text3);

		File src = driver.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snapDeal/pic.png");
		FileUtils.copyFile(src, dst);
		
		driver.close();

	}

}
