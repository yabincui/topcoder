public class PillarsDivTwo {
  public double maximalLength(int[] height, int w) {
    double[][] dp = new double[height.length + 1][101];
    for (int i = 1; i < height.length; ++i) {
      for (int j = 1; j <= height[i]; ++j) {
        double maxValue = 0;
        for (int k = 1; k <= height[i - 1]; ++k) {
          double temp = dp[i - 1][k] + Math.sqrt(w * w + Math.abs(j - k) * Math.abs(j - k));
          maxValue = Math.max(maxValue, temp);
        }
        dp[i][j] = maxValue;
      }
    }
    double maxValue = 0;
    for (int i = 1; i <= height[height.length - 1]; ++i) {
      maxValue = Math.max(maxValue, dp[height.length - 1][i]);
    }
    return maxValue;
  }
}
