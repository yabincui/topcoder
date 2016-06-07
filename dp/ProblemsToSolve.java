public class ProblemsToSolve {
  public int minNumber(int[] pleasantness, int variety) {
    int n = pleasantness.length;
    int[][] dpMax = new int[n][n + 1];
    int[][] dpMin = new int[n][n + 1];
    int result = n;
    
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j <= n; ++j) {
        dpMax[i][j] = -1;
        dpMin[i][j] = -1;
      }
    }
    dpMax[0][1] = pleasantness[0];
    dpMin[0][1] = pleasantness[0];
    for (int i = 1; i < n; ++i) {
      for (int j = 0; j <= n; ++j) {
        if ((dpMin[i - 1][j] != -1 && dpMin[i - 1][j] <= pleasantness[i] - variety) ||
            (i >= 2 && dpMin[i - 2][j] != -1 && dpMin[i - 2][j] <= pleasantness[i] - variety) ||
            (dpMax[i - 1][j] != -1 && dpMax[i - 1][j] >= pleasantness[i] + variety) ||
            (i >= 2 && dpMax[i - 2][j] != -1 && dpMax[i - 2][j] >= pleasantness[i] + variety)) {
          int num = j + 1;
          if (num < result) {
            result = num;
          }
          break;
        }
      }
      for (int j = 1; j <= n; ++j) {
        int minValue = Integer.MAX_VALUE;
        if (dpMin[i - 1][j - 1] != -1) {
          minValue = Math.min(minValue, pleasantness[i]);
          minValue = Math.min(minValue, dpMin[i - 1][j - 1]);
        }
        if (i >= 2 && dpMin[i - 2][j - 1] != -1) {
          minValue = Math.min(minValue, pleasantness[i]);
          minValue = Math.min(minValue, dpMin[i - 2][j - 1]);
        }
        if (minValue != Integer.MAX_VALUE) {
          dpMin[i][j] = minValue;
        }

        int maxValue = -1;
        if (dpMax[i - 1][j - 1] != -1) {
          maxValue = Math.max(maxValue, pleasantness[i]);
          maxValue = Math.max(maxValue, dpMax[i - 1][j - 1]);
        }
        if (i >= 2 && dpMax[i - 2][j - 1] != -1) {
          maxValue = Math.max(maxValue, pleasantness[i]);
          maxValue = Math.max(maxValue, dpMax[i - 2][j - 1]);
        }
        if (maxValue != -1) {
          dpMax[i][j] = maxValue;
        }
      }
    }
    return result;
  }
}
