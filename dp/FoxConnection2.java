import java.util.*;

public class FoxConnection2 {
  class TreeNode {
    int parent;
    ArrayList<Integer> children;

    TreeNode() {
      parent = -1;
      children = new ArrayList<Integer>();
    }
  }

  final long MOD = 1000000007;

  public int ways(int[] A, int[] B, int k) {
    int n = A.length + 1;
    TreeNode[] tree = buildTree(A, B);
    // dp[i][j] means how many ways to form j connected places in subtree rooted at i.
    long[][] dp = new long[n][k + 1];
    // dpWithTop means how many ways to form j connected places including i in subtree rooted at i.
    long[][] dpWithTop = new long[n][k + 1];

    searchTree(0, tree, dp, dpWithTop, n, k);
    return (int)dp[0][k];
  }

  void searchTree(int root, TreeNode[] tree, long[][] dp, long[][] dpWithTop, int n, int k) {
    TreeNode node = tree[root];
    if (node.children.size() == 0) {
      dp[root][0] = 1;
      dp[root][1] = 1;
      dpWithTop[root][0] = 1;
      dpWithTop[root][1] = 1;
      return;
    }
    for (Integer child : node.children) {
      searchTree(child, tree, dp, dpWithTop, n, k);
    }
    
    ArrayList<Integer> children = node.children;
    int childSize = children.size();
    // childDp[i][j] means the number of ways to form j connected places using children[0..i].
    long[][] childDp = new long[childSize][k + 1];
    for (int j = 0; j <= k; ++j) {
      childDp[0][j] = dpWithTop[children.get(0)][j];
    }
    for (int i = 1; i < childSize; ++i) {
      for (int j = 0; j <= k; ++j) {
        for (int t = 0; t <= j; ++t) {
          childDp[i][j] = (childDp[i][j] + childDp[i - 1][t] * dpWithTop[children.get(i)][j - t]) % MOD;
        }
      }
    }
    dpWithTop[root][0] = 1;
    for (int j = 1; j <= k; ++j) {
      dpWithTop[root][j] = childDp[childSize - 1][j - 1];
      dp[root][j] = dpWithTop[root][j];
      for (Integer child : children) {
        dp[root][j] = (dp[root][j] + dp[child][j]) % MOD;
      }
    }
  }

  TreeNode[] buildTree(int[] A, int[] B) {
    int n = A.length + 1;
    TreeNode[] tree = new TreeNode[n];
    for (int i = 0; i < n; ++i) {
      tree[i] = new TreeNode();
    }
    ArrayList<Integer> queue = new ArrayList<Integer>();
    tree[0].parent = -1;  // Use 0 as the root of the tree.
    queue.add(0);
    for (int i = 0; i < queue.size(); ++i) {
      int cur = queue.get(i);
      for (int j = 0; j < n - 1; ++j) {
        if (A[j] - 1 == cur || B[j] - 1== cur) {
          int other = (A[j] - 1 == cur) ? B[j] - 1 : A[j] - 1;
          if (other == tree[cur].parent) {
            continue;
          }
          tree[cur].children.add(other);
          tree[other].parent = cur;
          queue.add(other);
        }
      }
    }
    return tree;
  }
}
