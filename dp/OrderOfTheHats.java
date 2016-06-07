import java.util.*;

public class OrderOfTheHats {
  public int minChanged(String[] spellChart) {
    int n = spellChart.length;
    int maxState = (1 << n) - 1;
    int[] dp = new int[maxState + 1];
    for (int i = 0; i <= maxState; ++i) {
      dp[i] = -1;
    }
    int startState = process(maxState, spellChart);
    dp[startState] = 0;

    ArrayList<Integer> permute = getPermute(n);

    HashMap<Integer, Integer> processMap = new HashMap<Integer, Integer>();
    
    for (int i = 0; i < permute.size(); ++i) {
      int prevState = permute.get(i);
      if (dp[prevState] == -1) {
        continue;
      }
      for (int j = 0; j < n; ++j) {
        if ((prevState & (1 << j)) == 0) {
          continue;
        }
        int state = prevState & ~(1 << j);
        int cost = dp[prevState];
        for (int k = 0; k < n; ++k) {
          if ((prevState & (1 << k)) == 0) {
            continue;
          }
          if (spellChart[k].charAt(j) == 'Y') {
            ++cost;
          }
        }
        Integer t = processMap.get(state);
        if (t == null) {
          t = process(state, spellChart);
          processMap.put(state, t);
        }
        state = t;
        if (dp[state] == -1 || dp[state] > cost) {
          dp[state] = cost;
        }
      }
    }
    return dp[0];
  }

  ArrayList<Integer> getPermute(int n) {
    int maxState = (1 << n) - 1;
    ArrayList<Integer> list = new ArrayList<Integer>();
    for (int bitnum = n; bitnum > 0; --bitnum) {
      getPermute(0, bitnum, 0, n, list);
    }
    list.add(0);
    return list;
  }

  void getPermute(int bit, int lastBits, int value, int n, ArrayList<Integer> list) {
    if (lastBits == 0) {
      list.add(value);
      return;
    }
    for (; bit <= n - lastBits; ++bit) {
      getPermute(bit + 1, lastBits - 1, value | (1 << bit), n, list);
    }
  }

  int process(int state, String[] spellChart) {
    int originState = state;
    int n = spellChart.length;
    boolean changeFlag = true;
    while (changeFlag) {
      changeFlag = false;
      for (int i = 0; i < n; ++i) {
        if ((state & (1 << i)) != 0) {
          int prevCount = 0;
          for (int j = 0; j < n; ++j) {
            if ((state & (1 << j)) == 0) {
              continue;
            }
            if (spellChart[j].charAt(i) == 'Y') {
              ++prevCount;
            }
          }
          if (prevCount == 0) {
            state &= ~(1 << i);
            changeFlag = true;
          }
        }
      }
    }
    return state;
  }
}
