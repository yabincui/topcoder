// http://community.topcoder.com/stat?c=problem_statement&pm=2402&rd=5009
public class BadNeighbors {
  public int maxDonations(int[] donations) {
    int n = donations.length;
    // First we allow people 1 to donate, and don't allow people n to donate.
    // dp1[i] is the max donations of people 1-i.
    int[] dp1 = new int[n + 1];
    dp1[0] = 0;
    dp1[1] = donations[0];
    for (int i = 2; i < n; ++i) {
      dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + donations[i - 1]);
    }

    // Then we don't allow people 1 to donate, and allow people n to donate.
    // dp2[i] is the max donations of people 2-i.
    int[] dp2 = new int[n + 1];
    dp2[0] = 0;
    dp2[1] = 0;
    for (int i = 2; i <= n; ++i) {
      dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + donations[i - 1]);
    }

    int max_dp = 0;
    for (int i = 0; i <= n; ++i) {
      max_dp = Math.max(max_dp, dp1[i]);
      max_dp = Math.max(max_dp, dp2[i]);
    }
    return max_dp;
  }
}
