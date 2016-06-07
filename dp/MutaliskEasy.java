public class MutaliskEasy {
  public int minimalAttacks(int[] x) {
    int[] s = new int[3];
    for (int i = 0; i < x.length; ++i) {
      s[i] = x[i];
    }

    int[][] d = new int[][] {
      {9, 3, 1},
      {9, 1, 3},
      {3, 9, 1},
      {3, 1, 9},
      {1, 9, 3},
      {1, 3, 9},
    };

    int[][][] dp = new int[61][61][61];
    for (int i = 0; i <= s[0]; ++i) {
      for (int j = 0; j <= s[1]; ++j) {
        for (int k = 0; k <= s[2]; ++k) {
          if (i == 0 && j == 0 && k == 0) {
            continue;
          }
          int minValue = Integer.MAX_VALUE;
          for (int t = 0; t < d.length; ++t) {
            int ti = (i - d[t][0] < 0) ? 0 : (i - d[t][0]);
            int tj = (j - d[t][1] < 0) ? 0 : (j - d[t][1]);
            int tk = (k - d[t][2] < 0) ? 0 : (k - d[t][2]);
            minValue = Math.min(minValue, dp[ti][tj][tk] + 1);
          }
          dp[i][j][k] = minValue;
        }
      }
    }
    return dp[s[0]][s[1]][s[2]];
  }
}
