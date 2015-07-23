import java.util.*;

public class Marketing {
  public long howMany(String[] compete) {
    int n = compete.length;
    boolean[][] competeMap = new boolean[n][n];
    for (int i = 0; i < n; ++i) {
      if (compete[i].length() == 0) {
        continue;
      }
      String[] strs = compete[i].split(" ");
      for (String s : strs) {
        int j = Integer.parseInt(s);
        competeMap[i][j] = competeMap[j][i] = true;
      }
    }
    boolean[] sellSet = new boolean[n];
    int result = 1;
    for (int i = 0; i < n; ++i) {
      if (sellSet[i] == false) {
        if (!fillSet(i, competeMap, sellSet)) {
          result = -1;
          break;
        } else {
          result *= 2;
        }
      }
    }
    return result;
  }

  boolean fillSet(int start, boolean[][] competeMap, boolean[] sellSet) {
    int n = sellSet.length;
    int[] currentSell = new int[n];  // 0 not sell, 1 sell to adults, 2 sell to teenagers.
    Stack<Integer> stack = new Stack<Integer>();
    currentSell[start] = 1;
    stack.push(start);
    while (!stack.empty()) {
      int curr = stack.pop();
      for (int i = 0; i < n; ++i) {
        if (competeMap[i][curr]) {
          if (currentSell[i] == 0) {
            currentSell[i] = 3 - currentSell[curr];
            stack.push(i);
          } else if (currentSell[i] != 3 - currentSell[curr]) {
            return false;
          }
        }
      }
    }
    for (int i = 0; i < n; ++i) {
      if (currentSell[i] != 0) {
        sellSet[i] = true;
      }
    }
    return true;
  }
}
