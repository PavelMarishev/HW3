package Tests;

import Pages.CreateEditPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Scynet on 02.12.2016.
 */
public class CreatingTests {
    private WebDriver driver;
    private CreateEditPage page;
    @BeforeTest
    /**
     * Steps to reproduce:
     * 1.Open your browser
     * 2.Go to poker website
     * 3.Set name "admin"
     * 4.Set password "123"
     * 5.Click login
     * 6.Check that your page has title "Players"
     */
    public void b4Test(){
        driver=new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        page=new CreateEditPage(driver);
        page.login();
        Assert.assertEquals(page.getTitle(),"Players","Not logged in");
    }

    /**
     * Steps to reproduce:
     * 1.Open page with players list
     */
    @BeforeMethod
    public void b4Method(){
       page.openPlayers();
    }

    /**
     * Steps to reproduce:
     * 1.Click insert button
     * 2.Fill all fields by your data according to rules that
     * Max length of String = 12,email should be like "mymail@domen.com",
     * and remember all data
     * 3.Click save button
     * 4.Set field "search by Login " to your username
     * 5.Click search
     * 6.Find your player
     * 7.Click edit button ahead your player
     * 8.Check that data at the website equals to your data
     */
    @Test
    public void createAndCheckRandomUser(){
        page.insertButton();
        page.regInit();
        page.signUp(12);
        page.saveButton();
        page.openAlrdyReg();
        page.checkInit();
        page.checkRandom();
    }

    /**
     * Steps to reproduce:
     * * 1.Click insert button
     * 2.Fill all fields by your data according to rules that
     * Max length of String = 12,email should be like "mymail@domen.com",
     * 3.Fill field username with string more than Max length;
     * 4.Click save button
     * 5.You have to see error "Username: "Yourname" is more than 12 characters long"
     */
    @Test
    public void createBigNameTest(){
        page.insertButton();
        page.regInit();
        page.signUp(30);
        page.saveButton();
        page.getError("bigUname");
    }

    /**
     * * Steps to reproduce:
     * 1.Click insert button
     * 2.Fill all fields by your data according to rules that
     * Max length of String = 12,email should be like "mymail@domen.com",
     * 3.Fill field email not according to rules(Example:"mymaildomencom")
     * 4.Click save button
     * 5.You have to see error "Email: "Yourmail"  is no valid email address in the basic format local-part@hostname"
     */
    @Test
    public void createWrongMailTest(){
        page.insertButton();
        page.regInit();
        page.signUp(12);
        page.setMail("Maelto4karu");
        page.saveButton();
        page.getError("notEmail");
    }
    @Test
    /**
     * * Steps to reproduce:
     * 1.Click insert button
     * 2.Fill all fields by your data according to rules that
     * Max length of String = 12,email should be like "mymail@domen.com",
     * Field confirm password shouldn't be same as password
     * 4.Click save button
     * 5.You have to see error "Confirm Password: Does not match Password"
     */
    public void createWrongConfPass(){
        page.insertButton();
        page.regInit();
        page.signUp(12);
        page.setConfpass("123");
        page.saveButton();
        page.getError("confPass");
    }

    /**
     * Steps to reproduce:
     * 1.Click insert button
     * 2.Click save button
     * 3.You have to see errors "
     Username: Value is required and can't be empty
     E-mail: Value is required and can't be empty
     Password: Value is required and can't be empty
     Confirm Password: Value is required and can't be empty
     "
     */
    @Test
    public void createEmptyField(){
        page.insertButton();
        page.regInit();
        page.saveButton();
        page.getError("emptyName");
        page.getError("emptyPassword");
        page.getError("emptyMail");
        page.getError("emptyConf");
    }
    @AfterTest
    public void afterTest(){
        driver.quit();
    }
}
