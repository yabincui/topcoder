// http://community.topcoder.com/stat?c=problem_statement&pm=2829&rd=5072
public class QuickSums {
  public int minSums(String numbers, int sum) {
    int n = numbers.length();
    int[] s = new int[n];
    for (int i = 0; i < n; ++i) {
      s[i] = numbers.charAt(i) - '0';
    }
    // dp[i][j][k] is the smalles plus added to generate value k with digits i-j.
    int[][][] dp = new int[n][n][sum + 1];
    for (int i = 0; i < n; ++i) {
      for (int j = i; j < n; ++j) {
        for (int k = 0; k <= sum; ++k) {
          dp[i][j][k] = -1;  // Impossible.
        }
      }
    }

    // Initialize value.
    for (int i = 0; i < n; ++i) {
      int value = s[i];
      if (value > sum) {
        break;
      }
      dp[i][i][value] = 0;
      for (int j = i + 1; j < n; ++j) {
        value = value * 10 + s[j];
        if (value > sum) {
          break;
        }
        dp[i][j][value] = 0;
      }
    }

    for (int len = 2; len <= n; ++len) {
      for (int i = 0; i <= n - len; ++i) {
        int j = i + len - 1;
        for (int k = 0; k <= sum; ++k) {
          int minValue = dp[i][j][k];
          for (int k1 = 0; k1 <= k; ++k1) {
            for (int split = i; split < j; ++split) {
              if (dp[i][split][k1] != -1 && dp[split + 1][j][k - k1] != -1) {
                int temp = dp[i][split][k1] + dp[split + 1][j][k - k1] + 1;
                if (minValue == -1 || temp < minValue) {
                  minValue = temp;
                }
              }
            }
          }
          dp[i][j][k] = minValue;
        }
      }
    }
    return dp[0][n - 1][sum];
  }
}
