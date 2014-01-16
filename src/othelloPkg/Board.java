package othelloPkg;

import othelloPkg.Move;
import othelloPkg.Score;

public class Board {
public int state[][];
public Score g_score;
public Move last_move;
public Board(){
	state = new int [10][10];
	for (int i=0;i<10;i++)
		for (int j=0;j<10;j++){
			state[i][j]=Score.NOBODY;
		}	
	state[4][4]=Score.WHITE;
	state[4][5]=Score.BLACK;
	state[5][4]=Score.BLACK;
	state[5][5]=Score.WHITE;
	
	g_score = new Score();
 }

/*
 * Makes Move m into the board
 * */
public void makeMove(Move m){


    int player = m.GetPlayer();
    int opponent = Score.GetOpponent(player);

    state[m.GetX()][m.GetY()] = player;
    g_score.ScoreAdd(player, 1);

    // Checks the 8 Different directions and Flips the color where neccessary 
    for (int xinc=-1; xinc<=1; xinc++)
    for (int yinc=-1; yinc<=1; yinc++)
    if (xinc != 0 || yinc != 0)
    {
      int x, y;

      // Finds neighbouring opponent piece and traverse the direction until a player piece is found
      for (x = m.GetX()+xinc, y = m.GetY()+yinc; state[x][y] == opponent;
	   x += xinc, y += yinc)
	;

      // Flips the color
      if (state[x][y] == player){
		for (x -= xinc, y -= yinc; x != m.GetX() || y != m.GetY();
		     x -= xinc, y -= yinc)
		{
		  state[x][y] = player;
		  g_score.ScoreAdd(player, 1);
		  g_score.ScoreSubtract(opponent, 1);
		}
	  }	
    }

    last_move = new Move(m);
  }

/**
 * Get the current player's turn
 */
public int GetWhoseTurn()
{
	if(GetLastMove() == null)
		return Score.BLACK;
	
	int player = GetLastMove().GetPlayer();
	int opponent = player%2+1;
	
	if (MoveIsPossible(opponent)) return opponent;
	if (MoveIsPossible(player)) return player;
	
	return Score.NOBODY;
	
}


/**
 * GetSquare returns the occupant of the location
 */

public int GetSquare(int x, int y){return state[x][y];}

/**
 * GetScore returns the score for player
 */
public int GetScore(int player){return g_score.GetScore(player);}

/**
 * GetLastMove returns last move
 */
public Move GetLastMove() { return last_move; }

/**
* Checks if a move is legal.
*/

public boolean MoveIsLegal(Move m)
{
 if (state[m.GetX()][m.GetY()] != Score.NOBODY) return false;

 int player = m.GetPlayer();
 int opponent = Score.GetOpponent(player);

 for (int xinc=-1; xinc<=1; xinc++)
 for (int yinc=-1; yinc<=1; yinc++)
 if (xinc != 0 || yinc != 0)
 {
   int x, y;

   for (x = m.GetX()+xinc, y = m.GetY()+yinc; state[x][y] == opponent;
	   x += xinc, y += yinc)
	;

   if (state[x][y] == player &&
	  (x - xinc != m.GetX() || y - yinc != m.GetY()))
     return true;
 }

 return false;
}


/**
 * Checks if there is a legal move for player.
 */

public boolean MoveIsPossible(int player)
{
  for (int i=1; i<9; i++)
  for (int j=1; j<9; j++)
    if (MoveIsLegal(new Move(i, j, player))) return true;

  return false;
}

/**
 * Count flips of a possible move
 */
public int getFlips(int i, int j, int player){
	int flips=0;
	
	 int opponent = Score.GetOpponent(player);

	    
	    for (int xinc=-1; xinc<=1; xinc++)
	    for (int yinc=-1; yinc<=1; yinc++)
	    if (xinc != 0 || yinc != 0)
	    {
	      int x, y;

	      //what does this do?
	      for (x = i+xinc, y = j+yinc; state[x][y] == opponent;
		   x += xinc, y += yinc)
		;

	      
	      if (state[x][y] == player){
			for (x -= xinc, y -= yinc; x != i || y != j;
			     x -= xinc, y -= yinc)
			{
			  flips+=1;
			}
		  }	
	    }
	return flips;
}

/**
 * Display board
 */
public  void displayBoard(String message){
	//This should clear the screen
	System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	//int [][] b.state = new int[8][8];
	/*
	b.state[3][3] = 1;
	b.state[3][4] = 2;
	b.state[4][3] = 2;
	b.state[4][4] = 1;*/
	
	String rows = "";
	String cols = "  --- --- --- --- --- --- --- ---";
	String top = "   1   2   3   4   5   6   7   8   --> Column";
	
	String output = "";
	
	System.out.println(top + "\n" + cols);
	
		
		for ( int y = 1; y < 9; y++){
			output = "";
			for ( int x = 1; x < 9; x++){
			if (state[x][y] == 0){
				output += "|   ";
			}
			else if (state[x][y] == 1){
				output += "| o ";
			}
			else{
				output += "| x ";
			}
			
		}
		output += "|";
		System.out.println((y)+output);
		System.out.print(cols);
		if(y==2){
			//output black's score
			System.out.print("\t\tBlack:"+g_score.GetScore(2));
		}
		else if(y==3){
			//output white's score
			System.out.print("\t\tWhite:"+g_score.GetScore(1));
		}
		else if(y==4){
			System.out.print("\t\tStatus:"+message);
		}
		else if (y==5){
			if (last_move!=null)
			System.out.print("\t\tStatus: X:"+last_move.GetX()+" Y:"+last_move.GetY());
		}
		//creating a new line after the cols(or score output)
		System.out.println("");
		

	} 
		System.out.println("|");
		System.out.println("|");
		System.out.println("V");
		System.out.println("Row");
}
}

