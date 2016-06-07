import java.util.*;

public class SimilarNames2 {
  class Node {
    ArrayList<Integer> prefixes = new ArrayList<Integer>();
    ArrayList<Integer> extendIndexes = new ArrayList<Integer>();
  }

  final long MOD = 1000000007L;

  public int count(String[] names, int L) {
    int n = names.length;
    Node[] nodes = new Node[n];
    for (int i = 0; i < n; ++i) {
      nodes[i] = new Node();
    }
    for (int i = 0; i < n; ++i) {
      for (int j = i + 1; j < n; ++j) {
        if (names[i].length() < names[j].length()) {
          if (names[i].equals(names[j].substring(0, names[i].length()))) {
            nodes[j].prefixes.add(i);
            nodes[i].extendIndexes.add(j);
          }
        } else if (names[j].length() < names[i].length()) {
          if (names[j].equals(names[i].substring(0, names[j].length()))) {
            nodes[i].prefixes.add(j);
            nodes[j].extendIndexes.add(i);
          }
        }
      }
    }
    int[] prefixCount = new int[n];
    for (int i = 0; i < n; ++i) {
      prefixCount[i] = nodes[i].prefixes.size();
    }
    boolean[] valid = new boolean[n];
    for (int i = 0; i < n; ++i) {
      valid[i] = true;
    }
    // dp[i][j] means the number of ways to making the first j + 1 items ended with item i.
    long[][] dp = new long[n][L];
    for (int count = 0; count < n; ++count) {
      int k = -1;
      for (int i = 0; i < n; ++i) {
        if (valid[i] == true && prefixCount[i] == 0) {
          k = i;
          break;
        }
      }
      valid[k] = false;
      dp[k][0] = 1;
      for (int i = 1; i < L; ++i) {
        for (Integer j : nodes[k].prefixes) {
          dp[k][i] = (dp[k][i] + dp[j][i - 1]) % MOD;
        }
      }
      for (Integer i : nodes[k].extendIndexes) {
        prefixCount[i]--;
      }
    }

    long result = 0;
    for (int i = 0; i < n; ++i) {
      result = (result + dp[i][L - 1]) % MOD;
    }
    return (int)((result * pow(n - L)) % MOD);
  }

  long pow(int n) {
    long result = 1;
    for (int i = 2; i <= n; ++i) {
      result = (result * i) % MOD;
    }
    return result;
  }
}
