// http://community.topcoder.com/stat?c=problem_statement&pm=2940&rd=5854
// http://community.topcoder.com/tc?module=Static&d1=match_editorials&d2=srm208
public class StarAdventure {
  public int mostStars(String[] level) {
    int m = level.length;
    int n = level[0].length();
    if (n <= 3) {
      int result = 0;
      for (int i = 0; i < m; ++i) {
        for (int j = 0; j < n; ++j) {
          result += level[i].charAt(j) - '0';
        }
      }
      return result;
    }
    // dp[i][j][k] is max collection at each row (left = i, mid = j, right = k).
    int[][][] dp = new int[n][n][n];
    for (int row = 0; row < m; ++row) {
      for (int i = 0; i < n; ++i) {
        for (int j = i + 1; j < n; ++j) {
          for (int k = j + 1; k < n; ++k) {
            dp[i][j][k] += level[row].charAt(i) - '0' + level[row].charAt(j) - '0' + level[row].charAt(k) - '0';
          }
        }
      }
      // Increase i only.
      for (int i = 0; i < n; ++i) {
        for (int j = i + 1; j < n; ++j) {
          for (int k = j + 1; k < n; ++k) {
            if (i + 1 < j) {
              dp[i + 1][j][k] = Math.max(dp[i + 1][j][k], dp[i][j][k] + level[row].charAt(i + 1) - '0');
            }
          }
        }
      }
      // Increase j only.
      for (int i = 0; i < n; ++i) {
        for (int j = i + 1; j < n; ++j) {
          for (int k = j + 1; k < n; ++k) {
            if (j + 1 < k) {
              dp[i][j + 1][k] = Math.max(dp[i][j + 1][k], dp[i][j][k] + level[row].charAt(j + 1) - '0');
            }
          }
        }
      }
      // Increase k only.
      for (int i = 0; i < n; ++i) {
        for (int j = i + 1; j < n; ++j) {
          for (int k = j + 1; k < n; ++k) {
            if (k + 1 < n) {
              dp[i][j][k + 1] = Math.max(dp[i][j][k + 1], dp[i][j][k] + level[row].charAt(k + 1) - '0'); 
            }
          }
        }
      }
    }
    int maxValue = 0;
    for (int i = 0; i < n; ++i) {
      for (int j = i + 1; j < n; ++j) {
        for (int k = j + 1; k < n; ++k) {
          maxValue = Math.max(maxValue, dp[i][j][k]);
        }
      }
    }
    return maxValue;
  }
}
