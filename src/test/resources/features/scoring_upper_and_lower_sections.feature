Feature: Scoring Upper and Lower Sections

  Scenario Outline: Scoring Upper and Lower Sections
    Given I have rolled the dice <dice>
    When I score my roll in the <scoreType> section
    Then the <scoreType> section should be <score>

    Examples: 
      | dice        | scoreType | score |
      | "2 3 4 5 6" | "Aces"    |     0 |
      | "1 2 3 4 5" | "Aces"    |     1 |
      | "1 1 2 3 4" | "Aces"    |     2 |
      | "1 1 1 2 3" | "Aces"    |     3 |
      | "1 1 1 1 2" | "Aces"    |     4 |
      | "1 1 1 1 1" | "Aces"    |     5 |
      | "1 3 4 5 6" | "Twos"    |     0 |
      | "1 2 3 4 5" | "Twos"    |     2 |
      | "2 1 2 3 4" | "Twos"    |     4 |
      | "2 2 1 2 3" | "Twos"    |     6 |
      | "2 2 6 2 2" | "Twos"    |     8 |
      | "2 2 2 2 2" | "Twos"    |    10 |
