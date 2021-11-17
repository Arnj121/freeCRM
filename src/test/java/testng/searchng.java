package testng;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageFactory.*;
import util.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class searchng {

    WebDriver driver;
    JavascriptExecutor js;
    loginPage lp;companyPage cp;searchForm sf;
    homePage hp;logout lg; int size;
    @Test(priority = 1,dataProvider = "credential")
    public void __1(Object[] data) {
        lp.loginStart(data[1].toString(),data[2].toString());
        driver.switchTo().frame(driver.findElement(By.xpath("/html/frameset/frame[2]")));
    }
    @Test(priority = 2)
    public void __2() throws IOException {
        try{
            Assert.assertTrue(hp.verifyName("arjun d"));
            Excel.writeData("src/test/java/resources/credentials.xlsx","Valid",1,2);
        }
        catch (Exception e){
            Excel.writeData("src/test/java/resources/credentials.xlsx","Invalid",1,2);
            throw new AssertionError();
        }
    }
    @Test(priority = 3,dataProvider = "getData")
    public void __3(Object[] data) throws IOException {
        size = cp.clickCompanyBtn();
        Assert.assertTrue(cp.isCompanyPageVisible());
        sf.enterQueryAndSearch(data[1].toString());
        try{
            Assert.assertTrue(sf.verifySearchResults(data[1].toString()));
            Excel.writeData("src/test/java/resources/search.xlsx","Valid",Integer.parseInt(data[0].toString()),1);
        }
        catch (Exception e){
            Excel.writeData("src/test/java/resources/search.xlsx","Invalid",Integer.parseInt(data[0].toString()),1);
            throw new AssertionError();
        }
    }
    @Test(priority = 4)
    public void __4(){
        Assert.assertTrue(lg.isLogOutBtnVisible());
        lg.clickLogout();
    }

    @BeforeClass
    public void ____(){
        System.setProperty("webdriver.chrome.driver","G:\\chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        lp = new loginPage(driver);cp=new companyPage(driver);
        hp = new homePage(driver);lg = new logout(driver);
        js = (JavascriptExecutor)driver;sf = new searchForm(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://classic.freecrm.com/index.html");
    }

    @AfterClass
    public void __(){
        driver.quit();
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        return Excel.readData("src/test/java/resources/search.xlsx");
    }
    @DataProvider
    public Object[][] credential() throws IOException{
        return Excel.readData("src/test/java/resources/credentials.xlsx");
    }
}

