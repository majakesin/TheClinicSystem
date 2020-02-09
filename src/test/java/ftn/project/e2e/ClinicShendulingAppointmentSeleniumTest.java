package ftn.project.e2e;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ClinicShendulingAppointmentSeleniumTest {
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
	public void testShenudlingAsPacient() {
		driver.get("http://localhost:8081/logovanje");
		driver.manage().window().fullscreen();
		driver.findElement(By.id("usernameDto")).click();
		driver.findElement(By.id("usernameDto")).click();
		{
			WebElement element = driver.findElement(By.id("usernameDto"));
			Actions builder = new Actions(driver);
			builder.doubleClick(element).perform();
		}
		driver.findElement(By.id("usernameDto")).sendKeys("paci");
		driver.findElement(By.id("passwordDto")).click();
		driver.findElement(By.id("passwordDto")).sendKeys("123");
		driver.findElement(By.id("login")).click();
		driver.findElement(By.linkText("Lista klinika")).click();
		driver.findElement(By.linkText("Zakaži")).click();
		driver.findElement(By.linkText("Zakaži")).click();
		driver.findElement(By.id("vreme")).click();
		{
			WebElement dropdown = driver.findElement(By.id("vreme"));
			dropdown.findElement(By.xpath("//option[. = '12:00']")).click();
		}
		driver.findElement(By.id("vreme")).click();
		driver.findElement(By.id("datumPregledaDto")).click();
		driver.findElement(By.id("datumPregledaDto")).sendKeys("2020-02-22");
		driver.findElement(By.cssSelector(".btn")).click();
	}
	
	@Test
	  public void testClincsShendulingAsPacient() {
	    driver.get("http://localhost:8081/logovanje");
	    driver.manage().window().fullscreen();
	    driver.findElement(By.id("usernameDto")).click();
	    driver.findElement(By.id("usernameDto")).sendKeys("paci");
	    driver.findElement(By.id("passwordDto")).click();
	    driver.findElement(By.id("passwordDto")).sendKeys("123");
	    driver.findElement(By.id("login")).click();
	    driver.findElement(By.linkText("Lista klinika")).click();
	    driver.findElement(By.linkText("Profili klinika")).click();
	    driver.findElement(By.cssSelector(".btn")).click();
	    driver.findElement(By.linkText("Lista doktora")).click();
	    driver.findElement(By.linkText("Zakaži")).click();
	    driver.findElement(By.id("vreme")).click();
	    {
	      WebElement dropdown = driver.findElement(By.id("vreme"));
	      dropdown.findElement(By.xpath("//option[. = '10:30']")).click();
	    }
	    driver.findElement(By.id("vreme")).click();
	    driver.findElement(By.id("datumPregledaDto")).click();
	    driver.findElement(By.id("datumPregledaDto")).sendKeys("2020-02-23");
	    driver.findElement(By.cssSelector(".btn")).click();
	  }


	
	
}


