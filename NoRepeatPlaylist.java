public class NoRepeatPlaylist {
  public int numPlaylists(int N, int M, int P) {
    long mod = 1000000007;
    // dp[i] means M, P, using <=i types of songs.
    int[] dp = new int[N + 1];
    if (P > M) {
      dp[M] = 0;
    } else {
      dp[M] = (int)perm(M, P, mod);
    }
    for (int i = M + 1; i <= N; ++i) {
      long value = perm(i, M + 1, mod);
      for (int j = 0; j < P - M - 1; ++j) {
        value = (value * (i - M)) % mod;
      }
      dp[i] = (int)value;
    }
    int result = dp[N];
    int flag = -1;
    for (int i = N - 1; i >= M; --i) {
      result = (int)((result + (select(N, i, mod) * dp[i]) % mod * flag + mod) % mod);
      flag = -flag;
    }
    return result;
  }

  // C(n, m) % mod
  long select(int n, int m, long mod) {
    long[][] dp = new long[n + 1][m + 1];
    for (int i = 1; i <= n; ++i) {
      dp[i][0] = 1;
      if (i <= m) {
        dp[i][i] = 1;
      }
      for (int j = 1; j < i && j <= m; ++j) {
        dp[i][j] = (dp[i - 1][j] + dp[i - 1][j - 1]) % mod;
      }
    }
    return dp[n][m];
  }

  // P(n, m) % mod
  long perm(int n, int m, long mod) {
    long result = 1;
    for (int i = n - m + 1; i <= n; ++i) {
      result = result * i % mod;
    }
    return result;
  }
}
