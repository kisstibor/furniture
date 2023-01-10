Feature: Check if the find by id endpoint works

  Scenario: Get non existing billing entities
    Given that we have the following billing entities:
      | customerName | deposit |
    Then I should get the response code "404" for find by "3"

  Scenario: Get existing billing entities
    Given that we have the following billing entities:
      | customerName	| deposit	|
      | Deak Adrienn	| 150.0		|
      | Elekes Rebeka 	| 200.0		|
      | Csorvasi Endre	| 0.0		|
      | Kollo Zsolt	 	| 100.0		|
    Then I should get the response code "200" for find by "1"
    Then I should get the customer name "Deak Adrienn" for find by "1"
    Then I should get the deposit "150.0" for find by "1"
    Then I should get the response code "200" for find by "2"
    Then I should get the customer name "Elekes Rebeka" for find by "2"
    Then I should get the deposit "200.0" for find by "2"
    Then I should get the response code "200" for find by "3"
    Then I should get the customer name "Csorvasi Endre" for find by "3"
    Then I should get the deposit "0.0" for find by "3"
    Then I should get the response code "200" for find by "4"
    Then I should get the customer name "Kollo Zsolt" for find by "4"
    Then I should get the deposit "100.0" for find by "4"