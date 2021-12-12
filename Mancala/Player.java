package Mancala;

public class Player {
	private String name;
	private Pit[] pits = new Pit[6];
	private Mancala store;
	
	
	public Player(String name, Pit[] pits, Mancala store) {
		this.name = name;
		this.pits = pits;
		this.store = store;
	}
	
	public String getName() {
		return name;
	}
	
	/*
	 * public void setPitCount(int index, int value) { if(value <= 4 && value > 0) {
	 * this.pitsCount[index] = value; } }
	 * 
	 * public int getPitCount(int j) { return pitsCount[j]; }
	 */
	
	public void setPits(Pit[] pits) {
		this.pits = pits;
	}
	
	public Pit[] getPits() {
		return pits;
	}
	
	public int getSumPits() {
		int total = 0;
		for(int i=0; i<6; i++) {
			total += getPits()[i].getMarbles();
		}
		return total;
	}
	
	public Mancala getStore() {
		return store;
	}
	
	public int getStoreCount() {
		return store.getStoreCount();
	}
	
	
	
	
}


