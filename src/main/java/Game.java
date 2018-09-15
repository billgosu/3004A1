
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
		if(user.isSplit()) 
			user.setPoint(Math.max(user.getSplitPoint(), user.getSplitPoint()));
		if(dealer.isSplit())
			dealer.setPoint(Math.max(dealer.getSplitPoint(), dealer.getSplitPoint()));
			
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
	
	
	
}
