import java.util.*;

public class RandomPancakeStackDiv2 {
  public double expectedDeliciousness(int[] d) {
    int n = d.length;
    // dp[i][j] means for d[0 to i-1], with j bigger false value, the expected result.
    double[][] dp = new double[n + 1][n];

    for (int i = 1; i <= n; ++i) {
      for (int j = 0; j <= n - i; ++j) {
        double temp = 0;
        for (int choose = i; choose >= 1; --choose) {
          temp += 1.0 / (j + i) * (d[choose - 1] + dp[choose - 1][j + i - choose]);
        }
        dp[i][j] = temp;
      }
    }
    return dp[n][0];
  }
}
