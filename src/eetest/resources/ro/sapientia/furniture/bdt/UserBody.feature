Feature: Check if all the endpoints works
   As an user is should be able to Create, Read, Update and Delete
   So that I can manage my profile

   Scenario: Create an user
      Given I have a new user with id 2
      When I create the user
      Then the user should be created successfully

   Scenario: Get a user by id
      Given I have an user with id 1
      When I retrieve the user by id
      Then I should be able to see the user with id 1

   Scenario: Get all users
      Given I have multiple users
      When I retrieve all users
      When I retrieve all users
      Then I should be able to see all users

   Scenario: Update an user
      Given I have an user with id 1
      When I update the username to "Jason"
      Then the user should be updated successfully

   Scenario: Delete an user
      Given I have an user with id 1
      When I delete the user with id 1
      Then the user should be deleted successfully