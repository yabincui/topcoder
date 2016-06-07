public class MergeStrings {
  public String getmin(String S, String A, String B) {
    int n = A.length();
    int m = B.length();
    // dp[i][j] means S[i...] can match with A[j...] and corresponding left B.
    boolean[][] dp = new boolean[n + m + 1][n + 1];
    String[][] record = new String[n + m + 1][n + 1];
    dp[n + m][n] = true;
    record[n + m][n] = "";
    for (int i = n + m - 1; i >= 0; --i) {
      for (int j = n; j >= 0; --j) {
        int slen = n + m - i;
        int alen = n - j;
        int blen = slen - alen;
        if (blen < 0 || blen > m) {
          continue;
        }
        int k = m - blen;
        if (dp[i + 1][j] && blen > 0 && (S.charAt(i) == '?' || S.charAt(i) == B.charAt(k))) {
          dp[i][j] = true;
          StringBuilder builder = new StringBuilder();
          builder.append(B.charAt(k));
          builder.append(record[i + 1][j]);
          record[i][j] = builder.toString();
        }
        if (j < n && dp[i + 1][j + 1] && (S.charAt(i) == '?' || S.charAt(i) == A.charAt(j))) {
          dp[i][j] = true;
          StringBuilder builder = new StringBuilder();
          builder.append(A.charAt(j));;
          builder.append(record[i + 1][j + 1]);
          if (record[i][j] == null || builder.toString().compareTo(record[i][j]) < 0) {
            record[i][j] = builder.toString();
          }
        }
      }
    }
    if (dp[0][0] == false) {
      return "";
    }
    return record[0][0];
  }
}
