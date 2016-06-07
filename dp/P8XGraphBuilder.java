public class P8XGraphBuilder {
  public int solve(int[] scores) {
    int n = scores.length + 1;
    // dp[i] is the max score achieved with i nodes.
    int[] dp = new int[n + 1];

    // dp[i] is the max score achieved with i nodes, the root node is connected to the parent above.
    int[] dpWithParent = new int[n + 1];
    dp[0] = 0;
    dp[1] = 0;
    dpWithParent[0] = 0;
    dpWithParent[1] = scores[0];
    for (int i = 2; i <= n; ++i) {
      for (int j = 1; j <= i - 1; ++j) {
        int subTreeMax = permute(i - 1, j, i - 1, dpWithParent);
        dp[i] = Math.max(dp[i], subTreeMax + scores[j - 1]);
        if (j + 1 != n) {
          dpWithParent[i] = Math.max(dpWithParent[i], subTreeMax + scores[j]);
        }
      }
    }
    return dp[n];
  }

  int permute(int sum, int num, int maxValue, int[] dpWithParent) {
    if (sum == 0) {
      return 0;
    }
    maxValue = Math.min(maxValue, sum - (num - 1));
    int result = 0;
    for (int i = 0; i <= num; ++i) {
      if (sum > (maxValue - 1) * (num - i) + i * maxValue) {
        continue;
      }
      if (sum < i * maxValue + num - i) {
        break;
      }
      int temp = dpWithParent[maxValue] * i + permute(sum - i * maxValue, num - i, maxValue - 1,
                                                      dpWithParent);
      result = Math.max(result, temp);
    }
    return result;
  }
}
