
@tag
Feature: Check if the add endpoints works

  @tag1
  Scenario: Multiple elements
    Given that we have the following connection tools:
	   | size  | type |
	   | 3 		 | "sin" |
	   | 4     | "sarok" |
   When I invoke the connectiontool add endpoint
   Then I should succeed in creating "1" "5" "sin"
