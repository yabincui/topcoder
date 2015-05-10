public class Stamp {
  public int getMinimumCost(String desiredColor, int stampCost, int pushCost) {
    int minCost = Integer.MAX_VALUE;
    int n = desiredColor.length();
    for (int stampLen = 1; stampLen <= n; ++stampLen) {
      // dp[i][j] means the min push times used to paint 0 to i,
      //          the color of pos i should be j (0 R, 1 G, 2 B).
      int[][] dp = new int[n][3];
      for (int i = 0; i < n; ++i) {
        for (int j = 0; j < 3; ++j) {
          dp[i][j] = -1;
        }
      }
      for (int i = stampLen - 1; i < n; ++i) {
        for (int j = 0; j < 3; ++j) {
          if (desiredColor.charAt(i) == 'R' && j != 0) {
            continue;
          } else if (desiredColor.charAt(i) == 'G' && j != 1) {
            continue;
          } else if (desiredColor.charAt(i) == 'B' && j != 2) {
            continue;
          }

          boolean valid = true;
          for (int k = i - 1; k >= i - stampLen + 1; --k) {
            if ((desiredColor.charAt(k) == 'R' && j != 0) ||
                (desiredColor.charAt(k) == 'G' && j != 1) ||
                (desiredColor.charAt(k) == 'B' && j != 2)) {
              valid = false;
              break;
            }
          }
          if (!valid) {
            continue;
          }
          int minValue = -1;
          if (i - stampLen >= 0) {
            for (int k = 0; k < 3; ++k) {
              if (dp[i - stampLen][k] != -1) {
                if (minValue == -1 || minValue > dp[i - stampLen][k]) {
                  minValue = dp[i - stampLen][k];
                }
              }
            }
          } else {
            minValue = 0;
          }
          for (int k = i - 1; k >= i - stampLen + 1; --k) {
            if (dp[k][j] != -1) {
              if (minValue == -1 || minValue > dp[k][j]) {
                minValue = dp[k][j];
              }
            }
          }
          if (minValue != -1) {
            dp[i][j] = minValue + 1;
          }
        }
      }
      int minValue = -1;
      for (int j = 0; j < 3; ++j) {
        if (dp[n - 1][j] != -1) {
          if (minValue == -1 || minValue > dp[n - 1][j]) {
            minValue = dp[n - 1][j];
          }
        }
      }
      if (minValue != -1) {
        int cost = stampLen * stampCost + minValue * pushCost;
        minCost = Math.min(cost, minCost);
      }
    }
    return minCost;
  }
}
