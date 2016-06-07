public class ChangingSounds {
  public int maxFinal(int[] changeIntervals, int beginLevel, int maxLevel) {
    int n = changeIntervals.length;
    boolean[][] dp = new boolean[n + 1][maxLevel + 1];

    dp[0][beginLevel] = true;
    for (int i = 1; i <= n; ++i) {
      int value = changeIntervals[i - 1];
      for (int j = 0; j <= maxLevel; ++j) {
        if (dp[i - 1][j]) {
          if (j - value >= 0) {
            dp[i][j - value] = true;
          }
          if (j + value <= maxLevel) {
            dp[i][j + value] = true;
          }
        }
      }
    }
    for (int i = maxLevel; i >= 0; --i) {
      if (dp[n][i]) {
        return i;
      }
    }
    return -1;
  }
}
