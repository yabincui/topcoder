public class MakeSquare {
  public int minChanges(String S) {
    int result = S.length();
    for (int split = 0; split < S.length(); ++split) {
      String partOne = S.substring(0, split);
      String partTwo = S.substring(split);
      int cost = match(partOne, partTwo);
      result = Math.min(result, cost);
    }
    return result;
  }

  int match(String s1, String s2) {
    int m = s1.length();
    int n = s2.length();
    if (m == 0) {
      return n;
    }
    if (n == 0) {
      return m;
    }
    // dp[i][j] means the cost to match s1[0..i-1] and s2[0..j-1].
    int[][] dp = new int[m+1][n+1];
    for (int i = 0; i <= m; ++i) {
      dp[i][0] = i;
    }
    for (int j = 0; j <= n; ++j) {
      dp[0][j] = j;
    }
    for (int i = 1; i <= m; ++i) {
      for (int j = 1; j <= n; ++j) {
        if (s1.charAt(i-1) == s2.charAt(j-1)) {
          dp[i][j] = dp[i-1][j-1];
        } else {
          dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1])) + 1;
        }
      }
    }
    return dp[m][n];
  }
}
