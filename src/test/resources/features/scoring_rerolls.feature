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
