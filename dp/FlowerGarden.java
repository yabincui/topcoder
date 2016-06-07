public class FlowerGarden {
  public int[] getOrdering(int[] height, int[] bloom, int[] wilt) {
    // find the prev, but it may have multiple prev, someone have no prev, select
    // O(n^2), like the shortest path.
    int n = height.length;
    int[] result = new int[n];
    int[] prevCount = new int[n];

    // Init prev count.
    for (int i = 0; i < n; ++i) {
      for (int j = i + 1; j < n; ++j) {
        int cmpres = Compare(height[i], bloom[i], wilt[i], height[j], bloom[j], wilt[j]);
        if (cmpres < 0) {
          prevCount[j]++;
        } else if (cmpres > 0) {
          prevCount[i]++;
        }
      }
    }

    boolean[] used = new boolean[n];
    for (int fill = 0; fill < n; ++fill) {
      int max_pos = -1;
      int max_h = 0;
      for (int i = 0; i < n; ++i) {
        if (used[i] == true || prevCount[i] > 0) {
          continue;
        }
        if (height[i] > max_h) {
          max_h = height[i];
          max_pos = i;
        }
      }
      used[max_pos] = true;
      result[fill] = max_h;

      // Remove dependence.
      for (int i = 0; i < n; ++i) {
        if (used[i] == true || prevCount[i] == 0) {
          continue;
        }
        if (Compare(height[max_pos], bloom[max_pos], wilt[max_pos], height[i], bloom[i], wilt[i]) < 0) {
          prevCount[i]--;
        }
      }
    }
    return result;
  }

  // Return <0 if h1 should before h2, return >0 if h1 should after h2.
  // Return 0 if they have no strict order.
  private int Compare(int h1, int b1, int w1, int h2, int b2, int w2) {
    if (b1 > w2 || w1 < b2) {
      return 0;
    }
    return h1 - h2;
  }
}
