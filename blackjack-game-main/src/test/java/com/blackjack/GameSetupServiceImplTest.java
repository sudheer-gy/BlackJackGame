package com.blackjack;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.blackjack.model.Player;
import com.blackjack.service.GameSetupService;
import com.blackjack.service.GameSetupServiceImpl;

/**
 * Test class for GameSetupServiceImpl.
 *
 * It tests the preparation of players for the game.
 *
 *
 */
public class GameSetupServiceImplTest {

	private static GameSetupService GameSetupService;

	@BeforeAll
	static void setup() {
		GameSetupService = new GameSetupServiceImpl();
	}

	@Test
	@DisplayName("Prepare 4 players, 3 user players and 1 dealer player")
	public void test_prepare3UserPlayersAndDealerPlayer() {

		// Prepare 3 user players and 1 dealer player
		List<Player> players = GameSetupService.preparePlayers(3);

		// Assert that there are 4 players in total
		assertEquals(4, players.size());

		// Assert that there is 1 dealer player
		assertEquals(1, players.stream().filter(p -> p.isDealer()).collect(Collectors.toList()).size());
	}

}
