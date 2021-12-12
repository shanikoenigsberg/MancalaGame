package Mancala;
import java.util.*;

public class MancalaGame {
	private Player player1;
	private Player player2;
	
	private Scanner input;
	
	public MancalaGame(String name1, String name2) {
		 this.input = new Scanner(System.in);
		
		//sets up the pits for player1 -- gets indexes 0-5
		 //makes an array for the player object
		Pit[] pits1 = new Pit[6];
		for(int i=0; i<6; i++) {
			pits1[i] = new Pit(i);
		}
		//set up the store for player1
		Mancala storeLeft = new Mancala(name1);
		
		//INSTANTIATES THE PLAYER1 OBJECT
		this.player1 = new Player(name1, pits1, storeLeft);
		
		
		//sets up the pits for player2 -- gets indexes 6-11
		Pit[] pits2 = new Pit[6];
		for(int i=0; i<6; i++) {
			pits2[i] = new Pit(i);
		}
		//set up the store for player2
		Mancala storeRight = new Mancala(name1);
		
		//INSTANTIATES THE PLAYER2 OBJECT
		this.player2 = new Player(name2, pits2, storeRight);
		
		//INSTANTIATES THE BOARD STRING
		System.out.println("Welcome to Mancala. The first player gets indexes 1-6 and the second player gets 7-12.");
		System.out.println("The board will first diplay the index of each pit. The 0's represent the Mancala stores. Remember that each one starts off with 4 marbles.");
		System.out.println("   1  2  3  4  5  6");
		System.out.println("0                   0");
		System.out.println("   7  8  9 10 11 12 ");
		
		
		
	}
	
	
	public void player1Turn() {
		int pitNo, numMarbles, counter;
		boolean myPits = true;
		
		
		System.out.println();
		System.out.print(player1.getName() + ", which pit from your side would you like to start with? (1-6) ");
		pitNo = input.nextInt();
		while(pitNo < 1 || pitNo > 6) {
			System.out.println("You can only pick numbers 1-6. Try again. ");
			System.out.print(player1.getName() + ", which pit from your side would you like to start with? (1-6) ");
			pitNo = input.nextInt();
		}
		//the INDEX of the pit number that was chosen
		pitNo = pitNo - 1;
		//keeps track of what pit the player is in so subtracts because going counterclockwise
		counter = pitNo-1;
		//number of marbles in the pit
		numMarbles = player1.getPits()[pitNo].getMarbles();
		//empty the pit
		player1.getPits()[pitNo].emptyMarbles();
		//as long as the player is still holding marbles - this value is kept track by the number of marbles 
		while(numMarbles != 0) {
			//if the player is in holes on his side
			if(counter >= 0 && myPits == true) {
				player1.getPits()[counter].addMarbles();
				//drops one as goes along
				numMarbles--;
				//break before decrementing to keep the value the same
				if(numMarbles == 0) {
					break;
				}
				else {
				//decrement counter to go opposite way
				counter--;
				}
	
			}
			
			else if(counter == -1 && myPits == true) {
				//if counter is -1 that means the player1 is in their own store
				player1.getStore().addToStore(1);
				//drops marbles as goes along
				numMarbles--;
				//if its the end leave the while loop now because don't want counter to get re-initialized
				if(numMarbles == 0) {
					break;
				}
				else {
				//if counter is less than -1, that means they have entered player2's pits so start with that array
				 counter = 0;
				 myPits = false;
				}
			}
			
			//in other player's pits
			else if(counter >= 0 && myPits == false) {
				//add to opposite players pits
				player2.getPits()[counter].addMarbles();
				//drop marbles as go along
				numMarbles--;
				//don't increment counter if this is the last hole
				if(numMarbles == 0) {
					break;
				}
				else {
				counter++;
				}
				
				//if player gets past all pits on other player's side and skips other players store
				if(counter == 6) {
					counter = 5;
					myPits = true;
				}
			}
			
			
		}
		
		//After round:
		displayBoard();
		
			//if landed in own store
			if(counter == -1) {
				System.out.println("Extra turn because you landed in your mancala!");
				player1Turn();
			}
			//only test this condition if counter is greater than or equal to zero and in own pits
			else if(counter >= 0 && myPits == true) {
				//if in pits and last hole went to only has one- meaning the last marble dropped in was dropped into empty hole
				if(player1.getPits()[counter].getMarbles() == 1 && myPits == true) {
					System.out.println("Yay! You can steal extra pebbles because you landed in an empty hole.");
					//remove that one from the hole that was empty
					player1.getPits()[counter].emptyMarbles();
					//and put it into the store
					player1.getStore().addToStore(1);
					//add the marbles of the pit across into my store
					player1.getStore().addToStore(player2.getPits()[counter].getMarbles());
					//and empty that hole
					player2.getPits()[counter].emptyMarbles();
					displayBoard();
				}
			}
		
			//reset myPits to true for next time- always start in player's own pits
			myPits = true;
			
		
		
		
	}
	
	public void player2Turn() {
		int pitNo, numMarbles, counter;
		boolean myPits = true;
		
		System.out.println();
		System.out.print(player2.getName() + ", which pit from your side would you like to start with? (7-12) ");
		pitNo = input.nextInt();
		while(pitNo < 7 || pitNo > 12) {
			System.out.println("You can only pick numbers 7-12. Try again. ");
			System.out.print(player2.getName() + ", which pit from your side would you like to start with? (7-12) ");
			pitNo = input.nextInt();
		}
		//the INDEX of the pit number that was chosen
		pitNo = pitNo - 7;					//1
		//keeps track of what pit the player is in
		counter = pitNo+1;								//2
		//number of marbles in the chosen pit
		numMarbles = player2.getPits()[pitNo].getMarbles();
		//empty pit
		player2.getPits()[pitNo].emptyMarbles();
		//as long as the player is still holding marbles - this value is kept track by the number of marbles 
		while(numMarbles != 0) {
			//if the player is in holes on his side
			if(counter >= 0 && counter < 6 && myPits == true) {
				//adds to next pile going in counterclockwise fashion
				player2.getPits()[counter].addMarbles();
				//drops marbles
				numMarbles--;
				//break before decrementing to keep value of counter the same
				if(numMarbles == 0) {
					break;
				}
				else {
					//increment counter to go to the next pit
					counter++;
				}	
						
			}
					
			else if(counter == 6) {
				//if counter is 6 that means the player2 is in their own store
				player2.getStore().addToStore(1);
				//drops marbles
				numMarbles--;
				//if player is now done and is at his store, don't increment just break out of while loop
				if (numMarbles == 0) {
					break;
				}
				else {
					//starts at end of other player's pits
					counter = 5;
					myPits = false;
				}
			}
			else if(counter >= 0 && myPits == false) {
				//add to hole that are up to
				player1.getPits()[counter].addMarbles();
				//drops marbles
				numMarbles--;
				//only decrement if still more in pit
				if(numMarbles == 0) {
					break;
				}
				else {
					counter--;
				}
				//if player gets past all pits on other player's side and skips other players store
				if(counter == -1) {
					//starts at beginning of own pits
					counter = 0;
					myPits = true;
				}
			}
		}
				
		//After round:
		displayBoard();	
				
		//if landed in store
		if(counter == 6) {
			System.out.println("Extra turn because you landed in your mancala!");
			player2Turn();
		}
		//only test this condition if in own pits
		else if(counter >= 0 && counter < 6 && myPits == true) {
			//if in pits and last hole went to only has one- meaning the last marble dropped in was dropped into empty hole
			if(player2.getPits()[counter].getMarbles() == 1 && myPits == true) {
				System.out.println("Yay! You can steal extra pebbles because you landed in an empty hole.");
				//remove that one from the hole that was empty
				player2.getPits()[counter].emptyMarbles();
				//and put it into the store
				player2.getStore().addToStore(1);
				//add the marbles of the pit across into my store
				player2.getStore().addToStore(player1.getPits()[counter].getMarbles());
				//and empty that hole
				player1.getPits()[counter].emptyMarbles();
				displayBoard();
			}
		}
		
		//reset myPits to true for next time- always start in player's own pits
		myPits = true;
		

				
	}
	
	public void displayBoard() {
		System.out.println();
		System.out.println("The Mancala Board:");
		
		System.out.print("   ");
		
		for(int i=0; i<6; i++) {
			System.out.print(player1.getPits()[i].getMarbles() + " ");
		}
		
		System.out.println();
		System.out.println(player1.getStoreCount() + "                  " + player2.getStoreCount());
		
		System.out.print("   ");
		
		for(int i=0; i<6; i++) {
			System.out.print(player2.getPits()[i].getMarbles() + " ");
		}
		
		System.out.println();
		
	}
	
	public void playGame() {
		
		do {
			player1Turn();
			player2Turn();
			

			
		} while(player1.getSumPits() != 0 && player2.getSumPits() != 0);
		
		//if its end of game
		if(player1.getSumPits() == 0 || player2.getSumPits() == 0) {
			endGame();
			
		}
		
	}
	
	public void endGame() {
		//loop through and see if get sum of player1's pits
		for(int i=0; i<6; i++) {
			if(player1.getPits()[i].getMarbles() != 0) {
				//add pit's marbles to player's store
				player1.getStore().addToStore(player1.getPits()[i].getMarbles());
				//empty marbles from that pit
				player1.getPits()[i].emptyMarbles();
			}
		}
		//loop through and see get sum of player2's pits
		for(int i=0; i<6; i++) {
			if(player1.getPits()[i].getMarbles() != 0) {
				//add pit's marbles to player's store
				player1.getStore().addToStore(player1.getPits()[i].getMarbles());
				//empty marbles from that pit
				player1.getPits()[i].emptyMarbles();
			}
		}
		
		if(player1.getStoreCount() > player2.getStoreCount()) {
			System.out.println(player1.getName() + " is the winner.");
			System.out.println("Great game!! Re-start to play again!");
		}
		else if(player2.getStoreCount() > player1.getStoreCount()){
			System.out.println(player2.getName() + " is the winner.");
			System.out.println("Great game!! Re-start to play again!");
		}
		else {
			System.out.println("It's a tie!");
			System.out.println("Great game!! Re-start to play again!");
		}
	}
	

	
}
