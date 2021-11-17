Feature: verify the companies functionality
  Background: user is logged in
    Given user is on login page
    When user enters username and password and clicks on login
    Then user is displayed the home page

  @End2end
  Scenario: end-to-end working of application
    Given user is displayed the home page
    When user clicks on companies
    Then user is displayed list of companies
    When user clicks on new company
    Then user is displayed new company page
    When user enters all the information and clicks save
    Then user is displayed the details of the company
    When user clicks on companies
    Then user is displayed a full search form
    When user enters a search query and clicks search
    Then search results must be displayed
    When user can see log out button
    When user clicks on logout button
    Then user is on login page

  @AdCo
  Scenario: add company with valid credentials
    Given user is displayed the home page
    When user clicks on new company
    Then user is displayed new company page
    When user enters all the information and clicks save
    Then user is displayed the details of the company

  Scenario: add company with invalid credentials
    Given user is displayed the home page
    When user clicks on new company
    Then user is displayed new company page
    When user enters invalid information and clicks save
    Then user is displayed an error message

  Scenario: add company with blank credentials
    Given user is displayed the home page
    When user clicks on new company
    Then user is displayed new company page
    When user leaves all the fields blank and clicks save
    Then user is displayed an error message
  @AdCC
  Scenario: add company along with client with valid credentials
    Given user is displayed the home page
    When user clicks on combine form
    Then user is displayed the combine form
    When user enters all the information c&c and clicks save
    Then user is displayed the details of the company and clients added

  Scenario: add company along with client with invalid credentials
    Given user is displayed the home page
    When user clicks on combine form
    Then user is displayed the combine form
    When user enters invalid c&c information and clicks save
    Then user is displayed an error message

  @sampletest
  Scenario: add company along with client with blank credentials
    Given user is displayed the home page
    When user clicks on combine form
    Then user is displayed the combine form
    When user leaves all the c&c fields blank and clicks save
    Then user is displayed an error message

  @homeTest
  Scenario: user is displayed list of companies
    Given user is displayed the home page
    When user clicks on companies
    Then user is displayed list of companies

  @searchDis
  Scenario: search page for company
    Given user is displayed the home page
    When user clicks on companies
    Then user is displayed companies page with search fields and options

  @searchDisFul
  Scenario: full search form page
    Given user is displayed the home page
    When user clicks on full search form
    Then user is displayed a full search form

  @searchVer
  Scenario: verify search results
    Given user is displayed the home page
    When user clicks on companies
    Then user is displayed companies page with search fields and options
    When user enters a search query and clicks search
    Then search results must be displayed

  @edit
  Scenario: company details are editable
    Given user is displayed the home page
    When user clicks on companies
    Then user is displayed list of companies
    When user clicks on edit icon
    Then user is displayed a form to edit company details
    When user enters new value and clicks on save
    Then user is displayed updated form

  @delete
  Scenario: company can be removed
    Given user is displayed the home page
    When user clicks on companies
    Then user is displayed list of companies
    When user clicks on delete icon
    Then the company is removed from the list and display results are updated

  @filter
  Scenario: Check filters
    Given user is displayed the home page
    When user clicks on companies
    Then user is displayed companies page with search fields and options
    When user clicks on a filter
    Then user is displayed the filtered results


  @logout
  Scenario: user can logout after performing operations
     Given user can see log out button
     When user clicks on logout button
     Then user is on login page