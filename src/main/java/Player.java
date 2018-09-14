import java.util.ArrayList;

public class Player {

	private int point;
	private int hands;
	private ArrayList<ArrayList<Card>> cardOwning;
	private boolean iswin;
	private boolean split;
	
	public Player() {
		cardOwning = new ArrayList<ArrayList<Card>>();
		iswin = false;
		split = false;
		point = 0;
		hands = 1;
	}
	
	public void setPoint(int p) {point = p;}
	public void setWinner() {iswin = true;}
	public void setHands(int h) {hands =  h;}
	public void setSplit(boolean s) {split = s;}
	
	
	public boolean isWin() { return iswin;}
	public int getHands() {return hands;}
	public boolean canSplit() {return split;}
	public int getPoint() {return point;}
	public ArrayList<Card> getCard(int i){
		return cardOwning.get(i);
	}

	
	
	
	
	
}
