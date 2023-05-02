package com.blackjack.model;

/**
 * The AbstractPayoutCard class is an abstract class that represents a rate card
 * for the Blackjack game. This class will serve as a base for more specific
 * rate card classes.
 *
 */
public abstract class AbstractPayoutCard {

	// A protected instance variable to store the name of the card.
	protected String cardName;

	/**
	 * Getter method for the cardName instance variable.
	 *
	 * @return the name of the card.
	 */
	public String getCardName() {
		return cardName;
	}

	/**
	 * Setter method for the cardName instance variable.
	 *
	 * @param cardName the new name to set for the card.
	 */
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	/**
	 * Constructor for the AbstractPayoutCard class, which initializes the cardName.
	 *
	 * @param cardName the name of the card to be set.
	 */
	public AbstractPayoutCard(String cardName) {
		super(); // Call the superclass constructor, in this case, the Object class constructor.
		this.cardName = cardName;
	}
}
