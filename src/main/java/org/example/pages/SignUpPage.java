package org.example.pages;

import org.example.dataModels.UserDM;
import org.example.utils.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SignUpPage {

    /**
     * Signup Form Locators
     */

    private static final By CREATE_NEW_ACCOUNT_BUTTON = By.linkText("Create new account");
    private static final By FIRST_NAME_INPUT_LOCATOR = By.name("firstname");
    private static final By SURNAME_INPUT_LOCATOR = By.name("lastname");
    private static final By EMAIL_INPUT_LOCATOR = By.name("reg_email__");
    private static final By RE_ENTER_EMAIL_INPUT_LOCATOR = By.name("reg_email_confirmation__");
    private static final By PASSWORD_INPUT_LOCATOR = By.id("password_step_input");
    private static final By BIRTHDAY_DAY_SELECT_LOCATOR = By.id("day");
    private static final By BIRTHDAY_MONTH_SELECT_LOCATOR = By.id("month");
    private static final By BIRTHDAY_YEAR_SELECT_LOCATOR = By.id("year");
    private static final By GENDER_RADIO_BUTTONS_LOCATOR = By.xpath("//*[@name='sex']/preceding::label");
    private static final By SIGN_UP_BUTTON = By.name("websubmit");
    private static final By LOADING_LOGO_LOCATOR = By.xpath("//*[@name='websubmit']/ancestor::div[1]//span");
    private static final By VALIDATION_ELEMENT_LOCATOR = By.xpath("//*[@aria-label='Account controls and settings']");

    //click on create new account button
    public void clickOnCreateNewAccountButton(WebDriver driver) {
        Log.info("Click on Create New Account Button");
        driver.findElement(CREATE_NEW_ACCOUNT_BUTTON).click();
    }


    public boolean sign_up(WebDriver driver, UserDM userDM)
    {
        Log.info("Wait until the first name input is displayed");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(d -> d.findElement(FIRST_NAME_INPUT_LOCATOR).isDisplayed());

        Log.info("Fill the sign up form");
        Log.info("Enter First Name : " + userDM.getFirstName());
        driver.findElement(FIRST_NAME_INPUT_LOCATOR).sendKeys(userDM.getFirstName());

        Log.info("Enter Last Name : " + userDM.getLastName());
        driver.findElement(SURNAME_INPUT_LOCATOR).sendKeys(userDM.getLastName());

        Log.info("Enter Email : " + userDM.getEmail());
        driver.findElement(EMAIL_INPUT_LOCATOR).sendKeys(userDM.getEmail());

        Log.info("Re-Enter Email : " + userDM.getEmail());
        driver.findElement(RE_ENTER_EMAIL_INPUT_LOCATOR).sendKeys(userDM.getEmail());

        Log.info("Enter Password : " + userDM.getPassword());
        driver.findElement(PASSWORD_INPUT_LOCATOR).sendKeys(userDM.getPassword());

        Log.info("Select Birth Day : " + userDM.getBirthDay());
        Select select = new Select(driver.findElement(BIRTHDAY_DAY_SELECT_LOCATOR));
        select.selectByVisibleText(userDM.getBirthDay());

        Log.info("Select Birth Month : " + userDM.getBirthMonth());
        select = new Select(driver.findElement(BIRTHDAY_MONTH_SELECT_LOCATOR));
        select.selectByVisibleText(userDM.getBirthMonth());

        Log.info("Select Birth Year : " + userDM.getBirthYear());
        select = new Select(driver.findElement(BIRTHDAY_YEAR_SELECT_LOCATOR));
        select.selectByVisibleText(userDM.getBirthYear());

        Log.info("Select gender : " + userDM.getGender());
        List<WebElement> genderList = driver.findElements(GENDER_RADIO_BUTTONS_LOCATOR);
        for (int i = 0 ; i < genderList.size() ; i++)
        {
            if(genderList.get(i).getText().equals(userDM.getGender()))
            {
                genderList.get(i).click();
                break;
            }
        }

        Log.info("Click on Sign Up Button");
        driver.findElement(SIGN_UP_BUTTON).click();

        Log.info("Wait until the validation element is displayed");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(VALIDATION_ELEMENT_LOCATOR)).isDisplayed();

    }







}
