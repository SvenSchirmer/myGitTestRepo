package com.demoaut.newtours;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by SvenS on 09.05.2016.
 */
public class ReadDataFromExcel {

    public WebDriver driver;
    public WebDriverWait wait;

    String appUrl = "http://newtours.demoaut.com/mercurysignon.php";

    //Locators within the page
    private By byUserName = By.name("userName");
    private By byPassword = By.name("password");
    private By byLogin = By.name("login");
    private By bySignOff = By.linkText("SIGN-OFF");


    @BeforeClass
    public void testSetup() {

        // Chromedriver Internetexplorer ausprobieren
        driver = new FirefoxDriver();        
        driver.manage().window().maximize();
    }

    @Test (dataProvider = "flightSearchLogin")
    public void successfulLogin(String userName, String pwd) {
        driver.get(appUrl);

        driver.findElement(byUserName).sendKeys(userName);
        driver.findElement(byPassword).sendKeys(pwd);
        driver.findElement(byLogin).click();

        driver.findElement(bySignOff).click();

    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @DataProvider //(name="flightSearchLogin")
    public Object[][] flightSearchLogin() {

        return new Object[][]{
                {"tutorial", "tutorial"},
                {"tutorial", "tutorial"}
        };
    }
}