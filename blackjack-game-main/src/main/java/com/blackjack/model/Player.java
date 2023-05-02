package com.blackjack.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Player class represents a player in the Blackjack game. Each player has a name,
 * a list of cards, a status, a boolean flag to indicate if they are a dealer, and a score.
 *
 */
public class Player {

	// Instance variables to store the player's name, cards, status, dealer status, and score.
	private String name;
	private List<Card> cards;
	private PlayerStatusEnum status;
	private boolean dealer;
	private int score;

	/**
	 * Constructor for the Player class, which initializes the player's name, dealer status,
	 * an empty list of cards, and sets their initial status to ALLOW_MORE_CARD.
	 *
	 * @param name   the name of the player.
	 * @param dealer a boolean flag to indicate if the player is a dealer.
	 */
	public Player(String name, boolean dealer) {
		status = PlayerStatusEnum.ALLOW_MORE_CARD;
		cards = new ArrayList<Card>();
		this.name = name;
		this.dealer = dealer;
	}

	// Method to add a card to the player's list of cards.
	public boolean add(Card arg0) {
		return cards.add(arg0);
	}

	// Getter and setter methods for the player's status.
	public PlayerStatusEnum getStatus() {
		return status;
	}

	public void setStatus(PlayerStatusEnum status) {
		this.status = status;
	}

	// Getter method for the player's name.
	public String getName() {
		return name;
	}

	// Method to check if the player has any cards.
	public boolean hasCard() {
		return !this.cards.isEmpty();
	}

	// Method to check if the player is a dealer.
	public boolean isDealer() {
		return dealer;
	}

	// Getter method for the player's list of cards.
	public List<Card> getCards() {
		return cards;
	}

	// Getter and setter methods for the player's score.
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	// Method to check if the player's status is BUSTED.
	public boolean isBusted() {
		return status.equals(PlayerStatusEnum.BUSTED);
	}

	// Method to check if the player's status is ALLOW_MORE_CARD.
	public boolean isAllowMoreCard() {
		return status.equals(PlayerStatusEnum.ALLOW_MORE_CARD);
	}

	// toString method to generate a string representation of the player.
	@Override
	public String toString() {
		return name;
	}
}
