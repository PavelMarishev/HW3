package Tests;

import Pages.CreateEditPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Scynet on 03.12.2016.
 */
public class EditingTests {
    private WebDriver driver;
    private CreateEditPage page;
    /**
     *    /**
     * Steps to reproduce:
     * 1.Open your browser
     * 2.Go to poker website
     * 3.Set name "admin"
     * 4.Set password "123"
     * 5.Click login
     * 6.Check that your page has title "Players"
     * 7.Click insert button
     * 8.Fill all fields by your data according to rules that
     * Max length of String = 12,email should be like "mymail@domen.com",
     * and remember all data
     * 9.Click save button
     */
    @BeforeTest
    public void b4Test(){
        driver=new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        page=new CreateEditPage(driver);
        page.login();
        Assert.assertEquals(page.getTitle(),"Players","Not logged in");
        page.insertButton();
        page.regInit();
        page.signUp(12);
        page.saveButton();
    }
    @BeforeMethod

    public void b4Method(){
    page.openPlayers();
    }
    /**
     * Steps to repoduce:
     * 1.Set field "search by Login " to your username
     * 2.Click search
     * 3.Find your player
     * 4.Click edit button ahead your player
     * 5.Fill all fields by your data according to rules that
     * Max length of String = 12,email should be like "mymail@domen.com",
     * and remember all data
     * 6.Click save button
     * 7.Set field "search by Login " to your username
     * 8.Click search
     * 9.Find your player
     * 10.Click edit button ahead your player
     * 11.Check that data at the website equals to your data
     */
    @Test
    public void editPlayerRandom(){
        page.openAlrdyReg();
        page.changeInit();
        page.changeFields(12);
        page.saveButton();
        page.openAlrdyReg();
        page.checkInit();
        page.checkRandom();
    }

    /**
     * This "J" in the name of method JDeletePlayer i placed for correct priority, cuz Java starting
     * test depending on first letter of the method name and i want to test editPlayer will be 1st.
     */
    /**
     *Steps to reproduce:
     * 1.Set field "search by Login " to your username
     * 2.Click search
     * 3.Find your player
     * 4.Click delete button ahead of your player name
     * 5.At the window that appeared click Yes;
     * 6.You have to see text "Player has been deleted"
     * 7.Set field "search by Login " to your username
     * 8.Click search
     * 9.There is no your player
     */
    @Test
    public void JDeletePlayer(){
        Assert.assertEquals(page.deleteUser(),"Player has been deleted","Deleting error");
        Assert.assertEquals(page.playerExist(),Boolean.FALSE,"Deleting error");
    }
    /**
     * There are no negative tests  cuz at this website
     * register and edit forms are same and i
     * have already made negative for creating in CreatingTests
     */
}
