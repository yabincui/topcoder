import java.util.*;

public class TheCowDivTwo {
  final int MOD = 1000000007;
  public int find(int N, int K) {
    // dp[i][j][k] means using i numbers from from 0..j-1 numbers, to make a sum % N = k,
    // how many ways.
    int[][][] dp = new int[K + 1][N + 1][N];
    for (int j = 0; j <= N; ++j) {
      dp[0][j][0] = 1;
    }
    for (int i = 1; i <= K; ++i) {
      for (int j = i; j <= N; ++j) {
        int currValue = j - 1;
        for (int k = 0; k < N; ++k) {
          int prevK = (k - currValue + N) % N;
          dp[i][j][k] = (dp[i][j-1][k] + dp[i-1][j-1][prevK]) % MOD;
        }
      }
    }
    int result = dp[K][N][0];
    return result;
  }
}
