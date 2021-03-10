package application;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.scene.paint.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;


public class MainController {
	@FXML 
	private Pane pane1;
	
	@FXML
	private Pane pane2;
	@FXML
	private Label round = new Label();
	@FXML
	private TextField xTarget;
	@FXML
	private TextField yTarget;
	@FXML
	private Button shootEnemy;
	@FXML 
	private Label playerActions = new Label();
	@FXML 
	private Label playerScore = new Label();
	@FXML 
	private Label playerSuccessRate = new Label();
	@FXML 
	private Label enemyActions = new Label();
	@FXML 
	private Label enemyScore = new Label();
	@FXML 
	private Label enemySuccessRate = new Label();
	
	@FXML
    private TableView<EnemyShips> tableEnemyShips ;

    @FXML
    private TableColumn<EnemyShips,String> shipType;

    @FXML
    private TableColumn<EnemyShips,String> state;
    
    @FXML
    private TableView<PlayerShots> tablePlayerShots;
    @FXML
    private TableView<PlayerShots> tableEnemyShots;
    @FXML
    private TableColumn<PlayerShots,Integer> coordinateX;
    @FXML
    private TableColumn<PlayerShots,Integer> coordinateY;
    @FXML
    private TableColumn<PlayerShots,String> shotResult;
    
    @FXML
    private TableColumn<PlayerShots,String> shipTp;
    @FXML
    private Label totalActiveShipsPlayer = new Label();

    @FXML
    private Label totalActiveShipsEnemy = new Label();
    
    
	private int size = 200;
	private int spots = 10;
	private int squareSize = size/spots;
	private Player player = new Player();
	private Stack<PlayerShots> playerShots = new Stack<PlayerShots>(); 
	private Player enemy = new Player();
	private Stack<PlayerShots> enemyShots = new Stack<PlayerShots>();
	private int rnd = 0;
	private boolean winner = false;
	private int enemyPreviousX = -1;
	private int enemyPreviousY = -1;
	
	public void initialize() {
	   
		   for(int i=0; i<200; i+=squareSize) {
				for(int j=0; j<200; j+=squareSize) {
					Rectangle r1 = new Rectangle(i,j,squareSize, squareSize);
					r1.setFill(Color.WHITE);
					r1.setStroke(Color.BLACK);
					pane1.getChildren().add(r1);
					Rectangle r2 = new Rectangle(i,j, squareSize, squareSize);
					r2.setFill(Color.WHITE);
					r2.setStroke(Color.BLACK);
					pane2.getChildren().add(r2);
				}
			}
	   
		
	}
	
	public void exitFunction(ActionEvent Event) {
		File Obj = new File("medialab/game.txt");
		Obj.delete();
		System.out.println("Exit");
		Platform.exit();
		System.exit(0);
	}
	
	public void loadFunction(ActionEvent Event) {
		try {
			File Obj = new File("medialab/game.txt");
			Obj.delete();
			
			
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Application/Load.fxml"));
			Scene scene = new Scene(root,300,300);
			Stage stage = new Stage ();
			stage.setTitle("Medialab Project");
			stage.setScene(scene);
			stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static HashSet<Pair<Integer,Integer>> fillBox(HashSet<Pair<Integer,Integer>> set,String str){
		int x = Character.getNumericValue(str.charAt(0));
		int y = Character.getNumericValue(str.charAt(1));
		int xx = Character.getNumericValue(str.charAt(2));
		int yy = Character.getNumericValue(str.charAt(3));
		for(int i=x; i<=xx; i++) {
			for(int j=y; j<=yy; j++) {
				Pair<Integer,Integer> pair = new Pair<>(i,j);
				set.add(pair);
			}
		}
		return set;
	}
	public void startFunction(ActionEvent Event) {		
		for(int i=0; i<200; i+=squareSize) {
			for(int j=0; j<200; j+=squareSize) {
				Rectangle r1 = new Rectangle(i,j,squareSize, squareSize);
				r1.setFill(Color.WHITE);
				r1.setStroke(Color.BLACK);
				pane1.getChildren().add(r1);
				Rectangle r2 = new Rectangle(i,j, squareSize, squareSize);
				r2.setFill(Color.WHITE);
				r2.setStroke(Color.BLACK);
				pane2.getChildren().add(r2);
			}
		}
		try {

			File Obj = new File("medialab/game.txt");
			Scanner Reader = new Scanner(Obj);
			String data = Reader.nextLine();
			player.setPlayerCarrier(fillBox(new HashSet<Pair<Integer,Integer>>(),data));
			data = Reader.nextLine();
			player.setPlayerBattleship(fillBox(new HashSet<Pair<Integer,Integer>>(),data));
			data = Reader.nextLine();
			player.setPlayerCruiser(fillBox(new HashSet<Pair<Integer,Integer>>(),data));
			data = Reader.nextLine();
			player.setPlayerSubmarine(fillBox(new HashSet<Pair<Integer,Integer>>(),data));
			data = Reader.nextLine();
			player.setPlayerDestroyer(fillBox(new HashSet<Pair<Integer,Integer>>(),data));
			data = Reader.nextLine();
			enemy.setPlayerCarrier(fillBox(new HashSet<Pair<Integer,Integer>>(),data));
			data = Reader.nextLine();
			enemy.setPlayerBattleship(fillBox(new HashSet<Pair<Integer,Integer>>(),data));
			data = Reader.nextLine();
			enemy.setPlayerCruiser(fillBox(new HashSet<Pair<Integer,Integer>>(),data));
			data = Reader.nextLine();
			enemy.setPlayerSubmarine(fillBox(new HashSet<Pair<Integer,Integer>>(),data));
			data = Reader.nextLine();
			enemy.setPlayerDestroyer(fillBox(new HashSet<Pair<Integer,Integer>>(),data));
			Reader.close();
			for(Pair<Integer,Integer> pair: player.getPlayerCarrier() ) {
				Circle c = new Circle();
				c.setFill(Color.CORNFLOWERBLUE);
				c.setStroke(Color.BLACK);
				double radius = squareSize/3.0;
				int x = squareSize/2+squareSize*pair.getValue();
				int y = squareSize/2+squareSize*pair.getKey();
				Piece p = new Piece(x,y,radius,c);
				pane1.getChildren().add(c);
				p.draw();
			}
			for(Pair<Integer,Integer> pair: player.getPlayerBattleship() ) {
				Circle c = new Circle();
				c.setFill(Color.GREY);
				c.setStroke(Color.BLACK);
				double radius = squareSize/3.0;
				int x = squareSize/2+squareSize*pair.getValue();
				int y = squareSize/2+squareSize*pair.getKey();
				Piece p = new Piece(x,y,radius,c);
				pane1.getChildren().add(c);
				p.draw();
			}
			for(Pair<Integer,Integer> pair: player.getPlayerCruiser() ) {
				Circle c = new Circle();
				c.setFill(Color.ORANGE);
				c.setStroke(Color.BLACK);
				double radius = squareSize/3.0;
				int x = squareSize/2+squareSize*pair.getValue();
				int y = squareSize/2+squareSize*pair.getKey();
				Piece p = new Piece(x,y,radius,c);
				pane1.getChildren().add(c);
				p.draw();
			}
			for(Pair<Integer,Integer> pair: player.getPlayerSubmarine() ) {
				Circle c = new Circle();
				c.setFill(Color.YELLOW);
				c.setStroke(Color.BLACK);
				double radius = squareSize/3.0;
				int x = squareSize/2+squareSize*pair.getValue();
				int y = squareSize/2+squareSize*pair.getKey();
				Piece p = new Piece(x,y,radius,c);
				pane1.getChildren().add(c);
				p.draw();
			}
			for(Pair<Integer,Integer> pair: player.getPlayerDestroyer() ) {
				Circle c = new Circle();
				c.setFill(Color.DARKSEAGREEN);
				c.setStroke(Color.BLACK);
				double radius = squareSize/3.0;
				int x = squareSize/2+squareSize*pair.getValue();
				int y = squareSize/2+squareSize*pair.getKey();
				Piece p = new Piece(x,y,radius,c);
				pane1.getChildren().add(c);
				p.draw();
			}
			for(Pair<Integer,Integer> pair: enemy.getPlayerCarrier() ) {
				Circle c = new Circle();
				c.setFill(Color.CORNFLOWERBLUE);
				c.setStroke(Color.BLACK);
				double radius = squareSize/3.0;
				int x = squareSize/2+squareSize*pair.getValue();
				int y = squareSize/2+squareSize*pair.getKey();
				Piece p = new Piece(x,y,radius,c);
				pane2.getChildren().add(c);
				p.draw();
			}
			for(Pair<Integer,Integer> pair: enemy.getPlayerBattleship() ) {
				Circle c = new Circle();
				c.setFill(Color.GREY);
				c.setStroke(Color.BLACK);
				double radius = squareSize/3.0;
				int x = squareSize/2+squareSize*pair.getValue();
				int y = squareSize/2+squareSize*pair.getKey();
				Piece p = new Piece(x,y,radius,c);
				pane2.getChildren().add(c);
				p.draw();
			}
			for(Pair<Integer,Integer> pair: enemy.getPlayerCruiser() ) {
				Circle c = new Circle();
				c.setFill(Color.ORANGE);
				c.setStroke(Color.BLACK);
				double radius = squareSize/3.0;
				int x = squareSize/2+squareSize*pair.getValue();
				int y = squareSize/2+squareSize*pair.getKey();
				Piece p = new Piece(x,y,radius,c);
				pane2.getChildren().add(c);
				p.draw();
			}
			for(Pair<Integer,Integer> pair: enemy.getPlayerSubmarine() ) {
				Circle c = new Circle();
				c.setFill(Color.YELLOW);
				c.setStroke(Color.BLACK);
				double radius = squareSize/3.0;
				int x = squareSize/2+squareSize*pair.getValue();
				int y = squareSize/2+squareSize*pair.getKey();
				Piece p = new Piece(x,y,radius,c);
				pane2.getChildren().add(c);
				p.draw();
			}
			for(Pair<Integer,Integer> pair: enemy.getPlayerDestroyer() ) {
				Circle c = new Circle();
				c.setFill(Color.DARKSEAGREEN);
				c.setStroke(Color.BLACK);
				double radius = squareSize/3.0;
				int x = squareSize/2+squareSize*pair.getValue();
				int y = squareSize/2+squareSize*pair.getKey();
				Piece p = new Piece(x,y,radius,c);
				pane2.getChildren().add(c);
				p.draw();
			}
			
			System.out.println("New game starts!!!");
			player.setTotalNumberOfActions(0);
			playerActions.setText(Integer.toString(0));
			player.setScore(0);
			playerScore.setText(Integer.toString(0));
			player.setSuccessRate(0);
			playerSuccessRate.setText(Float.toString(0));
			player.setHasPlayed(false);
			player.setSuccessfulShots(0);
			player.setCarrierSize(5);
			player.setBattleshipSize(4);
			player.setCruiserSize(3);
			player.setSubmarineSize(3);
			player.setDestroyerSize(2);
			player.setCarrierState("Intact");
			player.setBattleshipState("Intact");
			player.setCruiserState("Intact");
			player.setSubmarineState("Intact");
			player.setDestroyerState("Intact");
			player.setType("");
			player.setTotalActiveShips(17);
			totalActiveShipsPlayer.setText(Integer.toString(17));
			totalActiveShipsPlayer.setTextFill(Color.BLUE);
			if(!playerShots.isEmpty()) {
				playerShots.clear();
			}
			enemy.setTotalNumberOfActions(0);
			enemyActions.setText(Integer.toString(0));
			enemy.setScore(0);
			enemyScore.setText(Integer.toString(0));
			enemy.setSuccessRate(0);
			enemySuccessRate.setText(Float.toString(0));
			enemy.setHasPlayed(false);
			enemy.setSuccessfulShots(0);
			enemy.setCarrierSize(5);
			enemy.setBattleshipSize(4);
			enemy.setCruiserSize(3);
			enemy.setSubmarineSize(3);
			enemy.setDestroyerSize(2);
			enemy.setCarrierState("Intact");
			enemy.setBattleshipState("Intact");
			enemy.setCruiserState("Intact");
			enemy.setSubmarineState("Intact");
			enemy.setDestroyerState("Intact");
			enemy.setType("");
			totalActiveShipsEnemy.setText(Integer.toString(17));
			totalActiveShipsEnemy.setTextFill(Color.RED);
			if(!enemyShots.isEmpty()) {
				enemyShots.clear();
			}
			enemyPreviousX = -1;
			enemyPreviousY = -1;
			winner = false;
			double coin = Math.random();
			if(coin>0.5) {
				System.out.println("Player plays first");
			}
			else {
				System.out.println("Enemy plays first");
				int xx = (int)(Math.random()*spots);
				int yy = (int)(Math.random()*spots);
				Pair<Integer,Integer> pair = new Pair<>(xx,yy);
				boolean successAttack = player.shipAttacked(pair);
				if(successAttack) {
					player.setTotalActiveShips(player.getTotalActiveShips()-1);
					totalActiveShipsPlayer.setText(Integer.toString(player.getTotalActiveShips()));
					totalActiveShipsPlayer.setTextFill(Color.BLUE);
					int points = player.enemyPoints();
					int scr = points + enemy.getScore();
					enemy.setScore(scr);
					enemyScore.setText(Integer.toString(scr));
					enemy.setSuccessfulShots(enemy.getSuccesfulShots()+1);
					String tp = player.getType();
					enemyShots.add(new PlayerShots(xx,yy,"Successful Shot",tp));
					Circle c = new Circle();
					c.setFill(Color.RED);
					c.setStroke(Color.BLACK);
					double radius = squareSize/3.0;
					int x = squareSize/2+squareSize*pair.getValue();
					int y = squareSize/2+squareSize*pair.getKey();
					Piece p = new Piece(x,y,radius,c);
					pane1.getChildren().add(c);
					p.draw();
				}
				else {
					enemyShots.add(new PlayerShots(xx,yy,"Unsuccessful Shot",""));
					Circle c = new Circle();
					c.setFill(Color.BLACK);
					c.setStroke(Color.BLACK);
					double radius = squareSize/3.0;
					int x = squareSize/2+squareSize*pair.getValue();
					int y = squareSize/2+squareSize*pair.getKey();
					Piece p = new Piece(x,y,radius,c);
					pane1.getChildren().add(c);
					p.draw();
				}
				enemy.setHasPlayed(true);
				int acts = enemy.getTotalNumberOfActions();
				acts++;
				enemy.setTotalNumberOfActions(acts);
				enemyActions.setText(Integer.toString(acts));
				enemy.setSuccessRate(100*enemy.getSuccesfulShots()/enemy.getTotalNumberOfActions());
				enemySuccessRate.setText(Float.toString(enemy.getSuccessRate()));
			}
			rnd = 1;
			round.setText(Integer.toString(rnd));
			
		} catch(FileNotFoundException e) {
			rnd = 0;
			round.setText(Integer.toString(rnd));
			System.out.println("There isn't a Loaded game file.");
			return;
		}
	}
	public void shoot(ActionEvent event) {
		if(event.getSource() == shootEnemy && (!winner)) {			
			int xx = Integer.parseInt(xTarget.getText());
			int yy = Integer.parseInt(yTarget.getText());
			Pair<Integer,Integer> pair = new Pair<>(xx,yy);
			boolean successAttack = enemy.shipAttacked(pair);
			if(successAttack) {
				enemy.setTotalActiveShips(enemy.getTotalActiveShips()-1);
				totalActiveShipsEnemy.setText(Integer.toString(enemy.getTotalActiveShips()));
				totalActiveShipsEnemy.setTextFill(Color.RED);
				int points = enemy.enemyPoints();
				int scr = points + player.getScore();
				player.setScore(scr);
				playerScore.setText(Integer.toString(scr));
				player.setSuccessfulShots(player.getSuccesfulShots()+1);
				String tp = enemy.getType();
				playerShots.add(new PlayerShots(xx,yy,"Successful Shot",tp));
				Circle c = new Circle();
				c.setFill(Color.RED);
				c.setStroke(Color.BLACK);
				double radius = squareSize/3.0;
				int x = squareSize/2+squareSize*pair.getValue();
				int y = squareSize/2+squareSize*pair.getKey();
				Piece p = new Piece(x,y,radius,c);
				pane2.getChildren().add(c);
				p.draw();
				if((enemy.getCarrierSize()+enemy.getBattleshipSize()+enemy.getCruiserSize()+enemy.getSubmarineSize()+enemy.getDestroyerSize())==0) {
					System.out.println("Congratulations, Player won!!!");
					winner = true;
				}
			}
			else {
				playerShots.add(new PlayerShots(xx,yy,"Unsuccessful Shot",""));
				Circle c = new Circle();
				c.setFill(Color.BLACK);
				c.setStroke(Color.BLACK);
				double radius = squareSize/3.0;
				int x = squareSize/2+squareSize*pair.getValue();
				int y = squareSize/2+squareSize*pair.getKey();
				Piece p = new Piece(x,y,radius,c);
				pane2.getChildren().add(c);
				p.draw();
			}
			player.setHasPlayed(true);
			int acts = player.getTotalNumberOfActions();
			acts++;
			player.setTotalNumberOfActions(acts);
			playerActions.setText(Integer.toString(acts));
			player.setSuccessRate(100*player.getSuccesfulShots()/player.getTotalNumberOfActions());
			playerSuccessRate.setText(Float.toString(player.getSuccessRate()));
			if(enemy.getHasPlayed()) {	
				player.setHasPlayed(false);
				enemy.setHasPlayed(false);
				rnd++;
				if(rnd==41) {
					winner = true;
					if(player.getScore()>enemy.getScore()) {
						System.out.println("Congratulations, Player won!!!");
					}
					else if(player.getScore()<enemy.getScore()){
						System.out.println("Oops, enemy won!");
					}
					else {
						System.out.println("Tie");
					}
					return;
				}
				round.setText(Integer.toString(rnd));
			}
			if((enemyPreviousX==-1)&&(enemyPreviousY==-1)) {
				xx = (int)(Math.random()*spots);
				yy = (int)(Math.random()*spots);
			}
			else {
				double rand = Math.random();
				
				if(rand<=0.25) {
					xx = enemyPreviousX-1;
					yy = enemyPreviousY;
				}
				else if(rand<=0.5) {
					xx = enemyPreviousX+1;
					yy = enemyPreviousY;
				}
				else if(rand<=0.75) {
					xx = enemyPreviousX;
					yy = enemyPreviousY-1;
				}
				else {
					xx = enemyPreviousX;
					yy = enemyPreviousY+1;
				}
				
			}
			
			Pair<Integer,Integer> ppair = new Pair<>(xx,yy);
			boolean ssuccessAttack = player.shipAttacked(ppair);
			if(ssuccessAttack) {
				enemyPreviousX = xx;
				enemyPreviousY = yy;
				player.setTotalActiveShips(player.getTotalActiveShips()-1);
				totalActiveShipsPlayer.setText(Integer.toString(player.getTotalActiveShips()));
				totalActiveShipsPlayer.setTextFill(Color.BLUE);
				int points = player.enemyPoints();
				int scr = points + enemy.getScore();
				enemy.setScore(scr);
				enemyScore.setText(Integer.toString(scr));
				enemy.setSuccessfulShots(enemy.getSuccesfulShots()+1);
				String tp = player.getType();
				enemyShots.add(new PlayerShots(xx,yy,"Successful Shot",tp));
				Circle c = new Circle();
				c.setFill(Color.RED);
				c.setStroke(Color.BLACK);
				double radius = squareSize/3.0;
				int x = squareSize/2+squareSize*ppair.getValue();
				int y = squareSize/2+squareSize*ppair.getKey();
				Piece p = new Piece(x,y,radius,c);
				pane1.getChildren().add(c);
				p.draw();
				if((player.getCarrierSize()+player.getBattleshipSize()+player.getCruiserSize()+player.getSubmarineSize()+player.getDestroyerSize())==0) {
					System.out.println("Oops, enemy won!");
					winner = true;
				}
			}
			else {
				enemyPreviousX = -1;
				enemyPreviousY = -1;
				enemyShots.add(new PlayerShots(xx,yy,"Unsuccessful Shot",""));
				Circle c = new Circle();
				c.setFill(Color.BLACK);
				c.setStroke(Color.BLACK);
				double radius = squareSize/3.0;
				int x= squareSize/2+squareSize*ppair.getValue();
				int y = squareSize/2+squareSize*ppair.getKey();
				Piece p = new Piece(x,y,radius,c);
				pane1.getChildren().add(c);
				p.draw();
			}
			enemy.setHasPlayed(true);
			acts = enemy.getTotalNumberOfActions();
			acts++;
			enemy.setTotalNumberOfActions(acts);
			enemyActions.setText(Integer.toString(acts));
			enemy.setSuccessRate(100*enemy.getSuccesfulShots()/enemy.getTotalNumberOfActions());
			enemySuccessRate.setText(Float.toString(enemy.getSuccessRate()));
			if(enemy.getHasPlayed()&&player.getHasPlayed()) {
				rnd++;
				if(rnd==41) {
					winner = true;
					if(player.getScore()>enemy.getScore()) {
						System.out.println("Congratulations, Player won!!!");
					}
					else if(player.getScore()<enemy.getScore()){
						System.out.println("Oops, enemy won!");
					}
					else {
						System.out.println("Tie");
					}
					return;
				}
				round.setText(Integer.toString(rnd));
				enemy.setHasPlayed(false);
			}
			player.setHasPlayed(false);
			
		}
		
	}
	public void enemyShips(ActionEvent event) {
		try {			
			Stage window = new Stage();
			window.setTitle("Medialab Project");
			tableEnemyShips = new TableView<>();
			ObservableList<EnemyShips> list = FXCollections.observableArrayList(
					new EnemyShips("Carrier",enemy.getCarrierState()),
					new EnemyShips("Battleship",enemy.getBattleshipState()),
					new EnemyShips("Cruiser",enemy.getCruiserState()),
					new EnemyShips("Submarine",enemy.getSubmarineState()),
					new EnemyShips("Destroyer",enemy.getDestroyerState())
			);
			shipType = new TableColumn<>("Ship Type");
			shipType.setMinWidth(300);
			shipType.setCellValueFactory(new PropertyValueFactory<EnemyShips,String>("type"));
			state = new TableColumn<>("State");
			state.setMinWidth(300);
			state.setCellValueFactory(new PropertyValueFactory<EnemyShips,String>("state"));
			tableEnemyShips.setItems(list);
			tableEnemyShips.getColumns().addAll(shipType,state);
			
			VBox vBox = new VBox();
			vBox.getChildren().addAll(tableEnemyShips);
			Scene scene = new Scene(vBox);
			window.setScene(scene);
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void playerShots(ActionEvent event) {
		try {			
			Stage window = new Stage();
			window.setTitle("Medialab Project");
			tablePlayerShots = new TableView<>();
			ObservableList<PlayerShots> list = FXCollections.observableArrayList();
			int counter = 5;
			ArrayList<PlayerShots> tmp = new ArrayList<PlayerShots>(playerShots);
			Collections.reverse(tmp);
			for(PlayerShots pl: tmp) {
				list.add(pl);
				counter--;
				if(counter==0) break;
			}
			coordinateX = new TableColumn<>("Coordinate X");
		    coordinateX.setMinWidth(200);
		    coordinateX.setCellValueFactory(new PropertyValueFactory<PlayerShots,Integer>("x"));
		    coordinateY = new TableColumn<>("Coordinate Y");
		    coordinateY.setMinWidth(200);
		    coordinateY.setCellValueFactory(new PropertyValueFactory<PlayerShots,Integer>("y"));
		    shotResult = new TableColumn<>("Shot Result");
		    shotResult.setMinWidth(200);
		    shotResult.setCellValueFactory(new PropertyValueFactory<PlayerShots,String>("shot"));
			shipTp = new TableColumn<>("Ship Type");
			shipTp.setMinWidth(200);
			shipTp.setCellValueFactory(new PropertyValueFactory<PlayerShots,String>("shipType"));
			tablePlayerShots.setItems(list);
			tablePlayerShots.getColumns().addAll(coordinateX,coordinateY,shotResult,shipTp);
			VBox vBox = new VBox();
			vBox.getChildren().addAll(tablePlayerShots);
			Scene scene = new Scene(vBox);
			window.setScene(scene);
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
	public void enemyShots(ActionEvent event) {
		try {			
			Stage window = new Stage();
			window.setTitle("Medialab Project");
			tableEnemyShots = new TableView<>();
			ObservableList<PlayerShots> list = FXCollections.observableArrayList();
			int counter = 5;
			ArrayList<PlayerShots> tmp = new ArrayList<PlayerShots>(enemyShots);
			Collections.reverse(tmp);
			for(PlayerShots pl: tmp) {
				list.add(pl);
				counter--;
				if(counter==0) break;
			}
			coordinateX = new TableColumn<>("Coordinate X");
		    coordinateX.setMinWidth(200);
		    coordinateX.setCellValueFactory(new PropertyValueFactory<PlayerShots,Integer>("x"));
		    coordinateY = new TableColumn<>("Coordinate Y");
		    coordinateY.setMinWidth(200);
		    coordinateY.setCellValueFactory(new PropertyValueFactory<PlayerShots,Integer>("y"));
		    shotResult = new TableColumn<>("Shot Result");
		    shotResult.setMinWidth(200);
		    shotResult.setCellValueFactory(new PropertyValueFactory<PlayerShots,String>("shot"));
			shipTp = new TableColumn<>("Ship Type");
			shipTp.setMinWidth(200);
			shipTp.setCellValueFactory(new PropertyValueFactory<PlayerShots,String>("shipType"));
			tableEnemyShots.setItems(list);
			tableEnemyShots.getColumns().addAll(coordinateX,coordinateY,shotResult,shipTp);
			VBox vBox = new VBox();
			vBox.getChildren().addAll(tableEnemyShots);
			Scene scene = new Scene(vBox);
			window.setScene(scene);
			window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
}
