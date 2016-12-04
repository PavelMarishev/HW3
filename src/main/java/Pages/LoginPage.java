package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Scynet on 02.12.2016.
 */
public class LoginPage {
    private WebDriver driver;
    String URL="http://80.92.229.236:81/auth/login";
    public LoginPage(WebDriver dri){
        driver=dri;
    }
    public void open(){
        driver.get(URL);
    }
    public void login(String name, String password){
        driver.findElement(By.id("username")).sendKeys(name);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("logIn")).click();
    }
    public String getTitle(){
        return driver.getTitle();
    }


    public String getErrorMessage(String which) {
        String elem="Error";
        if(which.equals("First error")) {
            WebElement wel=driver.findElement(By.xpath(".//*[@class='errors']/li"));
            if(wel.getLocation().getY()<200) elem=wel.getText();
        }else{
            List<WebElement> wl = driver.findElements(By.xpath(".//*[@class='errors']/li"));;
            for(int i=0;i<wl.size();i++){
                if(wl.get(i).getLocation().getY()>=200) elem=wl.get(i).getText();
            }
        }


        return elem;
    }


}
