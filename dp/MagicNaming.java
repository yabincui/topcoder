public class MagicNaming {
  public int maxReindeers(String magicName) {
    int n = magicName.length();
    // dp[i][j] means split magicName[0..i] to j strings, the minimum biggest item.
    // if dp[i][j] is null, it means magicNum[0..i] can't be split to j strings.
    String[][] dp = new String[n][n + 1];
    
    dp[0][1] = magicName.substring(0, 1);
    for (int i = 1; i < n; ++i) {
      dp[i][1] = magicName.substring(0, i + 1);
      for (int j = 1; j <= i; ++j) {
        String last = magicName.substring(j, i + 1);
        // Or use binary search here.
        for (int k = 1; dp[j-1][k] != null; ++k) {
          String prev = dp[j-1][k];
          if (compare(prev, last) <= 0) {
            if (dp[i][k+1] == null || compare(dp[i][k+1], last) > 0) {
              dp[i][k+1] = last;
            }
          }
        }
      }
    }
    int result = 0;
    for (int i = 1; i <= n; ++i) {
      if (dp[n-1][i] != null) {
        result = i;
      }
    }
    return result;
  }
  
  int compare(String a, String b) {
    String ab = a + b;
    String ba = b + a;
    return ab.compareTo(ba);
  }
}
