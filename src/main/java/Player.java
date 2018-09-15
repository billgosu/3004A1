import java.util.ArrayList;

public class Player {

	protected int point;
	protected int splitPoint;
	protected int hands;
	protected ArrayList<ArrayList<Card>> cardOwning;
	protected boolean iswin;
	protected boolean split;
	
	public Player() {
		cardOwning = new ArrayList<ArrayList<Card>>();
		cardOwning.add(new ArrayList<Card>());
		iswin = false;
		split = false;
		point = 0;
		splitPoint = 0;
		hands = 1;
	}
	
	public void setPoint(int p) {point = p;}
	public void setSplitPoint(int p) {splitPoint = p;}
	public void setWinner() {iswin = true;}
	public void setHands(int h) {hands =  h;}
	public void setSplit(boolean s) {split = s;}
	
	public int getSplitPoint() {return splitPoint;}
	public boolean isWin() { return iswin;}
	public int getHands() {return hands;}
	public boolean isSplit() {return split;}
	public int getPoint() {return point;}
	public ArrayList<Card> getCard(int i){
		return cardOwning.get(i);
	}
	
	public void addCard(Card c, int i) {
		char str = c.getName().charAt(1);
		if(str == '1' || str == 'J' || str == 'Q' || str == 'K') point += 10;
		else if (str == 'A') {
			if(!containsAces() && cardOwning.get(0).size() < 2) {
				if(i == 0) point += 11;
				else splitPoint += 11;
			}
			else if (containsAces() && cardOwning.get(0).size() < 2) {
				if(i == 0) point -= 9;
				else splitPoint -=9;
			}
			else if (!containsAces() && cardOwning.get(0).size() > 2) {
				if(i == 0) {
					if(point > 10) point+= 1;
					else point += 11;
				}
				else {
					if(splitPoint > 10) splitPoint+= 1;
					else splitPoint += 11;
				}
			}
			else { 
				if(i ==0) point -= 9;
				else splitPoint -= 9;
			}
		}
		else {
			if(i == 0) point += Character.getNumericValue(str);
			else  splitPoint += Character.getNumericValue(str);
		}
		cardOwning.get(i).add(c);
		
	}
	
	public boolean containsAces() {
		for(int i =0; i < cardOwning.get(0).size(); i++) {
			if(cardOwning.get(0).get(i).getName().equals("HA")||
				cardOwning.get(0).get(i).getName().equals("CA")||
				cardOwning.get(0).get(i).getName().equals("DA")||
				cardOwning.get(0).get(i).getName().equals("SA")) {
				return true;
			}
		}
		return false;
	}

	
	public void canSplit() {
		for(int i =0; i < 2; i++) {
		}
	}
	
	
	
}
