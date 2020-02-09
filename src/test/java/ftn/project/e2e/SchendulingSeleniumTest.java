package ftn.project.e2e;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class SchendulingSeleniumTest {

	private WebDriver driver;

	@Before
	public void setUp() {

		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().window().maximize();

	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void testCreateTermAsClinicAdmin() {
		driver.get("http://localhost:8081/logovanje");
		driver.manage().window().fullscreen();
		driver.findElement(By.id("usernameDto")).click();
		driver.findElement(By.id("usernameDto")).sendKeys("clinic");
		driver.findElement(By.id("passwordDto")).click();
		driver.findElement(By.cssSelector(".wrapper")).click();
		driver.findElement(By.id("passwordDto")).sendKeys("123");
		driver.findElement(By.id("login")).click();
		driver.get("http://localhost:8081/doctors");
		driver.findElement(By.linkText("Termini")).click();
		driver.findElement(By.id("dateDto")).click();
		driver.findElement(By.id("dateDto")).sendKeys("2020-02-22");
		driver.findElement(By.id("timeDto")).click();
		driver.findElement(By.id("timeDto")).click();
		{
			WebElement element = driver.findElement(By.id("timeDto"));
			Actions builder = new Actions(driver);
			builder.doubleClick(element).perform();
		}
		driver.findElement(By.id("timeDto")).click();
		driver.findElement(By.id("timeDto")).click();
		driver.findElement(By.id("timeDto")).click();
		driver.findElement(By.id("timeDto")).sendKeys("03:00");
		driver.findElement(By.id("timeDto")).click();
		driver.findElement(By.id("timeDto")).click();
		driver.findElement(By.id("timeDto")).sendKeys("04:00");
		driver.findElement(By.id("timeDto")).click();
		driver.findElement(By.id("timeDto")).sendKeys("05:00");
		driver.findElement(By.id("timeDto")).click();
		{
			WebElement element = driver.findElement(By.id("timeDto"));
			Actions builder = new Actions(driver);
			builder.doubleClick(element).perform();
		}
		driver.findElement(By.id("timeDto")).sendKeys("06:00");
		driver.findElement(By.id("timeDto")).click();
		driver.findElement(By.id("timeDto")).sendKeys("07:00");
		driver.findElement(By.id("timeDto")).click();
		driver.findElement(By.id("roomDto")).click();
		driver.findElement(By.id("roomDto")).click();
		{
			WebElement element = driver.findElement(By.id("roomId"));
			Actions builder = new Actions(driver);
			builder.doubleClick(element).perform();
		}
		driver.findElement(By.id("typeDto")).click();
		driver.findElement(By.id("typeDto")).click();
		{
			WebElement element = driver.findElement(By.id("typeDto"));
			Actions builder = new Actions(driver);
			builder.doubleClick(element).perform();
		}
		driver.findElement(By.id("priceDto")).click();
		driver.findElement(By.id("priceDto")).sendKeys("312");
		driver.findElement(By.id("discountDto")).click();
		driver.findElement(By.id("discountDto")).sendKeys("eaggae");
		driver.findElement(By.cssSelector(".btn-outline-danger")).click();
		driver.findElement(By.linkText("Odjava")).click();
	}

	@Test
	public void testShendulingAppointment() {

		driver.manage().window().maximize();

		driver.get("http://localhost:8081/logovanje");

		driver.findElement(By.id("usernameDto")).click();
		driver.findElement(By.id("usernameDto")).sendKeys("paci");
		driver.findElement(By.id("passwordDto")).click();
		driver.findElement(By.id("passwordDto")).sendKeys("123");
		driver.findElement(By.id("login")).click();
		driver.findElement(By.linkText("Profil")).click();
		driver.get("http://localhost:8081/patientProfile");
		driver.findElement(By.linkText("Zakaži predefinisani pregled")).click();
		driver.findElement(By.linkText("Izaberi termin")).click();
		

	}

}
