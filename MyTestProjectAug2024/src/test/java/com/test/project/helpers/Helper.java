package com.test.project.helpers;

import com.test.project.runners.Hook;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@Component
public class Helper {
    @Autowired
    private Hook hooks;

    private final int waitTimeOutSeconds = 5;

    /**
     * Waits until the given element is visible.
     * The element must be present on the DOM before the waiting starts
     *
     * @param element Element to check
     */
    public void waitForVisibilityOf(WebElement element) {
        hooks.getWait().until(visibilityOf(element));
    }

    /**
     * Waits for presence and visibility of the element matched by given selector.
     * The element can be present in the DOM or not before the waiting starts
     *
     * @param by Selector of the element
     */
    public void waitForPresenceOf(By by) {
        hooks.getWait().until(visibilityOfElementLocated(by));
    }

    public void clickOnCustomisedElementUsingXpath(String xpathOfElement) throws InterruptedException {
        WebElement customisedWebElement = hooks.getDriver().findElement(By.xpath(xpathOfElement));
        waitForPresenceOf(By.xpath(xpathOfElement));
        clickElementJS(customisedWebElement);
    }

    public void scrollToElement(WebElement element) throws InterruptedException {
        ((JavascriptExecutor) hooks.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(5000);
    }

    public void waitForPageLoad() {
        try {
            hooks.getDriver().manage().timeouts().pageLoadTimeout(waitTimeOutSeconds, TimeUnit.SECONDS);
        } catch (Exception rte) {
            throw new TimeoutException(rte.getMessage());
        }
    }

    //Explicit wait for element visible
    public void waitForElementVisible(WebElement element, int waitTimeOutSeconds) {
        Duration timeInSeconds = Duration.ofSeconds(waitTimeOutSeconds);
        System.out.println(timeInSeconds.toString());
        WebDriverWait wait = new WebDriverWait(hooks.getDriver(), timeInSeconds);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception rte) {
            throw new TimeoutException(rte.getMessage());
        }

    }

    //select value of dropdown by visible text
    public void selectOptionByVisibleText(WebElement element, String Val) {
        Select sel = new Select(element);
        sel.selectByVisibleText(Val);
    }

    //click on the web element
    public void clickElement(WebElement element) {
        element.click();
    }

    public void clickElementJS(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) hooks.getDriver();
        executor.executeScript("arguments[0].click();", element);
    }

    // Enter the value in the text box
    public void enterText(WebElement element, String value) {
        element.sendKeys(value);
    }

    // Clear the value in the text box
    public void clearText(WebElement element) {
        element.clear();
    }

    public void clearAndEnterText(WebElement element, String value) throws InterruptedException {
        clearText(element);
        Thread.sleep(1000);
        enterText(element, value);
    }

    // Get the value from the dropdown
    public String getSelectedValueOfDropDown(WebElement element) {
        Select sel = new Select(element);
        WebElement option = sel.getFirstSelectedOption();
        return option.getText();
    }

    public void browserRefresh() {
        hooks.getDriver().navigate().refresh();
    }

}
