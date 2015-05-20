public class BubbleSortWithReversals {
  public int getMinSwaps(int[] A, int K) {
    int n = A.length;
    // reverseCount[i][j][k], the count of numbers in A[i, j] bigger than A[k].
    int[][][] reverseCount = new int[n][n][n];

    for (int k = 0; k < n; ++k) {
      int value = A[k];
      for (int i = 0; i < k; ++i) {
        if (A[i] > value) {
          reverseCount[i][i][k] = 1;
        }
        for (int j = i + 1; j < k; ++j) {
          reverseCount[i][j][k] = reverseCount[i][j - 1][k] + (A[j] > value ? 1 : 0);
        }
      }
    }

    // dp[i][j] means the minimum reverseCount after j swaps in range A[0, i].
    int[][] dp = new int[n][K + 1];
    for (int i = 1; i < n; ++i) {
      dp[i][0] = dp[i - 1][0] + reverseCount[0][i - 1][i];
      for (int swap = 1; swap <= K; ++swap) {
        int match = -1;
        int minValue = dp[i][swap - 1];
        minValue = Math.min(minValue, countAfterReverse(A, 0, i));
        if (minValue < dp[i][swap - 1]) {
          match = 0;
        }
        for (int split = 1; split < i; ++split) {
          int temp = countAfterReverse(A, split, i) + dp[split - 1][swap - 1];

          for (int k = split; k <= i; ++k) {
            temp += reverseCount[0][split - 1][k];
          }
          if (minValue > temp) {
            match = split;
          }
          minValue = Math.min(minValue, temp);
        }
        dp[i][swap] = minValue;
      }
    }
    return dp[n - 1][K];
  }

  int countAfterReverse(int[] A, int start, int end) {
    int count = 0;
    for (int i = start; i <= end; ++i) {
      for (int j = i + 1; j <= end; ++j) {
        if (A[i] < A[j]) {
          count++;
        }
      }
    }
    return count;
  }
}
