public class SpellCardsEasy {
  public int maxDamage(int[] level, int[] damage) {
    int n = level.length;
    int m = 50;
    // dp[i][j] means using i..n-1 items, previous can damage j items, the maximum count.
    int[][] dp = new int[n + 1][m + 1];
    for (int i = 0; i <= n; ++i) {
      for (int j = 0; j <= m; ++j) {
        dp[i][j] = -1;
      }
    }
    dp[n][0] = 0;
    for (int i = n - 1; i >= 0; --i) {
      // If we don't damage item i.
      dp[i][0] = dp[i + 1][0];
      for (int j = 1; j <= m; ++j) {
        dp[i][j] = dp[i + 1][j - 1];
      }
      // If we damage item i.
      for (int j = 0; j <= m; ++j) {
        int nj = j + level[i] - 1;
        if (nj <= m && dp[i + 1][nj] != -1) {
          int temp = dp[i + 1][nj] + damage[i];
          if (dp[i][j] == -1 || dp[i][j] < temp) {
            dp[i][j] = temp;
          }
        }
      }
    }
    return dp[0][0];
  }
}
