public class PalindromicSubstringsDiv2 {
  public int count(String[] S1, String[] S2) {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < S1.length; ++i) {
      builder.append(S1[i]);
    }
    for (int i = 0; i < S2.length; ++i) {
      builder.append(S2[i]);
    }
    String s = builder.toString();
    int palinCount = 0;
    for (int i = 0; i < s.length(); ++i) {
      palinCount++;
      for (int j = i, t = i + 1; j >= 0 && t < s.length(); --j, ++t) {
        if (s.charAt(j) != s.charAt(t)) {
          break;
        }
        palinCount++;
      }
      for (int j = i - 1, t = i + 1; j >= 0 && t < s.length(); --j, ++t) {
        if (s.charAt(j) != s.charAt(t)) {
          break;
        }
        palinCount++;
      }
    }
    return palinCount;
  }
}
