@SmokeTest
Feature: Change language preference
  I want to use the links provided below to change my language preference

  Scenario Outline: Language Preference Testing
    Given a user navigate to login page
    When I click the "<language>" language link
    Then page should be in "<language>" language

    Examples: 
      | language  | 
      | lt				|
      | ru				|
      | lv				|
      | pl				|
      | bg				|
      | es				|       
      | sq				| 
			| et				|       
			| de				|        
            
