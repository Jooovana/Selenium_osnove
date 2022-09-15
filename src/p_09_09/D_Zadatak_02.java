package p_09_09;

import java.awt.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

	public class D_Zadatak_02 {

	public static void main(String[] args) {
	
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe"); 
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get(" https://s.bootsnipp.com/iframe/WaXlr");

		List<WebElement> stars = driver.findElements(By.xpath("//*[contains(@id, 'rating-star')]"));

		Scanner s = new Scanner(System.in);
		Thread.sleep(3000);
		int ocena = s.nextInt();

		stars.get(ocena-1).click();

		Thread.sleep(5000);
		driver.quit();	
		
		
		
		
		
		

	}

}
