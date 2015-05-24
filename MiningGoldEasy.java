public class MiningGoldEasy {
  public int GetMaximumGold(int N, int M, int[] event_i, int[] event_j) {
    int n = event_i.length;
    // dp[k][i][j] means at pos (event_i[i], event_j[j]), the max earn after event i - 1.
    int[][][] dp = new int[n + 1][n][n];
    for (int k = 1; k <= n; ++k) {
      for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
          int currentEarn = N + M - Math.abs(event_i[i] - event_i[k - 1]) -
              Math.abs(event_j[j] - event_j[k - 1]);
          int previousMax = 0;
          for (int t = 0; t < n; ++t) {
            previousMax = Math.max(previousMax, dp[k - 1][t][j]);
            previousMax = Math.max(previousMax, dp[k - 1][i][t]);
          }
          dp[k][i][j] = currentEarn + previousMax;
        }
      }
    }
    int maxEarn = 0;
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < n; ++j) {
        maxEarn = Math.max(maxEarn, dp[n][i][j]);
      }
    }
    return maxEarn;
  }
}
