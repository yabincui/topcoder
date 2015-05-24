public class VocaloidsAndSongs {
  public int count(int S, int gumi, int ia, int mayu) {
    // dp[i][j][k][t] means for 0..i songs, G songs j, I songs k, M songs t, how many ways?
    long[][][][] dp = new long[S + 1][S + 1][S + 1][S + 1];
    long MOD = 1000000007L;
    dp[0][0][0][0] = 1;
    for (int i = 1; i <= S; ++i) {
      for (int j = 0; j <= gumi; ++j) {
        for (int k = 0; k <= ia; ++k) {
          for (int t = 0; t <= mayu; ++t) {
            long value = dp[i - 1][j][k][t];
            // Song by only one singer.
            if (j < gumi) {
              dp[i][j + 1][k][t] = (dp[i][j + 1][k][t] + value) % MOD;
            }
            if (k < ia) {
              dp[i][j][k + 1][t] = (dp[i][j][k + 1][t] + value) % MOD;
            }
            if (t < mayu) {
              dp[i][j][k][t + 1] = (dp[i][j][k][t + 1] + value) % MOD;
            }
            // Song by two singers.
            if (j < gumi && k < ia) {
              dp[i][j + 1][k + 1][t] = (dp[i][j + 1][k + 1][t] + value) % MOD;
            }
            if (j < gumi && t < mayu) {
              dp[i][j + 1][k][t + 1] = (dp[i][j + 1][k][t + 1] + value) % MOD;
            }
            if (k < ia && t < mayu) {
              dp[i][j][k + 1][t + 1] = (dp[i][j][k + 1][t + 1] + value) % MOD;
            }
            // Song by all three singers.
            if (j < gumi && k < ia && t < mayu) {
              dp[i][j + 1][k + 1][t + 1] = (dp[i][j + 1][k + 1][t + 1] + value) % MOD;
            }
          }
        }
      }
    }
    return (int)dp[S][gumi][ia][mayu];
  }
}
