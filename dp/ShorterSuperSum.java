// http://community.topcoder.com/stat?c=problem_statement&pm=10240
public class ShorterSuperSum {
  public int calculate(int k, int n) {
    // dp[i][j] means SuperSum(i, j).
    int[][] dp = new int[k + 1][n + 1];
    for (int j = 1; j <= n; ++j) {
      dp[0][j] = j;
    }
    for (int i = 1; i <= k; ++i) {
      dp[i][1] = dp[i - 1][1];
      for (int j = 2; j <= n; ++j) {
        dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
      }
    }
    return dp[k][n];
  }
}
