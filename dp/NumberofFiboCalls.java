public class NumberofFiboCalls {
  public int[] fiboCallsMade(int n) {
    int[][] dp = new int[n + 2][2];
    dp[0][0] = 1;
    dp[1][1] = 1;
    for (int i = 2; i <= n; ++i) {
      dp[i][0] = dp[i - 1][0] + dp[i - 2][0];
      dp[i][1] = dp[i - 1][1] + dp[i - 2][1];
    }
    return dp[n];
  }
}
