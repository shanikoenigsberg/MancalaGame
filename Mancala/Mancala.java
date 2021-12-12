package Mancala;

public class Mancala {
	private String playerName;
	private int storeCount;
	
	public Mancala(String playerName) {
		this.playerName = playerName;
		//storeCount always starts off at 0
		storeCount = 0;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public int getStoreCount() {
		return storeCount;
	}
	
	public void addToStore(int num) {
			storeCount += num;
	}
}
