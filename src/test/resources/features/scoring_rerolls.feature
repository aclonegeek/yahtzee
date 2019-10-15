Feature: Scoring Rerolls

  Background: 
    Given I have rolled the dice "5 5 5 5 5"

  Scenario: Scoring With No Rerolls
    When I score in the fives section
    Then the fives section should be 25

  Scenario: Scoring With 1 Reroll of Less Than 5 Dice
    When I keep the dice "1 2 3 4"
    And I score in the fives section
    Then the fives section should be greater than or equal to 5

  Scenario: Scoring With 1 Reroll of Exactly 5 Dice
    When I keep the dice ""
    And I score in the fives section
    Then the fives section should be greater than or equal to 0

  Scenario: Scoring After 2 Rerolls
    When I keep the dice "5"
    And I keep the dice "4 5"
    And I score in the fives section
    Then the fives section should be greater than or equal to 5

  Scenario Outline: 
    When I keep the dice <diceToKeep1>
    And I keep the dice <diceToKeep2>
    And I score in the fives section
    Then the fives section should be greater than or equal to <score>
      | diceToKeep1 | diceToKeep2 | score |
      | ""          | ""          |     0 |
      | ""          | "1"         |     0 |
      | ""          | "1 2"       |     0 |
      | ""          | "1 2 3"     |     0 |
      | ""          | "1 2 3 4"   |     0 |
      | ""          | "1 2 3 4 5" |     0 |
      | "1"         | ""          |     5 |
      | "1 2"       | ""          |    10 |
      | "1 2 3"     | ""          |    15 |
      | "1 2 3 4"   | ""          |    20 |
      | "1 2 3 4 5" | ""          |    25 |
      | "1"         | "1"         |     5 |
      | "1 5"       | "2 3"       |     0 |