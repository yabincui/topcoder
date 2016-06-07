public class AliceBobShuffle {
  final long MOD = 1000000007L;
  public int countWays(int[] AliceStart, int[] BobStart, int[] AliceEnd, int[] BobEnd) {
    int m1 = AliceStart.length;
    int n1 = BobStart.length;
    int m2 = AliceEnd.length;
    int n2 = BobEnd.length;
    // dp[i][j][k] means the number of ways to play to use AliceStart[0..i-1], BobStart[0..j-1]
    // to produce AliceEnd[0..k-1], BobEnd[0..(i+j-k-1)].
    long[][][] dp = new long[m1 + 1][n1 + 1][m2 + 1];
    dp[0][0][0] = 1;
    for (int i = 0; i <= m1; ++i) {
      for (int j = 0; j <= n1; ++j) {
        if (i == 0 && j == 0) {
          continue;
        }
        int minK = Math.max(0, i + j - n2);
        int maxK = Math.min(i + j, m2);
        for (int k = minK; k <= maxK; ++k) {
          if (i + j - k > 0) {
            if (i > 0 && AliceStart[i-1] == BobEnd[i+j-k-1]) {
              dp[i][j][k] = (dp[i][j][k] + dp[i-1][j][k]) % MOD;
            }
            if (j > 0 && BobStart[j-1] == BobEnd[i+j-k-1]) {
              dp[i][j][k] = (dp[i][j][k] + dp[i][j-1][k]) % MOD;
            }
          }
          if (k > 0) {
            if (i > 0 && AliceStart[i-1] == AliceEnd[k-1]) {
              dp[i][j][k] = (dp[i][j][k] + dp[i-1][j][k-1]) % MOD;
            }
            if (j > 0 && BobStart[j-1] == AliceEnd[k-1]) {
              dp[i][j][k] = (dp[i][j][k] + dp[i][j-1][k-1]) % MOD;
            }
          }
        }
      }
    }
    return (int)dp[m1][n1][m2];
  }
}
