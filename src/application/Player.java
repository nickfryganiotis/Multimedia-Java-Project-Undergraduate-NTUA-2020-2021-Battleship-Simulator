package application;

import java.util.HashSet;


import javafx.util.Pair;

public class Player {
	//Total number of Actions
	private int totalNumberOfActions;
	//Total score;
	private int score;
	//Success Rate
	private float successRate;
	//Carrier points in the grid
	private HashSet<Pair<Integer,Integer>> playerCarrier;
	//Carrier Size
	private int carrierSize;
	//Carrier State
	private String carrierState;
	//Battleship points in the grid
	private HashSet<Pair<Integer,Integer>> playerBattleship;
	//Battleship Size
	private int battleshipSize;
	//Battleship State
	private String battleshipState;
	//Cruiser points in the grid
	private HashSet<Pair<Integer,Integer>> playerCruiser;
	//Cruiser Size
	private int cruiserSize;
	//Cruiser State
	private String cruiserState;
	//Submarine points in the grid
	private HashSet<Pair<Integer,Integer>> playerSubmarine;
	//Submarine Size
	private int submarineSize;
	//Submarine State
	private String submarineState;
	//Destroyer points in the grid
	private HashSet<Pair<Integer,Integer>> playerDestroyer;
	//Destroyer State
	private int destroyerSize;
	//Destroyer State
	private String destroyerState;
	//Boolean showing if player has played in this round
	private boolean hasPlayed;
	//Number of successful shots
	private int successfulShots;
	//Type of ship which last hit
	private String type;
	//Total Active Ships
	private int totalActiveShips;
	
	//CONSTRUCTOR
		/**
		   * This method is the Constructor of an Object Type Player.
		   * These objects are used for simulating Player player and Player enemy and their attributes.
		   * The constructor doesn't have parameters but it sets some standard values to the attributes.
		   */
	public Player() {
		totalNumberOfActions = 0;
		score = 0;
		successRate = 0;
		successfulShots = 0;
		hasPlayed = false;
		playerCarrier = new HashSet<Pair<Integer,Integer>>();
		carrierSize = 0;
		carrierState = "Intact";
		playerBattleship = new HashSet<Pair<Integer,Integer>>();
		battleshipSize = 0;
		battleshipState = "Intact";
		playerCruiser = new HashSet<Pair<Integer,Integer>>();
		cruiserSize = 0;
		cruiserState = "Intact";
		playerSubmarine = new HashSet<Pair<Integer,Integer>>();
		submarineSize = 0;
		submarineState = "Intact";
		playerDestroyer = new HashSet<Pair<Integer,Integer>>();
		destroyerSize = 0;
		destroyerState = "Intact";
		type = "";
		totalActiveShips = 17;
	}
	//Functions
	//Getters
	/**
	   * Getter Function for returning the totalNumberOfActions.
	   * @return int Returns the total number of actions for this player.
	   */
	
	public int getTotalNumberOfActions() {		
		return totalNumberOfActions;
	}
	/**
	   * Getter Function for returning score.
	   * @return int Returns the total score for this player.
	   */
	
	public int getScore() {
		return score;
	}
	/**
	   * Getter Function for returning successRate.
	   * @return float Returns the success rate for this player.
	   */
	
	public float getSuccessRate() {
		return successRate;
	}
	/**
	   * Getter Function for returning hasPlayed .
	   * @return boolean Returns if a player has played in this round.
	   */
	public boolean getHasPlayed() {
		return hasPlayed;
	}
	/**
	   * Getter Function for returning playerCarrier.
	   * @return HaseSet<Pair<Integer,Integer>> Returns the points of Carrier in the grid.
	   */
	public HashSet<Pair<Integer,Integer>> getPlayerCarrier(){
		return playerCarrier;
	}
	/**
	   * Getter Function for returning playerBattleship.
	   * @return HaseSet<Pair<Integer,Integer>> Returns the points of Battleship in the grid.
	   */
	public HashSet<Pair<Integer,Integer>> getPlayerBattleship(){
		return playerBattleship;
	}
	public HashSet<Pair<Integer,Integer>> getPlayerCruiser(){
		return playerCruiser;
	}
	public HashSet<Pair<Integer,Integer>> getPlayerSubmarine(){
		return playerSubmarine;
	}
	public HashSet<Pair<Integer,Integer>> getPlayerDestroyer(){
		return playerDestroyer;
	}
	public int getCarrierSize() {
		return carrierSize;
	}
	public int getBattleshipSize() {
		return battleshipSize;
	}
	public int getCruiserSize() {
		return cruiserSize;
	}
	public int getSubmarineSize() {
		return submarineSize;
	}
	public int getDestroyerSize() {
		return destroyerSize;
	}
	public String getCarrierState() {
		return carrierState;
	}
	public String getBattleshipState() {
		return battleshipState;
	}
	public String getCruiserState() {
		return cruiserState;
	}
	public String getSubmarineState() {
		return submarineState;
	}
	public String getDestroyerState() {
		return destroyerState;
	}
	public int getSuccesfulShots() {
		return successfulShots;
	}
	public String getType() {
		return type;
	}
	public int getTotalActiveShips() {
		return totalActiveShips;
	}
	//Setters
		/**
		   * This Function is used to Set totalNumberOfActions.
		   * @param acts The number of actions of a player during the game.
		   */
	public void setTotalNumberOfActions(int acts) {
		totalNumberOfActions = acts;
	}
	
	/**
	   * This Function is used to Set score.
	   * @param sc The score of a player up to this round.
	   */
	public void setScore(int sc) {
		score = sc;
	}
	
	public void setSuccessRate(float sr) {
		successRate = sr;
	}
	public void setHasPlayed(boolean pl) {
		hasPlayed = pl;
	}
	
	public void setPlayerCarrier(HashSet<Pair<Integer,Integer>> set) {
		playerCarrier = set;
	}
	public void setPlayerBattleship(HashSet<Pair<Integer,Integer>> set) {
		playerBattleship = set;
	}
	public void setPlayerCruiser(HashSet<Pair<Integer,Integer>> set) {
		playerCruiser = set;
	}
	public void setPlayerSubmarine(HashSet<Pair<Integer,Integer>> set) {
		playerSubmarine = set;
	}
	public void setPlayerDestroyer(HashSet<Pair<Integer,Integer>> set) {
		playerDestroyer = set;
	}
	public void setCarrierSize(int size) {
		carrierSize = size;
	}
	public void setBattleshipSize(int size) {
		battleshipSize = size;
	}
	public void setCruiserSize(int size) {
		cruiserSize = size;
	}
	public void setSubmarineSize(int size) {
		submarineSize = size;
	}
	public void setDestroyerSize(int size) {
		destroyerSize = size;
	}
	public void setCarrierState(String carS) {
		carrierState = carS;
	}
	public void setBattleshipState(String btlS) {
		battleshipState = btlS;
	}
	public void setCruiserState(String crsS) {
		cruiserState = crsS;
	}
	public void setSubmarineState(String subS) {
		submarineState = subS;
	}
	public void setDestroyerState(String dsrS) {
		destroyerState = dsrS;
	}
	public void setSuccessfulShots(int shot) {
		successfulShots = shot;
	}
	public void setType(String tp) {
		type = tp;
	}
	public void setTotalActiveShips(int sh) {
		totalActiveShips = sh;
	}
	/**
	   * This method checks whether a ship of a player 
	   * was attacked
	   * @param p This is the point that we check if a ship was attacked.
	   * @return boolean If there is a ship in the grid on this point return True, else returns False.
	   */
	public boolean shipAttacked(Pair<Integer,Integer> p) {
		if(playerCarrier.contains(p)) {
			playerCarrier.remove(p);
			type = "Carrier";
			return true;
		}
		if(playerBattleship.contains(p)) {
			playerBattleship.remove(p);
			type = "Battleship";
			return true;
		}
		if(playerCruiser.contains(p)) {
			playerCruiser.remove(p);
			type = "Cruiser";
			return true;
		}
		
		if(playerSubmarine.contains(p)) {
			playerSubmarine.remove(p);
			type = "Submarine";
			return true;
		}
		if(playerDestroyer.contains(p)) {
			playerDestroyer.remove(p);
			type = "Destroyer";
			return true;
		}
		return false;
	}
	/**
	   * This method returns the point an enemy gets from hitting one of 
	   * player's boats.
	   * @return int It returns zero if none of the boats was hit. Else it returns points according to the occasion
	   */
	public int enemyPoints() {
		
		if(playerCarrier.size()<carrierSize) {
			if(playerCarrier.size()==0) {
				carrierSize = 0;
				carrierState = "Sunk";
				System.out.println("Carrier was sunk");
				return (350+1000);
			}
			else {
				carrierSize--;
				carrierState = "Chipped";
				return 350;
			}
		}
		if(playerBattleship.size()<battleshipSize) {
			if(playerBattleship.size()==0) {
				battleshipSize = 0;
				battleshipState = "Sunk";
				System.out.println("Battleship was sunk");
				return (250+500);
			}
			else {
				battleshipSize--;
				battleshipState = "Chipped";
				return 250;
			}
		}
		if(playerCruiser.size()<cruiserSize) {
			if(playerCruiser.size()==0) {
				cruiserSize = 0;
				cruiserState = "Sunk";
				System.out.println("Cruiser was sunk");
				return (100+250);
			}
			else {
				cruiserSize--;
				cruiserState = "Chipped";
				return 100;
			}
		}
		if(playerSubmarine.size()<submarineSize) {
			if(playerSubmarine.size()==0) {
				submarineSize = 0;
				submarineState = "Sunk";
				System.out.println("Submarine was sunk");
				return (100+0);
			}
			else {
				submarineSize--;
				submarineState = "Chipped";
				return 100;
			}
		}
		if(playerDestroyer.size()<destroyerSize) {
			if(playerDestroyer.size()==0) {
				destroyerSize = 0;
				destroyerState = "Sunk";
				System.out.println("Destroyer was sunk");
				return (50+0);
			}
			else {
				destroyerSize--;
				destroyerState = "Chipped";
				return 50;
			}
		}
		return 0;
	}
	
	
}
