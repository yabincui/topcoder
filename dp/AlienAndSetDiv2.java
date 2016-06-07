public class AlienAndSetDiv2 {
  public int getNumber(int N, int K) {
    long MOD = 1000000007L;
    int stateCount = 1024;
    N *= 2;
    // dp[i][j][k] (i > j) means the count of ways to choose number i + 1 match number j + 1, with
    // state k. If bit t is set in k, it means number i - t is in the same set of number i + 1,
    // otherwise number i - t has already been used. 
    long[][][] dp = new long[N][N][stateCount];
    for (int i = 1; i <= Math.min(N - 1, K); ++i) {
      dp[i][0][0] = 2;
    }
    for (int i = 2; i < N; ++i) {
      for (int j = Math.max(0, i - K); j < i; ++j) {
        for (int bit = 0; bit <= 10 && bit <= j - 2; ++bit) {
          long temp = dp[j - 1][j - 2 - bit][(1 << bit) - 1];
          dp[i][j][0] = (dp[i][j][0] + temp * 2) % MOD;
        }
        for (int state = 1; state < stateCount && state < (1 << (i - j - 1)); ++state) {
          int bit = findFirstBit(state);
          int ci = i - 1 - bit;
          int cstate = state >> (bit + 1);
          for (int cj = j - 1; cj >= Math.max(0, ci - K); --cj) {
            long temp = dp[ci][cj][cstate];
            dp[i][j][state] = (dp[i][j][state] + temp) % MOD;
            cstate |= (1 << (ci - cj - 1));
          }
        }
      }
    }
    int cstate = 0;
    long result = 0;
    for (int j = N - 2; j >= Math.max(0, N - 1 - K); --j) {
      result = (result + dp[N - 1][j][cstate]) % MOD;
      cstate |= (1 << (N - 1 - j - 1));
    }
    return (int)result;
  }

  int findFirstBit(int state) {
    for (int i = 0; i < 10; ++i) {
      if ((state & (1 << i)) != 0) {
        return i;
      }
    }
    return -1;
  }
}
