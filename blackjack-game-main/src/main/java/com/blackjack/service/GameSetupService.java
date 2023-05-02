package com.blackjack.service;

import java.util.List;

import com.blackjack.model.AbstractPayoutCard;
import com.blackjack.model.CardTypeEnum;
import com.blackjack.model.Player;

/**
 * The GameSetupService interface provides methods for setting up the game.
 */
public interface GameSetupService {

	/**
	 * Prepares the players for the game.
	 *
	 * @param totalPlayer The total number of players.
	 * @return The list of prepared players.
	 */
	public List<Player> preparePlayers(int totalPlayer);

	/**
	 * Retrieves the list of rate cards for the game.
	 *
	 * @return The list of rate cards.
	 */
	public List<AbstractPayoutCard> getRateCards();

	/**
	 * Retrieves the list of card types for the game.
	 *
	 * @return The list of card types.
	 */
	public List<CardTypeEnum> getCardTypeEnums();

}
