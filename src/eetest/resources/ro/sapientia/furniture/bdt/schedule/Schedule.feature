Feature: Schedule service
    As a user I want to be able to create, read, update and delete schedules.

    Scenario: Create a schedule
    Given that I have a new schedule with id 5
    When I create the schedule
    Then the schedule should be created successfully

    Scenario: Read all schedules
    Given that I have multiple schedules
    When I retrieve all schedules
    Then I should be able to see all schedules

    Scenario: Read a schedule by id
    Given that I have a schedule with id 5
    When I retrieve the schedule by id
    Then I should be able to see the schedule with id 1

    Scenario: Update a schedule
    Given that I have a schedule with id 5
    When I update the schedule product to "updated product"
    Then the schedule should be updated successfully

    Scenario: Delete a schedule
    Given that I have a schedule with id 5
    When I delete the schedule with id 5
    Then the schedule should be deleted successfully