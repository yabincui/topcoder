public class TheBrickTowerHardDivTwo {
  final long MOD = 1234567891;
  public int find(int C, int K, int H) {
    int maxState = (1 << (2 * 4)) - 1;
    long[][][] dp = new long[H][K + 1][maxState + 1];
    for (int state = 0; state <= maxState; ++state) {
      if (!validState(state, C)) {
        continue;
      }
      int k = getSameColors(state);
      if (k <= K) {
        dp[0][k][state] = 1;
      }
    }
    for (int h = 1; h < H; ++h) {
      for (int state = 0; state <= maxState; ++state) {
        if (!validState(state, C)) {
          continue;
        }
        for (int prevState = 0; prevState <= maxState; ++prevState) {
          if (!validState(prevState, C)) {
            continue;
          }
          int addK = getSameColors(state, prevState);
          for (int k = addK; k <= K; ++k) {
            dp[h][k][state] = (dp[h][k][state] + dp[h - 1][k - addK][prevState]) % MOD;
          }
        }
      }
    }
    long result = 0;
    for (int h = 0; h < H; ++h) {
      for (int k = 0; k <= K; ++k) {
        for (int state = 0; state <= maxState; ++state) {
          if (!validState(state, C)) {
            continue;
          }
          result = (result + dp[h][k][state]) % MOD;
        }
      }
    }
    return (int)result;
  }

  int getSameColors(int state, int prevState) {
    int count = getSameColors(state);
    for (int i = 0; i < 4; ++i) {
      int t1 = (state >> (i * 2)) & 3;
      int t2 = (prevState >> (i * 2)) & 3;
      if (t1 == t2) {
        ++count;
      }
    }
    return count;
  }

  int getSameColors(int state) {
    int[] bricks = new int[4];
    for (int i = 0; i < 4; ++i) {
      bricks[i] = (state >> (i * 2)) & 3;
    }
    int count = 0;
    if (bricks[0] == bricks[1]) {
      ++count;
    }
    if (bricks[0] == bricks[2]) {
      ++count;
    }
    if (bricks[1] == bricks[3]) {
      ++count;
    }
    if (bricks[2] == bricks[3]) {
      ++count;
    }
    return count;
  }

  boolean validState(int state, int C) {
    for (int i = 0; i < 4; ++i) {
      int c = (state >> (i * 2)) & 3;
      if (c >= C) {
        return false;
      }
    }
    return true;
  }
}
