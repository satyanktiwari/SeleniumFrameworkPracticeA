package com.qa.opencart.utils;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtils {
    private WebDriver driver;

    public ElementUtils(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * This method will return the locator and uses By locator internally.<br/>
     * Parameters are both of type String.
     * @param locatorType
     * @param locatorValue
     * @return
     */
    public By getBy(String locatorType, String locatorValue) {
        By locator = null;
        switch (locatorType.toLowerCase()) {
            case "id":
                locator = By.id(locatorValue);
                break;
            case "name":
                locator = By.name(locatorValue);
                break;
            case "classname":
                locator = By.className(locatorValue);
                break;
            case "xpath":
                locator = By.xpath(locatorValue);
                break;
            case "css":
                locator = By.cssSelector(locatorValue);
                break;
            case "linktext":
                locator = By.linkText(locatorValue);
                break;
            case "partiallinktext":
                locator = By.partialLinkText(locatorValue);
                break;
            case "tagname":
                locator = By.tagName(locatorValue);
                break;

            default:
                break;
        }

        return locator;
    }

    /**
     * This method will return the element and uses By locator as parameter.
     * @param locator
     * @return
     */
    public WebElement getElement(By locator) {
        return driver.findElement(locator);
    }

    /**
     * This method used the getElement method to locate the element</br>
     * And then performs click on the element
     * @param locator
     */
    public void doClick(By locator) {
        getElement(locator).click();
    }


    /**
     * This method takes String locatorType and String locatorValue as parameters<br/>
     * internally uses the getElement method,<br/>
     * which in turn is using hte getBy method to get the locator<br/>
     * And then performs click on the element
     * @param locatorType
     * @param locatorValue
     */
    public void doClick(String locatorType, String locatorValue) {
        getElement(getBy(locatorType, locatorValue)).click();
    }

    /**
     * This method performs the sendKeys operation on the element<br/>
     * @param locator
     * @param value
     */
    public void doSendKeys(By locator, String value) {
        getElement(locator).sendKeys(value);
    }

    /**
     * This method takes String locatorType, String locatorValue and value to be passed
     * as parameters<br/>
     * E.g. locatorType = id, locatorValue = username, value = "admin@email.com"<br/>
     * @param locatorType
     * @param locatorValue
     * @param value
     */
    public void doSendKeys(String locatorType, String locatorValue, String value) {
        getElement(getBy(locatorType, locatorValue)).sendKeys(value);
    }

    /**
     * Method to get the text of the element
     * @param locator
     * @return
     */
    public String doGetText(By locator) {
        return getElement(locator).getText();
    }

    /**
     * Method returns attribute value of the element
     * @param locator
     * @param attrName
     * @return
     */
    public String doGetAttribute(By locator, String attrName) {
        return getElement(locator).getAttribute(attrName);
    }

    /**
     * Method to check if the element is displayed
     * @param locator
     * @return
     */
    public boolean doIsDisplayed(By locator) {
        return getElement(locator).isDisplayed();
    }

    /**
     * Method return a List of WebElements
     * @param locator
     * @return
     */
    public List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    /**
     * Method to get the size of the list
     * @param locator
     * @return
     */
    public int getElementCount(By locator) {
        return getElements(locator).size();
    }

    /**
     * Method uses a forEach loop to iterate through the List of WebElements<br>
     * And then prints the text of each element
     * @param locator
     */
    public void printElementsText(By locator) {
        List<WebElement> eleList = getElements(locator);
        for (WebElement e : eleList) {
            String text = e.getText();
            System.out.println(text);
        }
    }

    /**
     * This method will return the list of element's text
     *
     * @param locator
     * @return
     */
    public List<String> getElementsTextList(By locator) {
        List<WebElement> eleList = getElements(locator);
        List<String> eleTextList = new ArrayList<String>();
        for (WebElement e : eleList) {
            String text = e.getText();
            if (!text.isEmpty()) {
                eleTextList.add(text);
            }
        }
        return eleTextList;
    }

    /**
     * This method will return the list of element's attribute value
     *
     * @param locator
     * @param attrName
     * @return
     */
    public List<String> getElementsAttributeList(By locator, String attrName) {
        List<WebElement> eleList = getElements(locator);
        List<String> eleAttrList = new ArrayList<String>();
        for (WebElement e : eleList) {
            String attrVal = e.getAttribute(attrName);
            eleAttrList.add(attrVal);
        }
        return eleAttrList;
    }

    /**
     * Method to click on a specific link from the list of elements
     * @param locator
     * @param linkText
     */
    public void clickOnLink(By locator, String linkText) {
        List<WebElement> langList = getElements(locator);
        System.out.println(langList.size());
        for (WebElement e : langList) {
            String text = e.getText();
            System.out.println(text);
            if (text.contains(linkText)) {
                e.click();
                break;
            }
        }
    }

    /******************************* Drop Down Utils ***************************/

    /**
     * Method to select an option from a drop down using index
     * @param locator
     * @param index
     */
    public void doSelectDropDownByIndex(By locator, int index) {
        Select select = new Select(getElement(locator));
        select.selectByIndex(index);
    }

    /**
     * Method to select an option from a drop down using visible text
     * @param locator
     * @param visibleText
     */
    public void doSelectDropDownByVisibleText(By locator, String visibleText) {
        Select select = new Select(getElement(locator));
        select.selectByVisibleText(visibleText);
        ;
    }

    /**
     * Mehtod to select an option form a drop down using value
     * @param locator
     * @param value
     */
    public void doSelectDropDownByValue(By locator, String value) {
        Select select = new Select(getElement(locator));
        select.selectByValue(value);
    }

    /**
     * Method to get the size of the drop down
     * @param locator
     * @return
     */
    public int getDropDownOptionsCount(By locator) {
        Select select = new Select(getElement(locator));
        return select.getOptions().size();
    }

    /**
     * Method to get the list of options from the drop down and select option using text
     * @param locator
     * @param value
     * @return
     */
    public void selectValueFromDropDown(By locator, String value) {
        Select select = new Select(getElement(locator));
        List<WebElement> optionsList = select.getOptions();
        for (WebElement e : optionsList) {
            String text = e.getText();
            if (text.equals(value)) {
                e.click();
                break;
            }
        }
    }

    /**
     * Method iterates through a List of WebElements and stores corresponding text in a List
     * and returns this textList
     * @param locator
     * @return
     */
    public List<String> getDropDownOptionsList(By locator) {
        Select select = new Select(getElement(locator));
        List<WebElement> optionsList = select.getOptions();
        List<String> optionsTextList = new ArrayList<String>();
        System.out.println(optionsList.size());

        for (WebElement e : optionsList) {
            String text = e.getText();
            optionsTextList.add(text);
        }
        return optionsTextList;
    }

    /******************* Actions methods **********************/

    /**
     * Method to perform sendKeys using the Actions class
     * @param locator
     * @param value
     */
    public void doActionsSendKeys(By locator, String value) {
        Actions act = new Actions(driver);
        act.sendKeys(getElement(locator), value).perform();
    }

    /**
     * Clicks in the middle of the given element. Equivalent to:
     * Actions.moveToElement(onElement).click()
     *
     * @param locator
     */
    public void doActionsClick(By locator) {
        Actions act = new Actions(driver);
        act.click(getElement(locator)).perform();
    }

    /************************* Wait Utils **************************/

    /**
     * An expectation for checking an element is visible and enabled such that you can click it.
     * @param locator
     * @param timeOut
     */
    public void clickWhenReady(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * An expectation for checking that an element is present on the DOM of a page.
     * This does not necessarily mean that the element is visible.
     *
     * @param locator
     * @param timeOut
     * @return
     */
    public WebElement waitForElementPresence(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * An expectation for checking that there is at least one element present on a
     * web page.
     *
     * @param locator
     * @param timeOut
     * @return
     */
    public List<WebElement> waitForElementsPresence(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    /**
     * An expectation for checking that an element is present on the DOM of a page
     * and visible. Visibility means that the element is not only displayed but also
     * has a height and width that is greater than 0.
     * default polling time = 500 ms
     * @param locator
     * @param timeOut
     * @return
     */
    public WebElement waitForElementVisible(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * An expectation for checking that an element is present on the DOM of a page
     * and visible. Visibility means that the element is not only displayed but also
     * has a height and width that is greater than 0.
     * default polling time = customized time
     * @param locator
     * @param timeOut
     * @return
     */
    public WebElement waitForElementVisible(By locator, int timeOut, int pollingTime) {
        WebDriverWait wait = new WebDriverWait(driver,
                Duration.ofSeconds(timeOut), Duration.ofSeconds(pollingTime));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * An expectation for checking that all elements present on the web page that
     * match the locator are visible. Visibility means that the elements are not
     * only displayed but also have a height and width that is greater than 0.
     *
     * @param locator
     * @param timeOut
     * @return
     */
    public List<WebElement> waitForElementsVisible(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

    }

    // non web elements: title, url, alert

    public boolean waitForPageTitle(String titleVal, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.titleContains(titleVal));
    }

    /**
     * Method using WebDriverWait, waits for the Actual page title
     * @param actTitle
     * @param timeOut
     * @return
     */
    public boolean waitForPageActTitle(String actTitle, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.titleIs(actTitle));
    }

    /**
     * Method using WebDriverWait, waits for the page title
     * If title is found return the title
     * if false return null
     * @param titleVal
     * @param timeOut
     * @return
     */
    public String doGetPageTitleContains(String titleVal, int timeOut) {
        if (waitForPageTitle(titleVal, timeOut)) {
            return driver.getTitle();
        }
        return null;
    }

    /**
     * Method using WebDriverWait, waits for the Actual page title
     * If title is found return the title
     * if false return null
     * @param timeOut
     * @return
     */
    public String doGetPageTitleIs(String titleVal, int timeOut) {
        if (waitForPageActTitle(titleVal, timeOut)) {
            return driver.getTitle();
        }
        return null;
    }

    /**
     * Method using WebDriverWait, waits for the page url <br/>
     * until it matches the expected part/fraction url<br>
     * If found returns the url or returns null<br>
     * @param urlFraction
     * @param timeOut
     * @return
     */
    public String waitForUrlContains(String urlFraction, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

        try {
            if (wait.until(ExpectedConditions.urlContains(urlFraction))) {
                return driver.getCurrentUrl();
            }
        } catch (TimeoutException e) {
            return null;

        }
        return null;
    }

    public String waitForUrlToBe(String url, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

        try {
            if (wait.until(ExpectedConditions.urlToBe(url))) {
                return driver.getCurrentUrl();
            }
        } catch (TimeoutException e) {
            return null;

        }
        return null;
    }

    /**
     * Method using WebDriverWait,Waits until the alert is present<br>
     * @param timeOut
     * @return
     */
    public Alert waitForAlert(int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    /**
     * Method using WebDriverWait,Waits until the alert is present<br>
     * Then returns the text from the alert<br>
     * @param timeOut
     * @return
     */
    public String getAlertText(int timeOut) {
        return waitForAlert(timeOut).getText();
    }

    /**
     * Method using WebDriverWait,Waits until the alert is present<br>
     * Then accepts the alert<br>
     * @param timeOut
     */
    public void acceptAlert(int timeOut) {
        waitForAlert(timeOut).accept();
    }

    /**
     * Method using WebDriverWait,Waits until the alert is present<br>
     * Then dismisses the alert<br>
     * @param timeOut
     */
    public void dismissAlert(int timeOut) {
        waitForAlert(timeOut).dismiss();
    }

    /**
     * Method using WebDriverWait,waits until the iframe is present<br>
     * uses index to find the iframe<br>
     * Then switches to it<br>
     * @param timeOut
     * @param frameIndex
     * @return
     */
    public WebDriver waitForFrameByIndex(int timeOut, int frameIndex) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
    }

    /**
     * Method using WebDriverWait,waits until the iframe is present<br>
     * Uses By locator to find the iframe<br>
     * Then switches to it<br>
     * @param timeOut
     * @param frameLocator
     * @return
     */
    public WebDriver waitForFrameByLocator(int timeOut, By frameLocator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
    }

    /**
     * Method using WebDriverWait,waits until the iframe is present<br>
     * Uses WebElement to find the iframe<br>
     * Then switches to it<br>
     * @param timeOut
     * @param frameElement
     * @return
     */
    public WebDriver waitForFrameByElement(int timeOut, WebElement frameElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
    }

    /**
     * Method using FluentWait.
     * Return WebElement.
     * @param locator
     * @param timeOut
     * @param pollingTime
     * @return
     */
    public WebElement waitForElementPresenceWithFluentWait(By locator, int timeOut, int pollingTime) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(timeOut))
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .withMessage(locator + " is not found within the given time......");
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Method using WebDriverWait,waits until the element is present<br>
     * Return WebElement<br>
     * @param locator
     * @param timeOut
     * @param pollingTime
     * @return
     */
    public WebElement waitForElementPresenceWithWait(By locator, int timeOut, int pollingTime) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.pollingEvery(Duration.ofSeconds(pollingTime))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .withMessage(locator + " is not found within the given time......");

        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));


    }
}