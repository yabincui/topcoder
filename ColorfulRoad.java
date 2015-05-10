public class ColorfulRoad {
  public int getMin(String road) {
    int n = road.length();
    // dp[i] means the smallest cost to reach pos i.
    int[] dp = new int[n];
    dp[0] = 0;
    for (int i = 1; i < n; ++i) {
      dp[i] = -1;
    }
    for (int i = 1; i < n; ++i) {
      char prevColor = (road.charAt(i) == 'R') ? 'B' : (road.charAt(i) == 'G' ? 'R' : 'G');
      int minValue = -1;
      for (int j = i - 1; j >= 0; --j) {
        if (road.charAt(j) == prevColor && dp[j] != -1) {
          int temp = dp[j] + (i - j) * (i - j);
          if (minValue == -1 || minValue > temp) {
            minValue = temp;
          }
        }
      }
      dp[i] = minValue;
    }
    return dp[n - 1];
  }
}
