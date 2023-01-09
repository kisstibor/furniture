Feature: Check if delete one closing devices endpoints works
	As closing device tool user I want to be able to delete one closing devices

   Scenario: One element
   Given that we have the following closing device:
   | width  | height | depth |
   | 10 		| 12 		 | 10 	 |
   When I invoke the closing device delete endpoint
   Then Deletion completed successfully