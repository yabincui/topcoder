public class HandsShaking {
  public long countPerfect(int n) {
    int[] dp = new int[n + 1];
    dp[0] = 1;
    for (int i = 2; i <= n; i += 2) {
      for (int j = 1; j < i; j += 2) {
        dp[i] += dp[j + 1 - 2] * dp[i - j - 1];
      }
    }
    return dp[n];
  }
}
