package com.blackjack.model;

/**
 * The BlackjackErrorCodes is an enumeration that represents various error codes
 * and their associated messages for the Blackjack game. This enum can be used for
 * better error handling and providing more informative error messages.
 *
 */
public enum BlackjackErrorCodes {

	// Enum constants representing different error codes and their messages.
	INVALID_NUMBER_OF_PLAYER(1, "Total of player must be greater than 0"),
	DUPLICATED_CARD(2, "Card duplicated"),
	MISS_ONE_CARD(3, "At least the player should have one card"),
	NO_MORE_PLAYER_WITHOUT_CARD(4, "There is no more player without card"),
	NO_WINNER(5, "There is no winner"),
	INVALID_PLAYER_POS(6, "Invalid player position");

	// Instance variables to store the error code and message for each enum constant.
	private int code;
	private String message;

	/**
	 * Constructor for the BlackjackErrorCodes, which initializes the code and message.
	 *
	 * @param code    the error code to be set.
	 * @param message the error message to be set.
	 */
	BlackjackErrorCodes(int code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * Getter method for the code instance variable.
	 *
	 * @return the error code.
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Setter method for the code instance variable.
	 *
	 * @param code the new error code to set.
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * Getter method for the message instance variable.
	 *
	 * @return the error message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Setter method for the message instance variable.
	 *
	 * @param message the new error message to set.
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
