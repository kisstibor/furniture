
@tag
Feature: Check if the find by type endpoints works

  @tag1
  Scenario: Multiple elements
    Given that we have the following connection tools:
	   | size  | type |
	   | 3 		 | "sin" |
	   | 4     | "sarok" |
	   | 5     | "szeg" |
   When I invoke the connectiontool find type endpoint
   Then I should get the size "1" for the number of the same type
