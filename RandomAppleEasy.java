public class RandomAppleEasy {
  public double theRed(int[] red, int[] green) {
    int n = red.length;
    int sumRed = 0;
    int sumGreen = 0;
    for (int i = 0; i < n; ++i) {
      sumRed += red[i];
      sumGreen += green[i];
    }
    long[][] dp = new long[sumRed + 1][sumGreen + 1];
    dp[0][0] = 1;
    for (int i = 0; i < n; ++i) {
      for (int j = sumRed; j >= red[i]; --j) {
        for (int k = sumGreen; k >= green[i]; --k) {
          dp[j][k] += dp[j - red[i]][k - green[i]];
        }
      }
    }
    dp[0][0] = 0;
    double sumProbability = 0.0;
    for (int i = 0; i <= sumRed; ++i) {
      for (int j = 0; j <= sumGreen; ++j) {
        double probability = dp[i][j] * (i + j != 0 ? ((double)i) / (i + j) : 0);
        sumProbability += probability;
      }
    }
    long div = (1L << n) - 1;
    double result = sumProbability / div;
    return result;
  }
}