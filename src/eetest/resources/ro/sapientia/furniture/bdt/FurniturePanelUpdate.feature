Feature: Check if the update endpoint works

  Scenario: 1 element
    Given that we have the following furniture panels:
      | width | height | depth |
      | 10    | 30     | 30    |
    Then I should get "Furniture panel not found in db" error for updating "1" "10" "20" "30"

  Scenario: Multiple element
    Given that we have the following furniture panels:
      | width | height | depth |
      | 10    | 30     | 30    |
      | 40    | 60     | 10    |
      | 50    | 10     | 70    |
    Then I should get no error for updating "2" "50" "10" "70"