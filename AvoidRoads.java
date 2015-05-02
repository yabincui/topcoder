// http://community.topcoder.com/stat?c=problem_statement&pm=1889&rd=4709
public class AvoidRoads {
  public long numWays(int width, int height, String[] bad) {
    boolean[][] blockRightMap = new boolean[width + 1][height + 1];
    boolean[][] blockUpMap = new boolean[width + 1][height + 1];
    for (int k = 0; k < bad.length; ++k) {
      String[] strs = bad[k].split(" ");
      int[] nums = new int[4];
      for (int i = 0; i < 4; ++i) {
        nums[i] = Integer.parseInt(strs[i]);
      }
      if (nums[0] == nums[2]) {
        int r = Math.min(nums[1], nums[3]);
        blockUpMap[nums[0]][r] = true;
      } else {
        int c = Math.min(nums[0], nums[2]);
        blockRightMap[c][nums[1]] = true;
      }
    }

    long[][] dp = new long[width + 1][height + 1];
    dp[0][0] = 1;
    for (int i = 1; i <= width; ++i) {
      if (blockRightMap[i - 1][0] == false) {
        dp[i][0] = dp[i - 1][0];
      }
    }
    for (int i = 1; i <= height; ++i) {
      if (blockUpMap[0][i - 1] == false) {
        dp[0][i] = dp[0][i - 1]; 
      }
    }
    for (int i = 1; i <= width; ++i) {
      for (int j = 1; j <= height; ++j) {
        if (!blockRightMap[i - 1][j]) {
          dp[i][j] += dp[i - 1][j];
        }
        if (!blockUpMap[i][j - 1]) {
          dp[i][j] += dp[i][j - 1];
        }
      }
    }
    return dp[width][height];
  }
}
