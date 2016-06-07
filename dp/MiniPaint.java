// http://community.topcoder.com/tc?module=ProblemDetail&rd=4710&pm=1996
public class MiniPaint {
  public int leastBad(String[] picture, int maxStrokes) {
    // 1. dp per row, the mispainted for each stroke num.
    int m = picture.length;
    int n = picture[0].length();
    // painted[i][j] means for row i, having j strokes, painted at most painted[i][j] points.
    int[][] painted = new int[m][n + 1];
    for (int row = 0; row < m; ++row) {
      String s = picture[row];
      // dp[i][j][k] means for col 0-i, using strokes num j, at color k (0 black, 1 white),
      // the max correct paint count.
      int[][][] dp = new int[n][n + 1][2];
      if (s.charAt(0) == 'B') {
        for (int j = 1; j <= n; ++j) {
          dp[0][j][0] = 1;
        }
      } else {
        for (int j = 1; j <= n; ++j) {
          dp[0][j][1] = 1;
        }
      }
      for (int i = 1; i < n; ++i) {
        for (int j = 1; j <= n; ++j) {
          if (s.charAt(i) == 'B') {
            dp[i][j][0] = Math.max(dp[i - 1][j][0] + 1, dp[i - 1][j - 1][1] + 1);
            dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0]);
          } else {
            dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j - 1][1]);
            dp[i][j][1] = Math.max(dp[i - 1][j][1] + 1, dp[i - 1][j - 1][0] + 1);
          }
        }
      }
      painted[row][0] = 0;
      for (int j = 1; j <= n; ++j) {
        painted[row][j] = Math.max(dp[n - 1][j][0], dp[n - 1][j][1]);
      }
    }

    // above[i][j] means for row 0-i, with stroke num j, the max painted count.
    int[][] above = new int[m][maxStrokes + 1];
    for (int j = 0; j <= maxStrokes; ++j) {
      if (j <= n) {
        above[0][j] = painted[0][j];
      } else {
        above[0][j] = n;
      }
    }
    for (int i = 1; i < m; ++i) {
      for (int j = 0; j <= maxStrokes; ++j) {
        int maxPainted = 0;
        for (int j1 = 0; j1 <= j; ++j1) {
          int temp = above[i - 1][j1];
          if (j - j1 <= n) {
            temp += painted[i][j - j1];
          } else {
            temp += n;
          }
          maxPainted = Math.max(maxPainted, temp);
        }
        above[i][j] = maxPainted;
      }
    }
    return m * n - above[m - 1][maxStrokes];
  }
}
