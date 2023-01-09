
@tag
Feature: Check if the update endpoints works

  @tag1
  Scenario: Multiple elements
    Given that we have the following connection tools:
	   | size  | type |
	   | 3 		 | sin |
	   | 4     | sarok |
   When I invoke the connectiontool update endpoint
   Then The size after updating will be "5"
