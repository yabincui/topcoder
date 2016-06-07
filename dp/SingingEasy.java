public class SingingEasy {
  public int solve(int[] pitch) {
    int n = pitch.length;
    if (n == 1) {
      return 0;
    }
    // dp[i][j] means the first end is i - 1, the second end is j - 1, (i > j).
    int[][] dp = new int[n + 1][n + 1];
    for (int i = 2; i <= n; ++i) {
      dp[i][0] = dp[i - 1][0] + Math.abs(pitch[i - 1] - pitch[i - 2]);
      for (int j = 1; j < i - 1; ++j) {
        dp[i][j] = dp[i - 1][j] + Math.abs(pitch[i - 1] - pitch[i - 2]);
      }
      dp[i][i - 1] = dp[i - 1][0];
      for (int k = 1; k <= i - 2; ++k) {
        int temp = dp[i - 1][k] + Math.abs(pitch[i - 1] - pitch[k - 1]);
        dp[i][i - 1] = Math.min(dp[i][i - 1], temp);
      }
    }

    int minValue = Integer.MAX_VALUE;
    for (int i = n - 1; i > 0; --i) {
      minValue = Math.min(minValue, dp[n][i]);
    }
    return minValue;
  }
}
