package domaci_19_09;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BootstrapTableTests {

private WebDriver driver;
private WebDriverWait wait;
private String baseUrl = "https://s.bootsnipp.com";	
	
@BeforeClass
public void setup() {
	System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
	wait = new WebDriverWait(driver, Duration.ofSeconds(10));
}	
	
@BeforeMethod
public void beforeMethod() {
	driver.get(baseUrl);
}

@Test(priority = 10)
public void editRow() {
	driver.get(baseUrl + "/iframe/K5yrx");	
	
	Assert.assertEquals(
			driver.getTitle(),
			"Table with Edit and Update Data - Bootsnipp.com",
			"ERROR: Title should be Table with Edit and Update Data - Bootsnipp.com");	
	
	driver.findElement(By.xpath("//*[@id='d1']//*[contains(@class, 'update')]")).click();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='edit']//*[contains(@class, 'modal-content')]")));
	
	driver.findElement(By.id("fn")).clear();
	driver.findElement(By.id("fn")).sendKeys("Jovana");
	
	driver.findElement(By.id("ln")).clear();
	driver.findElement(By.id("ln")).sendKeys("Stevanovic");
	
	driver.findElement(By.id("mn")).clear();
	driver.findElement(By.id("mn")).sendKeys("Aleksa");
	
	driver.findElement(By.id("up")).click();
	
	assertEquals(
			driver.findElement(By.id("f1")).getText(),
			"Jovana",
			"Incorrect firstname.");
	assertEquals(
			driver.findElement(By.id("l1")).getText(), 
			"Stevanovic", 
			"Incorrect lastname.");
	assertEquals(
			driver.findElement(By.id("m1")).getText(), 
			"Aleksa", 
			"Incorrect middlename.");	
}
	
@Test(priority = 20)
public void deleteRow() throws InterruptedException {
	driver.get(baseUrl + "/iframe/K5yrx");
	
	Assert.assertEquals(
			driver.getTitle(),
			"Table with Edit and Update Data - Bootsnipp.com",
			"ERROR: Title should be Table with Edit and Update Data - Bootsnipp.com");
	driver.findElement(By.xpath("//*[@id='d1']//*[contains(@class, 'delete')]")).click();

	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='delete']//*[contains(@class, 'modal-content')]")));
	driver.findElement(By.id("del")).click();

	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='delete']//*[contains(@class, 'modal-content')]")));	
	
	Assert.assertFalse(
			driver.findElement(By.xpath("//tbody/tr[1]")).isDisplayed(),
			"ERROR: Number of rows is not change.");

	Thread.sleep(3000);
	
	
}
	
@Test(priority = 30)
public void  takeAScreenShoot() throws IOException{
	driver.get(baseUrl + "/iframe/K5yrx");
	
	Assert.assertEquals(
			driver.getTitle(),
			"Table with Edit and Update Data - Bootsnipp.com",
			"ERROR: Title should be Table with Edit and Update Data - Bootsnipp.com");
	TakesScreenshot scrShot = ((TakesScreenshot) driver);
	File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
	File DestFile=new File("src/paket_za_domaci/screenshot2.png");
	com.google.common.io.Files.copy(SrcFile, DestFile);
	
	
}

	@AfterMethod
	public void afterMethod() {
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}






}
