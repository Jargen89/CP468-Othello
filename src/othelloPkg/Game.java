package othelloPkg;

import java.util.Scanner;


public class Game {
	  public static final int WHITE  = 1;
	  public static final int BLACK  = 2;
	  
	public static void main(String[]args){
		
		//Initialize all the variables
		//black is the first player
		int currentPlayer = BLACK;
		Board b = new Board();
		b.displayBoard("Initializing Board");
		Scanner s = new Scanner(System.in);
		
		
		//Game continues if currentPlayer has no moves but opponent does
		while(b.MoveIsPossible(currentPlayer) || b.MoveIsPossible(currentPlayer % 2 + 1)){
			if(!b.MoveIsPossible(currentPlayer)){
				//the current player does not have a move but the opponent does
				System.out.println("Player"+currentPlayer+" does not have a valid move, this player forfits his/her turn");
				if(currentPlayer==BLACK){
					currentPlayer=WHITE;
				}
				else{
					currentPlayer=BLACK;
				}
				continue;
			}
			if(currentPlayer==WHITE){
				//wait 4 seconds for the AI to make its move
				try 
				{
				System.out.println("The AI is evaluating his next move (Purposely Delaying for 2.5 seconds)");
				Thread.sleep(2500); // do nothing for 2500 miliseconds (2.5 second)
				} 
				catch(InterruptedException e)
				{
				//there was an error sleeping, continue, this should never be interrupted since this is not a multithreaded application
				}
				
				//White is the AI player
				
				Move move = AI.computeMove(b); 
				//make the move on the board
				b.makeMove(move);
			//	System.out.println("white has calculated its move");
				//redraw the board
				b.displayBoard("Currently Playing");
				//next players turn	
				currentPlayer=BLACK;				
			}
			else{
			
				System.out.println("\nPlease enter a move player"+currentPlayer+" in the form COLUMNROW(no spaces between the two numbers):");
				String coord = s.next();
				//check if it is a valid coordinate 
				if(ValidCoord(coord)){
					//create the move
					Move move = new Move(Integer.parseInt(coord.substring(0,1)), Integer.parseInt(coord.substring(1,2)), currentPlayer);
					
					//if valid than update score
					if(b.MoveIsLegal(move)){
						//make the move on the board
						b.makeMove(move);
						//redraw the board
						b.displayBoard("Currently Playing");
						//next players turn	
						if(currentPlayer==BLACK){
							currentPlayer=WHITE;
						}
						else{
							currentPlayer=BLACK;
						}
					}
					else{
						//do nothing the move is not legal and ask the player to input a valid move
						System.out.println("That move was NOT a legal move");
					}
					
				}
				else{
					//not valid than ask for another move
					System.out.println("That move was NOT a valid move");
					//same players turn
				}
			}	
			//Next turn
		}
			
		//White won
		if(b.GetScore(1)>b.GetScore(2)){
			b.displayBoard("White Won");
	//		System.out.println("The game has ended, White won.");
		}
		//Black won
		else if(b.GetScore(2)>b.GetScore(1)){
			b.displayBoard("Black Won");
		//	System.out.println("The game has ended, Black won.");
		}
		
		//print out who won
		s.close();
			
	}
	
		public static boolean ValidCoord(String s){
			
			//check to see if the string is an integer (i.e has two values that is a number)
			try{
				//If s is not an integer (i.e it contains a letter) than it is not a valid coordinate and it will throw a NumberFormatExceptiona2
				int number = Integer.parseInt(s);
				if(s.length()==2){
					if ((s.startsWith("1") || s.startsWith("2") ||
						s.startsWith("3") || s.startsWith("4") ||
						s.startsWith("5") || s.startsWith("6") ||
						s.startsWith("7") || s.startsWith("8")) &&
						(s.endsWith("1") || s.endsWith("2") ||
						s.endsWith("3") || s.endsWith("4") ||
						s.endsWith("5") || s.endsWith("6") ||
						s.endsWith("7") || s.endsWith("8"))){
							return true;
					}
				}
			}
			catch(NumberFormatException e){
				//This exception is thrown if s cannot be parsed as an Integer
				return false;
			}
			return false;
		}
}
