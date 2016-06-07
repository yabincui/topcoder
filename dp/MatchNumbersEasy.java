public class MatchNumbersEasy {
  public String maxNumber(int[] matches, int n) {
    // dp[i][j] means using i matches, having j digits, the max value. null if not possible.
    String [][] dp = new String[n + 1][n + 1];
    for (int i = 0; i <= n; ++i) {
      for (int j = 0; j <= n; ++j) {
        dp[i][j] = null;
      }
    }
    dp[0][0] = "";
    for (int i = 1; i <= n; ++i) {
      for (int k = 0; k < matches.length; ++k) {
        int match = matches[k];
        if (i < match) {
          continue;
        }
        for (int j = 1; j <= n; ++j) {
          if (dp[i - match][j - 1] != null) {
            String value = Integer.toString(k) + dp[i - match][j - 1];
            if (dp[i][j] == null || compareString(dp[i][j], value) < 0) {
              dp[i][j] = value;
            }
          }
        }
      }
    }
    String maxValue = "0";
    for (int i = 0; i <= n; ++i) {
      for (int j = 0; j <= n; ++j) {
        if (dp[i][j] != null && compareString(maxValue, dp[i][j]) < 0) {
          maxValue = dp[i][j]; 
        }
      }
    }
    return maxValue;
  }

  int compareString(String s1, String s2) {
    int i = 0;
    int j = 0;
    while (true) {
      while (i < s1.length() && s1.charAt(i) == '0') {
        ++i;
      }
      while (j < s2.length() && s2.charAt(j) == '0') {
        ++j;
      }
      if (i == s1.length() && j == s2.length()) {
        return 0;
      } else if (i == s1.length()) {
        return -1;
      } else if (j == s2.length()) {
        return 1;
      } else {
        int t1 = s1.charAt(i) - '0';
        int t2 = s2.charAt(j) - '0';
        int l1 = s1.length() - i;
        int l2 = s2.length() - j;
        if (l1 != l2) {
          return l1 - l2;
        }
        if (t1 != t2) {
          return t1 - t2;
        }
        ++i;
        ++j;
      }
    }
  }
}
