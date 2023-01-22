
@tag
Feature: Check if the add endpoints works

  @tag1
  Scenario: Multiple elements
    Given that we have the following connection tools:
	   | size  | type |
	   | 3 		 | sin |
	   | 4     | sarok |
   When I invoke the connectiontool add endpoint
   Then The size will be "5"