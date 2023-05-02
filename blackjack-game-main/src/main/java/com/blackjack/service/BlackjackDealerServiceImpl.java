package com.blackjack.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import com.blackjack.model.AbstractPayoutCard;
import com.blackjack.model.BlackjackErrorCodes;
import com.blackjack.model.Card;
import com.blackjack.model.CardTypeEnum;
import com.blackjack.model.Player;
import com.blackjack.util.BlackjackGameException;

/**
 * The BlackjackDealerServiceImpl class implements the BlackjackDealerService interface and provides
 * the functionality for the dealer in the game.
 */
public class BlackjackDealerServiceImpl implements BlackjackDealerService {

	/**
	 * Retrieves the next card randomly (card type and value).
	 *
	 * @param cards      The set of cards currently in play.
	 * @param rateCards  The list of rate cards available.
	 * @param cardTypes  The list of card types available.
	 * @return The next card to be dealt.
	 */
	@Override
	public Card getNextCard(Set<Card> cards, List<AbstractPayoutCard> rateCards, List<CardTypeEnum> cardTypes) {
		Random random = new Random();
		int number = random.nextInt(rateCards.size());

		AbstractPayoutCard rateCard = rateCards.get(number);

		String name = rateCard.getCardName();
		Card card = new Card(name, cardTypes.get(random.nextInt(cardTypes.size())));
		card.setRateCard(rateCard);

		while (cards.contains(card)) {
			card = getNextCard(cards, rateCards, cardTypes);
			break;
		}

		return card;
	}

	/**
	 * Determines the winner among the players.
	 *
	 * @param players The list of players in the game.
	 * @return The player who wins the game.
	 */
	@Override
	public Player getWinner(List<Player> players) {
		if (players.isEmpty() || players.stream().allMatch(p -> p.isBusted())) {
			throw new BlackjackGameException(BlackjackErrorCodes.NO_WINNER);
		}

		List<Player> playersTemp = players.stream().filter(p -> !p.isBusted()).collect(Collectors.toList());

		Comparator<Player> comparator = (p1, p2) -> {
			if (p2.getScore() == p1.getScore() && p1.isDealer()) {
				return -1;
			}
			return Integer.compare(p2.getScore(), p1.getScore());
		};

		Collections.sort(playersTemp, comparator);

		validateTieBetweenNoDealerPlayer(playersTemp);

		return playersTemp.get(0);
	}

	/**
	 * Validates if there is a tie between non-dealer players.
	 *
	 * @param players The list of players.
	 */
	private void validateTieBetweenNoDealerPlayer(List<Player> players) {
		if (players.size() > 1) {
			if (!players.get(0).isDealer() && players.get(0).getScore() == players.get(1).getScore()) {
				throw new BlackjackGameException(BlackjackErrorCodes.NO_WINNER);
			}
		}
	}
}
