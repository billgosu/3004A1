
public class Game {
	private User user;
	private Dealer dealer;
	
	public Game() {
		user = new User();
		dealer = new Dealer();	
	}
	
	public User getUser() {return user;}
	public Dealer getDealer() {return dealer;}
	
	public void defineWinner() {
		if((user.getPoint() > 21 && dealer.getPoint() <= 21) ||
			(user.getPoint() == 21 && dealer.getPoint() == 21)||
			dealer.getPoint() == 21)
			dealer.setWinner();
		else if ((dealer.getPoint() > 21 && user.getPoint() <= 21)||
				user.getPoint() == 21)
			user.setWinner();
		else if (user.isStand() && dealer.isStop()) {
			if(user.getPoint() < dealer.getPoint()) dealer.setWinner();
			else user.setWinner();
		}	
}
	
	
	
}
