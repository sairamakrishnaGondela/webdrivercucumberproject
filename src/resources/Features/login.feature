Feature: login to salesforce page




  #Scenario: logging to the salesforce

    #Given i am on login page
     # When  I enter user name and password and click on login button
      # Then  I will be landing on home page



       Scenario: logging to salesforce with correct user name and correct password
       Given I am on login page
       When  I enter "krishna61295@gmail.com" or "Krishna@61295" and click on login button
       Then  I will be navigating to Home page and validate title "Lightning Experience"
       #Examples:
       #  | Username | Password |
       #  | krishna61295@gmail.com | Krishna@61295 |
       #  | krishna61295@gmail.com | Krishna@61295 |


       @smoke
       Scenario: logging to salesforce with correct wrong user name and correct password
              Given I am on login page
              When  I enter "krishna61295@gmail.com" or "Krishna@61295" and click on login button
              Then  I will be navigating to Home page and validate title "Lightning Experience"

       @smoke
       Scenario: logging to salesforce with correct  user name and correct password with datatable
              Given I am on login page
              When  I enter  and click on login button
                     | username | krishna61295@gmail.com |
                     | password | Krishna@61295          |

                     Then  I will be navigating to Home page and validate title "Lightning Experience"
#//input[contains(@placeholder,'Search apps and items...')]
  #//button[@title='App Launcher']
  #//a[@title='New']
   # //label[text()='Status']/..//button

#  Scenario: create a case
#    Given I am on Home page
#    When  I enter "krishna61295@gmail.com" or "Krishna@61295" and click on login button
#    Then  I will be seeing success message
