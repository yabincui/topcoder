public class MayTheBestPetWin {
  public int calc(int[] A, int[] B) {
    int n = A.length;
    final int MAX_VALUE = 50 * 10000;
    int aSum = 0;
    int bSum = 0;
    for (int i = 0; i < n; ++i) {
      aSum += A[i];
      bSum += B[i];
    }
    // dp[i] means the minimum difference when one minimum summary is i.
    int[] dp = new int[MAX_VALUE + 1];
    // record[i] records the maximum summary of dp[i].
    int[] record = new int[MAX_VALUE + 1]; 
    for (int i = 0; i <= MAX_VALUE; ++i) {
      dp[i] = -1;
    }
    dp[0] = bSum;
    for (int i = 0; i < n; ++i) {
      for (int j = MAX_VALUE; j >= A[i]; --j) {
        if (dp[j - A[i]] != -1) {
          int min1 = j;
          int max1 = record[j - A[i]] + B[i];
          int min2 = aSum - j;
          int max2 = bSum - max1;
          int maxDiff = Math.max(max1 - min2, max2 - min1);
          if (dp[j] == -1 || dp[j] > maxDiff) {
            dp[j] = maxDiff;
            record[j] = max1;
          }
        }
      }
    }
    int result = MAX_VALUE;
    for (int i = 0; i <= MAX_VALUE; ++i) {
      if (dp[i] == -1) {
        continue;
      }
      result = Math.min(result, dp[i]);
    }
    return result;
  }
}
