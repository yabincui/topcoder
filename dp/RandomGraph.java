public class RandomGraph {
  public double probability(int n, int p) {
    double pTrue = p / 1000.0;
    double pFalse = (1000 - p) / 1000.0;
    // dp[i][j][k] means having i one connected items, j two connected items, k three connected items.
    double[][][] dp = new double[n + 1][n + 1][n + 1];
    dp[1][0][0] = 1.0;
    for (int vertex = 1; vertex < n; ++vertex) {
      for (int i = 0; i <= vertex; ++i) {
        for (int j = 0; j <= (vertex - i) / 2; ++j) {
          int k = (vertex - i - 2 * j) / 3;
          if (i + 2 * j + 3 * k != vertex) {
            continue;
          }
          dp[i + 1][j][k] += dp[i][j][k] * Math.pow(pFalse, vertex);
          if (i > 0) {
            dp[i - 1][j + 1][k] += dp[i][j][k] * C(i, 1) * pTrue * Math.pow(pFalse, vertex - 1);
            if (i > 1) {
              dp[i - 2][j][k + 1] += dp[i][j][k] * C(i, 2) * pTrue * pTrue * Math.pow(pFalse, vertex - 2);
            }
          }
          if (j > 0) {
            dp[i][j - 1][k + 1] += dp[i][j][k] * (C(j * 2, 1) * pTrue * Math.pow(pFalse, vertex - 1) +
                C(j, 1) * pTrue * pTrue * Math.pow(pFalse, vertex - 2));
          }
        }
      }
    }

    double sum = 0.0;
    for (int i = 0; i <= n; ++i) {
      for (int j = 0; j <= (n - i) / 2; ++j) {
        int k = (n - i - 2 * j) / 3;
        if (i + 2 * j + 3 * k != n) {
          continue;
        }
        sum += dp[i][j][k];
      }
    }
    return 1.0 - sum;
  }

  int C(int n, int k) {
    if (k == 1) {
      return n;
    }
    if (k == 2) {
      return n * (n - 1) / 2;
    }
    return -1;
  }
}
