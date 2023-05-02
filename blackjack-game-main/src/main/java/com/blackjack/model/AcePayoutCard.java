package com.blackjack.model;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The AcePayoutCard class represents an Ace card's rate in the Blackjack game.
 * This class extends the AbstractPayoutCard class and adds a list of possible
 * integer values for the Ace card.
 *
 */
public class AcePayoutCard extends AbstractPayoutCard {

	// Instance variable to store the list of possible integer values for an Ace card.
	protected List<Integer> values;

	// Getter and setter methods for the list of possible values.
	public List<Integer> getValues() {
		return values;
	}

	public void setValues(List<Integer> values) {
		this.values = values;
	}

	/**
	 * Constructor for the AcePayoutCard class, which initializes the card name
	 * and the list of possible integer values for the Ace card.
	 *
	 * @param cardName the name of the Ace card.
	 * @param values   the list of possible integer values for the Ace card.
	 */
	public AcePayoutCard(String cardName, List<Integer> values) {
		super(cardName);
		this.values = values;
	}

	// toString method to generate a string representation of the AcePayoutCard object.
	@Override
	public String toString() {
		return "AcePayoutCard values: " + values.stream().map(v -> String.valueOf(v)).collect(Collectors.joining(", "));
	}
}
