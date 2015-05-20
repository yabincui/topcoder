// http://apps.topcoder.com/wiki/display/tc/SRM+624
public class GameOfSegments {
  public long enumerate(int N) {
    // Have equal number doesn't matter the result.
    // So all state are how many ways to enumerate a sum <= 1000, and no repeat value.
    long[][] dp = new long[N + 1][N + 1];
    dp[0][0] = 1;
    for (int i = 1; i <= N; ++i) {
      dp[i][0] = 1;
      for (int j = 1; j <= N; ++j) {
        dp[i][j] = dp[i - 1][j];
      }
      for (int j = i; j <= N; ++j) {
        dp[i][j] += dp[i - 1][j - i];
      }
    }
    long ways = 0;
    for (int i = 1; i <= N; ++i) {
      ways += dp[N][i];
    }
    System.out.printf("ways to enumerate(%d) is %d\n", N, ways);
    return ways;
  }

  public int winner(int N) {
    return N;
  }
}
