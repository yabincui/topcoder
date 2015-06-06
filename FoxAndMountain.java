public class FoxAndMountain {
  long MOD = 1000000009L;
  public int count(int n, String history) {
    int m = history.length();
    // dp[i][j][k] means the number of ways using first i steps, reaching j height, matching history to k.
    long[][][] dp = new long[n + 1][n + 1][m + 1];
    dp[0][0][0] = 1;
    for (int i = 1; i <= n; ++i) {
      for (int j = 0; j <= n; ++j) {
        for (int k = 0; k <= m; ++k) {
          // Step up.
          if (j < n) {
            int nj = j + 1;
            int nk = nextState(k, 'U', history);
            dp[i][nj][nk] = (dp[i][nj][nk] + dp[i - 1][j][k]) % MOD;
          }
          // Step down.
          if (j > 0) {
            int nj = j - 1;
            int nk = nextState(k, 'D', history);
            dp[i][nj][nk] = (dp[i][nj][nk] + dp[i - 1][j][k]) % MOD;
          }
        }
      }
    }
    return (int)dp[n][0][m];
  }

  int nextState(int k, char action, String history) {
    if (k == 0) {
      if (action == history.charAt(0)) {
        return 1;
      }
      return 0;
    }
    if (k == history.length()) {
      return k;
    }
    k--;
    int j = -1;
    for (j = k + 1; j >= 0; --j) {
      if (action != history.charAt(j)) {
        continue;
      }
      int i1 = k;
      int i2 = j - 1;
      if (i1 == i2) {
        break;
      }
      for (; i2 >= 0; --i1, --i2) {
        if (history.charAt(i1) != history.charAt(i2)) {
          break;
        }
      }
      if (i2 == -1) {
        break;
      }
    }
    return j + 1;
  }
}
