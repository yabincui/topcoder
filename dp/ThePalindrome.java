// http://community.topcoder.com/stat?c=problem_statement&pm=10182
public class ThePalindrome {
  public int find(String s) {
    int n = s.length();
    for (int i = 0; i < n; ++i) {
      if (isPalindrome(s.substring(i))) {
        return i * 2 + (n - i);
      }
    }
    return 2 * n;
  }
  
  boolean isPalindrome(String s) {
    for (int i = 0, j = s.length() - 1; i < j; ++i, --j) {
      if (s.charAt(i) != s.charAt(j)) {
        return false;
      }
    }
    return true;
  }
}
