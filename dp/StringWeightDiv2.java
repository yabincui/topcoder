public class StringWeightDiv2 {
  final long MOD = 1000000009L;

  public int countMinimums(int L) {
    // dp[i][j] means the number of ways to achieve minimum weight of length i with exactly j colors.
    long[][] dp = new long[L + 1][27];
    for (int i = 1; i <= L; ++i) {
      dp[i][1] = 1;
      for (int j = 2; j <= 26; ++j) {
        long sum = 0;
        for (int k = 1; k <= i - j + 1; ++k) {
          sum = (sum + dp[i - k][j - 1]) % MOD;
        }
        dp[i][j] = j * sum % MOD;
      }
    }
    int result = 0;
    if (L <= 26) {
      result = permute(26, L);
    } else {
      result = (int)dp[L][26];
    }
    return result;
  }

  int permute(int n, int k) {
    long result = 1;
    for (int i = 0; i < k; ++i) {
      result = (result * (n - i)) % MOD;
    }
    return (int)result;
  }
}
