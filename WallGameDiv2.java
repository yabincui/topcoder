public class WallGameDiv2 {
  public int play(String[] costs) {
    int m = costs.length;
    int n = costs[0].length();
    int[][] dp = new int[m][n];

    for (int i = 0; i < m; ++i) {
      for (int j = 0; j < n; ++j) {
        dp[i][j] = -1;
      }
    }

    for (int i = n - 1; i >= 0; --i) {
      if (costs[m - 1].charAt(i) == 'x') {
        continue;
      }
      dp[m - 1][i] = costs[m - 1].charAt(i) - '0';
    }
    for (int i = m - 2; i >= 0; --i) {
      for (int j = 0; j < n; ++j) {
        if (costs[i].charAt(j) == 'x') {
          continue;
        }
        if (dp[i + 1][j] != -1) {
          dp[i][j] = dp[i + 1][j] + costs[i].charAt(j) - '0';
        }
        int curRowCost = costs[i].charAt(j) - '0';
        for (int k = j - 1; k >= 0; --k) {
          if (costs[i].charAt(k) == 'x') {
            break;
          }
          curRowCost += costs[i].charAt(k) - '0';
          if (dp[i + 1][k] != -1) {
            int curCost = curRowCost + dp[i + 1][k];
            if (dp[i][j] == -1 || dp[i][j] < curCost) {
              dp[i][j] = curCost;
            }
          }
        }
        curRowCost = costs[i].charAt(j) - '0';
        for (int k = j + 1; k < n; ++k) {
          if (costs[i].charAt(k) == 'x') {
            break;
          }
          curRowCost += costs[i].charAt(k) - '0';
          if (dp[i + 1][k] != -1) {
            int curCost = curRowCost + dp[i + 1][k];
            if (dp[i][j] == -1 || dp[i][j] < curCost) {
              dp[i][j] = curCost;
            }
          }
        }
      }
    }
    return dp[0][0];
  }
}
