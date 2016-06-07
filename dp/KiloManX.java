public class KiloManX {
  public int leastShots(String[] damageChart, int[] bossHealth) {
    int n = damageChart.length;
    // damageMap[i][j] is how many shots to damage boss j using weapon i. -1 means can't damage.
    int[][] damageMap = new int[n][n];
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < n; ++j) {
        int d = damageChart[i].charAt(j) - '0';
        if (d == 0) {
          damageMap[i][j] = -1;
        } else {
          damageMap[i][j] = (bossHealth[j] + d - 1) / d;
        }
      }
    }
    int maxMask = 1;
    for (int i = 0; i < n; ++i) {
      maxMask <<= 1;
    }
    maxMask -= 1;
    int[] cost = new int[maxMask + 1];
    for (int i = 1; i <= maxMask; ++i) {
      int minCost = Integer.MAX_VALUE;
      for (int j = 0; j < n; ++j) {
        if (((i >> j) & 1) == 0) {
          continue;
        }
        int prev = i & ~(1 << j);
        int curCost = cost[prev];
        int add = bossHealth[j];
        for (int k = 0; k < n; ++k) {
          if (((prev >> k) & 1) == 0 || damageMap[k][j] == -1) {
            continue;
          }
          add = Math.min(add, damageMap[k][j]);
        }
        curCost += add;
        minCost = Math.min(minCost, curCost);
      }
      cost[i] = minCost;
    }
    return cost[maxMask];
  }
}
