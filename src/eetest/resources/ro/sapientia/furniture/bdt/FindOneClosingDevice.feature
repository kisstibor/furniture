Feature: Check if the find one endpoints works
	As closing device tool user I want to be able to see one closing devices

   Scenario: One element
   Given that we have the following closing device:
   | width  | height | depth |
   | 10 		| 12 		 | 10 	 |
   When I invoke the closing device find by id endpoint
   Then I should get the height "12"