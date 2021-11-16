package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginPage {

    public WebDriver driver;

    @FindBy(xpath = "//*[@id=\"loginForm\"]/div/input[1]")
    WebElement username;
    @FindBy(xpath = "//*[@id=\"loginForm\"]/div/input[2]")
    WebElement password;
    @FindBy(xpath = "//*[@id=\"loginForm\"]/div/div/input")
    WebElement submit;
    public loginPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void loginStart(String uname,String passwd){
        username.sendKeys(uname);
        password.sendKeys(passwd);
        submit.click();
    }
}
