package week4day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MyntraNew {

	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String title = driver.getTitle();
		System.out.println("Title" + title);

		WebElement men = driver.findElement(By.xpath("//a[@class='desktop-main']"));
		Actions a = new Actions(driver);
		a.moveToElement(men).perform();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@href='/men-jackets']")).click();
		String text = driver.findElement(By.className("title-count")).getText();
		System.out.println("No of Jackets " + text);

		String text2 = driver.findElement(By.xpath("//span[@class='categories-num']")).getText();
		System.out.println("Categories count " + text2);

		if (text.contains(text2)) {
			System.out.println("the sum of categories count matches");
		} else {
			System.out.println("Not Matched");
		}
		driver.findElement(By.xpath("//div[@class='common-checkboxIndicator']")).click();

		driver.findElement(By.className("brand-more")).click();

		driver.findElement(By.className("FilterDirectory-searchInput")).sendKeys("Duke");

		driver.findElement(By.xpath("//label[@class=' common-customCheckbox']//div")).click();

		driver.findElement(By.xpath("//span[contains(@class,'myntraweb-sprite FilterDirectory-close')]")).click();

		List<WebElement> brand = driver.findElements(By.xpath("//h3[text()='Duke']"));

		List<String> newbrand = new ArrayList<String>();

		for (WebElement e : brand) {
			newbrand.add(e.getText());
		}

		List<String> verynewbrand = new ArrayList<String>(newbrand);
		if (verynewbrand.equals(newbrand)) {
			System.out.println("All the brands are Duke");
		} else {
			System.out.println("diff brands");
		}
		driver.findElement(By.className("sort-sortBy")).click();

		driver.findElement(By.xpath("(//label[@class='sort-label '])[3]")).click();

		String text3 = driver.findElement(By.xpath("//span[@class='product-discountedPrice']")).getText();
		System.out.println("Categories count " + text3);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//img[@class='img-responsive']")).click();

		File src = driver.getScreenshotAs(OutputType.FILE);
		File dst = new File("./myntra/pic.png");
		FileUtils.copyFile(src, dst);
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> aList = new ArrayList<String>(windowHandles);
		driver.switchTo().window(aList.get(1));

		driver.findElement(By.xpath("//div[contains(@class,'pdp-add-to-wishlist pdp-button')]")).click();

		driver.close();
		driver.switchTo().window(aList.get(0));
		driver.close();
	}

}
