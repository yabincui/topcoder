public class TaroCards {
  public long getNumber(int[] first, int[] second, int K) {
    int stateCount = 1024;
    int n = first.length;
    // dp[i][j][k] means using first/second[0..i], having j different values, in state k, how many
    // ways? state k means, for each bit i in k, if bit i is set, the state contains value i + 1.
    long[][][] dp = new long[n][K + 1][stateCount];

    dp[0][0][0] = 1;
    if (first[0] == second[0]) {
      int state = (1 << (second[0] - 1));
      dp[0][1][state] = 1;
    } else {
      int state = (1 << (second[0] - 1));
      if (first[0] <= 10) {
        state |= (1 << (first[0] - 1));
      }
      if (K > 1) {
        dp[0][2][state] = 1;
      }
    }
    for (int i = 1; i < n; ++i) {
      // Copy old value.
      for (int j = 0; j <= K; ++j) {
        for (int k = 0; k < stateCount; ++k) {
          dp[i][j][k] = dp[i - 1][j][k];
        }
      }

      int add_state = (1 << (second[i] - 1));
      if (first[i] <= 10) {
        add_state |= (1 << (first[i] - 1));
      }

      // Add first[i] and second[i].
      for (int j = 0; j <= K; ++j) {
        for (int k = 0; k < stateCount; ++k) {
          boolean containFirst = false;
          if (first[i] <= 10 && ((k & (1 << (first[i] - 1))) != 0)) {
            containFirst = true;
          }
          boolean containSecond = false;
          if ((k & (1 << (second[i] - 1))) != 0) {
            containSecond = true;
          }
          if (first[i] == second[i]) {
            containFirst = true;  // Use containSecond only.
          }
          int nj = j;
          if (!containFirst) {
            nj++;
          }
          if (!containSecond) {
            nj++;
          }
          int nState = k | add_state;
          if (nj <= K) {
            dp[i][nj][nState] += dp[i - 1][j][k];
          }
        }
      }
    }
    long sum = 0;
    for (int j = 0; j <= K; ++j) {
      for (int k = 0; k < stateCount; ++k) {
        sum += dp[n - 1][j][k];
      }
    }
    return sum;
  }
}
