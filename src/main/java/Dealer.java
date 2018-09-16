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
			if(point == 17 && containsAces(0)) {
				return true;
			}
			else return false;
		}
	
	}
	//function to help calculate point
	public boolean containsAces(int i) {
		for(int u =0; u < this.getCard(i).size();u++) {
			if((this.getCard(i).get(u).getName().equals("HA")) ||
			(this.getCard(i).get(u).getName().equals("CA")) ||
			(this.getCard(i).get(u).getName().equals("SA")) ||
			(this.getCard(i).get(u).getName().equals("DA")))
				return true;
		}
		return false;
	}
	
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
		if(cardOwning.get(i).size() == 1) c.setVisible(false);
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
}
