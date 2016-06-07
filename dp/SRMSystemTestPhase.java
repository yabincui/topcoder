public class SRMSystemTestPhase {
  final int MOD = 1000000007;
  public int countWays(String[] description) {
    int[][] C = getSelect(3);
    // dp[i][j][k] means how many situations to make current coder success i, challenged j, failed k.
    int[][][] dp = new int[4][4][4];
    int submit = getSubmit(description[0]);
    for (int i = 0; i <= submit; ++i) {
      for (int j = 0; j <= submit - i; ++j) {
        int k = submit - i - j;
        dp[i][j][k] = C[submit][i] * C[submit-i][j];
      }
    }
    for (int t = 1; t < description.length; ++t) {
      int[][][] nDp = new int[4][4][4];
      submit = getSubmit(description[t]);
      for (int i = 0; i <= submit; ++i) {
        for (int j = 0; j <= submit - i; ++j) {
          int k = submit - i - j;
          int types = C[submit][i] * C[submit-i][j];
          int prev = getPrevInLimit(dp, i, j, k);
          nDp[i][j][k] = (int)((long)types * prev % MOD);
        }
      }
      dp = nDp;
    }
    int result = 0;
    for (int i = 0; i < 4; ++i) {
      for (int j = 0; j < 4; ++j) {
        for (int k = 0; k < 4; ++k) {
          result = (result + dp[i][j][k]) % MOD;
        }
      }
    }
    return result;
  }

  int getPrevInLimit(int[][][] dp, int passed, int challenged, int failed) {
    int count = 0;
    for (int i = 0; i < 4; ++i) {
      for (int j = 0; j < 4; ++j) {
        for (int k = 0; k < 4; ++k) {
          if (i != passed) {
            if (i > passed) {
              count = (count + dp[i][j][k]) % MOD;
            }
            continue;
          }
          if (j != challenged) {
            if (j < challenged) {
              count = (count + dp[i][j][k]) % MOD;
            }
            continue;
          }
          count = (count + dp[i][j][k]) % MOD;
        }
      }
    }
    return count;
  }

  int[][] getSelect(int n) {
    int[][] C = new int[n+1][n+1];
    C[0][0] = 1;
    for (int i = 1; i <= n; ++i) {
      C[i][0] = C[i][i] = 1;
      for (int j = 1; j < i; ++j) {
        C[i][j] = C[i-1][j] + C[i-1][j-1];
      }
    }
    return C;
  }

  int getSubmit(String s) {
    int count = 0;
    for (int i = 0; i < s.length(); ++i) {
      if (s.charAt(i) == 'Y') {
        ++count;
      }
    }
    return count;
  }
}
