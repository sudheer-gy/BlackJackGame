package com.blackjack;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.blackjack.model.AbstractPayoutCard;
import com.blackjack.model.Card;
import com.blackjack.model.CardTypeEnum;
import com.blackjack.model.Player;
import com.blackjack.model.AcePayoutCard;
import com.blackjack.model.StandardPayoutCard;
import com.blackjack.service.BlackjackGameService;
import com.blackjack.service.BlackjackGameServiceImpl;
import com.blackjack.util.BlackjackUtil;

/**
 * The BlackjackGameServiceImplTest class contains test cases for the BlackjackGameServiceImpl class.
 * It verifies the behavior and correctness of the methods in the BlackjackGameServiceImpl class.
 * It uses JUnit framework for unit testing.
 *
 */
public class BlackjackGameServiceImplTest {

	/**
	 * The BlackjackGameService instance used for testing.
	 */
	private static BlackjackGameService BlackjackGameService;

	/**
	 * Sets up the test environment.
	 */
	@BeforeAll
	static void setup() {
		BlackjackGameService = new BlackjackGameServiceImpl();
		BlackjackGameService.setup(1);
	}

	/**
	 * Tests that the startGame() method starts the game with all players having one card.
	 */
	@Test
	@DisplayName("Start game and all players with one card")
	public void test_starGameAllPlayerWithOneCard() {
		BlackjackGameService.setup(2);
		List<Player> players = BlackjackGameService.starGame();

		// Verify that each player has one card.
		players.stream().forEach(p -> assertEquals(1, p.getCards().size()));
	}
}

