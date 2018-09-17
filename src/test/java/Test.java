import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import junit.framework.TestCase;

public class Test extends TestCase {

	public void testNumberOfCard() {
		Cards cards = new Cards();
		assertTrue(cards.getCards().size() == 52);
	}
	
	public void testShufflingOfCard() {
		Cards cards = new Cards();
		Cards cards1 = new Cards();
		int num = 0;
		for(int i =0; i < 52; i++) {
			if(!cards.getCard(i).equals(cards1.getCard(i)))
				num++;
		}
		assertTrue(num >= 25);
	}
	
	public void testFileAndConsoleInput() {
		Game game = new Game();
		assertTrue(true == game.hasConsole());
		assertTrue(true == game.hasInput());
	}
	
	public void testCardInvisibleForUser() {
		Game game = new Game();
		Card card = new Card();
		card.setName("H6");
		Card card1 = new Card();
		card1.setName("H10");
		game.getUser().addCard(card, 0);
		game.getUser().addCard(card1, 0);
		assertTrue(true == card.getVisible());
		assertTrue(true == card1.getVisible());
	}
	
	public void testCardInvisibleForDealer() {
		Game game = new Game();
		Card card = new Card();
		card.setName("H6");
		Card card1 = new Card();
		card1.setName("H10");
		game.getDealer().addCard(card, 0);
		game.getDealer().addCard(card1, 0);
		assertTrue(true == card.getVisible());
		assertTrue(false == card1.getVisible());
	}
	
	public void testAtTheEndCardOfDealerDisplay() {
		Game game = new Game();
		Card card = new Card();
		card.setName("HA");
		Card card1 = new Card();
		card1.setName("H10");
		game.getDealer().addCard(card, 0);
		game.getDealer().addCard(card1, 0);
		Card card2 = new Card();
		Card card3 = new Card();
		card2.setName("C9");
		card2.setName("S9");
		game.defineWinner();
		assertTrue(true == game.getDealer().getCard(0).get(1).getVisible());
	}
	
	
	
	
	public void testDealerCanHit() {
		Game  game =  new Game();
		Card card = new Card();
		Card card1 = new Card();
		card.setName("H6");
		card1.setName("S10");
		game.getDealer().addCard(card, 0);
		game.getDealer().addCard(card1, 0);
		assertTrue(true == game.getDealer().canHit());
	}
	
	public void testDealerCanHit2() {
		Game  game =  new Game();
		Card card = new Card();
		Card card1 = new Card();
		card.setName("H5");
		card1.setName("SA");
		game.getDealer().addCard(card, 0);
		game.getDealer().addCard(card1, 0);
		assertTrue(true == game.getDealer().canHit());
	}
	
	public void test_JKQ_value() {
		Game game = new Game();
		Card card = new Card();
		Card card1 = new Card();
		Card card2 = new Card();
		card.setName("SK");
		card1.setName("SJ");
		card2.setName("SQ");
		game.getDealer().addCard(card, 0);
		game.getDealer().addCard(card1, 0);
		game.getUser().addCard(card2, 0);
		assertTrue(20 == game.getDealer().getPoint());
		assertTrue (10 == game.getUser().getPoint());
	}
	
	

	public void testBothBlackJack() {
		Game game = new Game();
		Card card = new Card();
		Card card1 = new Card();
		Card card2 = new Card();
		Card card3 = new Card();
		card.setName("HA");
		card1.setName("CA");
		card2.setName("SQ");
		card3.setName("CQ");
		game.getUser().addCard(card, 0);
		game.getUser().addCard(card2, 0);
		game.getDealer().addCard(card1, 0);
		game.getDealer().addCard(card3, 0);
		game.defineWinner();
		assertTrue(true == game.getDealer().isWin());
		assertTrue(false == game.getUser().isWin());
	}
	
	public void testPlayerBlackJack() {
		Game game = new Game();
		Card card = new Card();
		Card card1 = new Card();
		Card card2 = new Card();
		Card card3 = new Card();
		card.setName("HA");
		card1.setName("C9");
		card2.setName("SQ");
		card3.setName("CQ");
		game.getUser().addCard(card, 0);
		game.getUser().addCard(card2, 0);
		game.getDealer().addCard(card1, 0);
		game.getDealer().addCard(card3, 0);
		game.defineWinner();
		assertTrue(true == game.getUser().isWin());
		assertTrue(false == game.getDealer().isWin());
	}
	
	public void testDealerBlackJack() {
		Game game = new Game();
		Card card = new Card();
		Card card1 = new Card();
		Card card2 = new Card();
		Card card3 = new Card();
		card.setName("HA");
		card1.setName("C9");
		card2.setName("SQ");
		card3.setName("CQ");
		game.getUser().addCard(card1, 0);
		game.getUser().addCard(card3, 0);
		game.getDealer().addCard(card, 0);
		game.getDealer().addCard(card2, 0);
		game.defineWinner();
		assertTrue(true == game.getDealer().isWin());
		assertTrue(false == game.getUser().isWin());
	}
	
	
	public void testValueOfAce() {
		Card card = new Card();
		Card card1 = new Card();
		Card card2 = new Card();
		Card card3 = new Card();
		card1.setName("H8");
		card2.setName("SA");
		card3.setName("HA");
		Game game = new Game();
		game.getUser().addCard(card1, 0);
		game.getUser().addCard(card2, 0);
		game.getUser().addCard(card3, 0);
		System.out.println(game.getUser().getPoint());
		assertTrue(20 == game.getUser().getPoint());
		
		game = new Game();
		card.setName("H8");
		card1.setName("S9");
		card2.setName("SA");
		card3.setName("HA");
		game.getUser().addCard(card, 0);
		game.getUser().addCard(card1, 0);
		game.getUser().addCard(card2, 0);
		game.getUser().addCard(card3, 0);

		System.out.println(game.getUser().getPoint());
		assertTrue(19 == game.getUser().getPoint());
		
		game = new Game();
		card.setName("SA");
		card1.setName("H5");
		card2.setName("DA");
		card3.setName("C10");
		game.getUser().addCard(card, 0);
		game.getUser().addCard(card1, 0);
		game.getUser().addCard(card2, 0);
		game.getUser().addCard(card3, 0);
		System.out.println(game.getUser().getPoint());
		assertTrue(17 == game.getUser().getPoint());
		
		
		
	}
	
	public void testBothStandCountingPoint() {
		Game game = new Game();
		Card card = new Card();
		Card card1 = new Card();
		Card card2 = new Card();
		Card card3 = new Card();
		card.setName("S9");
		card1.setName("H8");
		card2.setName("H9");
		card3.setName("C9");
		
		game.getUser().addCard(card, 0);
		game.getUser().addCard(card1, 0);
		game.getDealer().addCard(card2, 0);
		game.getDealer().addCard(card3, 0);
		
		game.getUser().setStand();
		game.getDealer().setStop();
		game.defineWinner();
		assertTrue(true  == game.getDealer().isWin());
		assertTrue(false == game.getUser().isWin());
		
		game = new Game();
		game.getUser().addCard(card2, 0);
		game.getUser().addCard(card3, 0);
		game.getDealer().addCard(card, 0);
		game.getDealer().addCard(card1, 0);
		game.getUser().setStand();
		game.getDealer().setStop();
		game.defineWinner();
		assertTrue(false == game.getDealer().isWin());
		assertTrue(true == game.getUser().isWin());
		
	}
	
	
	public void testUserAndDealerSpliting() {
		Card card = new Card();
		Card card1 = new Card();
		Card card2 = new Card();
		Card card3 = new Card();
		Game game = new Game();
		card.setName("DQ");
		card1.setName("SQ");
		card2.setName("S10");
		card3.setName("H5");
		game.getUser().addCard(card, 0);
		game.getUser().addCard(card1, 0);
		//game.getUser().canSplit();
		game.getUser().addCard(card2, 0);
		Card card4 = new Card();
		card4.setName("S6");
		game.getUser().addCard(card2, 0);
		game.getUser().addCard(card4, 0);
		game.getUser().addCard(card3, 1);
		assertTrue(15 == game.getUser().getPoint());
	}
	public void testDealerStandOrNot() {
		Game game = new Game();
		Card card = new Card();
		card.setName("HJ");
		Card card1 = new Card();
		card1.setName("H6");
		game.getDealer().addCard(card, 0);
		game.getDealer().addCard(card1, 0);
		assertTrue(true == game.getDealer().canHit());
		
		game = new Game();
		card = new Card();
		card.setName("HA");
		card1.setName("C6");
		game.getDealer().addCard(card, 0);
		game.getDealer().addCard(card1, 0);
		assertTrue(true == game.getDealer().canHit());
		
		game = new Game();
		card = new Card();
		card.setName("HK");
		card1.setName("C7");
		game.getDealer().addCard(card, 0);
		game.getDealer().addCard(card1, 0);
		assertTrue(false == game.getDealer().canHit());
			
	}
	public void testPlayerCanHit() {
		Game game = new Game();
		assertTrue(false == game.getUser().isStand());
		Card card = new Card();
		card.setName("H6");
		game.getUser().addCard(card, 0);
		assertTrue(false == game.getUser().isStand());
		game.getUser().setStand();
		assertTrue(true == game.getUser().isStand());
	}
	public void testPlayerBust() {
		Game game = new Game();
		Card card = new Card();
		Card card1 = new Card();
		Card card2 = new Card();
		card.setName("SK");
		card1.setName("SQ");
		card2.setName("SJ");
		game.getUser().addCard(card, 0);
		game.getUser().addCard(card1, 0);
		game.getUser().addCard(card2, 0);
		game.getDealer().addCard(card,0);
		game.getDealer().addCard(card1,0);
		game.defineWinner();
		assertTrue(false == game.getUser().isWin());
		assertTrue(true == game.getDealer().isWin());
	
	}
	
	
	
	
	
	
	
	

}
