Feature: Check if the find all endpoints works
	As furniture tool user I want to be able to see all the furnitures

   Scenario: One element
   Given that we have the following furniture bodies:
   | id	 | created_at	| furniture_type	| notification_frequency	| receive_email_notification	| receive_sms_notification	| updated_at
   | 1	 | 1/12/2023 8:33	| kitchen_furniture	| 10	| 10	| 10	| 1/12/2023 8:33
   | 2	 | 1/11/2023 8:33	| kitchen_furniture	| 10	| 10	| 10	| 1/11/2023 8:33
   When I invoke the furniture all endpoint
   Then I should get the id "10" for the position "3"