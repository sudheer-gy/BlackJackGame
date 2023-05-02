package com.blackjack.service;

import com.blackjack.model.Player;
import com.blackjack.model.PlayerStatusEnum;

/**
 * The UserPlayerServiceImpl class extends the BasePlayerService class and provides methods specific to user players.
 */
public class UserPlayerServiceImpl extends BasePlayerService {

	/**
	 * Allows the user player to receive one more card.
	 *
	 * @param player The player to allow one more card.
	 * @return The status of the player.
	 */
	@Override
	public PlayerStatusEnum allowOneMoreCard(Player player) {
		calculateScore(player);
		return player.getStatus();
	}
}
