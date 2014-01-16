package othelloPkg;

public class PossibleMove 
{
	private int x, y;
	private int heuristicValue;
	
	public PossibleMove(int x, int y,int heuristicValue) {this.x = x; this.y = y; this.heuristicValue = heuristicValue;}
	public PossibleMove(String s){	
	}
	  public int GetX() { return this.x; }
	  public int GetY() { return this.y; }
	  public int GetHeuristicValue() { return this.heuristicValue; }
}
