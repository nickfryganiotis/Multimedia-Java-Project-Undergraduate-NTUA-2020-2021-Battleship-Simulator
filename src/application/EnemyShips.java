package application;

public class EnemyShips {
	private String type;
	private String state;
	
	public EnemyShips(String type,String state) {
		this.type = type;
		this.state = state;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getType() {
		return type;
	}
	public String getState() {
		return state;
	}
	
	
	
}
