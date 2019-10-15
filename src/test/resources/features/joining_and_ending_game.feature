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
