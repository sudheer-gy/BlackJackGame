package com.blackjack.model;

/**
 * The StandardPayoutCard class represents a standard card's rate in the Blackjack game.
 * This class extends the AbstractPayoutCard class and adds an integer value for the
 * standard card.
 *
 */
public class StandardPayoutCard extends AbstractPayoutCard {

	// Instance variable to store the integer value for a standard card.
	protected int value;

	// Getter and setter methods for the value of the standard card.
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * Constructor for the StandardPayoutCard class, which initializes the card name
	 * and the integer value for the standard card.
	 *
	 * @param cardName the name of the standard card.
	 * @param value    the integer value for the standard card.
	 */
	public StandardPayoutCard(String cardName, int value) {
		super(cardName);
		this.value = value;
	}

	// toString method to generate a string representation of the StandardPayoutCard object.
	@Override
	public String toString() {
		return "StandardPayoutCard value: " + value;
	}
}
