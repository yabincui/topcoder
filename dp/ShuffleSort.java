import java.util.*;

public class ShuffleSort {
  public double shuffle(int[] cards) {
    int n = cards.length;
    Arrays.sort(cards);
    double result = 1.0;
    for (int i = 0; i < n; ++i) {
      int equalNum = 0;
      int allNum = n - i;
      for (int j = i; j < n; ++j) {
        if (cards[j] == cards[i]) {
          equalNum++;
        } else {
          break;
        }
      }
      double expect = getExpect(equalNum, allNum);
      result += expect;
    }
    return result;
  }

  double getExpect(int k, int n) {
    double p = (double)k / n;
    return (1 - p) / p;
  }
}
