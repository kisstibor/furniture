Feature: Check if the history endpoint works
As a user I want to be able to see all the history entries

Scenario: One element
Given that we have the following history entries:
| userId | orderId | name |
| 1 | 1 | John |
When I invoke the history all endpoint
Then I should get the name "John" for the position "0"