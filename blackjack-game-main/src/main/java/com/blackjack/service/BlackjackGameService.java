package com.blackjack.service;

import java.util.List;

import com.blackjack.model.Player;

/**
 * The BlackjackGameService interface defines the methods needed for managing the game
 * logic, such as setting up the game, playing cards, and determining the winner.
 *
 */
public interface BlackjackGameService {

	/**
	 * Sets up the game with the given number of players.
	 *
	 * @param number The number of players in the game.
	 */
	public void setup(int number);

	/**
	 * Gives the next card to the player and applies the game logic to determine
	 * if the player can continue playing.
	 *
	 * @param player The player who will receive the next card.
	 * @return The updated player after receiving the card.
	 */
	public Player playCard(Player player);

	/**
	 * Sets the player's status to STAND.
	 *
	 * @param player The player who will have their status set to STAND.
	 */
	public void setStand(Player player);

	/**
	 * Starts the game by dealing the initial cards to the players.
	 *
	 * @return A list of players with their initial cards.
	 */
	public List<Player> starGame();

	/**
	 * Determines the winner of the game.
	 *
	 * @return The winning player.
	 */
	public Player getWinner();
}
