package com.blackjack;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.blackjack.model.BlackjackErrorCodes;
import com.blackjack.model.Player;
import com.blackjack.model.PlayerStatusEnum;
import com.blackjack.service.BlackjackGameServiceImpl;
import com.blackjack.service.MessageService;
import com.blackjack.service.MessageServiceImpl;
import com.blackjack.util.BlackjackGameException;

/**
 * The main class that runs the Blackjack game.
 */
public class BlackjackGameManager {

	private static final String REGEX = "blackjack \\d";

	private static final String STAND_COMMAND = "stand";
	private static final String HIT_COMMAND = "hit";

	private static BlackjackGameServiceImpl blackjackManager = new BlackjackGameServiceImpl();
	private static MessageService messageService = new MessageServiceImpl();

	/**
	 * The main method that starts the Blackjack game.
	 *
	 * @param args The command-line arguments.
	 */
	public static void main(String[] args) {

		if (args.length != 2) {
			printMessage(messageService.getInvalidInitialCommandMessage());
			System.exit(0);
		}

		String commandLine = args[0] + " " + args[1];

		if (!commandLine.matches(REGEX)) {
			printMessage(messageService.getInvalidInitialCommandMessage());
			System.exit(0);
		}

		// start game
		int totalPlayers = Integer.valueOf(args[1]);

		try {

			blackjackManager.setup(totalPlayers);

			printMessage(messageService.getStarGameMessage(totalPlayers));
			printMessage(messageService.getShufflingMessage());

			// ----------------------------- start game
			List<Player> players = blackjackManager.starGame();
			players.forEach(p -> printMessage(messageService.getDealingMessage(p, true)));

			// -------------------------- deliver card to Users
			deliverCardToUser(players.stream().filter(p -> !p.isDealer()).collect(Collectors.toList()));

			// -------------------------  deliver card to Dealer
			deliverCardToDealer(players.stream().filter(p -> p.isDealer()).findAny().get());

			Player player = blackjackManager.getWinner();
			printMessage(messageService.getWinnerMessage(player));

		} catch (BlackjackGameException exception) {
			
			if (exception.getCode() == BlackjackErrorCodes.NO_WINNER.getCode()) {
				printMessage(messageService.getNoWinnerMessage());
			} else {
				printMessage(messageService.getMessageFromException(exception));
			}
		}

	}

	/**
	 * Checks if the command is a valid stand or hit command.
	 *
	 * @param value The command value to check.
	 * @return true if the command is a valid stand or hit command, false otherwise.
	 */
	private static boolean isStandOrHitCommand(String value) {
		if (value == null) {
			return false;
		}
		value = value.trim().toLowerCase();
		return value.equalsIgnoreCase(HIT_COMMAND) || value.equalsIgnoreCase(STAND_COMMAND);
	}

	/**
	 * Delivers cards to the dealer.
	 *
	 * @param player The dealer player.
	 */
	private static void deliverCardToDealer(Player player) {
		
		player = blackjackManager.playCard(player);
		
		printMessage(messageService.getDealingMessage(player));
		
		if (player.getStatus().equals(PlayerStatusEnum.ALLOW_MORE_CARD)) {
			deliverCardToDealer(player);
		}
		return;
	}

	/**
	 * Delivers cards to the users.
	 *
	 * @param players The list of user players.
	 */
	private static void deliverCardToUser(List<Player> players) {

		Scanner scanner = new Scanner(System.in);

		for (Player player : players) {

			// ------- second deliver round
			player = blackjackManager.playCard(player);
			String message = messageService.getDealingMessage(player);
			printMessage(message);
			
			if (player.getStatus().equals(PlayerStatusEnum.BUSTED)) {
				continue;
			}

			while (scanner.hasNextLine()) {

				String commandValue = scanner.nextLine().trim();

				if (!isStandOrHitCommand(commandValue)) {
					printMessage(messageService.getInvalidCommandMessage());
					continue;
				}

				if (commandValue.equalsIgnoreCase("stand")) {
					// jump to another player
					blackjackManager.setStand(player);
					break;
				}

				if (commandValue.equalsIgnoreCase("hit")) {
					
					player = blackjackManager.playCard(player);
					printMessage(messageService.getDealingMessage(player));

					if (player.getStatus().equals(PlayerStatusEnum.BUSTED)) {
						break;
					}
				}
			}
		}

		scanner.close();
	}
	
	private static void printMessage(String message) {
		System.out.println(message);
	}

}
