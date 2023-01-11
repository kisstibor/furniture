Feature: Shipment service
  As a user
  I want to be able to create, read, update, and delete shipments
  So that I can manage shipments in my application

Scenario: Create a shipment
  Given I have a new shipment with street "Mak"
  When I create the shipment
  Then the shipment should be created successfully

Scenario: Read all shipments
  Given I have multiple shipments
  When I retrieve all shipments
  Then I should be able to see all the shipments

Scenario: Read a shipment by id
  Given I have a shipment with id 1
  When I retrieve the shipment by id
  Then I should be able to see the shipment with id 1

Scenario: Update a shipment
  Given I have a shipment with id 1
  When I update the shipment street to "Szezam"
  Then the shipment should be updated successfully

Scenario: Delete a shipment
  Given I have a shipment with id 1
  When I delete the shipment with id 1
  Then the shipment should be deleted successfully
