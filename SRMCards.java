// http://community.topcoder.com/stat?c=problem_statement&pm=11341
import java.util.Arrays;

public class SRMCards {
  public int maxTurnsWrong(int[] cards) {
    int n = cards.length;
    Arrays.sort(cards);

    // withEnd[i] is the max turns to remove 0 to i, the last choose card is i.
    int[] withEnd = new int[n];
    // withoutEnd[i] is the max turns to remove 0 to i, the last choose card is not i.
    int[] withoutEnd = new int[n];

    withEnd[0] = 1;
    withoutEnd[0] = 0;
    for (int i = 1; i < n; ++i) {
      if (cards[i] == cards[i - 1] + 1) {
        withEnd[i] = withoutEnd[i - 1] + 1;
        withoutEnd[i] = withEnd[i - 1];
      } else {
        withEnd[i] = Math.max(withEnd[i - 1], withoutEnd[i - 1]) + 1;
        withoutEnd[i] = 0;
      }
    }
    return Math.max(withEnd[n - 1], withoutEnd[n - 1]);
  }

  public int maxTurns(int[] cards) {
    int n = cards.length;
    Arrays.sort(cards);
    
    // withEnd[i] is the max turns to remove 0 to i, the last choose card is i.
    int[] withEnd = new int[n];
    // dp[i] is the max turns to remove 0 to i.
    int[] dp = new int[n];

    withEnd[0] = 1;
    dp[0] = 1;
    for (int i = 1; i < n; ++i) {
      if (cards[i] == cards[i - 1] + 1) {
        if (i == 1) {
          withEnd[i] = 1;
        } else {
          withEnd[i] = dp[i - 2] + 1;
        }
        dp[i] = Math.max(withEnd[i], withEnd[i - 1]);
      } else {
        withEnd[i] = dp[i - 1] + 1;
        dp[i] = withEnd[i];
      }
    }
    return dp[n - 1];
  }
}
