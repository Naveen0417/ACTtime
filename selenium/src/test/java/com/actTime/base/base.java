/**Automation for ACTtime |
 * Module : Base Class for ACTtime
 * Jan 19, 2021
 */
package com.actTime.base;

/**
 * @author Naveen G
 *
 */
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class base {

	public static final String DATA_PROPERTIES = "data.properties";

	private WebDriver driver;
	private Properties Prop;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(String browser) {
		if (browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = (new ChromeDriver());
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = (new FirefoxDriver());
		} else if (browser.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver = (new InternetExplorerDriver());
		}
	}

	public Properties getProp() {
		Prop = new Properties();
		return Prop;
	}

	@BeforeTest
	public void openBrowser() throws IOException {
		InputStream datapropertieStream = getClass().getClassLoader().getResourceAsStream(DATA_PROPERTIES);
		if (datapropertieStream != null) {
			getProp().load(datapropertieStream);
		}
		setDriver(Prop.getProperty("browser"));
		driver.get(Prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}

	@BeforeClass
	public void loginPage() {
		String username = Prop.getProperty("username");
		String password = Prop.getProperty("password");
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.name("pwd")).sendKeys(password);
		driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();
		System.out.println("The logout page is" + driver.getTitle());
	}

	@AfterClass
	public void logout() {
		driver.findElement(By.className("logoutImg")).click();
		System.out.println("The logout page is" + driver.getTitle());
	}

	@AfterTest
	public void closeBrowser() {
		driver.close();
	}

}
