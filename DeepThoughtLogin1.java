package com.TestEng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class DeepThoughtLogin1 {
	WebDriver driver;
	String expUrl = "https://beta.deepthought.education/dashboard", actUrl;

	@Test(dataProvider = "getLoginData")
	public void loginToOHRM(String UN, String PW) throws InterruptedException {
		driver.findElement(By.id("username")).sendKeys(UN);
		driver.findElement(By.id("password")).sendKeys(PW);
		driver.findElement(By.id("login")).click();
		Thread.sleep(5000);	 
		
		}

	@DataProvider
	public Object[][] getLoginData() {
		return new Object[][] { new Object[] { "deepthought2k", "Cybertron@87" }, 
			                    new Object[] { "deepthought2k", "Megatron@87" },
				              };

	}

	@AfterMethod
	public void afterMethod() {
		actUrl = driver.getCurrentUrl();
		if (expUrl.equals(actUrl)) {
			driver.findElement(By.xpath("//*[@id=\"dropdownMenuButton\"]")).click();
			driver.findElement(By.xpath("/html/body/header/div/div/div/form/button/span")).click();	
		}
		
        WebElement errorMessage = driver.findElement(By.id("login-error-notify"));

        if (errorMessage.isDisplayed()) {
            System.out.println("Error message: " + errorMessage.getText());
        } 
	}

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://beta.deepthought.education/login");
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
