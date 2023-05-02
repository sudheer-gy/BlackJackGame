package com.blackjack.service;

import com.blackjack.model.Player;
import com.blackjack.util.BlackjackGameException;

/**
 * The MessageService interface provides methods for generating messages related to the game.
 */
public interface MessageService {

	/**
	 * Generates the dealing message for a player.
	 *
	 * @param player           The player receiving the message.
	 * @param initialMessage   Whether it is the initial dealing message.
	 * @return The dealing message.
	 */
	public String getDealingMessage(Player player, boolean initialMessage);

	/**
	 * Generates the dealing message for a player.
	 *
	 * @param player   The player receiving the message.
	 * @return The dealing message.
	 */
	public String getDealingMessage(Player player);

	/**
	 * Generates the winner message for a player.
	 *
	 * @param player   The winning player.
	 * @return The winner message.
	 */
	public String getWinnerMessage(Player player);

	/**
	 * Generates the shuffling message.
	 *
	 * @return The shuffling message.
	 */
	public String getShufflingMessage();

	/**
	 * Generates the start game message with the total number of players.
	 *
	 * @param totalPlayers   The total number of players in the game.
	 * @return The start game message.
	 */
	public String getStarGameMessage(int totalPlayers);

	/**
	 * Generates the message for an invalid initial command.
	 *
	 * @return The invalid initial command message.
	 */
	public String getInvalidInitialCommandMessage();

	/**
	 * Generates the message for an invalid command.
	 *
	 * @return The invalid command message.
	 */
	public String getInvalidCommandMessage();

	/**
	 * Generates the message for no winner in the game.
	 *
	 * @return The no winner message.
	 */
	public String getNoWinnerMessage();

	/**
	 * Retrieves the message from a BlackjackGameException.
	 *
	 * @param exception   The BlackjackGameException.
	 * @return The corresponding message.
	 */
	public String getMessageFromException(BlackjackGameException exception);
}
