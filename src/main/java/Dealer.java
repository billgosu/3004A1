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
			if(point == 17 && containAces(0) >= 1) {
				return true;
			}
			else return false;
		}
	
	}
	public void addCard(Card c, int i) {
		char str = c.getName().charAt(1);
		if(i == 0) { point = addPoint(str,point);
			if(point > 21 && containAces(0) > 0) {
				if(!changeAcesValue) {
					if(containAces(0) == 1) point -= 10;
					else if (containAces(0)== 2) point -= 10;
					changeAcesValue = true;
				}
			}
		}
		else { splitPoint = addPoint(str,splitPoint);
			if(splitPoint > 21 && containAces(1) > 0) {
				if(!changeAcesValue) {
					if(containAces(1) == 1) splitPoint -= 10;
					else if (containAces(1)== 2) point -= 10;
					changeAcesValue = true;
				}
			}
		}
		if(cardOwning.get(i).size() == 1) c.setVisible(false);
		cardOwning.get(i).add(c);
	}	
}
