Feature: Scoring Yahtzee Bonus

  Scenario: Scoring Yahtzee Bonus
    Given I have already scored in the yahtzee section
    When I roll the dice "5 5 5 5 5"
    And score the dice in the yahtzee section
    And score the dice in the fives section
    Then the yahtzee section should be 150
    And the fives section should be 25
