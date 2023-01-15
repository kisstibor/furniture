Feature: Check if the create endpoint works
    As an user I want to be able to create a new schedule

    Scenario: One element
    Given that we have the following schedule:
    | product | start_date | end_date |
    | product1 | 2022-12-12T12:06:35 | 2022-12-15T14:20:00 |
    When I invoke the furniture all endpoint
    Then I should succeed in creating "1" "product1" "2022-12-12T12:06:35" "2022-12-15T14:20:00"