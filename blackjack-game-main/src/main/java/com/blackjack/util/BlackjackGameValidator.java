package com.blackjack.util;

import java.util.List;

import com.blackjack.model.BlackjackErrorCodes;
import com.blackjack.model.Card;
import com.blackjack.model.Player;

/**
 * The BlackjackGameValidator class provides methods for validating game-related data.
 */
public class BlackjackGameValidator {

	/**
	 * Validates the number of players in the game.
	 *
	 * @param number The number of players.
	 * @throws BlackjackGameException if the number of players is invalid.
	 */
	public void validateNumberOfPlayer(int number) {
		if (number <= 0) {
			throw new BlackjackGameException(BlackjackErrorCodes.INVALID_NUMBER_OF_PLAYER);
		}
	}

}

