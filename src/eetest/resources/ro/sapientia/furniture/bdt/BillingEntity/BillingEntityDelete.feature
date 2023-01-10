Feature: Check if the delete endpoint works

  Scenario: Delete existing billing entity
    Given that we have the following billing entities:
      | customerName	| deposit	|
      | Deak Adrienn	| 150.0		|
      | Elekes Rebeka 	| 200.0		|
      | Csorvasi Endre	| 0.0		|
      | Kollo Zsolt	 	| 100.0		|
    Then I should be able to delete billing entity by id "1"