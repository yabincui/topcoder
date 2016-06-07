import java.util.*;

public class ShopPositions {
  public int maxProfit(int n, int m, int[] c) {
    // dp[i][j][k] means the profits of 0-i buildings, with j shops in building i, and k shops in building i + 1.
    int[][][] dp = new int[n + 1][m + 1][m + 1];
    for (int i = 1; i <= n; ++i) {
      for (int curShop = 0; curShop <= m; ++curShop) {
        for (int nextShop = 0; nextShop <= m; ++nextShop) {
          // dp[i][curShop][nextShop]
          for (int prevShop = 0; prevShop <= m; ++prevShop) {
            int prevProfit = dp[i - 1][prevShop][curShop];
            int shopSum = prevShop + curShop + nextShop;
            int curProfit = 0;
            if (curShop != 0) {
              curProfit = c[(i - 1) * 3 * m + shopSum - 1] * curShop;
            }
            int profit = prevProfit + curProfit;
            dp[i][curShop][nextShop] = Math.max(dp[i][curShop][nextShop], profit);
          }
        }
      }
    }
    int result = 0;
    for (int i = 0; i <= m; ++i) {
      result = Math.max(result, dp[n][i][0]);
    }
    return result;
  }
}
