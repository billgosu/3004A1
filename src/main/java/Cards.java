import java.util.ArrayList;

public class Cards {
	private ArrayList<Card> cards; // cards
	
	//return cards;
	public ArrayList<Card> getCards() {	return cards;}
	
	// return cards	
	public Card getCard(int i ) {return cards.get(i);}
	//return cards size
	public int getSize() {return cards.size();}
	// index of card x
	public int Indexof(Card a) {return cards.indexOf(a);}
	public void remove(int i) {		cards.remove(i);}
	public void remove(Card i) {
		for(int u =0; u < cards.size();u++) {
			if(cards.get(u).getName().equals(i.getName())) {
				cards.remove(cards.get(u));
				return;
			}
		}
	}
	
	// set Card' values
	public void setCards() {
		int value = 0;
		cards = new ArrayList<Card>();
		for(int i = 1; i< 14; i++) {
			value = i;
			String str = "";
			for(int u =0; u < 4; u++) {
				String suit;
				if (u == 0)
					suit = "S";
				else if (u == 1)
					suit = "C";
				else if (u == 2)
					suit = "D";
				else
					suit = "H";
				Card newCard = new Card();
				str += suit;
				if(value == 1) str += ("A");	
				else if (value > 10) {
					if (value == 11) str += "J";
					else if (value == 12) str += "Q";
					else str += "K";
				}
				else
					str += value;
				
				newCard.setName(str);
				newCard.setVisible(true);
				str = "";
				cards.add(newCard);
			}
		}
	}
	//function to shuffling card
	public void shuffling () {
		for(int u =0; u < 5; u++) {
			for(int i =0; i < cards.size();i++) {
				int x = (int)(Math.random()*cards.size());
				Card card = cards.get(i);
				cards.set(i, cards.get(x));
				cards.set(x, card);
			}
		}
	}
	
	public Cards() {
		cards = new ArrayList<Card>();
		setCards();
		shuffling();
	}
	public boolean contain(Card u) {
		for(int i =0; i < cards.size();i++) {
			if(cards.get(i).getName().equals(u.getName()))
				return true;
		}
		return false;
	}


}
