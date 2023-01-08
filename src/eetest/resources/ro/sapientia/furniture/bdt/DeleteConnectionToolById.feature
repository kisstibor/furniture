
@tag
Feature: Check if the delet by id endpoints works

  @tag1
  Scenario: Multiple elements
    Given that we have the following connection tools:
	   | size  | type |
	   | 3 		 | "sin" |
	   | 4     | "sarok" |
	   | 5     | "szeg" |
   When I invoke the connectiontool delete id endpoint
   Then I should succeed in deleting the number of elements "2"
