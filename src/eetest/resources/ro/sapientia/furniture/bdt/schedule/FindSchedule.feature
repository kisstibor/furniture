Feature: Check if the find endpoint works
    As an user I want to be able to find a schedule by id

    Scenario: Find the element with the id 1
    Given that we have the following schedule:
    | id  | product | start_date | end_date |
    | 1   | product | 2022-12-12T12:06:35 | 2022-12-15T14:20:00 |
    When I invoke the furniture all endpoint
    Then I should get the product "product" for the id 1