#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: login to IRCTC
@Login
Scenario Outline: Login to IRCTC using given username and password
Given Url is given
When Invoke browser and open url
And Enter "<username>" and "<password>"
Then click on login button

Examples:
|username|password|
|*******|********|

Scenario: Book your ticket
Given To station and From station is known
|PUNE JN - PUNE|NAGPUR - NGP|
When Enter date
Then Click on Find Trains

Scenario:
Given User is on result page
When Apply filter for Quota
Then Changed option if Quota should be displayed

@End
Scenario: Logout from IRCTC
Given User is on Home page
Then click on logout button 
