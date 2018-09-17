import java.util.ArrayList;

public class Player {

	protected int point;
	protected int splitPoint;
	protected int hands;
	protected ArrayList<ArrayList<Card>> cardOwning;
	protected boolean iswin;
	protected boolean split;
	protected boolean changeAcesValue;
	
	public Player() {
		cardOwning = new ArrayList<ArrayList<Card>>();
		cardOwning.add(new ArrayList<Card>());
		iswin = false;
		split = false;
		changeAcesValue = false;
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
	public boolean isSplit() {
		if(cardOwning.get(0).get(0).getName().charAt(1) == 
				cardOwning.get(0).get(1).getName().charAt(1)) {
				split = true;}	
		return split;}
	public ArrayList<Card> getCard(int i){
		return cardOwning.get(i);
	}
	//add card to player' hand
	public void addCard(Card c, int i) {
		char str = c.getName().charAt(1);
		if(i == 0) { point = addPoint(str,point);
			if(point > 21 && containAces(i) > 0) {
				if(!changeAcesValue) {
					if(containAces(i) == 1) point -= 10;
					else if (containAces(i)== 2) point -= 10;
					changeAcesValue = true;
				}
			}
		}
		else { splitPoint = addPoint(str,splitPoint);
			if(splitPoint > 21 && containAces(i) > 0) {
				if(!changeAcesValue) {
					if(containAces(i) == 1) splitPoint -= 10;
					else if (containAces(i)== 2) point -= 10;
					changeAcesValue = true;
				}
			}
		}
		cardOwning.get(i).add(c);
	}
	public int addPoint(char str, int i) {
		if(str == '1' || str == 'J' || str == 'Q' || str == 'K') i += 10;
		else if (str == 'A') {
			if(i + 11 > 21) {
				if(containAces(0) == 0) {
					changeAcesValue = true;
					i += 1;
				}
			}
			else i += 11;
		}
		else {
			i += Character.getNumericValue(str);
		}
		return i;
	}
	//do the split();
	public void doSplit() {
		if(split == true) {
			cardOwning.add(new ArrayList<Card>());
			cardOwning.get(1).add(cardOwning.get(0).get(1));
			cardOwning.get(0).remove(1);
			splitPoint = point/2;
			point = splitPoint;
		}
	}
	// get point of player
	public int getPoint() {
		if(split) {
			if(splitPoint > 21 && point < 21) return point;
			else if (splitPoint < 21 && point > 21) return splitPoint;
			else {
				return Math.max(splitPoint, point);
			}
		}
		
		else
			return point;}
	
	public int containAces(int u) {
		int num = 0;
		for(int i =0; i < cardOwning.get(u).size();i++) {
			if(cardOwning.get(u).get(i).getName().equals("HA") ||
				cardOwning.get(u).get(i).getName().equals("SA") ||
				cardOwning.get(u).get(i).getName().equals("CA") ||
				cardOwning.get(u).get(i).getName().equals("DA"))
				num++;
		}
		return num;
	}
	
}
