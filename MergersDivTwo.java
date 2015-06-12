import java.util.*;

public class MergersDivTwo {
  final double INF = Integer.MIN_VALUE;

  public double findMaximum(int[] revenues, int k) {
    int n = revenues.length;
    Arrays.sort(revenues);

    double[] addUp = new double[n];
    addUp[0] = revenues[0];
    for (int i = 1; i < n; ++i) {
      addUp[i] = addUp[i-1] + revenues[i];
    }

    // dp[i] means the max sum revenue of revenues[0..i].
    double[] dp = new double[n];
    for (int i = 0; i < n; ++i) {
      dp[i] = INF;
    }

    for (int i = k; i <= n; ++i) {
      dp[i] = addUp[i] / i;
      for (int len = k; len < i; ++len) {
        int j = i - len + 1;
        if (dp[j] == INF) {
          continue;
        }
        double temp = (dp[j] + (addUp[i] - addUp[j])) / len;
        dp[i] = Math.max(dp[i], temp);
      }
    }
    return dp[n-1];
  }
}
