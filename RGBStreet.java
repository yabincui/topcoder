public class RGBStreet {
  public int estimateCost(String[] houses) {
    int n = houses.length;
    int[][] dp = new int[n][3];
    
    dp[0] = stringToCostArray(houses[0]);
    for (int i = 1; i < n; ++i) {
      int[] cost = stringToCostArray(houses[i]);
      for (int j = 0; j <= 2; ++j) {
        int minValue = Integer.MAX_VALUE;
        for (int t = 0; t <= 2; ++t) {
          if (j != t) {
            minValue = Math.min(minValue, dp[i - 1][t] + cost[j]);
          }
        }
        dp[i][j] = minValue;
      }
    }
    int minValue = Integer.MAX_VALUE;
    for (int i = 0; i <= 2; ++i) {
      minValue = Math.min(minValue, dp[n - 1][i]);
    }
    return minValue;
  }

  int[] stringToCostArray(String house) {
    String[] strs = house.split(" ");
    int[] result = new int[strs.length];
    for (int i = 0; i < result.length; ++i) {
      result[i] = Integer.parseInt(strs[i]);
    }
    return result;
  }
}
