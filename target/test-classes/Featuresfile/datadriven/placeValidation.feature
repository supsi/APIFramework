Feature: Validating Place API's


@AddPlace @Regression
Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI

Given Add Place Payload with "<name>" "<language>" "<address>"
When user calls "addPlaceAPI" with "POST" htpps request 
Then the API call got success with status code 200
And "status" in response code is "OK"
And "scope" in response code is "APP"
And verify place_Id  created maps to "<name>"  using "getPlaceAPI"  

Examples: 
|name       |language          |address            |
|suprava    |english           |DVC Mayapur        |
#|souvik     |hindi             |arambagh           |

@DeletePlace @Regression
Scenario: Verify if Place is being successfully deleted using DeletePlaceAPI

Given Delete Place Payload 
When user calls "deletePlaceAPI" with "POST" htpps request 
Then the API call got success with status code 200
And "status" in response code is "OK"
