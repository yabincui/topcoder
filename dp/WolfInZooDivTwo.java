import java.util.*;

public class WolfInZooDivTwo {
  final long MOD = 1000000007L;

  class Node implements Comparable<Node> {
    int left;
    int right;
    
    public int compareTo(Node node) {
      return left - node.left;
    }
  }

  public int count(int N, String[] L, String[] R) {
    StringBuilder builder = new StringBuilder();
    for (String elem : L) {
      builder.append(elem);
    }
    String ls = builder.toString();
    String[] strs = ls.split(" ");
    int[] left = new int[strs.length];
    for (int i = 0; i < strs.length; ++i) {
      left[i] = Integer.parseInt(strs[i]);
    }
    builder = new StringBuilder();
    for (String elem : R) {
      builder.append(elem);
    }
    String rs = builder.toString();
    strs = rs.split(" ");
    int[] right = new int[strs.length];
    for (int i = 0; i < strs.length; ++i) {
      right[i] = Integer.parseInt(strs[i]);
    }
    
    ArrayList<Node> nodes = new ArrayList<Node>();
    for (int i = 0; i < left.length; ++i) {
      boolean hasSmallerRange = false;
      for (int j = 0; j < left.length; ++j) {
        if (j == i) {
          continue;
        }
        if (left[i] <= left[j] && right[i] >= right[j]) {
          hasSmallerRange = true;
          break;
        }
      }
      if (hasSmallerRange) {
        continue;
      }
      boolean hasSameRange = false;
      for (int j = 0; j < nodes.size(); ++j) {
        if (left[i] == nodes.get(j).left && right[i] == nodes.get(j).right) {
          hasSameRange = true;
          break;
        }
      }
      if (!hasSameRange) {
        Node node = new Node();
        node.left = left[i];
        node.right = right[i];
        nodes.add(node);
      }
    }
    Collections.sort(nodes);
    
    int n = N;
    int m = nodes.size();
    // dp[i][j] means using first 0..i-1 items to fulfill the requirement of 0..j - 1.
    long[][] dp = new long[n + 1][m + 1];
    dp[0][0] = 1;
    for (int i = 1; i <= n; ++i) {
      dp[i][0] = (dp[i - 1][0] * 2) % MOD;
      for (int j = 1; j <= m; ++j) {
        int requireEnd = nodes.get(j - 1).right;
        int requireBegin = nodes.get(j - 1).left;
        if (requireEnd < i - 1) {
          dp[i][j] = (dp[i - 1][j] * 2) % MOD;
        } else if (requireBegin <= i - 1) {
          dp[i][j] = dp[i - 1][j];
          int nj = j - 1;
          for (; nj >= 1 && i - 1 <= nodes.get(nj - 1).right; nj--) {
          }
          dp[i][j] = (dp[i][j] + dp[i - 1][nj]) % MOD;
        }
      }
    }
    return (int)dp[n][m];
  }
}
