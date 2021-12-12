package Mancala;

public class Pit {
	private int pitIndex;
	private int marbles;
	
	public Pit(int pitIndex) {
		if(pitIndex >= 0 && pitIndex <=11) {
			this.pitIndex = pitIndex;
		}
		marbles = 4;
	}
	
	public void setMarbles(int marbles) {
		this.marbles = marbles;
	}
	
	public int getMarbles() {
		return marbles;
	}
	
	public void addMarbles() {
		marbles++;
	}
	
	public void loseMarbles() {
		marbles--;
	}
	
	public void emptyMarbles() {
		marbles = 0;
	}
}
