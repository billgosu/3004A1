
public class User extends Player {
	private boolean stand;
	private boolean hit;
	public User() {
		super();
		stand = false;
	}
	//some functions to decide user hit or stand
	public void setStand() {stand = true;}
	private void setHit() {hit = true;}
	
	public boolean isStand() {return stand;}
}
