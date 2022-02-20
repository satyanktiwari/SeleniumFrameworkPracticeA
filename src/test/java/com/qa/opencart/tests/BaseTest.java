package com.qa.opencart.tests;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.Properties;

public class BaseTest {

    DriverFactory driverFactory;
    WebDriver driver;
    Properties prop;
    LoginPage loginPage;

    @BeforeTest
    public void setup(){
        driverFactory = new DriverFactory();
        prop = driverFactory.init_properties();
//        driver = driverFactory.init_driver("chrome");
        driver = driverFactory.init_driver(prop);
//        loginPage = new LoginPage(driver);
        loginPage = new LoginPage();

    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
