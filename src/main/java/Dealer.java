import java.util.ArrayList;

public class Dealer extends Player{
	private boolean stop;
	
	public Dealer() {
		super();
		stop = false;
	}
	
	public boolean isStop() {return stop;}
	public void setStop() {stop = true;}
}
