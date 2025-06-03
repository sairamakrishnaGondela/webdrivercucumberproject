Feature: Navigate to Home page



  @smoke
Scenario: search with Teachers in App launcher
Given I am on login page
When  I enter "standardace@gmail.com" or "Krishna@61295" and click on login button
And  I will be navigating to Home page and validate title "Home | Salesforce"
Then I will be clicking on APP launcher and search with object
#Then I will navigate to Teachers object page





