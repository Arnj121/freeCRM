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
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class addCng {
    WebDriver driver;
    JavascriptExecutor js;
    loginPage lp;newCompanyPage ncp;
    homePage hp;logout lg;
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
        js.executeScript("document.querySelector(\"#navmenu > ul > li:nth-child(3) > ul > li:nth-child(1) > a\").click()");
        Assert.assertTrue(ncp.isCompanyPageVisible());
        String[] d = Arrays.copyOf(data,data.length,String[].class);
        ncp.addCompany(d);
        boolean error=false;
        try{
            driver.switchTo().alert().accept();
            Excel.writeData("src/test/java/resources/companies.xlsx","Invalid",Integer.parseInt(data[0].toString()),11);
        }
        catch (Exception e) {
            try {
                Assert.assertTrue(ncp.isAddedCompanyDisplayed(d[1]));
                Excel.writeData("src/test/java/resources/companies.xlsx", "Valid", Integer.parseInt(data[0].toString()), 11);
            } catch (Exception e1) {
                Excel.writeData("src/test/java/resources/companies.xlsx", "Invalid", Integer.parseInt(data[0].toString()), 11);
                throw new AssertionError();
            }
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
        lp = new loginPage(driver);ncp=new newCompanyPage(driver);
        hp = new homePage(driver);lg = new logout(driver);
        js = (JavascriptExecutor)driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://classic.freecrm.com/index.html");

    }

    @AfterClass
    public void ___(){
        driver.quit();
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        return Excel.readData("src/test/java/resources/companies.xlsx");
    }
    @DataProvider
    public Object[][] credential() throws IOException{
        return Excel.readData("src/test/java/resources/credentials.xlsx");
    }
}
