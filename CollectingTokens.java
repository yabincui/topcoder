import java.util.*;

public class CollectingTokens {
  class Node {
    int token;
    Node parent;
    ArrayList<Node> children;
    
    // collections[i] means the tokens can collected by using i steps starting at current node.
    int[] collections;
    // backCollections[i] is similar to collections[i] except that it needs to come back to current node.
    int[] backCollections;

    Node(int token, int L) {
      this.token = token;
      children = new ArrayList<Node>();
      collections = new int[L + 1];
      backCollections = new int[L + 1];
      collections[0] = backCollections[0] = token;
    }

    void collectTokens() {
      int L = collections.length - 1;
      for (int i = 0; i < children.size(); ++i) {
        children.get(i).collectTokens();
      }
      // For backCollections.
      for (int i = 0; i < children.size(); ++i) {
        for (int j = L; j >= 2; --j) {
          for (int k = 2; k <= j; ++k) {
            int newValue = backCollections[j - k] + children.get(i).backCollections[k - 2];
            if (newValue > backCollections[j]) {
              backCollections[j] = newValue;
            }
          }
        }
      }

      // For collections.
      for (int except = 0; except < children.size(); ++except) {
        int[] curBackCollections = new int[L + 1];
        curBackCollections[0] = token;
        for (int i = 0; i < children.size(); ++i) {
          if (i == except) {
            continue;
          }
          for (int j = L; j >= 2; --j) {
            for (int k = 2; k <= j; ++k) {
              int newValue = curBackCollections[j - k] + children.get(i).backCollections[k - 2];
              if (newValue > curBackCollections[j]) {
                curBackCollections[j] = newValue;
              }
            }
          }
        }
        for (int i = L; i >= 1; --i) {
          for (int k = 1; k <= i; ++k) {
            int newValue = curBackCollections[i - k] + children.get(except).collections[k - 1];
            if (newValue > collections[i]) {
              collections[i] = newValue;
            }
          }
        }
      }
    }
  }

  public int maxTokens(int[] A, int[] B, int[] tokens, int L) {
    int n = A.length + 1;

    Node[] nodes = buildNodes(A, B, tokens, L);

    nodes[0].collectTokens();
    int result = 0;
    for (int i = 0; i <= L; ++i) {
      result = Math.max(result, nodes[0].collections[i]);
    }
    return result;
  }

  Node[] buildNodes(int[] A, int[] B, int[] tokens, int L) {
    int n = tokens.length;
    boolean[][] connection = new boolean[n][n];
    for (int i = 0; i < A.length; ++i) {
      int a = A[i] - 1;
      int b = B[i] - 1;
      connection[a][b] = connection[b][a] = true;
    }
    boolean[] visited = new boolean[n];
    Node[] nodes = new Node[n];
    for (int i = 0; i < n; ++i) {
      nodes[i] = new Node(tokens[i], L);
    }
    Queue<Integer> queue = new ArrayDeque<Integer>();
    queue.add(0);
    visited[0] = true;
    while (!queue.isEmpty()) {
      int sizeInCurrentLevel = queue.size();
      while (sizeInCurrentLevel-- != 0) {
        int current = queue.poll();
        for (int i = 0; i < n; ++i) {
          if (connection[current][i] && !visited[i]) {
            nodes[current].children.add(nodes[i]);
            nodes[i].parent = nodes[current];
            visited[i] = true;
            queue.add(i);
          }
        }
      }
    }
    return nodes;
  }
}
