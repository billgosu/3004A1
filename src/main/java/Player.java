import java.util.ArrayList;

public class Player {

	protected int point;
	protected int splitPoint;
	protected int hands;
	protected ArrayList<ArrayList<Card>> cardOwning;
	protected boolean iswin;
	protected boolean split;
	protected boolean changeAcesValue1;
	protected boolean changeAcesValue2;
	
	public Player() {
		cardOwning = new ArrayList<ArrayList<Card>>();
		cardOwning.add(new ArrayList<Card>());
		iswin = false;
		split = false;
		changeAcesValue1 = false;
		changeAcesValue2 = false;
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
		if(str == '1' || str == 'J' || str == 'Q' || str == 'K') point += 10;
		else if (str == 'A') {
			if(i == 0) {
				if(containsAces() == 0 && (point + 11 <= 21))	point += 11;
				else point += 1;
			
				if(containsAces() == 1 && !changeAcesValue1) { 
					point -= 9;
					changeAcesValue1 = true;
				}
				else if (containsAces() == 1) point += 1;
				
				if(containsAces() == 2) point += 1;
				if(containsAces() == 3) point += 1;	
			}
			if(i == 1) {
				if(containsAces() == 0 && (splitPoint + 11 <= 21))		splitPoint += 11;
				else splitPoint += 1;
				
				if(containsAces() == 1 && !changeAcesValue2) { 
					splitPoint -= 9;
					changeAcesValue2 = true;
				}
				else if (containsAces() == 1) splitPoint += 1;
				
				if(containsAces() == 2) splitPoint += 1;
				if(containsAces() == 3) splitPoint += 1;	
			}
		}
		else {
			if(i == 0) point += Character.getNumericValue(str);
			else  splitPoint += Character.getNumericValue(str);
		}
		cardOwning.get(i).add(c);
		if(i == 0) {
			if(!changeAcesValue1 && containsAces() == 1 && point > 21) {
				point = Math.min(point-10, point);
				changeAcesValue1 = true;
				}
		}
		if(i == 1) {
			if(!changeAcesValue2 && containsAces() == 1 && point > 21) {
				point = Math.min(point-10, point);
				changeAcesValue2 = true;
				}
		}
		
	}
	//checking containsAces
	public int containsAces() {
		int num = 0;
		for(int i =0; i < cardOwning.get(0).size(); i++) {
			if((cardOwning.get(0).get(i).getName().equals("HA"))) num++;
			else if (cardOwning.get(0).get(i).getName().equals("CA")) num++;
			else if (cardOwning.get(0).get(i).getName().equals("DA")) num++;
			else if (cardOwning.get(0).get(i).getName().equals("SA")) num++;
		}
		return num;
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
	
	
	
}
