Feature: Check if the update closing device endpoints works
	As closing device tool user I want to be able to update one closing device

   Scenario: One element
   Given that we have the following closing device:
   | width  | height | depth |
   | 10 		| 12 		 | 10 	 |
   When I update that closing device
   Then This height will be "9"