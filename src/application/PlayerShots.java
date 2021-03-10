package application;

public class PlayerShots {
	private int x;
	private int y;
	private String shot;
	private String shipType;
	
	public PlayerShots(int x,int y,String shot,String shipType) {
		this.x = x;
		this.y = y;
		this.shot = shot;
		this.shipType = shipType;		
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public String getShot() {
		return shot;
	}
	public String getShipType() {
		return shipType;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setShot(String shot) {
		this.shot = shot;
	}
	public void setShipType(String shipType) {
		this.shipType = shipType; 
	}
	
	
}

