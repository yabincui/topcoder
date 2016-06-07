public class WinterAndReindeers {
  public int getNumber(String[] allA, String[] allB, String[] allC) {
    String A = buildString(allA);
    String B = buildString(allB);
    String C = buildString(allC);
    
    int n = A.length();
    int m = B.length();
    // commonPrefix[i][j] is the common sequence of A[0 .. i - 1] and B[0 .. j - 1].
    int[][] commonPrefix = new int[n + 1][m + 1];
    for (int i = 1; i <= n; ++i) {
      for (int j = 1; j <= m; ++j) {
        if (A.charAt(i - 1) == B.charAt(j - 1)) {
          commonPrefix[i][j] = commonPrefix[i - 1][j - 1] + 1;
        } else {
          commonPrefix[i][j] = Math.max(commonPrefix[i - 1][j], commonPrefix[i][j - 1]);
        }
      }
    }

    // commonSuffix[i][j] is the common sequence of A[i .. n - 1] and B[j .. m - 1].
    int[][] commonSuffix = new int[n + 1][m + 1];
    for (int i = n - 1; i >= 0; --i) {
      for (int j = m - 1; j >= 0; --j) {
        if (A.charAt(i) == B.charAt(j)) {
          commonSuffix[i][j] = commonSuffix[i + 1][j + 1] + 1;
        } else {
          commonSuffix[i][j] = Math.max(commonSuffix[i + 1][j], commonSuffix[i][j + 1]);
        }
      }
    }

    // fulfillA[i] means the smallest pos to fulfill requirement: C is a subsequence of A[i, fulfillA[i]].
    int[] fulfillA = new int[n];
    for (int i = 0; i < n; ++i) {
      fulfillA[i] = -1;
    }
    for (int i = 0; i < n; ++i) {
      int match = 0;
      for (int j = i; j < n && match < C.length(); ++j) {
        if (A.charAt(j) == C.charAt(match)) {
          ++match;
          if (match == C.length()) {
            fulfillA[i] = j;
          }
        }
      }
    }

    // fulfillB[i] means the smallest pos to fulfill requirement: C is a subsequence of B[i, fulfillB[i]].
    int[] fulfillB = new int[m];
    for (int i = 0; i < m; ++i) {
      fulfillB[i] = -1;
    }
    for (int i = 0; i < m; ++i) {
      int match = 0;
      for (int j = i; j < m && match < C.length(); ++j) {
        if (B.charAt(j) == C.charAt(match)) {
          ++match;
          if (match == C.length()) {
            fulfillB[i] = j;
          }
        }
      }
    }

    int result = 0;
    for (int i = 0; i < n; ++i) {
      if (fulfillA[i] == -1) {
        continue;
      }
      for (int j = 0; j < m; ++j) {
        if (fulfillB[j] == -1) {
          continue;
        }
        int ni = fulfillA[i] + 1;
        int nj = fulfillB[j] + 1;
        int length = commonPrefix[i][j] + C.length() + commonSuffix[ni][nj];
        result = Math.max(result, length);
      }
    }
    return result;
  }

  String buildString(String[] allS) {
    StringBuilder builder = new StringBuilder();
    for (String s : allS) {
      builder.append(s);
    }
    return builder.toString();
  }
}
