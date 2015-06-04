public class DistinctRemainders {
  final long MOD = 1000000007;

  long howMany(long N, int M) {
    int maxRemNum = M;
    int maxRem = M * maxRemNum;
    // dp[i][j][k] means the number of ways to get sum rem j in first i items, all rems <= k.
    long[][][] dp = new long[maxRemNum][maxRem][M];
    dp[0][0][0] = 1;
    for (int k = 1; k < M; ++k) {
      dp[0][0][k] = 1;
    }
    for (int j = 1; j < M; ++j) {
      dp[0][j][j] = 1;
      for (int k = j + 1; k < M; ++k) {
        dp[0][j][k] = 1;
      }
    }
    for (int i = 1; i < maxRemNum; ++i) {
      for (int j = 1; j < maxRem; ++j) {
        for (int k = 1; k <= j && k < M; ++k) {
          dp[i][j][k] = (dp[i][j][k - 1] + dp[i - 1][j - k][k - 1]) % MOD; 
        }
        for (int k = j + 1; k < M; ++k) {
          dp[i][j][k] = dp[i][j][j];
        }
      }
    }
    int requestRem = (int)(N % M);
    long result = 0;
    for (int j = requestRem; j < maxRem; j += M) {
      long itemCount = (N - j) / M;
      for (int i = 0; i < maxRemNum; ++i) {
        long remWays = dp[i][j][M - 1];
        if (remWays == 0) {
          continue;
        }
        int remCount = i + 1;
        long add = calcAdd(remWays, remCount, itemCount);
        result = (result + add) % MOD;
      }
    }
    return result;
  }

  long calcAdd(long remWays, long remCount, long itemCount) {
    // It should be remWays * C(itemCount + remCount - 1, remCount - 1) * A(remCount).
    // Which is the same as remWays * (itemCount + remCount - 1) * ... * (itemCount + 1) * remCount.
    long add = remWays;
    for (long i = 0; i < remCount - 1; ++i) {
      // It can overflow.
      long temp = (itemCount + remCount - 1 - i) % MOD;
      add = (add * temp) % MOD;
    }
    add = (add * remCount) % MOD;
    return add;
  }
}
