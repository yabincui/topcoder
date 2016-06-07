public class TheTicketsDivTwo {
  public double find(int n, int m, int K) {
    // dp[i][j][k] is the probability of selection in the j position in a i length queue, with k
    // last choice.
    double[][][] dp = new double[n+1][n+1][K+1];
    for (int i = 1; i <= n; ++i) {
      dp[i][1][0] = 1.0;
      for (int j = 2; j <= i; ++j) {
        dp[i][j][0] = 0.0;
      }
    }
    for (int k = 1; k <= K; ++k) {
      dp[1][1][k] = 1.0;
      for (int i = 2; i <= n; ++i) {
        dp[i][1][k] = 1.0 / 6 + 0.5 * dp[i][i][k-1];
        for (int j = 2; j <= i; ++j) {
          dp[i][j][k] = 0.5 * dp[i][j-1][k-1] + 1.0 / 3 * dp[i-1][j-1][k-1];
        }
      }
    }
    return dp[n][m][K];
  }
}
