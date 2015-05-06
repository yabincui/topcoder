// http://community.topcoder.com/stat?c=problem_statement&pm=1215&rd=4555
public class StripePainter {
  public int minStrokes(String stripes) {
    char[] t = stripes.toCharArray();
    char[] s = new char[t.length];
    for (int i = 0; i < s.length; ++i) {
      s[i] = t[0];
    }
    return 1 + MatchString2(s, t, 0);
  }

  public int MatchString(char[] s, char[] t, int start) {
    while (start < s.length && s[start] == t[start]) {
      ++start;
    }
    if (start == s.length) {
      return 0;
    }
    char color = t[start];
    char[] ns = s.clone();
    int stopPos = start;
    ns[stopPos] = color;
    int minValue = s.length;
    while (true) {
      while (stopPos + 1 < s.length && t[stopPos + 1] == color) {
        ns[++stopPos] = color;
      }
      minValue = Math.min(minValue, 1 + MatchString(ns, t, start));
      while (stopPos + 1 < s.length && t[stopPos + 1] != color) {
        ns[++stopPos] = color;
      }
      if (stopPos == s.length - 1) {
        break;
      }
    }
    return minValue;
  }

  // http://community.topcoder.com/tc?module=Static&d1=match_editorials&d2=srm150
  // dp[i][j][k], the count of brushes to paint position i-j, with initialized color k.
  public int MatchString2(char[] s, char[] t, int start) {
    int n = s.length;
    int[][][] dp = new int[n][n][26];
    for (int i = 0; i < n; ++i) {
      for (int k = 0; k < 26; ++k) {
        if (k + 'A' == t[i]) {
          dp[i][i][k] = 0;
        } else {
          dp[i][i][k] = 1;
        }
      }
    }
    for (int len = 2; len <= n; ++len) {
      for (int i = 0; i <= n - len; ++i) {
        int j = i + len - 1;
        for (int k = 0; k < 26; ++k) {
          if (k + 'A' == t[i]) {
            dp[i][j][k] = dp[i + 1][j][k];
          } else {
            int minValue = 1 + dp[i + 1][j][t[i] - 'A'];
            for (int split = i; split < j; ++split) {
              int temp = dp[i][split][t[i] - 'A'] + dp[split + 1][j][k] + 1;
              if (temp < minValue) {
                minValue = temp;
              }
            }
            dp[i][j][k] = minValue;
          }
        }
      }
    }
    return dp[0][n - 1][s[0] - 'A'];
  }
}
