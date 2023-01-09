Feature: Check if the find all closing devices endpoints works
	As closing device tool user I want to be able to see all the closing devices

   Scenario: One element
   Given that we have the following closing device:
   | width  | height | depth |
   | 10 		| 12 		 | 10 	 |
   When I invoke the closing device all endpoint
   Then I should get the height "12" for the position "0"