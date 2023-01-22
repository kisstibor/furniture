Feature: Check if the find by id endpoint works

  Scenario: No element
    Given that we have the following furniture panels:
      | name |
    Then I should get "Furniture panel not found in db" error for id "2"

  Scenario: Multiple element
    Given that we have the following furniture panels:
      | width | height | depth |
      | 10    | 30     | 30    |
      | 40    | 60     | 10    |
      | 50    | 10     | 70    |
    Then I should get "10" "30" "30" for find furniture panel by id "0"