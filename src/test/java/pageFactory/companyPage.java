package pageFactory;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class companyPage {

    public WebDriver driver;

    @FindBy(xpath = "//*[@id=\"navmenu\"]/ul/li[3]/a")
    WebElement companyBtn;
    @FindBy(xpath = "/html/body/table[2]/tbody/tr[1]/td[2]/table/tbody/tr/td/form/form/table/tbody")
    WebElement companyList;
    @FindBy(xpath = "/html/body/table[2]/tbody/tr[1]/td[2]/table/tbody/tr/td/form/table[1]/tbody/tr[1]/td[1]")
    WebElement companyVerify;
    @FindBy(xpath = "/html/body/table[2]/tbody/tr[1]/td[2]/table/tbody/tr/td/form/table[2]/tbody/tr")
    WebElement filters;
    public companyPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public int clickCompanyBtn(){
        this.companyBtn.click();
        return companyList.findElements(By.xpath("./child::*")).size();
    }

    public Boolean isCompanyPageVisible(){ return this.companyVerify.getText().contains("Advanced Search"); }

    public Boolean clickOnEdit(){
        List<WebElement> c = companyList.findElements(By.xpath("./child::*"));
        try {
            Assert.assertTrue(c.size()>4);WebElement f = c.get(4);
            f.findElement(By.xpath("./td[5]/a[1]")).click();return true;
        }
        catch (Exception e){ return false; }
    }

    public Boolean isEditFormVisible(){
        try{
            Assert.assertEquals("Edit Company", driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[1]/td[2]/table/tbody/tr/td/fieldset/legend")).getText());
            return true;
        }
        catch (Exception e){ return false; }
    }

    public void testEdit(String newName){
        driver.findElement(By.xpath("//*[@id=\"company_name\"]")).sendKeys(newName);
        driver.findElement(By.xpath("//*[@id=\"companyForm\"]/table/tbody/tr[7]/td/input")).click();

    }

    public Boolean verifyEdit(String newName){
        try{
            Assert.assertEquals(driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[1]/td[2]/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td[1]")).getText(),newName);
            return true;
        } catch (Exception e){return false;}
    }

    public Boolean clickOnDelete(){
        List<WebElement> c = companyList.findElements(By.xpath("./child::*"));
        try {
            Assert.assertTrue(c.size()>4);
            WebElement f = c.get(4);
            f.findElement(By.xpath("./td[5]/a[3]")).click();
            driver.switchTo().alert().accept();
            return true;
        }
        catch (Exception e){ return false; }
    }

    public Boolean isUpdated(int size){
        List<WebElement> c = companyList.findElements(By.xpath("./child::*"));
        System.out.println(size+" "+c.size());
        try { Assert.assertEquals(size-1,c.size());return true; }
        catch (Exception e){ return false; }
    }

    public void clickOnFilter(String filter){
        List<WebElement> p = filters.findElements(By.xpath("./child::*"));
        for( WebElement i:p){
            if (i.getText().toLowerCase().equals(filter.toLowerCase())){
                i.click();
                break;
            }
        }
    }
    public Boolean verifyFilter(String filter){
        List<WebElement> c = companyList.findElements(By.xpath("./child::*"));
        for(int i=0;i<4;i++){c.remove(0);}
        for(WebElement i:c){
            if(!(i.findElement(By.xpath("./td[2]/a")).getText().toLowerCase().startsWith(filter.toLowerCase()))){
                return false;
            }
        }
        return true;
    }


}
