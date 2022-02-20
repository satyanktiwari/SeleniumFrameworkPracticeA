package com.qa.opencart.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DriverFactory {
    private WebDriver driver;
    private Properties prop;
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public WebDriver init_driver(Properties prop){
        String browserName = prop.getProperty("browserName").trim();
        System.out.println("Browser name is: " + browserName);
        if (browserName.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
//            driver = new ChromeDriver();
            tlDriver.set(new ChromeDriver());
        } else if(browserName.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
//            driver = new FirefoxDriver();
            tlDriver.set(new FirefoxDriver());
        }else if(browserName.equalsIgnoreCase("edge")){
            WebDriverManager.edgedriver().setup();
//            driver = new EdgeDriver();
            tlDriver.set(new EdgeDriver());
        } else{
            System.out.println("Browser Name is not correct");
        }
        tlDriver.get().manage().window().maximize();
        tlDriver.get().manage().deleteAllCookies();
        tlDriver.get().get(prop.getProperty("url"));

        return getDriver();
    }

    public static WebDriver getDriver(){
        return tlDriver.get();
    }

    public Properties init_properties(){
        prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream(".\\src\\test\\java\\com\\qa\\opencart\\resources\\config\\config.properties");
            prop.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
//
}
