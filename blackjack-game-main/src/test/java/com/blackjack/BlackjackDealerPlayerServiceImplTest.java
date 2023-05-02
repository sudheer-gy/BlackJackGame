package com.blackjack;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.blackjack.model.AbstractPayoutCard;
import com.blackjack.model.Card;
import com.blackjack.model.CardTypeEnum;
import com.blackjack.model.Player;
import com.blackjack.model.AcePayoutCard;
import com.blackjack.model.StandardPayoutCard;
import com.blackjack.model.PlayerStatusEnum;
import com.blackjack.service.BlackjackDealerPlayerServiceImpl;
import com.blackjack.service.PlayerService;
import com.blackjack.util.BlackjackUtil;

public class BlackjackDealerPlayerServiceImplTest {

	private static PlayerService dealerPlayerService;

	@BeforeAll
	static void setup() {
		dealerPlayerService = new BlackjackDealerPlayerServiceImpl(0.8);
	}


	@Test
	@DisplayName("Calculate score of Dealer Player")
	public void test_calculateScore() {
		
		Player p1 = new Player("1", true);
		
		
		AbstractPayoutCard rateCard1 = new AcePayoutCard(BlackjackUtil.getGetAce(), Arrays.asList(11, 1));
		Card card1 = new Card(rateCard1.getCardName(), CardTypeEnum.CLUB);
		card1.setRateCard(rateCard1);
		
		AbstractPayoutCard rateCard2 = new StandardPayoutCard(BlackjackUtil.getGetJ(), 10);
		Card card2 = new Card(rateCard2.getCardName(), CardTypeEnum.CLUB);
		card2.setRateCard(rateCard2);
		
		p1.add(card1);
		p1.add(card2);
		
		dealerPlayerService.calculateScore(p1);
		
		
		Player p2 = new Player("2", true);
		
		rateCard1 = new AcePayoutCard(BlackjackUtil.getGetAce(), Arrays.asList(11, 1));
		card1 = new Card(rateCard1.getCardName(), CardTypeEnum.CLUB);
		card1.setRateCard(rateCard1);
		
		rateCard2 = new StandardPayoutCard(String.valueOf(1), 1);
		card2 = new Card(rateCard2.getCardName(), CardTypeEnum.CLUB);
		card2.setRateCard(rateCard2);
		
		
		p2.add(card1);
		p2.add(card2);
		dealerPlayerService.calculateScore(p2);
		
		Player p3 = new Player("3", true);
		
		rateCard1 = new AcePayoutCard(BlackjackUtil.getGetAce(), Arrays.asList(11, 1));
		card1 = new Card(rateCard1.getCardName(), CardTypeEnum.CLUB);
		card1.setRateCard(rateCard1);
		
		rateCard2 = new AcePayoutCard(BlackjackUtil.getGetAce(), Arrays.asList(11, 1));
		card2 = new Card(rateCard2.getCardName(), CardTypeEnum.CLUB);
		card2.setRateCard(rateCard2);
		
		
		p3.add(card1);
		p3.add(card2);
		dealerPlayerService.calculateScore(p3);
		
		assertEquals(p1.getScore(), 21);
		assertEquals(p2.getScore(), 12);
		assertEquals(p3.getScore(), 12);
		
	}
	
	@Test
	@DisplayName("Deliver card and get a player busted")
	public void test_playerBusted() {
		
		Player p1 = new Player("1", true);
		
		AbstractPayoutCard rateCard1 =  new StandardPayoutCard(BlackjackUtil.getGetJ(), 10);
		Card card1 = new Card(rateCard1.getCardName(), CardTypeEnum.CLUB);
		card1.setRateCard(rateCard1);
		
		AbstractPayoutCard rateCard2 = new StandardPayoutCard(BlackjackUtil.getGetJ(), 10);
		Card card2 = new Card(rateCard2.getCardName(), CardTypeEnum.CLUB);
		card2.setRateCard(rateCard2);
		
		AbstractPayoutCard rateCard3 = new StandardPayoutCard(BlackjackUtil.getGetJ(), 10);
		Card card3 = new Card(rateCard3.getCardName(), CardTypeEnum.CLUB);
		card3.setRateCard(rateCard3);
		
		p1.add(card1);
		p1.add(card2);
		p1.add(card3);
		
		
		dealerPlayerService.calculateScore(p1);
		
		assertTrue(p1.isBusted());
	}
	
	@Test
	@DisplayName("Deliver ace card and get a player not busted")
	public void test_playerNotBusted() {
		
		Player p1 = new Player("1", true);
		
		AbstractPayoutCard rateCard1 =  new AcePayoutCard(BlackjackUtil.getGetAce(), Arrays.asList(11, 1));
		Card card1 = new Card(rateCard1.getCardName(), CardTypeEnum.CLUB);
		card1.setRateCard(rateCard1);
		
		AbstractPayoutCard rateCard2 = new StandardPayoutCard(BlackjackUtil.getGetJ(), 10);
		Card card2 = new Card(rateCard2.getCardName(), CardTypeEnum.CLUB);
		card2.setRateCard(rateCard2);
		
		AbstractPayoutCard rateCard3 = new StandardPayoutCard(BlackjackUtil.getGetJ(), 10);
		Card card3 = new Card(rateCard3.getCardName(), CardTypeEnum.CLUB);
		card3.setRateCard(rateCard3);
		
		p1.add(card1);
		p1.add(card2);
		p1.add(card3);
		
		
		dealerPlayerService.calculateScore(p1);
		
		assertTrue(!p1.isBusted());
	}
	
	@Test
	@DisplayName("Allow more card, beacuse the score (16) is not greater than 80% out of 21")
	public void test_playerAllowMoreCard() {
		
		Player p1 = new Player("1", true);
		
		AbstractPayoutCard rateCard1 =  new AcePayoutCard(BlackjackUtil.getGetAce(), Arrays.asList(11, 1));
		Card card1 = new Card(rateCard1.getCardName(), CardTypeEnum.CLUB);
		card1.setRateCard(rateCard1);
		
		AbstractPayoutCard rateCard2 = new StandardPayoutCard(String.valueOf(5), 5);
		Card card2 = new Card(rateCard2.getCardName(), CardTypeEnum.CLUB);
		card2.setRateCard(rateCard2);
		
		AbstractPayoutCard rateCard3 = new StandardPayoutCard(BlackjackUtil.getGetJ(), 10);
		Card card3 = new Card(rateCard3.getCardName(), CardTypeEnum.CLUB);
		card3.setRateCard(rateCard3);
		
		p1.add(card1);
		p1.add(card2);
		p1.add(card3);
		
		
		dealerPlayerService.allowOneMoreCard(p1);
		
		assertTrue(p1.isAllowMoreCard());
	}
	
	@Test
	@DisplayName("Stand player, because the score (17) is grater than 80% out of 21")
	public void test_playerStand() {
		
		Player p1 = new Player("1", true);
		
		AbstractPayoutCard rateCard1 =  new AcePayoutCard(BlackjackUtil.getGetAce(), Arrays.asList(11, 1));
		Card card1 = new Card(rateCard1.getCardName(), CardTypeEnum.CLUB);
		card1.setRateCard(rateCard1);
		
		AbstractPayoutCard rateCard2 = new StandardPayoutCard(String.valueOf(6), 6);
		Card card2 = new Card(rateCard2.getCardName(), CardTypeEnum.CLUB);
		card2.setRateCard(rateCard2);
		
		AbstractPayoutCard rateCard3 = new StandardPayoutCard(BlackjackUtil.getGetJ(), 10);
		Card card3 = new Card(rateCard3.getCardName(), CardTypeEnum.CLUB);
		card3.setRateCard(rateCard3);
		
		p1.add(card1);
		p1.add(card2);
		p1.add(card3);
		
		
		dealerPlayerService.allowOneMoreCard(p1);
		
		assertEquals(PlayerStatusEnum.STAND, p1.getStatus());
	}

}
