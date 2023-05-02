package com.blackjack.service;

import java.util.List;
import java.util.stream.Collectors;

import com.blackjack.model.Card;
import com.blackjack.model.Player;
import com.blackjack.model.AcePayoutCard;
import com.blackjack.model.StandardPayoutCard;
import com.blackjack.model.PlayerStatusEnum;
import com.blackjack.util.BlackjackUtil;

/**
 * The BasePlayerService is an abstract class that implements the PlayerService interface
 * and provides a common implementation for calculating the player's score.
 *
 */
public abstract class BasePlayerService implements PlayerService {

	/**
	 * Calculates the score for a player based on their cards and updates their score and status.
	 *
	 * @param player The player whose score is to be calculated.
	 */
	@Override
	public void calculateScore(Player player) {

		List<Card> cards = player.getCards();

		// Calculate the subtotal of non-Ace cards' values.
		int subTotal = cards.stream().filter(c -> !c.isAce())
				.collect(Collectors.summingInt(c ->  ((StandardPayoutCard)c.getRateCard()).getValue()));

		player.setScore(subTotal);

		// If the subtotal is greater than the maximum allowed score, set the player's status to BUSTED.
		if(subTotal > BlackjackUtil.getMaxScore()) {
			player.setStatus(PlayerStatusEnum.BUSTED);
			return;
		}

		// Collect all Ace cards.
		List<Card> aceCards = cards.stream().filter(c -> c.isAce()).collect(Collectors.toList());

		// Logic to calculate the score, taking into account the Ace cards' values.
		for (int i = 0; i < aceCards.size(); i++) {

			subTotal+= getAceValue(subTotal, ((AcePayoutCard)aceCards.get(i).getRateCard()).getValues(), 0);
			player.setScore(subTotal);

			// If the new subtotal is greater than the maximum allowed score, set the player's status to BUSTED.
			if(subTotal > BlackjackUtil.getMaxScore()) {
				player.setStatus(PlayerStatusEnum.BUSTED);
				break;
			}
		}
	}

	/**
	 * Determines the best Ace card value to use based on the current subtotal, possible Ace values, and position.
	 *
	 * @param subTotal The current subtotal of the player's score.
	 * @param aceValues A list of possible Ace card values.
	 * @param pos The current position in the list of Ace card values.
	 * @return The best Ace card value to use.
	 */
	private int getAceValue(int subTotal, List<Integer> aceValues, int pos) {

		if(pos == aceValues.size()) {
			return aceValues.get(pos-1);
		}

		if(subTotal + aceValues.get(pos) > BlackjackUtil.getMaxScore())  {
			return getAceValue(subTotal, aceValues, ++pos);
		}

		return aceValues.get(pos);
	}
}
