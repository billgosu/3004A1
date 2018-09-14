
public class Game {
	private User user;
	private Dealer dealer;
	private Cards cards;
	
	public Game() {
		cards = new Cards();
		user = new User();
		dealer = new Dealer();	
	}
	
	public User getUser() {return user;}
	public Dealer getDealer() {return dealer;}
	
	public void defineWinner() {
		if(user.getHands() == 1 && dealer.getHands() == 1) {
			if((user.getPoint() > 21 && dealer.getPoint() <= 21) ||
				(user.getPoint() == 21 && dealer.getPoint() == 21))
				dealer.setWinner();
			else if (user.getPoint() > 21 && user.getPoint() < 21)
				user.setWinner();
			else if (user.isStand() && dealer.isStop()) {
					if(user.getPoint() < dealer.getPoint()) dealer.setWinner();
					else user.setWinner();
			}	
		}
		else if (user.getHands() == 1 && dealer.getHands() == 2) {
			
		}
		else if (user.getHands() == 2 && dealer.getHands() == 1) {
			
		}
	}
	
}
