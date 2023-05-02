package com.blackjack.service;

import java.util.stream.Collectors;

import com.blackjack.model.Player;
import com.blackjack.util.BlackjackGameException;
import com.blackjack.util.BlackjackUtil;

/**
 * The MessageServiceImpl class implements the MessageService interface and provides methods for generating messages related to the game.
 */
public class MessageServiceImpl implements MessageService {

	private static final String DEALING_PLAYER = "Dealing to %s, card: %s";
	private static final String SHUFFLING = "Shuffling.";
	private static final String HIT_OR_STAND = "Hit or Stand > ";
	private static final String BUSTED = "Busted over %d.";
	private static final String WINNER_MESSAGE = "Player %s wins.";
	private static final String INVALID_INITIAL_COMMAND_ = "Invalid initial command. You must type: blackjack <number of player>";
	private static final String INVALID_COMMAND = "Expecting hit or stand command";
	private static final String STAR_GAME = "Starting game with %d players.";
	private static final String NO_WINNER = "No winner.";
	private static final String HIT = "hits.";
	private static final String STAND = "stands.";
	private static final String FACE_DOWN = "face down";

	@Override
	public String getMessageFromException(BlackjackGameException exception) {
		return exception.getMessage();
	}

	@Override
	public String getWinnerMessage(Player player) {
		return String.format(WINNER_MESSAGE, player);
	}

	@Override
	public String getShufflingMessage() {
		return SHUFFLING;
	}

	@Override
	public String getStarGameMessage(int totalPlayers) {
		return String.format(STAR_GAME, totalPlayers);
	}

	@Override
	public String getDealingMessage(Player player, boolean initialMessage) {
		StringBuilder message = new StringBuilder();
		message.append(String.format(DEALING_PLAYER, player,
				player.getCards().stream().map(c -> initialMessage && player.isDealer() ? FACE_DOWN : c.toString())
						.collect(Collectors.joining(", "))));
		return message.toString();
	}

	@Override
	public String getDealingMessage(Player player) {
		StringBuilder message = new StringBuilder(getDealingMessage(player, false));
		if (player.isBusted()) {
			message.append(". " + String.format(BUSTED, BlackjackUtil.getMaxScore()));
		} else if (player.isDealer()) {
			if (player.isAllowMoreCard()) {
				message.append(" " + player + " " + HIT);
			} else {
				message.append(" " + player + " " + STAND);
			}
		} else {
			message.append(". " + HIT_OR_STAND);
		}
		return message.toString();
	}

	@Override
	public String getInvalidInitialCommandMessage() {
		return INVALID_INITIAL_COMMAND_;
	}

	@Override
	public String getInvalidCommandMessage() {
		return INVALID_COMMAND;
	}

	@Override
	public String getNoWinnerMessage() {
		return NO_WINNER;
	}
}
