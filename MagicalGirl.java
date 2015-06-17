import java.util.*;

public class MagicalGirl {
  class Node {
    int day;
    int win;
    int gain;
  }
  class NodeComparator implements Comparator<Node> {
    public int compare(Node n1, Node n2) {
      return n1.day - n2.day;
    }
  }

  public double maxExpectation(int M, int[] day, int[] win, int[] gain) {
    int n = day.length;
    Node[] nodes = new Node[n];
    for (int i = 0; i < n; ++i) {
      Node node = new Node();
      node.day = day[i];
      node.win = win[i];
      node.gain = gain[i];
      nodes[i] = node;
    }
    Arrays.sort(nodes, new NodeComparator());
    
    double[] dp = new double[M + 1];
    for (int i = 1; i <= M; ++i) {
      double p = nodes[n-1].win / 100.0;
      double expectFight = (1 - p) * nodes[n-1].day + p * (Math.min(i + nodes[n-1].gain, M) + nodes[n-1].day);
      double expectNotFight = nodes[n-1].day + i;
      dp[i] = Math.max(expectFight, expectNotFight);
    }
    for (int i = n - 2; i >= 0; --i) {
      double[] ndp = new double[M + 1];
      for (int j = 1; j <= M; ++j) {
        double p = nodes[i].win / 100.0;
        double losePart = (1 - p) * nodes[i].day;
        double winPart = 0.0;
        int dayAfterWin = Math.min(j + nodes[i].gain, M);
        int lastWhenNext = dayAfterWin - (nodes[i+1].day - nodes[i].day);
        if (lastWhenNext > 0) {
          winPart = p * dp[lastWhenNext];
        } else {
          winPart = p * (nodes[i].day + dayAfterWin);
        }
        double expectFight = losePart + winPart;

        double expectNotFight = 0.0;
        lastWhenNext = j - (nodes[i+1].day - nodes[i].day);
        if (lastWhenNext > 0) {
          expectNotFight = dp[lastWhenNext];
        } else {
          expectNotFight = j + nodes[i].day;
        }
        ndp[j] = Math.max(expectFight, expectNotFight);
      }
      dp = ndp;
    }
    if (M > nodes[0].day) {
      return dp[M-nodes[0].day];
    }
    return M;
  }
}
