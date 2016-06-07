// http://community.topcoder.com/stat?c=problem_statement&pm=1592&rd=4482
import java.util.ArrayList;

public class ChessMetric {
  public long howMany(int size, int[] start, int[] end, int numMoves) {
    long[][][] dp = new long[size][size][numMoves + 1];
    dp[start[0]][start[1]][0] = 1;
    for (int k = 1; k <= numMoves; ++k) {
      for (int i = 0; i < size; ++i) {
        for (int j = 0; j < size; ++j) {
          ArrayList<int[]> nextPositions = getPossibleMoves(i, j, size);
          for (int[] pos : nextPositions) {
            dp[pos[0]][pos[1]][k] += dp[i][j][k - 1];
          }
        }
      }
    }
    return dp[end[0]][end[1]][numMoves];
  }

  ArrayList<int[]> getPossibleMoves(int x, int y, int n) {
    ArrayList<int[]> result = new ArrayList<int[]>();
    tryMove(x - 1, y, n, result);
    tryMove(x + 1, y, n, result);
    tryMove(x, y - 1, n, result);
    tryMove(x, y + 1, n, result);
    tryMove(x - 1, y - 1, n, result);
    tryMove(x - 1, y + 1, n, result);
    tryMove(x + 1, y - 1, n, result);
    tryMove(x + 1, y + 1, n, result);
    tryMove(x - 2, y - 1, n, result);
    tryMove(x - 2, y + 1, n, result);
    tryMove(x - 1, y - 2, n, result);
    tryMove(x - 1, y + 2, n, result);
    tryMove(x + 1, y - 2, n, result);
    tryMove(x + 1, y + 2, n, result);
    tryMove(x + 2, y - 1, n, result);
    tryMove(x + 2, y + 1, n, result);
    return result;
  }

  void tryMove(int x, int y, int n, ArrayList<int[]> moves) {
    if (x >= 0 && x < n && y >= 0 && y < n) {
      int[] move = new int[]{x, y};
      moves.add(move);
    }
  }
}
