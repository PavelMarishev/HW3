package Tests;

import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class LoginTests {
    private WebDriver driver;
    private LoginPage loginPage;


    @BeforeTest
    public void beforetest(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    /**
     * Steps to reproduce:
     * 1.Open your browser
     * 2.Enter website http://80.92.229.236:81/auth/login
     */
    @BeforeMethod
    public void beforeMethod(){
        loginPage = new LoginPage(driver);
        loginPage.open();
    }

    /**
     * Steps to reproduce:
     * 1.Set username to "admin"
     * 2.Set password to "123"
     * 3.Click login
     * 4.You have to be at the page with the title "Players"
     */
    @Test
    public void positiveTest(){
        loginPage.login("admin","123");
        Assert.assertEquals(loginPage.getTitle(),"Players","Wrong title");
    }

    /**
     * Steps to reproduce:
     * 1.Set username to "admin"
     * 2.Set password to "1234"
     * 3.Click login
     * 4.You have to see message "Invaild username or password" under password field.
     */
    @Test
    public void negativeWrongPasswordTest(){
        loginPage.login("admin","1234");
        Assert.assertEquals(loginPage.getTitle(),"Login");
        Assert.assertEquals(loginPage.getErrorMessage("First error"),"Invalid username or password","negativeWrongPasswordTest Failed");
    }
    /**
     * Steps to reproduce:
     * 1.Set username to "admon"
     * 2.Set password to "1234"
     * 3.Click login
     * 4.You have to see message "Invaild username or password" under username field.
     */
    @Test
    public void negativeWrongLoginTest(){
        loginPage.login("admon","1234");
        Assert.assertEquals(loginPage.getTitle(),"Login");
        Assert.assertEquals(loginPage.getErrorMessage("First error"),"Invalid username or password","negativeWrongLoginTest Failed");
    }

    /**
     * Steps to reproduce:
     * 1.Set username to "admon"
     * 2.Set password to "1234"
     * 3.Click login
     * 4.You have to see message "Invaild username or password" under password and username fields.
     */
    @Test
    public void negativeWrongLoginPlusPasswordTest(){
        loginPage.login("admon","1234");
        Assert.assertEquals(loginPage.getTitle(),"Login");
        Assert.assertEquals(loginPage.getErrorMessage("First error"),"Invalid username or password","negativeWrongLoginPlusPasswordTest Failed");
    }
    /**
     * Steps to reproduce:
     * 1.Set username to "admin"
     * 2.Click login
     * 3.You have to see message "Value is required and can't be empty" under password field.
     */
    @Test
    public void negativeEmtpyPasswordTest(){
        loginPage.login("admin","");
        Assert.assertEquals(loginPage.getTitle(),"Login");
        Assert.assertEquals(loginPage.getErrorMessage("First error"),"Value is required and can't be empty","negativeEmptyPasswordTest Failed");
    }
    /**
     * Steps to reproduce:
     * 1.Set password to "1234"
     * 2.Click login
     * 3.You have to see message "Value is required and can't be empty" under username field.
     */
    @Test
    public void negativeEmptyUsernameTest(){
        loginPage.login("","123");
        Assert.assertEquals(loginPage.getTitle(),"Login");
        Assert.assertEquals(loginPage.getErrorMessage("First error"),"Value is required and can't be empty","negativeEmptyUsernameTest Failed");
    }
    /**
     * Steps to reproduce:
     * 1.Click login
     * 2.You have to see message "Value is required and can't be empty" under password and username fields.
     */
    @Test
    public void negativeEmptyUsernamePlusPasswordTest(){
        loginPage.login("","");
        Assert.assertEquals(loginPage.getTitle(),"Login");
        Assert.assertEquals(loginPage.getErrorMessage("First error"),"Value is required and can't be empty","negativeEmptyUsernamePlusPasswordTest Failed");
        Assert.assertEquals(loginPage.getErrorMessage("Second error"),"Value is required and can't be empty","negativeEmptyUsernamePlusPasswordTest Failed");
    }

    /**
     * Steps to reproduce:
     * 1.Set username to ADMIN
     * 2.Set password to 123
     * 3.Click login
     * 4.You have to see message"Invaild username or password" under username field
     */
    @Test
    public void negativeUpperCaseLogin(){
        loginPage.login("ADMIN","123");
        Assert.assertEquals(loginPage.getTitle(),"Login");
        Assert.assertEquals(loginPage.getErrorMessage("First error"),"Invalid username or password","negativeWrongLoginTest Failed");
    }
    @AfterTest
    public void aftertest(){
        driver.quit();
    }
}
