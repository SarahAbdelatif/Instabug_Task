package org.example.pages;

import org.example.dataModels.UserDM;
import org.example.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInPage {

    /**
     * Login Form Locators
     */
    private static final By EMAIL_INPUT_LOCATOR = By.id("email");
    private static final By PASSWORD_INPUT_LOCATOR = By.id("pass");
    private static final By SIGN_IN_BUTTON = By.name("login");
    private static final By VALIDATION_ELEMENT_LOCATOR = By.xpath("//*[@aria-label='Account controls and settings']");


    //login method
    public boolean login(WebDriver driver, UserDM userDM) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        Log.info("Enter Email : " + userDM.getEmail());
        driver.findElement(EMAIL_INPUT_LOCATOR).sendKeys(userDM.getEmail());

        Log.info("Enter Password : " + userDM.getPassword());
        driver.findElement(PASSWORD_INPUT_LOCATOR).sendKeys(userDM.getPassword());

        Log.info("Click on Sign In Button");
        driver.findElement(SIGN_IN_BUTTON).click();

        Log.info("Wait until the validation element is displayed");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(VALIDATION_ELEMENT_LOCATOR)).isDisplayed();

    }
}
