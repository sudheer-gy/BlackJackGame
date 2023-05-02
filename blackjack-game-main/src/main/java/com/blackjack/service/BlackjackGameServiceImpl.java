package com.blackjack.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.blackjack.model.AbstractPayoutCard;
import com.blackjack.model.BlackjackErrorCodes;
import com.blackjack.model.Card;
import com.blackjack.model.CardTypeEnum;
import com.blackjack.model.Player;
import com.blackjack.model.PlayerStatusEnum;
import com.blackjack.util.BlackjackGameException;

/**
 *
 */
/**
 * The BlackjackGameServiceImpl class implements the BlackjackGameService interface and provides
 * the actual implementation of the game logic.
 */
public class BlackjackGameServiceImpl implements BlackjackGameService {

	private BlackjackDealerService BlackjackDealerService;
	private GameSetupService GameSetupService;
	private Set<Card> cards;
	private List<Player> players;
	private List<AbstractPayoutCard> rateCards;
	private List<CardTypeEnum> cardTypes;
	private PlayerService userPlayerService;
	private PlayerService dealerPlayerService;

	/**
	 * Starts the game by dealing initial cards to the players.
	 *
	 * @return A list of players with their initial cards.
	 */
	@Override
	public List<Player> starGame() {
		players.forEach(p -> receiveCard(p));
		return players;
	}

	/**
	 * Sets up the game with the given number of players.
	 *
	 * @param number The number of players in the game.
	 */
	@Override
	public void setup(int number) {
		players = GameSetupService.preparePlayers(number);
		rateCards = GameSetupService.getRateCards();
		cardTypes = GameSetupService.getCardTypeEnums();

		dealerPlayerService = new BlackjackDealerPlayerServiceImpl(0.8);
		userPlayerService = new UserPlayerServiceImpl();
	}

	/**
	 * Plays a card for the given player, updates their state, and returns the updated player.
	 *
	 * @param player The player who will receive the next card and have their state updated.
	 * @return The updated player after receiving the card.
	 */
	@Override
	public Player playCard(Player player) {
		receiveCard(player);

		PlayerService playerService = getPlayerService(player);
		playerService.allowOneMoreCard(player);

		return player;
	}

	/**
	 * Receives a card from the dealer, adds it to the player's hand, and checks for any errors.
	 *
	 * @param player The player who will receive the card.
	 */
	private void receiveCard(Player player) {
		Card card = BlackjackDealerService.getNextCard(cards, rateCards, cardTypes);
		addCard(card);
		player.add(card);
	}

	/**
	 * Sets the stand status for the given player.
	 *
	 * @param player The player whose stand status will be set.
	 */
	@Override
	public void setStand(Player player) {
		player.setStatus(PlayerStatusEnum.STAND);
	}

	/**
	 * Determines the winner of the game based on the current state of the players.
	 *
	 * @return The winning player.
	 */
	@Override
	public Player getWinner() {
		return BlackjackDealerService.getWinner(players);
	}

	/**
	 * Constructor for the BlackjackGameServiceImpl class.
	 * Initializes the data structures and service instances.
	 */
	public BlackjackGameServiceImpl() {
		super();
		cards = new HashSet<Card>();
		BlackjackDealerService = new BlackjackDealerServiceImpl();
		GameSetupService = new GameSetupServiceImpl();
	}

	/**
	 * Adds a card to the set of cards and checks for duplication errors.
	 *
	 * @param card The card to be added.
	 */
	private void addCard(Card card) {
		if (!cards.add(card)) {
			throw new BlackjackGameException(BlackjackErrorCodes.DUPLICATED_CARD);
		}
	}

	/**
	 * Retrieves the appropriate player service based on the player type.
	 *
	 * @param player The player for which to retrieve the player service.
	 * @return The player service instance.
	 */
	private PlayerService getPlayerService(Player player) {
		if (player.isDealer()) {
			return dealerPlayerService;
		} else {
			return userPlayerService;
		}
	}
}
