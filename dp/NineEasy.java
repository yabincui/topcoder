public class NineEasy {
  public int count(int N, int[] d) {
    int M = d.length;
    int STATE_SIZE = (int)Math.pow(10, N);
    int MOD = 1000000007;
    // dp[i][j], i means arrive to bit i, j is the % 9 result of each question, dp[i][j] means the
    // count % MOD. bit comes from 1 - M.
    int[][] dp = new int[M + 1][STATE_SIZE];
    for (int i = 0; i < STATE_SIZE; ++i) {
      dp[0][i] = 0;
    }
    dp[0][0] = 1;
    for (int i = 1; i <= M; ++i) {
      for (int digit = 0; digit <= 9; ++digit) {
        for (int j = 0; j < STATE_SIZE; ++j) {
          int next_state = nextState(j, digit, d[i - 1], N);
          dp[i][next_state] = (dp[i][next_state] + dp[i - 1][j]) % MOD;
        }
      }
    }
    return dp[M][0];
  }

  int nextState(int prevState, int digit, int mask, int mask_bits) {
    int state = 0;
    int level = 1;
    for (int i = 0; i < mask_bits; ++i, level *= 10) {
      int bit = prevState / level % 10;
      if ((mask & (1 << i)) != 0) {
        bit = (bit + digit) % 9;
      }
      state += bit * level;
    }
    return state;
  }
}
