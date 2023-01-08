
@tag
Feature: Check if the find by size endpoints works

  @tag1
  Scenario: Multiple elements
    Given that we have the following connection tools:
	   | size  | type |
	   | 3 		 | "sin" |
	   | 3     | "sarok" |
	   | 5     | "sin" |
   When I invoke the connectiontool find size endpoint
   Then I should get the size "2" for the number of the same size
