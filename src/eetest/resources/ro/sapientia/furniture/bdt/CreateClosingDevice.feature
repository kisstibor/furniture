Feature: Check if the create one closing device endpoints works
	As closing device tool user I want to be able to see one created closing device

   Scenario: One element
   Given that we have the following closing device:
   | width  | height | depth |
   | 10 		| 12 		 | 10 	 |
   When I create a closing device
   Then The height will be "15"