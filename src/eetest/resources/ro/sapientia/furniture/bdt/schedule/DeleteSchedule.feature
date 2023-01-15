Feature: Check if the delete endpoint works
  As an user I want to be able to delete an existing schedule

  Scenario: No element
    Given that we have the following schedule:
     | product |
    Then I should get "NOT_FOUND" error for deleting schedule by id "99"

  Scenario: One element
    Given that we have the following schedule:
      | product | start_date | end_date |
      | product1 | 2022-12-12T12:06:35 | 2022-12-15T14:20:00 |
    Then I should succeed in deleting schedule by id "1"