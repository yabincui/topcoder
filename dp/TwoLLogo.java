public class TwoLLogo {
  public long countWays(String[] grid) {
    int n = grid.length;
    int m = grid[0].length();

    // h[i][j] means the white height started from i row, j col, up to previous rows like i-1,..0.
    int[][] h = new int[n][m];
    for (int i = 0; i < m; ++i) {
      h[0][i] = grid[0].charAt(i) == '.' ? 1 : 0;
    }
    for (int i = 1; i < n; ++i) {
      for (int j = 0; j < m; ++j) {
        h[i][j] = grid[i].charAt(j) == '.' ? h[i - 1][j] + 1 : 0;
      }
    }

    // dp[i][j][k] means use items between j to k in row i as the horizon line, how many different
    // Ls can we get.
    long[][][] dp = new long[n][m][m];
    for (int i = 1; i < n; ++i) {
      for (int j = m - 1; j >= 0; --j) {
        if (grid[i].charAt(j) == '#') {
          for (int k = j + 1; k < m; ++k) {
            dp[i][j][k] = dp[i][j + 1][k];
          }
        } else {
          int hNum = h[i][j];
          boolean metBlack = false;
          long startPosCount = 0;
          for (int k = j + 1; k < m; ++k) {
            if (h[i][k] == 0) {
              metBlack = true;
            }
            if (!metBlack) {
              startPosCount += hNum - 1;
            }
            dp[i][j][k] = startPosCount + dp[i][j + 1][k];
          }
        }
      }
    }

    long sum = 0;
    for (int i = 1; i < n; ++i) {
      for (int j = 0; j < m; ++j) {
        if (h[i][j] > 1) {
          for (int height = 2; height <= h[i][j]; ++height) {
            for (int k = j + 1; k < m && h[i][k] >= 1; ++k) {
              long count = 0;
              if (k + 1 < m) {
                count += dp[i][k + 1][m - 1];
              }
              for (int line = i - 1; line > i - height; --line) {
                count += dp[line][j + 1][m - 1];
              }
              if (j > 1) {
                for (int line = i - 1; line > i - height; --line) {
                  count += dp[line][0][j - 1];
                }
              }
              for (int line = i - height; line >= 1; --line) {
                count += dp[line][0][m - 1];
              }
              sum += count;
            }
          }
        }
      }
    }
    return sum;
  }
}
