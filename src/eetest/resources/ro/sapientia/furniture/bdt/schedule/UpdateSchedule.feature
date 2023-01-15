Feature: Check if the update endpoint works
  As an user I want to be able to update an existing schedule

  Scenario: One element
    Given that we have the following schedule:
      | product | start_date | end_date |
      | product1 | 2022-12-12T12:06:35 | 2022-12-15T14:20:00 |
    Then I should succeed in updating schedule "1" "product1" "2022-12-12T12:06:35" "2022-12-15T14:20:00"