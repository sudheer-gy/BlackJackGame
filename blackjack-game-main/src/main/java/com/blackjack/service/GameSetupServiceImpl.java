package com.blackjack.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.blackjack.model.AbstractPayoutCard;
import com.blackjack.model.CardTypeEnum;
import com.blackjack.model.Player;
import com.blackjack.model.AcePayoutCard;
import com.blackjack.model.StandardPayoutCard;
import com.blackjack.util.BlackjackUtil;
import com.blackjack.util.BlackjackGameValidator;

/**
 * The GameSetupServiceImpl class implements the GameSetupService interface and provides methods for setting up the game.
 */
public class GameSetupServiceImpl implements GameSetupService {

	private BlackjackGameValidator BlackjackGameValidator;

	public GameSetupServiceImpl() {
		BlackjackGameValidator = new BlackjackGameValidator();
	}

	/**
	 * Prepares the players for the game.
	 *
	 * @param totalPlayer The total number of players.
	 * @return The list of prepared players.
	 */
	@Override
	public List<Player> preparePlayers(int totalPlayer) {

		BlackjackGameValidator.validateNumberOfPlayer(totalPlayer);

		List<Player> players  = new ArrayList<Player>();

		for (int i = 0; i < totalPlayer; i++) {
			players.add(new Player(String.valueOf(i+1), false));
		}
		players.add(new Player("Dealer", true));

		return players;
	}

	/**
	 * Retrieves the list of rate cards for the game.
	 *
	 * @return The list of rate cards.
	 */
	@Override
	public List<AbstractPayoutCard> getRateCards() {
		List<AbstractPayoutCard> rateCards = IntStream.range(1, 11).boxed()
				.map(n -> {
					return new StandardPayoutCard(String.valueOf(n), n);
				}).collect(Collectors.toList());

		rateCards.add(new StandardPayoutCard(BlackjackUtil.getGetJ(), 10));
		rateCards.add(new StandardPayoutCard(BlackjackUtil.getGetQ(), 10));
		rateCards.add(new StandardPayoutCard(BlackjackUtil.getGetK(), 10));

		rateCards.add(new AcePayoutCard(BlackjackUtil.getGetAce(), Arrays.asList(11, 1)));

		return rateCards;
	}

	/**
	 * Retrieves the list of card types for the game.
	 *
	 * @return The list of card types.
	 */
	@Override
	public List<CardTypeEnum> getCardTypeEnums() {
		return Arrays.asList(CardTypeEnum.CLUB, CardTypeEnum.DIAMOND, CardTypeEnum.HEART, CardTypeEnum.SPADE);
	}
}
