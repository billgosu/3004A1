import javafx.scene.Scene;
import java.io.BufferedReader;
import java.io.File;
import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File.*;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class DisplayGame extends Application {
	private Button hitButton;
	private Button standButton;
	private Button restartButton;
	private Pane aPane;
	private GridPane grid;
	private Menu playmode;
	private Menu reset;
	private MenuItem console;
	private MenuItem inputFile;
	private MenuBar menuBar;
	private Image[] images;
	private Image backGround;
	private ImageView imageView;
	private Cards cards;
	private Game game;
	private int user_x = 400, user_y = 450,
			dealer_x = 400,dealer_y = 150, checkingPlayerOrDealer = 99;
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) {
		gamestart(primaryStage);
	}
	
	
	private void gamestart(Stage primaryStage) {
		aPane = new Pane();
		setup();
		primaryStage.setTitle("BlackJack Game");
		primaryStage.setScene(new Scene(aPane,1000,800));
		primaryStage.show();
	}

	private void setActions(Pane pane) {
		// set console action
		console.setOnAction(new EventHandler<ActionEvent>() {
		     public void handle(ActionEvent event) {
		       displayGameCards(aPane);
		       grid.add(hitButton,16,65,1,1);
		       grid.add(standButton,60,65,1,1);
		       grid.add(restartButton,70,2,1,1);
		   }});
		//reset
		reset.setOnAction(new EventHandler<ActionEvent>() {
		     public void handle(ActionEvent event) {
		       reset();
		   }});
		
		//set inputFolder action
		inputFile.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				pickFile();
			}});
		
		//set action on hitButton
		hitButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				hitButtonHandler(aPane);
			}});
		

		//set action on restartButton
		restartButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				reset();
			}});

		//set action on holdButton
		standButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				standButtonHandler(aPane);
			}});
		
	}
	//set up game when user click on console menu item.
	private void displayGameCards(Pane aPane) {
		reset();
		aPane.getChildren().remove(imageView);
		cards = new Cards();
		game = new Game();
		Image card;
		ImageView viewCard;
		checkingPlayerOrDealer = 0;
		for(int i =0; i < 4; i++) {
			card = new Image(getCardLocation(cards.getCard(0)));
			viewCard = new ImageView(card);
			
			if(i == 2) {
				viewCard = new ImageView("Red_back.jpg");
			}
			if(checkingPlayerOrDealer == 0) {
				game.getDealer().addCard((cards.getCard(0)),0);
				checkingPlayerOrDealer = 1;
				viewCard.relocate(dealer_x, dealer_y);
				dealer_x += 35;
			}
			
			else {
				game.getUser().addCard((cards.getCard(0)),0);
				checkingPlayerOrDealer = 0;
				viewCard.relocate(user_x, user_y);
				user_x += 35;
			}
		
			viewCard.setFitWidth(100);
			viewCard.setFitHeight(150);
			viewCard.setPreserveRatio(true);
			aPane.getChildren().addAll(viewCard);	
			cards.remove(0);	
		}
		game.defineWinner();
		announcement();
	}
	
	private void displayGameCardsOnFile(ArrayList<String> s) {
		//aPane.getChildren().remove(imageView);
		cards = new Cards();
		Image card;
		checkingPlayerOrDealer = 10;
		ImageView viewCard = null;
		
		for(int i = 0; i < s.size(); i++) {
			Card checkCard = new Card();
			int value = 0;
			if(s.get(i).length() >= 2) {
				char a = s.get(i).charAt(1);
				if (a == 'J' ||(a == 'K') || (a == 'Q')) {
					value = 10;
				}
				else if  (a == 'A') {
					value = 1;
				}
				else if  (a == '1') {
					value = 10;
				}
				else
					value = Character.getNumericValue(s.get(i).charAt(1));
			
				String b = "";
				b += s.get(i).charAt(0);
				b = "";
			
				if(s.get(i).charAt(1) == '1')
					b+= "10";
				else
					b += s.get(i).charAt(1);
				
				b += s.get(i).charAt(0);
				card = new Image(b + ".jpg");
				viewCard = new ImageView(card);
				if((i < 2) ||(checkingPlayerOrDealer == 1) ) {
					game.getUser().addCard(checkCard,0);
					viewCard.relocate(user_x, user_y);
					user_x += 35;
				}
				else if((i >= 2 && i < 4) ||(checkingPlayerOrDealer == 0)) {
					game.getDealer().addCard(checkCard,0);
					viewCard.relocate(dealer_x, dealer_y);
					dealer_x += 35;
				}
				viewCard.setFitWidth(100);
				viewCard.setFitHeight(150);
				viewCard.setPreserveRatio(true);
				aPane.getChildren().addAll(viewCard);
				}
			else {
				if(s.get(i).equals("H")) {
					checkingPlayerOrDealer = 1;}
				else if(s.get(i).equals("S")){
					checkingPlayerOrDealer = 0;}
				else {
					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setTitle("WARNING");
					alert.setContentText("your input is invalid");
					alert.showAndWait();
					reset();
					return;
				}
			}
					
		}
		//define winner;
		game.defineWinner();
		//report winner to users.
		announcement();
		reset();
	}
	

	//hit button actions
	private void hitButtonHandler(Pane aPane) {
		// TODO Auto-generated method stub		
		Image card = new Image(getCardLocation(cards.getCard(0)));
		ImageView viewCard = new ImageView(card);
		game.getUser().addCard(cards.getCard(0),0);
		//allocate card location on GUI
		viewCard.relocate(user_x, user_y);
		viewCard.setFitWidth(100);
		viewCard.setFitHeight(150);
		viewCard.setPreserveRatio(true);
		aPane.getChildren().addAll(viewCard);
		user_x += 35;
		cards.remove(0);
		if(game.getUser().getPoint() >= 22) {
			game.defineWinner();
			displayInvisibleCard();
			announcement();
		}
	};
	
	//stand button action
	private void standButtonHandler(Pane aPane) {
		// TODO Auto-generated method stub
		//disable invisible card
		ImageView viewCard;
		hitButton.setDisable(true);
		displayInvisibleCard();
		
			while(game.getDealer().canHit()) {
				Image card = new Image(getCardLocation(cards.getCard(0)));
				viewCard = new ImageView(card);
				game.getDealer().addCard(cards.getCard(0),0);
				//set up card location on GUI.
				viewCard.relocate(dealer_x, dealer_y);
				viewCard.setFitWidth(100);
				viewCard.setFitHeight(150);
				viewCard.setPreserveRatio(true);
				aPane.getChildren().addAll(viewCard);
				dealer_x += 35;
				cards.remove(0);
				
			}
		// using defineWinner function to find winner()
		game.defineWinner();
		//report winner if we have one.
		announcement();
		//drawing case.
		
	};
	
	// display invisible card of the dealer
	private void displayInvisibleCard() {
		// TODO Auto-generated method stub
		ImageView viewCard;
		if(game.getDealer().getCard(0).size() == 2) {
			String id = getCardLocation(game.getDealer().getCard(0).get(1));
			Image card = new Image(id);
			//set up card location.
			viewCard = new ImageView(card);
			viewCard.setFitWidth(100);
			viewCard.setFitHeight(150);
			viewCard.setPreserveRatio(true);
			viewCard.relocate(dealer_x-35, dealer_y);
			aPane.getChildren().addAll(viewCard);	
		}
		
	}

	//using card' points and quality to get card'id in resources
	private String getCardLocation(Card card) {
		String id = "",quality = "";
		quality +=  cards.getCard(0).getName().charAt(0);
		id += cards.getCard(0).getName().substring(1, cards.getCard(0).getName().length());
		id += quality + ".jpg";	
		return id;
	}
	// report who's gonna win this game
	private void announcement() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Winner Identify");
		Optional<ButtonType> result = null;
		if(game.getDealer().isWin()) {
			alert.setHeaderText("THE DEALER WON");
			alert.setContentText("The Dealer has " + game.getDealer().getPoint() + " point.\n You have "
					+ game.getUser().getPoint() + "\nThis is just a bad luck, try another. Good Luck next time");
			result = alert.showAndWait();
		}
		else{
			alert.setHeaderText("YOU WON");
			alert.setContentText("The Dealer has " + game.getDealer().getPoint() + " point.\n You have "
					+ game.getUser().getPoint() + "\n.CONGRATULATION");
			result = alert.showAndWait();	
		}
	}
	
	private void pickFile() {
		// TODO Auto-generated method stub
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int returnVal = chooser.showOpenDialog(null);
		String path = "";
		try{
			path = chooser.getSelectedFile().getAbsolutePath();
			}
		catch(NullPointerException e) {
			System.out.println(e);
		}
		if(!path.equals("") && path.contains("txt")) {
		ArrayList<String> s = new ArrayList();
		ArrayList<String> s1 = new ArrayList();
		
		BufferedReader inputStream = null;
		try {
			inputStream = new BufferedReader(new FileReader(path));
			for (String line = inputStream.readLine(); line != null; line = inputStream.readLine()) {
	            s.add(line);
	        }
			while(s.get(0).contains(" ")) {
				int x = s.get(0).indexOf(" ");
				String str = "";
				s1.add(s.get(0).substring(0, x));
				str = s.get(0).substring(x+1,s.get(0).length());
				s.set(0, str);
			}
			s1.add(s.get(0));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		aPane.getChildren().removeAll(imageView);
		displayGameCardsOnFile(s1);}
		else {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("WARNING");
			alert.setContentText("You pick wrong file, please choose txt file");
			alert.showAndWait();
		}
	}
	
	public void reset() {
		aPane.getChildren().clear();
		setup();
	    user_x = 400;user_y = 450;
		dealer_x = 400;dealer_y = 150; 
		checkingPlayerOrDealer = 99;
	}
	
	public void setup(){			
		 grid = new GridPane();
	     grid.setHgap(10);
	     grid.setVgap(10);
	     grid.setPadding(new Insets(10, 10, 10, 10));

		
		//create Play Mode
		 playmode = new Menu("Play Mode");
		 console =  new MenuItem("Console");
		 inputFile = new MenuItem("inputFile");
		 playmode.getItems().addAll(console,inputFile);
		
		//reset mode
		 reset = new Menu("reset");
		
		//add the hitButton
		 hitButton = new Button("Hit");
		 hitButton.setMinHeight(30);
		 hitButton.setMinWidth(100);
		 hitButton.setPrefWidth(100);
		
		 //add holdButton
		 standButton = new Button("Stand");
		 standButton.setMinHeight(30);
		 standButton.setMinWidth(100);
		 standButton.setPrefWidth(100);
		 
		//add restartButton
		 restartButton = new Button("restart");
		 restartButton.setMinHeight(30);
		 restartButton.setMinWidth(100);
		 restartButton.setPrefWidth(100);
		
		
		 //add background image
		 backGround = new Image("aces.jpg");
		 imageView = new ImageView(backGround);
		 imageView.relocate(100, 150);
		 imageView.setFitWidth(800);
		 imageView.setFitHeight(800);
		 imageView.setPreserveRatio(true);
		 setActions(aPane);
		
		//add Menu PlayMode
		menuBar = new MenuBar();
		menuBar.getMenus().addAll(playmode,reset);
		aPane.getChildren().addAll(menuBar,grid,imageView);
		
	}
}
