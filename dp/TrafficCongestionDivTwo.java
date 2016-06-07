public class TrafficCongestionDivTwo {
  public long theMinCars(int treeHeight) {
    //dp[i] means the cost to link Tree of height i.
    long[] dp = new long[treeHeight + 1];
    dp[0] = 1;
    for (int h = 1; h <= treeHeight; ++h) {
      long minValue = Long.MAX_VALUE;
      for (int leftEdge = 0; leftEdge <= h; ++leftEdge) {
        for (int rightEdge = 0; rightEdge <= h; ++rightEdge) {
          long leftCost = 0;
          if (leftEdge > 0) {
            leftCost += dp[leftEdge - 1];
          }
          for (int i = Math.max(0, leftEdge - 1); i < h - 1; ++i) {
            leftCost += dp[i];
          }
          long rightCost = 0;
          if (rightEdge > 0) {
            rightCost += dp[rightEdge - 1];
          }
          for (int i = Math.max(0, rightEdge - 1); i < h - 1; ++i) {
            rightCost += dp[i];
          }
          long temp = leftCost + rightCost + 1;
          minValue = Math.min(minValue, temp);
        }
      }
      dp[h] = minValue;
    }
    return dp[treeHeight];
  }
}
