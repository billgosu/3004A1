
public class User extends Player {
	private boolean stand;
	public User() {
		super();
		stand = false;
	}
	//some functions to decide user hit or stand
	public void setStand() {stand = true;}
	public boolean isStand() {return stand;}
}
