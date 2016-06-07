// http://apps.topcoder.com/wiki/display/tc/SRM+607
public class CombinationLockDiv2 {
  public int minimumMoves(String S, String T) {
    int n = S.length();
    int[] s = new int[n];
    for (int i = 0; i < n; ++i) {
      int diff = T.charAt(i) - S.charAt(i);
      if (diff < 0) {
        diff += 10;
      }
      s[i] = diff;
    }
    return minMove2(s);
  }
  
  int minMove(int[] s) {
    int n = s.length;
    int[][] dpDown = new int[n + 1][10]; 
    int[][] dpUp = new int[n + 1][10];

    for (int i = 0; i < n; ++i) {
      System.out.printf("s[%d] = %d\n", i, s[i]);
    }

    for (int i = 0; i <= n; ++i) {
      for (int j = 0; j < 10; ++j) {
        dpDown[i][j] = dpUp[i][j] = -1;
      }
    }
    dpDown[0][0] = 0;
    dpUp[0][0] = 0;
    for (int i = 1; i <= n; ++i) {
      int downDiff = s[i - 1];
      int minCost = Integer.MAX_VALUE;
      for (int j = 0; j <= downDiff && dpDown[i - 1][j] != -1; ++j) {
        int cost = dpDown[i - 1][j] + downDiff - j;
        minCost = Math.min(minCost, cost);
      }
      for (int j = 0; j <= downDiff; ++j) {
        dpDown[i][j] = minCost;
      }
      for (int j = downDiff + 1; j < 10; ++j) {
        dpDown[i][j] = -1;
      }

      int upDiff = 10 - s[i - 1];
      if (upDiff >= 10) {
        upDiff -= 10;
      }
      minCost = Integer.MAX_VALUE;
      for (int j = 0; j <= upDiff && dpUp[i - 1][j] != -1; ++j) {
        int cost = dpUp[i - 1][j] + upDiff - j;
        minCost = Math.min(minCost, cost);
      }
      for (int j = 0; j <= upDiff; ++j) {
        dpUp[i][j] = minCost;
      }
      for (int j = upDiff + 1; j < 10; ++j) {
        dpUp[i][j] = -1;
      }

      dpUp[i][0] = dpDown[i][0] = Math.min(dpUp[i][0], dpDown[i][0]);

      for (int j = 0; j < 10; ++j) {
        System.out.printf("dpDown[%d][%d] = %d\n", i, j, dpDown[i][j]);
      }
      for (int j = 0; j < 10; ++j) {
        System.out.printf("dpUp[%d][%d] = %d\n", i, j, dpUp[i][j]);
      }
    }
    int minValue = Integer.MAX_VALUE;
    for (int i = 0; i < 10; ++i) {
      if (dpUp[n][i] != -1) {
        minValue = Math.min(minValue, dpUp[n][i]);
      }
      if (dpDown[n][i] != -1) {
        minValue = Math.min(minValue,dpDown[n][i]);
      }
    }
    return minValue;
  }
  
  int minMove2(int[] s) {
    int n = s.length;
    int m = 10 * n;
    int[][] dpDown = new int[n][m]; 
    int[][] dpUp = new int[n][m];

    for (int i = 0; i < m; ++i) {
      if (i >= s[0] && (i - s[0]) % 10 == 0) {
        dpDown[0][i] = i;
      } else {
        dpDown[0][i] = -1;
      }

      int rem = (s[0] == 0) ? 0 : (10 - s[0]);
      if (i >= rem && (i - rem) % 10 == 0) {
        dpUp[0][i] = i;
      } else {
        dpUp[0][i] = -1;
      }
    }

    for (int i = 1; i < n; ++i) {
      for (int j = 0; j < m; ++j) {
        if (j >= s[i] && (j - s[i]) % 10 == 0) {
          int minValue = Integer.MAX_VALUE;
          for (int k = 0; k < m; ++k) {
            if (dpDown[i - 1][k] != -1) {
              if (k <= j) {
                minValue = Math.min(minValue, dpDown[i - 1][k] + j - k);
              } else {
                minValue = Math.min(minValue, dpDown[i - 1][k]);
              }
            }

            if (dpUp[i - 1][k] != -1) {
              minValue = Math.min(minValue, dpUp[i - 1][k] + j);
            }
          }
          dpDown[i][j] = minValue;
        } else {
          dpDown[i][j] = -1;
        }

        int rem = (s[i] == 0) ? 0 : (10 - s[i]);
        if (j >= rem && (j - rem) % 10 == 0) {
          int minValue = Integer.MAX_VALUE;
          for (int k = 0; k < m; ++k) {
            if (dpUp[i - 1][k] != -1) {
              if (k <= j) {
                minValue = Math.min(minValue, dpUp[i - 1][k] + j - k);
              } else {
                minValue = Math.min(minValue, dpUp[i - 1][k]);
              }
            }

            if (dpDown[i - 1][k] != -1) {
              minValue = Math.min(minValue, dpDown[i - 1][k] + j);
            }
          }
          dpUp[i][j] = minValue;
        } else {
          dpUp[i][j] = -1;
        }
      }
    }
    int minValue = Integer.MAX_VALUE;
    for (int i = 0; i < m; ++i) {
      if (dpDown[n - 1][i] != -1) {
        minValue = Math.min(minValue, dpDown[n - 1][i]);
      }
      if (dpUp[n - 1][i] != -1) {
        minValue = Math.min(minValue, dpUp[n - 1][i]);
      }
    }
    return minValue;
  }
}
