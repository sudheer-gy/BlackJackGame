package com.blackjack.service;

import com.blackjack.model.Player;
import com.blackjack.model.PlayerStatusEnum;

/**
 * The PlayerService interface provides methods for managing players in the game.
 */
public interface PlayerService {

	/**
	 * Calculates the score for the player based on the cards they have.
	 *
	 * @param player The player for which to calculate the score.
	 */
	public void calculateScore(Player player );

	/**
	 * Determines whether the player should be allowed to receive one more card, or if they have busted.
	 *
	 * @param player The player for which to determine the card allowance.
	 * @return The status of the player after considering the card allowance.
	 */
	public PlayerStatusEnum allowOneMoreCard(Player player);
}
