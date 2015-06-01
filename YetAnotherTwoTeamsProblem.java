import java.util.*;

public class YetAnotherTwoTeamsProblem {
  public long count(int[] skill) {
    int n = skill.length;
    int MAX_VALUE = 60000 * n;
    Arrays.sort(skill);
    int sum = 0;
    for (int value : skill) {
      sum += value;
    }

    long result = 0;
    for (int i = 0; i < n; ++i) {
      // dp[i] means the number of ways to achieving sum i.
      long[] dp = new long[MAX_VALUE + 1];
      dp[skill[i]] = 1;
      for (int j = i + 1; j < n; ++j) {
        for (int k = MAX_VALUE; k >= skill[j] + skill[i]; --k) {
          if (dp[k - skill[j]] != 0) {
            dp[k] += dp[k - skill[j]];
          }
        }
      }
      int strictHigher = sum / 2 + 1;
      int strictLower = sum - strictHigher;
      for (int j = strictHigher; j <= strictLower + skill[i]; ++j) {
        result += dp[j];
      }
    }
    return result;
  }
}
