
@tag
Feature: Check if the find by id endpoints works

  @tag1
  Scenario: Multiple elements
    Given that we have the following connection tools:
	   | size  | type |
	   | 3 		 | "sin" |
	   | 4     | "sarok" |
	   | 5     | "sin" |
   When I invoke the connectiontool find id endpoint
   Then I should get the type "sin" for the id "1"
