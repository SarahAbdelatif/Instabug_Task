package org.example.test;

import com.paypal.selion.platform.dataprovider.DataProviderFactory;
import com.paypal.selion.platform.dataprovider.DataResource;
import com.paypal.selion.platform.dataprovider.SeLionDataProvider;
import com.paypal.selion.platform.dataprovider.impl.InputStreamResource;
import org.example.dataModels.UserDM;
import org.example.pages.SignInPage;
import org.example.pages.SignUpPage;
import org.example.utils.GeneralConstants;
import org.example.utils.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public class Signup_SignInTest {

    WebDriver driver ;
    Random RANDOM = new Random();
    int random = RANDOM.nextInt(999999999);


    @BeforeMethod
    public void setup()
    {
        Log.info("Open Browser");
        driver = new ChromeDriver();
        Log.info("Maximize Browser");
        driver.manage().window().maximize();
        Log.info("Navigate to URL : " + GeneralConstants.URL);
        driver.get(GeneralConstants.URL);
    }

    @Test(priority = 0, enabled = true, dataProvider = "testData")
    public void sign_up(UserDM userDM) {

        Log.startTestCase("sign_up");

        userDM.setLastName(userDM.getLastName()+random);
        Log.info("Test Data : " + userDM.toString());

        SignUpPage signUpPage = new SignUpPage();
        signUpPage.clickOnCreateNewAccountButton(driver);

        Assert.assertTrue(signUpPage.sign_up(driver, userDM));

        Log.endTestCase("sign_up");
    }

    @Test(priority = 1, enabled = true, dataProvider = "testData")
    public void sign_in(UserDM userDM) {

        Log.startTestCase("sign_in");
        Log.info("Test Data : " + userDM.toString());
        SignInPage signInPage = new SignInPage();
        signInPage.login(driver, userDM);

        Log.endTestCase("sign_in");


    }

    @AfterMethod
    public void tearDown()
    {
        driver.quit();
    }

    @DataProvider(name = "testData")
    public Object[][] prepareSignUpListTD() throws IOException {
        String csvDataFilePath = GeneralConstants.SIGN_UP_JSON_FILE_PATH;
        DataResource resource =
                new InputStreamResource(new FileInputStream(System.getProperty("user.dir") + csvDataFilePath),
                        UserDM.class, "json");
        SeLionDataProvider dataProvider =
                DataProviderFactory.getDataProvider(resource);

        return dataProvider.getAllData();
    }
}
