public class FoxAverageSequence {
  final int MOD = 1000000007;
  public int theCount(int[] seq) {
    int n = seq.length;
    int maxValue = 40;
    int maxSum = maxValue * n;
    // dp[i][j][k] means using seq[0..i] get sum j, with last element in state k.
    // the value of the last element is k % (maxValue + 1).
    // if k <= maxValue, previous two item is in strict descending order, otherwise not.
    int[][][] dp = new int[n][maxSum + 1][(maxValue + 1) * 2];

    if (seq[0] != -1) {
      int sum = seq[0];
      dp[0][sum][maxValue + 1 + seq[0]] = 1;
    } else {
      for (int i = 0; i <= maxValue; ++i) {
        dp[0][i][maxValue + 1 + i] = 1;
      }
    }
    for (int i = 1; i < n; ++i) {
      for (int j = 0; j <= maxSum - maxValue; ++j) {
        double prevAverage = (double)j / i;
        for (int k = 0; k <= maxValue; ++k) {
          if (seq[i] != -1) {
            if (seq[i] - 1e-9 > prevAverage || seq[i] < k) {
              break;
            }
            int nj = j + seq[i];
            int nk = maxValue + 1 + seq[i];
            dp[i][nj][nk] = (dp[i][nj][nk] + dp[i-1][j][k]) % MOD;
          } else {
            for (int t = k; t <= maxValue; ++t) {
              if (t - 1e-9 > prevAverage) {
                break;
              }
              int nj = j + t;
              int nk = maxValue + 1 + t;
              dp[i][nj][nk] = (dp[i][nj][nk] + dp[i-1][j][k]) % MOD;
            }
          }
        }
        for (int k = maxValue + 1; k < 2 * maxValue + 2; ++k) {
          if (seq[i] != -1) {
            if (seq[i] - 1e-9 > prevAverage) {
              break;
            }
            int nj = j + seq[i];
            int lastValue = k - maxValue - 1;
            int nk = (seq[i] < lastValue) ? seq[i] : seq[i] + maxValue + 1;
            dp[i][nj][nk] = (dp[i][nj][nk] + dp[i-1][j][k]) % MOD;
          } else {
            for (int t = 0; t <= maxValue; ++t) {
              if (t - 1e-9 > prevAverage) {
                break;
              }
              int nj = j + t;
              int lastValue = k - maxValue - 1;
              int nk = (t < lastValue) ? t : t + maxValue + 1;
              dp[i][nj][nk] = (dp[i][nj][nk] + dp[i-1][j][k]) % MOD;
            }
          }
        }
      }
    }
    int result = 0;
    for (int j = 0; j <= maxSum; ++j) {
      for (int k = 0; k < maxValue + maxValue + 2; ++k) {
        result = (result + dp[n-1][j][k]) % MOD;
      }
    }
    return result;
  }
}
