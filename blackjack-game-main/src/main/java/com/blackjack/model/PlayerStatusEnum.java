package com.blackjack.model;

/**
 * The PlayerStatusEnum is an enumeration that represents the different status values
 * for a player in the Blackjack game. The possible status values are:
 * ALLOW_MORE_CARD, STAND, and BUSTED.
 *
 */
public enum PlayerStatusEnum {

	// Enum constants representing the possible status values for a player.
	ALLOW_MORE_CARD, // Player can request more cards.
	STAND,           // Player decides not to request any more cards.
	BUSTED;          // Player's card total value exceeds 21, causing them to lose the round.
}
