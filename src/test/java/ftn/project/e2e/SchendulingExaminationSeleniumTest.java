package ftn.project.e2e;

import java.awt.Dimension;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SchendulingExaminationSeleniumTest {
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
	public void testShendulingExamination() {
		driver.get("http://localhost:8081/logovanje");
		driver.manage().window().fullscreen();
		driver.findElement(By.id("userDto")).click();
		driver.findElement(By.id("usernameDto")).click();
		driver.findElement(By.id("usernameDto")).sendKeys("clinic");
		driver.findElement(By.id("passwordDto")).click();
		
		driver.findElement(By.id("passwordDto")).sendKeys("123");
		driver.findElement(By.id("login")).click();
		driver.findElement(By.linkText("Termini")).click();
		driver.findElement(By.linkText("Zahtevi")).click();
		driver.get("http://localhost:8081/appointmentRequests");
		driver.findElement(By.linkText("Rezervisi salu")).click();
		driver.findElement(By.linkText("Rezervisi")).click();
	}

}
