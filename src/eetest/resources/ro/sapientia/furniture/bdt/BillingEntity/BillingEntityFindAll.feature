Feature: Check if the find all endpoint works

  Scenario: There are no billing entity
    Given that we have the following billing entities:
      | customerName | deposit |
    Then I should get "0" items

  Scenario: There are two billing entities
    Given that we have the following billing entities:
      | customerName	| deposit	|
      | Deak Adrienn	| 150.0		|
      | Elekes Rebeka 	| 200.0		|
      | Csorvasi Endre	| 0.0		|
      | Kollo Zsolt	 	| 100.0		|
    Then I should get "4" items
    Then I should get the deposit "150.0" for the position "0"
    Then I should get the deposit "200.0" for the position "1"
    Then I should get the deposit "0.0" for the position "2"
    Then I should get the deposit "100.0" for the position "3"
    Then I should get the customer name "Deak Adrienn" for the position "0"
    Then I should get the customer name "Elekes Rebeka" for the position "1"
    Then I should get the customer name "Csorvasi Endre" for the position "2"
    Then I should get the customer name "Kollo Zsolt" for the position "3"