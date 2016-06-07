public class StrIIRec {
  public String recovstr(int n, int minInv, String minStr) {
    boolean[] valid = new boolean[n];
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < n; ++i) {
      valid[i] = true;
    }
    int curInv = 0;
    boolean biggerThanMinStr = false;
    for (int i = 0; i < n; ++i) {
      int startPos = 0;
      if (minStr.length() < i + 1) {
        biggerThanMinStr = true;
      }
      if (!biggerThanMinStr) {
        startPos = minStr.charAt(i) - 'a';
      }
      int invCount = 0;
      int leftMaxInvCount = (n - 1 - i) * (n - 2 - i) / 2;
      boolean findMatch = true;
      for (int j = 0; j < n; ++j) {
        if (!valid[j]) {
          continue;
        }
        if (j < startPos) {
          invCount++;
          continue;
        }
        if (curInv + invCount + leftMaxInvCount >= minInv) {
          findMatch = true;
          result.append((char)(j + 'a'));
          curInv += invCount;
          if (!biggerThanMinStr && j + 'a' > minStr.charAt(i)) {
            biggerThanMinStr = true;
          }
          valid[j] = false;
          break;
        }
        invCount++;
      }
      if (!findMatch) {
        return "";
      }
    }
    return result.toString();
  }
}
