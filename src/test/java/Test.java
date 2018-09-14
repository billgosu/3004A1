import static org.junit.Assert.assertArrayEquals;
import junit.framework.TestCase;

public class Test {

	public void testCardCount() {
		Cards card = new Cards();
		assertEquals(52,card.getSize());
	}
	
	public void testCardShuffer() {
		boolean testshuffer;
	}
	
	
	public void BothBlackJack() {
		Game game = new Game();
		game.getPlayer().setPoint(21);
		game.getDealer().setPoint(21);
		game.defineWinner();
		assertEquals(true,game.getDealer().isWin());
		assertEquals(false,game.getPlayer().isWin());
	}
	
	public void BothStand() {
		Game game = new Game();
		player.setPoint(20);
		dealer.setPoint(19);
		game.defineWinner();
		assertEquals(false,game.getDealer().isWin());
		assertEquals(true,game.getPlayer().isWin());
		
		game.getPlayer().setPoint(19);
		game.getDealer().setPoint(20);
		game.defineWinner();
	
		assertEquals(true,game.getDealer().isWin());
		assertEquals(false,game.getPlayer().isWin());
		
	}
	public void canSplit() {
		Game game = new Game();
		if(game.getPlayer().getCard().get(0).getPoint == game.getPlayer().getCard().get(1).getPoint)
			assertEquals(true,game.getPlayer().canSplit());
		if(game.Dealer().getCard().get(0).getPoint == game.Dealer().getCard().get(1).getPoint)
			assertEquals(true,game.getDealer().canSplit());
		
	}
	
	

}
