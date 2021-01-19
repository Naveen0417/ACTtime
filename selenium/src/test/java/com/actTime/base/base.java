package com.actTime.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class base {

	public static void main(String[] args) {

		// new method for declaring browser
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver = (new ChromeDriver());

		// old method for declaring browser
//		System.setProperty("webdriver.chrome.driver", "E://cdriver//chromedriver.exe");
//		ChromeDriver driver = new ChromeDriver();

		driver.get("http://127.0.0.1:81/login.do");
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.findElement(By.name("username")).sendKeys("admin");
		driver.findElement(By.name("pwd")).sendKeys("manager");
		driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();
		driver.findElement(By.xpath("//a[text()=\"Users\"]")).click();
		driver.findElement(By.xpath("//input[@type=\"button\"]")).click();
		// Copy Text
		WebElement copy = driver.findElement(By.name("username"));
		copy.sendKeys("Naveen");
		Actions builder = new Actions(driver);
		builder.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).build().perform();
		builder.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).build().perform();
		// Paste Text
		WebElement Paste = driver.findElement(By.name("firstName"));
		Paste.click();
		builder.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();

	}

}
