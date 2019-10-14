Feature: Scoring Upper Section Bonus

  Scenario Outline: Scoring Upper Section Bonus
    Given I have a score of <upperSectionTotalScore> in the upper section
    Then the upper section bonus should be <bonusScore>

    Examples: 
      | upperSectionTotalScore | bonusScore |
      |                      0 |          0 |
      |                     63 |         35 |
      |                     69 |         35 |
