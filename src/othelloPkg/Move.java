package othelloPkg;

public class Move
{
  private int x;
  private int y;
  private int player;

  public Move(Move m) { x = m.x; y = m.y; player = m.player; }
  public Move(int x, int y, int player) { this.x = x; this.y = y; this.player =player; }

  public int GetX() { return this.x; }
  public int GetY() { return this.y; }
  public int GetPlayer() { return this.player; }
}
