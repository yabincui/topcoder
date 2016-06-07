public class ColorfulCupcakesDivTwo {
  final long MOD = 1000000007;
  public int countArrangements(String cupcakes) {
    int n = cupcakes.length();
    int[] nums = new int[3];
    for (int i = 0; i < n; ++i) {
      if (cupcakes.charAt(i) == 'A') {
        nums[0]++;
      } else if (cupcakes.charAt(i) == 'B') {
        nums[1]++;
      } else if (cupcakes.charAt(i) == 'C') {
        nums[2]++;
      }
    }
    long result = 0;
    for (int start = 0; start < 3; ++start) {
      int maxState = n * n * n;
      // dp[i][j][k] means the number of ways to assign cpucakes to 0..i guests, with last cupcakes
      // state j (with j/n/n 'A', j/n%n 'B', j%n 'C'), k is the last cupcake color.
      long[][][] dp = new long[n][maxState + 1][3];
      int startState = (nums[0] * n + nums[1]) * n + nums[2];
      if (start == 0) {
        startState -= n * n;
      } else if (start == 1) {
        startState -= n;
      } else {
        startState -= 1;
      }
      dp[0][startState][start] = 1;
      for (int i = 1; i < n; ++i) {
        for (int prevState = 0; prevState <= maxState; ++prevState) {
          int[] tnum = new int[3];
          tnum[0] = prevState / n / n;
          tnum[1] = prevState / n % n;
          tnum[2] = prevState % n;
          for (int prevEnd = 0; prevEnd < 3; ++prevEnd) {
            long prevWays = dp[i - 1][prevState][prevEnd];
            if (prevWays == 0) {
              continue;
            }
            for (int end = 0; end < 3; ++end) {
              if (end == prevEnd) {
                continue;
              }
              if (tnum[end] == 0) {
                continue;
              }
              int state = prevState;
              if (end == 0) {
                state -= n * n;
              } else if (end == 1) {
                state -= n;
              } else {
                state--;
              }
              dp[i][state][end] = (dp[i][state][end] + prevWays) % MOD;
            }
          }
        }
      }
      for (int end = 0; end < 3; ++end) {
        if (end == start) {
          continue;
        }
        result = (result + dp[n - 1][0][end]) % MOD;
      }
    }
    return (int)result;
  }
}
