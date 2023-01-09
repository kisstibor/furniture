Feature: Check if the find all endpoint works
  As furniture tool user I want to be able to see all the furniture panels

  Scenario: Multiple element
    Given that we have the following furniture panels:
      | width | height | depth |
      | 10    | 30     | 30    |
      | 40    | 60     | 10    |
      | 50    | 10     | 70    |
    Then I should get "40" "60" "10" for find all furniture panels position "1"
    Then I should get "50" "10" "70" for find all furniture panels position "2"