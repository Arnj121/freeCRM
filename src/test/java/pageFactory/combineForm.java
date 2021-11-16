package pageFactory;

import cucumber.api.java.eo.Se;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class combineForm {

    public WebDriver driver;

    @FindBy(xpath = "/html/body/table[2]/tbody/tr[1]/td[2]/table/tbody/tr/td/fieldset/legend")
    WebElement combineVerify;
    @FindBy(xpath="//*[@id=\"company_name\"]")
    WebElement companyName;
    @FindBy(xpath="//*[@id=\"industry\"]")
    WebElement industry;
    @FindBy(xpath="//*[@id=\"annual_revenue\"]")
    WebElement annualrevenue;
    @FindBy(xpath="//*[@id=\"num_of_employees\"]")
    WebElement numofemp;
    @FindBy(xpath="//*[@id=\"company_phone\"]")
    WebElement phone;
    @FindBy(xpath="//*[@id=\"company_email\"]")
    WebElement email;
    @FindBy(xpath="//*[@id=\"combinedForm\"]/fieldset/table/tbody/tr[4]/td/fieldset/table/tbody/tr/td/table/tbody/tr[4]/td[2]/textarea")
    WebElement address;
    @FindBy(xpath="//*[@id=\"combinedForm\"]/fieldset/table/tbody/tr[4]/td/fieldset/table/tbody/tr/td/table/tbody/tr[8]/td[2]/input")
    WebElement country;
    @FindBy(xpath="//*[@id=\"combinedForm\"]/fieldset/table/tbody/tr[4]/td/fieldset/table/tbody/tr/td/table/tbody/tr[7]/td[2]/input")
    WebElement zipcode;
    @FindBy(xpath ="//*[@id=\"combinedForm\"]/fieldset/table/tbody/tr[1]/td/input")
    WebElement save;
    @FindBy(xpath = "//*[@id=\"combinedForm\"]/fieldset/table/tbody/tr[3]/td[1]/table/tbody/tr[6]/td[2]/select")
    WebElement status;
    @FindBy(xpath = "//*[@id=\"combinedForm\"]/fieldset/table/tbody/tr[3]/td[1]/table/tbody/tr[5]/td[2]/select")
    WebElement cat;
    @FindBy(xpath = "//*[@id=\"combinedForm\"]/fieldset/table/tbody/tr[3]/td[2]/table/tbody/tr[1]/td[2]/select")
    WebElement title;
    @FindBy(xpath = "//*[@id=\"first_name\"]")
    WebElement fname;
    @FindBy(xpath = "//*[@id=\"surname\"]")
    WebElement lname;
    @FindBy(xpath = "//*[@id=\"contact_phone\"]")
    WebElement con_phone;
    @FindBy(xpath = "//*[@id=\"contact_email\"]")
    WebElement con_email;
    @FindBy(xpath = "//*[@id=\"combinedForm\"]/fieldset/table/tbody/tr[3]/td[2]/table/tbody/tr[12]/td[2]/select")
    WebElement ucat;
    @FindBy(xpath = "//*[@id=\"combinedForm\"]/fieldset/table/tbody/tr[3]/td[2]/table/tbody/tr[13]/td[2]/select")
    WebElement ustatus;

    public combineForm(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public Boolean isCombinePageVisible(){
        return this.combineVerify.getText().equals("Combined Contact and Company Form");
    }

    public void addCompany(String[] data){
        companyName.sendKeys(data[1]);
        industry.sendKeys(data[2]);
        annualrevenue.sendKeys(data[3]);
        numofemp.sendKeys(data[4]);
        phone.sendKeys(data[7]);
        email.sendKeys(data[8]);
        address.sendKeys(data[9]);
        country.sendKeys(data[10]);
        zipcode.sendKeys(data[11]);
        Select s= new Select(status);s.selectByIndex(Integer.parseInt(data[5]));
        s= new Select(cat);s.selectByIndex(Integer.parseInt(data[6]));
        s = new Select(ucat);s.selectByIndex(Integer.parseInt(data[15]));
        s = new Select(ustatus);s.selectByIndex(Integer.parseInt(data[16]));
        title.sendKeys(data[12]);
        fname.sendKeys(data[13]);
        lname.sendKeys(data[14]);
        con_phone.sendKeys(data[17]);
        con_email.sendKeys(data[18]);
        if(Integer.parseInt(data[19])==1) driver.findElement(By.xpath("//*[@id=\"combinedForm\"]/fieldset/table/tbody/tr[3]/td[2]/table/tbody/tr[21]/td[2]/input[1]")).click();
        else driver.findElement(By.xpath("//*[@id=\"combinedForm\"]/fieldset/table/tbody/tr[3]/td[2]/table/tbody/tr[21]/td[2]/input[2]")).click();
        if(Integer.parseInt(data[20])==1) driver.findElement(By.xpath("//*[@id=\"combinedForm\"]/fieldset/table/tbody/tr[3]/td[2]/table/tbody/tr[22]/td[2]/input[1]")).click();
        else driver.findElement(By.xpath("//*[@id=\"combinedForm\"]/fieldset/table/tbody/tr[3]/td[2]/table/tbody/tr[22]/td[2]/input[2]")).click();

        save.click();
    }
    public Boolean isAddedCompanyDisplayed(String cname,String fname,String lname){
        try {
            Assert.assertEquals(fname,driver.findElement(By.xpath("//*[@id=\"first_name\"]")).getText());
            Assert.assertEquals(lname,driver.findElement(By.xpath("//*[@id=\"surname\"]")).getText());
            Assert.assertEquals(cname,driver.findElement(By.xpath("//*[@id=\"vSummary\"]/table/tbody/tr[2]/td[1]/table/tbody/tr[5]/td[2]/a")).getText());
            return true;
        }
        catch (Exception e){ return false; }
    }
}
