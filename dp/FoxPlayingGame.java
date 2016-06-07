public class FoxPlayingGame {
  public double theMax(int nA, int nB, int paramA, int paramB) {
    double[][] dpMax = new double[nA + 1][nB + 1];
    double[][] dpMin = new double[nA + 1][nB + 1];

    double scoreA = paramA / 1000.0;
    double scoreB = paramB / 1000.0;
    for (int i = 1; i <= nA; ++i) {
      dpMin[i][0] = dpMax[i][0] = dpMax[i - 1][0] + scoreA;
      for (int j = 1; j <= nB; ++j) {
        dpMax[i][j] = getMax(dpMin[i - 1][j] + scoreA, dpMax[i - 1][j] + scoreA,
                             dpMin[i][j - 1] * scoreB, dpMax[i][j - 1] * scoreB);
        dpMin[i][j] = getMin(dpMin[i - 1][j] + scoreA, dpMax[i - 1][j] + scoreA,
                             dpMin[i][j - 1] * scoreB, dpMax[i][j - 1] * scoreB);
      }
    }
    return dpMax[nA][nB];
  }

  double getMax(double a, double b, double c, double d) {
    return Math.max(Math.max(a, b), Math.max(c, d));
  }

  double getMin(double a, double b, double c, double d) {
    return Math.min(Math.min(a, b), Math.min(c, d));
  }
}
