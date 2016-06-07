public class MuddyRoad2 {
  long MOD = 555555555;

  final int CUR_EVEN_PREV_ODD = 0;
  final int CUR_EVEN_PREV_EVEN = 1;
  final int CUR_ODD_PREV_ODD = 2;
  final int CUR_ODD_PREV_EVEN = 3;

  public int theCount(int N, int muddyCount) {
    // dp[i][j][k] means the number of ways reaching situation, first i segments, having j muddy segments,
    // having k number of roads (k = 00 current even, prev even, k = 01 current event, prev odd,
    long[][][] dp = new long[N][muddyCount + 1][4];

    dp[0][0][CUR_ODD_PREV_EVEN] = 1;
    for (int i = 1; i <= N - 1; ++i) {
      dp[i][0][CUR_EVEN_PREV_ODD] = dp[i - 1][0][CUR_ODD_PREV_ODD];
      dp[i][0][CUR_EVEN_PREV_EVEN] = dp[i - 1][0][CUR_EVEN_PREV_EVEN];
      dp[i][0][CUR_ODD_PREV_EVEN] = dp[i - 1][0][CUR_EVEN_PREV_ODD];
      dp[i][0][CUR_ODD_PREV_ODD] = dp[i - 1][0][CUR_ODD_PREV_EVEN];

      for (int j = 1; j <= muddyCount; ++j) {
        // current is muddy.
        dp[i][j][CUR_EVEN_PREV_ODD] = dp[i - 1][j - 1][CUR_ODD_PREV_ODD] + dp[i - 1][j - 1][CUR_ODD_PREV_EVEN];
        dp[i][j][CUR_EVEN_PREV_EVEN] = dp[i - 1][j - 1][CUR_EVEN_PREV_ODD] + dp[i - 1][j - 1][CUR_EVEN_PREV_EVEN];
      
        // current is dry.
        dp[i][j][CUR_EVEN_PREV_ODD] += dp[i - 1][j][CUR_ODD_PREV_ODD];
        dp[i][j][CUR_EVEN_PREV_EVEN] += dp[i - 1][j][CUR_EVEN_PREV_EVEN];
        dp[i][j][CUR_ODD_PREV_ODD] = dp[i - 1][j][CUR_ODD_PREV_EVEN];
        dp[i][j][CUR_ODD_PREV_EVEN] = dp[i - 1][j][CUR_EVEN_PREV_ODD];

        for (int k = 0; k < 4; ++k) {
          dp[i][j][k] %= MOD;
        }
      }
    }
    long result = 0;
    result += dp[N - 2][muddyCount][CUR_ODD_PREV_ODD];
    result += dp[N - 2][muddyCount][CUR_EVEN_PREV_EVEN];
    result %= MOD;
    return (int)result;
  }
}
