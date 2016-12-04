package Pages;

import Other.Player;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Scynet on 02.12.2016.
 */
public class PlayersPage {

    WebDriver driver;
    WebElement password;
    WebElement confpass;
    WebElement usename;
    WebElement fname;
    WebElement sname;
    WebElement email;
    WebElement city;
    WebElement adress;
    WebElement phone;
    Select dropagent;
    Select dropcountry;
    Select dropgender;
    String URL = "http://80.92.229.236:81/auth/login";
    String prevname;
    Player p;


    public PlayersPage(WebDriver wd){
        driver=wd;
    }
    public void login() {
        driver.get(URL);
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("123");
        driver.findElement(By.id("logIn")).click();
    }
    public void insertButton() {
        driver.findElement(By.xpath(".//*[@title=\"Insert\"]")).click();
    }
    public void regInit(){
        fname = driver.findElement(By.xpath(".//*[contains(@name,'fname')]"));
        usename = driver.findElement(By.xpath(".//*[contains(@name,'login')]"));
        sname = driver.findElement(By.xpath(".//*[contains(@name,'lname')]"));
        password = driver.findElement(By.xpath(".//*[contains(@name,'us_password')]"));
        confpass = driver.findElement(By.xpath(".//*[contains(@name,'confirm_password')]"));
        email = driver.findElement(By.xpath(".//*[contains (@name,'email')]"));
        city = driver.findElement(By.xpath(".//*[contains(@name,'city')]"));
        adress = driver.findElement(By.xpath(".//*[contains(@name,'address')]"));
        dropagent = new Select(driver.findElement(By.xpath(".//*[contains(@name,'owner_id')]")));
        dropcountry = new Select(driver.findElement(By.xpath(".//*[contains(@name,'country')]")));
        dropgender = new Select(driver.findElement(By.xpath(".//*[contains(@name,'gender')]")));
        phone = driver.findElement(By.xpath(".//*[contains(@name,'phone')]"));
    }
    public void signUp(int lengthOfFields) {
        p = new Player(dropagent.getOptions().size(),dropcountry.getOptions().size(),lengthOfFields);
        usename.sendKeys(p.getUsename());
        password.sendKeys(p.getPass());
        prevname=p.getUsename();
        confpass.sendKeys(p.getPass());
        email.sendKeys(p.getEmail());
        fname.sendKeys(p.getFname());
        sname.sendKeys(p.getLname());
        city.sendKeys(p.getCity());
        adress.sendKeys(p.getAdress());
        phone.sendKeys(String.valueOf(p.getPhone()));
        dropagent.selectByIndex(p.getAgent()-1);
        dropcountry.selectByIndex(p.getCountry()-1);
        dropgender.selectByIndex(p.getGender()-1);
    }
    public Boolean searchBy(String what){
        String type="Nothing";
       if(what.equals("name")){
           what="filter_panel_filter__login";
           type=p.getUsename();
       }
        if(what.equals("email")){
           what="filter_panel_filter__email";
           type=p.getEmail();
        }
        if(what.equals("city")){
            what="filter_panel_filter__city";
            type=p.getCity();
        }
        if(what.equals("firstname")){
            what="filter_panel_filter__firstname";
            type=p.getFname();
        }
        if(what.equals("lastname")){
            what="filter_panel_filter__lastname";
            type=p.getLname();
        }
        Boolean exist=false;
        driver.findElement(By.xpath(".//*[@id=\""+what+"\"]/input")).clear();
        driver.findElement(By.xpath(".//*[@id=\""+what+"\"]/input")).sendKeys(type);
        driver.findElement(By.xpath(".//*[@name=\"search\"]")).click();
        if(!driver.findElements(By.xpath(".//tr[.//a[text()=\"" + p.getUsename() + "\"]]")).isEmpty()) exist=true;
        return exist;
    }
    public void resetButton(){
        driver.findElement(By.xpath(".//*[@value='Reset']")).click();
    }

    public void saveButton() {
        driver.findElement(By.xpath(".//*[contains(@name,'button_save')]")).click();
    }
}
