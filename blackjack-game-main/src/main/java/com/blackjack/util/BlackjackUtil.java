package com.blackjack.util;

/**
 * The BlackjackUtil class provides utility methods and constants for the blackjack game.
 */
public class BlackjackUtil {

	private final static String GET_ACE = "Ace";
	private final static String GET_J = "J";
	private final static String GET_Q = "Q";
	private final static String GET_K = "K";

	private final static int MAX_SCORE = 21;

	/**
	 * Retrieves the maximum score in the game.
	 *
	 * @return The maximum score.
	 */
	public static int getMaxScore() {
		return MAX_SCORE;
	}

	/**
	 * Retrieves the constant representing the string "Ace".
	 *
	 * @return The string "Ace".
	 */
	public static String getGetAce() {
		return GET_ACE;
	}

	/**
	 * Retrieves the constant representing the string "J".
	 *
	 * @return The string "J".
	 */
	public static String getGetJ() {
		return GET_J;
	}

	/**
	 * Retrieves the constant representing the string "Q".
	 *
	 * @return The string "Q".
	 */
	public static String getGetQ() {
		return GET_Q;
	}

	/**
	 * Retrieves the constant representing the string "K".
	 *
	 * @return The string "K".
	 */
	public static String getGetK() {
		return GET_K;
	}
}
