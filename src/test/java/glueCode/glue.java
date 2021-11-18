package glueCode;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import pageFactory.*;
import util.*;

public class glue {
    public static WebDriver driver;
    public static JavascriptExecutor js;
    public int size;
    public String cname,query,fname,lname;
    public homePage hp;loginPage lp;companyPage cp;newCompanyPage ncp;
    searchForm sf; combineForm cf;logout lbtn;
    @Given("^user is on login page$")
    public void user_is_on_login_page() {
        System.setProperty("webdriver.chrome.driver","G:\\chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        js = (JavascriptExecutor)driver;
        lp = new loginPage(driver);
        hp = new homePage(driver);
        cp = new companyPage(driver);
        ncp = new newCompanyPage(driver);
        sf = new searchForm(driver);
        cf = new combineForm(driver);
        lbtn = new logout(driver);
        String loginTitle = "CRMPRO - CRM software for customer relationship management, sales, and support.";
        String url="https://classic.crmpro.com/index.html";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);
        try { Assert.assertEquals(driver.getTitle(), loginTitle); }
        catch (Exception e) { System.out.println("Can't load login page"); }
    }

    @When("^user enters username and password and clicks on login$")
    public void userEntersUsernameAndPasswordAndClicksOnLogin() {
        lp.loginStart("Arnj121","~/Arn121654");
        driver.switchTo().frame(driver.findElement(By.xpath("/html/frameset/frame[2]")));
    }

    @Then("^user is displayed the home page$")
    public void user_is_displayed_home_page() {
        try { Assert.assertTrue(hp.verifyName("arjun d")); }
        catch (Exception e){ System.out.println("Can't display home page"); }
    }

    @When("^user clicks on companies$")
    public void userClicksOnCompanies() { size = cp.clickCompanyBtn(); }

    @Then("^user is displayed companies page with search fields and options$")
    public void userIsDisplayedCompaniesPageWithSearchFieldsAndOptions() {
        try{ Assert.assertTrue(cp.isCompanyPageVisible()); }
        catch (Exception e){ System.out.println("Can't display companies page"); }
    }

    @Then("^user is displayed list of companies$")
    public void userIsDisplayedListOfCompanies() {
        try{ Assert.assertTrue(size>=4); }
        catch (Exception e){ System.out.println("can't display list of companies"); }
    }

    @When("^user clicks on new company$")
    public void userClicksOnNewCompany() {
        js.executeScript("document.querySelector(\"#navmenu > ul > li:nth-child(3) > ul > li:nth-child(1) > a\").click()");
    }

    @Then("^user is displayed new company page$")
    public void userIsDisplayedNewCompanyPage() {
        try{ Assert.assertTrue(ncp.isCompanyPageVisible()); }
        catch (Exception e){ System.out.println("Can't display new company page"); }
    }

    @Then("^user is displayed an error message$")
    public void userIsDisplayedAnErrorMessage() {
        try{ driver.switchTo().alert().accept(); }
        catch (Exception e){
            System.out.println("Failed");
        }
    }

    @When("^user clicks on combine form$")
    public void userClicksOnCombineForm() {
        js.executeScript("document.querySelector(\"#navmenu > ul > li:nth-child(3) > ul > li:nth-child(2) > a\").click()");
    }

    @Then("^user is displayed the combine form$")
    public void userIsDisplayedTheCombineForm() {
        try{ Assert.assertTrue(cf.isCombinePageVisible()); }
        catch (Exception e){ System.out.println("Can't display combine form"); }
    }


    @When("^user clicks on full search form$")
    public void userClicksOnFullSearchForm() {
        js.executeScript("document.querySelector(\"#navmenu > ul > li:nth-child(3) > ul > li:nth-child(3) > a\").click()");
    }

    @Then("^user is displayed a full search form$")
    public void userIsDisplayedAFullSearchForm() {
        try { Assert.assertTrue(sf.isCompanyPageVisible()); }
        catch (Exception e){ System.out.println("cannot display full search form"); }
    }

    @When("^user enters a search query and clicks search$")
    public void userEntersASearchQueryAndClicksSearch() {
        //consider name
        query="app";
        sf.enterQueryAndSearch(query);
    }

    @Then("^search results must be displayed$")
    public void searchResultsMustBeDisplayed() {
        try { Assert.assertTrue(sf.verifySearchResults(query)); }
        catch (Exception e){ System.out.println("Search results were inaccurate"); }
    }

    @When("^user clicks on edit icon$")
    public void userClicksOnEditIcon() {
        try { Assert.assertTrue(cp.clickOnEdit()); }
        catch (Exception e){ System.out.println("No companies added to edit"); }
    }

    @Then("^user is displayed a form to edit company details$")
    public void userIsDisplayedAFormToEditCompanyDetails() {
        try{ Assert.assertTrue(cp.isEditFormVisible()); }
        catch (Exception e){ System.out.println("Can't display editable form"); }
    }

    @When("^user clicks on delete icon$")
    public void userClicksOnDeleteIcon() {
        try { Assert.assertTrue(cp.clickOnDelete()); }
        catch (Exception e){ System.out.println("No companies added to edit"); }
    }

    @Then("^the company is removed from the list and display results are updated$")
    public void theCompanyIsRemovedFromTheListAndDisplayResultsAreUpdated() {
        try { Assert.assertTrue(cp.isUpdated(size)); }
        catch (Exception e){ System.out.println("Deletion failed"); }
    }


    @When("^user enters all the information and clicks save$")
    public void userEntersAllTheInformationAndClicksSave() throws IOException {
        String[][] data = Excel.readData("src/test/java/resources/companies.xlsx");
        cname = data[0][1];ncp.addCompany(data[0]);
    }


    @When("^user enters invalid information and clicks save$")
    public void userEntersInvalidInformationAndClicksSave() {
    }

    @When("^user leaves all the fields blank and clicks save$")
    public void userLeavesAllTheFieldsBlankAndClicksSave() {
        String[] data = new String[]{"1","","","","","","","","","","",""};
        ncp.addCompany(data);
    }

    @When("^user leaves all the c&c fields blank and clicks save$")
    public void userLeavesAllTheccFieldsBlankAndClicksSave() {
        String[] data = new String[]{"","","","","","","","","","","","","","","","","","","","",""};
        cf.addCompany(data);
    }

    @When("^user enters all the information c&c and clicks save$")
    public void entersAllTheInformationAndClicksSave() throws IOException {
        String[][] data = Excel.readData("src/test/java/resources/companiesClient.xlsx");
        cname = data[0][1];fname=data[0][13];lname=data[0][14];cf.addCompany(data[0]);
    }

    @When("^user enters invalid c&c information and clicks save$")
    public void userEntersInvalidInformationClicksSave() {
    }

    @Then("^user is displayed the details of the company$")
    public void userIsDisplayedTheDetailsOfTheCompany() {
        try { Assert.assertTrue(ncp.isAddedCompanyDisplayed(cname)); }
        catch (Exception e){ System.out.println("Can't display the details of the company"); }
    }

    @Then("^user is displayed the details of the company and clients added$")
    public void userIsDisplayedTheDetailsOfTheCompanyAndClientsAdded() {
        try { Assert.assertTrue(cf.isAddedCompanyDisplayed(cname,fname,lname)); }
        catch (Exception e){ System.out.println("Incorrect information displayed"); }
    }

    @Given("^user can see log out button$")
    public void userCanSeeLogOutButton() {
        try{ Assert.assertTrue(lbtn.isLogOutBtnVisible()); }
        catch (Exception e){System.out.println("Logout button not visible");}
    }

    @When("^user clicks on logout button$")
    public void userClicksOnLogoutButton() { lbtn.clickLogout(); }

    @When("^user enters new value and clicks on save$")
    public void userEntersNewValueAndClicksOnSave() {
        cp.testEdit("kjnkjdfg");
    }

    @Then("^user is displayed updated form$")
    public void userIsDisplayedUpdatedForm() {
        try{ Assert.assertTrue(cp.verifyEdit("kjnkjdfg"));}
        catch (Exception e){System.out.println("Unable to edit");}
    }

    @When("^user clicks on a filter$")
    public void userClicksOnAFilter() {
        cp.clickOnFilter("a");
    }

    @Then("^user is displayed the filtered results$")
    public void userIsDisplayedTheFilteredResults() {
        Assert.assertTrue(cp.verifyFilter("a"));
    }
}