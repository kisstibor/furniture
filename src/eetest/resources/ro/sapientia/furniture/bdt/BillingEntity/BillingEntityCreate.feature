Feature: Check if the create endpoint works

  Scenario: Create sale when there are no other billing entities
    Given that we have the following billing entities:
      | customerName | deposit |
    Then I should be able to create billing entity "1", "1", "Lorem Ipsum", "2500.0"

  Scenario: Create sale when there are other billing entities
    Given that we have the following billing entities:
      | customerName	| deposit	|
      | Deak Adrienn	| 150.0		|
      | Elekes Rebeka 	| 200.0		|
      | Csorvasi Endre	| 0.0		|
      | Kollo Zsolt	 	| 100.0		|
    Then I should be able to create billing entity "1", "1", "Lorem Ipsum", "2500.0"
