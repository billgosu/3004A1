import java.util.ArrayList;

public class Dealer extends Player{
	private boolean stop;
	
	public Dealer() {
		super();
		stop = false;
	}
	
	public boolean isStop() {return stop;}
	public void setStop() {stop = true;}
	//function to check that dealer continuous hitting card or stop 
	public boolean canHit() {
		if(point < 17)	return true;
		else {
			if(point == 17 && containsAces(0))
				return true;
			else return false;
		}
	
	}
	//function to help calculate point
	public boolean containsAces(int i) {
		for(int u =0; u < this.getCard(u).size();u++) {
			if((this.getCard(i).get(u).getName() == "HA") ||
			(this.getCard(i).get(u).getName() == "CA") ||
			(this.getCard(i).get(u).getName() == "SA") ||
			(this.getCard(i).get(u).getName() == "DA"))
				return true;
		}
		return false;
	}
}
