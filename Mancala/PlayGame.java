package Mancala;
import java.util.*;

public class PlayGame {

	public static void main (String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Who are the players?");
		
		System.out.print("First Player: ");
		String player1 = input.nextLine();
		
		System.out.print("Second Player: ");
		String player2 = input.nextLine();
		
		
		MancalaGame myGame = new MancalaGame(player1, player2);
		myGame.playGame();
	}

}
