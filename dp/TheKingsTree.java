import java.util.*;

public class TheKingsTree {
  public int getNumber(int[] parent) {
    int n = parent.length + 1;
    ArrayList<ArrayList<Integer>> children = new ArrayList<ArrayList<Integer>>();
    for (int i = 0; i < n; ++i) {
      children.add(new ArrayList<Integer>());
    }
    for (int i = 0; i < n - 1; ++i) {
      int p = parent[i];
      children.get(p).add(i + 1);
    }
    int[] treeSize = new int[n];
    for (int i = n - 1; i >= 0; --i) {
      treeSize[i] = 1;
      for (Integer c : children.get(i)) {
        treeSize[i] += treeSize[c];
      }
    }
    // dp[i][j] means for subtree rooted at node i, having j nodes in red color,
    // the minimum painting cost.
    int[][] dp = new int[n][n + 1];
    dp[n - 1][0] = dp[n - 1][1] = 1;
    for (int i = n - 2; i >= 0; --i) {
      int nodes = children.get(i).size();
      if (nodes == 0) {
        dp[i][0] = dp[i][1] = 1;
        continue;
      }

      int m = treeSize[i];
      // cost[i][j] means comes to children i, the total red nodes size are j.
      int[][] cost = new int[nodes + 1][m];
      for (int j = 0; j <= nodes; ++j) {
        for (int k = 0; k < m; ++k) {
          cost[j][k] = -1;
        }
      }
      cost[0][0] = 0;
      for (int j = 1; j <= nodes; ++j) {
        int childPos = children.get(i).get(j - 1);
        for (int k = 0; k < m; ++k) {
          for (int t = 0; t <= treeSize[childPos] && t <= k; ++t) {
            if (cost[j - 1][k - t] == -1) {
              continue;
            }
            int temp = dp[childPos][t] + cost[j - 1][k - t];
            if (cost[j][k] == -1 || cost[j][k] > temp) {
              cost[j][k] = temp;
            }
          }
        }
      }
      
      dp[i][0] = cost[nodes][0] + m;
      dp[i][m] = cost[nodes][m - 1] + m;
      for (int j = 1; j < m; ++j) {
        dp[i][j] = Math.min(cost[nodes][j] + m - j, cost[nodes][j - 1] + j);
      }
    }
    int minValue = Integer.MAX_VALUE;
    for (int i = 0; i <= treeSize[0]; ++i) {
      minValue = Math.min(minValue, dp[0][i]);
    }
    return minValue;
  }
}
