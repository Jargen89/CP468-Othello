package othelloPkg;

public class Score
{
  private int g_score[];

  public static final int NOBODY = 0;
  public static final int WHITE  = 1;
  public static final int BLACK  = 2;

  public Score() { g_score = new int[2];  g_score[0] = 2; g_score[1] = 2; }


  public Score(int whitescore, int blackscore)
  {
    g_score = new int[2];
    g_score[WHITE-1] = whitescore;
    g_score[BLACK-1] = blackscore;
  }


  public static int GetOpponent(int player) { return player % 2 + 1; }


  public void ScoreCopy(Score s)
  {
    g_score[0] = s.g_score[0];
    g_score[1] = s.g_score[1];
  }


  public int GetScore(int player) { return g_score[player-1]; }


  public void SetScore(int player, int score) { g_score[player-1] = score; }


  public final void ScoreAdd(int player, int amount)
  {
    g_score[player-1] += amount;
  }


  public final void ScoreSubtract(int player, int amount)
  {
    g_score[player-1] -= amount;
  }
}