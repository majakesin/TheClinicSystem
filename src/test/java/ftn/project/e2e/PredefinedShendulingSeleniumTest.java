package ftn.project.e2e;

import java.awt.Dimension;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class PredefinedShendulingSeleniumTest {
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
	  public void testPredefinedShendulingAsPacien() {
	    driver.get("http://localhost:8081/logovanje");
	    driver.manage().window().fullscreen();
	    driver.findElement(By.id("usernameDto")).click();
	    driver.findElement(By.id("usernameDto")).sendKeys("clinic");
	    driver.findElement(By.cssSelector(".wrapper")).click();
	    driver.findElement(By.id("usernameDto")).sendKeys("paci");
	    driver.findElement(By.id("passwordDto")).click();
	    driver.findElement(By.id("passwordDto")).sendKeys("123");
	    driver.findElement(By.id("login")).click();
	    driver.findElement(By.linkText("Lista klinika")).click();
	    driver.findElement(By.linkText("Profil")).click();
	    driver.findElement(By.linkText("Zakaži predefinisani pregled")).click();
	    driver.findElement(By.linkText("Izaberi termin")).click();
	    driver.findElement(By.cssSelector(".col-sm-4:nth-child(6) .btn")).click();
	  }
	

	

}
