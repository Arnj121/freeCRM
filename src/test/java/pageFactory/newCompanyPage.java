package pageFactory;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class newCompanyPage {

    public WebDriver driver;

    @FindBy(xpath = "/html/body/table[2]/tbody/tr[1]/td[2]/table/tbody/tr/td/fieldset/legend")
    WebElement companyVerify;
    @FindBy(xpath="//*[@id=\"company_name\"]")
    WebElement companyName;
    @FindBy(xpath="//*[@id=\"companyForm\"]/table/tbody/tr[2]/td[1]/table/tbody/tr[2]/td[2]/input")
    WebElement industry;
    @FindBy(xpath="//*[@id=\"annual_revenue\"]")
    WebElement annualrevenue;
    @FindBy(xpath="//*[@id=\"num_of_employees\"]")
    WebElement numofemp;
    @FindBy(xpath="//*[@id=\"phone\"]")
    WebElement phone;
    @FindBy(xpath="//*[@id=\"email\"]")
    WebElement email;
    @FindBy(xpath="//*[@id=\"companyForm\"]/table/tbody/tr[3]/td/fieldset/table/tbody/tr/td/table/tbody/tr[4]/td[2]/textarea")
    WebElement address;
    @FindBy(xpath="//*[@id=\"companyForm\"]/table/tbody/tr[3]/td/fieldset/table/tbody/tr/td/table/tbody/tr[8]/td[2]/input")
    WebElement country;
    @FindBy(xpath="//*[@id=\"companyForm\"]/table/tbody/tr[3]/td/fieldset/table/tbody/tr/td/table/tbody/tr[7]/td[2]/input")
    WebElement zipcode;
    @FindBy(xpath ="/html/body/table[2]/tbody/tr[1]/td[2]/table/tbody/tr/td/fieldset/form/table/tbody/tr[1]/td/input")
    WebElement save;
    @FindBy(xpath = "//*[@id=\"companyForm\"]/table/tbody/tr[2]/td[1]/table/tbody/tr[5]/td[2]/select")
    WebElement status;
    @FindBy(xpath = "//*[@id=\"companyForm\"]/table/tbody/tr[2]/td[1]/table/tbody/tr[6]/td[2]/select")
    WebElement cat;

    public newCompanyPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public Boolean isCompanyPageVisible(){
        return this.companyVerify.getText().equals("Create New Company");
    }

    public void addCompany(String[] data){
        if(!data[1].isBlank())
            companyName.sendKeys(data[1]);
        industry.sendKeys(data[2]);
        annualrevenue.sendKeys(data[3]);
        numofemp.sendKeys(data[4]);
        phone.sendKeys(data[7]);
        email.sendKeys(data[8]);
        address.sendKeys(data[9]);
        country.sendKeys(data[10]);
        zipcode.sendKeys(data[11]);
        Select s= new Select(status);
        try{ s.selectByIndex(Integer.parseInt(data[5])); }
        catch (Exception e){s.selectByIndex(0);}
        s= new Select(cat);
        try{s.selectByIndex(Integer.parseInt(data[6]));}
        catch (Exception e){s.selectByIndex(0);}
        save.click();
    }
    public Boolean isAddedCompanyDisplayed(String name){
        try {
            Assert.assertEquals(driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[1]/td[2]/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td[1]")).getText(),name);
            return true;
        }
        catch (Exception e){ return false; }
    }
}
