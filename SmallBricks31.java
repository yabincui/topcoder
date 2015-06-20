public class SmallBricks31 {
  final int MOD = 1000000007;
  public int countWays(int w, int h) {
    int maxMask = (1 << w) - 1;
    // dp[i][j] means at height i + 1, how many ways to form mask j.
    // for each bit k in mask j, if bit k is 1, that bit has block, otherwise not.
    int[][] dp = new int[h][maxMask+1];
    fillMask(maxMask, 1, w, dp[0]);
    for (int i = 0; i < h - 1; ++i) {
      for (int j = 0; j <= maxMask; ++j) {
        if (dp[i][j] != 0) {
          fillMask(j, dp[i][j], w, dp[i+1]);
        }
      }
    }
    int result = 1;
    for (int i = 0; i < h; ++i) {
      // Don't count mask 0.
      for (int j = 1; j <= maxMask; ++j) {
        result = (result + dp[i][j]) % MOD;
      }
    }
    return result;
  }

  void fillMask(int prevMask, int count, int w, int[] level) {
    int maxMask = (1 << w) - 1;
    int[][] dp = new int[w + 1][maxMask + 1];
    dp[0][0] = 1;
    for (int i = 1; i <= w; ++i) {
      // Use no block here.
      int prevMaxMask = (1 << (i - 1)) - 1;
      for (int j = 0; j <= prevMaxMask; ++j) {
        dp[i][j] = dp[i-1][j];
      }
      if ((prevMask & (1 << (i - 1))) == 0) {
        continue;
      }
      // Try block 1*1*1.
      for (int j = 0; j <= prevMaxMask; ++j) {
        int newMask = j | (1 << (i - 1));
        dp[i][newMask] = (dp[i][newMask] + dp[i-1][j]) % MOD;
      }
      if (i >= 2 && (prevMask & (1 << (i - 2))) != 0) {
        // Try block 1*1*2.
        prevMaxMask = (1 << (i - 2)) - 1;
        for (int j = 0; j <= prevMaxMask; ++j) {
          int newMask = j | (3 << (i - 2));
          dp[i][newMask] = (dp[i][newMask] + dp[i-2][j]) % MOD;
        }
      }
      if (i >= 3 && (prevMask & (1 << (i - 3))) != 0) {
        // Try block 1*1*3.
        prevMaxMask = (1 << (i - 3)) - 1;
        for (int j = 0; j <= prevMaxMask; ++j) {
          int newMask = j | (7 << (i - 3));
          dp[i][newMask] = (dp[i][newMask] + dp[i-3][j]) % MOD;
        }
      }
    }
    for (int i = 0; i <= maxMask; ++i) {
      long value = (long)count * dp[w][i] % MOD;
      level[i] = (level[i] + (int)value) % MOD;
    }
  }
}
