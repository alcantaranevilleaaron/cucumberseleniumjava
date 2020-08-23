@RegressionTest
Feature: Login Functionalities
  I want to use the login page
  
  Scenario Outline: A user provides an invalid email address and phone number
		Given a user navigate to login page
		When I input the "<username>" username
		Then I will receieved an error "<errorMessage>" message
    
    Examples: 
      | username  							| errorMessage 													|
      | email@example.com				| The specified user could not be found	|
      | +370xxxxxxxx						| The specified user could not be found	|
      |													|																				|
      
  Scenario: A user provides a valid credential
		Given a user navigate to login page
		When I input a valid credential
		Then page should be in "Account Overview" page 