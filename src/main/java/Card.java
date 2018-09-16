public class Card{
	
	private String name;
	private boolean visible;
	
	public Card() {
		name = "";
		visible = true;
	}
	
	//set name card
	public void setName(String n) {	name = n;}
	public void setVisible(boolean b) {visible = b;}
	
	//get name card
	public String getName() {return name;}
	public boolean getVisible() {return visible;}

	
	
}
