## Blackjack Game
This is a Java application that plays the game of Blackjack (aka "21"). The computer acts as the dealer, and there can be up to three other players.

## Requirements
Java JDK (version 11 or higher) Apache Maven (for building the project) How to Run the Game Clone the repository or download the source code.

Open a terminal or command prompt and navigate to the project's root directory.

Build the project using Maven:
mvn clean package Run the game using the following command:

java -jar target/blackjack-game.jar <number_of_players> Replace <number_of_players> with the desired number of players (up to three).

## Game Rules
The game is played with a standard deck of 52 cards.
The goal is to get as close to 21 as possible without going over.
The value of face cards (Jack, Queen, King) is 10, and the value of an Ace is 1 or 11 (player's choice).
Players are dealt two cards initially and can choose to "hit" (receive another card) or "stand" (stop receiving cards).
If a player's card total exceeds 21, they are "busted" and lose the game. After all players have completed their turns, the dealer plays their hand based on a flexible strategy.
The dealer must hit if their card total is 16 or less, and must stand if it is 17 or more.
If the dealer busts, all remaining players win. If the dealer does not bust, the player with a total closest to 21 wins. In case of a tie, the dealer wins.
