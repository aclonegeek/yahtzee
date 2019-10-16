Feature: Scoring Upper and Lower Sections

  Scenario Outline: Scoring Upper and Lower Sections
    Given I have rolled the dice <dice>
    When I score my roll in the <scoreType> section
    Then the <scoreType> section should be <score>

    Examples:
      | dice        | scoreType         | score |
      | "2 3 4 5 6" | "Aces"            |     0 |
      | "1 2 3 4 5" | "Aces"            |     1 |
      | "1 2 3 4 1" | "Aces"            |     2 |
      | "1 1 2 3 1" | "Aces"            |     3 |
      | "1 1 1 2 1" | "Aces"            |     4 |
      | "1 1 1 1 1" | "Aces"            |     5 |
      | "1 3 4 5 6" | "Twos"            |     0 |
      | "1 2 3 4 5" | "Twos"            |     2 |
      | "1 2 3 4 2" | "Twos"            |     4 |
      | "2 2 3 4 2" | "Twos"            |     6 |
      | "2 2 2 4 2" | "Twos"            |     8 |
      | "2 2 2 2 2" | "Twos"            |    10 |
      | "1 2 4 5 6" | "Threes"          |     0 |
      | "1 2 3 4 5" | "Threes"          |     3 |
      | "1 3 3 4 5" | "Threes"          |     6 |
      | "3 3 3 4 5" | "Threes"          |     9 |
      | "3 3 3 4 3" | "Threes"          |    12 |
      | "3 3 3 3 3" | "Threes"          |    15 |
      | "1 2 3 5 6" | "Fours"           |     0 |
      | "1 2 3 4 5" | "Fours"           |     4 |
      | "1 2 4 4 5" | "Fours"           |     8 |
      | "1 4 4 4 5" | "Fours"           |    12 |
      | "4 4 4 4 5" | "Fours"           |    16 |
      | "4 4 4 4 4" | "Fours"           |    20 |
      | "1 2 3 4 6" | "Fives"           |     0 |
      | "1 2 3 4 5" | "Fives"           |     5 |
      | "5 2 3 4 5" | "Fives"           |    10 |
      | "5 5 3 4 5" | "Fives"           |    15 |
      | "5 5 5 4 5" | "Fives"           |    20 |
      | "5 5 5 5 5" | "Fives"           |    25 |
      | "1 2 3 4 5" | "Sixes"           |     0 |
      | "2 3 4 5 6" | "Sixes"           |     6 |
      | "6 3 4 5 6" | "Sixes"           |    12 |
      | "6 6 4 5 6" | "Sixes"           |    18 |
      | "6 6 6 5 6" | "Sixes"           |    24 |
      | "6 6 6 6 6" | "Sixes"           |    30 |
      | "1 2 3 4 5" | "Three of a Kind" |     0 |
      | "1 2 1 6 1" | "Three of a Kind" |    11 |
      | "1 1 1 6 1" | "Three of a Kind" |    10 |
      | "1 2 3 4 5" | "Four of a Kind"  |     0 |
      | "1 2 1 1 1" | "Four of a Kind"  |     6 |
      | "1 1 1 1 1" | "Four of a Kind"  |     5 |
      | "1 2 3 5 4" | "Full House"      |     0 |
      | "2 2 4 5 4" | "Full House"      |     0 |
      | "1 1 1 3 2" | "Full House"      |     0 |
      | "5 5 5 2 2" | "Full House"      |    25 |
      | "1 1 3 2 1" | "Small straight"  |     0 |
      | "4 1 3 2 5" | "Small straight"  |    30 |
      | "1 2 3 4 4" | "Large straight"  |     0 |
      | "1 2 3 4 5" | "Large straight"  |    40 |
      | "1 2 3 5 4" | "Yahtzee"         |     0 |
      | "5 5 5 5 5" | "Yahtzee"         |    50 |
      | "1 2 3 5 4" | "Chance"          |    15 |
