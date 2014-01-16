package othelloPkg;


public class AI  
{
	private static int w_board[][] ={   {0,  0,   0,  0, 0, 0,  0,   0,  0},
			                     {0, 50,  -1,  5, 2, 2,  5,  -1, 50},
			                     {0, -1, -10,  1, 1, 1,  1, -10, -1},
				                 {0,  5,   1,  1, 1, 1,  1,   1,  5}, 
				                 {0,  2,   1,  1, 0, 0,  1,   1,  2},
				                 {0,  2,   1,  1, 0, 0,  1,   1,  2},
				                 {0,  5,   1,  1, 1, 1,  1,   1,  5},
				                 {0, -1, -10,  1, 1, 1,  1, -10, -1},
				                 {0, 50,  -1,  5, 2, 2,  5,  -1, 50}
		   };
	
	/**
	 * This function finds the best move for the AI given the board.
	 * It finds the best move by using the minimax heuristic.
	 * 
	 */
	public static Move computeMove(Board b)
	{Move aiMove = null;
	 Board tempBoard = new Board();
	 PossibleMove ai[],human[];
	 Move tempMove;
	 int player = b.GetWhoseTurn(); 
	 int opponent=Score.GetOpponent(player);
	 int humanMax,aiMax,minmax,pos;
	 int count=0;
	 aiMax=-200;
	  
	 ai= new PossibleMove[64];
	 human=new PossibleMove[64];
	 
	 ai=computePossibleMoves(b,player);
	 // Traverse AI possible move array
	 while (ai[count]!=null){
		 tempMove= new Move(ai[count].GetX(),ai[count].GetY(),player);
		// Creates a temporary board
		    for (int i=0; i<10; i++){
		     System.arraycopy(b.state[i], 0, tempBoard.state[i], 0, 10);
		    }
		// Makes each possible move on temporary board
		    tempBoard.makeMove(tempMove);
		// Evaluates Human possible move and finds the max heuristic value
		    human=computePossibleMoves(tempBoard,opponent);
		    humanMax=getMax(human);
		    
		// Finds max value by evaluating the heuristic value of tempMove and subtracting the humanMax    
		    minmax=ai[count].GetHeuristicValue()-humanMax;
		   // System.out.println("POSSIBLE COMP MOVE: X="+ai[count].GetX()+",Y="+ai[count].GetY()+" HVALUE="+ minmax);
		    if (minmax>aiMax){
		    	aiMax=minmax;
		    	pos=count;
		    	aiMove = new Move(ai[pos].GetX(),ai[pos].GetY(),player);	
		    }
		    count++;    
	 }
		return aiMove;
	}
	
		/**
		 * This function returns an array of possible moves given the board and the player who is to make a move
		 * 
		 */
		public static PossibleMove[] computePossibleMoves(Board b,int player){
		
		int count = 0, flips = 0;
		int weight, hValue;
		
		PossibleMove p[];
		p = new PossibleMove[64];
		 
		//traverse board for possible moves
		for (int i = 1; i < 9; i++)
		{
			for (int j = 1; j < 9; j++)
			{
				if (b.MoveIsLegal(new Move(i,j,player)))
				{
					
					flips = b.getFlips(i,j, player);
					weight = getWeight(i,j);
					hValue = flips + weight; //calculate the Heuristic Value of each possible move
					p[count] = new PossibleMove(i,j,hValue);
					count++;	
					
				}
			}
		}
		return p;}
	
	/**
	 * Finds the max heuristic value of a given PossibleMove array
	 * 
	 */
	public static int getMax(PossibleMove[] p)
	{	int max=-100;
		int i=0;
		while (p[i]!=null){
			if (p[i].GetHeuristicValue()>max){
				max=p[i].GetHeuristicValue();		
			}
			i++;
		}
		return max;
	}
	
	/**
	 * Returns the weight at a given location of the board
	 *
	 */
	public static int getWeight(int i, int j)
	{
		int weight;
		weight = w_board[i][j];
		return weight;
	}

	/**
	 * Set the weighted board
	 */
	public void setW_board(int w_board[][]) {
		this.w_board = w_board;
	}

	/**
	 * Return the weighted board
	 */
	public int[][] getW_board() {
		return w_board;
	}
	 
}
