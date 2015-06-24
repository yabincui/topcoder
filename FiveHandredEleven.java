// It is interesting to enumerate the state in reasonable range.
public class FiveHandredEleven {
  public String theWinner(int[] cards) {
    int n = cards.length;
    // dp[i][j] means the value of g(state). state is memory is i, with j cards not changing value of
    // i. g(state) = mex(g(next_state), next_state belongs to followers(state)).
    int[][] dp = new int[512][n + 1];

    for (int i = 0; i <= n; ++i) {
      dp[511][i] = 1;  // Terminal state, but the next player wins.
    }
    for (int mask = 1; mask <= 511; ++mask) {
      int i = 511 & ~mask;
      for (int j = 0; j <= n; ++j) {
        boolean[] hit = new boolean[n + 1];
        if (j > 0) {
          hit[dp[i][j-1]] = true;
        }
        for (int k = 0; k < n; ++k) {
          if ((i | cards[k]) != i) {
            int ni = i | cards[k];
            int nj = j;
            for (int t = 0; t < n; ++t) {
              if (t == k) {
                continue;
              }
              if ((cards[t] | i) != i && (cards[t] | ni) == ni) {
                ++nj;
              }
            }
            nj = Math.min(n, nj); // if nj > n, dp[i][j] must be impossible.
            hit[dp[ni][nj]] = true;
          }
        }
        for (int k = 0; k <= n; ++k) {
          if (hit[k] == false) {
            dp[i][j] = k;
            break;
          }
        }
      }
    }
    int zero_count = 0;
    for (int i = 0; i < n; ++i) {
      if (cards[i] == 0) {
        zero_count++;
      }
    }
    return dp[0][zero_count] == 0 ? "Toastman" : "Fox Ciel";
  }
}
