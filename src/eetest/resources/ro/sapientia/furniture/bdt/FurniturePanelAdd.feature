Feature: Check if the add endpoint works

  Scenario: No element
    Given that we have the following furniture panels:
      | width | height | depth |
    Then I should get no error for adding "1" "100" "200" "300"

  Scenario: Multiple element
    Given that we have the following furniture panels:
      | width | height | depth |
      | 10    | 30     | 30    |
      | 40    | 60     | 10    |
      | 50    | 10     | 70    |
    Then I should get no error for adding "1" "100" "200" "300"
