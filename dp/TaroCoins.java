public class TaroCoins {
  public long getNumber(long N) {
    long[][] dp = new long[65][2];
    
    dp[0][0] = 1;
    for (int i = 0; i <= 63; ++i) {
      if ((N & (1L << i)) == 0) {
        // Leave 0 coin in bit i.
        dp[i + 1][0] = dp[i][0];
        dp[i + 1][1] = dp[i][0] + dp[i][1];
      } else {
        // Leave 1 coin in bit i.
        dp[i + 1][0] = dp[i][0] + dp[i][1];
        dp[i + 1][1] = dp[i][1];
      }
    }
    return dp[64][0];
  }
}
