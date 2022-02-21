package com.qa.opencart.pages;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.utils.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class LoginPage {
    private WebDriver driver;
    ElementUtils elementUtils;

    //By Locators
    private By emailField = By.id("input-email");
    private By passwordField = By.id("input-password");
    private By loginButton = By.xpath("//input[@class='btn btn-primary']");
    private By forgottenPasswordLink = By.cssSelector(".form-group a");
    private By registerLink = By.linkText("Register");

//    public LoginPage(WebDriver driver) {
//        this.driver = driver;
//    }

    public LoginPage() {
        driver = DriverFactory.getDriver();
//        elementUtils = new ElementUtils(driver);

    }

//    public action methods
    public String getLoginPageTitle(){
        String Title = driver.getTitle();
        System.out.println("Login Page Title is: " + Title);
        return Title;
    }

    public String getLoginPageUrl(){
        String url = driver.getCurrentUrl();
        System.out.println("Login Page URL is: " + url);
        return url;
    }


}

