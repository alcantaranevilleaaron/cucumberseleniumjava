@SmokeTest
Feature: Navigate to pages using the links provided
  I want to use the links to navigate to different pages

  Scenario Outline: Page Links Testing
		Given a user navigate to login page
		When I click the "<page>" page link
		Then page should be in "<header>" page

    Examples: 
      | page  																		| header																									|
      | Privacy Policy														| Privacy Policy																					|
      | Service agreements												| General Payment Services Agreement for Private Clients	|
      | Recommendations for the safe usage				| How to use the BankName services safely?									|
      | Bank of Lithuania													| BankName LT, UAB																					|

  Scenario Outline: Mobile App Links Testing
		Given a user navigate to login page
		When I click the "<application>" application link
		Then page should be in "BankName Mobile Wallet" page
		
		Examples: 
      | application			|
      | App Store				|
      | Google Play			|      
      
	Scenario: Activate chat support
		Given a user navigate to login page
		When I click the chat support
		Then I should be able to open a chat support window
		
	Scenario: User will navigate to registration page
		Given a user navigate to login page
		When I want to register
		Then I will be redirected to registration page
		
	Scenario: User clicks the logo
		Given a user navigate to login page
		When I click the logo
		Then I will be redirected to home page
				
		
      