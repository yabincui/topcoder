public class CoinReversing {
  public double expectedHeads(int N, int[] a) {
    double[][] C = buildSelect(N);
    int n = a.length;
    // dp[i][j] means after i coin reversing, the probability of having j coins heads up.
    double[][] dp = new double[n+1][N+1];
    dp[0][N] = 1.0;
    for (int i = 1; i <= n; ++i) {
      for (int j = 0; j <= N; ++j) {
        if (dp[i-1][j] == 0) {
          continue;
        }
        int minK = Math.max(0, a[i-1] - (N-j));
        int maxK = Math.min(a[i-1], j);
        for (int k = minK; k <= maxK; ++k) {
          int nj = j - k + a[i-1] - k;
          double prop = C[j][k] * C[N-j][a[i-1]-k] / C[N][a[i-1]];
          dp[i][nj] += dp[i-1][j] * prop;
        }
      }
    }
    double result = 0;
    for (int i = 0; i <= N; ++i) {
      result += i * dp[n][i];
    }
    return result;
  }

  double[][] buildSelect(int n) {
    double[][] C = new double[n+1][n+1];
    C[0][0] = 1;
    for (int i = 1; i <= n; ++i) {
      C[i][0] = C[i][i] = 1;
      for (int j = 1; j < i; ++j) {
        C[i][j] = C[i-1][j-1] + C[i-1][j];
      }
    }
    return C;
  }
}
