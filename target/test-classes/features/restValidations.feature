Feature: Validating rest API's
@Users @Regression
Scenario: Verify list of users
	Given Goto to url
	When User calls "GoRestAPI" with http get request
	Then the API call got success with status code 200


Scenario Outline: Update the user
	Given Goto to url and search "user"
	When Update "user" with http PUT request
	Then the API call got success with status code 200
	Then verify the "<email>"	
	
	Examples:
	| email |
	| test@test.com |
	
Scenario Outline: Verify active and inactive users
	Given Goto to url
	When Filter "<userstatus>" with http get request
	Then the API call got success with status code 200
	Then Get "<userstatus>" count
	
		Examples:
	| userstatus |
	| Active |
	| Inactive |
	
