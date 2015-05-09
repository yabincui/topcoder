public class NumbersChallenge {
  public int MinNumber(int[] s) {
    int sum = 0;
    for (int t : s) {
      sum += t;
    }
    boolean[] dp = new boolean[sum + 1];
    dp[0] = true;
    for (int i = 0; i < s.length; ++i) {
      for (int j = sum; j >= s[i]; --j) {
        if (dp[j - s[i]]) {
          dp[j] = true;
        }
      }
    }
    int result = sum + 1;
    for (int i = 1; i <= sum; ++i) {
      if (dp[i] == false) {
        result = i;
        break;
      }
    }
    return result;
  }
}
