// http://community.topcoder.com/stat?c=problem_statement&pm=1861&rd=4630
public class ShortPalindromes {
  public String shortest(String base) {
    int n = base.length();
    String result = new String();
    int minLength = 2 * n;
    for (int split = 0; split < n - 1; ++split) {
      char[] s = new char[split + 1];
      char[] t = new char[n - 1 - split];
      for (int i = 0; i < s.length; ++i) {
        s[i] = base.charAt(split - i);
      }
      for (int i = 0; i < t.length; ++i) {
        t[i] = base.charAt(split + 1 + i);
      }
      String temp = editDistance(s, t);
      if (temp.length() < minLength || (temp.length() == minLength && temp.compareTo(result) < 0)) {
        result = temp;
        minLength = temp.length();
      }
    }

    for (int split = 0; split <= n - 1; ++split) {
      char[] s = new char[split];
      char[] t = new char[n - 1 - split];
      for (int i = 0; i < s.length; ++i) {
        s[i] = base.charAt(split - 1 - i);
      }
      for (int i = 0; i < t.length; ++i) {
        t[i] = base.charAt(split + 1 + i);
      }
      String temp = editDistance(s, t);
      StringBuilder builder = new StringBuilder(temp);
      builder.insert(temp.length() / 2, base.charAt(split));
      temp = builder.toString();
      if (temp.length() < minLength || (temp.length() == minLength && temp.compareTo(result) < 0)) {
        result = temp;
        minLength = temp.length();
      }
    }
    return result;
  }

  public String editDistance(char[] s, char[] t) {
    int m = s.length;
    int n = t.length;
    int[][] dp = new int[m + 1][n + 1];
    for (int i = 0; i <= m; ++i) {
      dp[i][0] = i;
    }
    for (int j = 0; j <= n; ++j) {
      dp[0][j] = j;
    }
    for (int i = 1; i <= m; ++i) {
      for (int j = 1; j <= n; ++j) {
        int minValue = Integer.MAX_VALUE;
        if (s[i - 1] == t[j - 1]) {
          minValue = Math.min(minValue, dp[i - 1][j - 1]);
        }
        minValue = Math.min(minValue, dp[i - 1][j] + 1);
        minValue = Math.min(minValue, dp[i][j - 1] + 1);
        dp[i][j] = minValue;
      }
    }

    char[] result = new char[m + n + dp[m][n]];
    int i = m;
    int j = n;
    int k = 0;
    while (i != 0 && j != 0) {
      if (s[i - 1] == t[j - 1]) {
        result[k++] = s[i - 1];
        i--;
        j--;
      } else if (dp[i - 1][j] < dp[i][j - 1] || (dp[i - 1][j] == dp[i][j - 1] && s[i - 1] < t[j - 1])) {
        result[k++] = s[i - 1];
        i--;
      } else {
        result[k++] = t[j - 1];
        j--;
      }
    }
    while (i != 0) {
      result[k++] = s[i - 1];
      i--;
    }
    while (j != 0) {
      result[k++] = t[j - 1];
      j--;
    }
    for (; k < result.length; ++k) {
      result[k] = result[result.length - 1 - k];
    }
    return new String(result);
  }
}
