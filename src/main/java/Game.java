
public class Game {
	private User user;
	private Dealer dealer;
	private boolean console;
	private boolean input;
	
	public Game() {
		user = new User();
		dealer = new Dealer();
		console = true;
		input = true;
	}
	
	public User getUser() {return user;}
	public Dealer getDealer() {return dealer;}
	public boolean hasConsole() {return console;}
	public boolean hasInput() {return input;}
	
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
