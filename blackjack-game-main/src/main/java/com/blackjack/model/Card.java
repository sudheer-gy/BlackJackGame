package com.blackjack.model;

import com.blackjack.util.BlackjackUtil;

/**
 * The Card class represents a single card in the Blackjack game.
 * Each card has a value, a type (represented by the CardTypeEnum), and a rate card.
 *
 */
public class Card {

	// Instance variables to store the card's value, type, and rate card.
	private String value;
	private CardTypeEnum cardTypeEnum;
	private AbstractPayoutCard rateCard;

	/**
	 * Constructor for the Card class, which initializes the value and card type.
	 *
	 * @param value         the value of the card to be set.
	 * @param cardTypeEnum  the type of the card to be set.
	 */
	public Card(String value, CardTypeEnum cardTypeEnum) {
		this.value = value;
		this.cardTypeEnum = cardTypeEnum;
	}

	// Getter and setter methods for the card type.
	public CardTypeEnum getCardTypeEnum() {
		return cardTypeEnum;
	}

	public void setCardTypeEnum(CardTypeEnum cardTypeEnum) {
		this.cardTypeEnum = cardTypeEnum;
	}

	// Getter and setter methods for the card's value.
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	// hashCode method to generate a hash code based on the card's value and type.
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardTypeEnum == null) ? 0 : cardTypeEnum.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	// equals method to compare two Card objects based on their value and type.
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (cardTypeEnum != other.cardTypeEnum)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	// toString method to generate a string representation of the card.
	@Override
	public String toString() {
		return (value.equalsIgnoreCase("ace") || value.equals("1"))
				? String.format("%s %s", value, cardTypeEnum.name())
				: String.format("%s %s", value, cardTypeEnum.name() + "s");
	}

	// isAce method to check if the card is an Ace.
	public boolean isAce() {
		return this.value.equalsIgnoreCase(BlackjackUtil.getGetAce());
	}

	// Getter and setter methods for the card's rate card.
	public AbstractPayoutCard getRateCard() {
		return rateCard;
	}

	public void setRateCard(AbstractPayoutCard rateCard) {
		this.rateCard = rateCard;
	}
}
