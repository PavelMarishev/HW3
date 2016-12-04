package Pages;


import Other.Player;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class CreateEditPage {
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

    public CreateEditPage(WebDriver dri) {
        driver = dri;
    }

    public void openPlayers() {
        driver.get("http://80.92.229.236:81/players");
    }

    public void login() {
        driver.get(URL);
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("123");
        driver.findElement(By.id("logIn")).click();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void insertButton() {
        driver.findElement(By.xpath(".//*[@title=\"Insert\"]")).click();
    }

    public void cleaner() {
        fname.clear();
        sname.clear();
        city.clear();
        phone.clear();
        email.clear();
        adress.clear();
        phone.clear();
    }
    public void regInit(){
        usename = driver.findElement(By.xpath(".//*[contains(@name,'login')]"));
        fname = driver.findElement(By.xpath(".//*[contains(@name,'fname')]"));
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
        password.sendKeys(p.getPass());
        usename.sendKeys(p.getUsename());
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
    public String deleteUser(){
        driver.findElement(By.xpath(".//*[@id=\"filter_panel_filter__login\"]/input")).clear();
        driver.findElement(By.xpath(".//*[@id=\"filter_panel_filter__login\"]/input")).sendKeys(p.getUsename());
        driver.findElement(By.xpath(".//*[@name=\"search\"]")).click();
        driver.findElement(By.xpath(".//tr[.//a[text()='"+p.getUsename()+"']]//img[@alt='Delete']")).click();
        driver.switchTo().alert().accept();
       return driver.findElement(By.xpath(".//div[contains(@id,\"flashmessagespanel\")]/ul/li")).getText();
    }
    public Boolean playerExist(){
        Boolean exist=false;
        driver.findElement(By.xpath(".//*[@id=\"filter_panel_filter__login\"]/input")).clear();
        driver.findElement(By.xpath(".//*[@id=\"filter_panel_filter__login\"]/input")).sendKeys(p.getUsename());
        driver.findElement(By.xpath(".//*[@name=\"search\"]")).click();
        if(!driver.findElements(By.xpath(".//tr[.//a[text()=\"" + p.getUsename() + "\"]]")).isEmpty()) exist=true;
        return exist;
    }
    public void checkInit(){
        usename = driver.findElement(By.xpath(".//*[contains(@name,'login')]"));
        fname = driver.findElement(By.xpath(".//*[contains(@name,'fname')]"));
        sname = driver.findElement(By.xpath(".//*[contains(@name,'lname')]"));
        email = driver.findElement(By.xpath(".//*[contains (@name,'email')]"));
        city = driver.findElement(By.xpath(".//*[contains(@name,'city')]"));
        adress = driver.findElement(By.xpath(".//*[contains(@name,'address')]"));
        phone = driver.findElement(By.xpath(".//*[contains(@name,'phone')]"));
    }
    public void changeInit(){
        fname = driver.findElement(By.xpath(".//*[contains(@name,'fname')]"));
        sname = driver.findElement(By.xpath(".//*[contains(@name,'lname')]"));
        email = driver.findElement(By.xpath(".//*[contains (@name,'email')]"));
        city = driver.findElement(By.xpath(".//*[contains(@name,'city')]"));
        adress = driver.findElement(By.xpath(".//*[contains(@name,'address')]"));
        dropagent = new Select(driver.findElement(By.xpath(".//*[contains(@name,'owner_id')]")));
        dropcountry = new Select(driver.findElement(By.xpath(".//*[contains(@name,'country')]")));
        dropgender = new Select(driver.findElement(By.xpath(".//*[contains(@name,'gender')]")));
        phone = driver.findElement(By.xpath(".//*[contains(@name,'phone')]"));
    }
    public void changeFields(int lengthOfFields){
        cleaner();
        p = new Player(dropagent.getOptions().size(),dropcountry.getOptions().size(),prevname,lengthOfFields);
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
    public void saveButton() {
        driver.findElement(By.xpath(".//*[contains(@name,'button_save')]")).click();
    }
    public void openAlrdyReg(){
        driver.findElement(By.xpath(".//*[@id=\"filter_panel_filter__login\"]/input")).clear();
        driver.findElement(By.xpath(".//*[@id=\"filter_panel_filter__login\"]/input")).sendKeys(p.getUsename());
        driver.findElement(By.xpath(".//*[@name=\"search\"]")).click();
        driver.findElement(By.xpath(".//tr[.//a[text()=\""+p.getUsename()+"\"]]//img[@alt=\"Edit\"]")).click();
    }
    public void checkRandom(){
        SoftAssert soft =new SoftAssert();
       soft.assertEquals(email.getAttribute("value"),p.getEmail(),"Wrong Email");
        soft.assertEquals(usename.getAttribute("value"),p.getUsename(),"Wrong Username");
        soft.assertEquals(fname.getAttribute("value"),p.getFname(),"Wrong First name");
        soft.assertEquals(sname.getAttribute("value"),p.getLname(),"Wrong Last name");
        soft.assertEquals(city.getAttribute("value"),p.getCity(),"Wrong City");
        soft.assertEquals(adress.getAttribute("value"),p.getAdress(),"Wrong Address");
        soft.assertEquals(phone.getAttribute("value"),p.getPhone(),"Wrong Phone number");
        soft.assertAll();
    }
    public void setMail(String mael){
        p.setEmail(mael);
        email.clear();
        email.sendKeys(p.getEmail());
    }
    public void setConfpass(String conf){
        confpass.clear();
        confpass.sendKeys(conf);
    }
   public void getError(String errorAct){
       String error="Wrong";

        if(errorAct.equals("bigUname")) errorAct="Username: '"+p.getUsename()+"' is more than 12 characters long";
        if(errorAct.equals("confPass")) errorAct="Confirm Password: Does not match Password";
        if(errorAct.equals("notEmail")) errorAct="E-mail: '"+p.getEmail()+"' is no valid email address in the basic format local-part@hostname";
        if(errorAct.equals("emptyName")) errorAct="Username: Value is required and can't be empty";
        if(errorAct.equals("emptyMail")) errorAct="E-mail: Value is required and can't be empty";
        if(errorAct.equals("emptyPassword"))errorAct="Password: Value is required and can't be empty";
        if(errorAct.equals("emptyConf"))errorAct="Confirm Password: Value is required and can't be empty";
       List<WebElement> el=driver.findElements(By.xpath(".//*[@class=\"form_errors_container\"]/ul/li"));

       for(int i=0;i<el.size();i++){
           if(el.get(i).getText().equals(errorAct)){
               error=el.get(i).getText();
               break;
           }
       }
       Assert.assertEquals(error,errorAct,"Field rule is not working");
   }
}
