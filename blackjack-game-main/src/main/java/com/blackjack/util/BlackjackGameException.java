package com.blackjack.util;

import com.blackjack.model.BlackjackErrorCodes;

/**
 * The BlackjackGameException class represents an exception specific to the blackjack game.
 * It extends the RuntimeException class, making it an unchecked exception.
 */
public class BlackjackGameException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private int code;

	/**
	 * Constructs a BlackjackGameException with the given error code and message.
	 *
	 * @param code    The error code associated with the exception.
	 * @param message The error message describing the exception.
	 */
	public BlackjackGameException(int code, String message) {
		super(message);
		this.code = code;
	}

	/**
	 * Constructs a BlackjackGameException with the given BlackjackErrorCodes.
	 * The error code and message are retrieved from the enum.
	 *
	 * @param BlackjackErrorCodes The enum representing the blackjack error code and message.
	 */
	public BlackjackGameException(BlackjackErrorCodes BlackjackErrorCodes) {
		super(BlackjackErrorCodes.getMessage());
		this.code = BlackjackErrorCodes.getCode();
	}

	/**
	 * Retrieves the error code associated with the exception.
	 *
	 * @return The error code.
	 */
	public int getCode() {
		return code;
	}
}
