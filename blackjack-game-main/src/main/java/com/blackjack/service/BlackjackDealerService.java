package com.blackjack.service;

import java.util.List;
import java.util.Set;

import com.blackjack.model.AbstractPayoutCard;
import com.blackjack.model.Card;
import com.blackjack.model.CardTypeEnum;
import com.blackjack.model.Player;

/**
 * The BlackjackDealerService interface provides methods for the dealer in the game.
 */
public interface BlackjackDealerService {

	/**
	 * Retrieves the next card from the available cards.
	 *
	 * @param cards The set of cards currently in play.
	 * @param rateCards The list of rate cards available.
	 * @param cardTypes The list of card types available.
	 * @return The next card to be dealt.
	 */
	public Card getNextCard(Set<Card> cards, List<AbstractPayoutCard> rateCards, List<CardTypeEnum> cardTypes);

	/**
	 * Determines the winner among the players.
	 *
	 * @param players The list of players in the game.
	 * @return The player who wins the game.
	 */
	public Player getWinner(List<Player> players);
}
