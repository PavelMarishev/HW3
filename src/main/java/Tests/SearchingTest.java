package Tests;

import Pages.PlayersPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by Scynet on 02.12.2016.
 */
public class SearchingTest {
    WebDriver driver;
    PlayersPage page;

    /**
     * Steps to reproduce:
     * 1.Click insert button
     * 2.Fill all fields by your data according to rules that
     * Max length of String = 12,email should be like "mymail@domen.com",
     * and remember all data
     * 3.Click save button
     */
    @BeforeTest
    public void b4Test() {
    driver=new FirefoxDriver();
    page=new PlayersPage(driver);
    page.login();
    page.insertButton();
    page.regInit();
    page.signUp(12);
    page.saveButton();
    }

    @BeforeMethod
    public void b4Method(){
    page.resetButton();
    }

    /**
     * Steps to reproduce:
     * 1.Set field "search by Login " to your username
     * 2.Click search
     * 3.Find your player
     */
    @Test
    public void searchByName(){
        Assert.assertEquals(page.searchBy("name"),Boolean.TRUE,"Failed search by Name");
    }
    /**
     * Steps to reproduce:
     * 1.Set field "search by Email " to your email
     * 2.Click search
     * 3.Find your player
     */
    @Test
    public void searchByMail(){
        Assert.assertEquals(page.searchBy("email"),Boolean.TRUE,"Failed search by Email");
    }
    /**
     * Steps to reproduce:
     * 1.Set field "search by City" to your city
     * 2.Click search
     * 3.Find your player
     */
    @Test
    public void searchByCity(){
        Assert.assertEquals(page.searchBy("city"),Boolean.TRUE,"Failed search by City");
    }
    /**
     * Steps to reproduce:
     * 1.Set field "search by First name " to your first name
     * 2.Click search
     * 3.Find your player
     */
    @Test
    public void searchByFirstName(){
        Assert.assertEquals(page.searchBy("firstname"),Boolean.TRUE,"Failed search by First Name");
    }
    /**
     * Steps to reproduce:
     * 1.Set field "search by Last name " to your Last name
     * 2.Click search
     * 3.Find your player
     */
    @Test
    public void searchByLastName(){
        Assert.assertEquals(page.searchBy("lastname"),Boolean.TRUE,"Failed search by Last Name");
    }
    @AfterTest
    public void afterTest(){
        driver.quit();
    }

}