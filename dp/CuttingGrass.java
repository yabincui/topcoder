import java.util.*;

public class CuttingGrass {
  class Node {
    int init;
    int grow;
  }

  class NodeComparator implements Comparator<Node> {
    public int compare(Node n1, Node n2) {
      if (n1.grow != n2.grow) {
        return n2.grow - n1.grow;
      }
      return n2.init - n1.init;
    }
  }

  public int theMin(int[] init, int[] grow, int H) {
    int n = init.length;
    // Try 0 to n days. It must becomes smaller each day.
    // There are at most n days. Whenever a repeat or becomes bigger day happens, we
    // can achieve better result simply by removing that day.
    int sum = 0;
    for (int i = 0; i < init.length; ++i) {
      sum += init[i];
    }
    if (sum <= H) {
      return 0;
    }
    int growPerDay = 0;
    for (int i = 0; i < grow.length; ++i) {
      growPerDay += grow[i];
    }
    Node[] nodes = new Node[n];
    for (int i = 0; i < init.length; ++i) {
      Node node = new Node();
      node.init = init[i];
      node.grow = grow[i];
      nodes[i] = node;
    }
    Arrays.sort(nodes, new NodeComparator());
    for (int day = 1; day <= n; ++day) {
      // dp[i][j] means allocating j days in first i nodes.
      int[][] dp = new int[n + 1][n + 1];
      for (int i = 1; i <= n; ++i) {
        for (int j = 1; j <= i && j <= day; ++j) {
          int temp = dp[i-1][j-1] + (day + 1 - j) * nodes[i-1].grow + nodes[i-1].init;
          dp[i][j] = Math.max(dp[i-1][j], temp);
        }
      }
      sum += growPerDay;
      if (sum - dp[n][day] <= H) {
        return day;
      }
    }
    return -1;
  }
}
