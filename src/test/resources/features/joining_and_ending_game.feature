Feature: Joining and Ending a Game

  Scenario: Launching the server and joining the game.
    Given the server is running on port 8002
    When I connect to the server on port 8002
    And I enter "John Wick" as my player name
    Then the player count should be 1
    And player 1 should have the name "John Wick"

  Scenario: Launching the server, joining the game, and ending the game.
    Given the server is running on port 8003
    When I connect to the server on port 8003
    And I enter "John Wick" as my player name
    And I enter "y" to "begin" the game
    And I enter "q" to "end" the game
    Then the game should end

  Scenario: Launching the server, 3 players joining the game and starting it, and switching from one player to the next one.
    Given the server is running on port 8004
    When player 1 connects to the server on port 8004
    And player 2 connects to the server on port 8004
    And player 3 connects to the server on port 8004
    And player 1 enters "A" "as their player name"
    And player 2 enters "B" "as their player name"
    And player 3 enters "C" "as their player name"
    And player 1 enters "y" "to begin the game"
    And player 1 enters "" "to roll"
    And player 1 enters "3" "to score"
    And player 1 enters "1" "to score in the Aces section"
    Then the game is active
    And the current player should be player 2
