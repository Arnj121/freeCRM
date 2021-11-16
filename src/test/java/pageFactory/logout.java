package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class logout {
    WebDriver driver;

    @FindBy(xpath = "/html/body/table[1]/tbody/tr[2]/td[1]/div/table/tbody/tr/td[4]/a")
    WebElement logoutBtn;

    public logout(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public Boolean isLogOutBtnVisible(){ return logoutBtn.isDisplayed(); }

    public void clickLogout(){ logoutBtn.click();driver.close();}
}
