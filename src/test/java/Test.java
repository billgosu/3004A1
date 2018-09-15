import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import junit.framework.TestCase;

public class Test extends TestCase {

	
	public void testCardCount() {
		Cards card = new Cards();
		assertTrue(52 == card.getSize());
	}
	
	public void testCardShuffer() {
		Cards card = new Cards();
		Cards card1 = new Cards();
		assertTrue(card != card1);
	}
	
	public void testBothBlackJack() {
		Game game = new Game();
		game.getUser().setPoint(21);
		game.getDealer().setPoint(21);
		game.defineWinner();
		assertTrue(true == game.getDealer().isWin());
		assertTrue(false == game.getUser().isWin());
	}
	
	public void testBothStandCountingPoint() {
		Game game = new Game();
		game.getUser().setPoint(20);
		game.getDealer().setPoint(19);
		game.getUser().setStand();
		game.getDealer().setStop();
		game.defineWinner();
		assertTrue(false  == game.getDealer().isWin());
		assertTrue(true == game.getUser().isWin());
		
		game.getUser().setPoint(19);
		game.getDealer().setPoint(20);
		game.defineWinner();
	
		assertTrue(true == game.getDealer().isWin());
		assertFalse(false == game.getUser().isWin());
		
	}
	
	public void testingAddPointDealerAndPlayer() {
		Card card = new Card();
		Card card1 = new Card();
		card.setName("S10");
		card1.setName("D9");
		
		Game game = new Game();
		game.getDealer().addCard(card,0);
		game.getDealer().addCard(card1,0);
		assertTrue(19 == game.getDealer().getPoint());

		game.getUser().addCard(card,0);
		game.getUser().addCard(card1,0);
		assertTrue((int)19 == game.getUser().getPoint());
		
	}
	
	public void testAddAcesMethodForUser() {
		Card card = new Card();
		card.setName("HA");
		Card card1 = new Card();
		card1.setName("DJ");
		Game game = new Game();
		game.getUser().addCard(card,0);
		game.getUser().addCard(card1,0);
		assertTrue(21 == game.getUser().getPoint());	
	}
	

	public void testAddAcesMethodforDealer() {
		Card card = new Card();
		card.setName("HA");
		Card card1 = new Card();
		card1.setName("DJ");
		Game game = new Game();
		game.getDealer().addCard(card,0);
		game.getDealer().addCard(card1,0);
		assertTrue(21+0 == game.getDealer().getPoint());
	}
	
	public void testUserAndDealerSpliting() {
		Card card = new Card();
		Card card1 = new Card();
		Card card2 = new Card();
		Card card3 = new Card();
		Game game = new Game();
		card.setName("DQ");
		card1.setName("SQ");
		card2.setName("S9");
		card3.setName("H5");
		game.getUser().addCard(card, 0);
		game.getUser().addCard(card1, 0);
		game.getUser().canSplit();
		game.getUser().addCard(card2, 1);
		game.getUser().addCard(card3, 1);
		assertTrue(19 == game.getUser().getPoint());
	}
	
	
	
	
	
	

}
