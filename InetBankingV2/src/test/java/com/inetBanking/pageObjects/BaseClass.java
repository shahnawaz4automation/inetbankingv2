package com.inetBanking.pageObjects;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {

	public Logger logger;
	public String baseURL ="https://demo.guru99.com/v4";
	public String username = "mngr505967";
	public String password = "dehEbuz";
	public static WebDriver driver;

	@BeforeClass 
	// setup method
	public void setup() {
		System.setProperty("webdriver.http.factory", "jdk-http-client");

		//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "//Drivers//chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", "C://Users//shah4//eclipse-workspace//InetBankingV2//Drivers//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
	}

	@AfterClass
	public void teardown() {
		driver.quit();
	}
}
