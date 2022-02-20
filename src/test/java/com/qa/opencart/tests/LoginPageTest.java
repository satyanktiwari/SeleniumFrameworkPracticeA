package com.qa.opencart.tests;

import com.qa.opencart.utils.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {
    @Test
    public void loginTest() {
        System.out.println("This message is to check if the implementation is working fine\n If you are reading this setup is done");

    }

    @Test
    public void loginPageTitleTest(){
        String title = loginPage.getLoginPageTitle();
        Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
    }

    @Test
    public void loginPageUrlTest(){
        String url = loginPage.getLoginPageUrl();
        Assert.assertTrue(url.contains(Constants.LOGIN_PAGE_URL_FRACTION));
    }

}
