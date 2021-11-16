package pageFactory;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class searchForm {

    public WebDriver driver;

    @FindBy(xpath = "/html/body/table[2]/tbody/tr[1]/td[2]/table/tbody/tr/td/fieldset/legend")
    WebElement searchVerify;
    @FindBy(xpath = "//*[@id=\"extendedSearchLayer\"]/table/tbody/tr[2]/td[1]/table/tbody/tr[3]/td[2]/input")
    WebElement searchEntry;
    @FindBy(xpath = "//*[@id=\"extendedSearchLayer\"]/table/tbody/tr[2]/td[2]/table/tbody/tr[8]/td[2]/input[2]")
    WebElement searchBtn;

    public searchForm(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public Boolean isCompanyPageVisible(){
        return this.searchVerify.getText().equals("Search for Companies");
    }

    public void enterQueryAndSearch(String query){
        searchEntry.sendKeys(query);searchBtn.click();
    }

    public Boolean verifySearchResults(String query){
        WebElement p = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[1]/td[2]/table/tbody/tr/td/form/form/table/tbody"));
        List<WebElement> c = p.findElements(By.xpath("./child::*"));
        try{
            Assert.assertTrue(c.size()>=4);
            for(int i=0;i<4;i++){c.remove(0);}
            for(WebElement w : c){
                Assert.assertTrue(w.findElement(By.xpath("./td[2]/a")).getText().contains(query));
            }
            return true;
        }
        catch (Exception e){ return false; }
    }
}
