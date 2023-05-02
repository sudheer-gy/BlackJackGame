package com.blackjack.service;

import com.blackjack.model.Player;
import com.blackjack.model.PlayerStatusEnum;
import com.blackjack.util.BlackjackUtil;

/**
 * The BlackjackDealerPlayerServiceImpl class is an implementation of the PlayerService interface
 * specifically for the dealer player in the game.
 */
public class BlackjackDealerPlayerServiceImpl extends BasePlayerService {

	private double percentageToWin;

	/**
	 * Constructor for the BlackjackDealerPlayerServiceImpl class.
	 * Initializes the percentageToWin property.
	 *
	 * @param percentageToWin The percentage of the maximum score required to win.
	 */
	public BlackjackDealerPlayerServiceImpl(double percentageToWin) {
		super();
		this.percentageToWin = percentageToWin;
	}

	/**
	 * Allows the dealer player to receive one more card and updates their status accordingly.
	 *
	 * @param player The dealer player.
	 * @return The updated status of the dealer player.
	 */
	@Override
	public PlayerStatusEnum allowOneMoreCard(Player player) {
		calculateScore(player);

		if(player.isDealer() && !player.isBusted()) {
			if(player.getScore() > BlackjackUtil.getMaxScore() * percentageToWin) {
				player.setStatus(PlayerStatusEnum.STAND);
			}
		}
		return player.getStatus();
	}
}
