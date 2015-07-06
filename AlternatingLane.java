public class AlternatingLane {
  class Node {
    double probability;
    double beauty;
  }

  public double expectedBeauty0(int[] low, int[] high) {
    int n = low.length;
    if (n == 1) {
      return 0.0;
    }
    int maxValue = 100000;
    Node[] curNodes = new Node[maxValue + 1];
    Node[] nextNodes;
    int valueCount = (high[0] - low[0] + 1);
    double probability = 1.0 / valueCount;
    for (int i = low[0]; i <= high[0]; ++i) {
      Node node = new Node();
      node.probability = probability;
      node.beauty = 0.0;
      curNodes[i] = node;
    }
    for (int i = 1; i < n; ++i) {
      valueCount = (high[i] - low[i] + 1);
      probability = 1.0 / valueCount;
      nextNodes = new Node[maxValue + 1];
      for (int j = low[i]; j <= high[i]; ++j) {
        double sum = 0.0;
        for (int k = 1; k <= maxValue; ++k) {
          if (curNodes[k] != null) {
            double add = curNodes[k].probability * (curNodes[k].beauty + Math.abs(k - j));
            sum += add;
          }
        }
        Node node = new Node();
        node.probability = probability;
        node.beauty = sum;
        nextNodes[j] = node;
      }
      curNodes = nextNodes;
    }
    double expected = 0.0;
    for (int i = 1; i <= maxValue; ++i) {
      if (curNodes[i] != null) {
        expected += curNodes[i].probability * curNodes[i].beauty;
      }
    }
    return expected;
  }

  public double expectedBeauty(int[] low, int[] high) {
    int n = low.length;
    if (n == 1) {
      return 0.0;
    }
    double diffSum = 0.0;
    for (int i = 1; i < n; ++i) {
      // calculate the diff of (low[i-1]~high[i-1]) with (low[i]~high[i]).
      double sum = 0.0;
      for (int j = low[i]; j <= high[i]; ++j) {
        if (j > low[i-1]) {
          int right = Math.min(high[i-1], j-1);
          long a = j - low[i-1];
          long b = j - right;
          double add = (a + b) * (right - low[i-1] + 1) / 2;
          sum += add;
        }
        if (j < high[i-1]) {
          int left = Math.max(j + 1, low[i-1]);
          long a = left - j;
          long b = high[i-1] - j;
          double add = (a + b) * (high[i-1] - left + 1) / 2;
          sum += add;
        }
      }
      sum /= (high[i-1] - low[i-1] + 1);
      sum /= (high[i] - low[i] + 1);
      diffSum += sum;
    }
    return diffSum;
  }
}
